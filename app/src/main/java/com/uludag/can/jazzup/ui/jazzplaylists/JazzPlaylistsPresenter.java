package com.uludag.can.jazzup.ui.jazzplaylists;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class JazzPlaylistsPresenter implements JazzPlaylistsContract.Presenter {

    private static final String TAG = JazzPlaylistsPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    public JazzPlaylistsContract.View view;
    public JazzPlaylistsContract.Model model;
    public SharedPreferences preferences;

    @Inject
    public JazzPlaylistsPresenter(JazzPlaylistsContract.Model model, SharedPreferences preferences) {
        this.model = model;
        this.preferences = preferences;
    }

    @Override
    public void setView(@NotNull JazzPlaylistsContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onCreate() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        this.compositeDisposable.dispose();
    }

}
