package com.example.nikolas.messagernik;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nikolas.messagernik.adapter.NavigationDrawerBaseAdapter;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.helper.FragmentGetter;
import com.example.nikolas.messagernik.helper.ViewHelper;

import java.util.ArrayList;


public class MainPageFragment extends Fragment {

    private static final String ARG_PARAM_USER = "user_to_profile_fragment";

    private User profileUser;
    private ListView navigationDrawerListView;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

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
        navigationDrawerListViewArrayList.add(3, "Friends");
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        ViewHelper.initViewHelperDrawerLayout(drawerLayout);
        NavigationDrawerBaseAdapter navAdapter = new NavigationDrawerBaseAdapter(getActivity(), navigationDrawerListViewArrayList);
        navigationDrawerListView.setAdapter(navAdapter);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.additional_content_frame, ProfileFragment.newInstance(profileUser)).commit();
        navigationDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ((AppCompatActivity) getActivity()).getSupportActionBar().invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((AppCompatActivity) getActivity()).getSupportActionBar().invalidateOptionsMenu();
            }
        };
        ViewHelper.initViewHelperActionBarDrawerToggle(drawerToggle);
        ViewHelper.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!drawerLayout.isDrawerOpen(GravityCompat.START) && drawerToggle.isDrawerIndicatorEnabled()) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else if(drawerLayout.isDrawerOpen(GravityCompat.START) && drawerToggle.isDrawerIndicatorEnabled()) drawerLayout.closeDrawers();
                else getActivity().onBackPressed();
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//                drawerToggle.onDrawerStateChanged(DrawerLayout.STATE_IDLE);
//                drawerToggle.setDrawerIndicatorEnabled(false);
//                drawerToggle.syncState();
//else drawerLayout.closeDrawers();


//                if (drawerLayout.getDrawableState()) {
//                    drawerLayout.closeDrawers();
//                    return;
//                }

                //getActivity().onBackPressed();
                //onBackPressed();
            }
        });
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        return rootView;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
            if (null != fragment && fragment.getClass().equals(ConversationFragment.class)) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ConversationFragment.ARG_USER_KEY, profileUser);
                fragment.setArguments(bundle);
            }
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.additional_content_frame, fragment).addToBackStack(fragment.getClass().getName()).commit();
            navigationDrawerListView.setItemChecked(position, true);
            drawerLayout.closeDrawer(navigationDrawerListView);

        }
    }

}
