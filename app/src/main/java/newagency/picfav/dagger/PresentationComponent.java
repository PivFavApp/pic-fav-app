package newagency.picfav.dagger;

import dagger.Component;
import newagency.picfav.dagger.scope.ActivityScope;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                PresentationModule.class
        })
public interface PresentationComponent {
}
