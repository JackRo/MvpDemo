package cn.jackro.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.util.CheckUtil;

/**
 * <p>
 * 持有{@link BasePresenter}的List，用于处理网络请求的业务逻辑，不同的{@link BasePresenter}
 * 实例处理不同的网络请求业务。
 * <p/>
 * <p>
 * 实现Fragment的数据懒加载
 * </p>
 */
public abstract class MvpFragment extends BaseFragment {

    protected List<BasePresenter> mPresenterList;

    protected boolean mIsVisibleToUser;

    protected boolean mIsViewPrepared;

    @BindString(R.string.socket_timeout_exception_str)
    String mSocketTimeOutExceptionStr;
    @BindString(R.string.connect_exception_str)
    String mConnectExceptionStr;
    @BindString(R.string.server_unknown_exception_str)
    String mServerUnknownExceptionStr;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initPresenterList();
        if (getUserVisibleHint()) {
            mIsVisibleToUser = true;
            onVisible();
        } else {
            mIsVisibleToUser = false;
            onInvisible();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (CheckUtil.isListNotNull(mPresenterList)) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.detachView();
            }
        }
    }

    protected abstract void lazyLoad();

    public void onVisible() {
        lazyLoad();
    }

    public void onInvisible() {

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
