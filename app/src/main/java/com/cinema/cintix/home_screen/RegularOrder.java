package com.cinema.cintix.home_screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cinema.cintix.R;
import com.cinema.cintix.adapter.MovieAdapter;
import com.cinema.cintix.fetch_movies_data.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class RegularOrder extends Fragment {

    MovieAdapter adapter;
    ViewPager viewPager;
    Button btn;
    public static int page = 0;
    private List<Movie> moviesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.regular_order, container, false);
        moviesList.clear();
        moviesList.addAll(HomePage.moviesList);
        adapter = new MovieAdapter(moviesList);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(150, 0, 150, 0);
        setText(view, container, 0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if ((position + 1) % 15 == 0) {
                    moviesList.clear();
                    moviesList.addAll(HomePage.moviesList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageSelected(int position) {
                setText(view, container, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn = view.findViewById(R.id.movie_order);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebMovieFragment fragment = new WebMovieFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void setText(View view, ViewGroup container, int position) {
        TextView title, summary;
        title = view.findViewById(R.id.movie_name);
        title.setText(HomePage.moviesList.get(position).getTitle());
        summary = view.findViewById(R.id.movie_summary);
        summary.setText(HomePage.moviesList.get(position).getOverview());
    }
}
