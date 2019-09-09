package com.trongdeptrai.soundcloud.data.source.local;

import android.content.ContentResolver;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.respository.TrackDataResoure;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;

public class TrackLocalDataSource implements TrackDataResoure.LocalDataResoure {
    private static TrackLocalDataSource sLocalDataSource;

    public static TrackLocalDataSource getInstance(){
        if(sLocalDataSource == null){
            sLocalDataSource = new TrackLocalDataSource();
        }
        return sLocalDataSource;
    }

    @Override
    public void getMusicGenres(OnFetchDataListener<Genre> listener) {

    }

    @Override
    public void getLocalTracks(ContentResolver contentResolver,
            OnFetchDataListener<Track> listener) {

    }
}
