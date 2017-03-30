package cn.jackro.mvpdemo.ui;

/**
 * MVP的view层接口，用于Rx接口回调
 */
public interface IBaseView<T> {
    void onRxStart();

    void onComplete();

    void onError(Throwable e);

    void onNext(T t);
}
