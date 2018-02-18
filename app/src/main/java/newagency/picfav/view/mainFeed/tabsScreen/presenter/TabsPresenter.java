package newagency.picfav.view.mainFeed.tabsScreen.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import newagency.picfav.localDb.SharedPrefManager;
import newagency.picfav.view.mainFeed.tabsScreen.TabsContact;

public class TabsPresenter implements TabsContact.PresenterI {

    @Nullable
    private TabsContact.View view;

    @NonNull
    private SharedPrefManager sharedPrefManager;

    @Inject
    TabsPresenter(@NonNull TabsContact.View view,
                  @NonNull SharedPrefManager sharedPrefManager) {
        this.view = view;
        this.sharedPrefManager = sharedPrefManager;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean isLogin() {
        return sharedPrefManager.isLoggedIn();
    }

    @Override
    public void logout() {
        sharedPrefManager.setAuthToken(null);
        sharedPrefManager.setLoggedIn(false);
        if (view != null) {
            view.navigateToWelcome();
        }
    }
}
