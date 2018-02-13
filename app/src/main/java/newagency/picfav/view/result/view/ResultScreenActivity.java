package newagency.picfav.view.result.view;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.custom.GameLoadingView;
import newagency.picfav.view.result.ResultScreenContract;

public class ResultScreenActivity extends BaseActivity implements ResultScreenContract.View {

    @BindView(R.id.result_tv)
    TextView mResultTv;

    @BindView(R.id.toolbar_title_tv)
    TextView mToolbarTitle;

    @BindView(R.id.loading_game)
    GameLoadingView mGameLoadingView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ResultScreenActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        mToolbarTitle.setText(R.string.result_activity_title);
        mGameLoadingView.setMaxMembers(100);
        mGameLoadingView.setCountResponded(50);
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_result_screen;
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
    void onBack() {
        onBackPressed();
    }
}
