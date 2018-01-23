package newagency.picfav.view.login.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import newagency.picfav.netwotk.ApiService;
import newagency.picfav.view.login.LoginContract;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginContract.PresenterI {

    @Nullable
    private LoginContract.View mView;

    @Nullable
    private ApiService mApiService;

    @Inject
    public LoginPresenter(@NonNull LoginContract.View view,
                          @NonNull ApiService apiService) {
        this.mView = view;
        this.mApiService = apiService;
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
// TODO validation fields and call API
//        mApiService.login()
    }
}
