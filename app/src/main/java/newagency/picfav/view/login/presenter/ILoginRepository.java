package newagency.picfav.view.login.presenter;

import android.support.annotation.NonNull;

import newagency.picfav.netwotk.request.LoginRequestBody;

/**
 * Created by oroshka on 1/25/18.
 */

public interface ILoginRepository {

    void login(LoginRequestBody requestBody, @NonNull final LoginRepositoryImpl.LoginCallback callback);


    interface LoginCallback {

        void onSuccess(String token);

        void onError(String error);
    }
}
