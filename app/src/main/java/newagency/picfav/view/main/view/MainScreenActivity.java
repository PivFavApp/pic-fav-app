package newagency.picfav.view.main.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
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
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.main.presenter.ImageRecyclerAdapter;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class MainScreenActivity extends BaseActivity implements MainScreenContract.View {

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

    public static void start(Context context) {
        Intent starter = new Intent(context, MainScreenActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        if (!mPresenter.isLogin())
            navigateToWelcome();
        mPresenter.loadGame("8ce2ca1c-81d1-4396-a0e0-b09af2137438");
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

    @OnClick(R.id.next_tv)
    void onNextClick() {
        mPresenter.goToNextStep(mImageRecyclerAdapter.getData());
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
}
