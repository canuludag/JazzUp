package com.uludag.can.jazzup.di;

import com.uludag.can.jazzup.base.AppModule;
import com.uludag.can.jazzup.ui.getaccess.GetAccessActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(GetAccessActivity target);
}
