package com.JummumCo.Jummum.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Views.ListItem.OrderListmentListItem;

import java.util.List;


public class OderListmentAdapter extends BaseAdapter{


    private List<OrderTaking2ResultData> filteredData;
    public OderListmentAdapter(List<OrderTaking2ResultData> listDatas) {

        this.filteredData = listDatas;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }


    @Override
    public Object getItem(int position) {

        OrderTaking2ResultData data = filteredData.get(position);
        return data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OrderListmentListItem item;
        if (convertView != null)
            item = (OrderListmentListItem) convertView;
        else
            item = new OrderListmentListItem(parent.getContext());

        OrderTaking2ResultData data = (OrderTaking2ResultData) getItem(position);
        item.setItem(data,position);
        return item;
    }



}
