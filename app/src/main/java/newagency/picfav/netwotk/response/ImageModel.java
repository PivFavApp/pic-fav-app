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

    @SerializedName("isValid")
    public boolean isValid;

    public boolean isSelected;
}
