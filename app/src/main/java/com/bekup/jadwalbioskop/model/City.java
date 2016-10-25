package com.bekup.jadwalbioskop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TRIPOD STUDIO on 10/23/2016.
 */

public class City implements Parcelable {
    private String id ;
    private String kota ;

    protected City(Parcel in) {
        id = in.readString();
        kota = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(kota);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
