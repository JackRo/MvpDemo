package cn.jackro.mvpdemo.ui;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

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

    /**
     * <p>
     * view层的抽象类，在这里统一处理错误(错误以ErrorView的形式显示)，处理ProgressBar的显示和隐藏。
     * 你可以在{@link MvpActivity2}的子类Activity实现这个抽象类，
     * 并且你可以做自己的Rx回调实现，甚至覆盖这个抽象类的实现都可以。
     * <p/>
     *
     * @param <T> 网络请求解析的java bean类型
     */
    public abstract class BaseView2<T> implements IBaseView<T> {

        @Override
        public void onRxStart() {
            showLoadingProgressbar();
        }

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onComplete() {
            hideLoadingProgressbar();
        }

        @Override
        public void onError(Throwable e) {
            hideLoadingProgressbar();
            showErrorView();
            if (e instanceof SocketTimeoutException) {
                setErrorMsg(mSocketTimeOutExceptionStr);
            } else if (e instanceof ConnectException) {
                setErrorMsg(mConnectExceptionStr);
            } else {
                errorViewShowServerUnknownException();
            }
        }
    }

    @OnClick(R.id.error_text_view)
    public void onErrorViewClick() {
        hideErrorView();
        loadData();
    }

    /**
     * 点击ErrorView时重新加载数据
     */
    protected void loadData() {

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
