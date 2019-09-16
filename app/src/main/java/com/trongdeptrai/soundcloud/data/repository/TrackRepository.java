package com.trongdeptrai.soundcloud.data.repository;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;

public class TrackRepository {
    private static TrackRepository sRespository;
    private TrackDataResoure.LocalDataResoure mLocalDataSource;
    private TrackDataResoure.RemoteDataResoure mRemoteDataSource;

    private TrackRepository(TrackDataResoure.LocalDataResoure localDataResoure,
            TrackDataResoure.RemoteDataResoure remoteDataResoure) {
        mLocalDataSource = localDataResoure;
        mRemoteDataSource = remoteDataResoure;
    }

    public static TrackRepository getInstance(TrackDataResoure.LocalDataResoure localDataResoure,
            TrackDataResoure.RemoteDataResoure remoteDataResoure) {
        if (sRespository == null) {
            sRespository = new TrackRepository(localDataResoure, remoteDataResoure);
        }
        return sRespository;
    }

    public void getTrendingTracks(OnFetchDataListener<Track> listener) {
        mRemoteDataSource.getTrendingTracks(listener);
    }

    public void getTracksByGenre(String genreType, OnFetchDataListener<Track> listener) {
        mRemoteDataSource.getTracksByGenre(genreType, listener);
    }
}
