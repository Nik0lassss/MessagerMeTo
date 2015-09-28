package com.example.nikolas.messagernik.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;

/**
 * Created by Nikolas on 28.09.2015.
 */
public class ImageSetImageViewAcyncTask extends AsyncTask<String,String,Bitmap>{


    ImageView imageView;
    ProgressBar progressBar;
    public ImageSetImageViewAcyncTask(ImageView imageView,ProgressBar progressBar) {
        this.imageView = imageView;
        this.progressBar=progressBar;
    }


    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap iconBitmap=null;
        try{
            InputStream in =new java.net.URL(url).openStream();
            iconBitmap = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
        return iconBitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
       imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressBar.setProgress(Integer.parseInt(values[0]));
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        super.onCancelled(bitmap);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
