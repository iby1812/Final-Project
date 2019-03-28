package com.cinema.cintix.home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.cintix.R;
import com.cinema.cintix.adapters.MovieAdapter;
import com.cinema.cintix.data.Movie;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class RegularOrder extends Fragment {

    MovieAdapter adapter;
    ViewPager viewPager;
    private ArrayList<Movie> moviesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.regular_order, container, false);
        moviesList=HomePage.moviesList;
        adapter=new MovieAdapter(getContext(),moviesList);
        adapter.notifyDataSetChanged();
        viewPager=view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        return view;
    }
}
