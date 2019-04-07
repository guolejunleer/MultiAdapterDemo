package com.leer.baseadapter.listener;

import com.leer.baseadapter.holder.RViewHolder;

/**
 * Item布局样式
 */
public interface RViewItem<T> {

    /**
     * 获取item的布局
     *
     * @return 布局id
     */
    int getItemLayout();

    /**
     * 是否开启点击
     *
     * @return 开关
     */
    boolean openClick();

    /**
     * 是否为当前的item布局
     *
     * @param entity   数据
     * @param position 位置
     * @return 结果
     */
    boolean isItemView(T entity, int position);


    /**
     * 将item的控件与需要显示的数据绑定
     *
     * @param holder   holder
     * @param entity   数据
     * @param position 位置
     */
    void convert(RViewHolder holder, T entity, int position);
}
