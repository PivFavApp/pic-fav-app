package newagency.picfav.view.welcome.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.view.welcome.WelcomeContract;

public class WelcomePresenter implements WelcomeContract.PresenterI {

    @Nullable
    private WelcomeContract.View view;

    @Inject
    public WelcomePresenter(@NonNull WelcomeContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        view = null;
    }
}
