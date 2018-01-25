package cn.jackro.mvpdemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
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

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenterList() {
        super.initPresenterList();
        mAndroidPresenter = new AndroidPresenter(this);
        mPresenterList.add(mAndroidPresenter);
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAndroidResultsXrv.setLayoutManager(layoutManager);
        mAndroidResultsXrv.setItemAnimator(new DefaultItemAnimator());

        mAndroidResultsXrv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mAndroidResultsXrv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mAndroidResultsXrv.setArrowImageView(R.drawable.icon_arrow);

        mAndroidResultsXrv.getDefaultFootView().setLoadingHint("正在加载中...");
        mAndroidResultsXrv.getDefaultFootView().setNoMoreHint("没有更多数据了");

        //mAndroidResultsXrv.setLimitNumberToCallLoadMore(1);

        mAndroidResultsXrv.setLoadingListener(this);

        mAndroidAdapter = new AndroidAdapter();
        mAndroidAdapter.setOnItemClickListener(this);

        mAndroidResultsXrv.setAdapter(mAndroidAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // release memory
        if(mAndroidResultsXrv != null){
            mAndroidResultsXrv.destroy();
            mAndroidResultsXrv = null;
        }
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
        mAndroidPresenter.refreshData(getAndroidResultsCount());
    }

    @Override
    public void onLoadMore() {
        mAndroidPresenter.loadMoreData(getAndroidResultsCount());
    }

    @Override
    protected void errorViewClickToLoadData() {
        mAndroidPresenter.refreshData(getAndroidResultsCount());
    }

    /**
     * 获取页面上AndroidResult的数量
     */
    private int getAndroidResultsCount() {
        return mAndroidAdapter.getItemCount();
    }

    @Override
    public void showErrorView(String msg) {
        showErrorTextView(msg);
    }

    @Override
    public void showErrorToast(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void hideErrorView() {
        hideErrorTextView();
    }

    @Override
    public void showLoading() {
        showLoadingProgressbar();
    }

    @Override
    public void stopLoading() {
        hideLoadingProgressbar();
    }

    @Override
    public void refreshData(List<AndroidResult> androidResultList) {
        mAndroidAdapter.update(androidResultList);
    }

    @Override
    public void loadMoreData(List<AndroidResult> androidResultList) {
        mAndroidAdapter.addAll(androidResultList);
    }

    @Override
    public void refreshComplete() {
        mAndroidResultsXrv.refreshComplete();
    }

    @Override
    public void loadMoreComplete() {
        mAndroidResultsXrv.loadMoreComplete();
    }

    @Override
    public void noMoreData() {
        //mAndroidResultsXrv.setNoMore(true);
        //mAndroidResultsXrv.loadMoreComplete();
        ToastUtil.showShort("没有更多数据了");
    }

}
