package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;

import java.util.ArrayList;

/**
 * Created by Nikolas on 12.01.2016.
 */
public class FriendsBaseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ImageProgressViewScale imageView;
    private TextView nameFriendsTextView;
    private ArrayList<Friend> friendArrayList = new ArrayList<>();

    public FriendsBaseAdapter(Context context, ArrayList<Friend> friendArrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.friendArrayList = friendArrayList;
    }

    public void updateFriendsListArray(ArrayList<Friend> friendArrayList) {
        this.friendArrayList = friendArrayList;
    }

    @Override
    public int getCount() {
        return friendArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            view = layoutInflater.inflate(R.layout.friends_item_layout, parent, false);
            imageView = (ImageProgressViewScale) view.findViewById(R.id.fragment_friends_item_friend_image);
            nameFriendsTextView = (TextView) view.findViewById(R.id.fragment_friends_item_friend_name_text_view);
            view.setTag(R.id.fragment_friends_item_friend_image, imageView);
            view.setTag(R.id.fragment_friends_item_friend_name_text_view, nameFriendsTextView);
        }
        imageView = (ImageProgressViewScale) view.getTag(R.id.fragment_friends_item_friend_image);
        nameFriendsTextView = (TextView) view.getTag(R.id.fragment_friends_item_friend_name_text_view);
        imageView.setImageCircleUrl(friendArrayList.get(position).getFriend().getPhotoAvatar());
        nameFriendsTextView.setText(friendArrayList.get(position).getFriend().getFirst_name() + " " + friendArrayList.get(position).getFriend().getLast_name());
        return view;
    }
}
