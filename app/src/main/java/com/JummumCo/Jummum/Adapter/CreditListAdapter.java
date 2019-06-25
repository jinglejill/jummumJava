package com.JummumCo.Jummum.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.JummumCo.Jummum.Model.CreditUserData;
import com.JummumCo.Jummum.Views.ListItem.CreditListItem;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;

import java.util.List;


public class CreditListAdapter extends BaseAdapter{


    private List<CreditUserData> filteredData;
    private IClickListener<Integer> clickListener;
    private IClickListener<Integer> clickListenerList;

    public CreditListAdapter(List<CreditUserData> listDatas) {

        this.filteredData = listDatas;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {

        CreditUserData data = filteredData.get(position);
        return data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CreditListItem item;
        if (convertView != null)
            item = (CreditListItem) convertView;
        else
            item = new CreditListItem(parent.getContext());

        CreditUserData data = (CreditUserData) getItem(position);
        item.setItem(data,position);

        if (this.clickListener != null) {
            item.setItemClickListener(this.clickListener,position);
        }

        if (this.clickListenerList != null) {
            item.setItemClickListenerList(this.clickListenerList,position);
        }

        return item;
    }

    public void setItemClickListener(IClickListener<Integer> itemClickListener) {
        clickListener = itemClickListener;
    }

    public void setItemClickListenerList(IClickListener<Integer> itemClickListener) {
        clickListenerList = itemClickListener;
    }


}
