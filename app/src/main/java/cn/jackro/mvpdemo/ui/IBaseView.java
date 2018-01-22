package cn.jackro.mvpdemo.ui;

/**
 * MVP的view层接口，用于Rx接口回调
 */
public interface IBaseView {

    void showSocketTimeoutExceptionMsg();

    void showNetworkConnectExceptionMsg();

    void showServerUnknownExceptionMsg();
}
