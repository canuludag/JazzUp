package com.uludag.can.jazzup.ui.jazzplaylists

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.uludag.can.jazzup.R
import com.uludag.can.jazzup.base.App
import com.uludag.can.jazzup.ui.getaccess.GetAccessActivity
import kotlinx.android.synthetic.main.activity_jazz_playlists.*
import javax.inject.Inject

class JazzPlaylistsActivity : AppCompatActivity(), JazzPlaylistsContract.View {

    private val playlistAdapter = JazzPlaylistsAdapter()
    @Inject
    lateinit var presenter: JazzPlaylistsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jazz_playlists)
        (application as App).appComponent.inject(this)
        setWhiteStatusBar(this.toolbar)
        setupPresenter()
        setupRecyclerViewAndAdapter()
    }

    private fun setWhiteStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }

    private fun setupRecyclerViewAndAdapter() {
        val mLayoutManager = LinearLayoutManager(applicationContext)
        this.playlistRecyclerView.layoutManager = mLayoutManager
        this.playlistRecyclerView.itemAnimator = DefaultItemAnimator()
        this.playlistRecyclerView.adapter = this.playlistAdapter
    }

    override fun populateAdapter(playlistPresenters: MutableList<PlaylistPresenter>) {
        this.playlistAdapter.setPlaylistsPresenterList(playlistPresenters)
    }

    override fun updateAdapterData(position: Int, playlistPresenter: PlaylistPresenter) {
       this.playlistAdapter.updateData(position, playlistPresenter)
    }

    override fun showGetAccessScreen() {
        val intent = Intent(this, GetAccessActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupPresenter() {
        this.presenter.setView(this)
        this.presenter.onCreate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.onDestroy()
    }
}

