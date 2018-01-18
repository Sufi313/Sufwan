package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

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
import com.taggroup.www.darzeeco.R;

import java.util.List;

/**                                                                                        **
 * Created by muhammad.sufwan on 1/17/2018 at 5:16 pm in operating os microsoft windows 10.1*
 **                                                                                        **/

public class CustomProductAdapter extends RecyclerView.Adapter<CustomProductAdapter.CustomProductViewHolder> {

    private Context mCtx;
    private List<CustomProduct> customProductList;

    private int lastSelectedPosition = -1;

    public CustomProductAdapter(Context mCtx, List<CustomProduct> customProductList) {
        this.mCtx = mCtx;
        this.customProductList = customProductList;
    }

    @Override
    public CustomProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_product_list,null);
        return new CustomProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomProductViewHolder holder, int position) {
            final CustomProduct customProduct = customProductList.get(position);


        Glide.with(mCtx)
                .load("http://192.168.2.41/darzee/" + customProduct.getImage())
                .into(holder.customImage);

        holder.customName.setText(customProduct.getName());
        holder.selectButton.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return customProductList.size();
    }

    public class CustomProductViewHolder extends RecyclerView.ViewHolder {
        ImageView customImage;
        TextView customName;
        RadioButton selectButton;

        public CustomProductViewHolder(View itemView) {
            super(itemView);

            customImage=(ImageView)itemView.findViewById(R.id.customImage);
            customName=(TextView) itemView.findViewById(R.id.customName);
            selectButton=(RadioButton)itemView.findViewById(R.id.selectButton);

            selectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Toast.makeText(mCtx, ""+customName.getText(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
