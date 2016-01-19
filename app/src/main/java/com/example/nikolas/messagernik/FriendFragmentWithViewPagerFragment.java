package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikolas.messagernik.adapter.FriendFragmentViewPagerAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.entity.User;

import java.util.ArrayList;


public class FriendFragmentWithViewPagerFragment extends Fragment implements ServerApi.onUpdateFrinedsList {

    private ViewPager friendFragmentViewPager;
    private FriendFragmentViewPagerAdapter fragmentStatePagerAdapter;
    private ArrayList<Friend> friendList = new ArrayList<>();
    private static String KEY_USER = "FriendFragmentWithViewPagerFragment_KEY_USER";
    private User me;

    public static FriendFragmentWithViewPagerFragment newInstance(User user) {
        FriendFragmentWithViewPagerFragment fragment = new FriendFragmentWithViewPagerFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public FriendFragmentWithViewPagerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            me = getArguments().getParcelable(KEY_USER);
        }

        ServerApi.getFriendsList(this, me.getId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend_fragment_with_view_pager, container, false);
        friendFragmentViewPager = (ViewPager) view.findViewById(R.id.fragment_friend_fragment_with_viewpager);
        fragmentStatePagerAdapter = new FriendFragmentViewPagerAdapter(getChildFragmentManager(), friendList);

        friendFragmentViewPager.setAdapter(fragmentStatePagerAdapter);
        return view;
    }



    @Override
    public void onUpdate(ArrayList<Friend> frinedsList) {
        this.friendList = frinedsList;
        fragmentStatePagerAdapter.updateData(frinedsList);
        fragmentStatePagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.friends_menu, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_friends_friends_fragment:
                SearchFriendsFragment searchFriendsFragment = SearchFriendsFragment.newInstance();
                getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.additional_content_frame, searchFriendsFragment).addToBackStack(searchFriendsFragment.getClass().getName()).commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
