package com.uludag.can.jazzup.ui.jazzplaylists

import android.content.SharedPreferences
import com.uludag.can.jazzup.base.AppModule
import com.uludag.can.jazzup.networking.ApiModule
import com.uludag.can.jazzup.networking.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class, AppModule::class])
class JazzPlaylistsModule {

    @Provides
    fun provideJazzPlaylistsPresenter(model: JazzPlaylistsContract.Model, sharedPreferences: SharedPreferences):
            JazzPlaylistsContract.Presenter = JazzPlaylistsPresenter(model, sharedPreferences)

    @Provides
    fun provideRepository(apiService: ApiService): JazzPlaylistsContract.Model =
            JazzPlaylistsRepository(apiService)
}