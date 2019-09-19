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

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private List<Track> mTracks;
    private OnItemRecyclerViewClickListener<List<Track>, Track>
            mTrackOnItemRecyclerViewClickListener;

    TrackAdapter(List<Track> tracks) {
        mTracks = tracks;
    }

    void setTrackOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
        mTrackOnItemRecyclerViewClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_genres, parent, false),
                mTrackOnItemRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, OnGetImageFromUrlListener {
        private TextView mTextViewNam;
        private TextView mTextViewSinger;
        private ImageView mImageViewSong;
        private OnItemRecyclerViewClickListener<List<Track>, Track> mListener;

        ViewHolder(@NonNull View itemView,
                OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            mTextViewNam = itemView.findViewById(R.id.textViewNameSongItem);
            mTextViewSinger = itemView.findViewById(R.id.textViewNameSingerItem);
            mImageViewSong = itemView.findViewById(R.id.imageViewSongItem);
        }

        void bindView() {
            Track track = mTracks.get(getAdapterPosition());
            mTextViewNam.setText(track.getTitle());
            mTextViewSinger.setText(track.getArtistName());
            mImageViewSong.setClipToOutline(true);
            if (track.getArtworkUrl() != null) {
                getImageUrl(getBigImageUrl(track.getArtworkUrl()), this);
            } else {
                onGetImageFailed();
            }
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClickListener(mTracks, mTracks.get(getAdapterPosition()));
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
