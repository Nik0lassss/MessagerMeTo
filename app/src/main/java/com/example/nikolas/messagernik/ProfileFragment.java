package com.example.nikolas.messagernik;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageProgressView;


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
        imageProgressView.setImageUrl(profileUser.getPhotoAvatar());

        return rootView;
    }









}
