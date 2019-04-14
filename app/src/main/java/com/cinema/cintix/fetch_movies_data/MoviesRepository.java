package com.cinema.cintix.fetch_movies_data;



import com.cinema.cintix.home_screen.RegularOrder;

import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";
    private static final String ApiKey= "bffac436c406ffac0c7b2bbc005cfc16";
    private static MoviesRepository repository;
    private TMDbApi api;

    private MoviesRepository(TMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }

        return repository;
    }

    public void getMovies(final OnGetMoviesCallback callback) {
       if(RegularOrder.fetchOk) {
           RegularOrder.fetchOk=false;
           RegularOrder.page++;
           if (RegularOrder.page < 10) {

               api.getPopularMovies(ApiKey, LANGUAGE, RegularOrder.page)
                       .enqueue(new Callback<MoviesResponse>() {
                           @Override
                           public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                               if (response.isSuccessful()) {
                                   MoviesResponse moviesResponse = response.body();
                                   if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                       callback.onSuccess(moviesResponse.getMovies());
                                   } else {
                                       callback.onError();
                                   }
                               } else {
                                   callback.onError();
                               }
                           }

                           @Override
                           public void onFailure(Call<MoviesResponse> call, Throwable t) {
                               callback.onError();
                           }
                       });
           }
       }
    }

    /*public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres(ApiKey , LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }*/
}
