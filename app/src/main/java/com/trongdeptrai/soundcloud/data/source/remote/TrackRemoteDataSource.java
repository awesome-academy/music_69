package com.trongdeptrai.soundcloud.data.source.remote;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackDataResoure;
import com.trongdeptrai.soundcloud.utils.Constant;
import com.trongdeptrai.soundcloud.utils.QueryType;

public class TrackRemoteDataSource implements TrackDataResoure.RemoteDataResoure {
    private static TrackRemoteDataSource sRemoteDataSource;

    public static TrackRemoteDataSource getInstance() {
        if (sRemoteDataSource == null) {
            sRemoteDataSource = new TrackRemoteDataSource();
        }
        return sRemoteDataSource;
    }

    @Override
    public void getTrendingTracks(OnFetchDataListener<Track> listener) {
        new GetTracksAysncTask(listener, QueryType.GET_TRACKS)
                .execute(Constant.TRENDING_MUSIC_URL);
    }

    @Override
    public void getTracksByGenre(String genreType, OnFetchDataListener<Track> listener) {
        new GetTracksAysncTask(listener, QueryType.GET_TRACKS).execute(
                Constant.TRACK_MUSIC_URL + genreType + Constant.CLIENT_ID);
    }

    @Override
    public void querySearch(String keyword, OnFetchDataListener<Track> listener) {
    }
}
