package com.example.nikolas.messagernik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;
import com.example.nikolas.messagernik.helper.Helper;


import java.util.ArrayList;


public class SearchFriendsBaseAdapter extends BaseAdapter implements ServerApi.onSendRequestToFriend, ServerApi.onSubmitAddToFriends, ServerApi.onDeleteFriend, ServerApi.onCancelFriendRequest {
    private Button addToFriends, deleteFromFriends, cancelFriendRequest, submitFriendReqeust;
    private ImageProgressViewScale friendImageView;
    private TextView infoTextView;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ImageView imageViewStatus;
    private SearchFriendsBaseAdapter searchFriendsBaseAdapter;
    private Animation imageViewAnimation;


    public SearchFriendsBaseAdapter(Context context, ArrayList<User> userArrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userArrayList = userArrayList;
        this.searchFriendsBaseAdapter = this;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            view = layoutInflater.inflate(R.layout.search_friend_item, parent, false);
            addToFriends = (Button) view.findViewById(R.id.search_friends_item_add_to_friends);
            deleteFromFriends = (Button) view.findViewById(R.id.search_friends_item_delete_from_friends);
            friendImageView = (ImageProgressViewScale) view.findViewById(R.id.search_friends_item_image_friend);
            infoTextView = (TextView) view.findViewById(R.id.search_friends_item_text_view_info);
            imageViewStatus = (ImageView) view.findViewById(R.id.search_friends_item_is_friends);
            cancelFriendRequest = (Button) view.findViewById(R.id.search_friends_item_cancel_friend_request);
            submitFriendReqeust = (Button) view.findViewById(R.id.search_friends_item_submit_friends_request);
            view.setTag(R.id.search_friends_item_text_view_info, infoTextView);
            view.setTag(R.id.search_friends_item_add_to_friends, addToFriends);
            view.setTag(R.id.search_friends_item_delete_from_friends, deleteFromFriends);
            view.setTag(R.id.search_friends_item_image_friend, friendImageView);
            view.setTag(R.id.search_friends_item_is_friends, imageViewStatus);
            view.setTag(R.id.search_friends_item_cancel_friend_request, cancelFriendRequest);
            view.setTag(R.id.search_friends_item_submit_friends_request, submitFriendReqeust);
        }
        imageViewAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.search_friend_image_andimation);
        addToFriends = (Button) view.getTag(R.id.search_friends_item_add_to_friends);
        deleteFromFriends = (Button) view.getTag(R.id.search_friends_item_delete_from_friends);
        deleteFromFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        friendImageView = (ImageProgressViewScale) view.getTag(R.id.search_friends_item_image_friend);
        infoTextView = (TextView) view.getTag(R.id.search_friends_item_text_view_info);
        imageViewStatus = (ImageView) view.getTag(R.id.search_friends_item_is_friends);
        cancelFriendRequest = (Button) view.getTag(R.id.search_friends_item_cancel_friend_request);
        submitFriendReqeust = (Button) view.getTag(R.id.search_friends_item_submit_friends_request);
        infoTextView.setText(userArrayList.get(position).getFirst_name() + " " + userArrayList.get(position).getLast_name());
        friendImageView.setImageUrl(userArrayList.get(position).getPhotoAvatar());

        cancelFriendRequest.setVisibility(View.GONE);
        submitFriendReqeust.setVisibility(View.GONE);
        addToFriends.setVisibility(View.GONE);
        deleteFromFriends.setVisibility(View.GONE);

        if (userArrayList.get(position).getIsFriends().equals(1)) {

            deleteFromFriends.setVisibility(View.VISIBLE);
            deleteFromFriends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServerApi.deleteFriend(searchFriendsBaseAdapter, Helper.getMeUser().getId(), userArrayList.get(position).getId());
                }
            });
            imageViewStatus.setVisibility(View.VISIBLE);
            imageViewStatus.setImageDrawable(view.getResources().getDrawable(R.mipmap.ic_done_black_48dp));

        } else if (userArrayList.get(position).getIsFriends().equals(0)) {
            imageViewStatus.setVisibility(View.GONE);
            addToFriends.setVisibility(View.VISIBLE);
            addToFriends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ServerApi.sendRequestToFriend(searchFriendsBaseAdapter, Helper.getMeUser().getId(), userArrayList.get(position).getId());
                }
            });

        }
        if (userArrayList.get(position).getiFollow().equals(1)) {
            imageViewStatus.setImageDrawable(view.getResources().getDrawable(R.mipmap.friend_requsest));
            imageViewStatus.setVisibility(View.VISIBLE);
            cancelFriendRequest.setVisibility(View.VISIBLE);
            cancelFriendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServerApi.cancelFriendRequest(searchFriendsBaseAdapter, userArrayList.get(position).getFriendRequestId());
                }
            });
            addToFriends.setVisibility(View.GONE);
            deleteFromFriends.setVisibility(View.GONE);
            //submitFriendReqeust.setVisibility(View.GONE);


        }

        if (userArrayList.get(position).getIsFollower().equals(1)) {
            imageViewStatus.setImageDrawable(view.getResources().getDrawable(R.mipmap.follow));
            imageViewStatus.setVisibility(View.VISIBLE);
            submitFriendReqeust.setVisibility(View.VISIBLE);
            submitFriendReqeust.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    v.setVisibility(View.VISIBLE);
//                    v.startAnimation(imageViewAnimation);
                    ServerApi.submitAddToFriends(searchFriendsBaseAdapter, userArrayList.get(position).getFriendRequestId());
                }
            });
            addToFriends.setVisibility(View.GONE);
            //cancelFriendRequest.setVisibility(View.GONE);
            deleteFromFriends.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onSendRequestToFriend(User user) {
        //imageViewStatus.setImageDrawable(view.getResources().getDrawable(R.mipmap.friend_requsest));
//        imageViewStatus.setVisibility(View.VISIBLE);
//        imageViewStatus.startAnimation(imageViewAnimation);
//
        Integer position = userArrayList.indexOf(user);
        userArrayList.set(position, user);
        this.notifyDataSetChanged();
    }

    @Override
    public void onUpdateSearchFragmentViewSubmitFriends(User user) {
        // imageViewStatus.setImageDrawable(view.getResources().getDrawable(R.mipmap.friend_requsest));
//        imageViewStatus.setVisibility(View.VISIBLE);
//        imageViewStatus.startAnimation(imageViewAnimation);

        Integer position = userArrayList.indexOf(user);
        userArrayList.set(position, user);
        this.notifyDataSetChanged();
    }

    @Override
    public void onDeleteFriend(User user) {
        Integer position = userArrayList.indexOf(user);
        userArrayList.set(position, user);
        this.notifyDataSetChanged();
    }

    @Override
    public void onCancelFriend(User user) {
        Integer position = userArrayList.indexOf(user);
        userArrayList.set(position, user);
        this.notifyDataSetChanged();
    }
}
