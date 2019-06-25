package com.JummumCo.Jummum.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.btn_finish)
    TextView btnFinish;

    @BindView(R.id.save_pic)
    Button savePic;
    @BindView(R.id.tv_thx_msg)
    TextView tvThxMsg;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.tc_lucky_count)
    TextView tcLuckyCount;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.layout_lucky)
    RelativeLayout layoutLucky;
    @BindView(R.id.save_pic_and_order_buffet)
    Button savePicAndOrderBuffet;
    @BindView(R.id.order_buffet)
    Button orderBuffet;

    boolean isShowOrderBuffet = false;

    private String TAG = "SAVE_IMAGE";

    private Handler h;
    private Runnable r;

    private List<List<PayResponseResultData>> menuListResultData;
    private List<OrderSummary> orderLists;
    private List<SummaryResponseResultData> summayData;
    private HotDealData hotDeal;
    String buffetReceiptID;

    PayResponseResultData thxItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        Constant.menuListResultDataGlobal = new ArrayList<>();

        Parcelable parcelable = getIntent().getParcelableExtra("Orders");
        if (parcelable != null) {
            menuListResultData = Parcels.unwrap(parcelable);
            orderLists = Parcels.unwrap(getIntent().getParcelableExtra("OrderList"));
            summayData = Parcels.unwrap(getIntent().getParcelableExtra("Summary"));
            if (getIntent().getParcelableExtra("HotDeal") != null) {
                hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
            }

            if (menuListResultData.size() > 2) {
                thxItem = menuListResultData.get(5).get(0);
            } else {
                thxItem = menuListResultData.get(5).get(0);
            }


            tvThxMsg.setText(thxItem.getThankYouText());
            isShowOrderBuffet = thxItem.getShowOrderBuffetButton();
            buffetReceiptID = thxItem.getBuffetReceiptID();

            //if (!isShowOrderBuffet) {
                String luckyCount = menuListResultData.get(4).get(0).getLuckyDrawCount();

                if (luckyCount != null && Integer.parseInt(luckyCount) > 0) {
                    tcLuckyCount.setText(String.format("You've got %s tickets", luckyCount));
                    h = new Handler();
                    r = new Runnable() {
                        boolean big = false;

                        @Override
                        public void run() {
                            if (big) {
                                imageView.setImageResource(R.drawable.jummum_gift_box_pop);
                            } else {
                                imageView.setImageResource(R.drawable.jummum_gift_box_normal);
                            }
                            big = !big;
                            h.postDelayed(this, 370);
                        }
                    };

                } else {
                    layoutLucky.setVisibility(View.GONE);
                }
//            } else {
//                layoutLucky.setVisibility(View.GONE);
//            }


            savePic.setVisibility(View.GONE);
            savePicAndOrderBuffet.setVisibility(View.GONE);
            orderBuffet.setVisibility(View.GONE);

//            isShowOrderBuffet = true;
//            buffetReceiptID = "192";

            if (isShowOrderBuffet) {
                savePicAndOrderBuffet.setVisibility(View.VISIBLE);
                orderBuffet.setVisibility(View.VISIBLE);
            } else {
                savePic.setVisibility(View.VISIBLE);
            }


        }
    }


    @OnClick(R.id.layout_lucky)
    public void onViewClickedLucky() {
        Intent intent = new Intent(PaySuccessActivity.this, LuckyActivity.class);
        intent.putExtra("BranchID", menuListResultData.get(0).get(0).getBranchID());
        intent.putExtra("ReceiptID", menuListResultData.get(3).get(0).getReceiptID());
        intent.putExtra("TableQR", getIntent().getParcelableExtra("TableQR"));
        startActivity(intent);
    }

    @OnClick(R.id.btn_finish)
    public void onViewClickedBack() {

        Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("HotDeal", true);
        startActivity(intent);

    }

    @OnClick({R.id.save_pic, R.id.save_pic_and_order_buffet})
    public void onViewClickedSavePic() {
        //ActivityCompat.finishAffinity(this);

        if (isStoragePermissionGranted()) {
            goSave();
        }

    }

    @OnClick({R.id.order_buffet})
    public void onViewClickedOrderBuffet() {
        orderBuffet();

    }

    private void goSave() {

        Intent intent = new Intent(PaySuccessActivity.this, SaveReceiptActivity.class);
        intent.putExtra("Orders", getIntent().getParcelableExtra("Orders"));
        intent.putExtra("OrderList", getIntent().getParcelableExtra("OrderList"));
        intent.putExtra("Summary", Parcels.wrap(summayData.get(0)));
        intent.putExtra("Qty", getIntent().getIntExtra("Qty", 0));
        intent.putExtra("HotDeal", Parcels.wrap(hotDeal));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));




        startActivityForResult(intent, Constant.REQUEST_CODE_SAVE_RECEIPT);

    }

    private void orderBuffet() {

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("BuffetReceiptID", buffetReceiptID);
        intent.putExtra("BranchID", menuListResultData.get(0).get(0).getBranchID());
        intent.putExtra("TableQR", getIntent().getParcelableExtra("TableQR"));
        startActivity(intent);

    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
//            goSave();
//
//        }
//    }

    @Override
    public void onBackPressed() {
        onViewClickedBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_SAVE_RECEIPT:
                    if (isShowOrderBuffet) {
                        orderBuffet();
                    } else {
                        onViewClickedBack();
                    }

                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        if (h != null) {
            h.removeCallbacks(r);
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        if (h != null) {
            h.postDelayed(r, 0);
        }

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (h != null) {
            h.removeCallbacks(r);
        }
        super.onDestroy();
    }

    private void removeTimer() {
        if (h != null) {
            h.removeCallbacks(r);
        }
    }
}
