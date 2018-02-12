package newagency.picfav.view.gamelist.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.netwotk.response.GameResponse;

public class GameViewHolder extends RecyclerView.ViewHolder {

    public interface GameViewHolderCallback {
        void gameClick(@NonNull GameResponse gameResponse);
    }

    @BindView(R.id.tb_name)
    TextView tvName;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.btn_start_game)
    Button btnStartGame;

    @NonNull
    private GameViewHolderCallback callback;

    @Nullable
    private GameResponse game;

    public GameViewHolder(View itemView, @NonNull GameViewHolderCallback callback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.callback = callback;
    }

    public void bind(@NonNull GameResponse gameResponse) {
        this.game = gameResponse;
        tvName.setText(gameResponse.name);
        Date date = new Date(gameResponse.time);
        tvDate.setText(date.getDay() + ":" + date.getMonth() + ":" + date.getYear());
    }

    @OnClick(R.id.btn_start_game)
    void gameClick() {
        if (game != null) {
            callback.gameClick(game);
        }
    }
}
