package newagency.picfav.login;

import newagency.picfav.base.AbsView;
import newagency.picfav.base.IBasePresenter;

/**
 * Created by oroshka on 1/22/18.
 */

public class LoginContract {
    public interface View extends AbsView {
        void showProgress();

        void hideProgress();
    }

    public interface PresenterI extends IBasePresenter {

        void logIn();

    }
}
