package cn.jackro.mvpdemo.ui;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jackro.mvpdemo.R;

/**
 * <p>
 * 数据加载出错时使用ErrorView展示
 * </p>
 */
public abstract class MvpActivity2 extends MvpActivity {

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
        showLoadingProgressbar();
    }

    /**
     * 显示mErrorTextView
     */
    public void showErrorView() {
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置mErrorTextView显示的错误信息
     */
    public void setErrorMsg(String msg) {
        mErrorTextView.setText(msg);
    }

    /**
     * ErrorView显示服务器出现未知异常的信息
     */
    public void errorViewShowServerUnknownException() {
        setErrorMsg(mServerUnknownExceptionStr);
    }

    /**
     * 隐藏mErrorTextView
     */
    public void hideErrorView() {
        mErrorTextView.setVisibility(View.GONE);
    }

    /**
     * 显示mLoadingProgressbar
     */
    public void showLoadingProgressbar() {
        mLoadingProgressbar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏mLoadingProgressbar
     */
    public void hideLoadingProgressbar() {
        mLoadingProgressbar.setVisibility(View.GONE);
    }
}
