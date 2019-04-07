package com.leer.baseadapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.leer.baseadapter.base.RViewAdapter;
import com.leer.baseadapter.listener.RViewCreate;

import java.util.List;

public class RVHelper<T> {

    private SwipeRefreshLayout swipeRefresh;//下拉控件
    private SwipeRefreshLayoutHelper swipeRefreshLayoutHelper;//下拉刷新工具类
    private RecyclerView mRecyclerView;//RecyclerView;
    private RViewAdapter<T> mAdapter;//适配器
    private RecyclerView.LayoutManager mLayoutManager;//布局管理器
    private RecyclerView.ItemDecoration mItemDecoration;//条目分隔
    private int startPageNum;//开始页码
    private boolean isSupportPaging;//是否支持加载更多
    private SwipeRefreshLayoutHelper.SwipeRefreshListener listener;//下拉刷新，加载更多
    private int currentPageNum;//当前页数

    private RVHelper(Builder<T> builder) {
        this.swipeRefresh = builder.create.createSwipeRefreshLayout();
        this.mRecyclerView = builder.create.createRecyclerView();
        this.mAdapter = builder.create.createRViewAdapter();
        this.mLayoutManager = builder.create.createLayoutManager();
        this.mItemDecoration = builder.create.createItemDecoration();
        this.startPageNum = builder.create.startPageNum();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNum;
        int[] colorRes = builder.create.colorRes();
        Log.i("test001", "RVHelper.class-->>swipeRefresh:" + swipeRefresh);
        if (swipeRefresh != null) {
            if (colorRes != null) {
                swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper
                        .createSwipeRefreshLayoutHelper(swipeRefresh, colorRes);
            } else {
                swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper
                        .createSwipeRefreshLayoutHelper(swipeRefresh);
            }
        }
        init();
    }

    private void init() {
        //RecyclerView初始化
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (mItemDecoration != null) mRecyclerView.addItemDecoration(mItemDecoration);

        //下拉刷新操作
        Log.e("test001", "RVHelper.class-->>swipeRefreshLayoutHelper:" + swipeRefreshLayoutHelper);
        if (swipeRefreshLayoutHelper != null) {
            swipeRefreshLayoutHelper.setSwipeRefreshListener(new SwipeRefreshLayoutHelper
                    .SwipeRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.i("test001", "RVHelper.class-->>onRefresh:" + swipeRefreshLayoutHelper);
                    //重新加载
                    currentPageNum = startPageNum;
                    dismissSwipeRefresh();
                    if (listener != null) listener.onRefresh();
                }
            });
        }
    }

    private void dismissSwipeRefresh() {
        if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    public void notifyAdapterDataSetChanged(List<T> dataList) {
        //首次加载或者下拉刷新都要重置页码
        if (currentPageNum == startPageNum) {
            mAdapter.updateDataList(dataList);
        } else {
            mAdapter.addDataList(dataList);
        }
        mRecyclerView.setAdapter(mAdapter);

        if (isSupportPaging) {
            Log.e("leer", "加载更多......");
        }
    }

    public static class Builder<T> {
        private RViewCreate<T> create;
        private SwipeRefreshLayoutHelper.SwipeRefreshListener listener;

        public Builder(RViewCreate<T> create, SwipeRefreshLayoutHelper.SwipeRefreshListener listener) {
            this.create = create;
            this.listener = listener;
        }

        public RVHelper build() {
            //参数校验
            return new RVHelper(this);
        }
    }
}
