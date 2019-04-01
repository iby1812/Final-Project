package com.cinema.cintix.home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.cintix.R;
import com.cinema.cintix.adapter.MovieAdapter;
import com.cinema.cintix.data.Movie;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class RegularOrder extends Fragment {

    MovieAdapter adapter;
    ViewPager viewPager;
    private ArrayList<Movie> moviesList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.regular_order, container, false);
        moviesList.addAll( HomePage.moviesList);
        adapter=new MovieAdapter(getActivity(),moviesList);
        viewPager=view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setPadding(130,0,130,0);
        return view;
    }
}
