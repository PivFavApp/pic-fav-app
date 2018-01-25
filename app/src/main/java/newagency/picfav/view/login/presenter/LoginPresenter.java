package newagency.picfav.view.login.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.netwotk.request.LoginRequestBody;
import newagency.picfav.view.login.ILoginRepository;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.login.LoginRepositoryImpl;

public class LoginPresenter implements LoginContract.PresenterI {

    @Nullable
    private LoginContract.View mView;

    @NonNull
    LoginRepositoryImpl mLoginRepository;

    private LoginRepositoryImpl.LoginCallback mLoginCallback = new ILoginRepository.LoginCallback() {
        @Override
        public void onSuccess(String token) {
            if (mView != null) {
                mView.hideProgress();
            }
        }

        @Override
        public void onError(String error) {
            if (mView != null) {
                mView.hideProgress();
            }
        }
    };

    @Inject
    public LoginPresenter(@NonNull LoginContract.View view,
                          @NonNull LoginRepositoryImpl loginRepository) {
        this.mView = view;
        this.mLoginRepository = loginRepository;
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
    public void logIn(String userName, String password) {
        if (mView == null) return;
        mView.showProgress();
        mLoginRepository.login(new LoginRequestBody(userName, password), mLoginCallback);
    }
}
