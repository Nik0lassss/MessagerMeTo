package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.SearchFriendsBaseAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.Helper;
import com.example.nikolas.messagernik.helper.ViewHelper;

import java.util.ArrayList;


public class SearchFriendsFragment extends Fragment implements ServerApi.onGetAllUsers {
    private SearchFriendsBaseAdapter searchFriendsBaseAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ListView searchFriendListView;

    public static SearchFriendsFragment newInstance() {
        SearchFriendsFragment fragment = new SearchFriendsFragment();

        return fragment;
    }

    public SearchFriendsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        ServerApi.getAllUsers(this, Helper.getMeUser().getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_friends, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Friends");
        ViewHelper.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        ViewHelper.getActionBarDrawerToggle().syncState();
        searchFriendListView = (ListView) view.findViewById(R.id.fragment_search_friends_listview);
        searchFriendsBaseAdapter = new SearchFriendsBaseAdapter(getActivity(), userArrayList);
        searchFriendListView.setAdapter(searchFriendsBaseAdapter);
        return view;
    }


    @Override
    public void onUpdate(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        searchFriendsBaseAdapter.updateSearchFriendList(userArrayList);
        searchFriendsBaseAdapter.notifyDataSetChanged();
    }
}
