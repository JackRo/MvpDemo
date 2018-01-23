package cn.jackro.mvpdemo.presenter;


import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import org.reactivestreams.Subscription;

import cn.jackro.mvpdemo.ui.IBaseView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MVP的Presenter层基类，可感知页面(activity or fragment)生命周期以处理页面的data的初始化与view的初始化，管理rxjava订阅对象
 *
 * @author JackRo
 */
public class BasePresenter implements DefaultLifecycleObserver {

    private CompositeDisposable mCompositeDisposable;

    private IBaseView mIBaseView;

    public BasePresenter(IBaseView IBaseView) {
        mIBaseView = IBaseView;
        if (mIBaseView instanceof LifecycleOwner) {
            ((LifecycleOwner) mIBaseView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        initView();
        initData();
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        //取消订阅以取消网络请求
        unsubscribe();
    }

    private void initView() {
        mIBaseView.initView();
    }

    protected void initData() {

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

    /**
     * 取消订阅，避免内存泄露
     */
    public void unsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
        }
    }

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

}
