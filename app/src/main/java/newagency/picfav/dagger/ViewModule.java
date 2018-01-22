package newagency.picfav.dagger;


import dagger.Module;
import dagger.Provides;
import newagency.picfav.base.AbsView;
import newagency.picfav.login.LoginContract;

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

}