package com.uludag.can.jazzup.ui.jazzplaylists;

import android.content.SharedPreferences;

import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistItem;
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistsResponse;
import com.uludag.can.jazzup.models.playlistswithtracks.PlaylistWithTracks;
import com.uludag.can.jazzup.networking.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class JazzPlaylistsRepository implements JazzPlaylistsContract.Model {

    private ApiService apiService;
    private SharedPreferences preferences;

    @Inject
    public JazzPlaylistsRepository(ApiService apiService, SharedPreferences preferences) {
        this.apiService = apiService;
        this.preferences = preferences;
    }

    @NotNull
    @Override
    public Single<PlaylistsResponse> getJazzPlaylists() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + preferences.getString("access_token", ""));
        return this.apiService.getPlaylists(headerMap, "jazz", "50");
    }

    @NotNull
    @Override
    public Single<PlaylistWithTracks> getPlaylistWithTracks(String playlistId) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + preferences.getString("access_token", ""));
        return this.apiService.getPlaylistDetail(headerMap, playlistId);
    }
}
