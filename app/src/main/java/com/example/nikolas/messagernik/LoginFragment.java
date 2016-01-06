package com.example.nikolas.messagernik;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.api.UploadFileToServer;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.Helper;
import com.example.nikolas.messagernik.interfaces.UpdateLoginFragmentInterface;


public class LoginFragment extends android.support.v4.app.Fragment implements UpdateLoginFragmentInterface.onUpdateLoginFragmentListener{

    private OnFragmentInteractionListener mListener;
    private Uri imageUri;
    private EditText editTextLogin, editTextPassword;
    private Button btnSend, btnCreateAccount, btnTest, btnTestSelectImage;
    private android.widget.ImageView imageBanner;
    private ProgressBar prBar;
    private android.support.v4.app.Fragment fragment;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        //receiver = new Receiver(getActivity(), response, errorListener);
        fragment = this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    imageUri = selectedImage;
                    imageBanner.setImageURI(selectedImage);
                }

                break;
            case 1:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    imageUri = selectedImage;
                    imageBanner.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_layout, container, false);
        editTextLogin = (EditText) rootView.findViewById(R.id.login_layout_edit_text_login_or_email);
        editTextPassword = (EditText) rootView.findViewById(R.id.login_layout_edit_text_password);
        btnSend = (Button) rootView.findViewById(R.id.login_layout_btn_login);
        btnSend.setOnClickListener(btnSendOnClickListenerTest);
        btnCreateAccount = (Button) rootView.findViewById(R.id.login_layout_btn_create_account);
        btnCreateAccount.setOnClickListener(btnCreateAccountListenenr);
        btnTest = (Button) rootView.findViewById(R.id.login_layout_btn_test);
        btnTest.setOnClickListener(btnTestLisntenr);
        btnTestSelectImage = (Button) rootView.findViewById(R.id.login_layout_btn_test_select_image);
        btnTestSelectImage.setOnClickListener(btnTestSelectImageListnener);
        prBar = (ProgressBar) rootView.findViewById(R.id.login_layout_progress_bar);
        imageBanner =(android.widget.ImageView)rootView.findViewById(R.id.login_layout_image_view_banner);
        return rootView;
    }



    View.OnClickListener btnTestLisntenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String path = Helper.getRealPathFromURI(fragment.getActivity(), imageUri);
            new UploadFileToServer(path, editTextLogin.getText().toString(),prBar).execute();
        }
    };
    View.OnClickListener btnTestSelectImageListnener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);
        }
    };

    View.OnClickListener btnSendOnClickListenerTest = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ServerApi.loginUser(fragment, editTextLogin.getText().toString(), editTextPassword.getText().toString());
        }
    };
    View.OnClickListener btnCreateAccountListenenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().beginTransaction().replace(R.id.containerMain, CreateAccount.newInstance()).addToBackStack(this.getClass().getName()).commit();
        }
    };


    public void onResponseGet(User user) {
        if (mListener != null) {

            mListener.onFragmentInteraction(user);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onUpdate(User user) {
        onResponseGet(user);
    }

    @Override
    public void onUpdate(Object object) {
        String className = object.getClass().getName();
//        String response = (String) object;
//        try {
//            ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
//            if (responseObject.getCode() != 0) {
//                Toast.makeText(getActivity(),"Incorrect login or password",Toast.LENGTH_LONG).show();
//            } else {
//                JSONObject jsonObject = (JSONObject) responseObject.getResponseObject();
//                onResponseGet(User.fromJson((JSONObject) jsonObject));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
     if(className.equals(User.class.getName())) {
         onResponseGet((User) object);
         return;
     }
      if(className.equals(User.class.getName()))
      {

      }

    }


    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(User user);

    }

}
