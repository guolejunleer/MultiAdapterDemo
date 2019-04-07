package com.leer.multiadapterdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leer.baseadapter.RVHelper;
import com.leer.baseadapter.SwipeRefreshLayoutHelper;
import com.leer.baseadapter.listener.RViewCreate;

import java.util.List;

public abstract class BaseRViewActivity<T> extends AppCompatActivity
        implements RViewCreate, SwipeRefreshLayoutHelper.SwipeRefreshListener {

    protected RVHelper mRVHelper;

    protected void notifyAdapterDataSetChanged(List<T> dataList){
        mRVHelper.notifyAdapterDataSetChanged(dataList);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRVHelper = new RVHelper.Builder(this,this).build();
    }

    @Override
    public SwipeRefreshLayout createSwipeRefreshLayout() {
        return findViewById(R.id.srl_view);
    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.rcv_view);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
    }

    @Override
    public int startPageNum() {
        return 1;
    }

    @Override
    public boolean isSupportPaging() {
        return true;
    }
}
