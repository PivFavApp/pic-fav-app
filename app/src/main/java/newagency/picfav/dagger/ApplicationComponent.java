package newagency.picfav.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import newagency.picfav.PicFavApplication;
import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiService;
import newagency.picfav.view.login.ILoginRepository;
import newagency.picfav.view.sign.up.ISignUpRepository;

/**
 * Created by oroshka on 1/22/18.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class
})
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    Application getApplication();

    ApiService getApiService();

    ILoginRepository getLoginRepository();

    ISignUpRepository getSignRepository();

    void inject(PicFavApplication tripiApplication);
}

