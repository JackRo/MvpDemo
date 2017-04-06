package cn.jackro.mvpdemo.ui;

import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.jackro.mvpdemo.presenter.BasePresenter;
import cn.jackro.mvpdemo.util.ToastUtil;

/**
 * <p>
 * 数据加载出错时使用@{@link Toast}展示
 * </p>
 */
public abstract class MvpFragment1 extends MvpFragment implements ProgressCancelListener {

    protected ProgressDialogHandler mProgressDialogHandler;

    /**
     * <p>
     * view层的抽象类，在这里统一处理错误(错误以Toast形式显示)，处理ProgressDialog的显示和关闭。
     * 你可以在{@link MvpFragment1}的子类Fragment实现这个抽象类，
     * 并且你可以做自己的Rx回调实现，甚至覆盖这个抽象类的实现都可以。
     * <p/>
     *
     * @param <T> 网络请求解析的java bean类型
     */
    public abstract class BaseView1<T> implements IBaseView<T> {

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
            } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
                ToastUtil.showShort(mConnectExceptionStr);
            } else {
                toastShowServerUnknownException();
            }
        }
    }

    /**
     * Toast显示服务器出现未知异常的信息
     */
    public void toastShowServerUnknownException() {
        ToastUtil.showShort(mServerUnknownExceptionStr);
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
        //调用这个方法以取消网络请求
        presenter.detachView();
    }
}
