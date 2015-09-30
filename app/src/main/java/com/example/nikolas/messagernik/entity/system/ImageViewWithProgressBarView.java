package com.example.nikolas.messagernik.entity.system;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.R;

/**
 * Created by User on 30.09.2015.
 */
public class ImageViewWithProgressBarView extends LinearLayout{
    ImageView imageView;
    ProgressBar progressBar;
    public ImageViewWithProgressBarView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.image_progress_bar_view, this);
    }

    public ImageViewWithProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context,attrs);
    }

    public ImageViewWithProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context,attrs);
    }
    private void Init(Context context,AttributeSet attributeSet)
    {
        LayoutInflater.from(context).inflate(R.layout.image_progress_bar_view, this);
        imageView = (ImageView) this.findViewById(R.id.image_progress_bar_view_image_view_avatar);
        progressBar = (ProgressBar) this.findViewById(R.id.image_progress_bar_view_pr_bar);
    }
    public void setImageUrl(String url)
    {
        imageView.setImageUrl(url,progressBar);
    }

}
