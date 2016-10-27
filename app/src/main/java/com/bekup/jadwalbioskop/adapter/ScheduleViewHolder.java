package com.bekup.jadwalbioskop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.Schedule;
import com.bekup.jadwalbioskop.util.FlowLayout;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by TRIPOD STUDIO on 10/28/2016.
 */

public class ScheduleViewHolder extends ChildViewHolder {

    private TextView theatre,price ;
    private FlowLayout timeLayout ;
    private Context context ;

    public ScheduleViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context ;
        theatre = (TextView) itemView.findViewById(R.id.theater);
        timeLayout = (FlowLayout) itemView.findViewById(R.id.layout_time);
        price = (TextView) itemView.findViewById(R.id.price);
    }

    public void onBind(Schedule schedule) {
        theatre.setText(schedule.getBioskop());

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_time, timeLayout, false);
        TextView time = (TextView) v.findViewById(R.id.time);

        for (int i = 0; i < schedule.getJam().size(); i++) {
            time.setText(schedule.getJam().get(i));
        }

        timeLayout.addView(v);
        price.setText(schedule.getHarga());
    }
}
