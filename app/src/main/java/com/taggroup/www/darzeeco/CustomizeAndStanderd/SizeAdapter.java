package com.taggroup.www.darzeeco.CustomizeAndStanderd;

/**
 * Created by muhammad.sufwan on 11/28/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taggroup.www.darzeeco.ProductsAndContents.Product;
import com.taggroup.www.darzeeco.ProductsAndContents.ProductDetailActivity;
import com.taggroup.www.darzeeco.R;

import java.util.List;


public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder> {





    private Context mCtx;
    private List<Sizes> sizeList;

    public SizeAdapter(Context mCtx, List<Sizes> sizeList) {
        this.mCtx = mCtx;
        this.sizeList = sizeList;
    }


    @Override
    public SizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_size, null);
        return new SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SizeAdapter.SizeViewHolder holder, int position) {
        final Sizes sizes = sizeList.get(position);

        //loading the image
        holder.nameSize.setText(sizes.getSizeName());
        holder.sizeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mCtx,SelectDesignCategory.class);
                i.putExtra("size_id",sizes.getId());
                i.putExtra("user_size_name",sizes.getSizeName());
                mCtx.startActivity(i);

            }
        });
    }



    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    class SizeViewHolder extends RecyclerView.ViewHolder {

        TextView nameSize;
        ImageView sizeImage;

        public SizeViewHolder(View itemView) {
            super(itemView);

            nameSize = itemView.findViewById(R.id.size_name);
            sizeImage = itemView.findViewById(R.id.image_size);

        }
    }

}