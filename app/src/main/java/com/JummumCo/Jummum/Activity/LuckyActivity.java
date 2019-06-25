package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.LuckyDrawData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderTakingResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.DialogUtil;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LuckyActivity extends AppCompatActivity {
    /*
       eward rank คือ
         1 = excellent
       , 2 = awesome
       , 3 = good
       , 4 = boo ค่ะ
    */

    List<Integer> excellents = new ArrayList<>();
    List<Integer> awesomes = new ArrayList<>();
    List<Integer> goods = new ArrayList<>();
    List<Integer> boos = new ArrayList<>();

    List<Integer> integers;
    HashMap<String, List<Integer>> rankMap = new HashMap<>();

    String rank = "";
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.tc_lucky_count)
    TextView tcLuckyCount;
    @BindView(R.id.image_bg)
    ImageView imageBg;
    @BindView(R.id.layout_lucky)
    RelativeLayout layoutLucky;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_order_now)
    TextView tvOrderNow;
    @BindView(R.id.tv_text_lucky)
    TextView tvTextLucky;

    int luckyDrawCount = 0;
    int index = 0;

    String branchID;
    String memberID;
    String receiptID;

    Handler h;
    Runnable r;

    Handler h2;
    Runnable r2;

    List<List<LuckyDrawData>> luckyDrawData;
    CommonRepository commonRepository = new CommonRepository();
    DialogUtil dialogUtil;

    List<MenuListResultData> menuListResultData;
    SummaryResultData summaryResultData;
    int qty;
    int balance;
    boolean isRunning = false;
    boolean isRunning2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //make fully Android Transparent Status bar
//        if (Build.VERSION.SDK_INT >= 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }



        setContentView(R.layout.activity_lucky);
        ButterKnife.bind(this);

        initDefault();

        dialogUtil = new DialogUtil(this);

        rank = "1";
        luckyDrawCount = 5;

        layoutLucky.setVisibility(View.GONE);
        tvOrderNow.setVisibility(View.GONE);
        tvTextLucky.setVisibility(View.GONE);

        branchID = getIntent().getStringExtra("BranchID");
        receiptID = getIntent().getStringExtra("ReceiptID");
        memberID = PreferenceManager.getInstance().getMemberId();

        getLuckyDraw();

    }

    public static void setWindowFlag(android.app.Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void getLuckyDraw() {
        dialogUtil.showProgressDialog(getString(R.string.loading));
        commonRepository.getLuckyDraw(branchID, memberID, receiptID, new IHttpCallback<List<List<LuckyDrawData>>>() {
            @Override
            public void onSuccess(List<List<LuckyDrawData>> response) {
                luckyDrawData = response;
                dialogUtil.hideProgressDialog();
                render();
            }

            @Override
            public void onError(String message) {
                dialogUtil.hideProgressDialog();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            }
        });
    }

    private void initDefault() {
        excellents.add(R.drawable.giftboxexcellent00001);
        excellents.add(R.drawable.giftboxexcellent00002);
        excellents.add(R.drawable.giftboxexcellent00003);
        excellents.add(R.drawable.giftboxexcellent00004);
        excellents.add(R.drawable.giftboxexcellent00005);
        excellents.add(R.drawable.giftboxexcellent00006);
        excellents.add(R.drawable.giftboxexcellent00007);
        excellents.add(R.drawable.giftboxexcellent00008);
        excellents.add(R.drawable.giftboxexcellent00009);
        excellents.add(R.drawable.giftboxexcellent00010);
        excellents.add(R.drawable.giftboxexcellent00011);
        excellents.add(R.drawable.giftboxexcellent00012);
        excellents.add(R.drawable.giftboxexcellent00013);
        excellents.add(R.drawable.giftboxexcellent00014);
        excellents.add(R.drawable.giftboxexcellent00015);
        excellents.add(R.drawable.giftboxexcellent00016);
        excellents.add(R.drawable.giftboxexcellent00017);
        excellents.add(R.drawable.giftboxexcellent00018);
        excellents.add(R.drawable.giftboxexcellent00019);
        excellents.add(R.drawable.giftboxexcellent00020);
        excellents.add(R.drawable.giftboxexcellent00021);
        excellents.add(R.drawable.giftboxexcellent00022);
        excellents.add(R.drawable.giftboxexcellent00023);

        awesomes.add(R.drawable.giftboxawesome00001);
        awesomes.add(R.drawable.giftboxawesome00002);
        awesomes.add(R.drawable.giftboxawesome00003);
        awesomes.add(R.drawable.giftboxawesome00004);
        awesomes.add(R.drawable.giftboxawesome00005);
        awesomes.add(R.drawable.giftboxawesome00006);
        awesomes.add(R.drawable.giftboxawesome00007);
        awesomes.add(R.drawable.giftboxawesome00008);
        awesomes.add(R.drawable.giftboxawesome00009);
        awesomes.add(R.drawable.giftboxawesome00010);
        awesomes.add(R.drawable.giftboxawesome00011);
        awesomes.add(R.drawable.giftboxawesome00012);
        awesomes.add(R.drawable.giftboxawesome00013);
        awesomes.add(R.drawable.giftboxawesome00014);
        awesomes.add(R.drawable.giftboxawesome00015);
        awesomes.add(R.drawable.giftboxawesome00016);
        awesomes.add(R.drawable.giftboxawesome00017);
        awesomes.add(R.drawable.giftboxawesome00018);
        awesomes.add(R.drawable.giftboxawesome00019);
        awesomes.add(R.drawable.giftboxawesome00020);
        awesomes.add(R.drawable.giftboxawesome00021);
        awesomes.add(R.drawable.giftboxawesome00022);
        awesomes.add(R.drawable.giftboxawesome00023);

        goods.add(R.drawable.giftboxgood00001);
        goods.add(R.drawable.giftboxgood00002);
        goods.add(R.drawable.giftboxgood00003);
        goods.add(R.drawable.giftboxgood00004);
        goods.add(R.drawable.giftboxgood00005);
        goods.add(R.drawable.giftboxgood00006);
        goods.add(R.drawable.giftboxgood00007);
        goods.add(R.drawable.giftboxgood00008);
        goods.add(R.drawable.giftboxgood00009);
        goods.add(R.drawable.giftboxgood00010);
        goods.add(R.drawable.giftboxgood00011);
        goods.add(R.drawable.giftboxgood00012);
        goods.add(R.drawable.giftboxgood00013);
        goods.add(R.drawable.giftboxgood00014);
        goods.add(R.drawable.giftboxgood00015);
        goods.add(R.drawable.giftboxgood00016);
        goods.add(R.drawable.giftboxgood00017);
        goods.add(R.drawable.giftboxgood00018);
        goods.add(R.drawable.giftboxgood00019);
        goods.add(R.drawable.giftboxgood00020);
        goods.add(R.drawable.giftboxgood00021);
        goods.add(R.drawable.giftboxgood00022);
        goods.add(R.drawable.giftboxgood00023);

        boos.add(R.drawable.giftboxboo00001);
        boos.add(R.drawable.giftboxboo00002);
        boos.add(R.drawable.giftboxboo00003);
        boos.add(R.drawable.giftboxboo00004);
        boos.add(R.drawable.giftboxboo00005);
        boos.add(R.drawable.giftboxboo00006);
        boos.add(R.drawable.giftboxboo00007);
        boos.add(R.drawable.giftboxboo00008);
        boos.add(R.drawable.giftboxboo00009);
        boos.add(R.drawable.giftboxboo00010);
        boos.add(R.drawable.giftboxboo00011);
        boos.add(R.drawable.giftboxboo00012);
        boos.add(R.drawable.giftboxboo00013);
        boos.add(R.drawable.giftboxboo00014);
        boos.add(R.drawable.giftboxboo00015);
        boos.add(R.drawable.giftboxboo00016);
        boos.add(R.drawable.giftboxboo00017);
        boos.add(R.drawable.giftboxboo00018);
        boos.add(R.drawable.giftboxboo00019);
        boos.add(R.drawable.giftboxboo00020);
        boos.add(R.drawable.giftboxboo00021);
        boos.add(R.drawable.giftboxboo00022);
        boos.add(R.drawable.giftboxboo00023);

        rankMap.put("1", excellents);
        rankMap.put("2", awesomes);
        rankMap.put("3", goods);
        rankMap.put("4", boos);
    }

    private void render() {

        if (h2 != null) {
            h2.removeCallbacks(r2);
            isRunning2 = false;
        }

        index = 0;
        layoutLucky.setVisibility(View.GONE);
        tvOrderNow.setVisibility(View.GONE);
        tvTextLucky.setVisibility(View.GONE);
        integers = rankMap.get(luckyDrawData.get(0).get(0).getRewardRank());

        isRunning = true;
        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {

                int resID = integers.get(index);
                imageBg.setImageResource(resID);

//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                int screenHeight = displayMetrics.heightPixels+50;
//                int screenWidth = displayMetrics.widthPixels+50;
//                imageBg.getLayoutParams().height = screenHeight;
//                imageBg.getLayoutParams().width = screenWidth;
//                imageBg.requestLayout();


//                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(screenWidth,screenHeight);
//                imageBg.setLayoutParams(parms);


                index++;
                if (index == integers.size()) {
                    h.removeCallbacks(r);
                    h = null;

                    tvTextLucky.setVisibility(View.VISIBLE);
                    tvTextLucky.setText(luckyDrawData.get(0).get(0).getHeader());
                    luckyDrawCount = Integer.parseInt(luckyDrawData.get(1).get(0).getLuckyDrawCount());

                    /*
                    if (luckyDrawData.get(0).get(0).getVoucherCode() == null) {
                        tvOrderNow.setVisibility(View.GONE);
                    } else {
                        tvOrderNow.setVisibility(View.VISIBLE);
                    }
                    */
                    if (luckyDrawData.get(0).get(0).getShowOrderNow() == null){
                        tvOrderNow.setVisibility(View.GONE);
                    }else {
                        if (luckyDrawData.get(0).get(0).getShowOrderNow().equals("0")) {
                            tvOrderNow.setVisibility(View.GONE);
                        } else {
//                            if (luckyDrawData.get(0).get(0).getDiscountGroupMenuID().equals("0")){
//                                tvOrderNow.setVisibility(View.GONE);
//                            }else {
                                tvOrderNow.setVisibility(View.VISIBLE);
//                            }
                        }
                    }

                    renderBox();
                    isRunning = false;

                } else {
                    h.postDelayed(this, 150);
                }

            }
        };

        h.postDelayed(r, 150);

    }

    private void renderBox() {
        layoutLucky.setVisibility(View.VISIBLE);
        if (luckyDrawCount > 0)
        {

            tcLuckyCount.setText(luckyDrawCount + " more");

            isRunning2 = true;

            h2 = new Handler();
            r2 = new Runnable() {
                boolean big = false;

                @Override
                public void run() {
                    if (big) {
                        imageView.setImageResource(R.drawable.jummum_gift_box_pop);
                    } else {
                        imageView.setImageResource(R.drawable.jummum_gift_box_normal);
                    }
                    big = !big;
                    h2.postDelayed(this, 370);
                }
            };
            h2.postDelayed(r2, 370);
        }
        else
        {
            if(h2 != null && r2 != null)
            {
                h2.removeCallbacks(r2);
            }

            isRunning2 = false;
            tcLuckyCount.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.layout_back)
    public void onViewClickedBack() {

        onBackPressed();

    }

    @OnClick(R.id.tv_order_now)
    public void onViewClickedOrderNow() {


        dialogUtil.showProgressDialog(getString(R.string.loading));

        commonRepository.getOrderNow(branchID, luckyDrawData.get(0).get(0).getDiscountGroupMenuID(), new IHttpCallback<List<List<MenuListResultData>>>() {
            @Override
            public void onSuccess(List<List<MenuListResultData>> response) {

                if (response.get(2).get(0).getGoToPayOrMenu().equals("1")) {
                    menuListResultData = response.get(0);

                    List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                    notes.add(new ArrayList<NoteListResponseResultData>());
                    menuListResultData.get(0).setNoteList(notes);
                    qty = Integer.parseInt(response.get(1).get(0).getQuantity());
                    menuListResultData.get(0).setQty(qty);
                    menuListResultData.get(0).setQuantity(String.valueOf(qty));
                    keepData();
                }
                else
                {
                    Intent intent = new Intent(LuckyActivity.this
                            , MenuActivity.class);
                    HotDealData hotDealData = new HotDealData();
                    hotDealData.setHeader(luckyDrawData.get(0).get(0).getHeader());
                    hotDealData.setVoucherCode(luckyDrawData.get(0).get(0).getVoucherCode());
                    intent.putExtra("HotDeal", Parcels.wrap(hotDealData));
                    intent.putExtra("BranchID", branchID);
//                    intent.putExtra("BranchID", response.get(0).get(0).getBranchID());
                    startActivity(intent);
                }

            }

            @Override
            public void onError(String message) {
                dialogUtil.hideProgressDialog();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                onBackPressed();
            }
        });

//        intent.putExtra("Orders", Parcels.wrap(menuListResultData));
//        intent.putExtra("Balance", sumPrice);
//        intent.putExtra("Qty", qty);

    }

    private void keepData() {

        List<OrderTakingResultData> orderTakingResultData = new ArrayList<>();
        List<List<NoteListResponseResultData>> noteLists = new ArrayList<>();
        summaryResultData = new SummaryResultData();
        balance = 0;

        int trackingID = 0;
        for (MenuListResultData menu : menuListResultData) {
            for (int a = 0; a < menuListResultData.get(0).getQty(); a++) {
                OrderTakingResultData order = new OrderTakingResultData();
                trackingID -= 1;

                balance = (qty * Integer.parseInt(menu.getPrice()));

                order.setOrderTakingID(trackingID);
                order.setQuantity(qty);
                order.setModifiedUser(PreferenceManager.getInstance().getUserName());
                order.setPrice(Integer.parseInt(menu.getPrice()));
                order.setMenuID(Integer.parseInt(menu.getMenuID()));
                order.setBranchID(Integer.parseInt(branchID));
                order.setModifiedDate(Util.getModifireDate());

                order.setSpecialPrice(Integer.parseInt(menu.getSpecialPrice()));

                orderTakingResultData.add(order);

                noteLists.add(new ArrayList<NoteListResponseResultData>());


            }

            menuListResultData.get(0).setNoteList(noteLists);
        }

        summaryResultData.setOrderTaking(orderTakingResultData);
        summaryResultData.setVoucherCode(luckyDrawData.get(0).get(0).getVoucherCode());
        summaryResultData.setBranchID(branchID);
        summaryResultData.setUserAccountID(memberID);

        commonRepository.getSummary(summaryResultData, new IHttpCallback<List<List<SummaryResponseResultData>>>() {
            @Override
            public void onSuccess(List<List<SummaryResponseResultData>> response) {
                Constant.Omise_Pkey = response.get(2).get(0).getOmisePublicKey();
                List<SummaryResponseResultData> summaryData = response.get(2);

                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }

                dialogUtil.hideProgressDialog();
                HotDealData hotDealData = new HotDealData();
                hotDealData.setHeader(luckyDrawData.get(0).get(0).getHeader());
                hotDealData.setVoucherCode(luckyDrawData.get(0).get(0).getVoucherCode());


                Constant.menuListResultDataGlobal = menuListResultData;

                Intent intent = new Intent(LuckyActivity.this, PaymentActivity.class);
                intent.putExtra("Balance", balance);
                intent.putExtra("Qty", qty);
                intent.putExtra("Summary", Parcels.wrap(summaryData));
                intent.putExtra("VoucherCode", luckyDrawData.get(0).get(0).getVoucherCode());
                intent.putExtra("TableQR", getIntent().getParcelableExtra("TableQR"));
                intent.putExtra("SummaryOrder", Parcels.wrap(summaryResultData));
                intent.putExtra("BranchID", branchID);
                intent.putExtra("HotDeal", Parcels.wrap(hotDealData));
                startActivity(intent);
            }

            @Override
            public void onError(String message) {
                dialogUtil.hideProgressDialog();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            }
        });

    }

    @OnClick(R.id.image_view)
    public void onViewClickedLucky() {
        if (luckyDrawCount > 0) {
            getLuckyDraw();
        }
    }

    @Override
    protected void onPause() {
        if (h != null) {
            h.removeCallbacks(r);
        }
        if (h2 != null) {
            h2.removeCallbacks(r2);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (h != null && isRunning) {
            h.postDelayed(r, 0);
        }
        if (h2 != null && isRunning2) {
            h2.postDelayed(r2, 0);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (h != null) {
            h.removeCallbacks(r);
        }
        if (h2 != null) {
            h2.removeCallbacks(r2);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("HotDeal", true);
        startActivity(intent);
    }


}
