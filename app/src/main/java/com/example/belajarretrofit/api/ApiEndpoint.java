package com.example.belajarretrofit.api;

import com.example.belajarretrofit.model.Movie;
import com.example.belajarretrofit.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {

    @GET("3/movie/{category}?api_key=cd2c1b7e2c2d42fd709190ff4b115d68")
    Call<MovieResult> getMovies(@Path(value = "category",encoded = true) String category);

    @GET("3/movie/{id}?api_key=cd2c1b7e2c2d42fd709190ff4b115d68&append_to_response=videos")
    Call<Movie> getMovieById(@Path(value = "id",encoded = true) String movieId);

    @GET("3/movie/{movieid}/recommendations?api_key=cd2c1b7e2c2d42fd709190ff4b115d68&language=en-US&page=1")
    Call<MovieResult> getReccomendations(@Path(value = "movieid",encoded = true) String movieId);


    // image https://image.tmdb.org/t/p/w780/
    //https://image.tmdb.org/t/p/w500/
}
