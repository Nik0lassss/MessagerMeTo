package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.FriendsBaseAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.entity.User;

import java.util.ArrayList;


public class FriendFragment extends Fragment implements ServerApi.onUpdateFrinedsList {

    private ListView friendsListView;
    private ArrayList<Friend> friendsList = new ArrayList<>();
    private User me;
    private static String KEY_USER = "KEY_USER";
    private FriendsBaseAdapter friendsBaseAdapter;

    public FriendFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            me = getArguments().getParcelable(KEY_USER);
        }
        friendsBaseAdapter = new FriendsBaseAdapter(getActivity(), friendsList);

    }

    public static Fragment newInstance(User user) {
        FriendFragment fragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        fragment.setArguments(args);
        ServerApi.getFriendsList(fragment, user.getId());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsListView = (ListView) view.findViewById(R.id.fragment_friends_listview);
        friendsListView.setAdapter(friendsBaseAdapter);
        return view;
    }


    @Override
    public void onUpdate(ArrayList<Friend> friendsList) {
        this.friendsList = friendsList;
        friendsBaseAdapter.updateFriendsListArray(friendsList);
        friendsBaseAdapter.notifyDataSetChanged();
    }
}
