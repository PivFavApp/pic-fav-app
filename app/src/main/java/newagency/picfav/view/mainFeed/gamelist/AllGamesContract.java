package newagency.picfav.view.mainFeed.gamelist;

import android.support.annotation.NonNull;

import java.util.List;

import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface AllGamesContract {

    interface View extends AbsView {

        void showAllGame(boolean isForceRefresh, @NonNull List<GameResponse> games);

        void showMessage(String message);

        void showProgressBar();

        void hideProgressBar();

        void showEmptyList();

        void hideEmptyList();
    }

    interface Presenter extends IBasePresenter {

        void getAllGames();

    }
}
