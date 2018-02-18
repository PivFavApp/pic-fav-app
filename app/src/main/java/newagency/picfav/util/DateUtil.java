package newagency.picfav.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by oroshka on 10/19/17.
 */

public class DateUtil {

    private static SimpleDateFormat formatGameTime =
            new SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault());

    public static String getGameDate(long timeInMillisecond) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timeInMillisecond);
        return formatGameTime.format(calendar.getTime());
    }

    public static long nowMilliSec() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
    }
}
