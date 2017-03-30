package cn.jackro.mvpdemo.model;

/**
 * 用于传递RxJava的回调给Presenter层的接口，对应RxJava的各种回调
 *
 * @param <T> 网络请求成功json解析的java bean类型
 */
public interface ModelApiCallback<T> {

    /**
     * 对应RxJava的onStart回调
     */
    void onStart();

    /**
     * 对应RxJava的onNext回调
     */
    void onNext(T model);

    /**
     * 对应RxJava的onError回调
     */
    void onError(Throwable e);

    /**
     * 对应RxJava的onCompleted回调
     */
    void onCompleted();
}
