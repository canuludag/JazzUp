package com.uludag.can.jazzup.ui.getaccess

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.uludag.can.jazzup.R
import com.uludag.can.jazzup.base.App
import com.uludag.can.jazzup.networking.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class GetAccessActivity : AppCompatActivity() {

    private val tag = GetAccessActivity::class.java.simpleName

    @Inject lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

        setContentView(R.layout.activity_login)
        setWhiteStatusBar(mainBackground)

    }

    private fun setWhiteStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }
}
