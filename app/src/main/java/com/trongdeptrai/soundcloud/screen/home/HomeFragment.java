package com.trongdeptrai.soundcloud.screen.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackRepository;
import com.trongdeptrai.soundcloud.data.source.local.TrackLocalDataSource;
import com.trongdeptrai.soundcloud.data.source.remote.TrackRemoteDataSource;
import com.trongdeptrai.soundcloud.screen.BaseFragment;
import com.trongdeptrai.soundcloud.utils.Genres;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View {
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
        TrackRepository trackRepo =
                TrackRepository.getInstance(trackLocalDataSource, trackRemoteDataSource);
        HomeContract.Presenter presenter = new HomePresenter(trackRepo);
        presenter.setView(this);
        presenter.getHotTrend();
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
        Log.d(TAG, "SIZE:\t " + data.size());
    }

    @Override
    public void onGetTrackByGenresSucceed(List<Track> data, String genre) {
        new ArrayList<Genre>().add(new Genre(genre, data));
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
