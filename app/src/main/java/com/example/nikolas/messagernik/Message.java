package com.example.nikolas.messagernik;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.MessageAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Message extends Fragment implements ServerApi.onUpdateListener {

    public static final String ARG_USER_KEY = "message_fragment_arg_user_key";

    private User user;
    private Fragment fragment;
    private MessageAdapter messageAdapter;
    private ListView listView;
    private ArrayList<com.example.nikolas.messagernik.entity.Message> messageArrayList;

    private LoginFragment.OnFragmentInteractionListener mListener;


    public static Message newInstance(User user) {
        Message fragment = new Message();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER_KEY, user);
        fragment.setArguments(args);
        return fragment;
    }

    public static Message newInstance() {
        return new Message();
    }

    ;

    public Message() {
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
        messageAdapter = new MessageAdapter(getActivity(), messageArrayList);
        //ServerApi.getRecieveMessage(fragment, user.getId());
        ServerApi.getConversation(fragment, user.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        listView = (ListView) rootView.findViewById(R.id.fragment_message_listview);
        listView.setAdapter(messageAdapter);
        return rootView;
    }

    @Override
    public void onUpdate(Object object) {
        try {
            messageArrayList = (ArrayList<com.example.nikolas.messagernik.entity.Message>) object;
            messageAdapter.updateMessageArrayList(messageArrayList);
            messageAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String response = (String) object;
//        ResponseList responseObject = null;
//        try {
//            responseObject = ResponseList.fromJson(new JSONObject(response));
//            messageArrayList = com.example.nikolas.messagernik.entity.Message.fromJson((JSONArray) responseObject.getResponseList());
//            messageAdapter.updateMessageArrayList(messageArrayList);
//            messageAdapter.notifyDataSetChanged();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }


}
