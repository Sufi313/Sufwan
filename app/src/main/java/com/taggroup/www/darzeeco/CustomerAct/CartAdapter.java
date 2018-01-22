package com.taggroup.www.darzeeco.CustomerAct;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import com.taggroup.www.darzeeco.R;


import java.util.HashMap;
import java.util.List;


/**
 * Created by muhammad.sufwan on 12/15/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mCtx;
    private List<Cart> cartList;

    public CartAdapter(Context mCtx, List<Cart> cartList) {
        this.mCtx =  mCtx;
        this.cartList = cartList;
    }



    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_list, null);
        return new CartViewHolder(view);
    }



    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {
        final Cart cart = cartList.get(position);

        Glide.with(mCtx)
                .load("http://192.168.2.41/darzee/"+cart.getImage())
                .into(holder.imageView);

        holder.textViewName.setText(cart.getDesign_type());
        holder.textViewPrice.setText("Rs:"+String.valueOf(cart.getPrice()));
        holder.textViewDesc.setText("this is dummy");

        holder.removePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap postData = new HashMap();
                postData.put("pid", cart.getId());




            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewQuantity, textViewPrice, textViewDesc;
        ImageView imageView,incQty,decQty;
        Button removePro;

        public CartViewHolder(View itemView) {
            super(itemView);


            textViewDesc = itemView.findViewById(R.id.tvDesc);
            textViewName = itemView.findViewById(R.id.tvName);
            textViewQuantity = itemView.findViewById(R.id.edQty);
            textViewPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.ivImage);
            incQty = itemView.findViewById(R.id.qty_increase);
            decQty = itemView.findViewById(R.id.qty_decrease);
            removePro = itemView.findViewById(R.id.btnRemove);


        }
    }

}
