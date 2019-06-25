package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.OrderPaymentAdapter;
import com.JummumCo.Jummum.CustomView.ExpiryMonthSpinnerAdapter;
import com.JummumCo.Jummum.CustomView.ExpiryYearSpinnerAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.DBHelper;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.CreateOrderNote;
import com.JummumCo.Jummum.Model.CreditCardResultData;
import com.JummumCo.Jummum.Model.CreditUserData;
import com.JummumCo.Jummum.Model.CustomerTableResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Model.OrderTakingResultData;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.PayResultData;
import com.JummumCo.Jummum.Model.PromotionResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResultData;
import com.JummumCo.Jummum.Model.UserPromotionUsedResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import org.parceler.Parcels;

import java.io.IOError;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.KeyManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.omise.android.CardNumber;
import co.omise.android.Client;
import co.omise.android.TokenRequest;
import co.omise.android.TokenRequestListener;
import co.omise.android.models.APIError;
import co.omise.android.models.CardBrand;
import co.omise.android.models.Token;
import co.omise.android.ui.CreditCardEditText;


public class PaymentReOrderActivity extends BaseActivity {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.name_market)
    TextView nameMarket;
    @BindView(R.id.name_table)
    TextView nameTable;
    @BindView(R.id.txt_fname)
    EditText txtFname;
    @BindView(R.id.txt_lname)
    EditText txtLname;
    @BindView(R.id.edit_card_number)
    CreditCardEditText editCardNumber;
    @BindView(R.id.image_card_brand)
    ImageView imageCardBrand;
    @BindView(R.id.text_expiry_date)
    TextView textExpiryDate;
    @BindView(R.id.spinner_expiry_month)
    Spinner spinnerExpiryMonth;
    @BindView(R.id.spinner_expiry_year)
    Spinner spinnerExpiryYear;
    @BindView(R.id.text_security_code)
    TextView textSecurityCode;
    @BindView(R.id.edit_security_code)
    EditText editSecurityCode;
    @BindView(R.id.switch_status)
    SwitchCompat switchStatus;
    @BindView(R.id.text_error_message)
    TextView textErrorMessage;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.txt_note)
    EditText txtNote;
    @BindView(R.id.txt_qty)
    TextView txtQty;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.txt_code_promotion)
    TextView txtCodePromotion;
    @BindView(R.id.btn_pomotion)
    Button btnPomotion;
    @BindView(R.id.txt_total_2)
    TextView txtTotal2;
    @BindView(R.id.txt_service_charge)
    TextView txtServiceCharge;
    @BindView(R.id.txt_vat)
    TextView txtVat;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.layout_discount)
    LinearLayout layoutDiscount;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.layout_bottom_sub)
    LinearLayout layoutBottomSub;
    @BindView(R.id.cart)
    TextView cart;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.layout_promotion)
    LinearLayout layoutPromotion;
    @BindView(R.id.txt_name_discount)
    TextView txtNameDiscount;
    @BindView(R.id.txt_delete)
    TextView txtDelete;
    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.layout_payment)
    LinearLayout layoutPayment;
    @BindView(R.id.layout_list_card)
    RelativeLayout layoutListCard;
    @BindView(R.id.txt_credit_card)
    TextView txtCreditCard;
    @BindView(R.id.img_bran)
    ImageView imgBran;
    @BindView(R.id.txt_service_charge_lb)
    TextView txtServiceChargeLb;
    @BindView(R.id.txt_vat_lb)
    TextView txtVatLb;
    @BindView(R.id.layout_main_pay)
    LinearLayout layoutMainPay;
    @BindView(R.id.layout_include_summary)
    LinearLayout layoutIncludeSummary;
    @BindView(R.id.btn_choose_table)
    TextView btnChooseTable;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.btn_edit_order)
    RelativeLayout btnEditOrder;
    @BindView(R.id.layout_table_choose)
    LinearLayout layoutTableChoose;
    @BindView(R.id.layout_table)
    LinearLayout layoutTable;
    @BindView(R.id.txt_choose_table)
    TextView txtChooseTable;
    @BindView(R.id.lb_table_name)
    TextView lbTableName;
    @BindView(R.id.txt_tranfer_money)
    TextView txtTranferMoney;
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
    @BindView(R.id.txt_total_2_title)
    TextView txtTotal2Title;
    @BindView(R.id.show_after_discount)
    LinearLayout showAfterDiscount;
    @BindView(R.id.show_service_charge)
    LinearLayout showServiceCharge;
    @BindView(R.id.show_vat)
    LinearLayout showVat;
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
    @BindView(R.id.txt_code_promotion_code)
    EditText txtCodePromotionCode;
    @BindView(R.id.btn_pomotion_code)
    Button btnPomotionCode;
    @BindView(R.id.layout_promotion_code)
    LinearLayout layoutPromotionCode;
    @BindView(R.id.txt_label_promotion_code)
    TextView txtLabelPromotionCode;
    @BindView(R.id.layout_s_qr_code)
    RelativeLayout layoutSQrCode;
    @BindView(R.id.ns_scroll_view)
    NestedScrollView nsScrollView;
    private double balance, discount, sumBalance, serviceC, vat;
    private int qTy;
    private OrderPaymentAdapter adapter;
    private PromotionResultData promotionResultData;

    private CommonRepository commonRepository;
    private List<List<BranchAndCustomerTableResponseResultData>> tableResponseResultData;
    private PayResultData payResultData;
    private UserPromotionUsedResultData userPromotionUsedResultData;
    private List<OrderTakingResultData> orderTakingResultData;

    private int expiryMonth = 0;
    private int expiryYear = 0;
    private String tokenOmise = "";
    private String username;
    private String creditType;
    private List<CreditUserData> creditCardResultData;
    private CreditCardResultData creditCardResultData1 = null;
    private List<SummaryResponseResultData> summayData;
    private KeyManager mManager;
    private List<OrderSummary> orderList;
    private SummaryResultData summaryResultData;
    private HotDealData hotDeal;
    private String buffetReceiptID;
    private List<List<BranchAndCustomerTableResponseResultData>> branchs;

    private List<OrderSummary> summaryList;
    private OrderListResultData orders;

    private List<CreateOrderNote> orderNotes;

    private BottomSheetDialog bottomSheetDialog;
    private DBHelper mHelper;

    private List<List<PayResponseResultData>> response_send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mHelper = new DBHelper(PaymentReOrderActivity.this);
        txtChooseTable.setVisibility(View.VISIBLE);
        txtChooseTable.setVisibility(View.VISIBLE);
        lbTableName.setVisibility(View.GONE);
        nameTable.setVisibility(View.GONE);
        btnEditOrder.setVisibility(View.VISIBLE);

        if (getIntent().getParcelableExtra("HotDeal") != null) {
            hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
        }

        Util.showAlertIOS(PaymentReOrderActivity.this,
                "- กรุณาเลือกโต๊ะเพื่อสแกน QR Code เลขโต๊ะ \n - คุณสามารถแก้ไขรายการอาหารได้โดยกดที่ปุ่ม +/- ด้านบนขวามือ",
                new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                        dialog.dismiss();
                    }
                });

        buffetReceiptID = getIntent().getStringExtra("BuffetReceiptID");

        orderNotes = new ArrayList<>();
        summaryResultData = new SummaryResultData();
        commonRepository = new CommonRepository();
        orders = Parcels.unwrap(getIntent().getParcelableExtra("Orders"));
        summaryList = Parcels.unwrap(getIntent().getParcelableExtra("SummaryList"));
        qTy = getIntent().getIntExtra("Qty", 0);

        setSummaryResultData();
        adapter = new OrderPaymentAdapter(summaryList);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);

        spinnerExpiryMonth.setAdapter(new ExpiryMonthSpinnerAdapter());
        spinnerExpiryYear.setAdapter(new ExpiryYearSpinnerAdapter());
        editCardNumber.addTextChangedListener(new ActivityTextWatcher());

        promotionResultData = new PromotionResultData();

        creditCardResultData = new ArrayList<>();
        creditCardResultData = mHelper.getCreditCard(PreferenceManager.getInstance().getMemberId());

        getStatusListPayment();

        getBottomDialog();
    }

    private void setSummaryResultData() {
        List<OrderTakingResultData> orderTakingResultData = new ArrayList<>();
        List<CreateOrderNote> orderNotes_2 = new ArrayList<>();
        int a = -1;
        for (OrderTaking2ResultData data : orders.getOrderTaking()) {

            OrderTakingResultData news = new OrderTakingResultData();


            if (Integer.parseInt(data.getQuantity()) > 1) {

                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                    news = new OrderTakingResultData();

                    news.setOrderTakingID(a);
                    news.setQuantity(1);
                    news.setModifiedUser(PreferenceManager.getInstance().getUserName());
                    news.setTakeAwayPrice(data.getTakeAwayPrice() * Integer.parseInt(data.getQuantity()));
                    news.setNotePrice(String.valueOf(data.getNotePrice()));
                    news.setCustomerTableID(Integer.parseInt(data.getCustomerTableID()));
                    if (data.getPrice() != null) {
                        news.setPrice(Integer.parseInt(data.getPrice()));
                    } else {
                        news.setPrice(Integer.parseInt(data.getMenu().get(0).getPrice()));
                    }
                    news.setMenuID(Integer.parseInt(data.getMenuID()));
                    news.setBranchID(Integer.parseInt(data.getBranchID()));
                    news.setModifiedDate(Util.getModifireDate());
                    news.setSpecialPrice(Integer.parseInt(data.getMenu().get(0).getSpecialPrice()));
                    news.setTakeAway(Integer.parseInt(data.getTakeAway()));

                    if (data.getNotes() != null) {
                        for (NoteListResponseResultData note : data.getNotes()) {
                            CreateOrderNote noteList = new CreateOrderNote();
                            noteList.setNoteID(Integer.parseInt(note.getNoteID()));
                            noteList.setOrderTakingID(a);
                            noteList.setQuantity(note.getQuantity());
                            noteList.setModifiedUser(PreferenceManager.getInstance().getUserName());
                            noteList.setModifiedDate(Util.getModifireDate());

                            orderNotes.add(noteList);

                        }
                    }

                    orderTakingResultData.add(news);
                    a++;
                }


            } else {
                news = new OrderTakingResultData();
                news.setOrderTakingID(a);
                news.setQuantity(Integer.parseInt(data.getQuantity()));
                news.setModifiedUser(PreferenceManager.getInstance().getUserName());
                news.setTakeAwayPrice(data.getTakeAwayPrice() * Integer.parseInt(data.getQuantity()));
                news.setNotePrice(String.valueOf(data.getNotePrice()));
                news.setCustomerTableID(Integer.parseInt(data.getCustomerTableID()));
                if (data.getPrice() != null) {
                    news.setPrice(Integer.parseInt(data.getPrice()));
                } else {
                    news.setPrice(Integer.parseInt(data.getMenu().get(0).getPrice()));
                }
                news.setMenuID(Integer.parseInt(data.getMenuID()));
                news.setBranchID(Integer.parseInt(data.getBranchID()));
                news.setModifiedDate(Util.getModifireDate());
                news.setSpecialPrice(Integer.parseInt(data.getMenu().get(0).getSpecialPrice()));
                news.setTakeAway(Integer.parseInt(data.getTakeAway()));

                if (data.getNotes() != null) {
                    for (NoteListResponseResultData note : data.getNotes()) {
                        CreateOrderNote noteList = new CreateOrderNote();
                        noteList.setNoteID(Integer.parseInt(note.getNoteID()));
                        noteList.setOrderTakingID(a);
                        noteList.setQuantity(note.getQuantity());
                        noteList.setModifiedUser(PreferenceManager.getInstance().getUserName());
                        noteList.setModifiedDate(Util.getModifireDate());

                        orderNotes.add(noteList);
                    }
                }

                orderTakingResultData.add(news);
                a++;
            }
        }

        summaryResultData.setOrderTaking(orderTakingResultData);
        summaryResultData.setBranchID(orders.getBranchID());
        summaryResultData.setUserAccountID(PreferenceManager.getInstance().getMemberId());

        if (hotDeal != null) {
            calc(hotDeal.getVoucherCode());
        } else {
            calc2("");
        }


    }

    private void getBottomDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_choose_table, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());

        TextView scanqr = (TextView) bottomSheetView.findViewById(R.id.scan_qr);
        TextView choosetable = (TextView) bottomSheetView.findViewById(R.id.choose);
        TextView cancleDialog = (TextView) bottomSheetView.findViewById(R.id.menu_bottom_sheet_cancle);


        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentReOrderActivity.this
                        , ScanQrChooseTableActivity.class);
                startActivityForResult(intent, Constant.ADD_QR_ORDER_REQUEST_CODE);
                bottomSheetDialog.hide();
            }
        });

        choosetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BranchAndCustomerTableResponseResultData tableResponseResultData;
                tableResponseResultData = new BranchAndCustomerTableResponseResultData();
                tableResponseResultData.setBranchID(orders.getBranch().get(0).getBranchID());
                tableResponseResultData.setName(orders.getBranch().get(0).getName());
                tableResponseResultData.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                tableResponseResultData.setImageUrl(orders.getBranch().get(0).getImageUrl());

                Constant.reOrder = true;
                Intent intent = new Intent(PaymentReOrderActivity.this
                        , CustomTableActivity.class);
                intent.putExtra("BranchData", Parcels.wrap(tableResponseResultData));
                startActivityForResult(intent, Constant.ADD_QR_ORDER_REQUEST_CODE);
                bottomSheetDialog.hide();
            }
        });

        cancleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.hide();
            }
        });
    }

    private void checkOrder(List<String> data, List<Integer> prices, MenuListResultData menuList) {
        if (data.size() == 0) {
            return;
        }

        int qty = 1;


        String d = data.get(0);
        int p = prices.get(0);
        data.remove(0);
        prices.remove(0);

        //List<String> data2 = new ArrayList<>(data);

        for (int i = data.size() - 1; i >= 0; i--) {
            if (d.equals(data.get(i))) {
                qty += 1;
                data.remove(i);
                prices.remove(i);
            }
        }

        OrderSummary summary = new OrderSummary();
        summary.setProductName(menuList.getTitleThai());
        String priceStr;

        if (d.contains("Take")) {
            priceStr = String.valueOf(
                    (
                            Integer.parseInt(menuList.getSpecialPrice())
                                    + Integer.parseInt(tableResponseResultData.get(0).get(0).getTakeAwayFee())
                                    + p
                    ) * qty
            );
        } else {
            priceStr = String.valueOf((Integer.parseInt(menuList.getSpecialPrice()) + p) * qty);
        }

        summary.setPrice(priceStr);

        summary.setQty(qty);
        summary.setNoteName(d);

        orderList.add(summary);

        checkOrder(data, prices, menuList);
    }

    private void getStatusListPayment() {
        if (creditCardResultData != null) {

            if (Constant.payment_status == 1) {
                switchStatus.setChecked(true);
                imgBran.setVisibility(View.GONE);
                layoutPayment.setVisibility(View.VISIBLE);
                layoutListCard.setVisibility(View.VISIBLE);
                txtCreditCard.setText("");
                editCardNumber.setText("");
                txtFname.setText("");
                txtLname.setText("");
                editSecurityCode.setText("");
                txtTranferMoney.setVisibility(View.GONE);
                txtCreditCard.setText("เลือกวิธีการชำระเงิน");
                txtCreditCard.setTextColor(Color.parseColor("#FF3C4B"));
            } else {
                if (creditCardResultData.size() > 0) {
                    Constant.payment_status = 2;
                } else {
                    Constant.payment_status = 3;
                }
            }

            if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                Constant.payment_status = 3;
            }


            switch (Constant.payment_status) {
                case 2: {
                    int selectCard = PreferenceManager.getInstance().getSelectCardId();
                    switchStatus.setChecked(false);
                    layoutPayment.setVisibility(View.GONE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    if (creditCardResultData.get(selectCard).getCreditType() != null) {
                        imgBran.setVisibility(View.VISIBLE);
                        switch (creditCardResultData.get(selectCard).getCreditType()) {
                            case "visa":
                                imgBran.setImageResource(R.drawable.visa_card);
                                break;
                            case "mastercard":
                                imgBran.setImageResource(R.drawable.master_card);
                                break;
                            case "JCB":
                                imgBran.setImageResource(R.drawable.jcb);
                                break;
                        }
                    } else {
                        imgBran.setVisibility(View.GONE);
                    }
                    String numCredit = creditCardResultData.get(selectCard).getNumCredit();

                    if (numCredit != null) {
                        txtCreditCard.setText(" xxxx xxxx xxxx " + numCredit.substring(numCredit.length() - 4));
                        txtCreditCard.setTextColor(Color.parseColor("#000000"));
                        editCardNumber.setText(creditCardResultData.get(selectCard).getNumCredit());
                        txtFname.setText(creditCardResultData.get(selectCard).getFname());
                        txtLname.setText(creditCardResultData.get(selectCard).getLname());
                        expiryMonth = Integer.parseInt(creditCardResultData.get(selectCard).getMonth());
                        expiryYear = Integer.parseInt(creditCardResultData.get(selectCard).getYear());
                        editSecurityCode.setText(creditCardResultData.get(selectCard).getSecut());
                    }
                    txtTranferMoney.setVisibility(View.GONE);
                    cart.setText("ชำระเงิน");
//                    cart.setText("ชำระเงินแบบบัตรเครดิต");
                    break;
                }

                case 3: {
                    PreferenceManager.getInstance().setTransferMoney(1);
                    switchStatus.setChecked(false);
                    imgBran.setVisibility(View.GONE);
                    layoutPayment.setVisibility(View.GONE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    txtTranferMoney.setVisibility(View.VISIBLE);
                    txtTranferMoney.setText("โอนเงิน");
                    txtCreditCard.setText("เลือกวิธีการชำระเงิน");
                    txtCreditCard.setTextColor(Color.parseColor("#FF3C4B"));
                    cart.setText("ชำระเงิน");
//                    cart.setText("ชำระเงินแบบโอน");
                    break;
                }

                default: {
                    break;
                }
            }
            /*
            if (creditCardResultData.size() <= 0) {
                Constant.status_form_payment = false;
            } else {
                Constant.status_form_payment = true;
            }
            if (Constant.status_form_payment) {

                if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                    switchStatus.setChecked(false);
                    imgBran.setVisibility(View.GONE);
                    layoutPayment.setVisibility(View.GONE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    txtTranferMoney.setVisibility(View.VISIBLE);
                    txtTranferMoney.setText("โอนเงิน");
                    txtCreditCard.setText("เลือกวิธีการชำระเงิน");

                } else {
                    int selectCard = PreferenceManager.getInstance().getSelectCardId();
                    switchStatus.setChecked(false);
                    layoutPayment.setVisibility(View.GONE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    if (creditCardResultData.get(selectCard).getCreditType() != null) {
                        imgBran.setVisibility(View.VISIBLE);
                        switch (creditCardResultData.get(selectCard).getCreditType()) {
                            case "visa":
                                imgBran.setImageResource(R.drawable.visa_card);
                                break;
                            case "mastercard":
                                imgBran.setImageResource(R.drawable.master_card);
                                break;
                            case "JCB":
                                imgBran.setImageResource(R.drawable.jcb);
                                break;
                        }
                    } else {
                        imgBran.setVisibility(View.GONE);
                    }
                    String numCredit = creditCardResultData.get(selectCard).getNumCredit();

                    if (numCredit != null) {
                        txtCreditCard.setText("xxxx xxxx xxxx " + numCredit.substring(numCredit.length() - 4));
                        editCardNumber.setText(creditCardResultData.get(selectCard).getNumCredit());
                        txtFname.setText(creditCardResultData.get(selectCard).getFname());
                        txtLname.setText(creditCardResultData.get(selectCard).getLname());
//                spinnerExpiryMonth.setSelection(Integer.parseInt(creditCardResultData.get(0).getMonth())+1);
//                spinnerExpiryYear.setSelection(Integer.parseInt(creditCardResultData.get(0).getMonth())+1);
                        expiryMonth = Integer.parseInt(creditCardResultData.get(selectCard).getMonth());
                        expiryYear = Integer.parseInt(creditCardResultData.get(selectCard).getYear());
                        editSecurityCode.setText(creditCardResultData.get(selectCard).getSecut());
                    }
                    txtTranferMoney.setVisibility(View.GONE);
                }

            } else {

                if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                    switchStatus.setChecked(false);

                    imgBran.setVisibility(View.GONE);
                    layoutPayment.setVisibility(View.GONE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    txtTranferMoney.setVisibility(View.VISIBLE);
                    txtTranferMoney.setText("โอนเงิน");
                    txtCreditCard.setText("เลือกวิธีการชำระเงิน");
                } else {
                    switchStatus.setChecked(true);
                    imgBran.setVisibility(View.GONE);
                    layoutPayment.setVisibility(View.VISIBLE);
                    layoutListCard.setVisibility(View.VISIBLE);
                    txtCreditCard.setText("");
                    editCardNumber.setText("");
                    txtFname.setText("");
                    txtLname.setText("");
                    editSecurityCode.setText("");
                    txtTranferMoney.setVisibility(View.GONE);
                    txtCreditCard.setText("เลือกวิธีการชำระเงิน");
                }
            }
            */
        } else {
            creditCardResultData = new ArrayList<>();
            layoutPayment.setVisibility(View.VISIBLE);
            layoutListCard.setVisibility(View.GONE);
        }
    }

    private void setView() {

        balance = summayData.get(0).getTotalAmount();


        if (buffetReceiptID != null) {

            if (orders.getCustomerTable().size() != 0) {
                txtChooseTable.setVisibility(View.GONE);
                lbTableName.setVisibility(View.VISIBLE);
                nameTable.setVisibility(View.VISIBLE);
                nameTable.setText(orders.getCustomerTable().get(0).getTableName());

                List<BranchAndCustomerTableResponseResultData> branch = new ArrayList<>();
                List<BranchAndCustomerTableResponseResultData> customtable = new ArrayList<>();
                BranchAndCustomerTableResponseResultData tableResponseResultData2;
                tableResponseResultData2 = new BranchAndCustomerTableResponseResultData();
                tableResponseResultData2.setBranchID(orders.getBranch().get(0).getBranchID());
                tableResponseResultData2.setName(orders.getBranch().get(0).getName());
                tableResponseResultData2.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                tableResponseResultData2.setImageUrl(orders.getBranch().get(0).getImageUrl());
                branch.add(tableResponseResultData2);

                tableResponseResultData2.setBranchID(orders.getCustomerTable().get(0).getBranchID());
                tableResponseResultData2.setCustomerTableID(orders.getCustomerTable().get(0).getCustomerTableID());
                tableResponseResultData2.setTableName(orders.getCustomerTable().get(0).getTableName());
                tableResponseResultData2.setZone(orders.getCustomerTable().get(0).getZone());
                customtable.add(tableResponseResultData2);

                tableResponseResultData = new ArrayList<>();
                tableResponseResultData.add(branch);
                tableResponseResultData.add(customtable);
            }

        } else {
            if (tableResponseResultData != null) {
                txtChooseTable.setVisibility(View.GONE);
                lbTableName.setVisibility(View.VISIBLE);
                nameTable.setVisibility(View.VISIBLE);
                nameTable.setText(tableResponseResultData.get(1).get(0).getTableName());
                summaryResultData.setBranchID(tableResponseResultData.get(1).get(0).getBranchID());

                for (int i = 0; i < summaryResultData.getOrderTaking().size(); i++) {

                    if (summaryResultData.getOrderTaking().get(i).getTakeAwayPrice() != 0) {
                        summaryResultData.getOrderTaking().get(i).setTakeAwayPrice(Integer
                                .parseInt(tableResponseResultData.get(0)
                                        .get(0)
                                        .getTakeAwayFee()));
                    }

                    summaryResultData.getOrderTaking().get(i).setBranchID(Integer
                            .parseInt(tableResponseResultData
                                    .get(0)
                                    .get(0)
                                    .getBranchID()));
                }
            }
        }


        nameMarket.setText(orders.getBranch().get(0).getName());
        txtQty.setText(String.valueOf(qTy));

        if (summayData.get(0).getShowTotalAmount().equals("1")) {
            showTotalAmount.setVisibility(View.VISIBLE);
        } else {
            showTotalAmount.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowSpecialPriceDiscount().equals("1")) {
            showSpecialDiscount.setVisibility(View.VISIBLE);
        } else {
            showSpecialDiscount.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowDiscountProgram().equals("1")) {
            showDiscountProgram.setVisibility(View.VISIBLE);
        } else {
            showDiscountProgram.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowAfterDiscount().equals("1")) {
            showAfterDiscount.setVisibility(View.VISIBLE);
        } else {
            showAfterDiscount.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowServiceCharge().equals("1")) {
            showServiceCharge.setVisibility(View.VISIBLE);
        } else {
            showServiceCharge.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowVat().equals("1")) {
            showVat.setVisibility(View.VISIBLE);
        } else {
            showVat.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowNetTotal().equals("1")) {
            showNetTotal.setVisibility(View.VISIBLE);
        } else {
            showNetTotal.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowLuckyDrawCount().equals("1")) {
            showLuckyDrawCount.setVisibility(View.VISIBLE);
        } else {
            showLuckyDrawCount.setVisibility(View.GONE);
        }

        if (summayData.get(0).getShowBeforeVat().equals("1")) {
            showBeforeVat.setVisibility(View.VISIBLE);
        } else {
            showBeforeVat.setVisibility(View.GONE);
        }

        if (summayData.get(0).getApplyVoucherCode().equals("0")) {
            layoutPromotionCode.setVisibility(View.VISIBLE);

            if (summayData.get(0).getShowVoucherListButton().equals("1")) {
                txtLabelPromotionCode.setVisibility(View.VISIBLE);
                layoutPromotion.setVisibility(View.GONE);
            } else {
                layoutPromotion.setVisibility(View.GONE);
                txtLabelPromotionCode.setVisibility(View.GONE);
            }
        } else {

            layoutPromotion.setVisibility(View.GONE);
            layoutPromotionCode.setVisibility(View.GONE);
            layoutDiscount.setVisibility(View.VISIBLE);
            txtDelete.setVisibility(View.VISIBLE);
            txtNameDiscount.setText(summayData.get(0).getDiscountPromoCodeTitle());
            txtDiscount.setText(summayData.get(0).getDiscountPromoCodeValue());
        }

        if (summayData.get(0).getShowPayBuffetButton().equals("1")) {
            cart.setText("ชำระเงิน");
        } else if (summayData.get(0).getShowPayBuffetButton().equals("2")) {
            cart.setText("สั่งบุปเพ่ต์");
        } else if (summayData.get(0).getShowPayBuffetButton().equals("0")) {
            cart.setVisibility(View.GONE);
        }


//        Double total = Double.parseDouble(String.valueOf(summayData.get(0).getTotalAmount()));
//        Double discount = Double.parseDouble(summayData.get(0).getDiscountValue());

        txtDiscount.setText(summayData.get(0).getDiscountPromoCodeValue());
        txtNameDiscount.setText(summayData.get(0).getDiscountPromoCodeTitle());

        txtTotal.setText(Util.numberFormat(Double.parseDouble(String.valueOf(summayData.get(0).getTotalAmount()))));
        specialPriceDiscountTitle.setText(summayData.get(0).getSpecialPriceDiscountTitle());
        specialPriceDiscount.setText(Util.numberFormat(Double.parseDouble(summayData.get(0).getSpecialPriceDiscount())));
        discountProgramTitle.setText(summayData.get(0).getDiscountProgramTitle());
        discountProgramValue.setText(summayData.get(0).getDiscountProgramValue());


        txtTotal2Title.setText(summayData.get(0).getAfterDiscountTitle());
        txtTotal2.setText(Util.numberFormat(summayData.get(0).getAfterDiscount()));

        txtServiceChargeLb.setText("Service charge " + summayData.get(0).getServiceChargePercent() + "%");
        txtServiceCharge.setText(Util.numberFormat(summayData.get(0).getServiceChargeValue()));
        txtVatLb.setText("VAT " + summayData.get(0).getPercentVat() + "%");
        txtVat.setText(Util.numberFormat(summayData.get(0).getVatValue()));

        vat = summayData.get(0).getVatValue();
        serviceC = summayData.get(0).getServiceChargeValue();

        txtBalance.setText(Util.numberFormat(summayData.get(0).getNetTotal()));
        luckyDrawTitle.setText(summayData.get(0).getLuckyDrawTitle());

        beforeVat.setText(Util.numberFormat(summayData.get(0).getBeforeVat()));


        if (!summayData.get(0).getDiscountProgramValue().equals("0")) {
            layoutDiscount.setVisibility(View.VISIBLE);
            layoutPromotion.setVisibility(View.GONE);
        } else {
            layoutDiscount.setVisibility(View.GONE);
            layoutPromotion.setVisibility(View.GONE);
        }


//        txtTotal.setText(Util.numberFormat(balance));
//        txtTotal2.setText(Util.numberFormat(Double.parseDouble(summayData.get(0).getTotalAfterDiscount())));
//        txtServiceChargeLb.setText("Service charge " + summayData.get(0).getServiceChargePercent() + "%");
//        serviceC = summayData.get(0).getServiceChargeValue();
//        txtServiceCharge.setText(Util.numberFormat(summayData.get(0).getServiceChargeValue()));
//        txtVatLb.setText("VAT " + summayData.get(0).getVatPercent() + "%");
//        vat = summayData.get(0).getVatValue();
//        txtVat.setText(Util.numberFormat(summayData.get(0).getVatValue()));
//
//        txtBalance.setText(Util.numberFormat(summayData.get(0).getNetTotal()));
//
//        txtDiscount.setText(summayData.get(0).getDiscountValue());
//
////        hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
//
        if (hotDeal != null) {
            txtNameDiscount.setText(hotDeal.getVoucherCode());
            txtDelete.setVisibility(View.VISIBLE);
            layoutDiscount.setVisibility(View.VISIBLE);
            layoutPromotion.setVisibility(View.GONE);
        }
//
//
        if (isFree()) {
            layoutMainPay.setVisibility(View.GONE);
            layoutIncludeSummary.setVisibility(View.GONE);
        }



    }

    private boolean isFree() {
        return buffetReceiptID != null && balance == 0;
    }

    private void setDataCall() {


        orderTakingResultData = new ArrayList<>();
        payResultData = new PayResultData();
        userPromotionUsedResultData = new UserPromotionUsedResultData();
        orderTakingResultData.clear();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        username = PreferenceManager.getInstance().getUserName();

        payResultData.setCustomerType(4);
        payResultData.setAmount((sumBalance * 100));
        payResultData.setStatusRoute("");
        payResultData.setReceiptID(-1);
        payResultData.setRemark("");

        userPromotionUsedResultData.setUserAccountID(Integer.parseInt(PreferenceManager.getInstance().getMemberId())); //TODO
        userPromotionUsedResultData.setUserPromotionUsedID(-1);

        if (promotionResultData.getPromoCodeID() != null) {
            userPromotionUsedResultData.setPromotionID(Integer.parseInt(promotionResultData.getPromotionID()));
            payResultData.setDiscountType(Integer.parseInt(promotionResultData.getDiscountType()));
            payResultData.setDiscountAmount(Integer.parseInt(promotionResultData.getDiscountAmount()));
        } else {
            userPromotionUsedResultData.setPromotionID(0);
            payResultData.setDiscountType(0);
            payResultData.setDiscountAmount(0);
        }
        userPromotionUsedResultData.setReceiptID(0);
        userPromotionUsedResultData.setModifiedUser(PreferenceManager.getInstance().getUserName()); //TODO
        userPromotionUsedResultData.setModifiedDate(formattedDate);

        payResultData.setUserPromotionUsed(userPromotionUsedResultData);
        payResultData.setModifiedUser(PreferenceManager.getInstance().getUserName());    //TODO

//        Add data OrderTakingResultData

        OrderTakingResultData takingResultData;

        orderTakingResultData = summaryResultData.getOrderTaking();

        payResultData.setOrderNote(orderNotes);
        payResultData.setOrderTaking(orderTakingResultData);
        payResultData.setType(1);
        payResultData.setSendToKitchenDate(formattedDate);
        payResultData.setReceiptDate(formattedDate);
        if (hotDeal != null) {
            payResultData.setVoucherCode(hotDeal.getVoucherCode().toLowerCase());
        }

        if (!isFree()) {
            if (PreferenceManager.getInstance().getTransferMoney() != 1) {
                //TODO 1=amex, 2=jcb, 3=master card, 5=visa
                if (creditType.equals("visa")) {
                    payResultData.setCreditCardType(5);
                } else if (creditType.equals("master")) {
                    payResultData.setCreditCardType(3);
                } else if (creditType.equals("JCB")) {
                    payResultData.setCreditCardType(2);
                }
            }
            payResultData.setCustomerTableID(Integer.parseInt(tableResponseResultData.get(1).get(0).getCustomerTableID()));
            payResultData.setCreditCardNo(editCardNumber.getText().toString().trim());
            payResultData.setCreditCardAmount(sumBalance);
        }


        payResultData.setMergeReceiptID(0);
        if (tableResponseResultData.get(0).get(0).getServiceChargePercent() != null) {
            payResultData.setServiceChargePercent(Integer.parseInt(tableResponseResultData.get(0).get(0).getServiceChargePercent()));
        } else {
            payResultData.setServiceChargePercent(0);
        }

        payResultData.setServiceChargeValue(serviceC);
        payResultData.setModifiedDate(formattedDate);
        //payResultData.setOrderNote(null);
        payResultData.setReceiptNoID("");
        payResultData.setCashAmount(0);
        if (tableResponseResultData.get(0).get(0).getPriceIncludeVat() != null) {
            payResultData.setPriceIncludeVat(Integer.parseInt(tableResponseResultData.get(0).get(0).getPriceIncludeVat()));
        } else {
            payResultData.setPriceIncludeVat(0);
        }
        payResultData.setServingPerson(0);
        payResultData.setOpenTableDate(formattedDate);
        payResultData.setStatus(2);
        payResultData.setDeliveredDate(formattedDate); //TODO
        payResultData.setDiscountReason("");
        payResultData.setReceiptNoTaxID("");
        payResultData.setVatValue(vat);
        payResultData.setBranchID(Integer.parseInt(tableResponseResultData.get(0).get(0).getBranchID()));
        payResultData.setMemberID(Integer.parseInt(PreferenceManager.getInstance().getMemberId()));   //TODO
        payResultData.setPromoCodeID(0);
        payResultData.setCashReceive(0);
        if (summayData.get(0).getDiscountValue() != null) {
            payResultData.setDiscountValue(Double.parseDouble(summayData.get(0).getDiscountValue()));
        } else {
            payResultData.setDiscountValue(0);
        }

        if (tableResponseResultData.get(0).get(0).getPercentVat() != null) {
            payResultData.setVatPercent(Integer.parseInt(tableResponseResultData.get(0).get(0).getPercentVat()));
        } else {
            payResultData.setVatPercent(0);
        }
        payResultData.setTransferDate(formattedDate);
//        payResultData.setOmiseToken("");    // TODO tokenOmise
        payResultData.setTransferAmount(0);

        if (PreferenceManager.getInstance().getTransferMoney() == 1) {
            payResultData.setPaymentMethod(1);
        } else {
            payResultData.setPaymentMethod(2);
        }


    }

    @OnClick(R.id.btn_home)
    public void onViewClickedBack() {
        finish();
    }

    @OnClick(R.id.layout_list_card)
    public void onViewClickedListCard() {

        if (Constant.status_form_payment) {
            Constant.status_form_payment = false;
        } else {
            Constant.status_form_payment = true;
        }
        getStatusListPayment2();
    }

    private void getStatusListPayment2() {
        Intent intent = new Intent(this, CreditCardActivity.class);
        intent.putExtra("credit", 1);
        startActivityForResult(intent, Constant.ADD_CREDITCARD_REQUEST_CODE);
    }


    @OnClick(R.id.btn_edit_order)
    public void onClickEditOrder() {

        Constant.reOrder = true;

        setMenuGlobal();
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("BranchID", orders.getBranchID());
//            intent.putExtra("TableQR", Parcels.wrap(branchs));
        intent.putExtra("HotDeal", Parcels.wrap(hotDeal));

        if (tableResponseResultData != null) {
            intent.putExtra("TableQR", Parcels.wrap(tableResponseResultData));
        } else {
            BranchAndCustomerTableResponseResultData tableResponseResultData;
            tableResponseResultData = new BranchAndCustomerTableResponseResultData();
            tableResponseResultData.setBranchID(orders.getBranch().get(0).getBranchID());
            tableResponseResultData.setName(orders.getBranch().get(0).getName());
            tableResponseResultData.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
            tableResponseResultData.setImageUrl(orders.getBranch().get(0).getImageUrl());
            List<List<BranchAndCustomerTableResponseResultData>> tbs = new ArrayList<>();
            List<BranchAndCustomerTableResponseResultData> tb = new ArrayList<>();
            tb.add(tableResponseResultData);
            tbs.add(tb);
            intent.putExtra("TableQR", Parcels.wrap(tbs));
        }
        if (buffetReceiptID != null) {
            intent.putExtra("BuffetReceiptID", buffetReceiptID);

            BranchAndCustomerTableResponseResultData br = new BranchAndCustomerTableResponseResultData();
            br.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
            br.setName(orders.getBranch().get(0).getName());
            br.setBranchID(orders.getBranch().get(0).getBranchID());
            br.setImageUrl(orders.getBranch().get(0).getImageUrl());

            BranchAndCustomerTableResponseResultData tb = new BranchAndCustomerTableResponseResultData();
            tb.setTableName(orders.getCustomerTable().get(0).getTableName());
            tb.setCustomerTableID(orders.getCustomerTable().get(0).getCustomerTableID());
            tb.setBranchID(orders.getCustomerTable().get(0).getBranchID());
            tb.setZone(orders.getCustomerTable().get(0).getZone());

            List<BranchAndCustomerTableResponseResultData> br1 = new ArrayList<>();
            br1.add(br);

            List<BranchAndCustomerTableResponseResultData> tb1 = new ArrayList<>();
            tb1.add(tb);

            List<List<BranchAndCustomerTableResponseResultData>> list = new ArrayList<>();
            list.add(br1);
            list.add(tb1);

            intent.putExtra("TableQR", Parcels.wrap(list));
        }
        startActivity(intent);

    }

    private void setMenuGlobal() {
        Constant.menuListResultDataGlobal = new ArrayList<>();
        for (OrderTaking2ResultData data : orders.getOrderTaking()) {
            if (Constant.menuListResultDataGlobal.size() <= 0) {
                MenuListResultData menu = new MenuListResultData();
                if (data.getMenu() != null) {
                    menu.setMenuID(String.valueOf(data.getMenuID()));
                    menu.setQty(Integer.parseInt(data.getQuantity()));
                    menu.setQuantity(String.valueOf(data.getQuantity()));
                    menu.setMenuTypeID(String.valueOf(data.getMenu().get(0).getMenuTypeID()));
                    menu.setPrice(data.getMenu().get(0).getPrice());
                    menu.setSpecialPrice(data.getMenu().get(0).getSpecialPrice());
                    menu.setImageUrl(data.getMenu().get(0).getImageUrl());
                    menu.setMenuCode(data.getMenu().get(0).getMenuCode());
                    menu.setTitleThai(data.getMenu().get(0).getTitleThai());
                    menu.setBuffetMenu(data.getMenu().get(0).getBuffetMenu());
                    menu.setBranchID(data.getBranchID());

                    List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                    if (data.getNotes() != null) {
                        if (data.getNotes().size() <= 0) {
                            for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                notes.add(new ArrayList<NoteListResponseResultData>());
                            }
                            menu.setNoteList(notes);
                        } else {
                            for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                notes.add(data.getNotes());
                            }
                            menu.setNoteList(notes);
                        }
                    }


                    List<NoteListResponseResultData> takes = new ArrayList<>();
                    for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                        NoteListResponseResultData take = new NoteListResponseResultData();
                        take.setTakeAway(data.getTakeAway());
                        takes.add(take);
                    }

                    menu.setTakeAway(takes);

                    List<Integer> takeIndex = new ArrayList<>();
                    takeIndex.add(Integer.valueOf(data.getTakeAway()));
                    menu.setTakeawayIndex(takeIndex);

                    branchs = new ArrayList<>();

                    List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
                    BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
                    branch.setBranchID(orders.getBranchID());
                    branch.setName(orders.getBranch().get(0).getName());
                    branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                    branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
                    branchs1.add(branch);
                    branchs.add(branchs1);


                    Constant.menuListResultDataGlobal.add(menu);
                }
            } else {

                MenuListResultData menu = new MenuListResultData();
                int replateOrder = 0;

                for (MenuListResultData global : Constant.menuListResultDataGlobal) {
                    if (global.getMenuID().equals(data.getMenuID())) {
                        replateOrder = 1;
                        global.setQty(global.getQty() + Integer.parseInt(data.getQuantity()));

                        List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                        if (data.getNotes() != null) {
                            if (data.getNotes().size() <= 0) {
                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                    global.getNoteList().add(new ArrayList<NoteListResponseResultData>());
                                }
                            } else {
                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                    global.getNoteList().add(data.getNotes());
                                }
                            }
                        }

                        NoteListResponseResultData take = new NoteListResponseResultData();
                        for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                            take.setTakeAway(data.getTakeAway());
                            global.getTakeAway().add(take);
                        }

                        global.getTakeawayIndex().add(Integer.valueOf(data.getTakeAway()));

                        branchs = new ArrayList<>();

                        List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
                        BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
                        branch.setBranchID(orders.getBranchID());
                        branch.setName(orders.getBranch().get(0).getName());
                        branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                        branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
                        branchs1.add(branch);
                        branchs.add(branchs1);

//                        return;
                    }
                }

                if (replateOrder != 1) {
                    if (data.getMenu() != null) {
                        menu.setMenuID(String.valueOf(data.getMenuID()));
                        menu.setQty(Integer.parseInt(data.getQuantity()));
                        menu.setQuantity(String.valueOf(data.getQuantity()));
                        menu.setMenuTypeID(String.valueOf(data.getMenu().get(0).getMenuTypeID()));
                        menu.setPrice(data.getMenu().get(0).getPrice());
                        menu.setSpecialPrice(data.getMenu().get(0).getSpecialPrice());
                        menu.setImageUrl(data.getMenu().get(0).getImageUrl());
                        menu.setMenuCode(data.getMenu().get(0).getMenuCode());
                        menu.setTitleThai(data.getMenu().get(0).getTitleThai());
                        menu.setBuffetMenu(data.getMenu().get(0).getBuffetMenu());
                        menu.setBranchID(data.getBranchID());

                        List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                        if (data.getNotes() != null) {
                            if (data.getNotes().size() <= 0) {
                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                    notes.add(new ArrayList<NoteListResponseResultData>());
                                }
                                menu.setNoteList(notes);
                            } else {
                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                    notes.add(data.getNotes());
                                }
                                menu.setNoteList(notes);
                            }
                        }


                        List<NoteListResponseResultData> takes = new ArrayList<>();
                        for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                            NoteListResponseResultData take = new NoteListResponseResultData();
                            take.setTakeAway(data.getTakeAway());
                            takes.add(take);
                        }

                        menu.setTakeAway(takes);

                        List<Integer> takeIndex = new ArrayList<>();
                        takeIndex.add(Integer.valueOf(data.getTakeAway()));
                        menu.setTakeawayIndex(takeIndex);

                        branchs = new ArrayList<>();

                        List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
                        BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
                        branch.setBranchID(orders.getBranchID());
                        branch.setName(orders.getBranch().get(0).getName());
                        branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                        branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
                        branchs1.add(branch);
                        branchs.add(branchs1);


                        Constant.menuListResultDataGlobal.add(menu);
                    }
                }

            }
        }
    }


    @OnClick(R.id.name_table)
    public void onClick() {
    }

    @OnClick(R.id.layout_table)
    public void onClickChooseTable() {
        bottomSheetDialog.show();
    }

    @OnClick(R.id.btn_pomotion_code)
    public void onClickPromotionCode() {
        calc(txtCodePromotionCode.getText().toString().trim());
    }

    @OnClick(R.id.txt_label_promotion_code)
    public void onViewClickedChoosePomotion()
    {
        Intent intent = new Intent(this, VoucherActivity.class);
        intent.putExtra("BranchID",summaryResultData.getBranchID());
//        intent.putExtra("BranchID", tableResponseResultData.get(0).get(0).getBranchID());
        startActivityForResult(intent, Constant.REQUEST_CODE_VOUCHER);

//        if (tableResponseResultData != null) {
//            Intent intent = new Intent(this, VoucherActivity.class);
//            intent.putExtra("BranchID", tableResponseResultData.get(0).get(0).getBranchID());
//            startActivityForResult(intent, Constant.REQUEST_CODE_VOUCHER);
//        } else {
//            Util.showToast(mainContent, "กรุณาเลือกโต๊ะ");
//        }
    }

    private class ActivityTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String pan = s.toString();
            if (pan.length() > 6) {
                CardBrand brand = CardNumber.brand(pan);
                if (brand != null && brand.getLogoResourceId() > -1) {
                    imageCardBrand.setImageResource(brand.getLogoResourceId());
                    creditType = brand.getName();
                    return;
                }
            }

            imageCardBrand.setImageDrawable(null);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        listView.smoothScrollToPosition(0);
    }

    @OnClick(R.id.btn_pomotion)
    public void onViewClickedPromotion() {

        Intent intent = new Intent(this, VoucherActivity.class);
        intent.putExtra("BranchID",summaryResultData.getBranchID());
//        intent.putExtra("BranchID", tableResponseResultData.get(0).get(0).getBranchID());
        startActivityForResult(intent, Constant.REQUEST_CODE_VOUCHER);

//        if (tableResponseResultData != null) {
//            Intent intent = new Intent(this, VoucherActivity.class);
//            intent.putExtra("BranchID", tableResponseResultData.get(0).get(0).getBranchID());
//            startActivityForResult(intent, Constant.REQUEST_CODE_VOUCHER);
//        } else {
//            Util.showToast(mainContent, "กรุณาเลือกโต๊ะ");
//        }

    }

    private void checkPromotion() {
        layoutPromotion.setVisibility(View.GONE);
        layoutDiscount.setVisibility(View.VISIBLE);
        txtNameDiscount.setText(txtCodePromotion.getText().toString());
        if (promotionResultData.getDiscountType().equals("1")) {
            discount = (balance - Integer.parseInt(promotionResultData.getDiscountAmount()));
        } else if (promotionResultData.getDiscountType().equals("2")) {
            discount = (balance * Integer.parseInt(promotionResultData.getDiscountAmount())) / 100;
        }
        txtDiscount.setText(String.valueOf(discount));

        setView();
    }

    @OnClick(R.id.txt_delete)
    public void onViewClickedDeleteDiscount() {
        layoutPromotion.setVisibility(View.GONE);
        layoutDiscount.setVisibility(View.GONE);
        discount = 0;
        promotionResultData.setPromoCodeID(null);
        hotDeal = null;
        calc("");
    }

    @OnClick(R.id.cart)
    public void onViewClickedSubmit() {
        if (validate()) {
            showProgressDialog();

            if (buffetReceiptID == null) {

                if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                    createOrder(false);
                } else {
                    if (Constant.payment_status == 1) {
                        expiryMonth = (int) spinnerExpiryMonth.getSelectedItem();
                        expiryYear = (int) spinnerExpiryYear.getSelectedItem();
                    }
                    TokenRequest tokenRequest = new TokenRequest();
                    tokenRequest.number = editCardNumber.getText().toString();
                    tokenRequest.name = txtFname.getText().toString().trim() + " " + txtLname.getText().toString().trim();
                    tokenRequest.securityCode = editSecurityCode.getText().toString().trim();
                    tokenRequest.expirationMonth = expiryMonth;
                    tokenRequest.expirationYear = expiryYear;
                    disableForm();

                    ActivityTokenRequestListener listener = new ActivityTokenRequestListener();
                    try {
                        new Client(Constant.Omise_Pkey).send(tokenRequest, listener);
                    } catch (Exception ex) {
                        listener.onTokenRequestFailed(tokenRequest, ex);
                    }
                }

            } else {

                if (balance > 0) {
                    if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                        createOrder(true);
                    } else {
//                    if (Constant.status_form_payment == false) {
//                        expiryMonth = (int) spinnerExpiryMonth.getSelectedItem();
//                        expiryYear = (int) spinnerExpiryYear.getSelectedItem();
//                    }

                        if (Constant.payment_status == 1) {
                            expiryMonth = (int) spinnerExpiryMonth.getSelectedItem();
                            expiryYear = (int) spinnerExpiryYear.getSelectedItem();
                        }

                        TokenRequest tokenRequest = new TokenRequest();
                        tokenRequest.number = editCardNumber.getText().toString();
                        tokenRequest.name = txtFname.getText().toString().trim() + " " + txtLname.getText().toString().trim();
                        tokenRequest.securityCode = editSecurityCode.getText().toString().trim();
                        tokenRequest.expirationMonth = expiryMonth;
                        tokenRequest.expirationYear = expiryYear;
                        disableForm();

                        ActivityTokenRequestListener listener = new ActivityTokenRequestListener();
                        try {
                            new Client(Constant.Omise_Pkey).send(tokenRequest, listener);
                        } catch (Exception ex) {
                            listener.onTokenRequestFailed(tokenRequest, ex);
                        }
                    }

                } else {
                    createOrder(true);
                }

            }
        }
    }

    private class ActivityTokenRequestListener implements TokenRequestListener {
        @Override
        public void onTokenRequestSucceed(TokenRequest request, Token token) {
            tokenOmise = token.id;
            if (buffetReceiptID == null) {
                createOrder(false);
            } else {
                createOrder(true);
            }

        }

        @Override
        public void onTokenRequestFailed(TokenRequest request, Throwable throwable) {
            enableForm();

            //textErrorMessage.setVisibility(View.VISIBLE);

            String message = null;
            if (throwable instanceof IOError) {
                message = getString(co.omise.android.R.string.error_io, throwable.getMessage());
            } else if (throwable instanceof APIError) {
                message = getString(co.omise.android.R.string.error_api, ((APIError) throwable).message);
            } else {
                message = getString(co.omise.android.R.string.error_unknown, throwable.getMessage());
            }

            Util.showToast(mainContent, "Card Number ไม่ถูกต้อง");
            //textErrorMessage.setText(message);
            hideProgressDialog();
        }
    }

    private void disableForm() {
        setFormEnabled(false);
    }

    private void enableForm() {
        setFormEnabled(true);
    }

    private void setFormEnabled(boolean enabled) {
        editCardNumber.setEnabled(enabled);
        txtFname.setEnabled(enabled);
        txtLname.setEnabled(enabled);
        editSecurityCode.setEnabled(enabled);
        spinnerExpiryMonth.setEnabled(enabled);
        spinnerExpiryYear.setEnabled(enabled);
        cart.setEnabled(enabled);
        invalidateOptionsMenu();
    }

    private void createOrder(boolean isBuffet) {
        setDataCall();
        if (!isBuffet) {
            if (PreferenceManager.getInstance().getTransferMoney() == 1) {
                payResultData.setOmiseToken("");
            } else {
                payResultData.setOmiseToken(tokenOmise);
            }
            payResultData.setOmiseToken(tokenOmise);
            commonRepository.getCreateOrder(payResultData, httpCallback);
        } else {
            payResultData.setBuffetReceiptID(Integer.parseInt(buffetReceiptID));

            if (balance > 0) {
                payResultData.setOmiseToken(tokenOmise);
            }

            commonRepository.getCreateOrder(payResultData, httpCallback);
        }

    }

    private IHttpCallback<List<List<PayResponseResultData>>> httpCallback = new IHttpCallback<List<List<PayResponseResultData>>>() {
        @Override
        public void onSuccess(List<List<PayResponseResultData>> response) {
            hideProgressDialog();

            response_send = response;
            Constant.payment_status = 3;

            promotionResultData.setPromoCodeID(null);

            if (switchStatus.isChecked()) {
                creditCardResultData1 = new CreditCardResultData();

                CreditUserData creditUserData = new CreditUserData();
                creditUserData.setMembeerId(PreferenceManager.getInstance().getMemberId());
                creditUserData.setFname(txtFname.getText().toString().trim());
                creditUserData.setLname(txtLname.getText().toString().trim());
                creditUserData.setNumCredit(editCardNumber.getText().toString().trim());
                creditUserData.setYear(String.valueOf(expiryYear));
                creditUserData.setMonth(String.valueOf(expiryMonth));
                creditUserData.setSecut(editSecurityCode.getText().toString().trim());
                creditUserData.setCreditType(creditType);

                mHelper.addFriend(creditUserData);
                Constant.status_form_payment = true;

            }

            Constant.responsePay = response;
            Constant.orderListPay = summaryList;
            Constant.summayDataPay = summayData;
            Constant.qtyPay = qTy;
            Constant.hotDealPay = hotDeal;
            Constant.namePay = tableResponseResultData.get(0).get(0).getName();
            Constant.tableResponseResultDataPay = tableResponseResultData;

            if (PreferenceManager.getInstance().getTransferMoney() != 1) {

                Intent intent = new Intent(PaymentReOrderActivity.this, PaySuccessActivity.class);
                intent.putExtra("Orders", Parcels.wrap(response));
                intent.putExtra("OrderList", Parcels.wrap(summaryList));
                intent.putExtra("Summary", Parcels.wrap(summayData));
                intent.putExtra("Qty", qTy);
                intent.putExtra("HotDeal", Parcels.wrap(hotDeal));
                intent.putExtra("Name", tableResponseResultData.get(0).get(0).getName());
                intent.putExtra("TableQR", Parcels.wrap(tableResponseResultData));
                startActivity(intent);
            } else {
                Intent intent = new Intent(PaymentReOrderActivity.this, PaymentSuccessQRcodeActivity.class);
                intent.putExtra("ImageQR", Parcels.wrap(response_send));
                startActivity(intent);
            }

        }

        @Override
        public void onError(String message) {
            hideProgressDialog();
            message = "Cannot connect to server";
            Util.showToast(mainContent, message);
        }
    };

    private boolean validate() {

        if (isFree()) {
            return true;
        }

        boolean valid = true;

        if (PreferenceManager.getInstance().getTransferMoney() != 1) {
//            if (txtFname.getText().length() == 0) {
//                txtFname.setError(getString(R.string.fnameCard));
//                valid = false;
//            } else {
//                txtFname.setError(null);
//            }
//            if (txtLname.getText().length() == 0) {
//                txtLname.setError(getString(R.string.lnameCard));
//                valid = false;
//            } else {
//                txtLname.setError(null);
//            }
//            if (editCardNumber.getText().length() == 0) {
//                editCardNumber.setError(getString(R.string.label_card_number));
//                valid = false;
//            }
            switch (Constant.payment_status) {
                case 1: {
                    if (txtFname.getText().length() == 0) {
                        txtFname.setError(getString(R.string.fnameCard));
                        valid = false;
                    } else {
                        txtFname.setError(null);
                    }
                    if (txtLname.getText().length() == 0) {
                        txtLname.setError(getString(R.string.lnameCard));
                        valid = false;
                    } else {
                        txtLname.setError(null);
                    }
                    if (editCardNumber.getText().length() == 0) {
                        editCardNumber.setError(getString(R.string.label_card_number));
                        valid = false;

                    }
                    break;
                }

                case 2: {

                }

            }
        }

        if (tableResponseResultData == null) {
            Util.showToast(mainContent, "กรุณาเลือกโต๊ะ");
            valid = false;
        }
        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_VOUCHER:

                    hotDeal = Parcels.unwrap(data.getParcelableExtra("HotDeal"));
                    txtNameDiscount.setText(hotDeal.getVoucherCode());

                    layoutDiscount.setVisibility(View.VISIBLE);
                    layoutPromotion.setVisibility(View.GONE);

                    calc(hotDeal.getCode());

                    break;
                case Constant.ADD_QR_ORDER_REQUEST_CODE:
                    tableResponseResultData = Parcels.unwrap(data.getParcelableExtra("TableQR"));
                    if (buffetReceiptID != null) {
                        List<CustomerTableResultData> tableList = new ArrayList<>();
                        CustomerTableResultData table = new CustomerTableResultData();
                        table.setTableName(tableResponseResultData.get(1).get(0).getTableName());
                        table.setBranchID(tableResponseResultData.get(1).get(0).getBranchID());
                        table.setCustomerTableID(tableResponseResultData.get(1).get(0).getCustomerTableID());
                        table.setZone(tableResponseResultData.get(1).get(0).getZone());
                        tableList.add(table);
                        orders.setCustomerTable(tableList);
                    }
                    setView();
                    break;
                case Constant.ADD_CREDITCARD_REQUEST_CODE:
                    getStatusListPayment();
                    break;
            }
        }
    }

    private void calc2(String voucherCode) {

        summaryResultData.setVoucherCode(voucherCode);


        commonRepository.getSummary2(summaryResultData, new IHttpCallback<List<List<SummaryResponseResultData>>>() {
            @Override
            public void onSuccess(List<List<SummaryResponseResultData>> response) {

                Constant.Omise_Pkey = response.get(2).get(0).getOmisePublicKey();
                summayData = response.get(2);

                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }

                setView();
            }

            @Override
            public void onError(String message) {
                layoutPromotion.setVisibility(View.VISIBLE);
                layoutDiscount.setVisibility(View.GONE);
                discount = 0;
                promotionResultData.setPromoCodeID(null);
                hotDeal = null;
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });


    }

    private void calc(String voucherCode) {

        summaryResultData.setVoucherCode(voucherCode);


        commonRepository.getSummary(summaryResultData, new IHttpCallback<List<List<SummaryResponseResultData>>>() {
            @Override
            public void onSuccess(List<List<SummaryResponseResultData>> response) {

                Constant.Omise_Pkey = response.get(2).get(0).getOmisePublicKey();
                summayData = response.get(2);

                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }

                setView();
            }

            @Override
            public void onError(String message) {
                layoutPromotion.setVisibility(View.GONE);
                layoutDiscount.setVisibility(View.GONE);
                discount = 0;
                promotionResultData.setPromoCodeID(null);
                hotDeal = null;
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });


    }
}