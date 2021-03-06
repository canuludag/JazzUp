package com.uludag.can.jazzup.di;

import com.uludag.can.jazzup.base.AppModule;
import com.uludag.can.jazzup.networking.ApiModule;
import com.uludag.can.jazzup.ui.getaccess.GetAccessActivity;
import com.uludag.can.jazzup.ui.getaccess.GetAccessModule;
import com.uludag.can.jazzup.ui.jazzplaylists.JazzPlaylistsActivity;
import com.uludag.can.jazzup.ui.jazzplaylists.JazzPlaylistsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class,
        GetAccessModule.class, JazzPlaylistsModule.class})
public interface AppComponent {

    void inject(GetAccessActivity target);

    void inject(JazzPlaylistsActivity target);
}
