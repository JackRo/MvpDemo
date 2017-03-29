package cn.jackro.mvpdemo;

import org.junit.Test;

import cn.jackro.mvpdemo.bean.login.User;
import cn.jackro.mvpdemo.model.ModelApiCallback;
import cn.jackro.mvpdemo.model.login.LoginModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testBaseModel() throws Exception {
        LoginModel loginModel = new LoginModel();
        loginModel.login("", "", new ModelApiCallback<User>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onNext(User model) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}