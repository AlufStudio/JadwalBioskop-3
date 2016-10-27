package com.bekup.jadwalbioskop.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/24/2016.
 */

public class Schedule implements Parcelable {
    private String bioskop ;
    private List<String> jam ;
    private String harga ;

    protected Schedule(Parcel in) {
        bioskop = in.readString();
        jam = in.createStringArrayList();
        harga = in.readString();
    }

    public Schedule(String bioskop, List<String> jam, String harga) {
        this.bioskop = bioskop;
        this.jam = jam;
        this.harga = harga;
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bioskop);
        parcel.writeStringList(jam);
        parcel.writeString(harga);
    }
}
