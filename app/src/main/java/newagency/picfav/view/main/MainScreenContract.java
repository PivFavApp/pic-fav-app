package newagency.picfav.view.main;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

/**
 * Created by oroshka on 1/28/18.
 */

public interface MainScreenContract {

    interface View extends AbsView {

        void navigateToLogIn();

    }

    interface PresenterI extends IBasePresenter {

        boolean isLogin();

        void logout();

    }
}
