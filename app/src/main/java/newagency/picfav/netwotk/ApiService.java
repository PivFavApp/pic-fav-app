package newagency.picfav.netwotk;

import java.util.List;

import newagency.picfav.netwotk.request.SignUpRequestBody;
import newagency.picfav.netwotk.response.GameResponse;
import newagency.picfav.netwotk.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by oroshka on 1/22/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password,
                              @Field("grant_type") String grantType);

    @POST("/api/user")
    Call<Object> signUp(@Body SignUpRequestBody body);

    @GET("/api/game")
    Call<GameResponse> getGame(@Query("publicId") String publicId);

    @GET("/api/game")
    Call<List<GameResponse>> getAllGames();

    @GET("/api/userstatistic")
    Call<Object> postFinishGame(@Field("GamePublicId") String gameId,
                                @Field("Date") long data,
                                @Field("Result") int result);
}
