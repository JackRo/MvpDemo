package cn.jackro.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.util.CheckUtil;

/**
 * <p>
 * 持有{@link BasePresenter}的List，用于处理业务逻辑，不同的{@link BasePresenter}
 * 实例处理不同的业务。
 * <p/>
 */
public abstract class MvpActivity extends BaseActivity {

    protected List<BasePresenter> mPresenterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenterList();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CheckUtil.isListNotNull(mPresenterList)) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.detach();
            }
        }
    }

    /**
     * <p>初始化mPresenterList</p>
     * <p>
     * <p>子类覆盖这个方法必须调用父类的方法以初始化mPresenterList</p>
     */
    protected void initPresenterList() {
        mPresenterList = new ArrayList<>();
    }
}

