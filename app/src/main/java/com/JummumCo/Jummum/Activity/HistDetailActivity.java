package com.JummumCo.Jummum.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.OrderPaymentAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.DisputeResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.Rating;
import com.JummumCo.Jummum.Model.RatingResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.omise.android.CardNumber;
import co.omise.android.models.CardBrand;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;
import static com.JummumCo.Jummum.Activity.PaymentActivity.setListViewHeightBasedOnChildren;

public class HistDetailActivity extends AppCompatActivity {


    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.txt_name_market)
    TextView txtNameMarket;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.txt_qty)
    TextView txtQty;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.show_total_amount)
    LinearLayout showTotalAmount;
    @BindView(R.id.special_price_discount_title)
    TextView specialPriceDiscountTitle;
    @BindView(R.id.special_price_discount)
    TextView specialPriceDiscount;
    @BindView(R.id.show_special_discount)
    LinearLayout showSpecialDiscount;
    @BindView(R.id.discount_program_title)
    TextView discountProgramTitle;
    @BindView(R.id.discount_program_value)
    TextView discountProgramValue;
    @BindView(R.id.show_discount_program)
    LinearLayout showDiscountProgram;
    @BindView(R.id.txt_code_promotion)
    TextView txtCodePromotion;
    @BindView(R.id.btn_pomotion)
    Button btnPomotion;
    @BindView(R.id.layout_promotion)
    LinearLayout layoutPromotion;
    @BindView(R.id.txt_code_promotion_code)
    EditText txtCodePromotionCode;
    @BindView(R.id.btn_pomotion_code)
    Button btnPomotionCode;
    @BindView(R.id.txt_label_promotion_code)
    TextView txtLabelPromotionCode;
    @BindView(R.id.layout_promotion_code)
    LinearLayout layoutPromotionCode;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.txt_name_discount)
    TextView txtNameDiscount;
    @BindView(R.id.txt_delete)
    TextView txtDelete;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.layout_discount)
    LinearLayout layoutDiscount;
    @BindView(R.id.txt_total_2_title)
    TextView txtTotal2Title;
    @BindView(R.id.txt_total_2)
    TextView txtTotal2;
    @BindView(R.id.show_after_discount)
    LinearLayout showAfterDiscount;
    @BindView(R.id.txt_service_charge_lb)
    TextView txtServiceChargeLb;
    @BindView(R.id.txt_service_charge)
    TextView txtServiceCharge;
    @BindView(R.id.show_service_charge)
    LinearLayout showServiceCharge;
    @BindView(R.id.txt_vat_lb)
    TextView txtVatLb;
    @BindView(R.id.txt_vat)
    TextView txtVat;
    @BindView(R.id.show_vat)
    LinearLayout showVat;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.show_net_total)
    LinearLayout showNetTotal;
    @BindView(R.id.lucky_draw_title)
    TextView luckyDrawTitle;
    @BindView(R.id.show_lucky_draw_count)
    LinearLayout showLuckyDrawCount;
    @BindView(R.id.before_vat)
    TextView beforeVat;
    @BindView(R.id.show_before_vat)
    LinearLayout showBeforeVat;
    @BindView(R.id.txt_payment_method)
    TextView txtPaymentMethod;
    @BindView(R.id.layout_include_summary)
    LinearLayout layoutIncludeSummary;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.txt_summary)
    TextView txtSummary;
    @BindView(R.id.name_refund)
    TextView nameRefund;
    @BindView(R.id.price_refund)
    TextView priceRefund;
    @BindView(R.id.name_detail)
    TextView nameDetail;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.layout_refund)
    LinearLayout layoutRefund;
    @BindView(R.id.lb_rate)
    TextView lbRate;
    @BindView(R.id.rate_sta)
    MaterialRatingBar rateSta;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.layout_bottom_main)
    RelativeLayout layoutBottomMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;
    @BindView(R.id.layout_confirm_negotiate)
    LinearLayout layoutConfirmNegotiate;
    @BindView(R.id.layout_transfer)
    LinearLayout layoutTransfer;

    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btn_negotiate)
    Button btnNegotiate;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.btn_transfer)
    Button btnTransfer;


    private OrderPaymentAdapter adapter;
    private List<OrderSummary> summaryList;
    private OrderListResultData orders;
    private CommonRepository commonRepository;
    private double rate;
    private int sumQty;
    private boolean addComment = false;
    private AlertDialog alertDialog;
    private List<DisputeResultData> disputeResultData;
    private SummaryResponseResultData summayData;
    private double balance, discount, sumBalance, serviceC, vat;
    private Rating rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        setContentView(R.layout.activity_hist_detail);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

        orders = Parcels.unwrap(getIntent().getParcelableExtra("Orders"));
        summaryList = Parcels.unwrap(getIntent().getParcelableExtra("SummaryList"));
        summayData = Parcels.unwrap(getIntent().getParcelableExtra("OrderSummry"));

        commonRepository = new CommonRepository();



        SetRecycleView();
        getRating();


        //show confirmNegotiate button
        if(orders.getShowConfirmNegotiateCancelButton() == 1)
        {
            layoutConfirmNegotiate.setVisibility(View.VISIBLE);
        }
        else
        {
            layoutConfirmNegotiate.setVisibility(View.GONE);
        }

        //show transfer button
        if(orders.getShowTransferButton() == 1)
        {
            layoutTransfer.setVisibility(View.VISIBLE);
        }
        else
        {
            layoutTransfer.setVisibility(View.GONE);
        }


        rateSta.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                rate = rating;
            }
        });
    }


    private void getBottomDialog()
    {
        if(orders.getShowCancelDisputeButton() == 3)//delete
        {
            commonRepository.getUpdateReceiptAndPromoCode(
                    orders.getReceiptID(),
                    PreferenceManager.getInstance().getUserName(),
                    Util.getModifireDate(),
                    PreferenceManager.getInstance().getToken(),
                    "update receipt and promoCode (delete order)",
                    new IHttpCallback<String>() {
                        @Override
                        public void onSuccess(String response) {
                            //go back
//                            onBackPressed();
                            Intent intent = new Intent(HistDetailActivity.this
                                    , HistoryOrderActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(String message) {
//                            message = "Cannot connect to server";
                            Util.showToast(mainContent, message);
                        }
                    });
        }
        else if(orders.getShowCancelDisputeButton() == 4)//confirm transfer detail
        {

        }
        else if(orders.getShowCancelDisputeButton() == 1 || orders.getShowCancelDisputeButton() == 2)
        {
            final Dialog dialog = new Dialog(HistDetailActivity.this);
            dialog.setContentView(R.layout.diaolog_order);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


            final Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
            final Button btnNo = (Button) dialog.findViewById(R.id.btn_no);
            final TextView txtDetial = (TextView) dialog.findViewById(R.id.txt_detail);

            if (orders.getShowCancelDisputeButton() == 1) {
                txtDetial.setText("คุณสามารถเรียกพนักงานเพื่อสอบถามหรือแก้ไข ก่อนการยกเลิก หากคุณต้องการยกเลิก สามารถกดที่ปุ่มใช่");
            } else if (orders.getShowCancelDisputeButton() == 2) {
                txtDetial.setText("คุณสามารถเรียกพนักงานเพื่อสอบถามหรือแก้ไข ก่อนการส่งคำร้อง หากคุณต้องการส่งคำร้อง สามารถกดที่ปุ่มใช่");
            }

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (orders.getShowCancelDisputeButton() == 1) {
                        Intent intent = new Intent(HistDetailActivity.this
                                , CancleOrderActivity.class);
                        intent.putExtra("Orders", Parcels.wrap(orders));
                        startActivity(intent);
                    } else if (orders.getShowCancelDisputeButton() == 2) {
                        Intent intent = new Intent(HistDetailActivity.this
                                , ProcessingOrderActivity.class);
                        intent.putExtra("Orders", Parcels.wrap(orders));
                        startActivity(intent);
                    }
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    private void getRating() {
        commonRepository.getRating(orders.getReceiptID(), new IHttpCallback<List<RatingResultData>>() {
            @Override
            public void onSuccess(List<RatingResultData> response)
            {

                if (response.get(0).getRating().size() >= 1)
                {
                    rating = response.get(0).getRating().get(0);
                    rateSta.setRating(Float.parseFloat(response.get(0).getRating().get(response.get(0).getRating().size() - 1).getScore()));
                    addComment = true;
                    rateSta.setEnabled(false);

                    if(rating.getComment().equals(""))
                    {
                        submit.setText("ADD COMMENT");
                    }
                    else
                    {
                        submit.setText("VIEW COMMENT");
                    }
                }
                else
                {
                    addComment = false;
                    submit.setText("Submit");
                }


                if (response.get(0).getDispute().size() > 0) {
                    disputeResultData = response.get(0).getDispute();
                    setViewButton();
                } else {
                    layoutRefund.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });
    }

    private void setViewButton() {
        layoutRefund.setVisibility(View.VISIBLE);
        txtSummary.setText(disputeResultData.get(0).getStatusDetail());
        nameRefund.setText(disputeResultData.get(0).getDisputeReason());
        priceRefund.setText(Util.numberFormat(Double.parseDouble(disputeResultData.get(0).getRefundAmount())));
        nameDetail.setText(disputeResultData.get(0).getDetail());
        phoneNumber.setText(Util.phoneFormat(disputeResultData.get(0).getPhoneNo()));


    }

    private void SetRecycleView() {

        orderNo.setText(orders.getReceiptNoID());
        txtNameMarket.setText(orders.getBranch().get(0).getName());
        txtStatus.setText(orders.getStatusText());
        txtDate.setText(orders.getReceiptDate());
        adapter = new OrderPaymentAdapter(summaryList);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);


        if (orders.getShowCancelDisputeButton() == 0) {
            btnClear.setVisibility(View.GONE);
        } else if (orders.getShowCancelDisputeButton() == 1) {
            btnClear.setText("ยกเลิกบิลนี้");
            btnClear.setVisibility(View.VISIBLE);
        } else if (orders.getShowCancelDisputeButton() == 2) {
            btnClear.setText("Open dispute");
            btnClear.setVisibility(View.VISIBLE);
        }
        else if (orders.getShowCancelDisputeButton() == 3)
        {
            btnClear.setText("ลบบิลนี้");
            btnClear.setVisibility(View.VISIBLE);
        }


//        setview

        txtQty.setText(summayData.getNoOfItem());
        txtTotal.setText(Util.numberFormat(Double.parseDouble(String.valueOf(summayData.getTotalAmount()))));
        specialPriceDiscountTitle.setText(summayData.getSpecialPriceDiscountTitle());
        specialPriceDiscount.setText(Util.numberFormat(Double.parseDouble(summayData.getSpecialPriceDiscount())));
        discountProgramTitle.setText(summayData.getDiscountProgramTitle());
        discountProgramValue.setText(summayData.getDiscountProgramValue());


        txtTotal2Title.setText(summayData.getAfterDiscountTitle());
        txtTotal2.setText(Util.numberFormat(summayData.getAfterDiscount()));

        txtServiceChargeLb.setText("Service charge " + summayData.getServiceChargePercent() + "%");
        txtServiceCharge.setText(Util.numberFormat(summayData.getServiceChargeValue()));
        txtVatLb.setText("VAT " + summayData.getPercentVat() + "%");
        txtVat.setText(Util.numberFormat(summayData.getVatValue()));

        vat = summayData.getVatValue();
        serviceC = summayData.getServiceChargeValue();

        txtBalance.setText(Util.numberFormat(summayData.getNetTotal()));
        luckyDrawTitle.setText(summayData.getLuckyDrawTitle());

        beforeVat.setText(Util.numberFormat(summayData.getBeforeVat()));

        String cardBrand = "";
        if(summayData.getPaymentMethod() == 2) {
            CardBrand brand = CardNumber.brand(summayData.getCreditCardNo());
            if (brand != null && brand.getLogoResourceId() > -1) {
                cardBrand = brand.getName();
            }
        }
        txtPaymentMethod.setText(summayData.getStrPaymentMethod() + " " + cardBrand);

        txtDiscount.setText(summayData.getDiscountPromoCodeValue());
        txtNameDiscount.setText(summayData.getDiscountPromoCodeTitle());

        if (summayData.getShowTotalAmount().equals("1")) {
            showTotalAmount.setVisibility(View.VISIBLE);
        } else {
            showTotalAmount.setVisibility(View.GONE);
        }

        if (summayData.getShowSpecialPriceDiscount().equals("1")) {
            showSpecialDiscount.setVisibility(View.VISIBLE);
        } else {
            showSpecialDiscount.setVisibility(View.GONE);
        }

        if (summayData.getShowDiscountProgram().equals("1")) {
            showDiscountProgram.setVisibility(View.VISIBLE);
        } else {
            showDiscountProgram.setVisibility(View.GONE);
        }

        if (summayData.getShowAfterDiscount().equals("1")) {
            showAfterDiscount.setVisibility(View.VISIBLE);
        } else {
            showAfterDiscount.setVisibility(View.GONE);
        }

        if (summayData.getShowServiceCharge().equals("1")) {
            showServiceCharge.setVisibility(View.VISIBLE);
        } else {
            showServiceCharge.setVisibility(View.GONE);
        }

        if (summayData.getShowVat().equals("1")) {
            showVat.setVisibility(View.VISIBLE);
        } else {
            showVat.setVisibility(View.GONE);
        }

        if (summayData.getShowNetTotal().equals("1")) {
            showNetTotal.setVisibility(View.VISIBLE);
        } else {
            showNetTotal.setVisibility(View.GONE);
        }

        if (summayData.getShowLuckyDrawCount().equals("1")) {
            showLuckyDrawCount.setVisibility(View.VISIBLE);
        } else {
            showLuckyDrawCount.setVisibility(View.GONE);
        }

        if (summayData.getShowBeforeVat().equals("1")) {
            showBeforeVat.setVisibility(View.VISIBLE);
        } else {
            showBeforeVat.setVisibility(View.GONE);
        }

        if (summayData.getShowVoucherListButton().equals("1")){
            layoutPromotion.setVisibility(View.GONE);
        }else{
            layoutPromotion.setVisibility(View.GONE);
        }

        if (summayData.getApplyVoucherCode().equals("0")){
            layoutPromotionCode.setVisibility(View.GONE);
        }else {
            layoutPromotionCode.setVisibility(View.GONE);
            layoutDiscount.setVisibility(View.VISIBLE);
            txtDelete.setVisibility(View.GONE);
            txtNameDiscount.setText(summayData.getDiscountPromoCodeTitle());
            txtDiscount.setText(summayData.getDiscountPromoCodeValue());
        }


    }

    @OnClick(R.id.submit)
    public void onViewClickedSubmit() {
        if (!addComment)
        {
            if(rate == 0)
            {
                Util.showAlertIOS(HistDetailActivity.this,
                        "กรุณาให้คะแนนก่อนกด submit",
                        new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                dialog.dismiss();
                            }
                        });
                return;
            }
            commonRepository.getInsertRating(
                    orders.getReceiptID(),
                    String.valueOf(rate),
                    PreferenceManager.getInstance().getUserName(),
                    Util.getModifireDate(),
                    PreferenceManager.getInstance().getToken(),
                    "insert rating",
                    new IHttpCallback<String>() {
                        @Override
                        public void onSuccess(String response) {
                            //rateSta.setRating(Float.parseFloat(response.get(0).getRating().get(0).getScore()));
                            Util.showAlertIOS(HistDetailActivity.this,
                                    "We hope you have enjoyed our service For comments, compliments or enquiries, please write to us below",
                                    new iOSDialogClickListener() {
                                        @Override
                                        public void onClick(iOSDialog dialog) {
                                            dialog.dismiss();
                                            getRating();
                                        }
                                    });
                        }

                        @Override
                        public void onError(String message) {
                            message = "Cannot connect to server";
                            Util.showToast(mainContent, message);
                        }
                    });
        }
        else
        {
            Constant.commentMe = true;
            Intent intent2 = new Intent(HistDetailActivity.this, CommentActivity.class);

            intent2.putExtra("RatingID", rating.getRatingID());
            intent2.putExtra("ReceiptID", rating.getReceiptID());
            intent2.putExtra("Score", rating.getScore());
            intent2.putExtra("Comment", rating.getComment());
            startActivity(intent2);
        }
    }


    @OnClick(R.id.btn_back)
    public void onBackClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.btn_clear)
    public void onViewClickedAction() {

        getBottomDialog();

    }

    @OnClick(R.id.btn_confirm)
    public void onConfirmClickedAction() {

        commonRepository.getUpdateReceipt(
                orders.getReceiptID(),
                "13",
                PreferenceManager.getInstance().getUserName(),
                Util.getModifireDate(),
                PreferenceManager.getInstance().getToken(),
                "update receipt status (confirm dispute)",
                new IHttpCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        //go back
                        Intent intent = new Intent(HistDetailActivity.this
                                , HistoryOrderActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Util.showToast(mainContent, message);
                    }
                });

    }

    @OnClick(R.id.btn_negotiate)
    public void onNegotiateClickedAction() {

        commonRepository.getUpdateReceipt(
                orders.getReceiptID(),
                "11",
                PreferenceManager.getInstance().getUserName(),
                Util.getModifireDate(),
                PreferenceManager.getInstance().getToken(),
                "update receipt status (negotiate dispute)",
                new IHttpCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        //go back
                        Intent intent = new Intent(HistDetailActivity.this
                                , HistoryOrderActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Util.showToast(mainContent, message);
                    }
                });

    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClickedAction() {

        onBackPressed();

    }

    @OnClick(R.id.btn_transfer)
    public void onTransferClickedAction() {

        //transfer screen
        Intent intent = new Intent(HistDetailActivity.this
                , TransferDisputeActivity.class);
        intent.putExtra("RefundAmount",disputeResultData.get(0).getRefundAmount());
        intent.putExtra("ReceiptID",disputeResultData.get(0).getReceiptID());
        startActivity(intent);

    }
}
