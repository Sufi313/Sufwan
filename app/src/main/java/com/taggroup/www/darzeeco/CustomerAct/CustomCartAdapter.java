package com.taggroup.www.darzeeco.CustomerAct;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.Utils.DeleteCustomCart;

import java.util.List;

/**
 * Created by muhammad.sufwan on 1/26/2018.
 */

public class CustomCartAdapter extends RecyclerView.Adapter<CustomCartAdapter.CustomCartViewHolder> {

    private Context mCtx;
    private List<CustomCart> customCartList;

    public CustomCartAdapter(Context mCtx, List<CustomCart> customCartList) {
        this.mCtx =  mCtx;
        this.customCartList = customCartList;
    }



    @Override
    public CustomCartAdapter.CustomCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_cart_list, null);
        return new CustomCartAdapter.CustomCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomCartAdapter.CustomCartViewHolder holder, final int position) {
        final CustomCart customCart = customCartList.get(position);

        holder.type.setText(customCart.getDesign_Type());
        holder.price.setText(String.valueOf(customCart.getOrder_amount()));
        holder.size.setText(String.valueOf(customCart.getSize_id()));
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCustomCart deleteCustomCart = new DeleteCustomCart(mCtx);
                deleteCustomCart.DeleteItem(String.valueOf(customCart.getId()));
                delete(holder.getAdapterPosition());
            }
        });

    }


    public void delete(int position) { //removes the row
        customCartList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return customCartList.size();
    }

    public class CustomCartViewHolder extends RecyclerView.ViewHolder {
        TextView type, price, size;
        Button remove;

        public CustomCartViewHolder(View itemView) {
            super(itemView);
            remove= (Button)itemView.findViewById(R.id.removeCart);
            type = (TextView)itemView.findViewById(R.id.customCartDesc);
            price = (TextView)itemView.findViewById(R.id.customCartPrice);
            size = (TextView)itemView.findViewById(R.id.customCartSize);
        }
    }
}
