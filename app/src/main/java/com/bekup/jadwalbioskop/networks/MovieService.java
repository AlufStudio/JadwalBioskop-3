package com.bekup.jadwalbioskop.networks;

import com.bekup.jadwalbioskop.model.CityResponse;
import com.bekup.jadwalbioskop.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TRIPOD STUDIO on 10/23/2016.
 */

public interface MovieService {

    @GET("jadwal-bioskop")
    Call<CityResponse> getCity(@Query("api_key") String apiKey);

    @GET("jadwal-bioskop/{id}")
    Call<MovieResponse> getMovie(@Query("id") String id, @Query("api_key") String apiKey);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ibacor.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
