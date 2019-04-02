package com.cinema.cintix.home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.regular_order, container, false);
        moviesList.addAll( HomePage.moviesList);
        adapter=new MovieAdapter(moviesList);
        viewPager=view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setPadding(150,0,150,0);
        setText(view,container,0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setText(view,container,position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }
    public void setText(View view,ViewGroup container,int position){
        TextView title,summary;
        title=view.findViewById(R.id.movie_name);
        title.setText(moviesList.get(position).getOriginalTitle());
        summary=view.findViewById(R.id.movie_summary);
        summary.setText(moviesList.get(position).getOverview());
    }
}
