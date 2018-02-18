package newagency.picfav.view.mainFeed.gamelist.view;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.view.AbsFragment;
import newagency.picfav.view.main.view.MainScreenActivity;
import newagency.picfav.view.mainFeed.gamelist.AllGamesContract;
import newagency.picfav.view.mainFeed.gamelist.presenter.AllGamePresenter;

public class AllGamesFragment extends AbsFragment implements AllGamesContract.View {

    @BindView(R.id.rev_view)
    RecyclerView recyclerView;

    @BindView(R.id.root)
    View root;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.empty_tv)
    TextView mEmptyTv;

    @Inject
    AllGamePresenter presenter;

    AllGamesAdapter adapter;

    private AllGamesAdapter.AllGameCallback mAllGameCallback = new AllGamesAdapter.AllGameCallback() {
        @Override
        public void gameClick(@NonNull GameResponse game) {
            MainScreenActivity.start(getActivity(), game.getId());
//            ResultScreenActivity.start(getActivity(), 100);
        }
    };

    @Override
    protected void onViewReady() {
        initRecyclerView();
        presenter.getAllGames();
    }


    @Override
    protected int onRequestLayout() {
        return R.layout.fragment_all_game;
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
    public void showAllGame(boolean isForceRefresh, @NonNull List<GameResponse> games) {
        if (adapter != null) {
            adapter.addGames(isForceRefresh, games);
        }
    }

    private void initRecyclerView() {
        adapter = new AllGamesAdapter(mAllGameCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String error) {
        Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyList() {
        mEmptyTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyList() {
        mEmptyTv.setVisibility(View.GONE);
    }
}
