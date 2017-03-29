package cn.jackro.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.util.CheckUtil;
import cn.jackro.mvpdemo.util.ToastUtil;

/**
 * MVP's Activity, hold the instance of Presenter that handle business logic
 * Created by jack on 2016/12/22.
 */
public abstract class MvpActivity extends BaseActivity implements ProgressCancelListener {

    protected List<BasePresenter> mPresenterList;

    protected ProgressDialogHandler mProgressDialogHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenterList();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CheckUtil.isListNotNull(mPresenterList)) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.detachView();
            }
        }
    }

    protected void initPresenterList() {
        mPresenterList = new ArrayList<>();
    }

    abstract class BaseView<T> implements IBaseView<T> {

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
                ToastUtil.showShort("网络连接超时，请检查网络或稍后再试");
            } else if (e instanceof ConnectException) {
                ToastUtil.showShort("网络错误，请检查网络或稍后再试");
            } else {
                ToastUtil.showShort(e.getMessage());
            }
        }
    }

    /**
     * show ProgressDialog
     *
     * @param msg       the message of ProgressDialog
     * @param presenter the Presenter of handle ui logistic and business logistic. because it handle slowly , so we
     *                  need to show a ProgressDialog
     */
    protected void showProgressDialog(String msg, BasePresenter presenter) {
        if (mProgressDialogHandler == null) {
            mProgressDialogHandler = new ProgressDialogHandler(mActivity, true, this, msg, presenter);
        }
        Log.e(getClass().toString(), mProgressDialogHandler.toString());

        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
    }

    /**
     * dismiss ProgressDialog
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
        presenter.detachView();
    }
}
