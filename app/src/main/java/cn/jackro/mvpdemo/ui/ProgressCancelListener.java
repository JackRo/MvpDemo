package cn.jackro.mvpdemo.ui;

import cn.jackro.mvpdemo.presenter.BasePresenter;

/**
 * 手动取消ProgressDialog回调
 */
interface ProgressCancelListener {

    /**
     * 取消ProgressDialog回调方法
     *
     * @param presenter 用于取消网络请求的{@link BasePresenter}实例
     */
    void onCancelProgress(BasePresenter presenter);
}
