package com.adi.jumbledine;

import android.net.Uri;
import android.util.Log;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Adi on 2017-01-29.
 */
public class YelpFetcher {

    private static final String TAG = "YelpFetcher";

    private static final String CONSUMER_KEY = "RiUmEMcWzUyYcG4v1Ml5vw";
    private static final String CONSUMER_SECRET = "w9Z-5UBIFL5fBZIDGfd1rR_9Fi0";
    private static final String TOKEN = "UrAJk1p4jicWe5jJWbrM-l5gW0du2sXM";
    private static final String TOKEN_SECRET= "gFo-1Y_IPxMmBaTRGgKreEOE7oo";

    YelpAPIFactory apiFactory = new YelpAPIFactory(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
    YelpAPI yelpAPI = apiFactory.createAPI();


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

        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "food");
        params.put("limit", "3");

        // locale params
        params.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
        try{
            Response<SearchResponse> response = call.execute();
            JSONObject jsonBody = new JSONObject(response.toString());
        }catch(Exception e){
            Log.e(TAG, "Failed to fetch items", e);
        }
    }

   /* private void parseItems(List<Choice> items, JSONObject jsonBody)
            throws IOException, JSONException {
        JSONObject choicesJsonObject = jsonBody.getJSONObject("photos");
        JSONArray choiceJsonArray = choicesJsonObject.getJSONArray("photo");
        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject choiceJsonObject = choiceJsonArray.getJSONObject(i);
            Choice choice = new Choice();
            choice.setTitle(photoJsonObject.getString("id"));
            choice.setAddress(photoJsonObject.getString("title"));
            if (!photoJsonObject.has("url_s")) {
                continue;
            }
            item.setUrl(photoJsonObject.getString("url_s"));
            items.add(item);
        }
    }*/


}
