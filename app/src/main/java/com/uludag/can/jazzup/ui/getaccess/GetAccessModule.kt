package com.uludag.can.jazzup.ui.getaccess

import com.uludag.can.jazzup.networking.ApiModule
import com.uludag.can.jazzup.networking.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class GetAccessModule {

    @Provides
    fun provideGetAccessPresenter(model: GetAccessContract.Model): GetAccessContract.Presenter = GetAccessPresenter(model)

    @Provides
    fun provideRepository(apiService: ApiService): GetAccessContract.Model = Repository(apiService)

}