package newagency.picfav.view.result.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

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

//    ResultScreenContract.PresenterI methods
    @Override
    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void challengesClicked() {
        if (mView != null) {
            mView.navigateToChallenges();
        }
    }
}
