package cn.jackro.mvpdemo.presenter;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import org.reactivestreams.Subscription;

import cn.jackro.mvpdemo.ui.IBaseView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author JackRo
 */
public class BasePresenter implements LifecycleObserver {

    private CompositeDisposable mCompositeDisposable;

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

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void onError(Throwable e) {
        mIBaseView.stopLoading();
    }

    protected void onComplete() {
        mIBaseView.stopLoading();
    }

    protected void onSubscribe(Subscription subscription) {
        mIBaseView.showLoading();
    }
}
