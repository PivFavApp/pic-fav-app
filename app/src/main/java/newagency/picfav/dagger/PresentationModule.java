package newagency.picfav.dagger;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import newagency.picfav.login.LoginContract;
import newagency.picfav.login.LoginPresenter;

/**
 * Created by oroshka on 1/22/18.
 */

@Module
public class PresentationModule {
    @Provides
    LoginContract.PresenterI provideLoginPresenter(@NonNull LoginPresenter presenter) {
        return presenter;
    }

}
