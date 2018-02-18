package newagency.picfav.view.mainFeed.tabsScreen;

import newagency.picfav.view.AbsView;
import newagency.picfav.view.IBasePresenter;

public interface TabsContact {

    interface View extends AbsView {

        void navigateToWelcome();
    }

    interface PresenterI extends IBasePresenter {

        boolean isLogin();

        void logout();
    }
}
