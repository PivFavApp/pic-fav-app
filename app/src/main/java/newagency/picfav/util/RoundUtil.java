package newagency.picfav.util;

import android.content.Context;

import newagency.picfav.R;
import newagency.picfav.view.main.presenter.model.GameStateInfo;

public class RoundUtil {

    public static String getRoundAndStepStr(Context context, GameStateInfo gameStateInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        int roundForUI = gameStateInfo.round + 1;
        String roundStr = context.getString(R.string.main_round_holder, roundForUI);
        String stepStr = getStepNumberString(context, gameStateInfo.step);
        stringBuilder
                .append(roundStr)
                .append(" ")
                .append(stepStr);

        return stringBuilder.toString();
    }

    private static String getStepNumberString(Context context, int step) {
        String stepStr;
        switch (step) {
            case 1:
                stepStr = context.getString(R.string.main_first_step_holder, step);
                break;

            case 2:
                stepStr = context.getString(R.string.main_second_step_holder, step);
                break;

            case 3:
                stepStr = context.getString(R.string.main_third_step_holder, step);
                break;

            default:
                stepStr = context.getString(R.string.main_default_step_holder, step);
        }
        return stepStr;
    }
}
