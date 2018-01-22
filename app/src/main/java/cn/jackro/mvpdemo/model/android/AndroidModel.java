package cn.jackro.mvpdemo.model.android;

import cn.jackro.mvpdemo.bean.ApiResult;
import cn.jackro.mvpdemo.bean.android.AndroidResult;
import cn.jackro.mvpdemo.model.BaseModel;
import io.reactivex.Flowable;

/**
 * @author JackRo
 */
public class AndroidModel extends BaseModel {

    public Flowable<ApiResult<AndroidResult>> getAndroidResults(int page) {
        return mApiService.getAndroidResults("Android", 20, page);
    }
}
