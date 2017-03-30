package cn.jackro.mvpdemo.presenter;


import android.util.Log;

import cn.jackro.mvpdemo.model.ModelApiCallback;
import cn.jackro.mvpdemo.ui.IBaseView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP's P layer's super class, implement view's add and remove,
 * RxJava's subscribe management
 * Created by jack on 2016/12/22.
 */
public class BasePresenter implements Presenter<IBaseView> {

    private IBaseView mvpView;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(IBaseView view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        Log.e(getClass().toString(), "detachView");
        unsubscribe();
    }

    /**
     * unsubscribe all, avoid memory leak
     */
    private void unsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            Log.e(getClass().toString(), "unsubscribe");
            mCompositeSubscription.clear();
        }
    }

    /**
     * add the subscription to the CompositeSubscription，convenient for unsubscribe
     *
     * @param subscription {@link Subscription}
     */
    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public class BaseModelApiCallback<T> implements ModelApiCallback<T> {

        @Override
        public void onStart() {
            mvpView.onRxStart();
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onNext(T model) {
            mvpView.onNext(model);
        }

        @Override
        public void onError(Throwable e) {
            mvpView.onError(e);
        }

        @Override
        public void onCompleted() {
            mvpView.onComplete();
        }
    }
}
