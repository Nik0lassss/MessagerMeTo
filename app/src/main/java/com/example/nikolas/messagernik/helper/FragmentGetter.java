package com.example.nikolas.messagernik.helper;


import android.app.Fragment;

import com.example.nikolas.messagernik.ConversationFragment;

/**
 * Created by User on 12.10.2015.
 */
public class FragmentGetter {
    public static Fragment getFragment(int position) {
        switch(position)
        {
            case 0:
                return  ConversationFragment.newInstance();
            default:
                return null;
        }
    }
}
