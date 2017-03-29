package cn.jackro.mvpdemo.model.login;

import cn.jackro.mvpdemo.bean.login.User;
import cn.jackro.mvpdemo.model.BaseModel;
import cn.jackro.mvpdemo.model.ModelApiCallback;
import cn.jackro.mvpdemo.model.SubscriberCallback;
import rx.Subscription;

/**
 * LoginModel
 * Created by jack on 2017/3/29.
 */
public class LoginModel extends BaseModel {

    /**
     * 登录
     */
    public Subscription login(String username, String password,
                              ModelApiCallback<User> apiCallback) {
        return toSubscribe(mApiService.login("1", username, password), new SubscriberCallback<>(apiCallback));
    }
}
