package newagency.picfav.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.response.ApiError;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.util.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenRepositoryImpl implements IMainScreenRepository {

    @NonNull
    private ApiService mApiService;

    @NonNull
    @ApplicationContext
    private Context mContext;

    public MainScreenRepositoryImpl(@NonNull @ApplicationContext Context context,
                                    @NonNull ApiService apiService) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    @Override
    public void getGame(String idGame, @NonNull GameCallback callback) {
        mApiService.getGame(idGame).enqueue(new Callback<GameResponse>() {
            @Override
            public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());

                } else {
                    ApiError apiError = ErrorUtils.parseError(mContext, response.errorBody());
                    callback.onError(apiError.errorDescription);
                }
            }

            @Override
            public void onFailure(Call<GameResponse> call, Throwable t) {
                String message = ErrorUtils.parseError(mContext, t);
                callback.onError(message);
            }
        });
    }
}
