package com.uludag.can.jazzup.ui.jazzplaylists;

import com.uludag.can.jazzup.networking.ApiService;

import javax.inject.Inject;

public class JazzPlaylistsRepository implements JazzPlaylistsContract.Model {

    private ApiService apiService;

    @Inject
    public JazzPlaylistsRepository(ApiService apiService) {
        this.apiService = apiService;
    }
}
