package newagency.picfav.view.login.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import newagency.picfav.view.login.LoginContract;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginContract.PresenterI {

    @Nullable
    private LoginContract.View mView;

    @Inject
    public LoginPresenter(@NonNull LoginContract.View view) {
        this.mView = view;
    }

    //    LoginContract.PresenterI methods
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mView = null;
    }

    @Override
    public void logIn() {
        Log.e(TAG, "logIn: ");
    }
}
