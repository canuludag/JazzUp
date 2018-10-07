package com.uludag.can.jazzup.base

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideContext(): Context = this.app.applicationContext

    @Provides
    fun provideSharedPrefs(): SharedPreferences = provideContext().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
}