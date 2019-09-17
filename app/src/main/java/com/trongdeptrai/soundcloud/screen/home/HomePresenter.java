package com.trongdeptrai.soundcloud.screen.home;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackRepository;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;
import com.trongdeptrai.soundcloud.utils.Genres;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private TrackRepository mTrackRepository;

    HomePresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void getHotTrend() {
        mTrackRepository.getTrendingTracks(new OnFetchDataListener<Track>() {
            @Override
            public void onSucceed(List<Track> data) {
                if (data != null) mView.onGetTrendingTrackSucceed(data);
            }

            @Override
            public void onFailed(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void getTrackByGenre(final String genre) {
        mTrackRepository.getTracksByGenre(genre, new OnFetchDataListener<Track>() {
            @Override
            public void onSucceed(List<Track> data) {
                if (data != null) mView.onGetTrackByGenresSucceed(data, renameGenre(genre));
            }

            @Override
            public void onFailed(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void setView(HomeContract.View view) {
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
