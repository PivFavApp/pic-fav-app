package newagency.picfav.view.main.view;

import android.content.Context;
import android.content.Intent;

import com.gtomato.android.ui.transformer.CoverFlowViewTransformer;
import com.gtomato.android.ui.widget.CarouselView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.main.presenter.ImageRecyclerAdapter;
import newagency.picfav.view.main.presenter.model.ImageItemModel;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class MainScreenActivity extends BaseActivity implements MainScreenContract.View {

    @Inject
    MainScreenContract.PresenterI mPresenter;

    @BindView(R.id.carousel)
    CarouselView mCarouselView;

    private ImageRecyclerAdapter mImageRecyclerAdapter;

    private ImageRecyclerAdapter.ImageRecyclerAdapterCallback mAdapterCallback = new ImageRecyclerAdapter.ImageRecyclerAdapterCallback() {
        @Override
        public void onChangeCountSelected(int count) {

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

    private void initAdapter() {
        mImageRecyclerAdapter = new ImageRecyclerAdapter(this, mAdapterCallback);
        CoverFlowViewTransformer transformer = new CoverFlowViewTransformer();
        transformer.setYProjection(0f);
        transformer.setScaleYFactor(-0.15f);
        mCarouselView.setTransformer(transformer);
        mCarouselView.setAdapter(mImageRecyclerAdapter);
        mCarouselView.setInfinite(false);
        mImageRecyclerAdapter.addAll(generateImage());
    }

    private ArrayList<ImageItemModel> generateImage() {
        ArrayList<ImageItemModel> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ImageItemModel imageItemModel = new ImageItemModel();
            imageItemModel.setUrl("https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/68dd54ca-60cf-4ef7-898b-26d7cbe48ec7/10-dithering-opt.jpg");
            arrayList.add(imageItemModel);
        }
        return arrayList;
    }
}
