package com.uludag.can.jazzup.ui.jazzplaylists

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.style.Wave
import com.uludag.can.jazzup.R
import kotlinx.android.synthetic.main.activity_get_access.*
import kotlinx.android.synthetic.main.playlist_cell_item.view.*

class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), PlaylistView {

    init {
        setupProgressBar()
    }

    private lateinit var playlistPresenter: PlaylistPresenter

    private fun setupProgressBar() {
        val waveProgressBar = Wave()
        waveProgressBar.color = itemView.resources.getColor(R.color.accentTextColor)
        this.itemView.cellProgressbar.indeterminateDrawable = waveProgressBar
    }

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

    override fun hideProgressbar() {
        this.itemView.cellProgressbar.visibility = GONE
    }
}