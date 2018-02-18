package newagency.picfav.view.login.view;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.util.AppUtils;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.login.LoginContract;
import newagency.picfav.view.mainFeed.tabsScreen.view.TabsActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.PresenterI presenter;

    @BindView(R.id.login_input_layout)
    TextInputLayout mLoginInputLayout;

    @BindView(R.id.password_input_layout)
    TextInputLayout mPasswordInputLayout;

    @BindView(R.id.root)
    ConstraintLayout mRoot;

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
        return mLoginInputLayout.getEditText().getText().toString();
    }

    private String getPassword() {
        return mPasswordInputLayout.getEditText().getText().toString();
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        AppUtils.hideSoftInputFromWindow(getCurrentFocus());
        onBackPressed();
    }

    @OnClick(R.id.login_btn)
    void onLogInClick() {
        AppUtils.hideSoftInputFromWindow(getCurrentFocus());
        presenter.logIn(getUserName(), getPassword());
    }

    //    LoginContract.View methods
    @Override
    public void showProgressDialog() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String error) {
        Snackbar.make(mRoot, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUserNameError(String error) {
        mLoginInputLayout.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        mPasswordInputLayout.setError(error);
    }

    @Override
    public void clearAllError() {
        mLoginInputLayout.setError(null);
        mPasswordInputLayout.setError(null);
    }

    @Override
    public void navigateToMainScreen() {
      //  AllGamesFragment.launch(this);
        TabsActivity.launch(this);
        finish();
    }
}
