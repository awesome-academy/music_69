package com.trongdeptrai.soundcloud.screen.home;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackRespository;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;
import com.trongdeptrai.soundcloud.utils.Genres;
import java.util.List;

public class HomePresenter implements HomeConstant.Presenter {
    private HomeConstant.View mView;
    private TrackRespository mTrackRespository;

    HomePresenter(TrackRespository trackRespository) {
        mTrackRespository = trackRespository;
    }

    @Override
    public void getHotTrend() {
    }

    @Override
    public void getTrackByGenre(final String genre) {
        mTrackRespository.getTracksByGenre(genre, new OnFetchDataListener<Track>() {
            @Override
            public void onSucceed(List<Track> data) {
                if(data != null){
                    mView.onGetTrackByGenresSucceed(data, renameGenre(genre));
                }
            }

            @Override
            public void onFailed(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void setView(HomeConstant.View view) {
        mView = view;
    }

    private String renameGenre(String genre) {
        switch (genre) {
            case Genres.ALL_MUSIC:
                return Genres.ALL_MUSIC_TITLE;
            case Genres.ALL_AUDIO:
                return Genres.ALL_AUDIO_TITLE;
            case Genres.ALTERNATIVE_ROCK:
                return Genres.ALTERNATIVE_ROCK_TITLE;
            case Genres.AMBIENT:
                return Genres.AMBIENT_TITLE;
            case Genres.CLASSICAL:
                return Genres.CLASSICAL_TITLE;
            case Genres.COUNTRY:
                return Genres.COUNTRY_TITLE;
        }
        return "";
    }
}
