package com.debugandroid.VideoGallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Pawan on 2/20/2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List<VideoItem> videoList;
    Context context;
    private final static int FADE_DURATION = 1000;
    public static Glide glid;
    private  String album_name;
    String name;
    int days;
    Bundle bundle=new Bundle();
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private boolean mWithHeader;
    private boolean mWithFooter;


    public VideoAdapter(List<VideoItem> videoList, Context context, String album, boolean withHeader, boolean withFooter) {
        this.videoList = videoList;
        this.context=context;
        this.album_name=album;
        this.mWithHeader=withHeader;
        this.mWithFooter=withFooter;
    }
    @Override
    public int getItemViewType(int position) {

        if (mWithHeader && isPositionHeader(position))
            return TYPE_HEADER;
        if (mWithFooter && isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType==TYPE_HEADER) {

            return new headView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false));
        }
        else if(viewType==TYPE_FOOTER){
            return new footer(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer, viewGroup, false));
        }
        else {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.video_item, viewGroup, false);

            VideoViewHolder holder = new VideoViewHolder(itemView);
            itemView.setTag(holder);

            return holder;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  headView){
            ((headView) holder).vName.setText(album_name);
        }
        else if(holder instanceof  footer){
            ((footer) holder).context = context;
        }
        else {
            VideoItem mediaObject=getItem(position);
            name=mediaObject.getDISPLAY_NAME();
            if (name.length() > 25) {
             ((VideoViewHolder) holder).vName.setText(name.substring(0, 25) + "..");
             } else {
              ((VideoViewHolder) holder).vName.setText(name);
             }

            ((VideoViewHolder) holder).vImage.setImageResource(R.color.cardview_dark_background);
            ((VideoViewHolder) holder).vFilePath = mediaObject.getDATA();
            ((VideoViewHolder) holder).context = context;
            ((VideoViewHolder) holder).b = bundle;
            ((VideoViewHolder) holder).position = position;

            glid.with(context)
                    .load(mediaObject.getDATA())
                    .centerCrop()
                    .placeholder(R.color.cardview_dark_background)
                    .crossFade()
                    .into(((VideoViewHolder) holder).vImage);

            setScaleAnimation(((VideoViewHolder) holder).vImage);
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        int itemCount = videoList.size();
        if (mWithHeader)
            itemCount=itemCount+1;
        if (mWithFooter)
            itemCount=itemCount+1;
        return itemCount;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    protected VideoItem getItem(int position) {
        return mWithHeader ? videoList.get(position - 1) : videoList.get(position);
    }

    private int getItemPosition(int position){
        return mWithHeader ? position - 1 : position;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected TextView vDetails,vNew;
        protected String vFilePath;
        protected  Context context;
        protected   Bundle b;
        protected int position;


        public void clearAnimation()
        {
            this.clearAnimation();
        }
        public VideoViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.media_name);
            vImage = (ImageView)  v.findViewById(R.id.media_img_bck);
            vDetails = (TextView) v.findViewById(R.id.media_details);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent movieIntent = new Intent();
                    movieIntent.setAction(android.content.Intent.ACTION_VIEW);
                    movieIntent.setDataAndType(Uri.parse(vFilePath), "video/*");
                    context.startActivity(movieIntent);
                }
            });
        }


    }

    public class headView extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected  Context context;
        protected   Bundle b;
        protected int position;

        public headView(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.gallery_title);
        }
    }

    public class footer extends RecyclerView.ViewHolder {


        protected  Context context;
        protected int position;

        public footer(View v) {
            super(v);


        }


    }

}
