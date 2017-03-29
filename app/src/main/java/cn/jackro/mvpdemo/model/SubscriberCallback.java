package cn.jackro.mvpdemo.model;

import rx.Subscriber;

/**
 * Subscribe callback
 * Created by jack on 2016/12/21.
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
