package newagency.picfav.view.result.data;

import newagency.picfav.netwotk.request.ResultGameRequestBody;

/**
 * Created by oroshka on 2/17/18.
 */

public interface IResultRepository {

    void postGameResult(ResultGameRequestBody body);


}
