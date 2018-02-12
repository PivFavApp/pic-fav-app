package newagency.picfav.netwotk.token;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadersInterceptor implements Interceptor {

    @Nullable
    private AccessTokenManager tokenManager;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

    public HeadersInterceptor(@NonNull AccessTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request initialRequest = chain.request();
        Request modifiedRequest = initialRequest;

        if (tokenManager != null && tokenManager.isTokenValid()) {
            modifiedRequest = initialRequest.newBuilder()
                    .addHeader(AUTHORIZATION, BEARER + tokenManager.getToken())
                    .build();
        }
        return chain.proceed(modifiedRequest);
    }
}
