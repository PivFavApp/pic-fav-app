package newagency.picfav.view.login;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface LoginContract {
    interface View extends AbsView {
        void showProgressDialog();

        void hideProgressDialog();

        void showMessage(String error);

        void showUserNameError(String error);

        void showPasswordError(String error);

        void clearAllError();

        void navigateToMainScreen();

    }

    interface PresenterI extends IBasePresenter {

        void logIn(String userName, String password);

    }
}
