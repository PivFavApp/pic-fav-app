package newagency.picfav.dagger;

import dagger.Component;
import newagency.picfav.dagger.scope.ActivityScope;

/**
 * Created by oroshka on 1/22/18.
 */

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                PresentationModule.class
        })
public interface PresentationComponent {
}
