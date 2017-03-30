package cn.jackro.mvpdemo.model;


import cn.jackro.mvpdemo.bean.login.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * ApiService
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("xxx")
    Observable<User> login(@Field("type") String type,
                           @Field("usercode") String username,
                           @Field("password") String password);
}
