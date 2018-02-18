package newagency.picfav.dagger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;
import newagency.picfav.view.gamelist.presenter.AllGamePresenter;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.main.presenter.MainScreenPresenter;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.login.presenter.LoginPresenter;
import newagency.picfav.view.gamelist.MainContract;
import newagency.picfav.view.result.ResultScreenContract;
import newagency.picfav.view.result.presenter.ResultScreenPresenter;
import newagency.picfav.view.sign.up.SignUpContract;
import newagency.picfav.view.sign.up.presenter.SignUpPresenter;
import newagency.picfav.view.welcome.WelcomeContract;
import newagency.picfav.view.welcome.presenter.WelcomePresenter;

@Module
public class PresentationModule {

    @Provides
    LoginContract.PresenterI provideLoginPresenter(@NonNull LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    WelcomeContract.PresenterI providesWelcomePresenter(@Nullable WelcomePresenter presenter) {
        return presenter;
    }

    @Provides
    SignUpContract.PresenterI providesSignUpPresenter(@Nullable SignUpPresenter presenter) {
        return presenter;
    }

    @Provides
    MainScreenContract.PresenterI provideGamePresenter(@Nullable MainScreenPresenter presenter) {
        return presenter;
    }

    @Provides
    MainContract.Presenter provideMainPresenter(@Nullable AllGamePresenter presenter) {
        return presenter;
    }

    @Provides
    ResultScreenContract.PresenterI provideResultPresenter(@Nullable ResultScreenPresenter presenter) {
        return presenter;
    }

}
