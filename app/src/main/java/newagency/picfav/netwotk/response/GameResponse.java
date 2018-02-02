package newagency.picfav.netwotk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oroshka on 2/1/18.
 */

public class GameResponse {

    @SerializedName("PublicId")
    private String id;

    @SerializedName("Date")
    public long time;

    @SerializedName("Name")
    public String name;

    @SerializedName("Images")
    public List<ImageModel> mImageModels;
}
