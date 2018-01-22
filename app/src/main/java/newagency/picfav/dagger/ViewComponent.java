package newagency.picfav.dagger;

import dagger.Component;
import newagency.picfav.dagger.scope.ActivityScope;
import newagency.picfav.login.LoginActivity;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ViewModule.class,
                PresentationModule.class
        })
public interface ViewComponent {

    void inject(LoginActivity activity);

}

