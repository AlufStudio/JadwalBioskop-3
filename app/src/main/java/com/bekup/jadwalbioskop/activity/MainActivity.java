package com.bekup.jadwalbioskop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.adapter.CityListAdapter;
import com.bekup.jadwalbioskop.listener.RecyclerViewItemClickListener;
import com.bekup.jadwalbioskop.model.City;
import com.bekup.jadwalbioskop.model.CityResponse;
import com.bekup.jadwalbioskop.networks.MovieService;
import com.bekup.jadwalbioskop.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemClickListener {

    private RecyclerView rvCity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<City> daftarKota = new ArrayList<>() ;
    private Context mContext = this ;
    private final static String API_KEY = "7e96bc9650c0ba99f9c458a2d9aa11d8";

    private CityListAdapter mAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCity = (RecyclerView) findViewById(R.id.rv_city);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        mAdapter = new CityListAdapter(daftarKota);
        mAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvCity.setLayoutManager(mLayoutManager);
        rvCity.setItemAnimator(new DefaultItemAnimator());
        rvCity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvCity.setAdapter(mAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        loadData();

    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Load data...");
        progressDialog.show();

        if (swipeRefreshLayout != null)
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });

        MovieService movieService = MovieService
                .retrofit.create(MovieService.class);
        Call<CityResponse> call = movieService.getCity(API_KEY);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                progressDialog.dismiss();

                CityResponse cityResponse = response.body();

                if (cityResponse != null) {
                    Log.d("status", cityResponse.getStatus());
                    daftarKota.addAll(cityResponse.getData());
                    mAdapter.notifyDataSetChanged();

                    if (swipeRefreshLayout != null)
                        swipeRefreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();

                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void refreshData(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mAdapter.clear();
                loadData();
            }
        });
    }

    @Override
    public void onItemClick(int pos, View view) {
        City city = daftarKota.get(pos);

        Intent i = new Intent(MainActivity.this, MovieActivity.class);
        i.putExtra(MovieActivity.ARG_CITY, city);
        startActivity(i);
    }
}
