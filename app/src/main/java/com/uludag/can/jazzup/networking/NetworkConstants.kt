package com.uludag.can.jazzup.networking

import android.util.Base64
import com.uludag.can.jazzup.BuildConfig

object NetworkConstants {
    private val credentials = BuildConfig.CLIENT_ID + ":" + BuildConfig.CLIENT_SECRET
    val basicAuthCredential = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
}