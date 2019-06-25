package com.JummumCo.Jummum.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Views.RecycleViewHolder.HeaderNodetemViewHolder;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import java.util.List;

public class NoteGridViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater inflater;
    private List<NoteListResponseResultData> originalData;
    private List<NoteListResponseResultData> dataOld;
    private IClickListener<Integer> clickListener;

    public NoteGridViewAdapter(List<NoteListResponseResultData> data,List<NoteListResponseResultData> dataold) {
        this.originalData = data;
        this.dataOld = dataold;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_type_note_item, parent, false);
        return new HeaderNodetemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HeaderNodetemViewHolder itemViewHolder = (HeaderNodetemViewHolder) holder;
        itemViewHolder.setItem(originalData.get(position),position,dataOld);

        if (this.clickListener != null) {
//            itemViewHolder.setItemClickListener(this.clickListener, position);
        }

    }

    public void setOnClickListener(IClickListener<Integer> clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }

}
