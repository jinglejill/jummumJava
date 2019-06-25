package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Activity.NodeDialogActivity;
import com.JummumCo.Jummum.Adapter.NoteItemViewAdapter;
import com.JummumCo.Jummum.Interface.IClickListenerDataOrBloo;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class HeaderNodetemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view_add)
    RecyclerView recyclerViewAdd;
    @BindView(R.id.recycler_view_remove)
    RecyclerView recyclerViewRemove;
    @BindView(R.id.list_item)
    LinearLayout listItem;


    View viewG;


    private NoteItemViewAdapter adapterRemove,adapterAdd;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private NoteListResponseResultData item;
    private List<NoteListResponseResultData> data;


    public HeaderNodetemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(NoteListResponseResultData noteListResponseResultData,
                        int position,
                        List<NoteListResponseResultData> dataOld) {
        this.item = noteListResponseResultData;

        init(position,dataOld);
    }

    private void init(int position,List<NoteListResponseResultData> dataOld) {

        final List<NoteListResponseResultData> namesAdd = new ArrayList<>();
        final List<NoteListResponseResultData> namesRemove = new ArrayList<>();

        final List<NoteListResponseResultData> deleteGlo = new ArrayList<>();

        if (dataOld.size() > 0){
            Constant.noteListData = new ArrayList<>();
            for (NoteListResponseResultData d: dataOld) {
                if (d.getType().equals("-1")) {
                    namesRemove.add(d);
                }else {
                    namesAdd.add(d);
                }
                Constant.noteListData.add(d);
            }
        }else{
            if (Constant.noteListData == null) {
                Constant.noteListData = new ArrayList<>();
            }
        }


        tvTitle.setText(item.getName());
        linearLayoutManager = new LinearLayoutManager(viewG.getContext());
//        gridLayoutManager = new GridLayoutManager(viewG.getContext(),2);

        recyclerViewRemove.setLayoutManager(linearLayoutManager);
        adapterRemove = new NoteItemViewAdapter(item.getNoteRemove(),0,namesRemove,namesAdd,deleteGlo);
        adapterRemove.setOnClickListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position,boolean b) {
                if (b) {
                    Constant.noteListData.add(item.getNoteRemove().get(position));

                    if (item.getNoteRemove().get(position).getQuantity() == null){
                        item.getNoteRemove().get(position).setQuantity("1");
                    }

                    namesRemove.add(item.getNoteRemove().get(position));
                }else {
                    for (int i = 0;i < Constant.noteListData.size();i++) {
                        if (Constant.noteListData
                                .get(i)
                                .getNoteID()
                                .equals(item.getNoteRemove()
                                        .get(position)
                                        .getNoteID())){

                            Constant.noteListData.remove(i);
                            item.getNoteRemove()
                                    .get(position).setQuantity(null);

                        }
                    }

                    for (int i = 0;i<namesRemove.size();i++){
                        if (namesRemove.get(i).getNoteID().
                                equals(item.getNoteRemove().get(position).getNoteID())) {
                            namesRemove.remove(i);
                        }
                    }

                    deleteGlo.add(item.getNoteRemove().get(position));
                }

                adapterRemove.refresh(position);
                //Constant.noteListData = data;
            }
        });

        adapterRemove.setOnClickPlusListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position, boolean b) {
//                for (int i = 0;i < Constant.noteListData.size();i++) {
//                    if (Constant.noteListData
//                            .get(i)
//                            .getNoteID()
//                            .equals(item.getNoteRemove()
//                                    .get(position)
//                                    .getNoteID())){
//
//                        if (Constant.noteListData
//                                .get(i).getQuantity() == null) {
//
//                            Constant.noteListData
//                                    .get(i)
//                                    .setQuantity("0");
//                        }else {
//                            Constant.noteListData
//                                    .get(i)
//                                    .setQuantity(String.valueOf(Integer.parseInt(Constant.noteListData.get(i).getQuantity()) + 1));
//                        }
//                    }
//                }

                for (int i = 0;i<namesRemove.size();i++){
                    if (namesRemove.get(i).getNoteID().
                            equals(item.getNoteRemove().get(position).getNoteID())) {
                        namesRemove.get(i).setQuantity(String.valueOf(Integer.parseInt(namesRemove.get(i).getQuantity()) +1));
                    }
                }

                adapterRemove.refresh(position);
            }

        });

        adapterRemove.setOnClickMinusListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position, boolean b) {
                for (int i = 0;i < Constant.noteListData.size();i++) {
                    if (Constant.noteListData
                            .get(i)
                            .getNoteID()
                            .equals(item.getNoteRemove()
                                    .get(position)
                                    .getNoteID())){

                        if (Constant.noteListData.get(i).getQuantity() != null) {
                            if (Integer.parseInt(Constant.noteListData
                                    .get(i).getQuantity()) > 1) {
                                Constant.noteListData
                                        .get(i)
                                        .setQuantity(String.valueOf(Integer.parseInt(Constant.noteListData.get(i).getQuantity()) - 1));
                            } else {
                                Constant.noteListData.remove(i);
                                item.getNoteRemove()
                                        .get(position).setQuantity(null);

                                for (int x = 0; x < namesRemove.size(); x++) {
                                    if (namesRemove.get(x).getNoteID().
                                            equals(item.getNoteRemove().get(position).getNoteID())) {
                                        namesRemove.remove(x);
                                    }
                                }

                                deleteGlo.add(item.getNoteRemove().get(position));
                            }
                        }
                    }
                }

//                for (int i = 0;i<namesRemove.size();i++){
//                    if (namesRemove.get(i).getNoteID().
//                            equals(item.getNoteRemove().get(position).getNoteID())) {
//                        namesRemove.get(i).setQuantity(String.valueOf(Integer.parseInt(namesRemove.get(i).getQuantity()) - 1));
//                    }
//                }

                adapterRemove.refresh(position);
            }
        });

        recyclerViewRemove.setAdapter(adapterRemove);

        linearLayoutManager = new LinearLayoutManager(viewG.getContext());
//        gridLayoutManager = new GridLayoutManager(viewG.getContext(),2);
        recyclerViewAdd.setLayoutManager(linearLayoutManager);
        adapterAdd = new NoteItemViewAdapter(item.getNoteAdd(),1,namesRemove,namesAdd,deleteGlo);
        adapterAdd.setOnClickListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position,boolean b) {

                if (b) {
                    Constant.noteListData.add(item.getNoteAdd().get(position));
                    if (item.getNoteAdd().get(position).getQuantity() == null){
                        item.getNoteAdd().get(position).setQuantity("1");
                    }
                    namesAdd.add(item.getNoteAdd().get(position));
                }else{
                    for (int i = 0;i < Constant.noteListData.size();i++) {
                        if (Constant.noteListData
                                .get(i)
                                .getNoteID()
                                .equals(item.getNoteAdd()
                                        .get(position)
                                        .getNoteID())){

                            Constant.noteListData.remove(i);
                            item.getNoteAdd()
                                    .get(position).setQuantity(null);


                        }
                    }

                    for (int i = 0;i<namesAdd.size();i++){
                        if (namesAdd.get(i).getNoteID().
                                equals(item.getNoteAdd().get(position).getNoteID())) {
                            namesAdd.remove(i);
                        }
                    }

                    deleteGlo.add(item.getNoteAdd().get(position));
                }
                adapterAdd.refresh(position);
               //Constant.noteListData = data;
            }
        });

        adapterAdd.setOnClickPlusListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position, boolean b) {
                for (int i = 0;i < Constant.noteListData.size();i++) {
                    if (Constant.noteListData
                            .get(i)
                            .getNoteID()
                            .equals(item.getNoteAdd()
                                    .get(position)
                                    .getNoteID())){

                        if (Constant.noteListData
                                .get(i).getQuantity() == null) {

                            Constant.noteListData
                                    .get(i)
                                    .setQuantity("0");
                        }else {
                            Constant.noteListData
                                    .get(i)
                                    .setQuantity(String.valueOf(Integer.parseInt(Constant.noteListData.get(i).getQuantity()) + 1));
                        }
                    }
                }

//                for (int i = 0;i<namesAdd.size();i++){
//                    if (namesAdd.get(i).getNoteID().
//                            equals(item.getNoteRemove().get(position).getNoteID())) {
//                        namesAdd.get(i).setQuantity(String.valueOf(Integer.parseInt(namesAdd.get(i).getQuantity()) + 1));
//                    }
//                }

                adapterAdd.refresh(position);
            }
        });

        adapterAdd.setOnClickMinusListener(new IClickListenerDataOrBloo<Integer>() {
            @Override
            public void onClick(Integer position, boolean b) {
                for (int i = 0;i < Constant.noteListData.size();i++) {
                    if (Constant.noteListData
                            .get(i)
                            .getNoteID()
                            .equals(item.getNoteAdd()
                                    .get(position)
                                    .getNoteID())){

                        if (Constant.noteListData.get(i).getQuantity() != null) {
                            if (Integer.parseInt(Constant.noteListData
                                    .get(i).getQuantity()) > 1) {

                                Constant.noteListData
                                        .get(i)
                                        .setQuantity(String.valueOf(Integer.parseInt(Constant.noteListData.get(i).getQuantity()) - 1));
                            } else {
                                Constant.noteListData.remove(i);
                                item.getNoteAdd()
                                        .get(position).setQuantity(null);

                                for (int x = 0; x < namesAdd.size(); x++) {
                                    if (namesAdd.get(x).getNoteID().
                                            equals(item.getNoteAdd().get(position).getNoteID())) {
                                        namesAdd.remove(x);
                                    }
                                }

                                deleteGlo.add(item.getNoteAdd().get(position));
                            }
                        }
                    }
                }


                adapterAdd.refresh(position);
            }
        });
        recyclerViewAdd.setAdapter(adapterAdd);
    }
}
