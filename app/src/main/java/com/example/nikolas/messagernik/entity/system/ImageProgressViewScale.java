package com.example.nikolas.messagernik.entity.system;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.R;

/**
 * Created by User on 13.10.2015.
 */
public class ImageProgressViewScale extends LinearLayout {
    ImageView imageView;
    ProgressBar progressBar;
    public ImageProgressViewScale(Context context) {
        super(context);
    }

    public ImageProgressViewScale(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, attrs);
    }

    public ImageProgressViewScale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context,attrs);
    }
    private void Init(Context context,AttributeSet attributeSet)
    {
        LayoutInflater.from(context).inflate(R.layout.image_progress_bar_view_scale_center_crop, this);
        imageView = (ImageView) this.findViewById(R.id.image_progress_bar_view_scale_image_view_avatar);
        progressBar = (ProgressBar) this.findViewById(R.id.image_progress_bar_view_scale_pr_bar);
    }
    public void setImageUrl(String url)
    {
        imageView.setImageUrl(url,progressBar);
    }
}
