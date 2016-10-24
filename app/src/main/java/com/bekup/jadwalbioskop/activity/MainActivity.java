package com.bekup.jadwalbioskop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.City;
import com.bekup.jadwalbioskop.model.CityResponse;
import com.bekup.jadwalbioskop.networks.JadwalBioskopService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<City> daftarKota = new ArrayList<>() ;
    private Context mContext = this ;
    private final static String API_KEY = "7e96bc9650c0ba99f9c458a2d9aa11d8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Load data...");
        progressDialog.show();

        JadwalBioskopService bioskopService = JadwalBioskopService
                .retrofit.create(JadwalBioskopService.class);
        Call<CityResponse> call = bioskopService.getKota(API_KEY);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                progressDialog.dismiss();

                CityResponse cityResponse = response.body();

                if (cityResponse != null) {
                    Log.d("status", cityResponse.getStatus());
                    daftarKota.addAll(cityResponse.getData());


                    for (int i = 0; i < cityResponse.getData().size(); i++) {
                        Log.d("kota", cityResponse.getData().get(i).getKota());
                    }

                    Intent i = new Intent(mContext, JadwalActivity.class);
                    mContext.startActivity(i);

                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
