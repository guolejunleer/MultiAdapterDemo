package com.leer.baseadapter.manager;

import android.support.v4.util.SparseArrayCompat;

import com.leer.baseadapter.holder.RViewHolder;
import com.leer.baseadapter.listener.RViewItem;

/**
 * Item样式类型管理
 */
public class RViewItemManager<T> {

    private SparseArrayCompat<RViewItem<T>> mStylesList = new SparseArrayCompat<>();

    /**
     * 获取所有item样式的种类
     *
     * @return 数量
     */
    public int getItemViewStylesCount() {
        return mStylesList.size();
    }

    /**
     * 加入新的样式，末位放置
     *
     * @param item 新样式
     */
    public void addStyle(RViewItem<T> item) {
        if (item == null) return;
        mStylesList.put(mStylesList.size(), item);
    }

    /**
     * 根据数据源和位置返回某item类型的ViewType
     *
     * @param t        数据
     * @param position 位置
     * @return viewType
     */
    public int getItemViewType(T t, int position) {
        for (int i = mStylesList.size() - 1; i >= 0; i--) {
            RViewItem<T> item = mStylesList.valueAt(i);
            if (item.isItemView(t, position)) {//是否为当前显示模式，由外部实现
                return mStylesList.keyAt(i);
            }
        }
        throw new IllegalArgumentException("位置:" + position + ",没有匹配到RViewItem类型");
    }

    /**
     * 根据显示的ViewType返回RViewItem对象(获取Value)
     *
     * @param viewType 布局类型
     * @return RView对象
     */
    public RViewItem getRViewItem(int viewType) {
        return mStylesList.get(viewType);
    }

    //数据源与视图绑定
    public void convert(RViewHolder viewHolder, T entity, int position) {
        for (int i = 0; i < mStylesList.size(); i++) {
            RViewItem<T> item = mStylesList.valueAt(i);
            if (item.isItemView(entity, position)) {
                item.convert(viewHolder, entity, position);//绑定
                return;
            }
        }
        throw new IllegalArgumentException("位置:" + position + ",没有匹配到RViewItem类型");
    }
}
