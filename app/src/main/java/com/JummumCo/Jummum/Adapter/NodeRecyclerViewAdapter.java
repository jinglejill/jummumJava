package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteItemClick;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.JummumCo.Jummum.Views.RecycleViewHolder.NodetemViewHolder;
import com.android.jummum.R;

import java.util.List;


/**
 * Created by likit on 17/05/2559.
 */
public class NodeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater inflater;
    private IClickListener<NoteItemClick> clickListener;
    private IClickListener<NoteItemClick> TakeclickListener;
    private IClickListener<NoteItemClick> setClearNoteClickListener;
    private int rootPosition = -1;
    private List<List<NoteListResponseResultData>> data;
    private List<NoteListResponseResultData> take;
    private List<List<BranchAndCustomerTableResponseResultData>> tableData;
    private MenuListResultData item;

    public NodeRecyclerViewAdapter(List<List<NoteListResponseResultData>> data,
                                   int rootPosition,
                                   List<NoteListResponseResultData> take,
                                   List<List<BranchAndCustomerTableResponseResultData>> tableData,
                                   MenuListResultData item) {
        this.data = data;
        this.rootPosition = rootPosition;
        this.take = take;
        this.tableData = tableData;
        this.item = item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note_item, parent, false);
        return new NodetemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        NodetemViewHolder itemViewHolder = (NodetemViewHolder) holder;
        itemViewHolder.setItem(position+1,data.get(position),take.get(position),tableData);

        if (this.clickListener != null) {
            itemViewHolder.setItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NoteItemClick itemClick = new NoteItemClick();
                    itemClick.setRootPosition(rootPosition);
                    itemClick.setPosition(position);
                    Constant.menuNoteDataGlobal = item;

                    clickListener.onClick(itemClick);
                }
            });

        }

        if(this.TakeclickListener != null){
            itemViewHolder.setItemTakeClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteItemClick itemClick = new NoteItemClick();
                    itemClick.setRootPosition(rootPosition);
                    itemClick.setPosition(position);

                    TakeclickListener.onClick(itemClick);
                }
            });
        }

        if(this.setClearNoteClickListener != null){
            itemViewHolder.setClearNoteClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteItemClick itemClick = new NoteItemClick();
                    itemClick.setRootPosition(rootPosition);
                    itemClick.setPosition(position);

                    setClearNoteClickListener.onClick(itemClick);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(IClickListener<NoteItemClick> clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnTakeClickListener(IClickListener<NoteItemClick> TakeclickListener) {
        this.TakeclickListener = TakeclickListener;
    }


    public void setClearNoteClickListener(IClickListener<NoteItemClick> setClearNoteClickListener) {
        this.setClearNoteClickListener = setClearNoteClickListener;
    }


    public void removeItem(int index) {
//        filteredData.remove(index);
//        notifyItemRemoved(index);
//        notifyItemRangeChanged(index, filteredData.size());
    }

    public void refresh(int index) {
        notifyDataSetChanged();
    }


}