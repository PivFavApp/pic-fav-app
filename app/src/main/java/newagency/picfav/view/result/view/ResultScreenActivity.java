package newagency.picfav.view.result.view;

import android.content.Context;
import android.content.Intent;
import android.view.ViewTreeObserver;
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

    private final static String RESULT_KEY = "result_key";

    @BindView(R.id.result_tv)
    TextView mResultTv;

    @BindView(R.id.toolbar_title_tv)
    TextView mToolbarTitle;

    @BindView(R.id.loading_game)
    GameLoadingView mGameLoadingView;

    private int mResult;

    public static void start(Context context, int score) {
        Intent starter = new Intent(context, ResultScreenActivity.class);
        starter.putExtra(RESULT_KEY, score);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        getArgsData();
        mToolbarTitle.setText(R.string.result_activity_title);
        mGameLoadingView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGameLoadingView.setResult(mResult);
                mGameLoadingView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
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

    private void getArgsData() {
        if (getIntent() != null)
        mResult = getIntent().getIntExtra(RESULT_KEY, 0);
    }
}
