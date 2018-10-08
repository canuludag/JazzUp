package com.uludag.can.jazzup.ui.getaccess;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.uludag.can.jazzup.models.playlistswithcategory.AccessToken;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetAccessPresenter implements GetAccessContract.Presenter {

    private static final String TAG = GetAccessPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    public GetAccessContract.View view;
    public GetAccessContract.Model model;
    public SharedPreferences prefs;

    @Inject
    public GetAccessPresenter(GetAccessContract.Model model, SharedPreferences prefs) {
        this.model = model;
        this.prefs = prefs;
    }

    @Override
    public void setView(@NonNull GetAccessContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        checkAccessToken();
    }

    private void checkAccessToken() {
        this.view.showLoading();
        if (!this.prefs.getString("access_token", "").isEmpty()) {
            this.view.hideLoading();
            this.view.showPlaylistsScreen();
        } else {
            this.view.hideLoading();
            Log.i(TAG, "checkAccessToken: token not exist!");
        }
    }

    @Override
    public void onGetAccessClicked() {
        getNewAccessToken();
    }

    private void getNewAccessToken() {
        this.view.showLoading();
        Single<AccessToken> accessTokenSingle = this.model.getAccessToken();
        Disposable accessTokenDisposable = accessTokenSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AccessToken>() {
                    @Override
                    public void onSuccess(AccessToken accessToken) {
                        view.hideLoading();
                        saveToken(accessToken.getAccessToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                });
        addToDisposableBag(accessTokenDisposable);
    }

    private void saveToken(String accessToken) {
        this.prefs.edit().putString("access_token", accessToken).apply();
        openPlaylists();
    }

    private void openPlaylists() {
        this.view.showPlaylistsScreen();
    }

    private void addToDisposableBag(Disposable d) {
        this.compositeDisposable.add(d);
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
