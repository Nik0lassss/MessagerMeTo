package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;


import java.util.ArrayList;


public class SearchFriendsBaseAdapter extends BaseAdapter {
    private Button addToFriends, deleteFromFriends;
    private ImageProgressViewScale friendImageView;
    private TextView infoTextView;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ImageView imageViewIsFriends;

    public SearchFriendsBaseAdapter(Context context, ArrayList<User> userArrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userArrayList = userArrayList;
    }

    public void updateSearchFriendList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            view = layoutInflater.inflate(R.layout.search_friend_item, parent, false);
            addToFriends = (Button) view.findViewById(R.id.search_friends_item_add_to_friends);
            deleteFromFriends = (Button) view.findViewById(R.id.search_friends_item_delete_from_friends);
            friendImageView = (ImageProgressViewScale) view.findViewById(R.id.search_friends_item_image_friend);
            infoTextView = (TextView) view.findViewById(R.id.search_friends_item_text_view_info);
            imageViewIsFriends = (ImageView) view.findViewById(R.id.search_friends_item_is_friends);
            view.setTag(R.id.search_friends_item_text_view_info, infoTextView);
            view.setTag(R.id.search_friends_item_add_to_friends, addToFriends);
            view.setTag(R.id.search_friends_item_delete_from_friends, deleteFromFriends);
            view.setTag(R.id.search_friends_item_image_friend, friendImageView);
            view.setTag(R.id.search_friends_item_is_friends, imageViewIsFriends);
        }

        addToFriends = (Button) view.getTag(R.id.search_friends_item_add_to_friends);
        deleteFromFriends = (Button) view.getTag(R.id.search_friends_item_delete_from_friends);
        friendImageView = (ImageProgressViewScale) view.getTag(R.id.search_friends_item_image_friend);
        infoTextView = (TextView) view.getTag(R.id.search_friends_item_text_view_info);
        imageViewIsFriends = (ImageView) view.getTag(R.id.search_friends_item_is_friends);
        infoTextView.setText(userArrayList.get(position).getFirst_name() + " " + userArrayList.get(position).getLast_name());
        friendImageView.setImageUrl(userArrayList.get(position).getPhotoAvatar());
        if (userArrayList.get(position).getIsFriends().equals(1)) {
            addToFriends.setVisibility(View.GONE);
            deleteFromFriends.setVisibility(View.VISIBLE);
            imageViewIsFriends.setVisibility(View.VISIBLE);
        } else if (userArrayList.get(position).getIsFriends().equals(0)) {
            imageViewIsFriends.setVisibility(View.GONE);
            addToFriends.setVisibility(View.VISIBLE);
            deleteFromFriends.setVisibility(View.GONE);
        }
        return view;
    }
}
