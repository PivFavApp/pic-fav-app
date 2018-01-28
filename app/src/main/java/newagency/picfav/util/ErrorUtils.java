package newagency.picfav.util;

import android.content.Context;

import com.google.gson.Gson;

import java.net.UnknownHostException;

import newagency.picfav.R;
import newagency.picfav.netwotk.response.ApiError;
import okhttp3.ResponseBody;

/**
 * Created by oroshka on 1/28/18.
 */

public class ErrorUtils {

    public static String parseError(Context context, Throwable t) {

        String message;

        if (t instanceof UnknownHostException) {
            message = context.getString(R.string.error_connection_msg);

        } else {
            message = context.getString(R.string.error_something_wrong_msg);
        }

       return message;
    }

    public static ApiError parseError(Context context, ResponseBody responseErrir) {
        ApiError apiError;
        try {
            Gson gson = new Gson();
            apiError = gson.fromJson(responseErrir.string(), ApiError.class);

        } catch (Exception e) {
            apiError = new ApiError();
            apiError.errorDescription = context.getString(R.string.error_something_wrong_msg);
        }
        return apiError;
    }
}

