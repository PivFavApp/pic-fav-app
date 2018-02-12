package newagency.picfav.view.main.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.netwotk.response.AllGamesResponse;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.main.MainContract;
import newagency.picfav.view.main.data.GetAllGamesRepository;

public class MainPresenter implements MainContract.Presenter {

    @Nullable
    private MainContract.View view;

    @NonNull
    private SharedPrefManager sharedPrefManager;

    @NonNull
    private GetAllGamesRepository getAllGamesRepository;

    @Inject
    MainPresenter(@NonNull MainContract.View view,
                  @NonNull SharedPrefManager sharedPrefManager,
                  @NonNull GetAllGamesRepository getAllGamesRepository) {
        this.view = view;
        this.sharedPrefManager = sharedPrefManager;
        this.getAllGamesRepository = getAllGamesRepository;
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
        getAllGamesRepository.getAllGames(new GetAllGamesRepository.GetAllGamesCallback() {

            @Override
            public void onSuccess(List<GameResponse> allGames) {
                if (view != null && allGames != null) {
                    view.allGames(true, allGames);
                }
            }

            @Override
            public void onError(String error) {
                if (view != null) {
                    view.showMessage(error);
                }
            }
        });
    }
}
