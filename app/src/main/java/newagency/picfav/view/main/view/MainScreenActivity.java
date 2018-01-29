package newagency.picfav.view.main.view;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class MainScreenActivity extends BaseActivity implements MainScreenContract.View {

    @Inject
    MainScreenContract.PresenterI mPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainScreenActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        if (!mPresenter.isLogin())
            navigateToWelcome();
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_main_screen;
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.next_tv)
    void onNextClick() {

    }

    @OnClick(R.id.small_grid_iv)
    void onGridClick() {

    }

    @OnClick(R.id.btn_log_out)
    void onLogOutClick() {
        mPresenter.logout();
    }

    @Override
    public void navigateToWelcome() {
        finish();
        WelcomeActivity.start(this);
    }
}
