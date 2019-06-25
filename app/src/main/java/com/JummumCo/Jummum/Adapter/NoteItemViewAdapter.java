package com.JummumCo.Jummum.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IClickListenerDataOrBloo;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Views.RecycleViewHolder.NotetemSelectViewHolder;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;

public class NoteItemViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater inflater;
    private List<NoteListResponseResultData> originalData;
    private List<NoteListResponseResultData> nameRemove;
    private List<NoteListResponseResultData> nameAdd;
    private int typeNote = 0;
    private IClickListenerDataOrBloo<Integer> clickListener;

    private IClickListenerDataOrBloo<Integer> plusClickListener;
    private IClickListenerDataOrBloo<Integer> minusClickListener;
    private List<NoteListResponseResultData> delectGlo;


    public NoteItemViewAdapter(List<NoteListResponseResultData> data,
                               int typeNote,
                               List<NoteListResponseResultData> nameRemove,
                               List<NoteListResponseResultData> nameAdd,
                               List<NoteListResponseResultData> delete) {
        this.originalData = data;
        this.typeNote = typeNote;
        this.nameRemove = nameRemove;
        this.nameAdd = nameAdd;
        this.delectGlo = delete;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_new, parent, false);
//        return new NotetemCheckViewHolder(view);
        return new NotetemSelectViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotetemSelectViewHolder itemViewHolder = (NotetemSelectViewHolder) holder;
        itemViewHolder.setItem(originalData.get(position), typeNote, nameRemove, nameAdd, position,delectGlo);

        if (this.clickListener != null) {
            itemViewHolder.setItemClickListener(this.clickListener, position);
        }

        if (this.plusClickListener != null){
            itemViewHolder.setItemClickPlusListener(this.plusClickListener,position);
        }

        if (this.minusClickListener != null){
            itemViewHolder.setItemClickMinusListener(this.minusClickListener,position);
        }
    }

    public void setOnClickListener(IClickListenerDataOrBloo<Integer> clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnClickPlusListener(IClickListenerDataOrBloo<Integer> plusClickListener) {
        this.plusClickListener = plusClickListener;
    }

    public void setOnClickMinusListener(IClickListenerDataOrBloo<Integer> minusClickListener) {
        this.minusClickListener = minusClickListener;
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }

    public void refresh(int index) {
        notifyItemChanged(index);
//        notifyDataSetChanged();
    }
}
