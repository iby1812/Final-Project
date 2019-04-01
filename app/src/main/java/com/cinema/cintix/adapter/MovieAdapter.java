package com.cinema.cintix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinema.cintix.R;
import com.cinema.cintix.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MovieAdapter extends PagerAdapter {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<Movie> list;
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.list = movieList;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    public Movie getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.movie_item,container,false);
        ImageView imgmovie;
        TextView title,summary;
        imgmovie=view.findViewById(R.id.movie_image);
        title=view.findViewById(R.id.movie_name);
        summary=view.findViewById(R.id.movie_summary);
        Picasso.get().load(MOVIE_BASE_URL + list.get(position).getPosterPath())
                .into(imgmovie);
        title.setText(list.get(position).getOriginalTitle());
        summary.setText(list.get(position).getOverview());
        imgmovie.setScaleType(ImageView.ScaleType.FIT_XY);
        imgmovie.setAdjustViewBounds(true);
        container.addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}
