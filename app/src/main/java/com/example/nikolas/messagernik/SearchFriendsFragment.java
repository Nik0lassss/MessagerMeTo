package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class SearchFriendsFragment extends Fragment implements ServerApi.onGetAllUsers,ServerApi.onSubmitAddToFriends {
    private SearchFriendsBaseAdapter searchFriendsBaseAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ListView searchFriendListView;
    private Toolbar toolbar;
    private DrawerLayout profileFragmentDrawerLayout;

    public static SearchFriendsFragment newInstance() {
        SearchFriendsFragment fragment = new SearchFriendsFragment();

        return fragment;
    }

    public SearchFriendsFragment() {

    }

    private void initToolBar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.fragment_search_friends_toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), profileFragmentDrawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        profileFragmentDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setTitle("Search friends");


    }


    private void initTabs(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_search_friends_tabLayout);
        tabLayout.setVisibility(View.GONE);
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
       // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Friends");
//        ViewHelper.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
//        ViewHelper.getActionBarDrawerToggle().syncState();

        profileFragmentDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        searchFriendListView = (ListView) view.findViewById(R.id.fragment_search_friends_listview);
        searchFriendsBaseAdapter = new SearchFriendsBaseAdapter(getActivity(), userArrayList);
        searchFriendListView.setAdapter(searchFriendsBaseAdapter);
        initToolBar(view);
        initTabs(view);
        return view;
    }


    @Override
    public void onUpdate(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        searchFriendsBaseAdapter.updateSearchFriendList(userArrayList);
        searchFriendsBaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateSearchFragmentViewSubmitFriends() {

    }
}
