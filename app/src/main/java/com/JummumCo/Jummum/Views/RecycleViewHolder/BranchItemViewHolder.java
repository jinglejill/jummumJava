package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class BranchItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list_item)
    LinearLayout listItem;

    private CommonRepository commonRepository;
    View viewG;

    private BranchAndCustomerTableResponseResultData item;
    private int qty;
    private double total;
    private  String view;

    public BranchItemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(BranchAndCustomerTableResponseResultData item,String view) {

        this.item = item;
        this.view = view;
        init();

    }

    private void init() {


        if(view.equals("branch")) {
            commonRepository = new CommonRepository();
            tvTitle.setText(item.getName());

            commonRepository.getImage(item.getImageUrl(), "2", item.getBranchID(), new IHttpCallback<List<ImageResultData>>() {
                @Override
                public void onSuccess(List<ImageResultData> response) {

                    byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                    Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                    imgTitle.setImageBitmap(decode);
                }

                @Override
                public void onError(String message) {
                    Log.i("debug = ", message);
                }
            });
        }else{
            imgTitle.setVisibility(View.GONE);
            tvTitle.setText(item.getTableName());
        }



    }


    public void setItemClickListener(final IClickListener<Integer> clickListener, final int position) {

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }

}
