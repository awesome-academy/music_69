package com.trongdeptrai.soundcloud.screen.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
        implements OnItemRecyclerViewClickListener<List<Track>> {
    private static final int ITEM_VIEW_TRENDING = R.layout.item_trending;
    private static final int ITEM_VIEW_GENRE = R.layout.item_genres;
    private List<Genre> mGenres;
    private OnItemRecyclerViewClickListener<List<Track>> mOnItemRecyclerViewClickListener;
    private OnClickItemMoreListener mOnClickItemMoreListener;

    public GenresAdapter() {
        mGenres = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TRENDING) {
            return new TrendingItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_trending, parent, false), mGenres.get(0).getTracks(),
                    mOnItemRecyclerViewClickListener);
        } else {
            return new GenreItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_genres, parent, false), mGenres,
                    mOnItemRecyclerViewClickListener, mOnClickItemMoreListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_VIEW_TRENDING) {
            TrendingItemViewHolder trendingItem = (TrendingItemViewHolder) holder;
            trendingItem.bindView();
        } else {
            GenreItemViewHolder genreItem = (GenreItemViewHolder) holder;
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
            OnItemRecyclerViewClickListener<List<Track>> listener) {
        mOnItemRecyclerViewClickListener = listener;
    }

    public void setOnClickItemMoreListener(OnClickItemMoreListener onClickItemMoreListener) {
        mOnClickItemMoreListener = onClickItemMoreListener;
    }

    public void updateData(List<Genre> data) {
        mGenres.clear();
        mGenres.addAll(data);
        notifyItemInserted(mGenres.size());
    }

    @Override
    public void onItemClickListener(List<Track> list, int position) {
        mOnItemRecyclerViewClickListener.onItemClickListener(list, position);
    }

    public interface OnClickItemMoreListener {
        void onClickMore(String genre);
    }

    static class TrendingItemViewHolder extends RecyclerView.ViewHolder {
        private List<Track> mTracks;
        private RecyclerView mRecyclerViewTrending;
        private OnItemRecyclerViewClickListener<List<Track>> mListener;

        TrendingItemViewHolder(@NonNull View itemView, List<Track> tracks,
                OnItemRecyclerViewClickListener<List<Track>> listener) {
            super(itemView);
            mTracks = tracks;
            mListener = listener;
            mRecyclerViewTrending = itemView.findViewById(R.id.recyclerViewTrending);
            mRecyclerViewTrending.setHasFixedSize(true);
            RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
            mRecyclerViewTrending.setRecycledViewPool(viewPool);
            mRecyclerViewTrending.setLayoutManager(
                    new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL,
                            false));
        }

        void bindView() {
            TrendingTrackAdapter adapter = new TrendingTrackAdapter(mTracks);
            mRecyclerViewTrending.setAdapter(adapter);
            adapter.setOnItemRecyclerViewClickListener(mListener);
        }
    }

    static class GenreItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private List<Genre> mGenres;
        private OnItemRecyclerViewClickListener<List<Track>>
                mOnItemRecyclerViewClickListener;
        private OnClickItemMoreListener mOnClickItemMoreListener;
        private RecyclerView mRecyclerViewType;
        private TextView mTextViewGenre;

        GenreItemViewHolder(@NonNull View itemView, List<Genre> genres,
                OnItemRecyclerViewClickListener<List<Track>> onItemRecyclerViewClickListener,
                OnClickItemMoreListener onClickItemMoreListener) {
            super(itemView);
            mGenres = genres;
            mTextViewGenre = itemView.findViewById(R.id.textViewTitleGenres);
            TextView textViewMore = itemView.findViewById(R.id.textViewMore);
            mOnItemRecyclerViewClickListener = onItemRecyclerViewClickListener;
            mOnClickItemMoreListener = onClickItemMoreListener;
            RecyclerView.RecycledViewPool mViewPool = new RecyclerView.RecycledViewPool();
            mRecyclerViewType = itemView.findViewById(R.id.recyclerViewGenreType);
            mRecyclerViewType.setHasFixedSize(true);
            mRecyclerViewType.setRecycledViewPool(mViewPool);
            mRecyclerViewType.setLayoutManager(
                    new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL,
                            false));
            textViewMore.setOnClickListener(this);
        }

        void bindView() {
            mTextViewGenre.setText(mGenres.get(getAdapterPosition()).getGenres());
            TrackAdapter adapter = new TrackAdapter(mGenres.get(getAdapterPosition()).getTracks());
            mRecyclerViewType.setAdapter(adapter);
            adapter.setItemClickListener(mOnItemRecyclerViewClickListener);
        }

        @Override
        public void onClick(View view) {
            if (mOnClickItemMoreListener != null) {
                mOnClickItemMoreListener.onClickMore(mGenres.get(getAdapterPosition()).getGenres());
            }
        }
    }
}
