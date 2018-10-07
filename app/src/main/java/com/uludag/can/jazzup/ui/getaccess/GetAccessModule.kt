package com.uludag.can.jazzup.ui.getaccess

import android.content.SharedPreferences
import com.uludag.can.jazzup.base.AppModule
import com.uludag.can.jazzup.networking.ApiModule
import com.uludag.can.jazzup.networking.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class, AppModule::class])
class GetAccessModule {

    @Provides
    fun provideGetAccessPresenter(model: GetAccessContract.Model, sharedPreferences: SharedPreferences):
            GetAccessContract.Presenter = GetAccessPresenter(model, sharedPreferences)

    @Provides
    fun provideRepository(apiService: ApiService): GetAccessContract.Model = Repository(apiService)

}