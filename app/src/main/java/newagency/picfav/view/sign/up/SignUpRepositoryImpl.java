package newagency.picfav.view.sign.up;

import android.support.annotation.NonNull;

import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.request.SignUpRequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oroshka on 1/25/18.
 */

public class SignUpRepositoryImpl implements ISignUpRepository {

    @NonNull
    ApiService mApiService;

    public SignUpRepositoryImpl(@NonNull ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void singUp(SignUpRequestBody body, @NonNull final SignUpRepositoryImpl.SignUpCallback callback) {
        mApiService.signUp(body).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess();

                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
