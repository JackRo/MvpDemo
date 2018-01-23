package cn.jackro.mvpdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<E> mList;

    protected OnItemClickListener mOnItemClickListener;

    protected OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public E getItem(int position) {
        return mList.get(position);
    }

    public List<E> getList() {
        return mList;
    }

    public void update(List<E> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void add(E e) {
        mList.add(e);
        notifyDataSetChanged();
    }

    public void addAll(List<E> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(E e) {
        mList.remove(e);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll(List<E> e) {
        mList.removeAll(e);
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (getList() != null) {
            mList.removeAll(getList());
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {

        void onItemLongClick(View view, int position);
    }
}
