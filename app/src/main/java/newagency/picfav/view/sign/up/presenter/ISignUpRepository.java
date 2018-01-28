package newagency.picfav.view.sign.up.presenter;

import android.support.annotation.NonNull;

import newagency.picfav.netwotk.request.SignUpRequestBody;

/**
 * Created by oroshka on 1/25/18.
 */

public interface ISignUpRepository {

    void singUp(SignUpRequestBody body, @NonNull SignUpCallback callback);


    interface SignUpCallback {
        void onSuccess();

        void onError(String error);
    }

}
