package newagency.picfav.view.welcome.view;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.login.view.LoginActivity;
import newagency.picfav.view.sign.up.view.SignUpActivity;
import newagency.picfav.view.welcome.WelcomeContract;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    @Inject
    WelcomeContract.PresenterI presenter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, WelcomeActivity.class));
    }

    @Override
    protected void onViewReady() {

    }

    @OnClick(R.id.btn_log_in)
    void logInClick() {
        LoginActivity.start(this);
    }

    @OnClick(R.id.btn_sing_up)
    void signUpClick() {
        SignUpActivity.launch(this);
    }

    @Override
    protected void onViewDestroy() {
        presenter.onStop();
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_welcome;
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
