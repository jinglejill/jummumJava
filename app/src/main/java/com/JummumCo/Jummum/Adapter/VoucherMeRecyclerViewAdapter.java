package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.JummumCo.Jummum.Views.RecycleViewHolder.RewardListOrderItemViewHolder;
import com.JummumCo.Jummum.Views.RecycleViewHolder.VoucherMeItemViewHolder;
import com.android.jummum.R;

import java.util.List;


/**
 * Created by likit on 17/05/2559.
 */
public class VoucherMeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    public interface OrderListClickListener {
        void onClick(OrderListResultData orderListResult, List<OrderSummary> orderSummaries);
    }

    private LayoutInflater inflater;
    private List<HotDealData> filteredData;
    private IClickListener<Integer> clickListener;
    private int typeUI = 0;

    public VoucherMeRecyclerViewAdapter(List<HotDealData> data, int typeUI) {
        this.filteredData = data;
        this.typeUI = typeUI;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_hotdeal_item, parent, false);
        return new VoucherMeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        VoucherMeItemViewHolder itemViewHolder = (VoucherMeItemViewHolder) holder;

        final HotDealData item = filteredData.get(position);
        itemViewHolder.setItem(item,typeUI);

        if (this.clickListener != null) {
            itemViewHolder.setItemClickListener(this.clickListener, position);
        }


    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void setOnClickListener(IClickListener<Integer> clickListener) {
        this.clickListener = clickListener;
    }

}