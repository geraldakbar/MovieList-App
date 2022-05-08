package com.example.belajarretrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.belajarretrofit.activity.main.MainActivityInteractor;
import com.example.belajarretrofit.activity.main.MainActivityPresenter;
import com.example.belajarretrofit.activity.main.MainActivityView;
import com.example.belajarretrofit.activity.movie_detail.MovieDetail;
import com.example.belajarretrofit.activity.movie_detail.MovieDetailPresenter;
import com.example.belajarretrofit.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private ArrayList<Movie> movieArrayList;
    private Context context;
    private MainActivityPresenter presenter;


    public MovieAdapter(ArrayList<Movie> movieList, Context context){
        this.movieArrayList = movieList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        TextView movieRating;
        TextView movieDesc;
        ImageView moviePoster;
        CardView cardView;
        public MyViewHolder(final View view){
            super(view);
            movieTitle = view.findViewById(R.id.movieTitle);
            movieRating = view.findViewById(R.id.movieRating);
            movieDesc = view.findViewById(R.id.movieDescription);
            moviePoster = view.findViewById(R.id.movieImage);
            cardView = view.findViewById(R.id.cardView);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false);
        context = parent.getContext();
        return new MyViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movie.getImg_path())
                .into(holder.moviePoster);

        String title = movie.getTitle();
        int title_length = title.length();
        String new_title = title.substring(0,Math.min(11,title_length));

        if (title_length > 12){
           holder.movieTitle.setText(new_title.substring(0,9) + "..");
        }
        else{
            holder.movieTitle.setText(new_title);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.onListViewClicked(movie.getMovieId());
                String movieId = movie.getMovieId();
                Intent intent = new Intent(context,MovieDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID",movieId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }






}
