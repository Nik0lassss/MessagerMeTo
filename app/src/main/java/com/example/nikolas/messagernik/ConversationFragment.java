package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.ConversationAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.ViewHelper;

import java.util.ArrayList;


public class ConversationFragment extends Fragment implements ServerApi.onUpdateListener {

    public static final String ARG_USER_KEY = "message_fragment_arg_user_key";

    private User user;
    private Fragment fragment;
    private ConversationAdapter conversationAdapter;
    private ListView listView;
    private ArrayList<Message> messageArrayList;
    private LoginFragment.OnFragmentInteractionListener mListener;


    public static ConversationFragment newInstance(User user) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER_KEY, user);
        fragment.setArguments(args);
        return fragment;
    }

    public static ConversationFragment newInstance() {
        return new ConversationFragment();
    }

    ;

    public ConversationFragment() {
        // Required empty public constructor
        fragment = this;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER_KEY);
        }
        messageArrayList = new ArrayList<>();
        conversationAdapter = new ConversationAdapter(getActivity(), messageArrayList);

        //ServerApi.getRecieveMessage(fragment, user.getId());
        ServerApi.getConversation(fragment, user.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        listView = (ListView) rootView.findViewById(R.id.fragment_conversation_listview);
        listView.setAdapter(conversationAdapter);
        listView.setOnItemClickListener(onConversationItemClickListener);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ViewHelper.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ViewHelper.getActionBarDrawerToggle().onDrawerStateChanged(DrawerLayout.STATE_IDLE);
        ViewHelper.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        ViewHelper.getActionBarDrawerToggle().syncState();
        return rootView;
    }

    ListView.OnItemClickListener onConversationItemClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //((Activity) Helper.getContext()).getFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessagesFragment.newInstance(user, messageArrayList.get(position))).addToBackStack("").commit();
            //fragment.getFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessagesFragment.newInstance(user, messageArrayList.get(position))).addToBackStack("").commit();
            fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessageFragment.newInstance(user, messageArrayList.get(position))).addToBackStack("").commit();

        }
    };

    @Override
    public void onUpdate(Object object) {
        try {
            messageArrayList = (ArrayList<Message>) object;
            conversationAdapter.updateMessageArrayList(messageArrayList);
            conversationAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String response = (String) object;
//        ResponseList responseObject = null;
//        try {
//            responseObject = ResponseList.fromJson(new JSONObject(response));
//            messageArrayList = com.example.nikolas.messagernik.entity.Message.fromJson((JSONArray) responseObject.getResponseList());
//            conversationAdapter.updateMessageArrayList(messageArrayList);
//            conversationAdapter.notifyDataSetChanged();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }


}
