package cn.jackro.mvpdemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.bean.android.AndroidResult;
import cn.jackro.mvpdemo.presenter.android.AndroidPresenter;
import cn.jackro.mvpdemo.util.ToastUtil;

public class MainActivity extends MvpActivity2 implements AndroidView, BaseAdapter.OnItemClickListener,
        XRecyclerView.LoadingListener {

    @BindView(R.id.android_results_xrv)
    XRecyclerView mAndroidResultsXrv;

    private AndroidPresenter mAndroidPresenter;

    private AndroidAdapter mAndroidAdapter;

    private int currentPageIndex = 1;

    private boolean isRefresh = false;

    private boolean isLoadMore = false;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAndroidResultsXrv.setLayoutManager(layoutManager);
        mAndroidResultsXrv.setItemAnimator(new DefaultItemAnimator());
        mAndroidAdapter = new AndroidAdapter();
        mAndroidAdapter.setOnItemClickListener(this);
        mAndroidResultsXrv.setLoadingListener(this);
        mAndroidResultsXrv.setAdapter(mAndroidAdapter);
        mAndroidResultsXrv.setLimitNumberToCallLoadMore(2);
    }

    @Override
    protected void initPresenterList() {
        super.initPresenterList();
        mAndroidPresenter = new AndroidPresenter(this);
        mPresenterList.add(mAndroidPresenter);
    }

    @Override
    public void showSocketTimeoutExceptionMsg() {
        setErrorMsg(mSocketTimeOutExceptionStr);
    }

    @Override
    public void showNetworkConnectExceptionMsg() {
        setErrorMsg(mConnectExceptionStr);
    }

    @Override
    public void showServerUnknownExceptionMsg() {
        errorViewShowServerUnknownException();
    }

    @Override
    public void showAndroidResult(List<AndroidResult> androidResultList) {
        if (isRefresh) {
            mAndroidAdapter.update(androidResultList);
        } else if (isLoadMore) {
            mAndroidAdapter.addAll(androidResultList);
        } else {
            mAndroidAdapter.update(androidResultList);
        }
    }

    @Override
    public void showNoDataMsg() {
        if (currentPageIndex == 1) {
            mAndroidAdapter.clearAll();
            showErrorView();
            setErrorMsg("没有一条数据");
        } else {
            ToastUtil.showShort("没有更多数据了");
        }
    }

    @Override
    public void onError() {
        showErrorView();
        if (isRefresh) {
            mAndroidResultsXrv.refreshComplete();
            isRefresh = false;
        }
        if (isLoadMore) {
            mAndroidResultsXrv.loadMoreComplete();
            isLoadMore = false;
        }
    }

    @Override
    public void onComplete() {
        if (isRefresh) {
            mAndroidResultsXrv.refreshComplete();
            isRefresh = false;
        }
        if (isLoadMore) {
            mAndroidResultsXrv.loadMoreComplete();
            isLoadMore = false;
        }
    }

    @Override
    public void showLoading() {
        if (isRefresh || isLoadMore) {
            return;
        }
        showLoadingProgressbar();
    }

    @Override
    public void stopLoading() {
        hideLoadingProgressbar();
    }

    @Override
    public void onItemClick(View view, int position) {
        AndroidResult androidResult = mAndroidAdapter.getList().get(position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(androidResult.getUrl()));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        currentPageIndex = 1;
        if (mErrorTextView.getVisibility() == View.VISIBLE) {
            hideErrorView();
        }
        mAndroidPresenter.getAndroidResults(currentPageIndex);
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        if (currentPageIndex * 20 > mAndroidAdapter.getItemCount()) {
            mAndroidResultsXrv.loadMoreComplete();
            ToastUtil.showShort("没有更多数据了");
            return;
        }
        currentPageIndex++;
        mAndroidPresenter.getAndroidResults(currentPageIndex);
    }

    @Override
    protected void errorViewClickToLoadData() {
        super.errorViewClickToLoadData();
        currentPageIndex = 1;
        mAndroidPresenter.getAndroidResults(currentPageIndex);
    }
}
