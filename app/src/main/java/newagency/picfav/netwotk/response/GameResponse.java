package newagency.picfav.netwotk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {

    @SerializedName("PublicId")
    private String id;

    @SerializedName("Date")
    private long time;

    @SerializedName("Name")
    private String name;

    @SerializedName("Images")
    private List<ImageModel> mImageModels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeInMillisec() {
        return time * 1000;
    }

    public void setTimeInMillisec(long time) {
        this.time = time / 1000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageModel> getImageModels() {
        return mImageModels;
    }

    public void setImageModels(List<ImageModel> imageModels) {
        mImageModels = imageModels;
    }
}
