package newagency.picfav.view.main.data;

import android.support.annotation.NonNull;

import java.util.List;

import newagency.picfav.netwotk.response.GameResponse;

public interface GetAllGamesRepository {

    void getAllGames(@NonNull GetAllGamesCallback callback);

    interface GetAllGamesCallback {

        void onSuccess(List<GameResponse> allGames);

        void onError(String error);
    }
}
