package newagency.picfav.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.ApiClient;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.view.login.ILoginRepository;
import newagency.picfav.view.login.LoginRepositoryImpl;
import newagency.picfav.view.sign.up.ISignUpRepository;
import newagency.picfav.view.sign.up.SignUpRepositoryImpl;

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

    @Provides
    @Singleton
    ILoginRepository provideLoginRepository(@NonNull @ApplicationContext Context context,
                                            @NonNull ApiService apiService) {
        return new LoginRepositoryImpl(context, apiService);
    }

    @Provides
    @Singleton
    ISignUpRepository provideSignUpRepository(@NonNull @ApplicationContext Context context,
                                             @NonNull ApiService apiService) {
        return new SignUpRepositoryImpl(context, apiService);
    }
}
