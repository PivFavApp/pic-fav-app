package newagency.picfav.view.main.data;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.netwotk.response.ApiError;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.util.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllGamesRepositoryImpl implements GetAllGamesRepository {

    @NonNull
    private ApiService mApiService;

    @NonNull
    @ApplicationContext
    private Context mContext;

    public GetAllGamesRepositoryImpl(@NonNull @ApplicationContext Context context,
                                     @NonNull ApiService apiService) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    @Override
    public void getAllGames(@NonNull GetAllGamesCallback callback) {
        mApiService.getAllGames().enqueue(new Callback<List<GameResponse>>() {
            @Override
            public void onResponse(Call<List<GameResponse>> call, Response<List<GameResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());

                } else {
                    ApiError apiError = ErrorUtils.parseError(mContext, response.errorBody());
                    callback.onError(apiError.errorDescription);
                }
            }

            @Override
            public void onFailure(Call<List<GameResponse>> call, Throwable t) {
                String message = ErrorUtils.parseError(mContext, t);
                callback.onError(message);
            }
        });
    }
}
