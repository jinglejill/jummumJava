package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.ImageMenuBaseData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class MenuItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_qty)
    TextView tvQty;
    @BindView(R.id.layout_qty_total)
    RelativeLayout layoutQtyTotal;
    @BindView(R.id.tv_totle)
    TextView tvTotle;
    @BindView(R.id.tv_totle_dis)
    TextView tvTotleDis;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.img_title_f)
    ImageView imgTitleF;
    @BindView(R.id.tv_title_f)
    TextView tvTitleF;
    @BindView(R.id.tv_qty_f)
    TextView tvQtyF;
    @BindView(R.id.layout_qty_total_f)
    RelativeLayout layoutQtyTotalF;

    @BindView(R.id.tv_totle_f)
    TextView tvTotleF;
    @BindView(R.id.tv_totle_dis_f)
    TextView tvTotleDisF;


    View viewG;
    @BindView(R.id.list_item_f)
    CardView listItemF;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_bar_f)
    ProgressBar progressBarF;


    private MenuListResultData item;
    private int qty;
    private double total;
    private CommonRepository commonRepository;
    private String img = "";


    public MenuItemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(MenuListResultData item) {

        this.item = item;
        init();

    }

    private void init() {

        if (item.getMenuTypeID().equals("0"))
        {

            listItem.setVisibility(View.GONE);
            listItemF.setVisibility(View.VISIBLE);

            if (item.getQty() > 0)
            {
                Log.d("quantity","quantity");
                layoutQtyTotalF.setVisibility(View.VISIBLE);
            }
            else
            {
                layoutQtyTotalF.setVisibility(View.GONE);
            }




            Double price = Double.parseDouble(item.getPrice());
            Double spPrice = Double.parseDouble(item.getSpecialPrice());

            tvTotleF.setText("฿ " + Util.numberFormat(price));
            if (!price.equals(spPrice))
            {
                tvTotleF.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tvTotleDisF.setVisibility(View.VISIBLE);
                tvTotleDisF.setText("฿ " + Util.numberFormat(spPrice));
            }
            else
            {
                tvTotleDisF.setVisibility(View.GONE);
            }

            tvTitleF.setText(item.getTitleThai());
            tvQtyF.setText(String.valueOf(item.getQty()));
            final String imageUrl = Constant.BASE_URL + item.getMenuImageFolder() + "/Image/Menu/" + item.getImageUrl();

            if(item.getImageLoaded() == 0)
            {
                progressBarF.setVisibility(View.VISIBLE);
                Util.downloadImage(viewG.getContext(), imgTitleF, progressBarF, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID(),item);
                Log.d("recommended","menuLoading:"+item.getTitleThai());
            }
            else
            {
                progressBarF.setVisibility(View.GONE);
//                imgTitleF.setImageBitmap(item.getImageBitmap());
                for (ImageMenuBaseData data: Constant.imageMenuBaseData64) {
                    if (data.getMenuId().equals(item.getMenuID())) {
                        byte[] decodeString = Base64.decode(data.getImageBase64(), Base64.DEFAULT);
                        Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                        imgTitleF.setImageBitmap(decode);
                    }
                }
                Log.d("recommended","menuLoaded"+item.getTitleThai());
            }

//                if (item.getImageUrl() != "" || item.getImageUrl() == "")
//                {
//                    if (Constant.imageMenuBaseData64.size() > 0)
//                    {
//                        boolean isImage = false;
//                        for (ImageMenuBaseData data: Constant.imageMenuBaseData64) {
//                            if (data.getMenuId().equals(item.getMenuID())) {
//                                isImage = true;
//                                progressBarF.setVisibility(View.GONE);
//                                byte[] decodeString = Base64.decode(data.getImageBase64(), Base64.DEFAULT);
//                                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                                imgTitleF.setImageBitmap(decode);
//                            }
//                        }
//                        if (!isImage) {
//                            Util.loadImage3(viewG.getContext(), imgTitleF, progressBarF, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID());
//                        }
//                    }
//                    else
//                    {
//                        Util.loadImage3(viewG.getContext(), imgTitleF, progressBarF, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID());
//                    }
//                }
        }
        else
        {

            listItem.setVisibility(View.VISIBLE);
            listItemF.setVisibility(View.GONE);

            if (item.getQty() > 0) {
                layoutQtyTotal.setVisibility(View.VISIBLE);
            } else {
                layoutQtyTotal.setVisibility(View.GONE);
            }




            Double price = Double.parseDouble(item.getPrice());
            Double spPrice = Double.parseDouble(item.getSpecialPrice());
            tvTotle.setText("฿ " + Util.numberFormat(price));
            if (!price.equals(spPrice)) {
                tvTotle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tvTotleDis.setVisibility(View.VISIBLE);
                tvTotleDis.setText("฿ " + Util.numberFormat(spPrice));

            } else {
                tvTotleDis.setVisibility(View.GONE);
            }

            tvTitle.setText(item.getTitleThai());
            tvQty.setText(String.valueOf(item.getQty()));
            String imageUrl = Constant.BASE_URL + item.getMenuImageFolder() + "/Image/Menu/" + item.getImageUrl();


            if(item.getImageLoaded() == 0)
            {
                progressBar.setVisibility(View.VISIBLE);
                Util.downloadImage(viewG.getContext(), imgTitle, progressBar, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID(),item);
                Log.d("general","menuLoading:"+item.getTitleThai());
            }
            else
            {
                progressBar.setVisibility(View.GONE);
//                imgTitle.setImageBitmap(item.getImageBitmap());
                for (ImageMenuBaseData data: Constant.imageMenuBaseData64) {
                    if (data.getMenuId().equals(item.getMenuID())) {
                        byte[] decodeString = Base64.decode(data.getImageBase64(), Base64.DEFAULT);
                        Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                        imgTitle.setImageBitmap(decode);
                    }
                }

                Log.d("general","menuLoaded"+item.getTitleThai());
            }

//            if (item.getImageUrl() != "") {
//                if (Constant.imageMenuBaseData64.size() > 0){
//                    boolean isImage = false;
//                    for (ImageMenuBaseData data: Constant.imageMenuBaseData64) {
//                        if (data.getMenuId().equals(item.getMenuID())) {
//                            isImage = true;
//                            progressBar.setVisibility(View.GONE);
//                            byte[] decodeString = Base64.decode(data.getImageBase64(), Base64.DEFAULT);
//                            Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                            imgTitle.setImageBitmap(decode);
//
//                        }
//                    }
//                    if (!isImage) {
//                        Util.loadImage3(viewG.getContext(), imgTitle, progressBarF, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID());
//                    }
//                }
//                else
//                {
//                    Util.loadImage3(viewG.getContext(), imgTitle, progressBarF, item.getImageUrl(), item.getBranchID(), "1",item.getMenuID());
//                }
//            }
        }
    }


    public void setItemClickListener(final IClickListener<Integer> clickListener, final int position) {

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });

        listItemF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }
}
