package com.example.nikolas.messagernik.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;

import java.util.List;

/**
 * Created by Nikolas on 27.09.2015.
 */
public class NavigationDrawerBaseAdapter extends BaseAdapter {
    private Activity activity;
    private List listData;
    public NavigationDrawerBaseAdapter(Activity activity, List listData)  {
        this.activity=activity;
        this.listData=listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=activity.getLayoutInflater().inflate(R.layout.navigation_drawer_layout_item,null);
        TextView textView = (TextView) convertView.findViewById(R.id.navigation_drawer_layout_txt_view);
        textView.setText(listData.get(position).toString());
        return convertView;
    }
}
