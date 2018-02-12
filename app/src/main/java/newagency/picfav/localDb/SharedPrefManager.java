package newagency.picfav.localDb;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import newagency.picfav.dagger.scope.ApplicationContext;
import newagency.picfav.netwotk.ApiConst;
import newagency.picfav.util.AppConstants;

public class SharedPrefManager {
    private final SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefManager(@ApplicationContext Context context) {
        mSharedPreferences = context.getSharedPreferences(ApiConst.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void setAuthToken(String authToken) {
        mSharedPreferences.edit().putString(ApiConst.AUTH_TOKEN_SHARED_PREFS_KEY, authToken).apply();
    }

    public String getAuthToken() {
        return mSharedPreferences.getString(ApiConst.AUTH_TOKEN_SHARED_PREFS_KEY, "");
    }

    public boolean isLoggedIn() {
        return mSharedPreferences.getBoolean(ApiConst.IS_LOGGED_IN_KEY, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean(ApiConst.IS_LOGGED_IN_KEY, loggedIn).apply();
    }

    public void setGridState(AppConstants.GridState gridState) {
        mSharedPreferences.edit().putInt(ApiConst.GRID_STATE, gridState.ordinal()).apply();
    }

    public AppConstants.GridState getGridState() {
        int ordinal = mSharedPreferences.getInt(ApiConst.GRID_STATE, 0);
        return AppConstants.GridState.values()[ordinal];
    }
}
