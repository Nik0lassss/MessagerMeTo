package com.example.nikolas.messagernik;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nikolas.messagernik.adapter.NavigationDrawerBaseAdapter;
import com.example.nikolas.messagernik.entity.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainPageFragment.OnMainPageFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM_USER = "user_to_profile_fragment";

    private User profileUser;
    private OnMainPageFragmentInteractionListener mListener;
    private ListView navigationDrawerListView;

    // TODO: Rename and change types and number of parameters
    public static MainPageFragment newInstance(User user) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public MainPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileUser= new User();
        if (getArguments() != null) {
            profileUser=getArguments().getParcelable(ARG_PARAM_USER);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);
        //navigationDrawerListView = (ListView)rootView.findViewById(R.id.left_drawer);
//        ArrayList<String> navigationDrawerListViewArrayList = new ArrayList<String>();
//        navigationDrawerListViewArrayList.add("Profile");
//        navigationDrawerListViewArrayList.add("Photo");
//        NavigationDrawerBaseAdapter navAdapter = new NavigationDrawerBaseAdapter(getActivity(),navigationDrawerListViewArrayList);
//        navigationDrawerListView.setAdapter(navAdapter);
        //getFragmentManager().beginTransaction().add(R.id.additional_content_frame, ProfileFragment.newInstance(profileUser)).commit();

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
            mListener = (OnMainPageFragmentInteractionListener) activity;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMainPageFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
