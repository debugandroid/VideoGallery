package com.debugandroid.VideoGallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  MediaQuery mediaQuery;
    private  List<VideoItem> videoItemList;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        videoItemList=new ArrayList<VideoItem>();
        mediaQuery=new MediaQuery(this);
        videoItemList=mediaQuery.getAllVideo();
        Log.d("VideoList","Count:"+videoItemList.size());
        videoAdapter=new VideoAdapter(videoItemList,this,"Gallery",true,false);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (videoAdapter.getItemViewType(position)) {
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    default:
                        return 1;
                }
                // return (position == 0||position==1||position==2) ? 2 : 1;
            }
        });
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(videoAdapter);

    }
}
