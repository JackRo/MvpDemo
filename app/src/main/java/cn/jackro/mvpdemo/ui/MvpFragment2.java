package cn.jackro.mvpdemo.ui;

import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.util.ViewUtil;

/**
 * <p>
 * 数据加载出错时使用ErrorView展示
 * <p/>
 */
public abstract class MvpFragment2 extends MvpFragment {

    @BindView(R.id.loading_progressbar)
    ProgressBar mLoadingProgressbar;
    @BindView(R.id.error_text_view)
    TextView mErrorTextView;

    @OnClick(R.id.error_text_view)
    public void onErrorViewClick() {
        hideErrorView();
        errorViewClickToLoadData();
    }

    /**
     * 子类实现该方法以实现点击ErrorView时加载数据，可调用父类方法以在加载数据之前显示加载进度条
     */
    protected void errorViewClickToLoadData() {
        hideErrorView();
        showLoadingProgressbar();
    }

    /**
     * 显示mErrorTextView
     */
    public void showErrorView() {
        ViewUtil.setViewVisible(mErrorTextView);
    }

    /**
     * 设置mErrorTextView显示的错误信息
     */
    public void setErrorMsg(String msg) {
        mErrorTextView.setText(msg);
    }

    /**
     * 隐藏mErrorTextView
     */
    public void hideErrorView() {
        ViewUtil.setViewGone(mErrorTextView);
    }

    /**
     * 显示mLoadingProgressbar
     */
    public void showLoadingProgressbar() {
        ViewUtil.setViewVisible(mLoadingProgressbar);
    }

    /**
     * 隐藏mLoadingProgressbar
     */
    public void hideLoadingProgressbar() {
        ViewUtil.setViewGone(mLoadingProgressbar);
    }
}
