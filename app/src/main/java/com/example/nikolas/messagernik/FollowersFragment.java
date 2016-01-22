package com.example.nikolas.messagernik;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.FollowersBaseAdapter;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.interfaces.OnUpdateFollowersFragmentViewPagerFragment;

import java.util.ArrayList;


public class FollowersFragment extends Fragment implements OnUpdateFollowersFragmentViewPagerFragment{

    private FollowersBaseAdapter followersBaseAdapter;
    private ArrayList<Friend> friendArrayList = new ArrayList<>();
    private ListView followersListView;
    private static String KEY_FOLLOWERS = "KEY_FOLLOWERS_USER_KEYS";

    public static FollowersFragment newInstance(ArrayList<Friend> followers) {
        FollowersFragment fragment = new FollowersFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_FOLLOWERS, followers);
        fragment.setArguments(args);
        return fragment;
    }

    public FollowersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            friendArrayList = getArguments().getParcelableArrayList(KEY_FOLLOWERS);
        }
        followersBaseAdapter = new FollowersBaseAdapter(getActivity(), friendArrayList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        followersListView = (ListView) view.findViewById(R.id.fragment_followers_listview);
        followersListView.setAdapter(followersBaseAdapter);
        return view;
    }

    @Override
    public void onUpdateFollowers(ArrayList<Friend> friendArrayList) {

    }


//    @Override
//    public void onUpdate(ArrayList<Friend> friendArrayList) {
//        this.friendArrayList = friendArrayList;
//        followersBaseAdapter.updateFollowersListArray(friendArrayList);
//        followersBaseAdapter.notifyDataSetChanged();
//    }
}
