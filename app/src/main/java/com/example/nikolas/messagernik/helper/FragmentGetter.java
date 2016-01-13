package com.example.nikolas.messagernik.helper;




import android.support.v4.app.Fragment;

import com.example.nikolas.messagernik.ConversationFragment;
import com.example.nikolas.messagernik.FriendFragment;
import com.example.nikolas.messagernik.ProfileFragment;

/**
 * Created by User on 12.10.2015.
 */
public class FragmentGetter {
    public static Fragment getFragment(int position) {
        switch(position)
        {
            case 0:
                return  ConversationFragment.newInstance();
            case 1:
                return ProfileFragment.newInstance(Helper.getMeUser());
            case 3:
                return FriendFragment.newInstance(Helper.getMeUser());
            default:
                return null;
        }
    }
}
