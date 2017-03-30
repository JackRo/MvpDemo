package cn.jackro.mvpdemo.model;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * BaseModel
 */
public class BaseModel {

    protected ApiService mApiService;

    public BaseModel() {
        Api.setBaseUrl("xxx");
        mApiService = Api.getInstance().getApiService();
    }

    /**
     * subscribe the observable to the subscriber
     *
     * @param observable the observable
     * @param subscriber the subscriber
     * @param <T>        the type of data that the observable emits
     * @return return the subscription
     */
    protected <T> Subscription toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
