package newagency.picfav.view.mainFeed.tabsScreen.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.mainFeed.gamelist.view.AllGamesFragment;
import newagency.picfav.view.mainFeed.inProgress.InProgressFragment;
import newagency.picfav.view.mainFeed.tabsScreen.TabsContact;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class TabsActivity extends BaseActivity implements TabsContact.View {

    public static void launch(@NonNull Context context) {
        Intent intent = new Intent(context, TabsActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ViewPagerAdapter viewPagerAdapter;

    @Inject
    TabsContact.PresenterI presenter;

    @Override
    protected void onViewReady() {
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
        viewPager.setCurrentItem(1);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            switch (i) {
                case 0:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_friends);
                    break;
                case 1:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_home);
                    break;
                case 2:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_swords);
                    break;
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tvTitle.setText(R.string.tab_friends);
                        break;
                    case 1:
                        tvTitle.setText(R.string.tab_select_game);
                        break;
                    case 2:
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
}
