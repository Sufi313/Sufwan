package com.taggroup.www.darzeeco.CustomizeAndStanderd.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.CustomProduct;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.LetSelectSizePrefMngr;
import com.taggroup.www.darzeeco.R;

import java.util.List;

/**
 * *
 * Created by muhammad.sufwan on 1/17/2018 at 5:16 pm in operating os microsoft windows 10.1*
 * *
 **/

public class CustomProductAdapterDupatta extends RecyclerView.Adapter<CustomProductAdapterDupatta.CustomProductViewHolder> {

    private Context mCtx;
    private List<CustomProduct> customProductList;

    private int lastSelectedPosition = -1;

    public CustomProductAdapterDupatta(Context mCtx, List<CustomProduct> customProductList) {
        this.mCtx = mCtx;
        this.customProductList = customProductList;
    }

    @Override
    public CustomProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_product_list, null);
        return new CustomProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomProductViewHolder holder, int position) {
        final CustomProduct customProduct = customProductList.get(position);


        holder.customName.setText(customProduct.getName());
        holder.customPrice.setText("Cost: " + String.valueOf(customProduct.getPrice()));

        Glide.with(mCtx)
                .load("http://192.168.2.41/darzee/" + customProduct.getImage())
                .into(holder.customImage);

        holder.selectButton.setChecked(lastSelectedPosition == position);

        holder.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();


                if (LetSelectSizePrefMngr.getInstance(mCtx).isDupattaIn()) {
                    LetSelectSizePrefMngr.getInstance(mCtx).ClearSelectDupatta();
                }

                LetSelectSizePrefMngr.getInstance(mCtx).selectDupatta(customProduct.getId(), customProduct.getName()
                        ,customProduct.getPrice(), customProduct.getImage());

                Toast.makeText(mCtx, customProduct.getId() + "\n" +
                        customProduct.getName() + "\n" +
                        customProduct.getPrice() + "\n" +
                        customProduct.getImage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return customProductList.size();
    }

    public class CustomProductViewHolder extends RecyclerView.ViewHolder {
        ImageView customImage;
        TextView customName, customPrice;
        RadioButton selectButton;

        public CustomProductViewHolder(View itemView) {
            super(itemView);

            customPrice = itemView.findViewById(R.id.customPrice);
            customImage = itemView.findViewById(R.id.customImage);
            customName = itemView.findViewById(R.id.customName);
            selectButton = itemView.findViewById(R.id.selectButton);


        }
    }
}
