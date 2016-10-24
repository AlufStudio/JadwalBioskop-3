package com.bekup.jadwalbioskop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.MovieResponse;
import com.bekup.jadwalbioskop.networks.JadwalBioskopService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {

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

        JadwalBioskopService bioskopService = JadwalBioskopService
                .retrofit.create(JadwalBioskopService.class);
        Call<MovieResponse> call = bioskopService.getJadwalByKota("7", API_KEY);

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
