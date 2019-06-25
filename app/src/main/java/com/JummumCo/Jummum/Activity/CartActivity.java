package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Fragment.OrdersFragment;
import com.JummumCo.Jummum.Fragment.WrapperFragment;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.CreateOrderNote;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.MasterResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteItemClick;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderTakingResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity
        implements OrdersFragment.OnFragmentInteractionListener {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @BindView(R.id.tv_qty)
    TextView tvQty;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.layout_bottom_sub)
    LinearLayout layoutBottomSub;
    @BindView(R.id.cart)
    TextView cart;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.btn_option)
    RelativeLayout btnOption;
    //private List<MenuListResultData> menuListResultData;
    private double balance = 0.00;
    private int qTy;
    private CommonRepository commonRepository;
    private List<List<BranchAndCustomerTableResponseResultData>> tableResponseResultData;
    private BottomSheetDialog bottomSheetDialog;
    private OrdersFragment fragment;
    private NoteItemClick currentItem;
    private List<OrderTakingResultData> orderTakingResultData;
    private SummaryResultData summaryResultData;
    private List<CreateOrderNote> orderNotes;
    private HotDealData hotDeal;
    private String branchID;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        qTy = 0;
        balance = 0.0;

        tableResponseResultData = Parcels.unwrap(getIntent().getParcelableExtra("TableQR"));
        branchID = getIntent().getStringExtra("BranchID");
        balance = getIntent().getDoubleExtra("Balance", 0);
        qTy = getIntent().getIntExtra("Qty", 0);
        if (getIntent().getParcelableExtra("HotDeal") != null) {
            hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
        }
        commonRepository = new CommonRepository();
        gson = new Gson();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        fragment = OrdersFragment.newInstance(tableResponseResultData);

        ft.replace(R.id.fragment_layout, fragment);
        ft.commit();

        setView();

        getBottomDialog();

        Constant.noteListData = new ArrayList<>();


    }

    private void getBottomDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());

        TextView deleteAll = (TextView) bottomSheetView.findViewById(R.id.menu_bottom_delete_all);
        TextView cancleDialog = (TextView) bottomSheetView.findViewById(R.id.menu_bottom_sheet_cancle);


        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.menuListResultDataGlobal.clear();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_layout,
                        WrapperFragment.newInstance(OrdersFragment.newInstance(tableResponseResultData)));
                ft.commit();
                qTy = 0;
                balance = 0;
                setView();
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

    @Override
    public void onOpenNoteDialog(List<NoteListResponseResultData> data) {

        Intent intent = new Intent(this, NodeDialogActivity.class);
        intent.putExtra("noteData", Parcels.wrap(data));
        startActivityForResult(intent, Constant.ADD_NODE_ORDER_REQUEST_CODE);

    }

    @Override
    public void onTakeStatus(boolean status) {
        int takePrice = Integer.parseInt(tableResponseResultData.get(0).get(0).getTakeAwayFee());
        if (status) {
            balance += takePrice;
        } else {
            balance -= takePrice;
        }

        setView();
    }

    @Override
    public void onUpdatePrice(int price,
                              List<NoteListResponseResultData> data,
                              NoteItemClick noteItemClick) {

        int rootPrie = 0;
        int notePrie = 0;

        for (int root = 0; root < Constant.menuListResultDataGlobal.size(); root++) {

            rootPrie += (Integer.parseInt(Constant.menuListResultDataGlobal
                    .get(root)
                    .getSpecialPrice()) *
                    Constant.menuListResultDataGlobal
                            .get(root)
                            .getQty());

            for (List<NoteListResponseResultData> note : Constant.menuListResultDataGlobal
                    .get(root)
                    .getNoteList()) {

                for (NoteListResponseResultData note2 : note) {
                    notePrie += Integer.parseInt(note2.getPrice()) * Integer.parseInt(note2.getQuantity());
                }
            }

        }

        balance = rootPrie + notePrie;

        setView();
    }

    private void setView() {

        String netTotal = String.format("%.2f",balance);
        txtTotal.setText(Util.numberFormat(Double.parseDouble(netTotal)));
        tvQty.setText(String.valueOf(qTy));
    }

    @Override
    public void onItemClick(MenuListResultData menu, int type) {
        if (type == 1) {
            qTy--;
            balance -= Integer.parseInt(menu.getSpecialPrice());

            // menu.getNoteList().remove(menu.getNoteList().size() - 1);
        } else if (type == 2) {
            qTy++;
            balance += Integer.parseInt(menu.getSpecialPrice());

            // menu.getNoteList().add(new ArrayList<NoteListResponseResultData>());
        }
        setView();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setGlobalCart();
    }

    private void setGlobalCart() {
        List<MenuListResultData> aa =
        Constant.menuListResultDataGlobal;
        finish();
    }

    @OnClick(R.id.layout_bottom)
    public void onViewClickedSubmit() {

        if (Constant.menuListResultDataGlobal.size() > 0) {
            orderTakingResultData = new ArrayList<>();
            summaryResultData = new SummaryResultData();
            orderNotes = new ArrayList<>();

            int i = 0;
            int trackingID = 0;
            for (MenuListResultData menu : Constant.menuListResultDataGlobal) {
                menu.getTakeawayIndex().clear();
                for (int a = 0; a < menu.getQty(); a++) {
                    OrderTakingResultData order = new OrderTakingResultData();
                    int notePrie = 0;

                    trackingID -= 1;

                    order.setOrderTakingID(trackingID);
                    order.setQuantity(1);
                    order.setModifiedUser(PreferenceManager.getInstance().getUserName());

                    for (int x = 0; x < Constant.menuListResultDataGlobal.get(i)
                            .getNoteList().get(a).size(); x++) {
                        notePrie += Integer.parseInt(Constant.menuListResultDataGlobal
                                .get(i)
                                .getNoteList()
                                .get(a)
                                .get(x)
                                .getPrice()) *
                                Integer.parseInt(Constant.menuListResultDataGlobal
                                        .get(i)
                                        .getNoteList()
                                        .get(a)
                                        .get(x)
                                        .getQuantity());
                    }

                    order.setNotePrice(String.valueOf(notePrie));
                    order.setPrice(Integer.parseInt(menu.getPrice()));
                    order.setMenuID(Integer.parseInt(menu.getMenuID()));
                    order.setBranchID(Integer.parseInt(menu.getBranchID()));
                    order.setModifiedDate(Util.getModifireDate());
                    order.setSpecialPrice(Integer.parseInt(menu.getSpecialPrice()));
                    if (tableResponseResultData != null) {
                        if (tableResponseResultData.size() > 1) {
                            order.setCustomerTableID(Integer.parseInt(tableResponseResultData.get(1).get(0).getCustomerTableID()));
                        }
                    }

                    if (menu.getTakeAway().get(a).getTakeAway() == null) {
                        order.setTakeAway(0);
                        order.setTakeAwayPrice(0);
                    } else {
                        order.setTakeAway(
                                Integer.parseInt(menu.getTakeAway()
                                        .get(a)
                                        .getTakeAway()));
                        if (menu.getTakeAway().get(a).getTakeAway().equals("0")) {
                            order.setTakeAwayPrice(0);
                        } else {
                            order.setTakeAwayPrice(Integer.parseInt(tableResponseResultData
                                    .get(0).get(0).getTakeAwayFee()));
                        }

                        menu.getTakeawayIndex().add(a);

                    }
                    orderTakingResultData.add(order);


                    List<NoteListResponseResultData> notes = menu.getNoteList().get(a);

                    for (NoteListResponseResultData note : notes) {
                        CreateOrderNote orderNote = new CreateOrderNote();
                        orderNote.setOrderTakingID(order.getOrderTakingID());
                        orderNote.setNoteID(Integer.parseInt(note.getNoteID()));
                        orderNote.setModifiedDate(Util.getModifireDate());
                        orderNote.setModifiedUser(PreferenceManager.getInstance().getUserName());
                        orderNote.setQuantity(note.getQuantity());
                        orderNotes.add(orderNote);
                    }

                }
                i++;
            }

            summaryResultData.setOrderNote(orderNotes);
            summaryResultData.setOrderTaking(orderTakingResultData);
            if (hotDeal != null) {
                summaryResultData.setVoucherCode(hotDeal.getVoucherCode());
            } else {
                summaryResultData.setVoucherCode("");
            }
            summaryResultData.setBranchID(Constant.menuListResultDataGlobal.get(0).getBranchID());
            summaryResultData.setUserAccountID(PreferenceManager.getInstance().getMemberId());


            commonRepository.getSummary(summaryResultData, new IHttpCallback<List<List<SummaryResponseResultData>>>() {
                @Override
                public void onSuccess(List<List<SummaryResponseResultData>> response) {
                    Constant.Omise_Pkey = response.get(2).get(0).getOmisePublicKey();
                    List<SummaryResponseResultData> summaryData = response.get(2);

                    if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                        Constant.status_form_payment = true;
                    }

                    if (response.get(0).size() > 0) {
                        List<OrderTakingResultData> orderTakingResultData = new ArrayList<>();
                        for (SummaryResponseResultData orders : response.get(0)) {
                            OrderTakingResultData order;
                            String orderString;
                            orderString = gson.toJson(orders);
                            order = gson.fromJson(orderString,OrderTakingResultData.class);
                            orderTakingResultData.add(order);

                        }
                        summaryResultData.setOrderTaking(orderTakingResultData);
                    }

                    if (response.get(1).size() > 0) {
                        List<CreateOrderNote> createOrderNotes = new ArrayList<>();
                        for (SummaryResponseResultData notes : response.get(1)) {
                            CreateOrderNote note = new CreateOrderNote();
                            note.setQuantity(notes.getQuantity());
                            note.setNoteID(notes.getNoteID());
                            note.setOrderTakingID(notes.getOrderTakingID());
                            note.setModifiedUser(notes.getModifiedUser());
                            note.setModifiedDate(notes.getModifiedDate());
                            createOrderNotes.add(note);
                        }
                        summaryResultData.setOrderNote(createOrderNotes);
                    }

                    Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                    intent.putExtra("Balance", balance);
                    intent.putExtra("Qty", qTy);
                    intent.putExtra("TableQR", Parcels.wrap(tableResponseResultData));
                    intent.putExtra("Summary", Parcels.wrap(summaryData));
                    intent.putExtra("SummaryOrder", Parcels.wrap(summaryResultData));
                    intent.putExtra("BuffetReceiptID", getIntent().getStringExtra("BuffetReceiptID"));
                    intent.putExtra("HotDeal", Parcels.wrap(hotDeal));
                    intent.putExtra("BranchID", branchID);
                    startActivity(intent);
                }

                @Override
                public void onError(String message) {
                    Util.showToast(mainContent, message);
                }
            });
//          commonRepository.getMaster(PreferenceManager.getInstance().getToken(), PreferenceManager.getInstance().getUserName(), getHttpCallbackMaster());
        }else{
            Util.showToast(mainContent,"กรุณาเลือกรายการอาหาร");
        }
    }

    @NonNull
    private IHttpCallback<List<List<MasterResultData>>> getHttpCallbackMaster() {
        return new IHttpCallback<List<List<MasterResultData>>>() {
            @Override
            public void onSuccess(List<List<MasterResultData>> response) {
                Constant.Omise_Pkey = response.get(0).get(0).getValue();


                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }

                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                intent.putExtra("Balance", balance);
                intent.putExtra("Qty", qTy);
                intent.putExtra("TableQR", Parcels.wrap(tableResponseResultData));
                startActivity(intent);
            }

            @Override
            public void onError(String message) {
                Util.showToast(mainContent, message);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.ADD_NODE_ORDER_REQUEST_CODE:
                    List<NoteListResponseResultData> note = Parcels.unwrap(data.getParcelableExtra("notepush"));
                    fragment.refreshNote(note);
                    break;
            }
        }
    }

    @OnClick({R.id.btn_home, R.id.btn_option})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                setGlobalCart();
                break;
            case R.id.btn_option:
                bottomSheetDialog.show();
                break;
        }
    }
}
