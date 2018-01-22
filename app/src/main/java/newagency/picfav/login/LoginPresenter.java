package newagency.picfav.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by oroshka on 1/22/18.
 */

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

    }

    @Override
    public void logIn() {
        Log.e(TAG, "logIn: ");
    }
}
