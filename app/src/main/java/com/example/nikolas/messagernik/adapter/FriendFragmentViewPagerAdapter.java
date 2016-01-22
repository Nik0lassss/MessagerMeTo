package com.example.nikolas.messagernik.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.helper.ViewPagerFragmentGetter;
import com.example.nikolas.messagernik.interfaces.OnUpdateFriendsFragmentViewPagerFragment;

import java.util.ArrayList;


/**
 * Created by Nikolas on 14.01.2016.
 */
public class FriendFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Friend> friends = new ArrayList<>();
    private ArrayList<Friend> followers = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public FriendFragmentViewPagerAdapter(FragmentManager fm, ArrayList<Friend> friendArrayList, ArrayList<Friend> followers) {
        super(fm);
        this.friends = friendArrayList;
        this.followers = followers;
        titles.add("Friends");
        titles.add("Followers");
        titles.add("Friends request");

    }

    public void updateDataFriends(ArrayList<Friend> friendArrayList) {
        this.friends = friendArrayList;
    }

    public void updateDataFollowers(ArrayList<Friend> followers) {
        this.followers = followers;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
        }

        return ViewPagerFragmentGetter.getFragment(position, friends, followers);
    }

    @Override
    public int getCount() {
        return ViewPagerFragmentGetter.getFragmentCount();
    }

    @Override
    public int getItemPosition(Object object) {
        //((OnUpdateFriendsFragmentViewPagerFragment) object).onUpdate(friends);
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


}
