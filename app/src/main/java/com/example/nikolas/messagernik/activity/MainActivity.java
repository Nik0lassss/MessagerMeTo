package com.example.nikolas.messagernik.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.nikolas.messagernik.LoginFragment;
import com.example.nikolas.messagernik.MainPageFragment;
import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.SecretTocken;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.FileHelper;
import com.example.nikolas.messagernik.helper.Helper;
import com.example.nikolas.messagernik.helper.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, ServerApi.onUpdateListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileHelper.initFileHelper(this);
        SharedPreferencesHelper.initSharedPreferencesHelper(this);
        ServerApi.setUpReciever(getApplicationContext());
        SecretTocken.initialSecretTocken();
        Helper.initHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (null == savedInstanceState) {

        }
        if (SecretTocken.isCorrectToken())
            ServerApi.validateSecretTocken(this, SecretTocken.getSecretTockenString());
        else
            getSupportFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance()).commit();

    }


    @Override
    public void onFragmentInteraction(User meUser) {
        Helper.setMeUser(meUser);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, MainPageFragment.newInstance(meUser)).commit();
    }


    @Override
    public void onUpdate(Object object) {

        if (object.getClass().getName().equals(Boolean.class.getName())) {
            boolean isValid = (boolean) object;
            if (isValid) {
                ServerApi.getUser(this, SecretTocken.getSecretTockenString());
            } else
              getSupportFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance()).commit();
        }
        if (object.getClass().getName().equals(User.class.getName())) {
            Helper.setMeUser((User) object);
            getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, MainPageFragment.newInstance((User) object)).commit();
        }
    }
}
