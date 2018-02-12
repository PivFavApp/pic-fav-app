package newagency.picfav.netwotk.token;

import android.support.annotation.NonNull;

import newagency.picfav.localDb.SharedPrefManager;

public class AccessTokenManager implements TokenManager {

    private SharedPrefManager sharedPrefManager;

    public AccessTokenManager(@NonNull SharedPrefManager sharedPrefManager) {
        this.sharedPrefManager = sharedPrefManager;
    }

    @Override
    public String getToken() {
        return sharedPrefManager.getAuthToken();
    }

    @Override
    public String refreshToken() {
        return null;
        //TODO
    }

    @Override
    public boolean isTokenValid() {
        return sharedPrefManager.isLoggedIn();
    }

    @Override
    public void clearToken() {
        sharedPrefManager.setAuthToken(null);
        sharedPrefManager.setLoggedIn(false);
    }
}
