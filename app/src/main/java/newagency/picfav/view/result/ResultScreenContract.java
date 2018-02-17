package newagency.picfav.view.result;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface ResultScreenContract {

    interface View extends AbsView {

        void showProgress();

        void hideProgress();
    }

    interface PresenterI extends IBasePresenter {

    }
}
