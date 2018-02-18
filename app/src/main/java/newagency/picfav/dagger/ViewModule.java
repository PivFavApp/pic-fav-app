package newagency.picfav.dagger;


import dagger.Module;
import dagger.Provides;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.mainFeed.gamelist.AllGamesContract;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.mainFeed.tabsScreen.TabsContact;
import newagency.picfav.view.result.ResultScreenContract;
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
    MainScreenContract.View provideGameView() {
        return (MainScreenContract.View) view;
    }

    @Provides
    AllGamesContract.View provideMainView() {
        return (AllGamesContract.View) view;
    }

    @Provides
    ResultScreenContract.View proviceResultView() {
        return (ResultScreenContract.View) view;
    }

    @Provides
    TabsContact.View provideTabsView() {
        return (TabsContact.View) view;
    }

}