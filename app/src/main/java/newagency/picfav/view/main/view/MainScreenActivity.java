package newagency.picfav.view.main.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gtomato.android.ui.transformer.CoverFlowViewTransformer;
import com.gtomato.android.ui.widget.CarouselView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.util.AppConstants;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.main.presenter.ImageRecyclerAdapter;
import newagency.picfav.view.main.presenter.model.GameResult;
import newagency.picfav.view.result.view.ResultScreenActivity;

public class MainScreenActivity extends BaseActivity implements MainScreenContract.View {

    private static String GAME_ID_KEY = "game_id";

    public static void start(@NonNull Context context, @NonNull String gameId) {
        Intent starter = new Intent(context, MainScreenActivity.class);
        starter.putExtra(GAME_ID_KEY, gameId);
        context.startActivity(starter);
    }

    @Inject
    MainScreenContract.PresenterI mPresenter;

    @BindView(R.id.carousel)
    CarouselView mCarouselView;

    @BindView(R.id.set_name_tv)
    TextView setNameTv;

    @BindView(R.id.toolbar_sub_title_tv)
    TextView subTitleTv;

    @BindView(R.id.next_tv)
    TextView nextTv;

    @BindView(R.id.remain_tv)
    TextView remainTv;

    @BindView(R.id.choose_label)
    TextView chooseTv;

    @BindView(R.id.grid_iv)
    ImageView gridIv;

    private ImageRecyclerAdapter mImageRecyclerAdapter;

    private ImageRecyclerAdapter.ImageRecyclerAdapterCallback mAdapterCallback = new ImageRecyclerAdapter.ImageRecyclerAdapterCallback() {
        @Override
        public void onChangeCountSelected(int count) {
            enableDisableButton(count);
        }

        @Override
        public void selectedMaxCount(int maxCount) {
            showMessage(getString(R.string.main_selected_max_image_msg, maxCount));
        }

        @Override
        public void changeRemainState(int needCount) {
            remainTv.setText(getString(R.string.main_remain_count_msg, needCount));
        }

        @Override
        public void hideRemainCount() {
            remainTv.setVisibility(View.GONE);
        }

        @Override
        public void showRemainCount() {
            remainTv.setVisibility(View.VISIBLE);
        }
    };

    private String gameId = "";

    @Override
    protected void onViewReady() {
        getArgs();
        mPresenter.loadGame(gameId);
//        mPresenter.loadGame("494499d7-4826-46db-8315-a5bc90a67bbd");
        initAdapter();
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

    @OnClick(R.id.iv_back)
    void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.next_tv)
    void onNextClick() {
        mPresenter.goToNextStep(mImageRecyclerAdapter.getData());
    }

    @OnClick(R.id.grid_iv)
    void onGridClick() {
        mPresenter.changeGridState();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAdapterPhoto(List<ImageModel> packImage, int countNeedPreliminary) {
        mImageRecyclerAdapter.addAll(packImage, countNeedPreliminary);
    }

    @Override
    public void showPreliminaryCount() {
        chooseTv.setVisibility(View.GONE);
        remainTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNeededCountSimple() {
        chooseTv.setVisibility(View.VISIBLE);
        remainTv.setVisibility(View.GONE);
    }

    @Override
    public void changedSizeGrid(AppConstants.GridState gridState) {
        if (mImageRecyclerAdapter != null) {
            mImageRecyclerAdapter.changeGridSize(gridState);
            switch (gridState) {
                case SMALL:
                    gridIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_big_grid));
                    break;

                case BIG:
                    gridIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_small_grid));
                    break;
            }
        }
    }

    @Override
    public void navigateToResult(GameResult gameResult) {
        finish();
        ResultScreenActivity.start(this, gameResult.score);
    }

    @Override
    public void updateToolbar(String name, String setRoundName) {
        setNameTv.setText(setRoundName);
        subTitleTv.setText(name);
    }

    private void enableDisableButton(int countSelected) {
        if (countSelected >= ImageRecyclerAdapter.getMinSelectedCount()) {
            nextTv.setTextAppearance(this, R.style.Roboto_Normal_Black_16);

        } else {
            nextTv.setTextAppearance(this, R.style.Roboto_Normal_Grey_16);
        }
    }

    private void initAdapter() {
        mImageRecyclerAdapter = new ImageRecyclerAdapter(this, mAdapterCallback);
        mPresenter.restoreGridState();
        CoverFlowViewTransformer transformer = new CoverFlowViewTransformer();
        transformer.setYProjection(0f);
        transformer.setScaleYFactor(-0.15f);
        mCarouselView.setTransformer(transformer);
        mCarouselView.setAdapter(mImageRecyclerAdapter);
        mCarouselView.setInfinite(false);
    }

    private ArrayList<ImageModel> generateImage() {
        ArrayList<ImageModel> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ImageModel imageItemModel = new ImageModel();
            imageItemModel.url = "https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/68dd54ca-60cf-4ef7-898b-26d7cbe48ec7/10-dithering-opt.jpg";
            arrayList.add(imageItemModel);
        }
        return arrayList;
    }

    private void getArgs() {
        gameId = getIntent().getStringExtra(GAME_ID_KEY);
    }
}
