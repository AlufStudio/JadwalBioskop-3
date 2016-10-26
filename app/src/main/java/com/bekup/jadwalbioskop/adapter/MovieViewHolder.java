package com.bekup.jadwalbioskop.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bekup.jadwalbioskop.R;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

/**
 * Created by Lenovo on 26/10/2016.
 */

public class MovieViewHolder extends ParentViewHolder {

    private ImageView poster;
    private TextView title;
    private TextView genre;
    private TextView duration;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        poster = (ImageView) itemView.findViewById(R.id.poster);
        title = (TextView) itemView.findViewById(R.id.title);
        genre = (TextView) itemView.findViewById(R.id.genre);
        duration = (TextView) itemView.findViewById(R.id.duration);
    }
}
