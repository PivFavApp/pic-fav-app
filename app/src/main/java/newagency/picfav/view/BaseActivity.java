package newagency.picfav.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import newagency.picfav.PicFavApplication;
import newagency.picfav.R;
import newagency.picfav.dagger.ApplicationComponent;

/**
 * Created by oroshka on 1/22/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();
        setContentView(onRequestLayout());
        initLoading();
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

    protected void initLoading() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.general_waiting_please_msg));
        mProgressDialog.setCancelable(false);
    }

    protected void initializeViewsInjection() {
        ButterKnife.bind(this);
        onViewReady();
    }

    protected ApplicationComponent getApplicationComponent() {
        return PicFavApplication.mApplicationComponent;
    }
}
