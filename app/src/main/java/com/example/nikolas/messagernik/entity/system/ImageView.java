package com.example.nikolas.messagernik.entity.system;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.api.ImageViewAsyncTask;
import com.example.nikolas.messagernik.interfaces.OnLoadImageNavigationView;

/**
 * Created by User on 30.09.2015.
 */
public class ImageView extends android.widget.ImageView {
    private Bitmap bm;


    public ImageView(Context context) {
        super(context);
    }


    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setImageUrl(String url, ProgressBar progressBar) {
        new ImageViewAsyncTask(this, progressBar, false).execute(url);
    }

    public void setImageCircleUrl(String url, ProgressBar progressBar) {
        new ImageViewAsyncTask(this, progressBar, true).execute(url);
    }

}
