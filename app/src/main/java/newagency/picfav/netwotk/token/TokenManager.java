package newagency.picfav.netwotk.token;

import android.support.annotation.NonNull;

public interface TokenManager {
    String getToken();

    String refreshToken();

    boolean isTokenValid();

    void clearToken();
}
