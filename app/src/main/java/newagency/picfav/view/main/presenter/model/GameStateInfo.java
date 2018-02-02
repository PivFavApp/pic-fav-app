package newagency.picfav.view.main.presenter.model;

import java.util.List;

import newagency.picfav.netwotk.response.ImageModel;

/**
 * Created by oroshka on 2/2/18.
 */

public class GameStateInfo {

    public String name;

    public int round;

    public int step;

    public boolean isPreliminary;

    public int countNeedPreliminary;

    public List<ImageModel> imageModels;
}
