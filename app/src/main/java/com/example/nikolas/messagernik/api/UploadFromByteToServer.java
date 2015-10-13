package com.example.nikolas.messagernik.api;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.AndroidMultiPartEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nikolas on 14.10.2015.
 */
public class UploadFromByteToServer extends AsyncTask<Void, Integer, String> {
    private long totalSize = 0;
    private String loginUser;
    private ProgressBar progressBar;
    private Bitmap bitmap;

    public UploadFromByteToServer(Bitmap bitmap, String loginUser, ProgressBar prBar) {
        this.bitmap = bitmap;
        this.progressBar = prBar;
        this.loginUser = loginUser;
    }

    @Override
    protected void onPreExecute() {
        // setting progress bar to zero
        progressBar.setProgress(0);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // Making progress bar visible
        progressBar.setProgress(progress[0]);

    }

    @Override
    protected String doInBackground(Void... params) {
        return uploadFile();
    }

    @SuppressWarnings("deprecation")
    private String uploadFile() {
        String responseString = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL + "/" + loginUser);

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
            byte[] bytes = bao.toByteArray();
            String photoName = "TMP.jpg";
            //File sourceFile = new File(filePath);
            entity.addPart("file", new ByteArrayBody(bytes, photoName));
            // Adding file data to http body
            //entity.addPart("file", new FileBody(sourceFile));
            // Extra parameters if you want to pass to server
//            entity.addPart("website",
//                    new StringBody("www.androidhive.info"));
//            entity.addPart("email", new StringBody("abc@gmail.com"));

            totalSize = entity.getContentLength();
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        return responseString;

    }

    @Override
    protected void onPostExecute(String result) {
        //Log.e(TAG, "Response from server: " + result);

        // showing the server response in an alert dialog


        super.onPostExecute(result);
    }

}

