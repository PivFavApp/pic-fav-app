package newagency.picfav.netwotk.request;

/**
 * Created by oroshka on 1/24/18.
 */

public class LoginRequestBody {

    public String username;

    public String password;

    public LoginRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
