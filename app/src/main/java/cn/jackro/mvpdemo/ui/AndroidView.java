package cn.jackro.mvpdemo.ui;

import java.util.List;

import cn.jackro.mvpdemo.bean.android.AndroidResult;

/**
 * @author JackRo
 */
public interface AndroidView extends IBaseView {

    void refreshData(List<AndroidResult> androidResultList);

    void loadMoreData(List<AndroidResult> androidResultList);

    void refreshComplete();

    void loadMoreComplete();

    void noMoreData();

}
