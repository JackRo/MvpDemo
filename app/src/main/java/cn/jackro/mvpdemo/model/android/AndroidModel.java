package cn.jackro.mvpdemo.model.android;

import java.util.List;

import cn.jackro.mvpdemo.bean.android.AndroidResult;
import cn.jackro.mvpdemo.model.BaseModel;
import cn.jackro.mvpdemo.model.exception.ApiException;
import io.reactivex.Flowable;

/**
 * @author JackRo
 */
public class AndroidModel extends BaseModel {

    public Flowable<List<AndroidResult>> getAndroidResults(int page) {
        return mApiService.getAndroidResults("Android", 20, page)
                .map(androidResultApiResult -> {
                    if (androidResultApiResult.error) {
                        throw new ApiException("服务器出错了");
                    }
                    return androidResultApiResult.results;
                });
    }
}
