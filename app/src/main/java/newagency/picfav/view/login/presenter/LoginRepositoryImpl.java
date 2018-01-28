package newagency.picfav.view.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.request.LoginRequestBody;
import newagency.picfav.netwotk.response.ApiError;
import newagency.picfav.netwotk.response.LoginResponse;
import newagency.picfav.util.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oroshka on 1/25/18.
 */

public class LoginRepositoryImpl implements ILoginRepository {

    @NonNull
    ApiService mApiService;

    @NonNull
    Context mContext;

    @Inject
    public LoginRepositoryImpl(@NonNull @ApplicationContext Context context,
            @NonNull ApiService apiService) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    @Override
    public void login(final LoginRequestBody requestBody,
                      @NonNull final LoginRepositoryImpl.LoginCallback callback) throws NullPointerException{
        mApiService.login(requestBody.username,
                requestBody.password,
                requestBody.grantType).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && loginResponse != null) {
                        callback.onSuccess(loginResponse.accessToken);

                } else {
                    ApiError apiError = ErrorUtils.parseError(mContext, response.errorBody());
                    callback.onError(apiError.errorDescription);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = ErrorUtils.parseError(mContext, t);
                callback.onError(message);
            }
        });
    }
}
