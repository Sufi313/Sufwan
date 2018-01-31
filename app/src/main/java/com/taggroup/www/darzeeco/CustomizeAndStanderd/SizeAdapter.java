package com.taggroup.www.darzeeco.CustomizeAndStanderd;

/**
 * Created by muhammad.sufwan on 11/28/2017.
 */


import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.taggroup.www.darzeeco.ProductsAndContents.Product;
import com.taggroup.www.darzeeco.ProductsAndContents.ProductDetailActivity;
import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.Utils.DeleteDataFromServer;

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
    public void onBindViewHolder(final SizeAdapter.SizeViewHolder holder, final int position) {
        final Sizes sizes = sizeList.get(position);

        //loading the image
        holder.nameSize.setText(sizes.getSizeName());
        holder.sizeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LetSelectSizePrefMngr.getInstance(mCtx).isSizeIn()) {
                    LetSelectSizePrefMngr.getInstance(mCtx).ClearSize();
                }

                LetSelectSizePrefMngr.getInstance(mCtx).selectSize(sizes.getId(), sizes.getSizeName());
                Intent i = new Intent(mCtx, SelectDesignCategory.class);
                i.putExtra("size_id", sizes.getId());
                i.putExtra("user_size_name", sizes.getSizeName());
                mCtx.startActivity(i);

            }
        });
        holder.sizeImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getRootView().getContext())
                .setMessage("Do you really want to delete this size")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DeleteDataFromServer deleteDataFromServer = new DeleteDataFromServer(mCtx);
                                deleteDataFromServer.DeleteSize(String.valueOf(sizes.getId()));
                                delete(holder.getAdapterPosition());
                                Toast.makeText(mCtx, String.valueOf(sizes.getId()), Toast.LENGTH_SHORT).show();
                            }
                        });
               final AlertDialog dialog = alertDialog.create();
                dialog.setTitle("Delete");
                dialog.show();

                return true;
            }
        });

    }

    public void delete(int position) { //removes the row
        sizeList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
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
//            sizeImage.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Toast.makeText(mCtx, "Are you show you want to delete this size", Toast.LENGTH_SHORT).show();
//                    delete(getAdapterPosition());
//                    return true;
//                }
//            });
        }
    }

}
