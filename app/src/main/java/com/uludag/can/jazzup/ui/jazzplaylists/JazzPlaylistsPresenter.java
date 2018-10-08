package com.uludag.can.jazzup.ui.jazzplaylists;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.uludag.can.jazzup.models.PlaylistCellItem;
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistItem;
import com.uludag.can.jazzup.models.playlistswithcategory.PlaylistsResponse;
import com.uludag.can.jazzup.models.playlistswithtracks.PlaylistWithTracks;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class JazzPlaylistsPresenter implements JazzPlaylistsContract.Presenter {

    private static final String TAG = JazzPlaylistsPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private List<PlaylistPresenter> playlistPresenters = new ArrayList<>();
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
        ConnectableObservable<PlaylistsResponse> playlistsObservable = this.model
                .getJazzPlaylists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .replay();

        addToDisposableBag(createPlaylistResponseDisposable(playlistsObservable));
        addToDisposableBag(createPlaylistItemDisposable(playlistsObservable));

        playlistsObservable.connect();

    }

    @NonNull
    private Disposable createPlaylistResponseDisposable(ConnectableObservable<PlaylistsResponse> playlistsObservable) {
        return playlistsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PlaylistsResponse>() {

                    @Override
                    public void onNext(PlaylistsResponse playlistsResponse) {
                        view.populateAdapter(getPlaylistPresenters(playlistsResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e != null) {
                            preferences.edit().putString("access_token", "").apply();
                            view.showGetAccessScreen();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @NonNull
    private Disposable createPlaylistItemDisposable(ConnectableObservable<PlaylistsResponse> playlistsObservable) {
        return playlistsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<PlaylistsResponse, Observable<PlaylistPresenter>>() {
                    @Override
                    public Observable<PlaylistPresenter> apply(PlaylistsResponse playlistsResponse) throws Exception {
                        return Observable.fromIterable(playlistPresenters);
                    }
                }).flatMap(new Function<PlaylistPresenter, Observable<PlaylistPresenter>>() {
                    @Override
                    public Observable<PlaylistPresenter> apply(PlaylistPresenter playlistPresenter) throws Exception {
                        return getPlaylistWithTrackObservable(playlistPresenter);
                    }
                }).subscribeWith(new DisposableObserver<PlaylistPresenter>() {
                    @Override
                    public void onNext(PlaylistPresenter playlistPresenter) {
                        int position = playlistPresenters.indexOf(playlistPresenter);
                        if (position == -1)
                            return;
                        view.updateAdapterData(position, playlistPresenter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<PlaylistPresenter> getPlaylistWithTrackObservable(PlaylistPresenter playlistPresenter) {
        return this.model.getPlaylistWithTracks(playlistPresenter.getPlaylistCellItem().getId())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<PlaylistWithTracks, PlaylistPresenter>() {
                    @Override
                    public PlaylistPresenter apply(PlaylistWithTracks playlistWithTracks) throws Exception {
                        return createPlaylistPresenter(playlistWithTracks, playlistPresenter);
                    }
                });
    }

    @NonNull
    private PlaylistPresenter createPlaylistPresenter(PlaylistWithTracks playlistWithTracks, PlaylistPresenter playlistPresenter) {
        playlistPresenter.getPlaylistCellItem().setDescription(playlistWithTracks.getDescription());
        return playlistPresenter;
    }

    @NonNull
    private List<PlaylistPresenter> getPlaylistPresenters(PlaylistsResponse playlistsResponse) {
        for (PlaylistItem item : playlistsResponse.getPlaylists().getPlaylistItems()) {
            PlaylistCellItem playlistCellItem = new PlaylistCellItem();
            playlistCellItem.setId(item.getId());
            playlistCellItem.setTitle(item.getName());
            playlistCellItem.setCreator(item.getOwner().getDisplayName());
            playlistCellItem.setTrackCount(item.getTracks().getTotal());
            playlistCellItem.setDescription("");
            playlistCellItem.setImageUrl(item.getImages().get(0).getUrl());
            this.playlistPresenters.add(new PlaylistPresenter(playlistCellItem));
        }
        return this.playlistPresenters;
    }

    private void addToDisposableBag(Disposable playlistResponseDisposable) {
        this.compositeDisposable.add(playlistResponseDisposable);
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
