package com.example.nikolas.messagernik;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.ConversationAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Conversation;

import java.util.ArrayList;


public class MessagesFragment extends Fragment implements ServerApi.onUpdateListener {


    private ArrayList<Conversation> messageArrayList;
    private ListView messagesListView;
    private ConversationAdapter conversationAdapter;

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();

        return fragment;
    }

    public MessagesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageArrayList = new ArrayList<Conversation>();
        conversationAdapter = new ConversationAdapter(getActivity(),messageArrayList);
        ServerApi.getConversationMessages(this, 3);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        messagesListView = (ListView) rootView.findViewById(R.id.fragment_messages_list_view);
        messagesListView.setAdapter(conversationAdapter);
        return rootView;
    }


    @Override
    public void onUpdate(Object object) {
        try {
            messageArrayList = (ArrayList<Conversation>) object;
            conversationAdapter.updateMessageArrayList(messageArrayList);
            conversationAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
