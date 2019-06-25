package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by com_s on 05-Feb-17.
 */

public class RewardListOrderItemViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.txt_point)
    TextView txtPoint;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.txt_date)
    TextView txtDate;

    private CountDownTimer timer;
    View viewG;
    RewardListResultData item;
    private int typeUI = 0;
    private CommonRepository commonRepository;


    public RewardListOrderItemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(RewardListResultData item,int typeUI) {

        this.item = item;
        this.typeUI = typeUI;
        init();

    }

    private void init() {

        tvTitle.setText(item.getHeader());
        tvDesc.setText(item.getSubTitle());


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(item.getPoint()));
        txtPoint.setText(yourFormattedString);


        if (typeUI == 1) {
            txtDate.setVisibility(View.VISIBLE);
            if (item.getWithInPeriod() != null) {

                if (!item.getWithInPeriod().equals("0")) {
                    if (timer != null) {
                        timer.cancel();
                    }
                    timer = new CountDownTimer(Integer.parseInt(item.getTimeToCountDown()) * 1000, 1000) {
                        @Override
                        public void onTick(long millis) {
                            txtDate.setText("" + String.format("%02d:%02d:%02d",
                                    TimeUnit.MILLISECONDS.toHours(millis),
                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                            ));
                            item.setTimeToCountDown(String.valueOf(Integer.parseInt(item.getTimeToCountDown()) - 1));
                        }

                        @Override
                        public void onFinish() {
                            txtDate.setText("00:00:00");
                        }
                    };
                    timer.start();
                } else {
                    if (Constant.myReward == 0) {
                        txtDate.setText("ใช้ได้ 1 ครั้ง ภายใน " + item.getExpiredDate().substring(0, 10));
                    } else if (Constant.myReward == 1) {
                        txtDate.setText("ใช้ไปเมื่อ " + item.getUsedDate().substring(0, 10));
                    } else {
                        txtDate.setText("หมดอายุเมื่อ " + item.getExpiredDate().substring(0, 10));
                    }
                }
            }else {
                if (Constant.myReward == 0) {
                    txtDate.setText("ใช้ได้ 1 ครั้ง ภายใน " + item.getExpiredDate().substring(0, 10));
                } else if (Constant.myReward == 1) {
                    txtDate.setText("ใช้ไปเมื่อ " + item.getUsedDate().substring(0, 10));
                } else {
                    txtDate.setText("หมดอายุเมื่อ " + item.getExpiredDate().substring(0, 10));
                }
            }
        }else{
            txtDate.setVisibility(View.GONE);
        }

        commonRepository = new CommonRepository();

        commonRepository.getImage(item.getBranchImageUrl(), "2", item.getMainBranchID(), new IHttpCallback<List<ImageResultData>>() {
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
