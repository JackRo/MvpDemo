package cn.jackro.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.util.CheckUtil;
import cn.jackro.mvpdemo.util.ToastUtil;


/**
 * <p>
 * MvpFragment，持有{@link BasePresenter}的List，用于处理网络请求的业务逻辑，不同的{@link BasePresenter}
 * 实例处理不同的网络请求业务。
 * <p/>
 */
public abstract class MvpFragment extends BaseFragment implements ProgressCancelListener {

    protected List<BasePresenter> mPresenterList;

    protected boolean mIsVisibleToUser;

    protected boolean mIsViewPrepared;

    protected ProgressDialogHandler mProgressDialogHandler;

    @BindView(R.id.loading_progressbar)
    ProgressBar mLoadingProgressbar;
    @BindView(R.id.error_text_view)
    TextView mErrorTextView;

    @BindString(R.string.socket_timeout_exception_str)
    String mSocketTimeOutExceptionStr;
    @BindString(R.string.connect_exception_str)
    String mConnectExceptionStr;
    @BindString(R.string.server_unknown_exception_str)
    String mServerUnknownExceptionStr;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initPresenterList();
        if (getUserVisibleHint()) {
            mIsVisibleToUser = true;
            onVisible();
        } else {
            mIsVisibleToUser = false;
            onInvisible();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (CheckUtil.isListNotNull(mPresenterList)) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.detachView();
            }
        }
    }

    protected abstract void lazyLoad();

    public void onVisible() {
        lazyLoad();
    }

    public void onInvisible() {

    }

    /**
     * <p>初始化mPresenterList</p>
     * <p>
     * <p>子类覆盖这个方法必须调用父类的方法以初始化mPresenterList</p>
     */
    protected void initPresenterList() {
        mPresenterList = new ArrayList<>();
    }

    /**
     * <p>
     * view层的抽象类，在这里统一处理错误(错误以Toast形式显示)，处理ProgressDialog的显示和关闭。
     * 你可以在{@link MvpActivity}的子类Activity实现这个抽象类，
     * 并且你可以做自己的Rx回调实现，甚至覆盖这个抽象类的实现都可以。
     * <p/>
     *
     * @param <T> 网络请求解析的java bean类型
     */
    abstract class BaseView2<T> implements IBaseView<T> {

        public abstract BasePresenter getPresenter();

        public abstract String getProgressDialogMsg();

        @Override
        public void onRxStart() {
            showProgressDialog(getProgressDialogMsg(), getPresenter());
        }

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onComplete() {
            dismissProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            dismissProgressDialog();
            if (e instanceof SocketTimeoutException) {
                ToastUtil.showShort(mSocketTimeOutExceptionStr);
            } else if (e instanceof ConnectException) {
                ToastUtil.showShort(mConnectExceptionStr);
            } else {
                ToastUtil.showShort(mServerUnknownExceptionStr);
            }
        }
    }

    /**
     * <p>
     * view层的抽象类，在这里统一处理错误(错误以ErrorView的形式显示)，处理ProgressBar的显示和隐藏。
     * 你可以在{@link MvpActivity}的子类Activity实现这个抽象类，
     * 并且你可以做自己的Rx回调实现，甚至覆盖这个抽象类的实现都可以。
     * <p/>
     *
     * @param <T> 网络请求解析的java bean类型
     */
    abstract class BaseView3<T> implements IBaseView<T> {

        @Override
        public void onRxStart() {
            mLoadingProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onComplete() {
            mLoadingProgressbar.setVisibility(View.GONE);
        }

        @Override
        public void onError(Throwable e) {
            mLoadingProgressbar.setVisibility(View.GONE);
            mErrorTextView.setVisibility(View.VISIBLE);
            if (e instanceof SocketTimeoutException) {
                mErrorTextView.setText(mSocketTimeOutExceptionStr);
            } else if (e instanceof ConnectException) {
                mErrorTextView.setText(mConnectExceptionStr);
            } else {
                mErrorTextView.setText(mServerUnknownExceptionStr);
            }
        }
    }

    /**
     * 显示ProgressDialog
     *
     * @param msg       设置ProgressDialog的message
     * @param presenter 传递BasePresenter实例给ProgressDialogHandler以实现手动取消ProgressDialog取消网络请求
     */
    protected void showProgressDialog(String msg, BasePresenter presenter) {
        if (mProgressDialogHandler == null) {
            mProgressDialogHandler = new ProgressDialogHandler(mActivity, true, this, msg, presenter);
        }
        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
    }

    /**
     * 关闭ProgressDialog
     */
    protected void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onCancelProgress(BasePresenter presenter) {
        mProgressDialogHandler = null;
        //调用这个方法以取消网络请求
        presenter.detachView();
    }
}
