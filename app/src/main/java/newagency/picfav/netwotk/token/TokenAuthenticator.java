package newagency.picfav.netwotk.token;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;


public class TokenAuthenticator implements Authenticator {
    @NonNull
    private TokenManager tokenManager;

    private final String AUTHORIZATION = "Authorization";
    private final String JWT = "JWT";

    public TokenAuthenticator(@NonNull TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    /**
     * Method will be triggered by OkHttpClient
     * automatically every time  if will receive 401 error code
     *
     * @param route
     * @param response for individual server call
     * @return new request with refreshed token in call header
     * @throws IOException
     */

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        // Refresh your access_token using a synchronous api request
        boolean unauthorized = response.code() == 401;
        String accessToken = tokenManager.getToken();

        if (unauthorized) {
            accessToken = tokenManager.refreshToken();
        }

        // Add new header to rejected request and retry it
        if (tokenManager.isTokenValid()) {
            return response.request().newBuilder()
                    .header(AUTHORIZATION, JWT + accessToken)
                    .build();
        } else {
            return response.request().newBuilder()
                    .build();
        }
    }
}
