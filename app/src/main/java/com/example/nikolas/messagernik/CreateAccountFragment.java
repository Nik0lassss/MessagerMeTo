package com.example.nikolas.messagernik;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.helper.FileHelper;
import com.example.nikolas.messagernik.helper.Helper;
import com.example.nikolas.messagernik.helper.ImageHelper;
import com.example.nikolas.messagernik.verification.Verificator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class CreateAccountFragment extends Fragment implements ServerApi.onUpdateFragmentListener {

    private ImageView userCoverPhoto;
    private EditText edtxtFirstName, edtxtLastName, edtxtUserLogin, edtxtUserPassword, edtUserConfirmPassword;
    private Button btnRegistration;
    private Verificator verificator;
    private Fragment fragment;
    private ProgressBar prBar;
    private byte[] uploadImageByteArray;
    private Uri uploadImageUri;

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
                    uploadImageUri = selectedImage;
                    userCoverPhoto.setImageURI(selectedImage);
                    openCropImage(uploadImageUri);
                }

                break;
            case 1:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    uploadImageUri = selectedImage;
                    userCoverPhoto.setImageURI(selectedImage);
                    openCropImage(uploadImageUri);
                }
                break;
            case ImageHelper.PIC_CROP:
                if (data != null) {
                    // get the returned data
                    Bundle extras = data.getExtras();
                    // get the cropped bitmap
                    Bitmap selectedBitmap = extras.getParcelable("data");
                    uploadImageByteArray = FileHelper.BitmapToByteArray(selectedBitmap);
                    userCoverPhoto.setImageBitmap(selectedBitmap);

                }
                break;
        }
    }

    public void openCropImage(Uri cropImageUri) {
        startActivityForResult(ImageHelper.initCropeImage(cropImageUri), ImageHelper.PIC_CROP);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = this;

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
        prBar = (ProgressBar) rootView.findViewById(R.id.fragment_create_account_progress_bar);
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
            if (verificator.validate())
                ServerApi.createAccount(fragment, verificator.getFirstName(), verificator.getLastName(), verificator.getLogin(), verificator.getPassword());
            else Toast.makeText(fragment.getActivity(), "Validate error", Toast.LENGTH_LONG).show();
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
    public void onUpdateFragment(Object object) {
        ResponseObject responseObject = null;
        try {
            responseObject = ResponseObject.fromJson(new JSONObject((String) object));

            switch (responseObject.getCode()) {
                case 0:
                    Toast.makeText(getActivity(), "Succesful create account", Toast.LENGTH_SHORT).show();
                    if (null != uploadImageUri) {
                        ServerApi.uploadFileToServer(uploadImageByteArray, FileHelper.UriToFile(uploadImageUri).getName().toString(), verificator.getLogin(), prBar);
                    }
                    break;
                case 1:
                    Toast.makeText(getActivity(), "Errore server", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
