package cn.jackro.mvpdemo.ui;

/**
 * MVP的view层接口，用于UI逻辑接口回调
 *
 * @author JackRo
 */
public interface IBaseView {

    void initView();

    void showErrorView(String msg);

    void showErrorToast(String msg);

    void showLoading();

    void stopLoading();
}
