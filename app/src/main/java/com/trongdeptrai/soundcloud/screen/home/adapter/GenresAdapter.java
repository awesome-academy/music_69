package com.trongdeptrai.soundcloud.screen.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter
        implements OnItemRecyclerViewClickListener<List<Track>, Track> {
    private static final int ITEM_VIEW_TRENDING = R.layout.item_trending;
    private static final int ITEM_VIEW_GENRE = R.layout.item_genres;
    private Context mContext;
    private List<Genre> mGenres;
    private OnItemRecyclerViewClickListener<List<Track>, Track> mOnItemRecyclerViewClickListener;

    public GenresAdapter(Context context) {
        mContext = context;
        mGenres = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_VIEW_TRENDING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_trending, parent, false);
            return new TrendingItem(mContext, mGenres.get(0).getTracks(), view,
                    mOnItemRecyclerViewClickListener);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_genres, parent, false);
            return new GenreItem(mContext, mGenres, view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_VIEW_TRENDING) {
            TrendingItem trendingItem = (TrendingItem) holder;
            trendingItem.bindView();
        } else {
            GenreItem genreItem = (GenreItem) holder;
            genreItem.bindView();
        }
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_VIEW_TRENDING;
        }
        return ITEM_VIEW_GENRE;
    }

    public void setOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
        mOnItemRecyclerViewClickListener = listener;
    }

    @Override
    public void onItemClickListener(List<Track> list, Track item) {
        mOnItemRecyclerViewClickListener.onItemClickListener(list, item);
    }

    static class TrendingItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private List<Track> mTracks;
        private RecyclerView mRecyclerViewTrending;
        private OnItemRecyclerViewClickListener<List<Track>, Track> mListener;

        TrendingItem(Context context, List<Track> tracks, @NonNull View itemView,
                OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
            super(itemView);
            mContext = context;
            mTracks = tracks;
            mListener = listener;
            mRecyclerViewTrending = itemView.findViewById(R.id.recyclerViewTrending);
            mRecyclerViewTrending.setHasFixedSize(true);
            RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
            mRecyclerViewTrending.setRecycledViewPool(viewPool);
            mRecyclerViewTrending.setLayoutManager(
                    new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }

        void bindView() {
            TrendingTrackAdapter adapter = new TrendingTrackAdapter(mContext, mTracks);
            mRecyclerViewTrending.setAdapter(adapter);
            adapter.setOnItemRecyclerViewClickListener(mListener);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClickListener(mTracks, mTracks.get(getAdapterPosition()));
        }
    }

    static class GenreItem extends RecyclerView.ViewHolder {
        GenreItem(Context context, List<Genre> genres, @NonNull View itemView) {
            super(itemView);
        }

        void bindView() {
        }
    }

    public void updateData(List<Genre> data) {
        mGenres.clear();
        mGenres.addAll(data);
        notifyItemInserted(mGenres.size());
    }
}
