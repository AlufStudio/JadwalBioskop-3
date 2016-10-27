package com.bekup.jadwalbioskop.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by TRIPOD STUDIO on 10/24/2016.
 */

public class Movie extends ExpandableGroup<Schedule> {
    private String movie ;
    private String poster ;
    private String genre ;
    private String duration ;
    private List<Schedule> jadwal ;


    public Movie(String title, List<Schedule> items, String movie, String poster, String genre, String duration) {
        super(title, items);
        this.movie = movie;
        this.poster = poster;
        this.genre = genre;
        this.duration = duration;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Schedule> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Schedule> jadwal) {
        this.jadwal = jadwal;
    }
}
