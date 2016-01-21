package com.example.nikolas.messagernik.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.nikolas.messagernik.ConversationFragment;
import com.example.nikolas.messagernik.FriendFragmentWithViewPagerFragment;
import com.example.nikolas.messagernik.LoginFragment;
import com.example.nikolas.messagernik.MainPageFragment;
import com.example.nikolas.messagernik.ProfileFragment;
import com.example.nikolas.messagernik.R;
import com.example.nikolas.messagernik.api.ServerApi;
import com.example.nikolas.messagernik.entity.SecretTocken;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.system.ImageProgressViewScale;
import com.example.nikolas.messagernik.helper.FileHelper;
import com.example.nikolas.messagernik.helper.Helper;
import com.example.nikolas.messagernik.helper.SharedPreferencesHelper;
import com.example.nikolas.messagernik.helper.ViewHelper;
import com.example.nikolas.messagernik.FriendFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, ServerApi.onUpdateListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private ImageProgressViewScale profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHelper.initFileHelper(this);
        SharedPreferencesHelper.initSharedPreferencesHelper(this);
        ServerApi.setUpReciever(getApplicationContext());
        SecretTocken.initialSecretTocken();
        Helper.initHelper(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
        // ViewHelper.initViewHelperToolbar(toolbar);


        if (null == savedInstanceState) {
            if (SecretTocken.isCorrectToken())
                ServerApi.validateSecretTocken(this, SecretTocken.getSecretTockenString());
            else
                getSupportFragmentManager().beginTransaction().add(R.id.containerMain, LoginFragment.newInstance()).commit();
        } else {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }

    }


//    private void initToolBar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.friends_menu);
//
//    }


//    private void initTabs() {
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.setVisibility(View.GONE);
//    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_bar_open, R.string.navigation_bar_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        View headerLayout = navigationView.inflateHeaderView(R.layout.navigation_header);
        TextView textView = (TextView) headerLayout.findViewById(R.id.nameTextView);
        profileImageView = (ImageProgressViewScale) headerLayout.findViewById(R.id.playerImageView);
        profileImageView.setImageCircleUrl(Helper.getMeUser().getPhotoAvatar());
        textView.setText(Helper.getMeUser().getFirst_name() + " " + Helper.getMeUser().getLast_name());
        navigationView.setNavigationItemSelectedListener(this);
//        Bitmap profileImageBitmap =  profileImageView.getBitmap();
//        Bitmap circleBitmap = ImageHelper.getCircularBitmap(profileImageBitmap);
//        profileImageView.setImageBitmap(circleBitmap);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            imageHeaderView.setImageDrawable(getResources().getDrawable(R.mipmap.androidlollipop_allpapers_very_small,getContext().getTheme()));
//        }
//        else  imageHeaderView.setImageDrawable(getResources().getDrawable(R.mipmap.androidlollipop_allpapers_very_small));
        //ImageView imageView = (ImageView) navigationView.inflateHeaderView(R.layout.navigation_header).findViewById(R.id.playerImageView);
        //((ImageView)imageHeaderView).setImageBitmap(ImageHelper.getCircularBitmap());
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
//            initToolBar();
//            initTabs();
            initNavigationView();
            getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, ProfileFragment.newInstance((User) object)).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.actionMessageItem:
                fragment = ConversationFragment.newInstance();
                break;
            case R.id.actionFriendItem:
                fragment = FriendFragmentWithViewPagerFragment.newInstance(Helper.getMeUser());
                break;
            case R.id.actionProfileItem:
                fragment = ProfileFragment.newInstance(Helper.getMeUser());
                break;
        }

        if (null != fragment && fragment.getClass().equals(ConversationFragment.class)) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ConversationFragment.ARG_USER_KEY, Helper.getMeUser());
            fragment.setArguments(bundle);
        }

        //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).commit();
        return true;
    }

}
