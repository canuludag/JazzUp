package com.uludag.can.jazzup.ui.getaccess;

import com.uludag.can.jazzup.models.playlistswithcategory.AccessToken;
import com.uludag.can.jazzup.networking.ApiService;
import com.uludag.can.jazzup.networking.NetworkConstants;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAccessRepository implements GetAccessContract.Model {

    private ApiService apiService;

    @Inject
    GetAccessRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @NotNull
    @Override
    public Single<AccessToken> getAccessToken() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Basic " + NetworkConstants.INSTANCE.getBasicAuthCredential());
        return this.apiService.getAccessToken(headerMap, NetworkConstants.INSTANCE.getTokenEndpoint()
                , "client_credentials");
    }
}
