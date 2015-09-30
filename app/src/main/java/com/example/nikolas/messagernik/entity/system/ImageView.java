package com.example.nikolas.messagernik.entity.system;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.api.ImageViewAsyncTask;

/**
 * Created by User on 30.09.2015.
 */
public class ImageView extends android.widget.ImageView{
    private  Bitmap bm;


    public ImageView(Context context) {
        super(context);
    }


    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setImageUrl(String url, ProgressBar progressBar)
    {
    new ImageViewAsyncTask(this,progressBar).execute(url);
    }

}
