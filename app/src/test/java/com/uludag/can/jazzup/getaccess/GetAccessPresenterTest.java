package com.uludag.can.jazzup.getaccess;

import android.content.SharedPreferences;

import com.uludag.can.jazzup.models.playlistswithcategory.AccessToken;
import com.uludag.can.jazzup.ui.getaccess.GetAccessContract;
import com.uludag.can.jazzup.ui.getaccess.GetAccessPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAccessPresenterTest {

    private GetAccessContract.View mockView;
    private GetAccessContract.Model mockModel;
    private SharedPreferences mockPrefs;
    private GetAccessPresenter presenter;

    @Before
    public void setUp() {
        this.mockView = mock(GetAccessContract.View.class);
        this.mockModel = mock(GetAccessContract.Model.class);
        this.mockPrefs = mock(SharedPreferences.class);
        setupPresenter();
    }

    private void setupPresenter() {
        this.presenter = new GetAccessPresenter(this.mockModel, this.mockPrefs);
        this.presenter.view = this.mockView;
    }

    @After
    public void tearDown() {
        this.mockView = null;
        this.mockModel = null;
        this.mockPrefs = null;
    }

    private void mockGetAccessTokenRequest() {
        AccessToken mockAccesToken = mock(AccessToken.class);
        when(mockAccesToken.getAccessToken()).thenReturn("mock_access_token");
        when(this.mockModel.getAccessToken()).thenReturn(Single.just(mockAccesToken));
    }

    @Test
    public void loadData_checkAccessToken_hasAccessToken__showPlaylistsScreen() {
        when(this.mockPrefs.getString(anyString(), anyString())).thenReturn("mock_access_token");
        this.presenter.loadData();
        verify(this.mockView).showLoading();
        verify(this.mockView).showPlaylistsScreen();
        verify(this.mockView).hideLoading();
    }

    @Test
    public void loadData_checkAccessToken_accessTokenEmpty__hideLoading() {
        when(this.mockPrefs.getString(anyString(), anyString())).thenReturn("");
        this.presenter.loadData();
        verify(this.mockView).showLoading();
        verify(this.mockView, never()).showPlaylistsScreen();
        verify(this.mockView).hideLoading();
    }

    @Test
    public void onGetAccessClicked_getNewAccessToken_onSuccess__showPlaylistsScreen() {
        mockGetAccessTokenRequest();
        this.presenter.onCreate();
        this.presenter.onGetAccessClicked();
        verify(this.mockView).showLoading();
        verify(this.mockView).showPlaylistsScreen();
        verify(this.mockView).hideLoading();
    }

}
