package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.NoteGridViewAdapter;
import com.JummumCo.Jummum.Interface.IClickListenerDataOrBloo;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class NotetemSelectViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.btn_minus)
    ImageButton btnMinus;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_plus)
    ImageButton btnPlus;
    @BindView(R.id.layout_note)
    LinearLayout layoutNote;
    @BindView(R.id.layout_note_quantity)
    LinearLayout layoutNoteQuantity;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.list_item)
    LinearLayout listItem;

    View viewG;
    @BindView(R.id.tv_name)
    TextView tvName;

    private NoteListResponseResultData item;
    private NoteGridViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int typenote = 0;
    private List<NoteListResponseResultData> deleteGlo;
    private boolean selectNote = false;

    public NotetemSelectViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(NoteListResponseResultData data,
                        int typenote,
                        List<NoteListResponseResultData> nameRemove,
                        List<NoteListResponseResultData> nameAdd,
                        int position,
                        List<NoteListResponseResultData> delete) {
        this.item = data;
        this.typenote = typenote;
        this.deleteGlo = delete;
        init(nameRemove, nameAdd, position);
    }

    private void init(List<NoteListResponseResultData> nameRemove,
                      List<NoteListResponseResultData> nameAdd,
                      int position) {

        List<NoteListResponseResultData> xx = Constant.noteListData;
        if (typenote == 0) {
            tvName.setText(item.getPrefix() + " " + item.getName());


            if (nameRemove != null && nameRemove.size() > 0) {
                for (int i = 0; i < nameRemove.size(); i++) {
                    if (item.getNoteID().equals(nameRemove.get(i).getNoteID())) {
                        mainContent.setBackgroundColor(Color.parseColor("#ffd7da"));
                        selectNote = true;
                        if (item.getAllowQuantity().equals("0")) {
                            tvCount.setText(nameRemove.get(i).getQuantity());
                            layoutNote.setVisibility(View.GONE);
                            layoutNoteQuantity.setVisibility(View.VISIBLE);
                        } else if (item.getAllowQuantity().equals("1")) {
                            tvCount.setText(nameRemove.get(i).getQuantity());
                            layoutNote.setVisibility(View.VISIBLE);
                            layoutNoteQuantity.setVisibility(View.GONE);
                        }
                    }else {

                        if (deleteGlo != null && deleteGlo.size() > 0) {
                            for (int x = 0; x < deleteGlo.size(); x++) {
                                if (item.getNoteID().equals(deleteGlo.get(x).getNoteID())) {
                                    mainContent.setBackgroundColor(Color.TRANSPARENT);
                                    selectNote = false;
                                    layoutNote.setVisibility(View.GONE);
                                    layoutNoteQuantity.setVisibility(View.VISIBLE);
                                }

                            }
                        }


                    }
                }
            }else {
                mainContent.setBackgroundColor(Color.TRANSPARENT);
                selectNote = false;
                layoutNote.setVisibility(View.GONE);
                layoutNoteQuantity.setVisibility(View.VISIBLE);
            }

        } else {
            tvName.setText(item.getPrefix() + " " +  item.getName());
            if (nameAdd != null && nameAdd.size() > 0) {

                for (int i = 0; i < nameAdd.size(); i++) {
                    if (item.getNoteID().equals(nameAdd.get(i).getNoteID())) {

                        mainContent.setBackgroundColor(Color.parseColor("#ffd7da"));
                        selectNote = true;

                        if (item.getAllowQuantity().equals("0")){
                            tvCount.setText(nameAdd.get(i).getQuantity());
                            layoutNote.setVisibility(View.GONE);
                            layoutNoteQuantity.setVisibility(View.VISIBLE);
                        }else if(item.getAllowQuantity().equals("1")) {
                            tvCount.setText(nameAdd.get(i).getQuantity());
                            layoutNote.setVisibility(View.VISIBLE);
                            layoutNoteQuantity.setVisibility(View.GONE);
                        }
                    }
                    else{

                        if (deleteGlo != null && deleteGlo.size() > 0) {
                            for (int x = 0; x < deleteGlo.size(); x++) {
                                if (item.getNoteID().equals(deleteGlo.get(x).getNoteID())) {
                                    mainContent.setBackgroundColor(Color.TRANSPARENT);
                                    selectNote = false;
                                    layoutNote.setVisibility(View.GONE);
                                    layoutNoteQuantity.setVisibility(View.VISIBLE);
                                }

                            }
                        }
                    }
                }
            }else{
                mainContent.setBackgroundColor(Color.TRANSPARENT);
                selectNote = false;
                layoutNote.setVisibility(View.GONE);
                layoutNoteQuantity.setVisibility(View.VISIBLE);
            }
        }

        if (item.getPrice().equals("0")) {
            tvPrice.setVisibility(View.GONE);
        } else {
            tvPrice.setVisibility(View.VISIBLE);
            tvPrice.setText("+" + item.getPrice());
        }


    }

    public void setItemClickListener(final IClickListenerDataOrBloo<Integer> clickListener, final int position) {

        mainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectNote){
                    clickListener.onClick(position,false);
                }else {
                    clickListener.onClick(position,true);
                }

            }
        });

    }

    public void setItemClickPlusListener(final IClickListenerDataOrBloo<Integer> clickListener, final int position) {

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("debug : ","Plus +++ + + + + + +  + + + + + ");
                clickListener.onClick(position,false);

            }
        });
    }

    public void setItemClickMinusListener(final IClickListenerDataOrBloo<Integer> clickListener, final int position) {

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("debug : ","Minus -- - - - - - - - - - - - ");
                clickListener.onClick(position,false);

            }
        });
    }

}
