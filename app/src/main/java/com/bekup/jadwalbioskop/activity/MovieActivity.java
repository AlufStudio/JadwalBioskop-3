package com.bekup.jadwalbioskop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.MovieResponse;
import com.bekup.jadwalbioskop.networks.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private Context mContext = this ;
    private final static String API_KEY = "7e96bc9650c0ba99f9c458a2d9aa11d8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        loadData();
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Load data...");
        progressDialog.show();

        MovieService movieService = MovieService
                .retrofit.create(MovieService.class);
        Call<MovieResponse> call = movieService.getMovie("2", API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressDialog.dismiss();

                MovieResponse jadwalResponse = response.body();

                Log.d("status", jadwalResponse.getStatus());
                Log.d("kota", jadwalResponse.getKota());
                Log.d("date", jadwalResponse.getDate());

                if (jadwalResponse != null) {

                    for (int i = 0; i < jadwalResponse.getData().size();i++) {
                        Log.d("movie", jadwalResponse.getData().get(i).getMovie());

                        for (int j = 0; j < jadwalResponse.getData().get(i).getJadwal().size(); j++) {
                            Log.d("bioskop", jadwalResponse.getData().get(i).getJadwal().get(j).getBioskop());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

}
