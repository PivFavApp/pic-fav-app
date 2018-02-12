package newagency.picfav.dagger;


import dagger.Module;
import dagger.Provides;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.game.GameContract;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.main.MainContract;
import newagency.picfav.view.main.view.MainActivity;
import newagency.picfav.view.sign.up.SignUpContract;
import newagency.picfav.view.welcome.WelcomeContract;

@Module
public class ViewModule {
    private AbsView view;

    public ViewModule(AbsView view) {
        this.view = view;
    }

    public ViewModule() {
    }

    @Provides
    LoginContract.View provideLoginView() {
        return (LoginContract.View) view;
    }

    @Provides
    WelcomeContract.View providesWelcomeView() {
        return (WelcomeContract.View) view;
    }

    @Provides
    SignUpContract.View providesSignUpView() {
        return (SignUpContract.View) view;
    }

    @Provides
    GameContract.View provideGameView() {
        return (GameContract.View) view;
    }

    @Provides
    MainContract.View provideMainView() {
        return (MainContract.View) view;
    }

}