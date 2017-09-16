package cn.jackro.mvpdemo.presenter;

/**
 * MVP的P层接口，管理View层
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
