package newagency.picfav.view.main.presenter;

import android.support.annotation.NonNull;

import newagency.picfav.netwotk.response.GameResponse;

/**
 * Created by oroshka on 2/1/18.
 */

public interface IMainScreenRepository {

    void getGame(String idGame, @NonNull GameCallback callback);

    interface GameCallback {

        void onSuccess(GameResponse gameResponse);

        void onError(String error);
    }

}
