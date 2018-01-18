package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taggroup.www.darzeeco.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by muhammad.sufwan on 1/15/2018.
 */

public class StanderdProductAdapter extends RecyclerView.Adapter<StanderdProductAdapter.StanderdProductViewHolder> {

    private Context mCtx;
    private List<StanderdProduct> standerProductList;
//    private LayoutInflater mInflater;


    public StanderdProductAdapter( Context mCtx, List<StanderdProduct> standerProductList) {
        this.mCtx = mCtx;
        this.standerProductList = standerProductList;
//        mInflater = LayoutInflater.from(mCtx);


    }

    @Override
    public StanderdProductAdapter.StanderdProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.stander_product_list, null);
        return new StanderdProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StanderdProductAdapter.StanderdProductViewHolder holder,final int position) {

        final StanderdProduct standerdProduct = standerProductList.get(position);
        Glide.with(mCtx)
                .load("http://192.168.2.41/darzee/" + standerdProduct.getImage())
                .into(holder.showImage);

        holder.designType.setText(standerdProduct.getType());
        holder.designId.setText("ST00" + standerdProduct.getId());
        holder.selectDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.viewDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(mCtx);
                dialog.setContentView(R.layout.show_standerd_product);
                dialog.setTitle("Position" + position);
                dialog.setCancelable(true);

                 ImageView d_image = (ImageView) dialog.findViewById(R.id.dialog_design_image);
                 TextView d_type = (TextView) dialog.findViewById(R.id.dialog_design_type);
                 TextView d_name = (TextView) dialog.findViewById(R.id.dialog_design_name);
                 TextView d_cost = (TextView) dialog.findViewById(R.id.dialog_design_cost);


                d_type.setText(standerdProduct.getType());
                d_name.setText(standerdProduct.getName());
                d_cost.setText("Cost "+String.valueOf(standerdProduct.getAmount()));

                Glide.with(mCtx)
                        .load("http://192.168.2.41/darzee/" + standerdProduct.getImage())
                        .into(d_image);


                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return standerProductList.size();
    }


    public class StanderdProductViewHolder extends RecyclerView.ViewHolder {

        TextView designType, designId, selectDesign, viewDesign;
        ImageView showImage;


        public StanderdProductViewHolder(final View itemView) {
            super(itemView);

//            d_image = (ImageView) itemView.findViewById(R.id.dialog_design_image);
//            d_type = (TextView) itemView.findViewById(R.id.dialog_design_type);
//            d_name = (TextView) itemView.findViewById(R.id.dialog_design_name);
//            d_cost = (TextView) itemView.findViewById(R.id.dialog_design_cost);

            designType = (TextView) itemView.findViewById(R.id.standerdTextCtg);
            designId = (TextView) itemView.findViewById(R.id.standerd_design_id);
            selectDesign = (TextView) itemView.findViewById(R.id.selectDesign);
            viewDesign = (TextView) itemView.findViewById(R.id.selectDesignView);
            showImage = (ImageView) itemView.findViewById(R.id.imageStanderd);

//            viewDesign.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    StanderdProduct standerdProduct = (StanderdProduct) v.getTag();
//
//                    LayoutInflater inflater = LayoutInflater.from(mCtx);
//                    AlertDialog.Builder myDialog = new AlertDialog.Builder(mCtx);
//                    View myView = inflater.inflate(R.layout.show_standerd_product, null);
//                    myDialog.setView(myView);
//
//                    AlertDialog dialog = myDialog.create();
//
//                    ImageView d_image = (ImageView) dialog.findViewById(R.id.dialog_design_image);
//                    TextView d_type = (TextView) dialog.findViewById(R.id.dialog_design_type);
//                    TextView d_name = (TextView) dialog.findViewById(R.id.dialog_design_name);
//                    TextView d_cost = (TextView) dialog.findViewById(R.id.dialog_design_cost);
//
//                    d_type.setText(standerdProduct.getType());
//                    d_name.setText(standerdProduct.getName());
//                    d_cost.setText(String.valueOf(standerdProduct.getAmount()));
//
//                    Glide.with(mCtx)
//                            .load("http://192.168.2.41/darzee/" + standerdProduct.getImage())
//                            .into(d_image);
//
//
//                    dialog.show();
//
//
//
//                }
//            });
        }
    }
}