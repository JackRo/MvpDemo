package cn.jackro.mvpdemo.model;

import rx.Subscriber;

/**
 * RxJava的{@link Subscriber}回调，传递RxJava回调给{@link ModelApiCallback}，让
 * {@link ModelApiCallback}把RxJava的回调传递给Presenter层
 *
 * @param <T> 网络请求成功json解析的java bean类型
 */
public class SubscriberCallback<T> extends Subscriber<T> {

    private ModelApiCallback<T> mModelApiCallback;

    public SubscriberCallback(ModelApiCallback<T> modelApiCallback) {
        mModelApiCallback = modelApiCallback;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mModelApiCallback != null) {
            mModelApiCallback.onStart();
        }
    }

    @Override
    public void onCompleted() {
        if (mModelApiCallback != null) {
            mModelApiCallback.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mModelApiCallback != null) {
            //when data flow occurred error, pass the error to Android UI layer to handle it,
            //keep model layer clean as much as possible
            mModelApiCallback.onError(e);
        }
    }

    @Override
    public void onNext(T t) {
        if (mModelApiCallback != null) {
            mModelApiCallback.onNext(t);
        }
    }
}
