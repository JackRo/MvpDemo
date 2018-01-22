package cn.jackro.mvpdemo.presenter.android;

import org.reactivestreams.Subscription;

import java.util.List;

import cn.jackro.mvpdemo.bean.android.AndroidResult;
import cn.jackro.mvpdemo.model.android.AndroidModel;
import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.ui.AndroidView;
import cn.jackro.mvpdemo.util.CheckUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author JackRo
 */
public class AndroidPresenter extends BasePresenter {

    private AndroidModel mAndroidModel;

    private AndroidView mAndroidView;

    public AndroidPresenter(AndroidView androidView) {
        super(androidView);
        mAndroidView = androidView;
        mAndroidModel = new AndroidModel();
    }

    @Override
    protected void initLoadData() {
        super.initLoadData();
        getAndroidResults(1);
    }

    public void getAndroidResults(int page) {
        addDisposable(mAndroidModel.getAndroidResults(page)
                .map(androidResultApiResult -> androidResultApiResult.results)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, this::onComplete, this::onSubscribe));
    }

    private void onNext(List<AndroidResult> androidResultList) {
        if (CheckUtil.isListNotNull(androidResultList)) {
            mAndroidView.showAndroidResult(androidResultList);
        } else {
            mAndroidView.showNoDataMsg();
        }
    }

    @Override
    protected void onError(Throwable e) {
        mAndroidView.stopLoading();
        mAndroidView.onError();
        super.onError(e);
    }

    private void onComplete() {
        mAndroidView.stopLoading();
        mAndroidView.onComplete();
    }

    private void onSubscribe(Subscription subscription) {
        mAndroidView.showLoading();
        subscription.request(Long.MAX_VALUE);
    }
}
