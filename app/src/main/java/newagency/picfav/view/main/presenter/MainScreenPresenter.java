package newagency.picfav.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import newagency.picfav.R;
import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.util.AppConstants;
import newagency.picfav.util.GameUtil;
import newagency.picfav.view.main.MainScreenContract;
import newagency.picfav.view.main.presenter.model.GameResult;
import newagency.picfav.view.main.presenter.model.GameStateInfo;

/**
 * Created by oroshka on 1/28/18.
 */

public class MainScreenPresenter implements MainScreenContract.PresenterI, IGameManager.GameManagerCallback {

    @Nullable
    private MainScreenContract.View mView;

    @NonNull
    private SharedPrefManager mSharedPrefManager;

    @NonNull
    private IGameManager mIGameManager;

    @NonNull
    private Context mContext;

    @NonNull
    private IMainScreenRepository mIMainScreenRepository;

    private IMainScreenRepository.GameCallback mGameCallback = new IMainScreenRepository.GameCallback() {
        @Override
        public void onSuccess(GameResponse gameResponse) {
            mIGameManager.initGame(gameResponse);
        }

        @Override
        public void onError(String error) {
            //TODO
        }
    };

    @Inject
    public MainScreenPresenter(@NonNull @ApplicationContext Context context,
                               @Nullable MainScreenContract.View view,
                               @NonNull SharedPrefManager sharedPrefManager,
                               @NonNull IMainScreenRepository mainScreenRepository,
                               @NonNull IGameManager gameManager) {
        this.mContext = context;
        this.mView = view;
        this.mSharedPrefManager = sharedPrefManager;
        this.mIMainScreenRepository = mainScreenRepository;
        this.mIGameManager = gameManager;
        this.mIGameManager.setCallback(this);
    }

    //    MainScreenContract.PresenterI methods
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadGame(String idGame) {
        mIMainScreenRepository.getGame(idGame, mGameCallback);
    }

    @Override
    public void changeGridState() {
        if(mView == null) return;
        AppConstants.GridState gridState = mSharedPrefManager.getGridState();
        switch (gridState) {
            case BIG:
                mSharedPrefManager.setGridState(AppConstants.GridState.SMALL);
                break;

            case SMALL:
                mSharedPrefManager.setGridState(AppConstants.GridState.BIG);
                break;
        }
        mView.changedSizeGrid(mSharedPrefManager.getGridState());
    }

    @Override
    public void restoreGridState() {
        if (mView != null) {
            mView.changedSizeGrid(mSharedPrefManager.getGridState());
        }
    }

    @Override
    public void goToNextStep(List<ImageModel> packImage) {
        if (mView == null || packImage == null) return;
        if (GameUtil.calculateSelected(packImage) < ImageRecyclerAdapter.getMinSelectedCount()) {
            mView.showMessage(mContext.getString(R.string.main_selected_min_image_msg, ImageRecyclerAdapter.getMinSelectedCount()));

        } else {
            mIGameManager.saveSelected(packImage);
            moveToStep();
        }
    }

    //    IGameManager.GameManagerCallback methods
    @Override
    public void selectedStep(GameStateInfo gameStateInfo) {
        //            TODO need realize change IO for second round
        if (mView != null) {
            String setName = detectSetName(gameStateInfo);
            mView.updateToolbar(gameStateInfo.name, setName);
            mView.updateAdapterPhoto(gameStateInfo.imageModels, gameStateInfo.countNeedPreliminary);
            if (gameStateInfo.isPreliminary) {
                mView.showPreliminaryCount();

            } else {
                mView.showNeededCountSimple();
            }
        }
    }

    @Override
    public void finishedGame(GameResult gameResult) {
        if (mView != null) {
            mView.showMessage("game over: " + gameResult.score);
        }
    }

    private String detectSetName(GameStateInfo gameStateInfo) {
        String setName;
        if (gameStateInfo.isPreliminary) {
            setName = mContext.getString(R.string.main_set_name_preliminary);

        } else {
            setName = mContext.getString(R.string.main_set_name, gameStateInfo.step);
        }
        return setName;
    }

    private void moveToStep() {
        mIGameManager.moveToNextStep();
    }
}
