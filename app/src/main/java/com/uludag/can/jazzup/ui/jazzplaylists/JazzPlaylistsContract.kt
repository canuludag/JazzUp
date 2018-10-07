package com.uludag.can.jazzup.ui.jazzplaylists

interface JazzPlaylistsContract {
    interface View {

    }

    interface Presenter {
        fun setView(view: View)
        fun onCreate()
        fun onDestroy()
        fun loadData()
    }

    interface Model {

    }
}