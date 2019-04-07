package com.leer.multiadapterdemo.adpter;

import com.leer.baseadapter.holder.RViewHolder;
import com.leer.baseadapter.listener.RViewItem;
import com.leer.multiadapterdemo.R;
import com.leer.multiadapterdemo.bean.TestEntity;

public class EItem implements RViewItem<TestEntity> {

    @Override
    public int getItemLayout() {
        return R.layout.item_e;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(TestEntity entity, int position) {
        return entity.getLayoutType() == 5;
    }

    @Override
    public void convert(RViewHolder holder, TestEntity entity, int position) {
        //Todo

    }
}
