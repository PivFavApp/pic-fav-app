package newagency.picfav.view.login.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.login.LoginContract;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.PresenterI presenter;

    @BindView(R.id.login_input_layout)
    TextInputLayout loginInputLayout;

    @BindView(R.id.password_input_layout)
    TextInputLayout passwordInputLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        presenter.onStart();
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

    private String getUserName() {
        return loginInputLayout.getEditText().getText().toString();
    }

    private String getPassword() {
        return passwordInputLayout.getEditText().getText().toString();
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.login_btn)
    void onLogInClick() {
        presenter.logIn(getUserName(), getPassword());
    }

//    LoginContract.View methods
    @Override
    public void showProgress() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
