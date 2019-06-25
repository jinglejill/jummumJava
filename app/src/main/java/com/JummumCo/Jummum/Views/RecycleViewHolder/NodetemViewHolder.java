package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.android.jummum.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class NodetemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.btn_remove)
    ImageView btnRemove;
    @BindView(R.id.txt_note)
    TextView txtNote;
    @BindView(R.id.btn_cap)
    ImageView btnCap;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.txt_price_add)
    TextView txtPriceAdd;

    View viewG;
    private int num;
    private List<NoteListResponseResultData> data;
    private NoteListResponseResultData take;
    private boolean a = false;
    private List<List<BranchAndCustomerTableResponseResultData>> tableData;

    public NodetemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(int num,
                        List<NoteListResponseResultData> data,
                        NoteListResponseResultData take,
                        List<List<BranchAndCustomerTableResponseResultData>> tableData) {
        this.num = num;
        this.data = data;
        this.take = take;
        this.tableData = tableData;
        init();
    }

    private void init() {

        txtNum.setText(String.valueOf(num));

        List<String> namesAdd = new ArrayList<>();
        List<String> namesRemove = new ArrayList<>();

        int notePrice = 0;
        String prefixAdd = "";
        String prefixNo = "";
        for (NoteListResponseResultData d: data) {
            if (d.getType().equals("-1"))
            {
                prefixNo = d.getPrefix();
                if(d.getQuantity().equals("1"))
                {
                    namesRemove.add(d.getName());
                }
                else
                {
                    namesRemove.add(d.getName()+"("+d.getQuantity()+")");
                }
            }
            else
            {
                prefixAdd = d.getPrefix();
                if(d.getQuantity().equals("1"))
                {
                    namesAdd.add(d.getName());
                }
                else
                {
                    namesAdd.add(d.getName()+"("+d.getQuantity()+")");
                }
            }

            if(!d.getPrice().equals("0")){
                notePrice += Integer.parseInt(d.getPrice())  * Integer.parseInt(d.getQuantity());
            }
            else
            {
            }
        }

        if(notePrice != 0){

            txtPriceAdd.setVisibility(View.VISIBLE);
            txtPriceAdd.setText("+"+notePrice);
        }else{
            txtPriceAdd.setVisibility(View.GONE);
        }


        if (namesAdd.size() > 0) {
//            txtNote.append(Html.fromHtml("<u>เพิ่ม</u> "));
            txtNote.append(Html.fromHtml("<u>" + prefixAdd + "</u> "));
            txtNote.append(TextUtils.join(", ", namesAdd));
        }

        if (namesRemove.size() > 0){
            if (namesAdd.size() > 0) {
                txtNote.append(Html.fromHtml("&nbsp;<u>"+ prefixNo  + "</u> "));
            }else{
                txtNote.append(Html.fromHtml("<u>ไม่ใส่</u> "));
            }
            txtNote.append(TextUtils.join(", ", namesRemove));
        }


        if (take.getTakeAway() != null) {
            if (take.getTakeAway().equals("0")) {
                btnCap.setImageResource(R.drawable.takeout);
                a = false;
            } else {
                btnCap.setImageResource(R.drawable.takeout1);
                a = true;
            }
        }



    }


    public void setItemClickListener(final View.OnClickListener clickListener){
        txtNote.setOnClickListener(clickListener);

    }

    public void setItemTakeClickListener(final View.OnClickListener clickListener){
        btnCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onClick(v);
            }
        });
    }

    public void setClearNoteClickListener(final View.OnClickListener clickListener){
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view);
            }
        });
    }


}
