package com.adi.jumbledine;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adi on 2017-01-29.
 */
public class YelpFetcher {

    private static final String TAG = "YelpFetcher";

    private static final String CONSUMER_KEY = "RiUmEMcWzUyYcG4v1Ml5vw";
    private static final String CONSUMER_SECRET = "w9Z-5UBIFL5fBZIDGfd1rR_9Fi0";
    private static final String TOKEN = "UrAJk1p4jicWe5jJWbrM-l5gW0du2sXM";
    private static final String TOKEN_SECRET= "gFo-1Y_IPxMmBaTRGgKreEOE7oo";


   /*  The  getUrlBytes(String) method fetches raw data from a URL and
    returns it as an array of bytes.*/
   public byte[] getUrlBytes(String urlSpec) throws IOException {
       URL url = new URL(urlSpec);
       //CREATES CONNECTION POINTED AT URL
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       try {
           ByteArrayOutputStream out = new ByteArrayOutputStream();
           //actual endpoint connection
           InputStream in = connection.getInputStream();
           if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
               throw new IOException(connection.getResponseMessage() +
                       ": with " +
                       urlSpec);
           }
           int bytesRead = 0;
           byte[] buffer = new byte[1024];
           //call  read() repeatedly until your connection runs out of data
           while ((bytesRead = in.read(buffer)) > 0) {
               out.write(buffer, 0, bytesRead);
           }
           out.close();
           return out.toByteArray();
       } finally {
           connection.disconnect();
       }
   }
   /*The  getUrlString(String) method converts the result from
    getUrlBytes(String) to a  String .*/
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public void fetchItems() {
        try {
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
    }


}
