package com.bekup.jadwalbioskop.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.adapter.MovieAdapter;
import com.bekup.jadwalbioskop.model.City;
import com.bekup.jadwalbioskop.model.Movie;
import com.bekup.jadwalbioskop.model.MovieResponse;
import com.bekup.jadwalbioskop.model.Schedule;
import com.bekup.jadwalbioskop.networks.MovieService;
import com.bekup.jadwalbioskop.util.DividerItemDecoration;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private final static String API_KEY = "7e96bc9650c0ba99f9c458a2d9aa11d8";

    public final static String ARG_CITY = "Intent.CITY" ;

    private String id ;

    private List<Movie> movieList = new ArrayList<>() ;
    private List<Schedule> scheduleList = new ArrayList<>();
    private MovieAdapter adapter ;

    private FirebaseAnalytics mFirebaseAnalytics ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        City city = getIntent().getParcelableExtra(ARG_CITY);
        id = city.getId() ;

        loadData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_movie);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(int flatPos) {

                String movie = movieList.get(flatPos).getMovie() ;

                Bundle bundle = new Bundle();
                bundle.putString("movie_title", movie);
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                return false;
            }


        });
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Load data...");
        progressDialog.show();

        MovieService movieService = MovieService
                .retrofit.create(MovieService.class);
        Call<MovieResponse> call = movieService.getMovie(id, API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressDialog.dismiss();

                MovieResponse movieResponse = response.body();

                if (movieResponse != null) {

                    for (int i = 0; i < movieResponse.getData().size();i++) {

                        scheduleList.addAll(movieResponse.getData().get(i).getJadwal());

                        movieList.add(new Movie("movie", scheduleList,
                                movieResponse.getData().get(i).getMovie(),
                                movieResponse.getData().get(i).getPoster(),
                                movieResponse.getData().get(i).getGenre(),
                                movieResponse.getData().get(i).getDuration()));
                        adapter.notifyDataSetChanged();
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
