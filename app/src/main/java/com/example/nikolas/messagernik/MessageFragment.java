package com.example.nikolas.messagernik;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nikolas.messagernik.activity.MainActivity;
import com.example.nikolas.messagernik.adapter.MessageAdapter;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.NotifyMessage;
import com.example.nikolas.messagernik.entity.SecretTocken;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.helper.Helper;

import java.util.ArrayList;


public class MessageFragment extends Fragment implements ServerApi.onUpdateMessageFragmentMessageList {

    public static final String ARG_USER_KEY = "message_fragment_arg_user_key";

    private User user;
    private Fragment fragment;
    private MessageAdapter messageAdapter;
    private ListView listView;
    private static String KEY_USER = "KEY_USER";
    private static String KEY_CONVERSATION = "KEY_CONVERSATION";
    private ArrayList<Message> messageArrayList;
    private LoginFragment.OnFragmentInteractionListener mListener;
    private Message message;
    private Button btnSendMessage;
    private EditText edtTextMessage;



    public static MessageFragment newInstance(User user, Message message) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        args.putParcelable(KEY_CONVERSATION, message);
        fragment.setArguments(args);
        ServerApi.getNotifyNewMessage(fragment, message.getConversation().getId(), SecretTocken.getSecretTockenString());

        return fragment;
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    public int createInfoNotification(String message){
        Intent notificationIntent = new Intent(Helper.getContext(), MainActivity.class); // по клику на уведомлении откроется HomeActivity
        NotificationCompat.Builder nb = new NotificationCompat.Builder(Helper.getContext())
//NotificationCompat.Builder nb = new NotificationBuilder(context) //для версии Android > 3.0
                .setSmallIcon(R.drawable.avatar_img) //иконка уведомления
                .setAutoCancel(true) //уведомление закроется по клику на него
                .setTicker(message) //текст, который отобразится вверху статус-бара при создании уведомления
                .setContentText(message) // Основной текст уведомления
                .setContentIntent(PendingIntent.getActivity(Helper.getContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT))
                .setWhen(System.currentTimeMillis()) //отображаемое время уведомления
                .setContentTitle("AppName") //заголовок уведомления
                .setDefaults(Notification.DEFAULT_ALL); // звук, вибро и диодный индикатор выставляются по умолчанию
NotificationManager manager= manager = (NotificationManager) fragment.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = nb.getNotification(); //генерируем уведомление
        manager.notify(1, notification); // отображаем его пользователю.
        //notifications.put(1, notification); //теперь мы можем обращаться к нему по id
        return 2;
    }

    public MessageFragment() {
        // Required empty public constructor
        fragment = this;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(KEY_USER);
            message = getArguments().getParcelable(KEY_CONVERSATION);
        }
        messageArrayList = new ArrayList<>();
        messageAdapter = new MessageAdapter(getActivity(), messageArrayList);
        ServerApi.getConversationMessages(this, user.getId(), message.getConversation().getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        listView = (ListView) rootView.findViewById(R.id.fragment_message_listview);
        btnSendMessage = (Button) rootView.findViewById(R.id.fragment_message_lin_layout_button_send_message);
        btnSendMessage.setOnClickListener(btnOnClickListenerSendMessage);
        edtTextMessage = (EditText) rootView.findViewById(R.id.fragment_message_lin_layout_edit_text);
        listView.setAdapter(messageAdapter);
        listView.setOnItemClickListener(onConversationItemClickListener);
        return rootView;
    }

    ListView.OnItemClickListener onConversationItemClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            fragment.getFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessageFragment.newInstance(user, messageArrayList.get(position))).addToBackStack("").commit();

        }
    };

    Button.OnClickListener btnOnClickListenerSendMessage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer toUserId;
            if (message.getConversation().getUser_first().getId().equals(user.getId()))
                toUserId = message.getConversation().getUser_second().getId();
            else toUserId = message.getConversation().getUser_first().getId();
            ServerApi.putMessage(fragment, user.getId(), toUserId, message.getConversation().getId(), edtTextMessage.getText().toString(), SecretTocken.getSecretTockenString());
            //ServerApi.getConversationMessages(fragment, user.getId(), message.getConversation().getId());
            // ServerApi.getNotifyNewMessage(fragment,message.getConversation().getId(), SecretTocken.getSecretTockenString());
        }
    };


//    @Override
//    public void onUpdate(Object object) {
//        try {
//            if (object.getClass().getName().equals(NotifyMessage.class.getName())) {
////                NotifyMessage notifyMessage = (NotifyMessage)object;
////                Toast.makeText(fragment.getActivity(),"Code: "+notifyMessage.getStatus() +"NotifyMessage:"+notifyMessage.getNotifyMessageString(),Toast.LENGTH_LONG).show();
////                if(0==notifyMessage.getStatus())  ServerApi.getConversationMessages(this, user.getId(), message.getConversation().getId());
////                ServerApi.getNotifyNewMessage(fragment,message.getConversation().getId(), SecretTocken.getSecretTockenString());
//            }
//            if (object.getClass().getName().equals(Message.class.getName()))
//            {
//                messageArrayList.add((Message)object);
//                messageAdapter.updateMessageArrayList(messageArrayList);
//                messageAdapter.notifyDataSetChanged();
//            }
//            else {
//
//                messageArrayList = (ArrayList<Message>) object;
//                messageAdapter.updateMessageArrayList(messageArrayList);
//                messageAdapter.notifyDataSetChanged();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        String response = (String) object;
////        ResponseList responseObject = null;
////        try {
////            responseObject = ResponseList.fromJson(new JSONObject(response));
////            messageArrayList = com.example.nikolas.messagernik.entity.Message.fromJson((JSONArray) responseObject.getResponseList());
////            conversationAdapter.updateMessageArrayList(messageArrayList);
////            conversationAdapter.notifyDataSetChanged();
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//    }


    @Override
    public void onUpdate(ArrayList<Message> messageList) {
        messageAdapter.updateMessageArrayList(messageList);
        messageAdapter.notifyDataSetChanged();
        createInfoNotification("ok");
    }

    @Override
    public void onUpdate(Message message) {
        messageAdapter.addMessageArrayList(message);
        messageAdapter.notifyDataSetChanged();
        edtTextMessage.setText("");

    }

    @Override
    public void onUpdate(NotifyMessage notifyMessage) {
        Toast.makeText(fragment.getActivity(), "Code: " + notifyMessage.getStatus() + "NotifyMessage:" + notifyMessage.getNotifyMessageString(), Toast.LENGTH_LONG).show();
        if (0 == notifyMessage.getStatus())
            ServerApi.getConversationMessages(this, user.getId(), message.getConversation().getId());
        ServerApi.getNotifyNewMessage(fragment, message.getConversation().getId(), SecretTocken.getSecretTockenString());
    }
}
