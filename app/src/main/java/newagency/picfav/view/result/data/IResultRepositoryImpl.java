package newagency.picfav.view.result.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oroshka on 2/17/18.
 */

public class IResultRepositoryImpl implements IResultRepository {

    @NonNull
    private ApiService mApiService;

    @NonNull
    @ApplicationContext
    private Context mContext;

    public IResultRepositoryImpl(@NonNull @ApplicationContext Context context,
                                 @NonNull ApiService apiService) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    @Override
    public void postGameResult() {
        mApiService.postFinishGame().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e("s", "onResponse: ");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("s", "onFailure: ");
            }
        });
    }
}
