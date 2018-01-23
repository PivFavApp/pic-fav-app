package newagency.picfav.view.sign.up.presenter;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.view.sign.up.SignUpContract;

public class SignUpPresenter implements SignUpContract.PresenterI {

    @Nullable
    private SignUpContract.View view;

    @Inject
    public SignUpPresenter(@Nullable SignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        view=null;
    }
}
