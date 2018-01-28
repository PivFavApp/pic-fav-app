package newagency.picfav.view.main.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.view.main.MainScreenContract;

/**
 * Created by oroshka on 1/28/18.
 */

public class MainScreenPresenter implements MainScreenContract.PresenterI {

    @Nullable
    private MainScreenContract.View mView;

    @NonNull
    private SharedPrefManager mSharedPrefManager;

    @Inject
    public MainScreenPresenter(@Nullable MainScreenContract.View view,
                               @NonNull SharedPrefManager sharedPrefManager) {
        this.mSharedPrefManager = sharedPrefManager;
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

//    MainScreenContract.PresenterI  methods
    @Override
    public boolean isLogin() {
        return mSharedPrefManager.isLoggedIn();
    }

    @Override
    public void logout() {
        mSharedPrefManager.setAuthToken(null);
        mSharedPrefManager.setLoggedIn(false);
        if (mView != null) {
            mView.navigateToLogIn();
        }
    }
}
