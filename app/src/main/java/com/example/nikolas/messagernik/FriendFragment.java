package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        setHasOptionsMenu(true);
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
        friendsListView.setOnItemClickListener(onFriendsListClickListener);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Friends");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return view;
    }

    ListView.OnItemClickListener onFriendsListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ProfileFragment profileFragment = ProfileFragment.newInstance(friendsList.get(position).getFriend());
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.additional_content_frame, profileFragment).addToBackStack(profileFragment.getClass().getName()).commit();
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.conversation_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(ArrayList<Friend> friendsList) {
        this.friendsList = friendsList;
        friendsBaseAdapter.updateFriendsListArray(friendsList);
        friendsBaseAdapter.notifyDataSetChanged();
    }
}
