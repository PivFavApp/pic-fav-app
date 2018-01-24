package newagency.picfav.util;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {

    public static String toNewFormat(@NonNull String stringDate) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = originalFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String toNewFormat(@NonNull String stringDate,
                                     @NonNull String originalFormatString,
                                     @NonNull String targetFormatString) {
        SimpleDateFormat originalFormat = new SimpleDateFormat(originalFormatString, Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat(targetFormatString, Locale.getDefault());
        Date date = null;
        try {
            date = originalFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String toAnotherFormat(@NonNull String stringDate,
                                         @NonNull String sourceFormat,
                                         @NonNull String outFormat) {
        SimpleDateFormat originalFormat = new SimpleDateFormat(sourceFormat, Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat(outFormat, Locale.getDefault());
        Date date = null;
        try {
            date = originalFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String formatDateFromOnetoAnother(String date, String givenformat, String resultformat) {
        String result;
        SimpleDateFormat sdf;
        SimpleDateFormat sdf1;

        try {
            sdf = new SimpleDateFormat(givenformat);
            sdf1 = new SimpleDateFormat(resultformat);
            result = sdf1.format(sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }

    public static String when(@NonNull String createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = 0;
        try {
            time = sdf.parse(createdAt).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DateUtils
                .getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
                .toString();
    }
}
