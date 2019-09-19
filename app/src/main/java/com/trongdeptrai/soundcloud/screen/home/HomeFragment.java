package com.trongdeptrai.soundcloud.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.repository.TrackRepository;
import com.trongdeptrai.soundcloud.data.source.local.TrackLocalDataSource;
import com.trongdeptrai.soundcloud.data.source.remote.TrackRemoteDataSource;
import com.trongdeptrai.soundcloud.screen.BaseFragment;
import com.trongdeptrai.soundcloud.screen.home.adapter.GenresAdapter;
import com.trongdeptrai.soundcloud.utils.Genres;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment
        implements HomeContract.View, OnItemRecyclerViewClickListener<List<Track>, Track>,
        GenresAdapter.OnClickItemMoreListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final String HOT_TREND = "Hot Trend";
    private GenresAdapter mGenresAdapter;
    private List<Genre> mGenres = new ArrayList<>();

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
        RecyclerView recyclerViewHome = v.findViewById(R.id.recyclerViewHome);
        recyclerViewHome.setHasFixedSize(true);
        mGenresAdapter = new GenresAdapter();
        recyclerViewHome.setAdapter(mGenresAdapter);
        mGenresAdapter.setOnItemRecyclerViewClickListener(this);
        mGenresAdapter.setOnClickItemMoreListener(this);
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
        mGenres.add(0, new Genre(HOT_TREND, data));
        mGenresAdapter.updateData(mGenres);
    }

    @Override
    public void onGetTrackByGenresSucceed(List<Track> data, String genre) {
        mGenres.add(new Genre(genre, data));
        mGenresAdapter.updateData(mGenres);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickListener(List<Track> list, Track item) {
    }

    @Override
    public void onClickMore(Genre genre) {
    }
}
