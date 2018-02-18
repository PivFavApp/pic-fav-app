package newagency.picfav.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import newagency.picfav.PicFavApplication;
import newagency.picfav.dagger.ApplicationComponent;

public abstract class AbsFragment extends Fragment{

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(onRequestLayout(), container, false);

        initializeInjection();
        initializeViewsInjection(view);
        return view;
    }

    private void initializeInjection() {
        onInitializeInjection();
    }

    private void initializeViewsInjection(@NonNull View view) {
        ButterKnife.bind(this, view);
        onViewReady();
    }

    @LayoutRes
    protected abstract int onRequestLayout();

    protected abstract void onViewReady();

    protected abstract void onInitializeInjection();

    protected void showSnackBar(@NonNull View view, int message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    protected void showSnackBar(@NonNull View view, @NonNull String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    protected ApplicationComponent getApplicationComponent() {
        return PicFavApplication.mApplicationComponent;
    }
}
