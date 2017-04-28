package cn.jackro.mvpdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Base RecyclerView.ViewHolder
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {

    private BaseAdapter.OnItemClickListener mOnItemClickListener;
    private BaseAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BaseViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public BaseViewHolder(final View itemView, BaseAdapter.OnItemClickListener onItemClickListener) {
        this(itemView);
        mOnItemClickListener = onItemClickListener;
        itemView.setOnClickListener(this);
    }

    public BaseViewHolder(final View itemView, BaseAdapter.OnItemClickListener onItemClickListener,
                          BaseAdapter.OnItemLongClickListener onItemLongClickListener) {
        this(itemView, onItemClickListener);
        mOnItemLongClickListener = onItemLongClickListener;
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            return true;
        }
        return false;
    }
}