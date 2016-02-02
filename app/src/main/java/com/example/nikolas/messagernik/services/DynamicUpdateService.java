package com.example.nikolas.messagernik.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.Cursor;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.NotifyMessage;
import com.example.nikolas.messagernik.entity.SecretTocken;

import java.util.ArrayList;

/**
 * Created by Nikolas on 02.02.2016.
 */
public class DynamicUpdateService extends Service implements ServerApi.onUpdateMessageFragmentMessageList {
    private ArrayList<Message> messageArrayList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //ServerApi.getNotifyNewMessage(this, messageArrayList.get(0).getConversation().getId(), SecretTocken.getSecretTockenString());

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onUpdate(ArrayList<Message> messageList, Cursor cursor) {

    }

    @Override
    public void onUpdate(Message message) {

    }

    @Override
    public void onUpdate(NotifyMessage notifyMessage) {

    }
}
