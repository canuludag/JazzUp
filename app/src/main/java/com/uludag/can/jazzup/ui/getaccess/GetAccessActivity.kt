package com.uludag.can.jazzup.ui.getaccess

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.uludag.can.jazzup.R
import com.uludag.can.jazzup.base.App
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class GetAccessActivity : AppCompatActivity(), GetAccessContract.View {

    private val tag = GetAccessActivity::class.java.simpleName

    @Inject
    lateinit var presenter: GetAccessContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as App).appComponent.inject(this)
        this.presenter.setView(this)

        setWhiteStatusBar(mainBackground)
        setClickListeners()
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.presenter.loadData()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter.onDestroy()
    }
}
