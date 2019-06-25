package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.android.jummum.R;
import com.JummumCo.Jummum.Views.RecycleViewHolder.HistoryListOrderItemViewHolder;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;

import java.util.List;


/**
 * Created by likit on 17/05/2559.
 */
public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OrderListClickListener {
        void onClick(OrderListResultData orderListResult, List<OrderSummary> orderSummaries, SummaryResponseResultData orderSummary);
    }

    private LayoutInflater inflater;
    private List<OrderListResultData> filteredData;
    private IClickListener<OrderListResultData> clickListener;
    private OrderListClickListener openDetailClick;

    private IClickListener<OrderListResultData> orderBuffetClick;
    private OrderListClickListener reOrderClick;
    private IClickListener<OrderListResultData> qrJoinClick;

    public OrderListRecyclerViewAdapter(List<OrderListResultData> data) {
        this.filteredData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_history_order_item, parent, false);
        return new HistoryListOrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        HistoryListOrderItemViewHolder itemViewHolder = (HistoryListOrderItemViewHolder) holder;

        final OrderListResultData item = filteredData.get(position);
        itemViewHolder.setItem(item);

        if (openDetailClick != null) {
            itemViewHolder.setListViewClickListener(new IClickListener<List<OrderSummary>>() {
                @Override
                public void onClick(List<OrderSummary> item2) {
                    openDetailClick.onClick(item, item2,item.getOrderSummary().get(0));
                }
            });
        }

        if (orderBuffetClick != null) {
            itemViewHolder.setOrderBuffetClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderBuffetClick.onClick(item);
                }
            });
        }

        if (reOrderClick != null) {
            itemViewHolder.setReorderClickListener(new IClickListener<List<OrderSummary>>() {
                @Override
                public void onClick(List<OrderSummary> item2) {
                    reOrderClick.onClick(item, item2,item.getOrderSummary().get(0));
                }
            });
        }

        if (clickListener != null){
            itemViewHolder.setPaymentClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(item);
                }
            });
        }

        if (qrJoinClick != null){
            itemViewHolder.setOrJoinClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    qrJoinClick.onClick(item);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void setOnClickPaymentListener(IClickListener<OrderListResultData> clickListener) {
        this.clickListener = clickListener;
    }

    public void setOpenDetailClick(OrderListClickListener openDetailClick) {
        this.openDetailClick = openDetailClick;
    }

    public void setOrderBuffetClick(IClickListener<OrderListResultData> orderBuffetClick) {
        this.orderBuffetClick = orderBuffetClick;
    }

    public void setReorderClickListener(OrderListClickListener reOrderClick) {
        this.reOrderClick = reOrderClick;
    }

    public void setQrJoinClickListener(IClickListener<OrderListResultData> qrJoinClick) {
        this.qrJoinClick = qrJoinClick;
    }

    public void removeItem(int index) {
        filteredData.remove(index);
        //notifyItemChanged(index);
        notifyDataSetChanged();
    }

    public void refresh(int index) {
        notifyItemChanged(index);
    }


}