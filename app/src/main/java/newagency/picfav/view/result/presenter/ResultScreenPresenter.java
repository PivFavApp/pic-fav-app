package newagency.picfav.view.result.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.view.result.ResultScreenContract;

/**
 * Created by oroshka on 2/13/18.
 */

public class ResultScreenPresenter implements ResultScreenContract.PresenterI {

    @NonNull
    @ApplicationContext
    Context mContext;

    @Nullable
    ResultScreenContract.View mView;

    @Inject
    public ResultScreenPresenter(@NonNull @ApplicationContext Context context,
                                 @Nullable ResultScreenContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
