package com.uludag.can.jazzup.base

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideContext() = this.app.appComponent
}