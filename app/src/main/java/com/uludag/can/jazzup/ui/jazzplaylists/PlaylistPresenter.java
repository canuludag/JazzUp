package com.uludag.can.jazzup.ui.jazzplaylists;

import com.uludag.can.jazzup.models.PlaylistItem;

public class PlaylistPresenter {

    private PlaylistItem playlistItem;
    public PlaylistView playlistView;

    public PlaylistPresenter(PlaylistItem playlistItem) {
        this.playlistItem = playlistItem;
    }

    public void setView(PlaylistView view) {
        this.playlistView = view;
    }

    public void loadData() {

    }

}
