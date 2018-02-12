package newagency.picfav;

import android.app.Application;

import newagency.picfav.dagger.ApplicationComponent;
import newagency.picfav.dagger.ApplicationModule;
import newagency.picfav.dagger.DaggerApplicationComponent;
import newagency.picfav.dagger.DataModule;


public class PicFavApplication extends Application {

    public static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
        mApplicationComponent.inject(this);
    }
}
