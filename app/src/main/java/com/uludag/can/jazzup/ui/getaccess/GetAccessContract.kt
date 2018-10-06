package com.uludag.can.jazzup.ui.getaccess

import com.uludag.can.jazzup.models.AccessToken
import io.reactivex.Single

interface GetAccessContract {
    interface View {

    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
        fun loadData()
        fun setView(view: GetAccessContract.View)
        fun onGetAccessClicked()
    }

    interface Model {
        fun getAccessToken(): Single<AccessToken>
    }
}