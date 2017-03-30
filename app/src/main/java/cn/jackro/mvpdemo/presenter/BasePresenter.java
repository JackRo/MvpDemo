package cn.jackro.mvpdemo.presenter;


import cn.jackro.mvpdemo.model.ModelApiCallback;
import cn.jackro.mvpdemo.ui.IBaseView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP的P层超类，实现attachView和detachView，以管理RxJava的订阅对象
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
        //取消订阅以取消网络请求
        unsubscribe();
    }

    /**
     * 取消订阅，避免内存泄露
     */
    private void unsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

    /**
     * 添加{@link Subscription}到mCompositeSubscription，以方便取消订阅
     *
     * @param subscription {@link Subscription}实例
     */
    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 传递{@link ModelApiCallback}的回调给View层
     *
     * @param <T> 网络请求成功json解析的java bean类型
     */
    public class BaseModelApiCallback<T> implements ModelApiCallback<T> {

        @Override
        public void onStart() {
            if (mvpView != null) {
                mvpView.onRxStart();
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onNext(T model) {
            if (mvpView != null) {
                mvpView.onNext(model);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (mvpView != null) {
                mvpView.onError(e);
            }
        }

        @Override
        public void onCompleted() {
            if (mvpView != null) {
                mvpView.onComplete();
            }
        }
    }
}
