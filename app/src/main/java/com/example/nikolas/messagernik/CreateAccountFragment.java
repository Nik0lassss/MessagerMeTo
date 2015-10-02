package com.example.nikolas.messagernik;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.receiver.Receiver;
import com.example.nikolas.messagernik.verification.Verificator;

import org.json.JSONArray;
import org.json.JSONException;


public class CreateAccountFragment extends Fragment implements ServerApi.onUpdateAccountFragmentListener{

    private ImageView userCoverPhoto;
    private EditText edtxtFirstName, edtxtLastName, edtxtUserLogin, edtxtUserPassword, edtUserConfirmPassword;
    private Button btnRegistration;
    private Verificator verificator;


    public static CreateAccountFragment newInstance() {
        CreateAccountFragment fragment = new CreateAccountFragment();

        return fragment;
    }

    public CreateAccountFragment() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    userCoverPhoto.setImageURI(selectedImage);
                }

                break;
            case 1:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    userCoverPhoto.setImageURI(selectedImage);

                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
        userCoverPhoto = (ImageView) rootView.findViewById(R.id.fragment_create_account_image_view_user_cover);
        edtxtFirstName = (EditText) rootView.findViewById(R.id.fragment_create_account_edt_first_name);
        edtxtLastName = (EditText) rootView.findViewById(R.id.fragment_create_account_edt_last_name);
        edtxtUserLogin = (EditText) rootView.findViewById(R.id.fragment_create_account_edt_login);
        edtxtUserPassword = (EditText) rootView.findViewById(R.id.fragment_create_account_edt_password);
        edtUserConfirmPassword = (EditText) rootView.findViewById(R.id.fragment_create_account_edt_confirm_password);
        btnRegistration = (Button) rootView.findViewById(R.id.fragment_create_account_btn_create_account);
        btnRegistration.setOnClickListener(btnCreateAccountOnClickListener);
        userCoverPhoto.setOnClickListener(imageOnClickListener);
        verificator = new Verificator();
        return rootView;
    }


    View.OnClickListener imageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);
        }
    };
    View.OnClickListener btnCreateAccountOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            verificator.setFirstName(edtxtFirstName.getText().toString());
            verificator.setLastName(edtxtLastName.getText().toString());
            verificator.setLogin(edtxtUserLogin.getText().toString());
            verificator.setPassword(edtxtUserPassword.getText().toString());
            verificator.setConfirmPassword(edtUserConfirmPassword.getText().toString());
            if (verificator.equelsPassword())
                ServerApi.createAccount(verificator.getFirstName(), verificator.getLastName(), verificator.getLogin(), verificator.getPassword());
            // else  Toast.makeText(getContext(),"Password error",Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onGetResponseFromServerCreateAccount(int id) {

    }
}
