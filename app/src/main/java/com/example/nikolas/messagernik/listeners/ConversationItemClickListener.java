package com.example.nikolas.messagernik.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nikolas.messagernik.MessagesFragment;
import com.example.nikolas.messagernik.ProfileFragment;
import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.helper.Helper;

/**
 * Created by Nikolas on 25.10.2015.
 */
public class ConversationItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Activity)Helper.getContext()).getFragmentManager().beginTransaction().replace(R.id.additional_content_frame, MessagesFragment.newInstance()).addToBackStack("").commit();
    }
}
