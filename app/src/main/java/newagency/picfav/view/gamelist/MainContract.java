package newagency.picfav.view.gamelist;

import android.support.annotation.NonNull;

import java.util.List;

import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface MainContract {

    interface View extends AbsView {

        void navigateToWelcome();

        void showAllGame(boolean isForceRefresh, @NonNull List<GameResponse> games);

        void showMessage(String message);

        void showProgressBar();

        void hideProgressBar();

        void showEmptyList();

        void hideEmptyList();
    }

    interface Presenter extends IBasePresenter {

        void logout();

        boolean isLogin();

        void getAllGames();

    }
}
