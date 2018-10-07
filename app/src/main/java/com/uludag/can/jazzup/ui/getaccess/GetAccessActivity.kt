package com.uludag.can.jazzup.ui.getaccess

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.uludag.can.jazzup.R
import com.uludag.can.jazzup.base.App
import com.uludag.can.jazzup.ui.jazzplaylists.JazzPlaylistsActivity
import kotlinx.android.synthetic.main.activity_get_access.*
import javax.inject.Inject

class GetAccessActivity : AppCompatActivity(), GetAccessContract.View {

    private val tag = GetAccessActivity::class.java.simpleName

    @Inject
    lateinit var presenter: GetAccessContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_access)
        (application as App).appComponent.inject(this)
        setupPresenter()

        setWhiteStatusBar(mainBackground)
        setClickListeners()
    }

    private fun setupPresenter() {
        this.presenter.setView(this)
        this.presenter.onCreate()
    }

    private fun setClickListeners() {
        getAccessBtn.setOnClickListener { this.presenter.onGetAccessClicked() }
    }

    private fun setWhiteStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }

    override fun showPlaylistsScreen() {
        val intent = Intent(this, JazzPlaylistsActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showLoading() {
        this.getAccessBtn.visibility = GONE
        this.progressBar.visibility = VISIBLE
    }

    override fun hideLoading() {
        this.getAccessBtn.visibility = VISIBLE
        this.progressBar.visibility = GONE
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
