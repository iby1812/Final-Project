package com.cinema.cintix.fetch_movies_data;

import java.util.List;

public interface OnGetGenresCallback {
    void onSuccess(List<Genre> genres);

    void onError();
}
