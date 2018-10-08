package com.uludag.can.jazzup.networking

import com.uludag.can.jazzup.models.playlistswithcategory.AccessToken
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistItem
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistsResponse
import com.uludag.can.jazzup.models.playlistswithtracks.PlaylistWithTracks
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST
    fun getAccessToken(@HeaderMap header: HashMap<String, String>,
                       @Url url: String,
                       @Field("grant_type") grantType: String): Single<AccessToken>

    @GET("browse/categories/{categoryId}/playlists")
    fun getPlaylists(@HeaderMap header: HashMap<String, String>,
                     @Path("categoryId") categoryId: String,
                     @Query("limit") limit: String): Single<PlaylistsResponse>

    @GET("playlists/{playlistId}")
    fun getPlaylistDetail(@HeaderMap header: HashMap<String, String>,
                     @Path("playlistId") playlistId: String): Single<PlaylistWithTracks>
}