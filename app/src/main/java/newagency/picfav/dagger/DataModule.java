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
import newagency.picfav.view.gamelist.data.IGetAllGamesRepository;
import newagency.picfav.view.gamelist.data.IGetAllGamesRepositoryImpl;
import newagency.picfav.view.login.presenter.ILoginRepository;
import newagency.picfav.view.login.presenter.LoginRepositoryImpl;
import newagency.picfav.view.main.presenter.GameManager;
import newagency.picfav.view.main.presenter.IGameManager;
import newagency.picfav.view.main.presenter.IMainScreenRepository;
import newagency.picfav.view.main.presenter.MainScreenRepositoryImpl;
import newagency.picfav.view.result.data.IResultRepository;
import newagency.picfav.view.result.data.ResultRepositoryImpl;
import newagency.picfav.view.sign.up.presenter.ISignUpRepository;
import newagency.picfav.view.sign.up.presenter.SignUpRepositoryImpl;

@Module
public class DataModule {

    @Provides
    @Singleton
    SharedPrefManager provideSharedPrefManager(@ApplicationContext Context context) {
        return new SharedPrefManager(context);
    }

    @Provides
    @Singleton
    ApiService providesDataApiService(SharedPrefManager sharedPrefManager) {
        return ApiClient.getDataApiService(sharedPrefManager);
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

    @Provides
    @Singleton
    IMainScreenRepository provideGameRepository(@NonNull @ApplicationContext Context context,
                                                @NonNull ApiService apiService) {
        return new MainScreenRepositoryImpl(context, apiService);
    }

    @Provides
    @Singleton
    IGetAllGamesRepository provideAllGamesRepository(@NonNull @ApplicationContext Context context,
                                                     @NonNull ApiService apiService) {
        return new IGetAllGamesRepositoryImpl(context, apiService);
    }

    @Provides
    @Singleton
    IResultRepository provideResultRepository(@NonNull @ApplicationContext Context context,
                                              @NonNull ApiService apiService) {
        return new ResultRepositoryImpl(context, apiService);
    }


    @Provides
    @Singleton
    IGameManager provideGameManager(@NonNull @ApplicationContext Context context) {
        return new GameManager(context);
    }

}
