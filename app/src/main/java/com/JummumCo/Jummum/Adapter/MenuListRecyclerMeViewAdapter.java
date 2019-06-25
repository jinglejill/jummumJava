package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.JummumCo.Jummum.Views.RecycleViewHolder.MenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by likit on 17/05/2559.
 */
public class MenuListRecyclerMeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable{

    private LayoutInflater inflater;
    // Keep original data (un-modified by filtering)
    private List<MenuListResultData> originalData;

    // Filtered data by criteria
    private List<MenuListResultData> filteredData;
    private IClickListener<Integer> clickListener;

    public MenuListRecyclerMeViewAdapter(List<MenuListResultData> data) {
        this.originalData = data;
        this.filteredData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_menu_item_new, parent, false);

        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder itemViewHolder = (MenuItemViewHolder) holder;

        final MenuListResultData item = filteredData.get(position);
        itemViewHolder.setItem(item);

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
                    if(Pattern.compile(Pattern.quote((String) constraint), Pattern.CASE_INSENSITIVE).matcher(searching.getTitleThai()).find())
                    {
                        filteredResults.add(searching);
                    }
//                    if (searching.getTitleThai().contains(constraint))


                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    public void refresh(int index) {
        notifyItemChanged(index);
    }
}