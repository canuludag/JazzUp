package com.uludag.can.jazzup.ui.jazzplaylists

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.playlist_cell_item.view.*

class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), PlaylistView {

    private lateinit var playlistPresenter: PlaylistPresenter

    fun setPresenter(presenter: PlaylistPresenter) {
        this.playlistPresenter = presenter
    }

    override fun showCoverImage(imageUrl: String) {
        Glide.with(itemView).load(imageUrl).into(this.itemView.coverImage)
    }

    override fun showTitle(title: String) {
        this.itemView.playlistTitle.text = title
    }

    override fun showDescription(description: String) {
        this.itemView.playlistDescription.text = description
    }

    override fun showTrackCount(count: String) {
        this.itemView.playlistTrackCount.text = count
    }

    override fun showCreatedBy(creator: String) {
        this.itemView.playlistCreatedBy.text = creator
    }
}