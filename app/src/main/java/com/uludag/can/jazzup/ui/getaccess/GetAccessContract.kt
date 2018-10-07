package com.uludag.can.jazzup.ui.getaccess

import com.uludag.can.jazzup.models.AccessToken
import io.reactivex.Single

interface GetAccessContract {
    interface View {
        fun showPlaylistsScreen()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
        fun loadData()
        fun setView(view: View)
        fun onGetAccessClicked()
    }

    interface Model {
        fun getAccessToken(): Single<AccessToken>
    }
}