package newagency.picfav.view.sign.up.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.sign.up.SignUpContract;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    public static void launch(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SignUpActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Inject
    SignUpContract.PresenterI presenterI;

    @Override
    protected void onViewReady() {

    }

    @Override
    protected void onViewDestroy() {
        presenterI.onStop();
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }
}
