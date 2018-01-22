package cn.jackro.mvpdemo.model;


import cn.jackro.mvpdemo.bean.ApiResult;
import cn.jackro.mvpdemo.bean.android.AndroidResult;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ApiService
 */
public interface ApiService {

    @GET("data/{category}/{count}/{page}")
    Flowable<ApiResult<AndroidResult>> getAndroidResults(@Path("category") String category,
                                                         @Path("count") int count,
                                                         @Path("page") int page);
}
