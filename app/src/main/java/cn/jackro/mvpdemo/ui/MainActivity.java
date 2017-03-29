package cn.jackro.mvpdemo.ui;

import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.bean.login.User;
import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.presenter.login.LoginPresenter;

public class MainActivity extends MvpActivity {

    @BindView(R.id.username_edit_text)
    EditText mUsernameEditText;
    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initPresenterList() {
        super.initPresenterList();
        mLoginPresenter = new LoginPresenter(new LoginView());
        mPresenterList.add(mLoginPresenter);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        mLoginPresenter.login(mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString());
    }

    private class LoginView extends BaseView<User> {

        @Override
        public BasePresenter getPresenter() {
            return mLoginPresenter;
        }

        @Override
        public String getProgressDialogMsg() {
            return "正在登录中。。。";
        }

        @Override
        public void onNext(User user) {
            super.onNext(user);
            Log.e("User", user.msg + user.code);
        }
    }
}
