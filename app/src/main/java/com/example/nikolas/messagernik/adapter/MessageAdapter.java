package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.entity.Message;

import java.util.ArrayList;

/**
 * Created by User on 12.10.2015.
 */
public class MessageAdapter extends BaseAdapter {
    ArrayList<Message> messageArrayList = new ArrayList<>();
    LayoutInflater lInflater;
    public MessageAdapter() {

    }

    public MessageAdapter(Context context,ArrayList<Message> messageArrayList) {
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageArrayList = messageArrayList;
    }

    public void updateMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList=messageArrayList;
    };

    @Override
    public int getCount() {
        return messageArrayList.size();
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
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.message_item_layout, parent, false);
        }
        ((TextView)view.findViewById(R.id.message_item_text_view)).setText(messageArrayList.get(position).getMessage());
        ((TextView)view.findViewById(R.id.message_item_text_view_from)).setText(messageArrayList.get(position).getFromUser().getFirst_name());
        ((TextView)view.findViewById(R.id.message_item_text_view_to)).setText(messageArrayList.get(position).getToUser().getFirst_name());
        return view;
    }
}
