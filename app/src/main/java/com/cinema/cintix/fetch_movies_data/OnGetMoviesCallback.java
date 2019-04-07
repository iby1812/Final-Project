package com.cinema.cintix.fetch_movies_data;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movie> movies);

    void onError();
}
