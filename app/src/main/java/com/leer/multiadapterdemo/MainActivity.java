package com.leer.multiadapterdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leer.baseadapter.base.RViewAdapter;
import com.leer.baseadapter.listener.ItemListener;
import com.leer.multiadapterdemo.adpter.MulAdapter;
import com.leer.multiadapterdemo.bean.TestEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseRViewActivity {

    private List<TestEntity> mDataList = new ArrayList<>();
    private MulAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intData();
        listener();
    }

    private void listener() {
        mAdapter.setItemClick(new ItemListener<TestEntity>() {
            @Override
            public void onItemClick(View view, TestEntity entity, int position) {
                Log.i("test", "onItemClick position:" + position);
            }

            @Override
            public void onItemLongClick(View view, TestEntity entity, int position) {
                Log.i("test", "onItemLongClick position:" + position);
            }
        });
    }

    private void intData() {
        new Thread(() -> {
            if (mDataList.isEmpty()) {
                for (int i = 0; i < 20; i++) {
                    TestEntity entity = new TestEntity();
                    if (i % 20 == 1) {
                        entity.setLayoutType(1);
                    } else if (i % 20 == 2 || i % 20 == 3) {
                        entity.setLayoutType(2);
                    } else if (i % 20 == 4 || i % 20 == 5 | i % 20 == 6) {
                        entity.setLayoutType(3);
                    } else if (i % 20 == 7 || i % 20 == 8 | i % 20 == 9) {
                        entity.setLayoutType(4);
                    } else {
                        entity.setLayoutType(5);
                    }
                    mDataList.add(entity);
                }
            }
            notifyAdapterDataSetChanged(mDataList);
        }).start();
    }

    @Override
    public void onRefresh() {
        intData();
    }

    @Override
    public RViewAdapter createRViewAdapter() {
        mAdapter = new MulAdapter(null);
        return mAdapter;
    }
}
