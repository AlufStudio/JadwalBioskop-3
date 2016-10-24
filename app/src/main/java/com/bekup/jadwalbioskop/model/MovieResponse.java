package com.bekup.jadwalbioskop.model;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/24/2016.
 */

public class MovieResponse {
    private String status ;
    private String kota;
    private String date ;

    private List<Movie> data ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
}
