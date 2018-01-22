package cn.jackro.mvpdemo.presenter.android;

import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
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

    private int currentPage = 1;

    /**
     * UI上的RecyclerView是否有数据
     */
    private boolean isUIHaveData = false;

    public AndroidPresenter(AndroidView androidView) {
        super(androidView);
        mAndroidView = androidView;
        mAndroidModel = new AndroidModel();
    }

    @Override
    protected void initLoadData() {
        super.initLoadData();
        getAndroidResults(currentPage, isUIHaveData);
    }

    /**
     * 获取AndroidResult的list
     *
     * @param page         页数
     * @param isUIHaveData UI上的RecyclerView是否有数据
     */
    public void getAndroidResults(final int page, boolean isUIHaveData) {
        currentPage = page;
        this.isUIHaveData = isUIHaveData;
        addDisposable(mAndroidModel.getAndroidResults(currentPage)
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
        super.onError(e);
        if (e instanceof SocketTimeoutException) {
            showError("网络连接超时，请检查网络或稍后再试");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            showError("网络错误，请检查网络或稍后再试");
        } else {
            showError("服务器出现未知异常");
        }
        mAndroidView.onError();
    }

    @Override
    protected void onComplete() {
        super.onComplete();
        mAndroidView.onComplete();
    }

    @Override
    protected void onSubscribe(Subscription subscription) {
        super.onSubscribe(subscription);
        subscription.request(Long.MAX_VALUE);
    }

    private void showError(final String msg) {
        if (currentPage == 1 && !isUIHaveData) {
            mAndroidView.showErrorView(msg);
        } else {
            mAndroidView.showErrorToast(msg);
        }
    }
}
