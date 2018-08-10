package com.ksyun.live.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private LayoutInflater mLayInf;
    List<Integer> mItemList;
    public ListAdapter(Context context, List<Integer> itemList)
    {
        mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemList = itemList;
    }

    @Override
    public int getCount()
    {
        //取得 ListView 列表 Item 的數量
        return mItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        //取得 ListView 列表於 position 位置上的 Item
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        //取得 ListView 列表於 position 位置上的 Item 的 ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
        View v = mLayInf.inflate(R.layout.activity_list_adapter, parent, false);

        ImageView imgView = (ImageView) v.findViewById(R.id.imgview);
//        TextView txtView = (TextView) v.findViewById(R.id.txtView);
//
        imgView.setImageResource(mItemList.get(position));
//        txtView.setText(mItemList.get(position).get("Item title").toString());

        return v;
    }

}
