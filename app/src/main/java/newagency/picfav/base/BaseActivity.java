package newagency.picfav.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import newagency.picfav.PicFavApplication;
import newagency.picfav.dagger.ApplicationComponent;

/**
 * Created by oroshka on 1/22/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();
        setContentView(onRequestLayout());
        onInitializeInjection();
        initializeViewsInjection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    protected abstract void onViewReady();

    protected abstract void onViewDestroy();

    @LayoutRes
    protected abstract int onRequestLayout();

    protected abstract void onInitializeInjection();

    protected void beforeLayout() {}

    protected void initializeViewsInjection() {
        ButterKnife.bind(this);
        onViewReady();
    }

    protected ApplicationComponent getApplicationComponent() {
        return PicFavApplication.mApplicationComponent;
    }
}
