package cn.jackro.mvpdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Base RecyclerView.Adapter
 */
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

    /**
     * get the item of the RecyclerView's DataSource List
     *
     * @param position the position
     * @return the item of the RecyclerView's DataSource List
     */
    public E getItem(int position) {
        return mList.get(position);
    }

    /**
     * get the DataSource List of the RecyclerView
     *
     * @return the DataSource List of the RecyclerView
     */
    public List<E> getList() {
        return mList;
    }

    /**
     * update the DataSource List of the RecyclerView
     *
     * @param list the DataSource List of the RecyclerView
     */
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

    /**
     * the RecyclerView's item click listener
     */
    public interface OnItemClickListener {
        /**
         * item click
         *
         * @param view     the clicked item view of the RecyclerView
         * @param position the clicked item view's position
         */
        void onItemClick(View view, int position);
    }

    /**
     * the RecyclerView's item long click listener
     */
    public interface OnItemLongClickListener {
        /**
         * item long click
         *
         * @param view     the long clicked item view of the RecyclerView
         * @param position the clicked item view's position
         */
        void onItemLongClick(View view, int position);
    }
}
