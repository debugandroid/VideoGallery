package com.debugandroid.VideoGallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.List;


public class WifiScanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<WifiFragment.device> wifiList;
private Context context;

    public WifiScanAdapter(List<WifiFragment.device> wifiList, Context context) {
        this.wifiList = wifiList;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.network_list, viewGroup, false);

            VideoViewHolder holder = new VideoViewHolder(itemView);
            itemView.setTag(holder);

            return holder;

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


           WifiFragment.device device=wifiList.get(position);
           String name=device.getName().toString();

              ((VideoViewHolder) holder).vName.setText(name);


            ((VideoViewHolder) holder).vImage.setImageResource(R.drawable.ic_network_wifi_black_24dp);
            ((VideoViewHolder) holder).context = context;
            ((VideoViewHolder) holder).position = position;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        int itemCount = wifiList.size();

        return itemCount;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected  Context context;
        protected int position;


        public VideoViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.ssid_name);
            vImage = (ImageView)  v.findViewById(R.id.Wifilogo);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   Intent movieIntent = new Intent();
                  //  movieIntent.setAction(Intent.ACTION_VIEW);
                  //  movieIntent.setDataAndType(Uri.parse(vFilePath), "video/*");
                  //  context.startActivity(movieIntent);
                }
            });
        }
    }

}
