package newagency.picfav.view.mainFeed.tabsScreen.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.util.AppConstants;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.mainFeed.gamelist.view.AllGamesFragment;
import newagency.picfav.view.mainFeed.inProgress.InProgressFragment;
import newagency.picfav.view.mainFeed.tabsScreen.TabsContact;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class TabsActivity extends BaseActivity implements TabsContact.View {

    private final static String TAB_ITEM_KEY = "tab_item_key";

    public static void launch(@NonNull Context context, AppConstants.TabItem tabItem) {
        Intent intent = new Intent(context, TabsActivity.class);
        intent.putExtra(TAB_ITEM_KEY, tabItem.ordinal());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ViewPagerAdapter viewPagerAdapter;

    private AppConstants.TabItem mSelectedTabItem;

    @Inject
    TabsContact.PresenterI presenter;

    @Override
    protected void onViewReady() {
        getArgs(getIntent());
        if (!presenter.isLogin()) {
            navigateToWelcome();
        } else {
            setupViewPager();
        }
    }

    @OnClick(R.id.iv_log_out)
    void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.log_out_title))
                .setPositiveButton(getResources().getString(R.string.yes), (dialogInterface, i) -> presenter.logout())
                .setNegativeButton(getResources().getString(R.string.no), (dialogInterface, i) -> {
                    //no-op
                })
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_tabs;
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getArgs(intent);
        moveToTab();
    }

    @Override
    public void navigateToWelcome() {
        finish();
        WelcomeActivity.start(this);
    }


    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new InProgressFragment());
        viewPagerAdapter.addFragment(new AllGamesFragment());
        viewPagerAdapter.addFragment(new InProgressFragment());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(mSelectedTabItem.index);

        int tabIconColorUnSelected = ContextCompat.getColor(this, R.color.colorWhite);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab currentTab = tabLayout.getTabAt(i);
            if (currentTab == null) return;
            switch (i) {
                case 0:
                    currentTab.setIcon(R.drawable.ic_friends);
                    break;
                case 1:
                    currentTab.setIcon(R.drawable.ic_home);
                    break;
                case 2:
                    currentTab.setIcon(R.drawable.ic_swords);
                    break;
            }
            if (currentTab.getIcon() == null) return;
            currentTab.getIcon().setColorFilter(tabIconColorUnSelected, PorterDuff.Mode.SRC_IN);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSelectedTabItem = AppConstants.TabItem.values()[tab.getPosition()];
                if (mSelectedTabItem == null) return;
                switch (mSelectedTabItem) {
                    case FRIEND:
                        tvTitle.setText(R.string.tab_friends);
                        break;
                    case HOME:
                        tvTitle.setText(R.string.tab_select_game);
                        break;
                    case CHALLENGES:
                        tvTitle.setText(R.string.tab_challenges);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //no-op
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //no-op
            }
        });
    }

    private void moveToTab() {
        if (viewPager != null) {
            viewPager.setCurrentItem(mSelectedTabItem.index);
        }
    }

    private void getArgs(Intent intent) {
        if (intent.getExtras() != null) {
            mSelectedTabItem = AppConstants.TabItem.values()[intent.getIntExtra(TAB_ITEM_KEY, AppConstants.TabItem.HOME.index)];
            intent.removeExtra(TAB_ITEM_KEY);
        } else {
            mSelectedTabItem = AppConstants.TabItem.HOME;
        }
    }
}
