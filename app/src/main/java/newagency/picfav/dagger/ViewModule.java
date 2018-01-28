package newagency.picfav.dagger;


import dagger.Module;
import dagger.Provides;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.main.MainScreenContract;
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
    MainScreenContract.View provideMainScreenView() {
        return (MainScreenContract.View) view;
    }

}