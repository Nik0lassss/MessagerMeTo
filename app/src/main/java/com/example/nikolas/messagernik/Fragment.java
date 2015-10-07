package com.example.nikolas.messagernik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.api.UploadFileToServer;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.receiver.Receiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment extends android.app.Fragment implements ServerApi.onUpdateFragmentListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Receiver receiver;
    private OnFragmentInteractionListener mListener;
    private Uri imageUri;
    private EditText editTextLogin, editTextPassword;
    private Button btnSend, btnCreateAccount, btnTest, btnTestSelectImage;
    private android.widget.ImageView imageBanner;
    private ProgressBar prBar;
    private android.app.Fragment fragment;

    public static Fragment newInstance(String param1, String param2) {
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        receiver = new Receiver(getActivity(), response, errorListener);
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
                    //userCoverPhoto.setImageURI(selectedImage);
                }

                break;
            case 1:
                if (resultCode == -1) {
                    Uri selectedImage = data.getData();
                    imageUri = selectedImage;
                    imageBanner.setImageURI(selectedImage);
                    //userCoverPhoto.setImageURI(selectedImage);

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

    Response.Listener response = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
                JSONArray jsonArray = (JSONArray) responseObject.getResponseObject();
                onResponseGet(User.fromJson((JSONObject) jsonArray.get(0)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    View.OnClickListener btnTestLisntenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent testIntent = new Intent(fragment.getActivity(), UploadActivity.class);
            String path = getRealPathFromURI(fragment.getActivity(), imageUri);
//            testIntent.putExtra("filePath",path);
//
//            testIntent.putExtra("isImage", true);
//            startActivity(testIntent);

            new UploadFileToServer(path,prBar).execute();
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
    View.OnClickListener btnSendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HashMap<String, String> values = new HashMap<String, String>();
            values.put("login", editTextLogin.getText().toString());
            values.put("password", editTextPassword.getText().toString());
            receiver.sendPostRequest(values, Config.LOGIN_URL);
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
            getFragmentManager().beginTransaction().replace(R.id.containerMain, CreateAccountFragment.newInstance()).addToBackStack(this.getClass().getName()).commit();
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
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
    public void onUpdateFragment(Object object) {
        String response = (String) object;
        try {
            ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
            if (responseObject.getCode() != 0) {
                Toast.makeText(getActivity(),"Incorrect login or password",Toast.LENGTH_LONG).show();
            } else {
                JSONArray jsonArray = (JSONArray) responseObject.getResponseObject();
                onResponseGet(User.fromJson((JSONObject) jsonArray.get(0)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(User user);

    }

}
