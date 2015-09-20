package com.example.nikolas.messagernik;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.receiver.Receiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Receiver receiver;
    private OnFragmentInteractionListener mListener;

    private EditText editTextLogin, editTextPassword;
    private Button btnSend,btnCreateAccount;


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        receiver = new Receiver(getActivity(),response,errorListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_layout,container,false);
        editTextLogin = (EditText)rootView.findViewById(R.id.login_layout_edit_text_login_or_email);
        editTextPassword = (EditText)rootView.findViewById(R.id.login_layout_edit_text_password);
        btnSend = (Button) rootView.findViewById(R.id.login_layout_btn_login);
        btnSend.setOnClickListener(btnSendOnClickListener);
        btnCreateAccount = (Button)rootView.findViewById(R.id.login_layout_btn_create_account);
        btnCreateAccount.setOnClickListener(btnCreateAccountListenenr);

        return rootView;
    }
    Response.Listener response = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                onResponseGet (User.fromJson(new JSONObject(response)));
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

     View.OnClickListener btnSendOnClickListener = new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             HashMap<String ,String > values = new HashMap<String, String>();
             values.put("login",editTextLogin.getText().toString());
             values.put("password",editTextPassword.getText().toString());
         receiver.sendPostRequest(values,Config.LOGIN_URL);
         }
     };

    View.OnClickListener btnCreateAccountListenenr= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().beginTransaction().replace(R.id.containerMain,CreateAccountFragment.newInstance()).addToBackStack(this.getClass().getName()).commit();
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
