package cn.jackro.mvpdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import butterknife.BindString;
import butterknife.ButterKnife;
import cn.jackro.mvpdemo.R;

/**
 * BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Intent mExtraDataIntent;

    protected Activity mActivity;

    protected String mApplicationPackageName;

    @BindString(R.string.dialog_title_warm_tip)
    String mDialogTitleWarmTip;
    @BindString(R.string.dialog_go_to_app_settings)
    String mDialogGoToAppSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        mApplicationPackageName = getPackageName();
        mActivity = this;
        if (getIntent() != null) {
            mExtraDataIntent = getIntent();
        }
    }

    protected abstract int getLayoutResourceId();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //统一处理点击空白处关闭软键盘
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService
                        (INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 显示app必须的权限提醒对话框
     *
     * @param desc 必须权限的说明
     */
    @SuppressWarnings("unused")
    protected void showAppMustPermissionTipDialog(String desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(mDialogTitleWarmTip);
        builder.setMessage(desc);
        builder.setPositiveButton(mDialogGoToAppSettings, (dialog, which) -> openAppSettingsDetails());
        builder.show();
    }

    /**
     * 打开应用设置详情页面
     */
    private void openAppSettingsDetails() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.parse("package:" + mApplicationPackageName);
        intent.setData(uri);
        startActivity(intent);
    }
}
