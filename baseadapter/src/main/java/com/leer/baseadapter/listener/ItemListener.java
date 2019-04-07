package com.leer.baseadapter.listener;

import android.view.View;

public interface ItemListener<T> {

    void onItemClick(View view, T entity, int position);

    void onItemLongClick(View view, T entity, int position);
}
