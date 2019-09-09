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
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private Context mContext;
    private List<Track> mTracks;

    public TrackAdapter(Context context, List<Track> tracks) {
        mContext = context;
        mTracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genres_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtSinger;
        private ImageView imgSong;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameSongItem);
            txtSinger = itemView.findViewById(R.id.txtNameSingerItem);
            imgSong = itemView.findViewById(R.id.imgSongItem);
        }

        void bindView() {
            Track track = mTracks.get(getAdapterPosition());
            txtName.setText(track.getTitle());
            txtSinger.setText(track.getArtistName());
            Glide.with(mContext)
                    .load(track.getArtworkUrl())
                    .error(R.drawable.ic_image_erro)
                    .into(imgSong);
        }
    }

    public void updateData(List<Track> data) {
        mTracks.clear();
        mTracks.addAll(data);
    }
}
