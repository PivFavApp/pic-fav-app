package newagency.picfav.netwotk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oroshka on 1/28/18.
 */

public class ApiError {

    @SerializedName("error")
    public String error;

    @SerializedName("error_description")
    public String errorDescription;

}
