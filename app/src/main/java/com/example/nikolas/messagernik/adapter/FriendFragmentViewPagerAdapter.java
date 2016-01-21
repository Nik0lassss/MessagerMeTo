package com.example.nikolas.messagernik.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nikolas.messagernik.FriendFragment;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.helper.Helper;

import java.util.ArrayList;


/**
 * Created by Nikolas on 14.01.2016.
 */
public class FriendFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Friend> friends = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public FriendFragmentViewPagerAdapter(FragmentManager fm, ArrayList<Friend> friendArrayList) {
        super(fm);
        this.friends = friendArrayList;
        titles.add("Friends");
        titles.add("Friends request");
        titles.add("Followers");
    }

    public void updateData(ArrayList<Friend> friendArrayList) {
        this.friends = friendArrayList;
    }

    @Override
    public Fragment getItem(int position) {

        return FriendFragment.newInstance(friends);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        ((FriendFragment) object).onUpdate(friends);
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


}
