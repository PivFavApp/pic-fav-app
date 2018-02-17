package newagency.picfav.netwotk.response;

import java.io.Serializable;
import java.util.List;

public class AllGamesResponse implements Serializable {

    private List<GameResponse> allGames;

    public AllGamesResponse(List<GameResponse> allGames) {
        this.allGames = allGames;
    }

    public List<GameResponse> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<GameResponse> allGames) {
        this.allGames = allGames;
    }

    @Override
    public String toString() {
        return "AllGamesResponse{" +
                "showAllGame=" + allGames +
                '}';
    }
}
