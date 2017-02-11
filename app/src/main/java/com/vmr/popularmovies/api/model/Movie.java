package com.vmr.popularmovies.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Victor Ramos on 2/5/17.
 */

public class Movie implements Parcelable {
    String poster_path;
    Boolean adult;
    String overview;
    String release_date;
    ArrayList<Integer> genre_ids;
    Integer id;
    String original_title;
    String original_language;
    String title;
    String backdrop_ath;
    Integer popularity;
    Integer vote_count;
    Boolean video;
    Integer vote_average;

    public Movie (JSONObject jsonObj){
        poster_path = String.format(Locale.getDefault(),"%s%s","http://image.tmdb.org/t/p/w185/",jsonObj.optString("poster_path"));
        adult = jsonObj.optBoolean("adult");
        overview = jsonObj.optString("overview");
        release_date = jsonObj.optString("release_date");
        id = jsonObj.optInt("id");
        original_title = jsonObj.optString("original_title");
        original_language = jsonObj.optString("original_language");
        title = jsonObj.optString("title");
        backdrop_ath = jsonObj.optString("backdrop_ath");
        popularity = jsonObj.optInt("popularity");
        vote_count = jsonObj.optInt("vote_count");
        video = jsonObj.optBoolean("video");
        vote_average = jsonObj.optInt("vote_average");
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_ath() {
        return backdrop_ath;
    }

    public void setBackdrop_ath(String backdrop_ath) {
        this.backdrop_ath = backdrop_ath;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Integer getVote_average() {
        return vote_average;
    }

    public void setVote_average(Integer vote_average) {
        this.vote_average = vote_average;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeValue(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeList(this.genre_ids);
        dest.writeValue(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_ath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.vote_count);
        dest.writeValue(this.video);
        dest.writeValue(this.vote_average);
    }

    protected Movie(Parcel in) {
        this.poster_path = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        this.release_date = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_ath = in.readString();
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.vote_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.vote_average = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
