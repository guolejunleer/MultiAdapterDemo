package com.leer.baseadapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.leer.baseadapter.holder.RViewHolder;
import com.leer.baseadapter.listener.ItemListener;
import com.leer.baseadapter.listener.RViewItem;
import com.leer.baseadapter.manager.RViewItemManager;

import java.util.ArrayList;
import java.util.List;

public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private RViewItemManager mItemStyleManager;//item样式管理者
    private ItemListener<T> mItemListener;//item点击事件监听
    private List<T> mDataList;//数据

    private final static long QUICK_EVENT_TIME_SPAN = 1000;//阻塞事件
    private long mLastClickTime;

    //单样式
    public RViewAdapter(List<T> data) {
        if (data == null) mDataList = new ArrayList<>();
        this.mDataList = data;
        mItemStyleManager = new RViewItemManager();
    }

    //多样式
    public RViewAdapter(List<T> data, RViewItem<T> item) {
        if (data == null) mDataList = new ArrayList<>();
        this.mDataList = data;
        mItemStyleManager = new RViewItemManager();
        //添加item样式
        addItemStyles(item);
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMulItemStyle()) {
            int itemViewType = mItemStyleManager.getItemViewType(mDataList.get(position), position);
            Log.i("test001", "getItemViewType itemViewType:" + itemViewType);
            return itemViewType;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RViewItem item = mItemStyleManager.getRViewItem(viewType);
        int itemLayout = item.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(
                parent.getContext(), parent, itemLayout);
        //设置监听
        if (item.openClick()) setListener(viewHolder);
        Log.e("test001", "onCreateViewHolder viewType:" + viewType + ",item:" + item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder viewHolder, int position) {
        convert(viewHolder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setItemClick(ItemListener<T> listener) {
        this.mItemListener = listener;
    }

    public void addItemStyles(RViewItem<T> item) {
        mItemStyleManager.addStyle(item);
    }

    /**
     * 修改整个数据集合
     *
     * @param dataList 数据集合
     */
    public void updateDataList(List<T> dataList) {
        if (dataList == null) return;
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 添加数据集合
     *
     * @param dataList 数据集合
     */
    public void addDataList(List<T> dataList) {
        if (dataList == null) return;
        this.mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    private void convert(RViewHolder viewHolder, T entity) {
        mItemStyleManager.convert(viewHolder, entity, viewHolder.getAdapterPosition());
    }

    private void setListener(final RViewHolder viewHolder) {
        //阻塞事件
        viewHolder.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    //阻塞事件的添加
                    long delTime = System.currentTimeMillis() - mLastClickTime;
                    if (delTime < QUICK_EVENT_TIME_SPAN) {
                        return;
                    }
                    mLastClickTime = System.currentTimeMillis();
                    mItemListener.onItemClick(v, mDataList.get(position), position);
                }
            }
        });
        viewHolder.getContentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mItemListener.onItemLongClick(v, mDataList.get(position), position);
                }
                return false;
            }
        });
    }

    private boolean hasMulItemStyle() {
        return mItemStyleManager.getItemViewStylesCount() > 1;
    }
}
