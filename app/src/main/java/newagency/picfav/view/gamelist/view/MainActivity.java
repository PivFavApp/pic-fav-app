package newagency.picfav.view.gamelist.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.gamelist.MainContract;
import newagency.picfav.view.gamelist.presenter.MainPresenter;
import newagency.picfav.view.main.view.MainScreenActivity;
import newagency.picfav.view.welcome.view.WelcomeActivity;

public class MainActivity extends BaseActivity implements MainContract.View {

    public static void launch(@NonNull AppCompatActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.rev_view)
    RecyclerView recyclerView;

    @BindView(R.id.root)
    View root;

    @Inject
    MainPresenter presenter;

    AllGamesAdapter adapter;

    @Override
    protected void onViewReady() {
        if (!presenter.isLogin()) {
            navigateToWelcome();
            return;

        } else {
            presenter.onStart();
        }
        initRecView();
    }

    @Override
    protected void onViewDestroy() {

    }

    @OnClick(R.id.iv_log_out)
    void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.log_out_title))
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.logout();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //no-op
                    }
                })
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_main;
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

    @Override
    public void allGames(boolean isForceRefresh, @NonNull List<GameResponse> games) {
        if (adapter != null) {
            adapter.addGames(isForceRefresh, games);
        }
    }

    private void initRecView() {
        adapter = new AllGamesAdapter(new AllGamesAdapter.AllGameCallback() {
            @Override
            public void gameClick(@NonNull GameResponse game) {
                MainScreenActivity.start(MainActivity.this, game.getId());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String error) {
        Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
    }

}
