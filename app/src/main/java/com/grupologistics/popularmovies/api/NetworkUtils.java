package com.grupologistics.popularmovies.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grupologistics.popularmovies.entities.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkUtils {

    private MovieResponse movieResponse;

    public interface popularMovies {
        @GET("movie/popular")
        Call<MovieResponse> getPopularMovies(@Query("api_key")String api_key);
    }

    public interface ratedMovies{
        @GET("movie/top_rated")
        Call<MovieResponse> getTopRatedMovies(@Query("api_key")String api_key);
    }

    public MovieResponse getPopularMovies(String api_key){

        try {
            Gson gson = new GsonBuilder()
                    .create();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            popularMovies restClient = retrofit.create(popularMovies.class);

            ArrayList<String> arrayList = new ArrayList<>();

            Call<MovieResponse> call = restClient.getPopularMovies("2efcac45874a700c394b9a10dadc4a8f");

            Response<MovieResponse> response = call.execute();

            if (response.isSuccessful()) {
                movieResponse = response.body();
            }
            else
            {
                String error = "error";
            }
        } catch (Exception ex) {
            // Toast.makeText(context, "error  server not responding " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return movieResponse;
    }




}


