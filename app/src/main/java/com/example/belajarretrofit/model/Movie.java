package com.example.belajarretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("id")
    private String movieId;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("poster_path")
    private String img_path;

    @SerializedName("overview")
    private String description;

    @SerializedName("release_date")
    private String release_date;

    public String getRelease_date() {
        return release_date;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
