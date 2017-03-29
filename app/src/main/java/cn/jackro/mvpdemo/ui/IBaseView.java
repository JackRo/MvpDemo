package cn.jackro.mvpdemo.ui;

/**
 * MVP's V layer, super interface
 * Created by jack on 2016/12/23.
 */
public interface IBaseView<T> {
    void onRxStart();

    void onComplete();

    void onError(Throwable e);

    void onNext(T t);
}
