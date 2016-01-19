package com.example.nikolas.messagernik;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageProgressView;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;
import com.example.nikolas.messagernik.helper.ViewHelper;


public class ProfileFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private TextView textViewFirstName, textViewLastName;
    private static String userTag="user_to_profile_fragment";
    private ImageProgressView imageProgressView;
    private User profileUser;



    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(userTag, user);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profileUser= getArguments().getParcelable(userTag);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewFirstName = (TextView) rootView.findViewById(R.id.fragment_main_page_txt_first_name);
        textViewLastName = (TextView) rootView.findViewById(R.id.fragment_main_page_txt_last_name);
        imageProgressView = (ImageProgressView) rootView.findViewById(R.id.fragment_profile_imageview_with_progressbar_view);
        textViewLastName.setText(profileUser.getFirst_name());
        textViewFirstName.setText(profileUser.getLast_name());
        imageProgressView.setImageCircleUrl(profileUser.getPhotoAvatar());
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ViewHelper.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
//        ViewHelper.getActionBarDrawerToggle().syncState();

        return rootView;
    }









}
