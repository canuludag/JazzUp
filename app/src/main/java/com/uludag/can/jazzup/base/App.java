package com.uludag.can.jazzup.base;

import android.app.Application;

import com.uludag.can.jazzup.di.AppComponent;
import com.uludag.can.jazzup.di.DaggerAppComponent;
import com.uludag.can.jazzup.networking.ApiModule;
import com.uludag.can.jazzup.ui.getaccess.GetAccessModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .getAccessModule(new GetAccessModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
