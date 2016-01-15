package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikolas.messagernik.adapter.FriendFragmentViewPagerAdapter;


public class FriendFragmentWithViewPagerFragment extends Fragment {

    protected ViewPager friendFragmentViewPager;
    protected FriendFragmentViewPagerAdapter fragmentStatePagerAdapter;

    public static FriendFragmentWithViewPagerFragment newInstance() {
        FriendFragmentWithViewPagerFragment fragment = new FriendFragmentWithViewPagerFragment();

        return fragment;
    }

    public FriendFragmentWithViewPagerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        fragmentStatePagerAdapter = new FriendFragmentViewPagerAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_fragment_with_view_pager, container, false);
        friendFragmentViewPager = (ViewPager) view.findViewById(R.id.fragment_friend_fragment_with_viewpager);
        friendFragmentViewPager.setAdapter(fragmentStatePagerAdapter);
        friendFragmentViewPager.setCurrentItem(1);


        return view;
    }


}
