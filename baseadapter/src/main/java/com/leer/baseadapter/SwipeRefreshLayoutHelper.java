package com.leer.baseadapter;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutHelper {

    public interface SwipeRefreshListener {
        void onRefresh();
    }

    private SwipeRefreshLayout mSwipeRefresh;
    private SwipeRefreshListener mSwipeRefreshListener;

    static SwipeRefreshLayoutHelper createSwipeRefreshLayoutHelper(SwipeRefreshLayout swipeRefresh, int... colorResId) {
        return new SwipeRefreshLayoutHelper(swipeRefresh, colorResId);
    }

    public void setSwipeRefreshListener(SwipeRefreshListener listener) {
        this.mSwipeRefreshListener = listener;
    }

    private SwipeRefreshLayoutHelper(SwipeRefreshLayout swipeRefresh, int... colorResId) {
        this.mSwipeRefresh = swipeRefresh;
        init(colorResId);
    }

    @SuppressLint("ResourceAsColor")
    private void init(int... colorResId) {
        if (colorResId == null || colorResId.length == 0) {
            mSwipeRefresh.setColorSchemeColors(android.R.color.holo_orange_light, android.R.color.holo_blue_light);
        } else {
            mSwipeRefresh.setColorSchemeColors(colorResId);
        }
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mSwipeRefreshListener != null) mSwipeRefreshListener.onRefresh();
            }
        });
    }

}