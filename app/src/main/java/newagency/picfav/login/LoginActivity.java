package newagency.picfav.login;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import newagency.picfav.R;
import newagency.picfav.base.BaseActivity;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.PresenterI presenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        presenter.logIn();
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

//    LoginContract.View methods
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
