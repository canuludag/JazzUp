package com.uludag.can.jazzup.ui.jazzplaylists;

import android.text.Html;
import android.text.Spanned;

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
        Spanned sp = Html.fromHtml(this.playlistCellItem.getDescription());
        this.playlistView.showDescription(sp.toString());
        if (!sp.toString().isEmpty())
            this.playlistView.hideProgressbar();
    }

    private void loadCreatedBy() {
        this.playlistView.showCreatedBy("Owner: " + this.playlistCellItem.getCreator());
    }

    private void loadTrackCount() {
        this.playlistView.showTrackCount(String.valueOf(this.playlistCellItem.getTrackCount()) + " Songs");
    }

    public PlaylistCellItem getPlaylistCellItem() {
        return this.playlistCellItem;
    }

    public void playOnSpotify() {
        this.playlistView.openOnSpotify(this.playlistCellItem.getId());
    }
}
