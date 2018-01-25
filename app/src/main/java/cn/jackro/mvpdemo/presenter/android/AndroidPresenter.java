package cn.jackro.mvpdemo.presenter.android;

import android.util.Log;

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

    private boolean isRefresh = false;

    private boolean isLoadMore = false;

    /**
     * 页面上AndroidResult的数量
     */
    private int count = 0;

    public AndroidPresenter(AndroidView androidView) {
        super(androidView);
        mAndroidView = androidView;
        mAndroidModel = new AndroidModel();
    }

    @Override
    protected void initData() {
        super.initData();
        refreshData(count);
    }

    public void refreshData(int androidResultSize) {
        isRefresh = true;
        count = androidResultSize;
        currentPage = 1;
        getAndroidResults();
    }

    public void loadMoreData(int androidResultSize) {
        isLoadMore = true;
        count = androidResultSize;

        Log.e("loadMore", "loadMore: " + currentPage + "::" + count);

        if (canLoadMore()) {
            currentPage++;
            getAndroidResults();
        } else {
            mAndroidView.noMoreData();
        }
    }

    private boolean canLoadMore() {
        return currentPage * 20 <= count;
    }

    /**
     * 获取AndroidResult的list
     */
    private void getAndroidResults() {
        addDisposable(mAndroidModel.getAndroidResults(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, this::onComplete, this::onSubscribe));
    }

    private void onNext(List<AndroidResult> androidResultList) {
        if (CheckUtil.isListNotNull(androidResultList)) {
            if (isRefresh) {
                mAndroidView.refreshData(androidResultList);
            } else if (isLoadMore) {
                mAndroidView.loadMoreData(androidResultList);
            } else {
                mAndroidView.refreshData(androidResultList);
            }
        } else {
            if (currentPage == 1 && count <= 0) {
                mAndroidView.showErrorView("没有加载到数据");
            } else {
                mAndroidView.noMoreData();
            }
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
            showError(e.getMessage());
        }
        refreshOrLoadMoreComplete();
    }

    @Override
    protected void onComplete() {
        super.onComplete();
        refreshOrLoadMoreComplete();
    }

    @Override
    protected void onSubscribe(Subscription subscription) {
        if (currentPage == 1 && count <= 0 && isRefresh) {
            mAndroidView.hideErrorView();
            super.onSubscribe(subscription);
        }
        subscription.request(Long.MAX_VALUE);
    }

    private void showError(final String msg) {
        if (currentPage == 1 && count <= 0) {
            mAndroidView.showErrorView(msg);
        } else {
            mAndroidView.showErrorToast(msg);
        }
    }

    private void refreshOrLoadMoreComplete() {
        if (isRefresh) {
            mAndroidView.refreshComplete();
            isRefresh = false;
        }
        if (isLoadMore) {
            mAndroidView.loadMoreComplete();
            isLoadMore = false;
        }
    }
}
