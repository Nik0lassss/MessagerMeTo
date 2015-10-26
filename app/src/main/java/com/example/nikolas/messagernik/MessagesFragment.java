package com.example.nikolas.messagernik;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.ConversationAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Conversation;
import com.example.nikolas.messagernik.entity.User;

import java.util.ArrayList;


public class MessagesFragment extends Fragment implements ServerApi.onUpdateListener {


    private ArrayList<Conversation> messageArrayList;
    private ListView messagesListView;
    private ConversationAdapter conversationAdapter;
    private static String KEY_USER = "KEY_USER";
    private static String KEY_CONVERSATION = "KEY_CONVERSATION";
    private User user;
    private Button btnSendMessage;
    private EditText edtMessage;
    private Conversation conversation;
    private Fragment fragment;

    public static MessagesFragment newInstance(User user, Conversation conversation) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        args.putParcelable(KEY_CONVERSATION, conversation);
        fragment.setArguments(args);
        return fragment;
    }

    public MessagesFragment() {
        fragment = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(KEY_USER);
            conversation = getArguments().getParcelable(KEY_CONVERSATION);
        }
        messageArrayList = new ArrayList<Conversation>();
        conversationAdapter = new ConversationAdapter(getActivity(), messageArrayList);
        ServerApi.getConversationMessages(this, user.getId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        messagesListView = (ListView) rootView.findViewById(R.id.fragment_messages_list_view);
        messagesListView.setAdapter(conversationAdapter);
        btnSendMessage = (Button) rootView.findViewById(R.id.fragment_message_lin_layout_button_send_message);
        edtMessage = (EditText)rootView.findViewById(R.id.fragment_message_lin_layout_edit_text);
        return rootView;
    }

    Button.OnClickListener onClickListenener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer toUserId;
            if(!conversation.getToUser().getId().equals(user.getId())) toUserId=conversation.getToUser().getId();
            else toUserId=user.getId();
            ServerApi.putMessage(fragment, user.getId(),toUserId,conversation.getId(),edtMessage.getText().toString());
        }
    };

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
