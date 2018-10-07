package com.uludag.can.jazzup.networking

import android.util.Base64
import com.uludag.can.jazzup.BuildConfig

object NetworkConstants {
    private const val credentials = BuildConfig.CLIENT_ID + ":" + BuildConfig.CLIENT_SECRET
    val tokenEndpoint = BuildConfig.GET_TOKEN_URL
    val basicAuthCredential: String = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
}