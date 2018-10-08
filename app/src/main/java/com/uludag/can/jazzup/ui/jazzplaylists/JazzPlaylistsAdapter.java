package com.uludag.can.jazzup.ui.jazzplaylists;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uludag.can.jazzup.R;

import java.util.List;

public class JazzPlaylistsAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {

    private List<PlaylistPresenter> playlistPresenterList;

    public JazzPlaylistsAdapter() {
    }

    public void setPlaylistsPresenterList(List<PlaylistPresenter> presenterList) {
        this.playlistPresenterList = presenterList;
        notifyDataSetChanged();
    }

    public void updateData(int position, PlaylistPresenter playlistPresenter) {
        this.playlistPresenterList.set(position, playlistPresenter);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_cell_item, parent, false);
        return new PlaylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        PlaylistPresenter playlistPresenter = this.playlistPresenterList.get(position);
        holder.setPresenter(playlistPresenter);
        playlistPresenter.setView(holder);
        playlistPresenter.loadData();
    }

    @Override
    public int getItemCount() {
        if (this.playlistPresenterList != null && !this.playlistPresenterList.isEmpty())
            return this.playlistPresenterList.size();
        else
            return 0;
    }
}
