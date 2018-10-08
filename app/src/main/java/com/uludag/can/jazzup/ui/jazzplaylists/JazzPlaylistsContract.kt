package com.uludag.can.jazzup.ui.jazzplaylists

import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistItem
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistsResponse
import com.uludag.can.jazzup.models.playlistswithtracks.PlaylistWithTracks
import io.reactivex.Single

interface JazzPlaylistsContract {
    interface View {
        fun populateAdapter(playlistPresenters: MutableList<PlaylistPresenter>)
        fun updateAdapterData(position: Int, playlistPresenter: PlaylistPresenter)
        fun showGetAccessScreen()
    }

    interface Presenter {
        fun setView(view: View)
        fun onCreate()
        fun onDestroy()
        fun loadData()
    }

    interface Model {
        fun getJazzPlaylists(): Single<PlaylistsResponse>
        fun getPlaylistWithTracks(playlistId: String): Single<PlaylistWithTracks>
    }
}