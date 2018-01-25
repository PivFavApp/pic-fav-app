package newagency.picfav.netwotk.request;

import com.google.gson.annotations.SerializedName;

import newagency.picfav.util.AppConstants;

/**
 * Created by oroshka on 1/24/18.
 */

public class LoginRequestBody {

    public String username;

    public String password;

    @SerializedName("grant_type")
    public String grantType;

    public LoginRequestBody(String username, String password, AppConstants.GrantType grantType) {
        this.username = username;
        this.password = password;
        this.grantType = grantType.value;
    }
}
