package cn.jackro.mvpdemo.presenter;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.jackro.mvpdemo.ui.IBaseView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MVP的P层超类，实现attachView和detachView，以管理RxJava的订阅对象
 */
public class BasePresenter implements LifecycleObserver {

    private CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("FieldCanBeLocal")
    private IBaseView mIBaseView;

    public BasePresenter(IBaseView IBaseView) {
        mIBaseView = IBaseView;
        if (mIBaseView instanceof LifecycleOwner) {
            ((LifecycleOwner) mIBaseView).getLifecycle().addObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void initData() {
        initLoadData();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void detach() {
        //取消订阅以取消网络请求
        unsubscribe();
    }

    /**
     * 取消订阅，避免内存泄露
     */
    private void unsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
        }
    }

    protected void initLoadData() {

    }

    /**
     * 添加{@link Disposable}到mCompositeDisposable，以方便取消订阅
     *
     * @param disposable {@link Disposable}实例
     */
    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            mIBaseView.showSocketTimeoutExceptionMsg();
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            mIBaseView.showNetworkConnectExceptionMsg();
        } else {
            mIBaseView.showServerUnknownExceptionMsg();
        }
    }
}
