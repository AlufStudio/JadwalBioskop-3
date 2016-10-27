package com.bekup.jadwalbioskop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.Movie;
import com.bekup.jadwalbioskop.model.Schedule;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/27/2016.
 */

public class MovieAdapter extends ExpandableRecyclerViewAdapter<MovieViewHolder, ScheduleViewHolder> {

    private Context context ;

    public MovieAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context ;
    }

    @Override
    public MovieViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_movie, parent, false);

        return new MovieViewHolder(view,context);
    }

    @Override
    public ScheduleViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_schedule, parent, false);

        return new ScheduleViewHolder(view,context);
    }

    @Override
    public void onBindChildViewHolder(ScheduleViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Schedule schedule = ((Movie) group).getItems().get(childIndex);

        holder.onBind(schedule);
    }

    @Override
    public void onBindGroupViewHolder(MovieViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}
