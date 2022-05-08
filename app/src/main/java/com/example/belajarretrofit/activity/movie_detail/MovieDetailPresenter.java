package com.example.belajarretrofit.activity.movie_detail;

import android.widget.Toast;

public class MovieDetailPresenter implements MovieDetailInteractor.onMovieClickedListener{

    private MovieDetailView view;
    private MovieDetailInteractor interactor;

    public MovieDetailPresenter(MovieDetailView view, MovieDetailInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getMovieById(String movieId){

        interactor.getMovieById(movieId,this);
        interactor.getMovieReccomendation(movieId,this);
    }


    @Override
    public void onApiSuccess() {
        if (view != null){
            view.setDetails(interactor.getMovie());
            if (interactor.getRecommendations() != null){
                view.setAdapter(interactor.getRecommendations());
            }
            else{
                view.setErrorMsg(interactor.getErrorMsg());
            }

        }
    }

    @Override
    public void onApiFailed() {
        if (view != null){
            view.setErrorMsg(interactor.getErrorMsg());
        }
    }
}
