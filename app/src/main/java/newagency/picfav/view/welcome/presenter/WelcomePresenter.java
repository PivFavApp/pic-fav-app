package newagency.picfav.view.welcome.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.view.welcome.WelcomeContract;

public class WelcomePresenter implements WelcomeContract.PresenterI {

    @Nullable
    private WelcomeContract.View view;

    @NonNull
    private SharedPrefManager sharedPrefManager;

    @Inject
    public WelcomePresenter(@NonNull WelcomeContract.View view,
                            @NonNull SharedPrefManager sharedPrefManager) {
        this.view = view;
        this.sharedPrefManager = sharedPrefManager;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        view = null;
    }
}
