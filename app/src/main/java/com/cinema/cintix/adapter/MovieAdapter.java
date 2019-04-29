package com.cinema.cintix.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cinema.cintix.R;
import com.cinema.cintix.fetch_movies_data.Genre;
import com.cinema.cintix.fetch_movies_data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MovieAdapter extends PagerAdapter {

    private static final String TAG = MovieAdapter.class.getSimpleName();


    private List<Movie> movies;

    public static final String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    public MovieAdapter(List<Movie> movieList) {
        this.movies = movieList;

    }
    public  void addMovies(List<Movie> lst){
        movies.clear();
        movies.addAll(lst);
    }
    @Override
    public int getCount() {
        return movies.size();
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.movie_item, container, false);

        Movie movie = movies.get(position);

        ImageView imgmovie = view.findViewById(R.id.movie_image);
        Picasso.get()
                .load(MOVIE_BASE_URL + movie.getPosterPath())
                .into(imgmovie);

        imgmovie.setScaleType(ImageView.ScaleType.FIT_XY);
        imgmovie.setAdjustViewBounds(true);

        container.addView(view, 0);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
