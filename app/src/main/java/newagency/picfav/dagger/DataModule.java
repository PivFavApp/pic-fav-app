package newagency.picfav.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.ApiClient;
import newagency.picfav.netwotk.ApiService;

/**
 * Created by oroshka on 1/22/18.
 */

@Module
public class DataModule {
    @Provides
    @Singleton
    SharedPrefManager provideSharedPrefManager(@ApplicationContext Context context) {
        return new SharedPrefManager(context);
    }

    @Provides
    @Singleton
    ApiService providesDataApiService() {
        return ApiClient.getDataApiService();
    }
}
