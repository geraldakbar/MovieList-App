package com.example.belajarretrofit.activity.movie_detail;

import com.example.belajarretrofit.model.Movie;

import java.util.ArrayList;

public interface MovieDetailView {

    // method yang ingin diimplementasikan di activity
    void setDetails(Movie movie);
    void setErrorMsg(String text);
    void setAdapter(ArrayList<Movie> movieArrayList);
}
