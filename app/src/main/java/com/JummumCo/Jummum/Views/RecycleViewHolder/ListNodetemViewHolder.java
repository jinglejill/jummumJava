package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.JummumCo.Jummum.Adapter.NoteItemViewAdapter;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class ListNodetemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    View viewG;

    private List<NoteListResponseResultData> item;
    private NoteItemViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    public ListNodetemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(List<NoteListResponseResultData> data) {
        this.item = data;
        init();
    }

    private void init() {

//        gridLayoutManager = new GridLayoutManager(viewG.getContext(),2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        adapter = new NoteItemViewAdapter(item);
//        recyclerView.setAdapter(adapter);
    }

    public void setItemClickListener(final IClickListener<Integer> clickListener, final int position) {

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }

}
