package newagency.picfav.view.result;

import android.graphics.Bitmap;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface ResultScreenContract {

    interface View extends AbsView {

        void showProgress();

        void hideProgress();

        void navigateToChallenges();

    }

    interface PresenterI extends IBasePresenter {

        Bitmap getBitmapFromView(android.view.View contentResult);

        void challengesClicked();

    }
}
