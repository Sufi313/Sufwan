package com.taggroup.www.darzeeco.CustomerAct;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taggroup.www.darzeeco.R;

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
    public void onBindViewHolder(CustomCartAdapter.CustomCartViewHolder holder, int position) {
        final CustomCart customCart = customCartList.get(position);

        holder.type.setText(customCart.getDesign_Type());
        holder.price.setText(String.valueOf(customCart.getOrder_amount()));
        holder.size.setText(String.valueOf(customCart.getSize_id()));

    }


    @Override
    public int getItemCount() {
        return customCartList.size();
    }

    public class CustomCartViewHolder extends RecyclerView.ViewHolder {
        TextView type, price, size;

        public CustomCartViewHolder(View itemView) {
            super(itemView);

            type = (TextView)itemView.findViewById(R.id.customCartDesc);
            price = (TextView)itemView.findViewById(R.id.customCartPrice);
            size = (TextView)itemView.findViewById(R.id.customCartSize);
        }
    }
}
