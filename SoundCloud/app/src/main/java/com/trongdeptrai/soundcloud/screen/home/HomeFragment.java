package com.trongdeptrai.soundcloud.screen.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.data.respository.TrackRespository;
import com.trongdeptrai.soundcloud.data.source.local.TrackLocalDataSource;
import com.trongdeptrai.soundcloud.data.source.remote.TrackRemoteDataSource;
import com.trongdeptrai.soundcloud.screen.BaseFragment;
import com.trongdeptrai.soundcloud.screen.home.adapter.GenresAdapter;
import com.trongdeptrai.soundcloud.screen.home.adapter.TrendindTrackAdapter;
import com.trongdeptrai.soundcloud.utils.Genres;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment implements HomeConstant.View,
        OnItemRecyclerViewClickListener<Object> {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private GenresAdapter mGenresAdapter;
    private TrendindTrackAdapter mTrendindTrackAdapter;
    private List<Genre> mGenres = new ArrayList<>();
    private RecyclerView mRecyclerViewHotTrend;
    private RecyclerView mRecyclerViewGenres;


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
    }


    public void initView(View v) {
        mGenresAdapter = new GenresAdapter(getContext(), mGenres);
        mTrendindTrackAdapter = new TrendindTrackAdapter(getContext());
        mTrendindTrackAdapter.setOnItemRecyclerViewClickListener(this);
        mRecyclerViewHotTrend = v.findViewById(R.id.recyclerviewHotTrend);
        mRecyclerViewGenres = v.findViewById(R.id.recyclerviewGenres);
        mRecyclerViewGenres.setNestedScrollingEnabled(false);

    }


    public void initData() {
        TrackLocalDataSource trackLocalDataSource = TrackLocalDataSource.getInstance();
        TrackRemoteDataSource trackRemoteDataSource = TrackRemoteDataSource.getInstance();
        TrackRespository respository = TrackRespository.getInstance(trackLocalDataSource, trackRemoteDataSource);
        HomeConstant.Presenter presenter =  new HomePresenter(respository);
        presenter.setView(this);
        presenter.GetHotTrend();
        presenter.GetTrackByGenre(Genres.ALL_AUDIO);
        presenter.GetTrackByGenre(Genres.ALL_MUSIC);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onGetTrendingTrackSucceed(List<Track> data) {

    }

    @Override
    public void onGetTrendingTrackFailed(Exception e) {

    }

    @Override
    public void getTrackByGenresSucceed(List<Track> data, String genre) {
        if (data != null) {
            mGenres.add(new Genre(genre, data));
        }
                mGenres.add(new Genre(genre, data));
        mGenresAdapter.updateData(mGenres);
    }

    @Override
    public void getTrackByGenresFailed(Exception e) {
        Log.d(TAG, Objects.requireNonNull(e.getMessage()));
    }

    @Override
    public void OnItemClickListener(Object item) {

    }
}
