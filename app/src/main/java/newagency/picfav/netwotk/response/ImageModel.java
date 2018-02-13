package newagency.picfav.netwotk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oroshka on 2/1/18.
 */

public class ImageModel {

    @SerializedName("id")
    public String id;

    @SerializedName("ImageUrl")
    public String url;

    @SerializedName("IsValid")
    public boolean isValid;

    public boolean isSelected;

    public ImageModel(ImageModel other) {
        id = other.id;
        url = other.url;
        isValid = other.isValid;
        isSelected = other.isSelected;
    }

    public ImageModel() {
    }
}
