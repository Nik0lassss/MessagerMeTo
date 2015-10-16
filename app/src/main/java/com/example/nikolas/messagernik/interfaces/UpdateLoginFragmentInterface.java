package com.example.nikolas.messagernik.interfaces;

import com.example.nikolas.messagernik.entity.User;

/**
 * Created by User on 16.10.2015.
 */
public class UpdateLoginFragmentInterface {
    public interface onUpdateLoginFragmentListener {
        public void onUpdate(User user);

        public void onUpdate(Object object);
    }
}
