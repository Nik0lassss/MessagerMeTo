package com.example.nikolas.messagernik;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.ConversationAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Conversation;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.Helper;

import java.util.ArrayList;


public class ConversationFragment extends Fragment implements ServerApi.onUpdateListener {

    public static final String ARG_USER_KEY = "message_fragment_arg_user_key";

    private User user;
    private Fragment fragment;
    private ConversationAdapter conversationAdapter;
    private ListView listView;
    private ArrayList<Conversation> conversationArrayList;
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
        conversationArrayList = new ArrayList<>();
        conversationAdapter = new ConversationAdapter(getActivity(), conversationArrayList);

        //ServerApi.getRecieveMessage(fragment, user.getId());
        ServerApi.getConversation(fragment, user.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        listView = (ListView) rootView.findViewById(R.id.fragment_message_listview);
        listView.setAdapter(conversationAdapter);
        listView.setOnItemClickListener(onConversationItemClickListener);
        return rootView;
    }

    ListView.OnItemClickListener onConversationItemClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ((Activity) Helper.getContext()).getFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessagesFragment.newInstance(user,conversationArrayList.get(position))).addToBackStack("").commit();
        }
    };

    @Override
    public void onUpdate(Object object) {
        try {
            conversationArrayList = (ArrayList<Conversation>) object;
            conversationAdapter.updateMessageArrayList(conversationArrayList);
            conversationAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String response = (String) object;
//        ResponseList responseObject = null;
//        try {
//            responseObject = ResponseList.fromJson(new JSONObject(response));
//            conversationArrayList = com.example.nikolas.messagernik.entity.Conversation.fromJson((JSONArray) responseObject.getResponseList());
//            conversationAdapter.updateMessageArrayList(conversationArrayList);
//            conversationAdapter.notifyDataSetChanged();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }


}
