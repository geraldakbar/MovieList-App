package com.example.belajarretrofit.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belajarretrofit.MovieAdapter;
import com.example.belajarretrofit.R;
import com.example.belajarretrofit.activity.movie_detail.MovieDetail;
import com.example.belajarretrofit.api.ApiClient;
import com.example.belajarretrofit.api.ApiEndpoint;
import com.example.belajarretrofit.model.Movie;
import com.example.belajarretrofit.model.MovieResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private ArrayList<Movie> movies;
    private RecyclerView topRatedList,nowPlaying,popularList;
    private TextView errorMessage;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    protected void initialize(){
        topRatedList = findViewById(R.id.topRatedList);
        nowPlaying = findViewById(R.id.nowPlaying);
        popularList = findViewById(R.id.popularList);
        errorMessage = findViewById(R.id.errorMessage);
        presenter = new MainActivityPresenter(this,new MainActivityInteractor());
        movies = new ArrayList<Movie>();

        presenter.updateMovieData("popular");
        presenter.updateMovieData("top_rated");
        presenter.updateMovieData("now_playing");
    }
    @Override
    public void setAdapter(ArrayList<Movie> movieArrayList,String category){
        MovieAdapter adapter = new MovieAdapter(movieArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        if (category.equals("popular")){
            popularList.setLayoutManager(layoutManager);
            popularList.setItemAnimator(new DefaultItemAnimator());
            popularList.setAdapter(adapter);
        }
        else if (category.equals("top_rated")){
            topRatedList.setLayoutManager(layoutManager);
            topRatedList.setItemAnimator(new DefaultItemAnimator());
            topRatedList.setAdapter(adapter);
        }
        else if (category.equals("now_playing")){
            nowPlaying.setLayoutManager(layoutManager);
            nowPlaying.setItemAnimator(new DefaultItemAnimator());
            nowPlaying.setAdapter(adapter);
        }

    }

    @Override
    public void setErrorMsg(String errorMsg) {

        Toast.makeText(getApplicationContext(),errorMsg,Toast.LENGTH_LONG).show();
        errorMessage.setText(errorMsg);
    }

    @Override
    public void navigateToDetailPage(String movieId) {
        Intent intent = new Intent(this, MovieDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID",movieId);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}