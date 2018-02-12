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
import newagency.picfav.util.GameUtil;
import newagency.picfav.view.main.presenter.model.GameResult;
import newagency.picfav.view.main.presenter.model.GameStateInfo;

/**
 * Created by oroshka on 2/1/18.
 */

public class GameManager implements IGameManager {

    private final static int COUNT_FIRST_ROUND = 4;

    private final static int COUNT_SECOND_ROUND = 2;

    private final static int STEP_IN_FIRST_ROUND = 3;

    private final static int FIRST_ROUND_PRELIMINARY = 4;

    private final static int STEP_IN_SECOND_ROUND = 6;

    private final static int SECOND_ROUND_PRELIMINARY = 7;

    private final static int NEED_SELECT_FIRST_ROUND = 20;

    private final static int NEED_SELECT_SECOND_ROUND = 10;

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
            int selectedCount = calculateSelectedInAllGroup(groupImageUserFirstRound);

            if (selectedCount < NEED_SELECT_FIRST_ROUND) {
                List<ImageModel> packImage = getPicsBySelection(groupImageUserFirstRound, false);
                int countRemains = NEED_SELECT_FIRST_ROUND - selectedCount;
                GameStateInfo gameStateInfo = generateGameCurrentState(packImage, countRemains);
                mGameManagerCallback.selectedStep(gameStateInfo);

            } else {
                moveToNextStep();  // continue next step
            }
        } else if (mCurrentStep <= STEP_IN_SECOND_ROUND) {
            mRound++;
            groupSecondRoundGame(getPicsBySelection(groupImageUserFirstRound, false));

        } else if (mCurrentStep == SECOND_ROUND_PRELIMINARY) {
            int selectedInSecondRound = calculateSelectedInAllGroup(groupImageUserSecondRound);

            if (selectedInSecondRound < NEED_SELECT_SECOND_ROUND) {
                List<ImageModel> packImageUnselected = getPicsBySelection(groupImageUserSecondRound,
                        false);
                int countRemains = NEED_SELECT_SECOND_ROUND - selectedInSecondRound;
                GameStateInfo gameStateInfo = generateGameCurrentState(packImageUnselected, countRemains);
                mGameManagerCallback.selectedStep(gameStateInfo);

            } else {
                moveToNextStep();
            }

        } else if (mCurrentStep > SECOND_ROUND_PRELIMINARY) {
            finishGame();
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
        int itemInGroup = sizeImagesGame / COUNT_FIRST_ROUND;
        int groupIndex = mCurrentStep;
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

    private void groupSecondRoundGame(List<ImageModel> originList) {
        groupImageUserSecondRound = new HashMap<>();
        int sizeImagesGame = originList.size();
        int countInGroup = 0;
        int itemInGroup = sizeImagesGame / COUNT_SECOND_ROUND;
        int groupIndex = mCurrentStep;
        List<ImageModel> groupImage = new ArrayList<>();
        for (int i = 0; i < sizeImagesGame; i++) {
            ImageModel imageModel = originList.get(i);
            if (countInGroup == itemInGroup) {
                countInGroup = 0;
                groupImageUserSecondRound.put(groupIndex, groupImage);
                groupImage = new ArrayList<>();
                groupIndex++;
            }
            groupImage.add(imageModel);
            countInGroup++;
            if (i == sizeImagesGame - 1) {
                groupImageUserSecondRound.put(groupIndex, groupImage);
            }
        }

        if (mGameManagerCallback != null) {
            mGameManagerCallback.selectedStep(generateGameCurrentState(groupImageUserSecondRound.get(mCurrentStep), -1));
        }
    }


    private int calculateSelectedInAllGroup(Map<Integer, List<ImageModel>> groupImageUserFirstRound) {
        int countSelected = 0;
        for (Integer key : groupImageUserFirstRound.keySet()) {
            countSelected += GameUtil.calculateSelected(groupImageUserFirstRound.get(key));
        }
        return countSelected;
    }

    private List<ImageModel> getPicsBySelection(Map<Integer, List<ImageModel>> originGroup, boolean isSelected) {
        List<ImageModel> imageModels = new ArrayList<>();
        for (Integer key : originGroup.keySet()) {
            for (ImageModel imageModel : originGroup.get(key)) {
                if (imageModel.isSelected == isSelected)
                    imageModels.add(imageModel);
            }
        }
        return imageModels;
    }

    private void finishGame() {
        int validCountInFirstRound = GameUtil.calculateValid(getPicsBySelection(groupImageUserFirstRound, true));
        int validCountInSecondRound = GameUtil.calculateValid(getPicsBySelection(groupImageUserSecondRound, true));
        int firstPercent = (validCountInFirstRound / NEED_SELECT_FIRST_ROUND) * 100;
        int secondInPercent = (validCountInSecondRound / NEED_SELECT_SECOND_ROUND) * 100;

        float result = (firstPercent * 0.75f) + secondInPercent;

        GameResult gameResult = new GameResult();
        gameResult.score = result;

        if (mGameManagerCallback != null) {
            mGameManagerCallback.finishedGame(gameResult);
        }
    }
}
