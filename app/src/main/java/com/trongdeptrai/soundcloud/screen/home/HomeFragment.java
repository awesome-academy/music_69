package com.trongdeptrai.soundcloud.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackRespository;
import com.trongdeptrai.soundcloud.data.source.local.TrackLocalDataSource;
import com.trongdeptrai.soundcloud.data.source.remote.TrackRemoteDataSource;
import com.trongdeptrai.soundcloud.screen.BaseFragment;
import com.trongdeptrai.soundcloud.utils.Genres;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment
        implements HomeConstant.View, OnItemRecyclerViewClickListener<Object> {
    private static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initListener();
    }

    @Override
    public void initView(View v) {
    }

    @Override
    public void initData() {
        TrackLocalDataSource trackLocalDataSource = TrackLocalDataSource.getInstance();
        TrackRemoteDataSource trackRemoteDataSource = TrackRemoteDataSource.getInstance();
        TrackRespository trackRepo =
                TrackRespository.getInstance(trackLocalDataSource, trackRemoteDataSource);
        HomeConstant.Presenter presenter = new HomePresenter(trackRepo);
        presenter.setView(this);
        presenter.getTrackByGenre(Genres.ALL_MUSIC);
        presenter.getTrackByGenre(Genres.ALL_AUDIO);
        presenter.getTrackByGenre(Genres.ALTERNATIVE_ROCK);
        presenter.getTrackByGenre(Genres.AMBIENT);
        presenter.getTrackByGenre(Genres.CLASSICAL);
        presenter.getTrackByGenre(Genres.COUNTRY);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onGetTrendingTrackSucceed(List<Track> data) {
    }

    @Override
    public void onGetTrackByGenresSucceed(List<Track> data, String genre) {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(genre, data));
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onItemClickListener(Object item) {
    }
}
