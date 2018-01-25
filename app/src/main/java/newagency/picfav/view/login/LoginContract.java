package newagency.picfav.view.login;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface LoginContract {
    interface View extends AbsView {
        void showProgress();

        void hideProgress();
    }

    interface PresenterI extends IBasePresenter {

        void logIn(String userName, String password);

    }
}
