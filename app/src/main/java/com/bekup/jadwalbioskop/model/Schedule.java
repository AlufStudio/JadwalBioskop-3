package com.bekup.jadwalbioskop.model;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/24/2016.
 */

public class Schedule {
    private String bioskop ;
    private List<String> jam ;
    private String harga ;

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
}
