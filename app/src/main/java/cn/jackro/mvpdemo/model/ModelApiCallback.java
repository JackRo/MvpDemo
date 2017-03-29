package cn.jackro.mvpdemo.model;

/**
 * api request callback
 * Created by jack on 2016/12/21.
 */
public interface ModelApiCallback<T> {

    /**
     * callback before request start
     */
    void onStart();

    /**
     * request success callback
     *
     * @param model request data model
     */
    void onNext(T model);

    /**
     * request error callback
     *
     * @param e  error exception
     */
    void onError(Throwable e);

    /**
     * request callback
     */
    void onCompleted();
}
