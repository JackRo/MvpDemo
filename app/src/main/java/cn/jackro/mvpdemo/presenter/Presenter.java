package cn.jackro.mvpdemo.presenter;

/**
 * MVP's P layer interface, manage MVP's V layer
 * Created by jack on 2016/12/22.
 */
interface Presenter<V> {
    /**
     * add view
     *
     * @param view view
     */
    void attachView(V view);

    /**
     * remove view
     */
    void detachView();
}
