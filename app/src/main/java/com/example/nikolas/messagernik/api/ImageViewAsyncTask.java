package com.example.nikolas.messagernik.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;


import com.example.nikolas.messagernik.entity.system.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by User on 30.09.2015.
 */
public class ImageViewAsyncTask extends AsyncTask<String,String,Bitmap> {


    ImageView imageView;
    ProgressBar progressBar;

    public ImageViewAsyncTask(ImageView imageView,ProgressBar progressBar) {
        this.imageView = imageView;
        this.progressBar=progressBar;

    }


    @Override
    protected Bitmap doInBackground(String... urls) {
//        String url = urls[0];
//        Bitmap iconBitmap=null;
//        try{
//            InputStream in =new java.net.URL(url).openStream();
//            iconBitmap = BitmapFactory.decodeStream(in);
//        }
//        catch (Exception e)
//        {
//
//            e.printStackTrace();
//        }
        int count;
        Bitmap iconBitmap=null;
        try {
            URL url = new URL(urls[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // this will be useful so that you can show a tipical 0-100% progress
            // bar
            int lenghtOfFile = conection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream
            //OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

            byte data[] = new byte[1024];

            long total = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                baos.write(data,0,count);
            }
            byte[] imageData = baos.toByteArray();
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            iconBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length,options);
            // flushing output
            baos.flush();
            // output.flush();
            baos.close();
            // closing streams
            // output.close();

            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        //return iconBitmap;
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
