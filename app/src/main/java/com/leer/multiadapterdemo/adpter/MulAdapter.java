package com.leer.multiadapterdemo.adpter;

import com.leer.baseadapter.base.RViewAdapter;
import com.leer.multiadapterdemo.bean.TestEntity;

import java.util.List;

public class MulAdapter extends RViewAdapter<TestEntity> {

    public MulAdapter(List<TestEntity> data) {
        super(data);
        addItemStyles(new AItem());
        addItemStyles(new BItem());
        addItemStyles(new CItem());
        addItemStyles(new DItem());
        addItemStyles(new EItem());
    }
}
