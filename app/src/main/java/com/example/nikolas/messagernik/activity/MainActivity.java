package com.example.nikolas.messagernik.activity;


import android.app.Activity;
import android.os.Bundle;


import com.example.nikolas.messagernik.LoginFragment;
import com.example.nikolas.messagernik.MainPageFragment;
import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.SecretTocken;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.FileHelper;
import com.example.nikolas.messagernik.helper.SharedPreferencesHelper;

public class MainActivity extends Activity implements LoginFragment.OnFragmentInteractionListener, ServerApi.onUpdateListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileHelper.initFileHelper(this);
        SharedPreferencesHelper.initSharedPreferencesHelper(this);
        ServerApi.setUpReciever(getApplicationContext());
        SecretTocken.initialSecretTocken();
        if (null == savedInstanceState) {

        }
        if (SecretTocken.isCorrectToken())
            ServerApi.validateSecretTocken(this, SecretTocken.getSecretTockenString());
        else getFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance()).commit();

    }


    @Override
    public void onFragmentInteraction(User meUser) {
        getFragmentManager().beginTransaction().replace(R.id.containerMain, MainPageFragment.newInstance(meUser)).commit();
    }


    @Override
    public void onUpdate(Object object) {

        if(object.getClass().getName().equals(Boolean.class.getName()))
        {
            boolean isValid=(boolean) object;
           if(isValid)
           {

           }
            else getFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance()).commit();
        }
    }
}
