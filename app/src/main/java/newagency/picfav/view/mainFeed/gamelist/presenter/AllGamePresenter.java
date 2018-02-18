package newagency.picfav.view.mainFeed.gamelist.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.mainFeed.gamelist.AllGamesContract;
import newagency.picfav.view.mainFeed.gamelist.data.IGetAllGamesRepository;

public class AllGamePresenter implements AllGamesContract.Presenter {

    @Nullable
    private AllGamesContract.View view;

    @NonNull
    private SharedPrefManager sharedPrefManager;

    @NonNull
    private IGetAllGamesRepository mIGetAllGamesRepository;

    @Inject
    AllGamePresenter(@NonNull AllGamesContract.View view,
                     @NonNull SharedPrefManager sharedPrefManager,
                     @NonNull IGetAllGamesRepository IGetAllGamesRepository) {
        this.view = view;
        this.sharedPrefManager = sharedPrefManager;
        this.mIGetAllGamesRepository = IGetAllGamesRepository;
    }

    @Override
    public void onStart() {
        getAllGames();
    }

    @Override
    public void onStop() {
        view = null;
    }

    @Override
    public void getAllGames() {
        if (view == null) return;
        view.showProgressBar();
        mIGetAllGamesRepository.getAllGames(new IGetAllGamesRepository.GetAllGamesCallback() {
            @Override
            public void onSuccess(List<GameResponse> allGames) {
                if (view != null) {
                    view.hideProgressBar();
                    if (allGames != null) {
                        view.showAllGame(true, allGames);
                        if (allGames.size() == 0) {
                            view.showEmptyList();
                        } else {
                            view.hideEmptyList();
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {
                if (view != null) {
                    view.hideProgressBar();
                    view.showMessage(error);
                    view.showEmptyList();
                }
            }
        });
    }
}
