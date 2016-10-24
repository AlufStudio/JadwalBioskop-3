package com.bekup.jadwalbioskop.model;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/23/2016.
 */

public class CityResponse {
    private String status ;
    private List<City> data ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }
}
