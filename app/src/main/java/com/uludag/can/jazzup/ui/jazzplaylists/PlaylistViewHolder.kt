package com.uludag.can.jazzup.ui.jazzplaylists

import android.support.v7.widget.RecyclerView
import android.view.View

class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), PlaylistView {

    private lateinit var playlistPresenter: PlaylistPresenter

    fun setPresenter(presenter: PlaylistPresenter) {
        this.playlistPresenter = presenter
    }

}