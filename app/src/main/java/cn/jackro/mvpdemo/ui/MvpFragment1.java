package cn.jackro.mvpdemo.ui;

import android.widget.Toast;

import cn.jackro.mvpdemo.presenter.BasePresenter;

/**
 * <p>
 * 数据加载出错时使用@{@link Toast}展示
 * </p>
 */
@SuppressWarnings("unused")
public abstract class MvpFragment1 extends MvpFragment implements ProgressCancelListener {

    protected ProgressDialogHandler mProgressDialogHandler;

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
        presenter.unsubscribe();
    }
}
