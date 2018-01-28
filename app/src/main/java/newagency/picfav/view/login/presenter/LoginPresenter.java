package newagency.picfav.view.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.R;
import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.request.LoginRequestBody;
import newagency.picfav.util.AppConstants;
import newagency.picfav.view.login.LoginContract;

public class LoginPresenter implements LoginContract.PresenterI {

    @Nullable
    private LoginContract.View mView;

    @NonNull
    @ApplicationContext Context mContext;

    @NonNull
    LoginRepositoryImpl mLoginRepository;

    @NonNull
    SharedPrefManager mSharedPrefManager;

    private LoginRepositoryImpl.LoginCallback mLoginCallback = new ILoginRepository.LoginCallback() {
        @Override
        public void onSuccess(String token) {
            mSharedPrefManager.setAuthToken(token);
            mSharedPrefManager.setLoggedIn(true);
            if (mView != null) {
                mView.navigateToMainScreen();
                mView.hideProgress();
            }
        }

        @Override
        public void onError(String error) {
            if (mView != null) {
                mView.showMessage(error);
                mView.hideProgress();
            }
        }
    };

    @Inject
    public LoginPresenter(@NonNull @ApplicationContext Context context,
                          @NonNull LoginContract.View view,
                          @NonNull LoginRepositoryImpl loginRepository,
                          @NonNull SharedPrefManager sharedPrefManager) {
        this.mView = view;
        this.mLoginRepository = loginRepository;
        this.mSharedPrefManager = sharedPrefManager;
        this.mContext = context;
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
        if (isValidData(userName, password)) {
            mView.showProgress();
            mLoginRepository.login(new LoginRequestBody(userName, password, AppConstants.GrantType.PASSWORD), mLoginCallback);
        }
    }

    private boolean isValidData(String userName, String password) {
        if (mView == null) return false;
        mView.clearAllError();
        boolean isValid = true;

        if (userName.isEmpty()) {
            mView.showUserNameError(mContext.getString(R.string.login_error_user_name_empty));
            isValid = false;

        } else if (password.isEmpty()) {
            mView.showPasswordError(mContext.getString(R.string.login_error_password_empty));
            isValid = false;
        }
        return isValid;
    }
}
