package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class DetialHotDealActivity extends BaseActivity {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.image_view_type)
    ImageView imageViewType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.more)
    TextAwesome more;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.btn_buy_now)
    TextView btnBuyNow;

    private HotDealData hotDealData;
    private CommonRepository commonRepository;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_hot_deal);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Parcelable parcelable = getIntent().getParcelableExtra("HotdealData");
        if (parcelable != null) {
            hotDealData = Parcels.unwrap(parcelable);
        }


        tvTitle.setText(hotDealData.getHeader());
        tvDesc.setText(hotDealData.getSubTitle());

        /*
        if (!hotDealData.getDiscountGroupMenuID().equals("0")
                && !hotDealData.getMainBranchID().equals("0")){
            btnBuyNow.setVisibility(View.VISIBLE);
        }else{
            btnBuyNow.setVisibility(View.GONE);
        }
        */

        if (!hotDealData.getShowOrderNow().equals("0")){
            btnBuyNow.setVisibility(View.VISIBLE);
        }else{
            btnBuyNow.setVisibility(View.GONE);
        }

        new CommonRepository().getImage(hotDealData.getImageUrl(), "3", "0", new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response) {

                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                imageViewType.setImageBitmap(decode);
            }

            @Override
            public void onError(String message) {
                Log.i("debug = ", message);
            }
        });

    }

    @OnClick(R.id.btn_back)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.btn_buy_now)
    public void onClickBuyNow() {

        commonRepository = new CommonRepository();
        showProgressDialog();
//        commonRepository.getOrderNow("1","50"
        commonRepository.getOrderNow(hotDealData.getMainBranchID(), hotDealData.getDiscountGroupMenuID()
                , new IHttpCallback<List<List<MenuListResultData>>>() {
            @Override
            public void onSuccess(List<List<MenuListResultData>> response) {

                if (response.get(2).get(0).getGoToPayOrMenu().equals("1"))
                {
                    if (response.get(0).size() > 0) {
                        Constant.reOrder = false;
                        MenuListResultData menuListResultData = new MenuListResultData();
                        menuListResultData = response.get(0).get(0);

                        hideProgressDialog();
                        Intent intent = new Intent(DetialHotDealActivity.this
                                , PaymentReOrderActivity.class);

                        List<OrderSummary> summaryLists = new ArrayList<>();
                        OrderSummary summaryList = new OrderSummary();
                        summaryList.setPrice(response.get(0).get(0).getSpecialPrice());
                        summaryList.setQty(Integer.parseInt(response.get(1).get(0).getQuantity()));
                        summaryList.setProductName(response.get(0).get(0).getTitleThai());
                        summaryList.setNoteName("");
                        summaryLists.add(summaryList);

                        OrderListResultData orderListResult = new OrderListResultData();

                        orderListResult.setBranchID(menuListResultData.getBranchID());
                        orderListResult.setMemberID(PreferenceManager.getInstance().getMemberId());
                        orderListResult.setCustomerTableID("1");


                        BranchResultData branchResultData = new BranchResultData();
                        branchResultData.setBranchID(menuListResultData.getBranchID());
                        branchResultData.setName(menuListResultData.getBranchName());
                        branchResultData.setTakeAwayFee("0");
                        branchResultData.setImageUrl(menuListResultData.getImageUrl());
                        List<BranchResultData> b1 = new ArrayList<>();
                        b1.add(branchResultData);
                        orderListResult.setBranch(b1);

                        List<OrderTaking2ResultData> orderTakings = new ArrayList<>();
                        OrderTaking2ResultData orderTaking2 = new OrderTaking2ResultData();
                        orderTaking2.setBranchID(menuListResultData.getBranchID());
                        orderTaking2.setQuantity(response.get(1).get(0).getQuantity());
                        orderTaking2.setMenuID(menuListResultData.getMenuID());
                        orderTaking2.setTakeAwayPrice(0);
                        orderTaking2.setNotePrice(0);
                        orderTaking2.setCustomerTableID("1");
                        orderTaking2.setPrice(menuListResultData.getPrice());
                        orderTaking2.setSpecialPrice(menuListResultData.getSpecialPrice());
                        orderTaking2.setTakeAway("0");
                        List<MenuListResultData> menus = new ArrayList<>();
                        menuListResultData.setNoteList(new ArrayList<List<NoteListResponseResultData>>());
                        menus.add(menuListResultData);
                        orderTaking2.setMenu(menus);
                        orderTaking2.setNotes(new ArrayList<NoteListResponseResultData>());
                        orderTakings.add(orderTaking2);
                        orderListResult.setOrderTaking(orderTakings);


                        intent.putExtra("Orders", Parcels.wrap(orderListResult));
                        intent.putExtra("SummaryList", Parcels.wrap(summaryLists));
                        intent.putExtra("Qty", Integer.parseInt(response.get(1).get(0).getQuantity()));
                        intent.putExtra("HotDeal", Parcels.wrap(hotDealData));

                        startActivity(intent);

                    }
                }
                else
                {
                    hideProgressDialog();
                    Intent intent = new Intent(DetialHotDealActivity.this
                            , MenuActivity.class);
                    HotDealData hotDealData = new HotDealData();
                    hotDealData.setHeader(hotDealData.getHeader());
                    hotDealData.setVoucherCode(hotDealData.getVoucherCode());
                    intent.putExtra("HotDeal", Parcels.wrap(hotDealData));


                    MenuListResultData menuListResultData = new MenuListResultData();
                    menuListResultData = response.get(0).get(0);
//                    intent.putExtra("BranchID", hotDealData.getMainBranchID());
                    intent.putExtra("BranchID", menuListResultData.getBranchID());
                    startActivity(intent);

                }
                hideProgressDialog();
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                Util.showToast(mainContent,message);
            }
        });

//        private HotDealData hotDeal;
//        private OrderListResultData orders;
//
//        hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));

    }
}
