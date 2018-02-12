package newagency.picfav.view.main;

import java.util.List;

import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

/**
 * Created by oroshka on 1/28/18.
 */

public interface MainScreenContract {

    interface View extends AbsView {

        void showMessage(String message);

        void updateToolbar(String name, String setRoundName);

        void updateAdapterPhoto(List<ImageModel> imageModels, int countNeedPreliminary);

        void showPreliminaryCount();

        void showNeededCountSimple();

    }

    interface PresenterI extends IBasePresenter {

        void goToNextStep(List<ImageModel> packImage);

        void loadGame(String idGame);
    }
}
