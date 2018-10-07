
package com.uludag.can.jazzup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistsResponse {

    @SerializedName("playlists")
    @Expose
    private Playlists playlists;

    public Playlists getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

}
