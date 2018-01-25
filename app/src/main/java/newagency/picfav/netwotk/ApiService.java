package newagency.picfav.netwotk;

import newagency.picfav.netwotk.request.LoginRequestBody;
import newagency.picfav.netwotk.request.SignUpRequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by oroshka on 1/22/18.
 */

public interface ApiService {

    @POST("/api/login")
    Call<Object> login(@Body LoginRequestBody body);

    @POST("/api/user")
    Call<Object> signUp(@Body SignUpRequestBody body);
}
