package com.trongdeptrai.soundcloud.screen.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.data.model.Genre;
import com.trongdeptrai.soundcloud.data.model.Track;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private Context mContext;
    private List<Genre> mGenres;
    private RecyclerView.RecycledViewPool mViewPool = new RecyclerView.RecycledViewPool();
    public GenresAdapter(Context context, List<Genre> genres) {
        mContext = context;
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genres, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private RecyclerView mRecyclerViewGenresType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitleGenres);
            mRecyclerViewGenresType = itemView.findViewById(R.id.recyclerviewGenreType);
        }

        public void bindView() {
            txtTitle.setText(mGenres.get(getAdapterPosition()).getGenres());
            TrackAdapter adapter =
                    new TrackAdapter(mContext, mGenres.get(getAdapterPosition()).getTracks());
            mRecyclerViewGenresType.setRecycledViewPool(mViewPool);
            mRecyclerViewGenresType.setAdapter(adapter);
        }
    }

    public void updateData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
    }
}
