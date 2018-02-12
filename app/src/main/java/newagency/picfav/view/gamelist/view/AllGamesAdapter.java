package newagency.picfav.view.gamelist.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import newagency.picfav.R;
import newagency.picfav.netwotk.response.GameResponse;

public class AllGamesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface AllGameCallback {
        void gameClick(@NonNull GameResponse game);
    }

    private List<GameResponse> allGames = new ArrayList<>();

    @NonNull
    private AllGameCallback callback;

    public AllGamesAdapter(@NonNull AllGameCallback callback) {
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(v, new GameViewHolder.GameViewHolderCallback() {
            @Override
            public void gameClick(@NonNull GameResponse gameResponse) {
                callback.gameClick(gameResponse);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GameViewHolder) {
            ((GameViewHolder) holder).bind(allGames.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return allGames.size();
    }

    public void addGames(boolean isForceRefresh, @NonNull List<GameResponse> allGames) {
        if (isForceRefresh) {
            this.allGames.clear();
        }
        this.allGames.addAll(allGames);
        notifyDataSetChanged();
    }
}
