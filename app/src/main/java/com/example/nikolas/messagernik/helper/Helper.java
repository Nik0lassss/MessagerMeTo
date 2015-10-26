package com.example.nikolas.messagernik.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.nikolas.messagernik.entity.User;

/**
 * Created by User on 08.10.2015.
 */
public class Helper {
    private static Context context;
    private static User meUser;
    public static String getRealPathFromURI(Context context, Uri contentUri) {
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

    };
    public static void initHelper(Context context)
    {
     Helper.context=context;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Helper.context = context;
    }


    public static User getMeUser() {
        return meUser;
    }

    public static void setMeUser(User meUser) {
        Helper.meUser = meUser;
    }
}
