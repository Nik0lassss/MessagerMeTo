package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;
import com.example.nikolas.messagernik.helper.Helper;

import java.util.ArrayList;

/**
 * Created by User on 12.10.2015.
 */
public class MessageAdapter extends BaseAdapter {
    private ArrayList<Message> messageArrayList = new ArrayList<>();
    LayoutInflater lInflater;
    public MessageAdapter() {

    }

    public MessageAdapter(Context context, ArrayList<Message> messageArrayList) {
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageArrayList = messageArrayList;
    }

    public void updateMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    };
    public void addMessageArrayList(Message message) {
        this.messageArrayList.add(0,message);
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
            //view = lInflater.inflate(R.layout.message_item_layout, parent, false);
            view = lInflater.inflate(R.layout.message_item_layout, parent, false);
        }

        ((TextView)view.findViewById(R.id.conversation_last_content)).setText(messageArrayList.get(position).getMessage());
        ((TextView)view.findViewById(R.id.conversation_sender_info)).setText(messageArrayList.get(position).getFromUser().getFirst_name() + " " + messageArrayList.get(position).getFromUser().getLast_name());
        ((TextView) view.findViewById(R.id.conversation_date)).setText(messageArrayList.get(position).getTime().toString());
//        ImageProgressViewScale fromUserImageView= ((ImageProgressViewScale)view.findViewById(R.id.conversation_image_sender));
//        fromUserImageView.setImageUrl(messageArrayList.get(position).getFromUser().getPhotoAvatar());
        RelativeLayout conversation_item_group_message_data_relative_layout= (RelativeLayout) view.findViewById(R.id.conversation_item_group_message_data);
        if(!Helper.getMeUser().getId().equals(messageArrayList.get(position).getFromUser().getId()))
        {
            ImageProgressViewScale fromUserImageView= ((ImageProgressViewScale)view.findViewById(R.id.conversation_image_sender));
            fromUserImageView.setImageUrl(messageArrayList.get(position).getFromUser().getPhotoAvatar());

            conversation_item_group_message_data_relative_layout.setBackgroundResource(R.mipmap.message_layout_background_dialog_system);
        }
        else  conversation_item_group_message_data_relative_layout.setBackgroundResource(R.mipmap.message_layout_background);
            //((ImageProgressViewScale)view.findViewById(R.id.conversation_image_reciever)).setImageUrl(messageArrayList.get(position).getToUser().getPhotoAvatar());
        //RelativeLayout conversation_item_group_message_data_relative_layout= (RelativeLayout) view.findViewById(R.id.conversation_item_group_message_data);
        //conversation_item_group_message_data_relative_layout.setBackgroundResource(R.mipmap.message_layout_background_dialog_system);
//        ((TextView)view.findViewById(R.id.message_item_text_view)).setText(messageArrayList.get(position).getMessage());
//        ((TextView)view.findViewById(R.id.message_item_text_view_from)).setText(messageArrayList.get(position).getFromUser().getFirst_name());
//        ((TextView)view.findViewById(R.id.message_item_text_view_to)).setText(messageArrayList.get(position).getToUser().getFirst_name());
//        ((ImageProgressViewScale)view.findViewById(R.id.message_item_imageview_avatart_from)).setImageUrl(messageArrayList.get(position).getFromUser().getPhotoAvatar());
//        ((ImageProgressViewScale)view.findViewById(R.id.message_item_imageview_avatart_to)).setImageUrl(messageArrayList.get(position).getToUser().getPhotoAvatar());
        return view;
    }
}
