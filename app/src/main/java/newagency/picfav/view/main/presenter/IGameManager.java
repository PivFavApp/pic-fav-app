package newagency.picfav.view.main.presenter;

import java.util.List;

import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.view.main.presenter.model.GameStateInfo;

/**
 * Created by oroshka on 2/1/18.
 */

public interface IGameManager {

    void initGame(GameResponse gameResponse);

    void setCallback(GameManager.GameManagerCallback callback);

    void moveToNextStep();

    void saveSelected(List<ImageModel> packImage);

    interface GameManagerCallback {

        void selectedStep(GameStateInfo gameStateInfo);

    }
}
