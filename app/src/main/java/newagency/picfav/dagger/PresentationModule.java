package newagency.picfav.dagger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;
import newagency.picfav.view.game.GameContract;
import newagency.picfav.view.game.presenter.GamePresenter;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.login.presenter.LoginPresenter;
import newagency.picfav.view.main.MainContract;
import newagency.picfav.view.main.presenter.MainPresenter;
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
    GameContract.PresenterI provideGamePresenter(@Nullable GamePresenter presenter) {
        return presenter;
    }

    @Provides
    MainContract.Presenter provideMainPresenter(@Nullable MainPresenter presenter) {
        return presenter;
    }

}
