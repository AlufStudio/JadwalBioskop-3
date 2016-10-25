package com.bekup.jadwalbioskop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bekup.jadwalbioskop.R;
import com.bekup.jadwalbioskop.model.City;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/25/2016.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private List<City> cityList ;

    public CityListAdapter(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);

        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.id.setText(city.getId());
        holder.city.setText(city.getKota());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public City getItem(int positon){
        return cityList.get(positon);
    }

    public void remove(City item) {
        int position = cityList.indexOf(item);
        if (position > -1) {
            cityList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }



    public class CityViewHolder extends RecyclerView.ViewHolder {

        TextView id, city ;

        public CityViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id_city);
            city = (TextView) itemView.findViewById(R.id.city);
        }
    }


}
