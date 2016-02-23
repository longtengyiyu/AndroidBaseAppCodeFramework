package in.guanjia.demo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import in.guanjia.demo.listener.OnRecyclerViewClickItemListener;

/**
 * Description:适配器基类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/11/10 0009
 * Modification  History:
 * Date         	Author        	 Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/11/10 0009        Oscar            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final String TAG = getClass().getSimpleName();
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;
    protected OnRecyclerViewClickItemListener onRecyclerViewClickItemListener = null;

    public ArrayList<T> mDataList = new ArrayList<>();
    protected T t;

    public BaseRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(T t) {
        this.t = t;
    }

    public void setOnRecyclerViewClickItemListener(OnRecyclerViewClickItemListener onRecyclerViewClickItemListener) {
        this.onRecyclerViewClickItemListener = onRecyclerViewClickItemListener;
    }

    private int headCount = 0;

    public void setHeadCount(int headCount){
        this.headCount = headCount;
    }
    public int getHeadCount(){
        return headCount;
    }
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size() + headCount;
    }

    /**
     * 移除某一条记录
     *
     * @param position 移除数据的position
     */
    public void removeItem(int position) {
        if (position < mDataList.size()) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItem(T data, int position) {
        if (position <= mDataList.size()) {
            mDataList.add(position, data);
            notifyItemInserted(position);
        }
    }

    /**
     * 移除所有记录
     */
    public void clearItems() {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * 批量添加记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItems(List<T> data, int position) {
        //position <= mDataList.size() &&
        if (data != null && data.size() > 0) {
            mDataList.addAll(data);
            notifyItemRangeChanged(position, data.size());
        }
    }

    public void addItems(List<T> data) {
        if ( data != null && data.size() > 0) {
            mDataList.clear();
            mDataList.addAll(data);
            notifyDataSetChanged();
        }
    }

}
