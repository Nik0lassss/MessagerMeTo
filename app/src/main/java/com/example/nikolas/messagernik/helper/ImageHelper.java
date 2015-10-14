package com.example.nikolas.messagernik.helper;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by User on 14.10.2015.
 */
public class ImageHelper {
    public static final int PIC_CROP=2;
    public static Intent initCropeImage(Uri uploadImageUri) {
        //call the standard crop action intent (the user device may not support it)
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        //indicate image type and Uri
        cropIntent.setDataAndType(uploadImageUri, "image/*");
        //set crop properties
        cropIntent.putExtra("crop", "true");
        //indicate aspect of desired crop
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("noFaceDetection", false);
        //indicate output X and Y
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        //retrieve data on return
        cropIntent.putExtra("return-data", true);
        return cropIntent;

    }
}
