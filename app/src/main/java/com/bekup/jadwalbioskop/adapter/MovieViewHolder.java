package com.bekup.jadwalbioskop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.Movie;
import com.bumptech.glide.Glide;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by TRIPOD STUDIO on 10/28/2016.
 */

public class MovieViewHolder extends GroupViewHolder {

    private ImageView poster ;
    private TextView title, genre, duration ;
    private Context context ;
    //private FirebaseAnalytics mFire

    public MovieViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context ;

        poster = (ImageView) itemView.findViewById(R.id.poster);
        title = (TextView) itemView.findViewById(R.id.title);
        genre = (TextView) itemView.findViewById(R.id.genre);
        duration = (TextView)itemView.findViewById(R.id.duration);
    }

    public void setGroupName(ExpandableGroup movie) {

        //if (movie instanceof Movie) {
            Glide.with(context)
                    .load(((Movie) movie).getPoster())
                    .into(poster);

        Log.d("title", movie.getTitle());

            title.setText(((Movie) movie).getMovie());
            genre.setText(((Movie) movie).getGenre());
            duration.setText(((Movie) movie).getDuration());
        //}

    }
}
