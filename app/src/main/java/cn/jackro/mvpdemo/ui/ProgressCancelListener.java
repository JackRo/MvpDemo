package cn.jackro.mvpdemo.ui;

import cn.jackro.mvpdemo.presenter.BasePresenter;

/**
 * ProgressDialog关闭时回调
 */
public interface ProgressCancelListener {

    /**
     * ProgressDialog关闭时回调此方法
     *
     * @param presenter 用于取消网络请求的{@link BasePresenter}实例
     */
    void onCancelProgress(BasePresenter presenter);
}
