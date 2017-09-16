package cn.jackro.mvpdemo.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import cn.jackro.mvpdemo.presenter.BasePresenter;

/**
 * ProgressDialogHandler
 */
public class ProgressDialogHandler extends Handler {

    static final int SHOW_PROGRESS_DIALOG = 1;
    static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog mProgressDialog;

    private Context mContext;
    private boolean mCancelable;
    private String mPdMsg;
    private ProgressCancelListener mProgressCancelListener;

    private BasePresenter mPresenter;

    ProgressDialogHandler(Context context, boolean cancelable, ProgressCancelListener
            progressCancelListener, String msg, BasePresenter presenter) {
        super();
        mContext = context;
        mCancelable = cancelable;
        mProgressCancelListener = progressCancelListener;
        mPdMsg = msg;
        mPresenter = presenter;
    }

    /**
     * 初始化mProgressDialog并显示它
     */
    private void initProgressDialog() {
        if (mContext != null && mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage(mPdMsg);
            mProgressDialog.setCanceledOnTouchOutside(mCancelable);

            if (mCancelable) {
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressDialog = null;
                        mProgressCancelListener.onCancelProgress(mPresenter);
                    }
                });
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    /**
     * 关闭mProgressDialog
     */
    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
