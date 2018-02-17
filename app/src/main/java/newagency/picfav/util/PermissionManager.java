package newagency.picfav.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * This class will contain all needed permission strings. Add new one clickLike other below
 */
public class PermissionManager {

    public static boolean hasPermission(@NonNull AppCompatActivity activity, @NonNull String permission, int requestCode) {
        if (!hasPermissionInternal(activity, permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermission(activity, permission, requestCode);
            }
            return false;
        }
        return true;
    }

    public static boolean hasPermission(@NonNull Fragment fragment, @NonNull String permission, int requestCode) {
        if (!hasPermissionInternal(fragment, permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermission(fragment, permission, requestCode);
            }
            return false;
        }
        return true;
    }

    public static boolean isNeverAsk(Activity activity, String... permissions) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0]);
    }

    public static boolean isNeverAsk(Fragment fragment, String... permissions) {
        return !fragment.shouldShowRequestPermissionRationale(permissions[0]);
    }

    private static boolean hasPermissionInternal(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean hasPermissionInternal(@NonNull Fragment fragment, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(fragment.getContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void requestPermission(@NonNull AppCompatActivity appCompatActivity, @NonNull String permission, int requestCode) {
        appCompatActivity.requestPermissions(new String[]{permission}, requestCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void requestPermission(@NonNull Fragment fragment, @NonNull String permission, int requestCode) {
        fragment.requestPermissions(new String[]{permission}, requestCode);
    }
}

