package newagency.picfav.view.gamelist.data;

import android.support.annotation.NonNull;

import java.util.List;

import newagency.picfav.netwotk.response.GameResponse;

public interface IGetAllGamesRepository {

    void getAllGames(@NonNull GetAllGamesCallback callback);

    interface GetAllGamesCallback {

        void onSuccess(List<GameResponse> allGames);

        void onError(String error);
    }
}
