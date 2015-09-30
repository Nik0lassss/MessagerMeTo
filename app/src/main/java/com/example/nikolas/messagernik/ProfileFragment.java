package com.example.nikolas.messagernik;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikolas.messagernik.adapter.NavigationDrawerBaseAdapter;
import com.example.nikolas.messagernik.api.ImageSetImageViewAcyncTask;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageView;
import com.example.nikolas.messagernik.entity.system.ImageViewWithProgressBarView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnProfileFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private ImageView imageViewProfilePicture;
    private ProgressBar progressBar;
    ImageViewWithProgressBarView imageViewWithProgressBarView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User profileUser;
    private OnProfileFragmentInteractionListener mListener;


    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable("user_to_profile_fragment", user);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profileUser= getArguments().getParcelable("user_to_profile_fragment");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewFirstName = (TextView) rootView.findViewById(R.id.fragment_main_page_txt_first_name);
        textViewLastName = (TextView) rootView.findViewById(R.id.fragment_main_page_txt_last_name);
        imageViewWithProgressBarView = (ImageViewWithProgressBarView) rootView.findViewById(R.id.fragment_profile_imageview_with_progressbar_view);
        textViewLastName.setText(profileUser.getFirst_name());
        textViewFirstName.setText(profileUser.getLast_name());
        imageViewWithProgressBarView.setImageUrl(profileUser.getPhotoAvatar());

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnProfileFragmentInteractionListener) activity;
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
    public interface OnProfileFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
