package newagency.picfav.view.gamelist.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.gamelist.MainContract;
import newagency.picfav.view.gamelist.data.IGetAllGamesRepository;

public class AllGamePresenter implements MainContract.Presenter {

    @Nullable
    private MainContract.View view;

    @NonNull
    private SharedPrefManager sharedPrefManager;

    @NonNull
    private IGetAllGamesRepository mIGetAllGamesRepository;

    @Inject
    AllGamePresenter(@NonNull MainContract.View view,
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
    public void logout() {
        sharedPrefManager.setAuthToken(null);
        sharedPrefManager.setLoggedIn(false);
        if (view != null) {
            view.navigateToWelcome();
        }
    }

    @Override
    public boolean isLogin() {
        return sharedPrefManager.isLoggedIn();
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
