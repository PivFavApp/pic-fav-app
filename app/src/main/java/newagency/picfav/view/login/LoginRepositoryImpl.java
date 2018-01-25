package newagency.picfav.view.login;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.request.LoginRequestBody;
import newagency.picfav.netwotk.response.LoginResponse;
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
    public void login(final LoginRequestBody requestBody, @NonNull final LoginRepositoryImpl.LoginCallback callback) throws NullPointerException{
        mApiService.login(requestBody.username, requestBody.password, requestBody.grantType).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && loginResponse != null) {
                        callback.onSuccess(loginResponse.accessToken);

                } else {
                    callback.onError("");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("");
            }
        });
    }
}
