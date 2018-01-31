package com.taggroup.www.darzeeco.CustomizeAndStanderd.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.taggroup.www.darzeeco.CustomerAct.Track;
import com.taggroup.www.darzeeco.R;

import java.util.List;

/**
 * Created by muhammad.sufwan on 1/31/2018.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {


    private Context mCtx;
    private List<Track> orderList;

    public TrackAdapter(Context mCtx, List<Track> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }


    @Override
    public TrackAdapter.TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.track_order_list, null);
        return new TrackAdapter.TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackAdapter.TrackViewHolder holder, int position) {
            final Track track = orderList.get(position);
            holder.orderdate.setText(track.getInvoice_date());
            holder.totalAmount.setText(String.valueOf(track.getAmount()));
            holder.orderstatus.setText(track.getStatus());
            holder.pickupstatus.setText(track.getPickUp_status());
            holder.deliverstatus.setText(track.getDeliver_status());
            holder.shippingAddress.setText(track.getAddress()+", "+track.getShipping_address());
            holder.invoiceNumber.setText("DCD00"+track.getId());

            animate(holder);
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mCtx, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView orderdate, invoiceNumber, totalAmount, orderstatus, pickupstatus, deliverstatus, shippingAddress;
        Button orderView;
        public TrackViewHolder(View itemView) {
            super(itemView);

            orderdate=(TextView)itemView.findViewById(R.id.orderdate);
            invoiceNumber=(TextView)itemView.findViewById(R.id.invoiceNumber);
            totalAmount=(TextView)itemView.findViewById(R.id.totalAmount);
            orderstatus=(TextView)itemView.findViewById(R.id.orderstatus);
            pickupstatus=(TextView)itemView.findViewById(R.id.pickupstatus);
            deliverstatus=(TextView)itemView.findViewById(R.id.deliverstatus);
            shippingAddress=(TextView)itemView.findViewById(R.id.shippingAddress);
            orderView = (Button)itemView.findViewById(R.id.viewOrder);
        }
    }
}
