package newagency.picfav.netwotk.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oroshka on 2/17/18.
 */

public class ResultGameRequestBody {
    @SerializedName("GamePublicId")
    public String gameId;

    @SerializedName("Date")
    public long time;

    @SerializedName("Result")
    public int result;
}
