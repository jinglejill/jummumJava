package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.NodeRecyclerViewAdapter;
import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteItemClick;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class OrderItemViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_totle)
    TextView tvTotle;
    @BindView(R.id.btn_min)
    TextView btnMin;
    @BindView(R.id.btn_plush)
    TextView btnPlush;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.qry_count)
    TextView txtQty;
    @BindView(R.id.btn_note)
    TextAwesome btn_note;
    @BindView(R.id.list_note)
    RecyclerView listNote;


    View viewG;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private MenuListResultData item;
    private NodeRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private IClickListener<NoteItemClick> noteItemClickListener;
    private IClickListener<NoteItemClick> noteItemTakeClickListener;
    private IClickListener<NoteItemClick> clearNoteItemTakeClickListener;
    private boolean expanded = false;
    private List<List<BranchAndCustomerTableResponseResultData>> tableData;
    private CommonRepository commonRepository;

    public OrderItemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);


    }

    public void setItem(MenuListResultData item,
                        int position,
                        List<List<BranchAndCustomerTableResponseResultData>> tableData) {
        this.item = item;
        this.tableData = tableData;
        init(position);

    }

    private void init(int position) {


        tvTitle.setText(item.getTitleThai());

        double sumprice = Double.parseDouble(item.getSpecialPrice()) * item.getQty();

        if (item.getNoteList() != null) {
            for (int i = 0; i < item.getNoteList().size(); i++) {
                for (NoteListResponseResultData d : item.getNoteList().get(i)) {
                    if (!d.getPrice().equals("0")) {
                        sumprice += Integer.parseInt(d.getPrice()) * Integer.parseInt(d.getQuantity());
                    }
                }
            }
        }

        int takePrice = 0;
        if (tableData != null) {
            takePrice = Integer.parseInt(tableData.get(0).get(0).getTakeAwayFee());
        }
        if (item.getTakeAway() != null) {
            for (NoteListResponseResultData take : item.getTakeAway()) {
                if (take.getTakeAway() != null) {
                    if (take.getTakeAway().equals("1")) {
                        sumprice += takePrice;
                    }
                }
            }
        }

        tvTotle.setText(Util.numberFormat(sumprice));
        txtQty.setText(String.valueOf(item.getQty()));

        //if (imgTitle.getDrawable() == null) {
        String imageUrl = Constant.BASE_URL + item.getMenuImageFolder() + "/Image/Menu/" + item.getImageUrl();
//        Util.loadImage(viewG.getContext(), imageUrl, imgTitle);
        // }


        commonRepository = new CommonRepository();

        commonRepository.getImage(item.getImageUrl(), "1", item.getBranchID(), new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response) {

                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                imgTitle.setImageBitmap(decode);
            }

            @Override
            public void onError(String message) {
                Log.i("debug = ", message);
            }
        });

        setQtynote(position);

        if (item.isExpand()) {
            open();
        }

    }


    public void setPlusClickListener(final IClickListener<Integer> listener, final int index) {

        btnPlush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(index);
            }
        });
    }

    public void setMinusClickListener(final IClickListener<Integer> listener, final int index) {

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(index);
            }
        });
    }

    public void setNodeClickListener(final IClickListener<Integer> listener, final int index, final int countNote) {

        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkShowNote(item, index);

                listener.onClick(index);
            }
        });
    }

    private void checkShowNote(MenuListResultData menu, int index) {

        Constant.menuNoteDataGlobal = menu;

        expanded = !expanded;

        item.setExpand(expanded);

        if (expanded) {
            open();

        } else {
            close();

        }


        setQtynote(index);
    }

    private void open() {
        btn_note.setText(R.string.fa_sort_up);
        Animation animation = new TranslateAnimation(0, 0, 0, 1);
        animation.setDuration(200);
        animation.setFillAfter(true);
        listNote.startAnimation(animation);
        listNote.setVisibility(View.VISIBLE);
    }

    private void close() {
        btn_note.setText(R.string.fa_sort_desc);
        Animation animation = new TranslateAnimation(0, 0, 1, 0);
        animation.setDuration(200);
        animation.setFillAfter(true);
        listNote.startAnimation(animation);
        listNote.setVisibility(View.GONE);
    }


    private void setQtynote(int position) {
        linearLayoutManager = new LinearLayoutManager(viewG.getContext());
        listNote.setLayoutManager(linearLayoutManager);
        adapter = new NodeRecyclerViewAdapter(item.getNoteList(), position, item.getTakeAway(), tableData,item);
        adapter.setOnClickListener(noteItemClickListener);
        adapter.setOnTakeClickListener(noteItemTakeClickListener);
        adapter.setClearNoteClickListener(clearNoteItemTakeClickListener);
        listNote.setAdapter(adapter);
    }

    public void setNoteItemClickListener(IClickListener<NoteItemClick> noteItemClickListener) {
        this.noteItemClickListener = noteItemClickListener;
    }

    public void setNoteItemTakeClickListener(IClickListener<NoteItemClick> noteItemTakeClickListener) {
        this.noteItemTakeClickListener = noteItemTakeClickListener;
    }

    public void setClearNoteItemTakeClickListener(IClickListener<NoteItemClick> clearNoteItemTakeClickListener) {
        this.clearNoteItemTakeClickListener = clearNoteItemTakeClickListener;
    }


}
