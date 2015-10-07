package com.example.nikolas.messagernik.activity;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;


import com.example.nikolas.messagernik.LoginFragment;
import com.example.nikolas.messagernik.MainPageFragment;
import com.example.nikolas.messagernik.ProfileFragment;
import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.User;

public class MainActivity extends Activity implements LoginFragment.OnFragmentInteractionListener, MainPageFragment.OnMainPageFragmentInteractionListener,ProfileFragment.OnProfileFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance("", "")).commit();
        }
        ServerApi.setUpRecuever(getApplicationContext());
    }


    @Override
    public void onFragmentInteraction(User meUser) {
    getFragmentManager().beginTransaction().replace(R.id.containerMain, MainPageFragment.newInstance(meUser)).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
