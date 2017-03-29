package cn.jackro.mvpdemo.presenter.login;

import cn.jackro.mvpdemo.bean.login.User;
import cn.jackro.mvpdemo.model.login.LoginModel;
import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.ui.IBaseView;

/**
 * LoginPresenter
 * Created by jack on 2017/3/29.
 */
public class LoginPresenter extends BasePresenter {

    private LoginModel mLoginModel;

    public LoginPresenter(IBaseView loginView) {
        mLoginModel = new LoginModel();
        attachView(loginView);
    }

    public void login(String username, String password) {
        addSubscription(mLoginModel.login(username, password,
                new BaseModelApiCallback<User>()));
    }
}
