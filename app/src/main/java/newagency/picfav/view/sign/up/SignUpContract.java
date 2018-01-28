package newagency.picfav.view.sign.up;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface SignUpContract {

    interface View extends AbsView {
        void showProgress();

        void hideProgress();

        void showMessage(String error);

        void redirectToLogin();

    }

    interface PresenterI extends IBasePresenter {

        void signUp(String firstName, String lastName, String username, String password, String dateOfBirth);
    }
}
