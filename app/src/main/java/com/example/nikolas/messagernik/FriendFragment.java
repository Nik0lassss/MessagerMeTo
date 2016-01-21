package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.example.nikolas.messagernik.adapter.FriendsBaseAdapter;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.ViewHelper;
import com.example.nikolas.messagernik.interfaces.OnUpdateFriendsFragment;

import java.util.ArrayList;


public class FriendFragment extends Fragment implements OnUpdateFriendsFragment {

    private ListView friendsListView;
    private ArrayList<Friend> friendsList = new ArrayList<>();
    private User me;
    private static String KEY_FRIENDS = "KEY_FRIENDS_USER_KEYS";
    private FriendsBaseAdapter friendsBaseAdapter;
    private Toolbar toolbar;

    public FriendFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            friendsList = getArguments().getParcelableArrayList(KEY_FRIENDS);
        }
        friendsBaseAdapter = new FriendsBaseAdapter(getActivity(), friendsList);

    }


    public static Fragment newInstance(ArrayList<Friend> friendArrayList) {
        FriendFragment fragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_FRIENDS, friendArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsListView = (ListView) view.findViewById(R.id.fragment_friends_listview);
        friendsListView.setAdapter(friendsBaseAdapter);
        friendsListView.setOnItemClickListener(onFriendsListClickListener);


//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Friends");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ViewHelper.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
//        ViewHelper.getActionBarDrawerToggle().syncState();
        return view;
    }


    ListView.OnItemClickListener onFriendsListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ProfileFragment profileFragment = ProfileFragment.newInstance(friendsList.get(position).getFriend());

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, profileFragment).addToBackStack(profileFragment.getClass().getName()).commit();
        }
    };

    @Override
    public void onUpdate(ArrayList<Friend> friendArrayList) {
        this.friendsList = friendArrayList;
        friendsBaseAdapter.updateFriendsListArray(friendsList);
        friendsBaseAdapter.notifyDataSetChanged();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.friends_menu, menu);
//
//
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_friends_friends_fragment:
                SearchFriendsFragment searchFriendsFragment = SearchFriendsFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, searchFriendsFragment).addToBackStack(searchFriendsFragment.getClass().getName()).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
