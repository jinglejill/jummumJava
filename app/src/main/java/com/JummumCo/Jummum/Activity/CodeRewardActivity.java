package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.AppBarLayout;
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
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class CodeRewardActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_point)
    TextView txtPoint;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.txt_point2)
    TextView txtPoint2;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.btn_buy_now)
    TextView btnBuyNow;
    @BindView(R.id.more)
    TextAwesome more;
    @BindView(R.id.tv_desc_law)
    TextView tvDescLaw;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.cart)
    TextView cart;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.img_qr)
    ImageView imgQr;

    private RewardListResultData codeReward;
    private RewardListResultData reward;
    private CommonRepository commonRepository;
    private CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_reward);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (getIntent().getParcelableExtra("Reward") != null) {
            codeReward = Parcels.unwrap(getIntent().getParcelableExtra("RewardCode"));
            reward = Parcels.unwrap(getIntent().getParcelableExtra("Reward"));
        }

        setView();
    }

    private void setView() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(codeReward.getRemainingPoint()));
        txtPoint.setText(yourFormattedString);

//        txtPoint.setText(codeReward.getRemainingPoint());
        tvTitle.setText(reward.getHeader());
        tvDesc.setText(reward.getSubTitle());
        txtPoint2.setText(codeReward.getPoint());
        tvCode.setText(codeReward.getCode());
        tvDate.setText(reward.getUsingEndDate().substring(0,16));
        tvDescLaw.setText(reward.getTermsConditions());

        /*
        if (!codeReward.getMainBranchID().equals("0")
                && !codeReward.getDiscountGroupMenuID().equals("0")) {
            btnBuyNow.setVisibility(View.VISIBLE);
        } else {
            btnBuyNow.setVisibility(View.GONE);
        }
        */
        if (!codeReward.getShowOrderNow().equals("0")) {
            btnBuyNow.setVisibility(View.VISIBLE);
        } else {
            btnBuyNow.setVisibility(View.GONE);
        }


        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(Integer.parseInt(reward.getWithInPeriod2()) * 1000, 1000) {
            @Override
            public void onTick(long millis) {
                cart.setText("" + String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                ));


                reward.setWithInPeriod2(String.valueOf(Integer.parseInt(reward.getWithInPeriod2()) - 1));
            }

            @Override
            public void onFinish() {
                cart.setText("00:00:00");
            }
        };
        timer.start();
    }

    @OnClick(R.id.btn_buy_now)
    public void onClickBuyNow() {
        if (!codeReward.getMainBranchID().equals("0")
                && !codeReward.getDiscountGroupMenuID().equals("0")) {
            buyNow();
        }
    }

    private void buyNow() {
        commonRepository = new CommonRepository();
        showProgressDialog();
        commonRepository.getOrderNow(codeReward.getMainBranchID(), codeReward.getDiscountGroupMenuID()
                , new IHttpCallback<List<List<MenuListResultData>>>() {
                    @Override
                    public void onSuccess(List<List<MenuListResultData>> response) {

                        if (response.get(2).get(0).getGoToPayOrMenu().equals("2")){

                            Intent intent = new Intent(CodeRewardActivity.this
                                    , MenuActivity.class);
                            intent.putExtra("BranchID", codeReward.getMainBranchID());
                            startActivity(intent);
                        }else {
                            if (response.get(0).size() > 0) {
                                Constant.reOrder = false;
                                MenuListResultData menuListResultData = new MenuListResultData();
                                menuListResultData = response.get(0).get(0);

                                hideProgressDialog();
                                Intent intent = new Intent(CodeRewardActivity.this
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

                                startActivity(intent);

                            }
                        }
                    }

                    @Override
                    public void onError(String message) {
                        hideProgressDialog();
                        message = "Cannot connect to server";
                        Util.showToast(mainContent, message);
                    }
                });
    }

    @OnClick(R.id.btn_back)
    public void onClickBack() {
        finish();
    }
}
