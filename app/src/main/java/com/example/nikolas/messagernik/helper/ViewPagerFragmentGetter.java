package com.example.nikolas.messagernik.helper;

import android.support.v4.app.Fragment;

import com.example.nikolas.messagernik.FollowersFragment;
import com.example.nikolas.messagernik.FriendFragment;
import com.example.nikolas.messagernik.FriendsRequestFragment;
import com.example.nikolas.messagernik.entity.Follower;
import com.example.nikolas.messagernik.entity.Friend;

import java.util.ArrayList;

/**
 * Created by Nikolas on 21.01.2016.
 */
public class ViewPagerFragmentGetter {

    private static int fragmentCount = 2;

    public static Fragment getFragment(int position, ArrayList<Friend> friendArrayList, ArrayList<Follower> followerArrayList) {
        switch (position) {
            case 0:
                return FriendFragment.newInstance(friendArrayList);
            case 1:
                return FollowersFragment.newInstance(followerArrayList);
            case 2:
                return FriendsRequestFragment.newInstance("", "");


            default:
                return FriendFragment.newInstance(friendArrayList);
        }
    }

    public static int getFragmentCount() {
        return fragmentCount;
    }

    public static void setFragmentCount(int fragmentCount) {
        ViewPagerFragmentGetter.fragmentCount = fragmentCount;
    }
}
