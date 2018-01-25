package newagency.picfav.view.login.view;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.login.LoginContract;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.PresenterI presenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        presenter.onStart();
        presenter.logIn();
    }

    @Override
    protected void onViewDestroy() {
        presenter.onStop();
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.login_btn)
    void signIn() {

    }

//    LoginContract.View methods
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
