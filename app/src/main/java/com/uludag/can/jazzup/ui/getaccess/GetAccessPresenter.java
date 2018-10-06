package com.uludag.can.jazzup.ui.getaccess;

import android.util.Log;

import com.uludag.can.jazzup.models.AccessToken;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetAccessPresenter implements GetAccessContract.Presenter {

    private static final String TAG = GetAccessPresenter.class.getSimpleName();
    private GetAccessContract.View view;
    private GetAccessContract.Model model;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public GetAccessPresenter(GetAccessContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(GetAccessContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onGetAccessClicked() {
        Single<AccessToken> accessTokenSingle = this.model.getAccessToken();
        addToDisposableBag(accessTokenSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AccessToken>() {
                    @Override
                    public void onSuccess(AccessToken accessToken) {
                        Log.i(TAG, "onSuccess: " + accessToken.getAccessToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                }));
    }

    private void addToDisposableBag(Disposable d) {
        this.compositeDisposable.add(d);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        this.compositeDisposable.dispose();
    }
}
