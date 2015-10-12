package com.example.nikolas.messagernik.helper;


import android.app.Fragment;

import com.example.nikolas.messagernik.MainPageFragment;
import com.example.nikolas.messagernik.MessageFragment;

/**
 * Created by User on 12.10.2015.
 */
public class FragmentGetter {
    public static Fragment getFragment(int position) {
        switch(position)
        {
            case 0:
                return  MessageFragment.newInstance();
            default:
                return null;
        }
    }
}
