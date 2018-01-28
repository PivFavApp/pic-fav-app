package newagency.picfav.view.sign.up;

import android.content.Context;
import android.support.annotation.NonNull;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.request.SignUpRequestBody;
import newagency.picfav.netwotk.response.ApiError;
import newagency.picfav.util.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oroshka on 1/25/18.
 */

public class SignUpRepositoryImpl implements ISignUpRepository {

    @NonNull
    ApiService mApiService;

    @NonNull
    @ApplicationContext Context mContext;

    public SignUpRepositoryImpl(@NonNull @ApplicationContext Context context,
                                @NonNull ApiService apiService) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    @Override
    public void singUp(SignUpRequestBody body, @NonNull final SignUpRepositoryImpl.SignUpCallback callback) {
        mApiService.signUp(body).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess();

                } else {
                    ApiError apiError = ErrorUtils.parseError(mContext, response.errorBody());
                    callback.onError(apiError.errorDescription);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                String message = ErrorUtils.parseError(mContext, t);
                callback.onError(message);
            }
        });
    }
}
