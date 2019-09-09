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

public class TrendindTrackAdapter extends RecyclerView.Adapter<TrendindTrackAdapter.ViewHolder> {
    private Context mContext;
    private List<Track> mTracks;
    private OnItemRecyclerViewClickListener<Object> mObjectOnItemRecyclerViewClickListener;
    public TrendindTrackAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trending, parent, false);
        return new ViewHolder(view, mObjectOnItemRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public void setOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<Object> onItemClickListener) {
        mObjectOnItemRecyclerViewClickListener = onItemClickListener;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtName;
        private TextView txtSinger;
        private ImageView imgSong;
        private OnItemRecyclerViewClickListener<Object> mListener;
        ViewHolder(@NonNull View itemView, OnItemRecyclerViewClickListener<Object> listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameSongTrendItem);
            txtSinger = itemView.findViewById(R.id.txtNameSingerTrendItem);
            imgSong = itemView.findViewById(R.id.imgSongTrendItem);
            itemView.setOnClickListener(this);
            mListener = listener;
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

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.OnItemClickListener(mTracks.get(getAdapterPosition()));
            }
        }
    }

    public void updateData(List<Track> data) {
        mTracks.clear();
        mTracks.addAll(data);
    }
}
