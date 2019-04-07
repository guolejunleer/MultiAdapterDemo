package com.leer.baseadapter.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.leer.baseadapter.base.RViewAdapter;

public interface RViewCreate<T> {

    /**
     * 创建下拉控件
     *
     * @return 下拉控件
     */
    SwipeRefreshLayout createSwipeRefreshLayout();

    /**
     * 下拉颜色
     *
     * @return 下拉颜色
     */
    int[] colorRes();

    /**
     * 创建RecyclerView
     *
     * @return RecyclerView
     */
    RecyclerView createRecyclerView();

    /**
     * 创建适配器
     *
     * @return 适配器
     */
    RViewAdapter<T> createRViewAdapter();

    /**
     * 创建管理器
     *
     * @return 管理器
     */
    RecyclerView.LayoutManager createLayoutManager();

    /**
     * 创建分隔线
     *
     * @return 分隔线
     */
    RecyclerView.ItemDecoration createItemDecoration();

    /**
     * 设置开始页码
     *
     * @return 开始页码
     */
    int startPageNum();

    /**
     * 是否支持分页
     *
     * @return 支持分页
     */
    boolean isSupportPaging();

}
