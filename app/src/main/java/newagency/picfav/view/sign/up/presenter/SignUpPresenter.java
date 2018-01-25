package newagency.picfav.view.sign.up.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.netwotk.request.SignUpRequestBody;
import newagency.picfav.view.sign.up.ISignUpRepository;
import newagency.picfav.view.sign.up.SignUpContract;

public class SignUpPresenter implements SignUpContract.PresenterI {

    @Nullable
    private SignUpContract.View mView;

    @NonNull
    private ISignUpRepository mISignUpRepository;

    private ISignUpRepository.SignUpCallback mSignUpCallback = new ISignUpRepository.SignUpCallback() {
        @Override
        public void onSuccess() {
            if (mView != null) {
                mView.hideProgress();
            }
        }

        @Override
        public void onError() {
            if (mView != null) {
                mView.hideProgress();
            }
        }
    };

    @Inject
    public SignUpPresenter(@Nullable SignUpContract.View view,
                           @NonNull ISignUpRepository signUpRepository) {
        this.mView = view;
        this.mISignUpRepository = signUpRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mView = null;
    }

    //    SignUpContract.PresenterI  methods
    @Override
    public void signUp(String firstName, String lastName, String username, String password, String dateOfBirth) {
        if (mView == null) return;
        mView.showProgress();
        SignUpRequestBody signUpRequestBody = new SignUpRequestBody();
        signUpRequestBody.firstName = firstName;
        signUpRequestBody.lastName = lastName;
        signUpRequestBody.username = username;
        signUpRequestBody.password = password;

        mISignUpRepository.singUp(signUpRequestBody, mSignUpCallback);
    }
}
