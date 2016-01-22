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
public class FollowersBaseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ImageProgressViewScale imageView;
    private TextView nameFriendTextView;
    private ArrayList<Friend> followersArrayList = new ArrayList<>();

    public FollowersBaseAdapter(Context context, ArrayList<Friend> followersArrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.followersArrayList = followersArrayList;
    }

    public void updateFollowersListArray(ArrayList<Friend> friendArrayList) {
        this.followersArrayList = friendArrayList;
    }

    @Override
    public int getCount() {
        return followersArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return followersArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            view = layoutInflater.inflate(R.layout.follower_item_layout, parent, false);
            imageView = (ImageProgressViewScale) view.findViewById(R.id.follower_item_layout_follower_image);
            nameFriendTextView = (TextView) view.findViewById(R.id.follower_item_layout_follower_name_text_view);
            view.setTag(R.id.fragment_friends_item_friend_image, imageView);
            view.setTag(R.id.fragment_friends_item_friend_name_text_view, nameFriendTextView);
        }
        imageView = (ImageProgressViewScale) view.getTag(R.id.follower_item_layout_follower_image);
        nameFriendTextView = (TextView) view.getTag(R.id.follower_item_layout_follower_name_text_view);
        imageView.setImageCircleUrl(followersArrayList.get(position).getFriend().getPhotoAvatar());
        nameFriendTextView.setText(followersArrayList.get(position).getFriend().getFirst_name() + " " + followersArrayList.get(position).getFriend().getLast_name());
        return view;
    }
}
