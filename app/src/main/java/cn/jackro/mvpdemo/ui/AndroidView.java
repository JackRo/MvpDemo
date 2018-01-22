package cn.jackro.mvpdemo.ui;

import java.util.List;

import cn.jackro.mvpdemo.bean.android.AndroidResult;

/**
 * @author JackRo
 */
public interface AndroidView extends IBaseView {

    void showAndroidResult(List<AndroidResult> androidResultList);

    void showNoDataMsg();

    void onError();

    void onComplete();

    void showLoading();

    void stopLoading();
}
