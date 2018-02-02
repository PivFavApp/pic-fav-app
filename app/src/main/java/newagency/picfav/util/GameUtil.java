package newagency.picfav.util;

import java.util.List;

import newagency.picfav.netwotk.response.ImageModel;

/**
 * Created by oroshka on 2/2/18.
 */

public class GameUtil {

    public static int calculateSelected(List<ImageModel> mImageItemList) {
        int mSelectedCount = 0;
        for (ImageModel imageItemModel : mImageItemList) {
            if (imageItemModel.isSelected) {
                mSelectedCount++;
            }
        }
        return mSelectedCount;
    }
}
