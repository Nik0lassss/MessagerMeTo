package com.example.nikolas.messagernik;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.NavigationDrawerBaseAdapter;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.FragmentGetter;

import java.util.ArrayList;



public class MainPageFragment extends Fragment {

    private static final String ARG_PARAM_USER = "user_to_profile_fragment";

    private User profileUser;
    private ListView navigationDrawerListView;
    private DrawerLayout drawerLayout;


    public static MainPageFragment newInstance(User user) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public MainPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileUser = new User();
        if (getArguments() != null) {
            profileUser = getArguments().getParcelable(ARG_PARAM_USER);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);
        navigationDrawerListView = (ListView) rootView.findViewById(R.id.left_drawer);
        ArrayList<String> navigationDrawerListViewArrayList = new ArrayList<String>();
        navigationDrawerListViewArrayList.add(0, "Messages");
        navigationDrawerListViewArrayList.add(1, "Profile");
        navigationDrawerListViewArrayList.add(2, "Photo");
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        NavigationDrawerBaseAdapter navAdapter = new NavigationDrawerBaseAdapter(getActivity(), navigationDrawerListViewArrayList);
        navigationDrawerListView.setAdapter(navAdapter);
        getFragmentManager().beginTransaction().add(R.id.additional_content_frame, ProfileFragment.newInstance(profileUser)).commit();
        navigationDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        return rootView;
    }



    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        public DrawerItemClickListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }


        private void selectItem(int position) {
            Fragment fragment = FragmentGetter.getFragment(position);
            if (null != fragment) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ConversationFragment.ARG_USER_KEY, profileUser);
                fragment.setArguments(bundle);
            }
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.additional_content_frame, fragment).addToBackStack("").commit();
            navigationDrawerListView.setItemChecked(position, true);
            drawerLayout.closeDrawer(navigationDrawerListView);
        }
    }

}
