package com.vmr.popularmovies.api.client;

import android.net.Uri;
import android.os.AsyncTask;

import com.vmr.popularmovies.api.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Victor Ramos on 2/8/17.
 */

public class MovieFetchAsync extends AsyncTask<Void,Void,List<Movie>> {

    public final static String MOST_POPULAR = "popular";
    public final static String TOP_RATED = "top_rated";
    public final static String FAVORITES = "favorites";
    private String mSortBy = MOST_POPULAR;
    private MovieFetchListener mListener;

    private String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private String API_KEY = "7ddc0f922272658862a9feb2065d0d7c";
    private String PARAM_API_KEY = "api_key";


    public interface MovieFetchListener{
        void onMovieFetched(List<Movie> movies);
    }

    public MovieFetchAsync(String sortBy, MovieFetchListener listener){
        mListener = listener;
        mSortBy = sortBy;
    }

    public void sortSearch(String sortBy){
        mSortBy = sortBy;
        execute();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mListener.onMovieFetched(movies);
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {

        List<Movie> movieList = new ArrayList<>();
        try {
            String response = getResponseFromHttpUrl(buildUrl());
            JSONObject jsonObj = new JSONObject(response);
            JSONArray jsonArray = jsonObj.optJSONArray("results");
            if(jsonArray!=null ){
                int size = jsonArray.length();
                for(int i = 0; i<size;i++){
                    movieList.add(new Movie(jsonArray.getJSONObject(i)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return movieList;
    }




    private  URL buildUrl() {
        StringBuilder urlString = new StringBuilder();
        urlString.append(BASE_URL).append(mSortBy);
        Uri builtUri = Uri.parse(urlString.toString()).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    private  String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
