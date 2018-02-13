package newagency.picfav.dagger;

import dagger.Component;
import newagency.picfav.dagger.scope.ActivityScope;
import newagency.picfav.view.gamelist.view.MainActivity;
import newagency.picfav.view.login.view.LoginActivity;
import newagency.picfav.view.main.view.MainScreenActivity;
import newagency.picfav.view.result.view.ResultScreenActivity;
import newagency.picfav.view.sign.up.view.SignUpActivity;
import newagency.picfav.view.welcome.view.WelcomeActivity;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ViewModule.class,
                PresentationModule.class
        })
public interface ViewComponent {

    void inject(LoginActivity activity);

    void inject(WelcomeActivity activity);

    void inject(SignUpActivity activity);

    void inject(MainScreenActivity activity);

    void inject(MainActivity activity);

    void inject(ResultScreenActivity activity);
}

