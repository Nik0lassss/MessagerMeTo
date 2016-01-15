package com.example.nikolas.messagernik.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nikolas.messagernik.FriendFragment;
import com.example.nikolas.messagernik.helper.Helper;


/**
 * Created by Nikolas on 14.01.2016.
 */
public class FriendFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    public FriendFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return FriendFragment.newInstance(Helper.getMeUser());
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


}
