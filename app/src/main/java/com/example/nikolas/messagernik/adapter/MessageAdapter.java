package com.example.nikolas.messagernik.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nikolas.messagernik.entity.Message;

import java.util.ArrayList;

/**
 * Created by User on 12.10.2015.
 */
public class MessageAdapter extends BaseAdapter {
    ArrayList<Message> messageArrayList = new ArrayList<>();

    public MessageAdapter() {
    }

    public MessageAdapter(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }

    public void updateMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList=messageArrayList;
    };

    @Override
    public int getCount() {
        return 0;
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
        return null;
    }
}
