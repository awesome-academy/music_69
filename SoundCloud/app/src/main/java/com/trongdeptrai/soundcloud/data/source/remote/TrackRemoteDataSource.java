package com.trongdeptrai.soundcloud.data.source.remote;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.respository.TrackDataResoure;

public class TrackRemoteDataSource implements TrackDataResoure.RemoteDataResoure {
    private static TrackRemoteDataSource sRemoteDataSource;

    public static TrackRemoteDataSource getInstance(){
        if(sRemoteDataSource == null){
            sRemoteDataSource = new TrackRemoteDataSource();
        }
        return sRemoteDataSource;
    }

    @Override
    public void getTrendingTracks(OnFetchDataListener<Track> listener) {

    }

    @Override
    public void getTracksByGenre(String genreType, OnFetchDataListener<Track> listener) {

    }

    @Override
    public void querySearch(String keyword, OnFetchDataListener<Track> listener) {

    }
}
