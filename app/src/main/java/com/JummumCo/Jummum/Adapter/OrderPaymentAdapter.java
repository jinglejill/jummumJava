package com.JummumCo.Jummum.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Views.ListItem.OrderPaymentListItem;

import java.util.List;


public class OrderPaymentAdapter extends BaseAdapter{


    private List<OrderSummary> filteredData;
    public OrderPaymentAdapter(List<OrderSummary> listDatas) {

        this.filteredData = listDatas;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }


    @Override
    public Object getItem(int position) {

        OrderSummary data = filteredData.get(position);
        return data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OrderPaymentListItem item;
        if (convertView != null)
            item = (OrderPaymentListItem) convertView;
        else
            item = new OrderPaymentListItem(parent.getContext());

        OrderSummary data = (OrderSummary) getItem(position);
        item.setItem(data,position);
        return item;
    }


}
