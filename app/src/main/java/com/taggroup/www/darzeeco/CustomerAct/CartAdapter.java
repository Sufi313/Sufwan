package com.taggroup.www.darzeeco.CustomerAct;


import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import com.nostra13.universalimageloader.utils.L;
import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.Utils.DeleteStanderdCart;


import java.util.HashMap;
import java.util.List;

import static android.os.Build.VERSION.SDK;


/**
 * Created by muhammad.sufwan on 12/15/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mCtx;
    private List<Cart> cartList;
    private int itemLayout;

    public CartAdapter(Context mCtx, List<Cart> cartList, int itemLayout) {
        this.mCtx =  mCtx;
        this.cartList = cartList;
        this.itemLayout = itemLayout;
    }



    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // You can use here an API which was added in Lollipop.
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.cart_list, null);
            return new CartViewHolder(view);
        }
        View v = LayoutInflater.from(mCtx).inflate(itemLayout, parent, false);
        return new CartViewHolder(v);


    }



    @Override
    public void onBindViewHolder(final CartAdapter.CartViewHolder holder, int position) {
        final Cart cart = cartList.get(position);

        Glide.with(mCtx)
                .load("http://192.168.2.41/darzee/"+cart.getImage())
                .into(holder.imageView);

        holder.textViewName.setText(cart.getDesign_type());
        holder.textViewPrice.setText(String.valueOf(cart.getPrice()));
        holder.textViewDesc.setText("this is dummy");

        holder.removePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteStanderdCart deleteDataFromServer = new DeleteStanderdCart(mCtx);
                deleteDataFromServer.DeleteItem(String.valueOf(cart.getId()));
                delete(holder.getAdapterPosition());

            }
        });

    }

    public void delete(int position) { //removes the row
        cartList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewPrice, textViewDesc;
        ImageView imageView;
        Button removePro;

        public CartViewHolder(View itemView) {
            super(itemView);


            textViewDesc = itemView.findViewById(R.id.tvDesc);
            textViewName = itemView.findViewById(R.id.tvName);
            textViewPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.ivImage);
            removePro = itemView.findViewById(R.id.btnRemove);


        }
    }

}
