package com.trongdeptrai.soundcloud.screen.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.utils.OnItemRecyclerViewClickListener;
import java.util.List;

import static com.trongdeptrai.soundcloud.utils.Common.getBigImageUrl;

public class TrendingTrackAdapter extends RecyclerView.Adapter<TrendingTrackAdapter.ViewHolder> {
    private Context mContext;
    private List<Track> mTracks;
    private OnItemRecyclerViewClickListener<List<Track>, Track> mOnItemRecyclerViewClickListener;

    TrendingTrackAdapter(Context context, List<Track> tracks) {
        mContext = context;
        mTracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_trending, parent, false);
        return new ViewHolder(mContext, mTracks, view, mOnItemRecyclerViewClickListener);
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private List<Track> mTracks;
        private TextView txtName;
        private TextView txtSinger;
        private ImageView imgSong;
        private OnItemRecyclerViewClickListener<List<Track>, Track> mListener;

        ViewHolder(Context context, List<Track> tracks, @NonNull View itemView,
                OnItemRecyclerViewClickListener<List<Track>, Track> listener) {
            super(itemView);
            mContext = context;
            mTracks = tracks;
            mListener = listener;
            txtName = itemView.findViewById(R.id.txtNameSongTrendItem);
            txtSinger = itemView.findViewById(R.id.txtNameSingerTrendItem);
            imgSong = itemView.findViewById(R.id.imgSongTrendItem);
            itemView.setOnClickListener(this);
        }

        void bindView() {
            Track track = mTracks.get(getAdapterPosition());
            txtName.setText(track.getTitle());
            txtSinger.setText(track.getArtistName());
            Glide.with(mContext)
                    .load(getBigImageUrl(track.getArtworkUrl()))
                    .error(R.drawable.ic_music_default)
                    .into(imgSong);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClickListener(mTracks, mTracks.get(getAdapterPosition()));
        }
    }
}
