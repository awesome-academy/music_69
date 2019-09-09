package com.trongdeptrai.soundcloud.screen.home;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.respository.TrackRespository;
import com.trongdeptrai.soundcloud.data.source.remote.OnFetchDataListener;
import java.util.List;

public class HomePresenter implements HomeConstant.Presenter{
    private HomeConstant.View mView;
    private TrackRespository mTrackRespository;

    public HomePresenter(TrackRespository trackRespository) {
        mTrackRespository = trackRespository;
    }

    @Override
    public void GetHotTrend() {
        mTrackRespository.getTrendingTracks(new OnFetchDataListener<Track>() {
            @Override
            public void onSucceed(List<Track> data) {
                mView.onGetTrendingTrackSucceed(data);
            }

            @Override
            public void onFaied(Exception ex) {
                mView.onGetTrendingTrackFailed(ex);
            }
        });
    }

    @Override
    public void GetTrackByGenre(final String genre) {
        mTrackRespository.getTracksByGenre(genre, new OnFetchDataListener<Track>() {
            @Override
            public void onSucceed(List<Track> data) {
                mView.getTrackByGenresSucceed(data, genre);
            }

            @Override
            public void onFaied(Exception ex) {
                mView.getTrackByGenresFailed(ex);
            }
        });
    }

    @Override
    public void setView(HomeConstant.View view) {
        mView = view;
    }
}
