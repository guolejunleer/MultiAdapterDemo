package com.leer.baseadapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mContentView;

    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
    }

    public static RViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(itemView);
    }

    /**
     * 获取当前View
     *
     * @return 当前View
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 获取布局View的某个控件
     * @param viewId 控件id
     * @param <T> 控件
     * @return 控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

}
