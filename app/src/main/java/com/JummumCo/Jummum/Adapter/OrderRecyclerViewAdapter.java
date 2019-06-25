package com.JummumCo.Jummum.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteItemClick;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.android.jummum.R;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.JummumCo.Jummum.Views.RecycleViewHolder.OrderItemViewHolder;

import java.util.List;


/**
 * Created by likit on 17/05/2559.
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<MenuListResultData> filteredData;
    private IClickListener<Integer> clickListener;
    private IClickListener<Integer> plusOnClickListener;
    private IClickListener<Integer> minusOnClickListener;
    private IClickListener<Integer> noteOnClickListener;
    private IClickListener<NoteItemClick> noteItemClickListener;
    private IClickListener<NoteItemClick> noteItemTakeClickListener;
    private IClickListener<NoteItemClick> clearItemTakeClickListener;
    private List<List<BranchAndCustomerTableResponseResultData>> tableData;

    public OrderRecyclerViewAdapter(List<MenuListResultData> data,
                                    List<List<BranchAndCustomerTableResponseResultData>> tableData) {
        this.filteredData = data;
        this.tableData = tableData;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_order_item, parent, false);

        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        OrderItemViewHolder itemViewHolder = (OrderItemViewHolder) holder;

        final MenuListResultData item = filteredData.get(position);



        if (this.plusOnClickListener != null) {
            itemViewHolder.setPlusClickListener(this.plusOnClickListener, position);
        }
        if(this.minusOnClickListener != null) {
            itemViewHolder.setMinusClickListener(this.minusOnClickListener, position);
        }

        if(this.noteOnClickListener != null) {
            itemViewHolder.setNodeClickListener(this.noteOnClickListener, position, filteredData.get(position).getQty());
        }

        if(this.noteItemClickListener != null){
            itemViewHolder.setNoteItemClickListener(this.noteItemClickListener);
        }

        if(this.noteItemTakeClickListener != null){
            itemViewHolder.setNoteItemTakeClickListener(this.noteItemTakeClickListener);
        }

        if(this.clearItemTakeClickListener != null){
            itemViewHolder.setClearNoteItemTakeClickListener(this.clearItemTakeClickListener);
        }

        itemViewHolder.setItem(item,position,tableData);
    }

    public void setNoteItemClickListener(IClickListener<NoteItemClick> noteItemClickListener){
        this.noteItemClickListener = noteItemClickListener;
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void setOnClickListener(IClickListener<Integer> clickListener) {
        this.clickListener = clickListener;
    }

    public void setPlusOnClickListener(IClickListener<Integer> plusOnClickListener){
        this.plusOnClickListener = plusOnClickListener;
    }

    public void setMinusOnClickListener(IClickListener<Integer> minusOnClickListener){
        this.minusOnClickListener = minusOnClickListener;
    }

    public void setNoteOnClickListener(IClickListener<Integer> noteOnClickListener){
        this.noteOnClickListener = noteOnClickListener;
    }

    public void setNoteItemTakeClickListener(IClickListener<NoteItemClick> noteItemTakeClickListener){
        this.noteItemTakeClickListener = noteItemTakeClickListener;
    }

    public void setClearNoteItemTakeClickListener(IClickListener<NoteItemClick> clearItemTakeClickListener){
        this.clearItemTakeClickListener = clearItemTakeClickListener;
    }


    public void removeItem(int index) {

        //notifyItemMoved(index+1,index);
        filteredData.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, filteredData.size());

        //notifyItemChanged(index);
        //notifyDataSetChanged();

    }

    public void refresh(int index) {
//        notifyDataSetChanged();
        setNoteOnClickListener(this.noteOnClickListener);
        notifyItemChanged(index);
    }

    public void refreshNote(NoteItemClick itemClick, List<NoteListResponseResultData> data){

        if (this.filteredData.get(itemClick.getRootPosition())
                .getNoteList().get(itemClick.getPosition())
                .size() > 0){
            this.filteredData.get(itemClick.getRootPosition())
                    .getNoteList().get(itemClick.getPosition()).clear();
        }
        this.filteredData
                .get(itemClick.getRootPosition())
                .getNoteList()
                .get(itemClick.getPosition())
                .addAll(data);

        notifyItemChanged(itemClick.getRootPosition());

    }


}