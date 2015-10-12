package com.example.nikolas.messagernik;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikolas.messagernik.adapter.MessageAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.User;

import java.util.ArrayList;


public class MessageFragment extends Fragment implements ServerApi.onUpdateFragmentListener {

    public static final String ARG_USER_KEY = "message_fragment_arg_user_key";

    private User user;
    private Fragment fragment;
    private MessageAdapter messageAdapter;

    private LoginFragment.OnFragmentInteractionListener mListener;


    public static MessageFragment newInstance(User user) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER_KEY, user);
        fragment.setArguments(args);
        return fragment;
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    ;

    public MessageFragment() {
        // Required empty public constructor
        fragment = this;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER_KEY);
        }
        messageAdapter = new MessageAdapter();
        ServerApi.getRecieveMessage(fragment, user.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onUpdateFragment(Object object) {
        // messageAdapter.updateMessageArrayList((ArrayList<Message>) object);
    }


}
