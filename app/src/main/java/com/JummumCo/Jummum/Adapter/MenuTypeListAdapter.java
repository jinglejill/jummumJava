package com.JummumCo.Jummum.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Views.ListItem.MenuTypeListItem;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;

import java.util.ArrayList;
import java.util.List;


public class MenuTypeListAdapter extends BaseAdapter implements Filterable {

    // Keep original data (un-modified by filtering)
    private List<MenuListResultData> originalData;

    // Filtered data by criteria
    private List<MenuListResultData> filteredData;

    private IClickListener<Integer> clickListener;

    public MenuTypeListAdapter(List<MenuListResultData> listDatas) {

        this.originalData = listDatas;
        this.filteredData = listDatas;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }


    @Override
    public Object getItem(int position) {

        MenuListResultData data = filteredData.get(position);
        return data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MenuTypeListItem item;
        if (convertView != null)
            item = (MenuTypeListItem) convertView;
        else
            item = new MenuTypeListItem(parent.getContext());

        MenuListResultData data = (MenuListResultData) getItem(position);

        item.setItem(data);

        if (this.clickListener != null) {
            item.setItemClickListener(this.clickListener,position);
        }
        return item;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredData = (List<MenuListResultData>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<MenuListResultData> filteredResults = new ArrayList<>();

                // Filter any field with constraint
                for (MenuListResultData searching : originalData) {

                    if (searching.getTitleThai().contains(constraint))
                        filteredResults.add(searching);

                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    public void setItemClickListener(IClickListener<Integer> itemClickListener) {
        clickListener = itemClickListener;
    }


}
