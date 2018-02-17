package newagency.picfav.view.result.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.view.result.ResultScreenContract;
import newagency.picfav.view.result.data.IResultRepository;

/**
 * Created by oroshka on 2/13/18.
 */

public class ResultScreenPresenter implements ResultScreenContract.PresenterI {

    @NonNull
    @ApplicationContext
    private Context mContext;

    @Nullable
    private ResultScreenContract.View mView;

    @NonNull
    private IResultRepository resultRepository;

    @Inject
    public ResultScreenPresenter(@NonNull @ApplicationContext Context context,
                                 @Nullable ResultScreenContract.View view,
                                 @NonNull IResultRepository resultRepository) {
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
