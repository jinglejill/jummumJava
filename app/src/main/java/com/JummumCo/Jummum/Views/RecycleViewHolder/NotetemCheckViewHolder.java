package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class NotetemCheckViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.check_note)
    CheckBox checkNote;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    View viewG;

    private NoteListResponseResultData item;
    private NoteGridViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int typenote = 0;

    public NotetemCheckViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(NoteListResponseResultData data,
                        int typenote,
                        List<NoteListResponseResultData> nameRemove,
                        List<NoteListResponseResultData> nameAdd,
                        int position) {
        this.item = data;
        this.typenote = typenote;
        init(nameRemove,nameAdd,position);
    }

    private void init(List<NoteListResponseResultData> nameRemove,
                      List<NoteListResponseResultData> nameAdd,
                      int position) {

        List<NoteListResponseResultData> xx = Constant.noteListData;
        if (typenote == 0) {
            checkNote.setText("ไม่ใส่ "+item.getName());
            if (nameRemove != null && nameRemove.size() > 0){
                for(int i=0;i<nameRemove.size();i++){
                    if (item.getNoteID().equals(nameRemove.get(i).getNoteID())){
                        checkNote.setChecked(true);
                        //Constant.noteListData.add(nameRemove.get(i));
                    }
                }

            }
        }
        else
        {
            checkNote.setText("เพิ่ม " + item.getName());
            if (nameAdd != null && nameAdd.size() > 0){
                for(int i=0;i<nameAdd.size();i++){
                    if (item.getNoteID().equals(nameAdd.get(i).getNoteID())){
                        checkNote.setChecked(true);
                       // Constant.noteListData.add(nameAdd.get(i));
                    }
                }
            }
        }

        if (item.getPrice().equals("0")){
            tvPrice.setVisibility(View.GONE);
        }else{
            tvPrice.setVisibility(View.VISIBLE);
            tvPrice.setText("+"+item.getPrice());
        }




    }

    public void setItemClickListener(final IClickListenerDataOrBloo<Integer> clickListener, final int position) {

        checkNote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                clickListener.onClick(position,b);
            }
        });
    }

}
