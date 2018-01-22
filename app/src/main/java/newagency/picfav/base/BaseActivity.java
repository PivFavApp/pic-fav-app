package newagency.picfav.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import newagency.picfav.R;

/**
 * Created by oroshka on 1/22/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected abstract void onViewReady();

    @LayoutRes
    protected abstract int onRequestLayout();

    protected abstract void onInitializeInjection();

    protected void beforeLayout() {}
}
