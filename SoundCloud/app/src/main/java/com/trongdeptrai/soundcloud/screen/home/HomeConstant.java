package com.trongdeptrai.soundcloud.screen.home;

import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.screen.BasePresenter;
import java.util.List;

public interface HomeConstant {
    interface View{
        void onGetTrendingTrackSucceed(List<Track> data);
        void onGetTrendingTrackFailed(Exception e);
        void getTrackByGenresSucceed(List<Track> data, String genre);
        void getTrackByGenresFailed(Exception e);
    }

    interface Presenter extends BasePresenter<View> {
        void GetHotTrend();
        void GetTrackByGenre(String genre);
    }
}
