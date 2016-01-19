package com.example.nikolas.messagernik.helper;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Nikolas on 07.01.2016.
 */
public class ViewHelper {
    private static Toolbar toolbar;
    private static DrawerLayout drawerLayout;
    private static ActionBarDrawerToggle actionBarDrawerToggle;

    public static void initViewHelperToolbar(Toolbar toolbar)
    {
        ViewHelper.toolbar=toolbar;
    }
    public static void initViewHelperDrawerLayout(DrawerLayout drawerLayout) {ViewHelper.drawerLayout=drawerLayout;}
    public static void initViewHelperActionBarDrawerToggle (ActionBarDrawerToggle actionBarDrawerToggle) {ViewHelper.actionBarDrawerToggle=actionBarDrawerToggle;}
   // public static Toolbar getToolbar() {
   //     return toolbar;
    //}

//    public static DrawerLayout getDrawerLayout() {
//        return drawerLayout;
//    }

//    public static ActionBarDrawerToggle getActionBarDrawerToggle() {
//        return actionBarDrawerToggle;
//    }
}
