package com.example.belajarretrofit.activity.movie_detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.belajarretrofit.MovieAdapter;
import com.example.belajarretrofit.MovieDetailAdapter;
import com.example.belajarretrofit.R;
import com.example.belajarretrofit.model.Movie;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity implements MovieDetailView {

    TextView movieDescription,movieTitle,movieRating,releaseDate;
    ImageView movieImg;
    Bundle bundle;
    String movieId;
    MovieDetailPresenter presenter;
    ProgressBar progressBar;
    RecyclerView movieRecommendations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // pass id_movie kesini dari main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        initialize();
        presenter.getMovieById(movieId);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(){
        bundle = getIntent().getExtras();
        presenter = new MovieDetailPresenter(this,new MovieDetailInteractor());
        movieId = bundle.getString("ID");
        movieDescription = findViewById(R.id.movieDescription);
        movieTitle = findViewById(R.id.movieTitle);
        movieRating = findViewById(R.id.movieRating);
        movieImg = findViewById(R.id.movieImage);
        progressBar = findViewById(R.id.progressBar);
        releaseDate = findViewById(R.id.releaseDate);
        movieRecommendations = findViewById(R.id.movieReccomendations);
    }

    @Override
    public void setDetails(Movie movie) {
        movieDescription.setText(movie.getDescription());
        releaseDate.setText(movie.getRelease_date());
        movieTitle.setText(movie.getTitle());
        movieRating.setText(Float.toString(movie.getVote_average()) + "/10");
        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + movie.getImg_path())
                .into(movieImg);
        progressBar.setProgress((int)movie.getVote_average());
    }

    @Override
    public void setErrorMsg(String errorMsg) {

        movieRecommendations.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(ArrayList<Movie> movieArrayList) {

        MovieDetailAdapter adapter = new MovieDetailAdapter(movieArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        movieRecommendations.setVisibility(View.VISIBLE);
        movieRecommendations.setLayoutManager(layoutManager);
        movieRecommendations.setItemAnimator(new DefaultItemAnimator());
        movieRecommendations.setAdapter(adapter);

    }
}