package com.uludag.can.jazzup.ui.jazzplaylists

interface PlaylistView {
    fun showCoverImage(imageUrl: String)
    fun showTitle(title: String)
    fun showDescription(description: String)
    fun showTrackCount(count: String)
    fun showCreatedBy(creator: String)
    fun hideProgressbar()
}