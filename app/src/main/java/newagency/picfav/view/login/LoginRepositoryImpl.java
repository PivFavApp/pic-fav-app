package newagency.picfav.view.login;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.request.LoginRequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oroshka on 1/25/18.
 */

public class LoginRepositoryImpl implements ILoginRepository {

    @NonNull
    ApiService mApiService;

    @Inject
    public LoginRepositoryImpl(@NonNull ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void login(final LoginRequestBody requestBody, @NonNull final LoginRepositoryImpl.LoginCallback callback) {
        mApiService.login(requestBody).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess("");

                } else {
                    callback.onError("");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onError("");
            }
        });
    }
}
