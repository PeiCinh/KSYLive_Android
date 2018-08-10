package com.ksyun.live.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.ksyun.media.streamer.demo.DemoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

public class StartListView extends AppCompatActivity {

    @BindView(R.id.listview)
    public ListView listView;

    private ListAdapter mlistAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_list_view);
        ButterKnife.bind(this);
        listView.setOnItemClickListener(onItemClickListener);
        //ListView 要顯示的內容
        List<Integer> itemlist = new ArrayList<Integer>();

        itemlist.add(R.drawable.facebook_live);
        itemlist.add(R.drawable.twitch_live);
        itemlist.add(R.drawable.youtube_live);
        itemlist.add(R.drawable.douyu_live);
        itemlist.add(R.drawable.livehouse_live);

        mlistAdaper = new ListAdapter(this,itemlist);

        listView.setAdapter(mlistAdaper);

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("LogDemo",Integer.toString(position));
            Intent intent = new Intent(StartListView.this,DemoActivity.class);
            intent.putExtra("rtmpType",position);
            startActivity(intent);
        }
    };
}
