package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.entity.Conversation;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;

import java.util.ArrayList;

/**
 * Created by User on 12.10.2015.
 */
public class ConversationAdapter extends BaseAdapter {
    ArrayList<Conversation> conversationArrayList = new ArrayList<>();
    LayoutInflater lInflater;
    public ConversationAdapter() {

    }

    public ConversationAdapter(Context context, ArrayList<Conversation> conversationArrayList) {
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.conversationArrayList = conversationArrayList;
    }

    public void updateMessageArrayList(ArrayList<Conversation> conversationArrayList) {
        this.conversationArrayList = conversationArrayList;
    };

    @Override
    public int getCount() {
        return conversationArrayList.size();
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
            //view = lInflater.inflate(R.layout.message_item_layout, parent, false);
            view = lInflater.inflate(R.layout.message_item, parent, false);
        }
        ((TextView)view.findViewById(R.id.message_last_content)).setText(conversationArrayList.get(position).getMessage());
        ((TextView)view.findViewById(R.id.message_sender_info)).setText(conversationArrayList.get(position).getFromUser().getFirst_name()+" "+ conversationArrayList.get(position).getFromUser().getLast_name());
        ((TextView) view.findViewById(R.id.message_date)).setText(conversationArrayList.get(position).getTime().toString());
        ((ImageProgressViewScale)view.findViewById(R.id.message_image_sender)).setImageUrl(conversationArrayList.get(position).getFromUser().getPhotoAvatar());
        ((ImageProgressViewScale)view.findViewById(R.id.message_image_reciever)).setImageUrl(conversationArrayList.get(position).getToUser().getPhotoAvatar());
//        ((TextView)view.findViewById(R.id.message_item_text_view)).setText(conversationArrayList.get(position).getMessage());
//        ((TextView)view.findViewById(R.id.message_item_text_view_from)).setText(conversationArrayList.get(position).getFromUser().getFirst_name());
//        ((TextView)view.findViewById(R.id.message_item_text_view_to)).setText(conversationArrayList.get(position).getToUser().getFirst_name());
//        ((ImageProgressViewScale)view.findViewById(R.id.message_item_imageview_avatart_from)).setImageUrl(conversationArrayList.get(position).getFromUser().getPhotoAvatar());
//        ((ImageProgressViewScale)view.findViewById(R.id.message_item_imageview_avatart_to)).setImageUrl(conversationArrayList.get(position).getToUser().getPhotoAvatar());
        return view;
    }
}
