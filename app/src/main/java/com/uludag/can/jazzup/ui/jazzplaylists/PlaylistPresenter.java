package com.uludag.can.jazzup.ui.jazzplaylists;

import com.uludag.can.jazzup.models.PlaylistCellItem;

public class PlaylistPresenter {

    private PlaylistCellItem playlistCellItem;
    public PlaylistView playlistView;

    public PlaylistPresenter(PlaylistCellItem playlistCellItem) {
        this.playlistCellItem = playlistCellItem;
    }

    public void setView(PlaylistView view) {
        this.playlistView = view;
    }

    public void loadData() {
        loadImage();
        loadTitle();
        loadDescription();
        loadCreatedBy();
        loadTrackCount();
    }

    private void loadImage() {
        this.playlistView.showCoverImage(this.playlistCellItem.getImageUrl());
    }

    private void loadTitle() {
        this.playlistView.showTitle(this.playlistCellItem.getTitle());
    }

    private void loadDescription() {
        this.playlistView.showDescription(this.playlistCellItem.getDescription());
    }

    private void loadCreatedBy() {
        this.playlistView.showCreatedBy("Created by " + this.playlistCellItem.getCreator());
    }

    private void loadTrackCount() {
        this.playlistView.showTrackCount(String.valueOf(this.playlistCellItem.getTrackCount()) + " Songs");
    }

    public PlaylistCellItem getPlaylistCellItem() {
        return playlistCellItem;
    }
}
