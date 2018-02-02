package newagency.picfav.view.main.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.view.main.presenter.model.GameStateInfo;

/**
 * Created by oroshka on 2/1/18.
 */

public class GameManager implements IGameManager {

    private final static int COUNT_FIRST_STEP = 4;

    private final static int COUNT_SECOND_STEP = 2;

    private final static int STEP_IN_FIRST_ROUND = 3;

    private final static int FIRST_ROUND_PRELIMINARY = 4;

    private final static int STEP_IN_SECOND_ROUND = 6;

    private final static int SECOND_ROUND_PRELIMINARY = 7;

    private final static int NEED_SELECT_FIRST_ROUND = 20;

    @Nullable
    @ApplicationContext
    private Context mContext;

    @Nullable
    private GameManagerCallback mGameManagerCallback;

    private GameResponse mGameResponse;

    private Map<Integer, List<ImageModel>> groupImageUserFirstRound;

    private Map<Integer, List<ImageModel>> groupImageUserSecondRound;

    private int mCurrentStep;

    private int mRound;

    @Inject
    public GameManager(@Nullable @ApplicationContext Context context) {
        mContext = context;
    }

    //    IGameManager methods
    @Override
    public void setCallback(GameManagerCallback gameManagerCallback) {
        mGameManagerCallback = gameManagerCallback;
    }

    @Override
    public void initGame(GameResponse gameResponse) {
        this.mGameResponse = gameResponse;
        mCurrentStep = 0;
        mRound = 0;
        groupFirstRoundGame(gameResponse);
    }

    @Override
    public void moveToNextStep() {
        if (mGameManagerCallback == null) return;
        mCurrentStep++;
        if (mCurrentStep <= STEP_IN_FIRST_ROUND) {
            GameStateInfo gameStateInfo = generateGameCurrentState(groupImageUserFirstRound.get(mCurrentStep), -1);
            mGameManagerCallback.selectedStep(gameStateInfo);

        } else if (mCurrentStep == FIRST_ROUND_PRELIMINARY) {
            int selectedCount = calculateSelectedInAllGroup();

            if (selectedCount < NEED_SELECT_FIRST_ROUND) {
                List<ImageModel> packImage = getUnSelectedPics();
                int countRemains = NEED_SELECT_FIRST_ROUND - selectedCount;
                GameStateInfo gameStateInfo = generateGameCurrentState(packImage, countRemains);
                mGameManagerCallback.selectedStep(gameStateInfo);

            } else {
                moveToNextStep();  // continue next step
            }
        } else if (mCurrentStep <= STEP_IN_SECOND_ROUND) {
//            groupSecondRoundGame();
//            TODO second round

        } else if (mCurrentStep == SECOND_ROUND_PRELIMINARY) {

        } else if (mCurrentStep > SECOND_ROUND_PRELIMINARY) {
//            finish
        }
    }

    @Override
    public void saveSelected(List<ImageModel> packImage) {
        Log.e("", "saveSelected: ");
    }

    private GameStateInfo generateGameCurrentState(List<ImageModel> imageModels, int remainCountPreliminary) { // remainCount default
        GameStateInfo gameStateInfo = new GameStateInfo();
        gameStateInfo.round = mRound;
        gameStateInfo.name = mGameResponse.name;
        gameStateInfo.step = mCurrentStep + 1;
        gameStateInfo.countNeedPreliminary = remainCountPreliminary;
        gameStateInfo.isPreliminary = mCurrentStep == FIRST_ROUND_PRELIMINARY || mCurrentStep == SECOND_ROUND_PRELIMINARY;
        gameStateInfo.imageModels = imageModels;
        return gameStateInfo;
    }

    private void groupFirstRoundGame(GameResponse gameResponse) {
        groupImageUserFirstRound = new HashMap<>();
        int sizeImagesGame = gameResponse.mImageModels.size();
        int countInGroup = 0;
        int itemInGroup = sizeImagesGame / COUNT_FIRST_STEP;
        int groupIndex = 0;
        List<ImageModel> groupImage = new ArrayList<>();
        for (int i = 0; i < sizeImagesGame; i++) {
            ImageModel imageModel = gameResponse.mImageModels.get(i);
            if (countInGroup == itemInGroup) {
                countInGroup = 0;
                groupImageUserFirstRound.put(groupIndex, groupImage);
                groupImage = new ArrayList<>();
                groupIndex++;
            }
            groupImage.add(imageModel);
            countInGroup++;
            if (i == sizeImagesGame - 1) {
                groupImageUserFirstRound.put(groupIndex, groupImage);
            }
        }

        if (mGameManagerCallback != null) {
            mGameManagerCallback.selectedStep(generateGameCurrentState(groupImageUserFirstRound.get(mCurrentStep), -1));
        }
    }

    private int calculateSelectedInAllGroup() {
        int countSelected = 0;
        for (Integer key : groupImageUserFirstRound.keySet()) {
            for (ImageModel imageModel : groupImageUserFirstRound.get(key)) {
                if (imageModel.isSelected)
                    countSelected++;
            }
        }
        return countSelected;
    }

    private List<ImageModel> getUnSelectedPics() {
        List<ImageModel> imageModels = new ArrayList<>();
        for (Integer key : groupImageUserFirstRound.keySet()) {
            for (ImageModel imageModel : groupImageUserFirstRound.get(key)) {
                if (!imageModel.isSelected)
                    imageModels.add(imageModel);
            }
        }
        return imageModels;
    }
}
