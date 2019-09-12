package com.trongdeptrai.soundcloud.data.repository;

import android.content.ContentResolver;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;

public interface TrackDataResoure {
    interface LocalDataResoure {
        void getMusicGenres(OnFetchDataListener<Genre> listener);

        void getLocalTracks(ContentResolver contentResolver, OnFetchDataListener<Track> listener);
    }

    interface RemoteDataResoure {
        void getTrendingTracks(OnFetchDataListener<Track> listener);

        void getTracksByGenre(String genreType, OnFetchDataListener<Track> listener);

        void querySearch(String keyword, OnFetchDataListener<Track> listener);
    }
}
