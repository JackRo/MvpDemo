package cn.jackro.mvpdemo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import cn.jackro.mvpdemo.R;
import cn.jackro.mvpdemo.bean.android.AndroidResult;

/**
 * @author JackRo
 */
public class AndroidAdapter extends BaseAdapter<AndroidResult> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_androidresult, parent, false);
        return new AndroidResultViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        AndroidResult androidResult = mList.get(position);
        AndroidResultViewHolder androidResultViewHolder = (AndroidResultViewHolder) holder;
        androidResultViewHolder.mAndroidResultDesc.setText(androidResult.getDesc());
    }

    static class AndroidResultViewHolder extends BaseViewHolder {

        @BindView(R.id.android_result_desc)
        TextView mAndroidResultDesc;

        AndroidResultViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }
    }
}
