package com.trongdeptrai.soundcloud.data.model;

import java.util.List;

public class Genre {
    private String mGenres;
    private List<Track> mTracks;

    public Genre() {
    }

    public Genre(String genres, List<Track> tracks) {
        mGenres = genres;
        mTracks = tracks;
    }

    public String getGenres() {
        return mGenres;
    }

    public void setGenres(String genres) {
        mGenres = genres;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(List<Track> tracks) {
        mTracks = tracks;
    }
}
