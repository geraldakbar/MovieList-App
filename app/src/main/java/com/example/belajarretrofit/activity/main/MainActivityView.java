package com.example.belajarretrofit.activity.main;

import com.example.belajarretrofit.model.Movie;

import java.util.ArrayList;

public interface MainActivityView {
    // method yang berhubungan dengan UI
    void setAdapter(ArrayList<Movie> movieArrayList,String category);
    void setErrorMsg(String errorMsg);
    void navigateToDetailPage(String movieId);
}
