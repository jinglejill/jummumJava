package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Views.RecycleViewHolder.BranchItemViewHolder;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by likit on 17/05/2559.
 */
public class BranchListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {


    private LayoutInflater inflater;
    // Keep original data (un-modified by filtering)
    private List<BranchAndCustomerTableResponseResultData> originalData;

    // Filtered data by criteria
    private List<BranchAndCustomerTableResponseResultData> filteredData;
    private IClickListener<Integer> clickListener;


    public BranchListRecyclerViewAdapter(List<BranchAndCustomerTableResponseResultData> data) {
        this.originalData = data;
        this.filteredData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_branch_item, parent, false);

        return new BranchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        BranchItemViewHolder itemViewHolder = (BranchItemViewHolder) holder;

        final BranchAndCustomerTableResponseResultData item = filteredData.get(position);
        itemViewHolder.setItem(item,"branch");

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

                filteredData = (List<BranchAndCustomerTableResponseResultData>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<BranchAndCustomerTableResponseResultData> filteredResults = new ArrayList<>();

                // Filter any field with constraint
                for (BranchAndCustomerTableResponseResultData searching : originalData) {

                    if (searching.getName().contains(constraint))
                        filteredResults.add(searching);

                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    public void refresh(int index) {
        notifyDataSetChanged();
    }
}