package com.trongdeptrai.soundcloud.screen.home.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.utils.OnGetImageFromUrlListener;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.List;

import static com.trongdeptrai.soundcloud.utils.Common.getBigImageUrl;
import static com.trongdeptrai.soundcloud.utils.Common.getImageUrl;

public class TrendingTrackAdapter extends RecyclerView.Adapter<TrendingTrackAdapter.ViewHolder> {
    private List<Track> mTracks;
    private OnItemRecyclerViewClickListener<List<Track>, Track> mOnItemRecyclerViewClickListener;

    TrendingTrackAdapter(List<Track> tracks) {
        mTracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_trending, parent, false), mTracks,
                mOnItemRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    void setOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
        mOnItemRecyclerViewClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, OnGetImageFromUrlListener {
        private List<Track> mTracks;
        private TextView mTextViewName;
        private TextView mTextViewSinger;
        private ImageView mImageViewSong;
        private OnItemRecyclerViewClickListener<List<Track>, Track> mListener;

        ViewHolder(@NonNull View itemView, List<Track> tracks,
                OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
            super(itemView);
            mTracks = tracks;
            mListener = listener;
            mTextViewName = itemView.findViewById(R.id.textViewNameSongTrendItem);
            mTextViewSinger = itemView.findViewById(R.id.textViewNameSingerTrendItem);
            mImageViewSong = itemView.findViewById(R.id.imageViewSongTrendItem);
            itemView.setOnClickListener(this);
            mImageViewSong.setClipToOutline(true);
        }

        void bindView() {
            Track track = mTracks.get(getAdapterPosition());
            mTextViewName.setText(track.getTitle());
            mTextViewSinger.setText(track.getArtistName());
            String url = getBigImageUrl(track.getArtworkUrl());
            getImageUrl(url, this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClickListener(mTracks, mTracks.get(getAdapterPosition()));
            }
        }

        @Override
        public void onGetImageSucceed(Bitmap bitmap) {
            mImageViewSong.setImageBitmap(bitmap);
        }

        @Override
        public void onGetImageFailed() {
            mImageViewSong.setImageResource(R.drawable.ic_music_default);
        }
    }
}
