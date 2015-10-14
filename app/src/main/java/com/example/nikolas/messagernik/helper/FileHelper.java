package com.example.nikolas.messagernik.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 14.10.2015.
 */
public class FileHelper {
    private static Context context;

    public static void initFileHelper(Context context) {
        FileHelper.context = context;
    }

    public static byte[] fileToByteArray(File file) {
        byte[] fileInBytes = new byte[(int) file.length()];
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(file);

            inStream.read(fileInBytes);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                inStream.close();
                return fileInBytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileInBytes;
    }

    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] bytes = bao.toByteArray();
        return bytes;
    }

    public static File UriToFile(Uri uri) {
        String path = Helper.getRealPathFromURI(FileHelper.context, uri);
        File file = new File(path);
        return file;
    }


}
