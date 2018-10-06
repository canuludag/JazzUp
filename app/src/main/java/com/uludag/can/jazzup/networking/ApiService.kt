package com.uludag.can.jazzup.networking

import com.uludag.can.jazzup.models.AccessToken
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST
    fun getAccessToken(@HeaderMap header: HashMap<String, String>,
                       @Url url: String,
                       @Field("grant_type") grantType: String) : Single<AccessToken>
}