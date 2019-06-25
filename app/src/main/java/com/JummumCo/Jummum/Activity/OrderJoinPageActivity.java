package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.OrderListRecyclerViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class OrderJoinPageActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;
    @BindView(R.id.layout_shared)
    RelativeLayout layoutShared;
    @BindView(R.id.layout_qr_code)
    RelativeLayout layoutQrCode;

    private OrderListRecyclerViewAdapter adapter;
    private List<OrderListResultData> orders;
    private CommonRepository commonRepository;
    private LinearLayoutManager linearLayoutManager;
    private List<OrderSummary> orderListReOrder;

    private int perPage = 20;
    private int page = 1;
    private boolean isLoading;
    private boolean allowNext;

    private BranchAndCustomerTableResponseResultData table;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        ButterKnife.bind(this);
        init();
    }

    private void init() {


        commonRepository = new CommonRepository();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition() + 2;
                if (!isLoading && totalItemCount <= lastVisibleItem) {
                    isLoading = true;
                    loadMore();
                }
            }
        });

        layoutShared.setVisibility(View.GONE);
        layoutQrCode.setVisibility(View.VISIBLE);
        titleHeader.setText("รายการอาหารของเพื่อน");

        page = 1;
        //page = 8;

        getOrderList(getHttpCallbackFirst());

    }

    private void getOrderList(IHttpCallback<List<OrderListResultData>> callback) {
        showProgressDialog("กำลังโหลด...");
        commonRepository.getOrderJoinPageList(
                String.valueOf(page)
                , String.valueOf(perPage)
                , PreferenceManager.getInstance().getMemberId()
                , callback);
    }

    @NonNull
    private IHttpCallback<List<OrderListResultData>> getHttpCallbackFirst() {
        return new IHttpCallback<List<OrderListResultData>>() {
            @Override
            public void onSuccess(List<OrderListResultData> response) {
                hideProgressDialog();

                if (response.size() > 0) {
                    orders = new ArrayList<>();
                    keepItem(response);
                    allowNext = response != null && response.size() == perPage;
                    isLoading = false;
                    SetRecycleView();
                }else{
                    Util.showToast(mainContent,"ไม่พบข้อมูล");
                }
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                Util.showAlert(OrderJoinPageActivity.this, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        };
    }


    private IHttpCallback<List<OrderListResultData>> pagingCallback() {
        return new IHttpCallback<List<OrderListResultData>>() {
            @Override
            public void onSuccess(List<OrderListResultData> response) {
                hideProgressDialog();
                keepItem(response);
                allowNext = response != null && response.size() == perPage;
                adapter.notifyDataSetChanged();
                isLoading = false;
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                if (page > 1) {
                    page--;
                    allowNext = true;
                    if (orders.get(orders.size() - 1) == null) {
                        orders.remove(orders.size() - 1);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };
    }

    private void SetRecycleView() {

        adapter = new OrderListRecyclerViewAdapter(orders);

        adapter.setOpenDetailClick(new OrderListRecyclerViewAdapter.OrderListClickListener() {
            @Override
            public void onClick(OrderListResultData orderListResult,
                                List<OrderSummary> orderSummaries,
                                SummaryResponseResultData orderSummary) {

                Intent intent = new Intent(OrderJoinPageActivity.this,
                        HistDetailActivity.class);
                intent.putExtra("Orders", Parcels.wrap(orderListResult));
                intent.putExtra("SummaryList", Parcels.wrap(orderSummaries));
                intent.putExtra("OrderSummry", Parcels.wrap(orderSummary));
                startActivityForResult(intent, Constant.REQUEST_CODE_HISTORY_DETAIL);

            }
        });


        adapter.setOrderBuffetClick(new IClickListener<OrderListResultData>() {
            @Override
            public void onClick(OrderListResultData item) {
                Constant.reOrder = false;
                Constant.menuListResultDataGlobal = null;
                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }
                orderBuffet(item);
            }
        });

        adapter.setReorderClickListener(new OrderListRecyclerViewAdapter.OrderListClickListener() {
            @Override
            public void onClick(OrderListResultData orderListResult,
                                List<OrderSummary> orderSummaries,
                                SummaryResponseResultData orderSummary) {

                Constant.reOrder = true;
                Constant.menuListResultDataGlobal = null;
                int qty = 0;
                for (OrderSummary data : orderSummaries) {
                    qty += data.getQty();
                }

                if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                    Constant.status_form_payment = true;
                }

                Intent intent = new Intent(OrderJoinPageActivity.this, PaymentReOrderActivity.class);
                intent.putExtra("Orders", Parcels.wrap(orderListResult));
                intent.putExtra("SummaryList", Parcels.wrap(orderSummaries));
                intent.putExtra("OrderSummry", Parcels.wrap(orderSummary));
                intent.putExtra("Qty", qty);
                if (!orderListResult.getBuffetReceiptID().equals("0")) {
                    intent.putExtra("BuffetReceiptID", orderListResult.getBuffetReceiptID());
                }
                startActivityForResult(intent, Constant.REQUEST_CODE_HISTORY_DETAIL);

            }
        });

        adapter.setOnClickPaymentListener(new IClickListener<OrderListResultData>() {
            @Override
            public void onClick(OrderListResultData item) {
                showProgressDialog();
                commonRepository.getGBPrimeSetting(item.getReceiptID(), new IHttpCallback<List<List<PayResponseResultData>>>() {
                    @Override
                    public void onSuccess(List<List<PayResponseResultData>> response) {
                        hideProgressDialog();
                        Intent intent = new Intent(OrderJoinPageActivity.this,
                                PaymentSuccessQRcodeActivity.class);
                        intent.putExtra("historyPayment", true);
                        intent.putExtra("ImageQR", Parcels.wrap(response));

                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        hideProgressDialog();
                        message = "Cannot connect to server";
                        Util.showToast(mainContent, message);
                    }
                });
            }
        });

        adapter.setQrJoinClickListener(new IClickListener<OrderListResultData>() {
            @Override
            public void onClick(OrderListResultData item) {
                Intent intent = new Intent(OrderJoinPageActivity.this, OrderJoinQRGetActivity.class);
                intent.putExtra("receiptID", item.getReceiptID());
                intent.putExtra("NameMarket", item.getBranch().get(0).getName());
                intent.putExtra("timeBuffet", item.getTimeToCountDown());
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(adapter);
    }

    private void orderBuffet(final OrderListResultData item) {
        BranchAndCustomerTableResponseResultData br = new BranchAndCustomerTableResponseResultData();
        br.setTakeAwayFee(item.getBranch().get(0).getTakeAwayFee());
        br.setName(item.getBranch().get(0).getName());
        br.setBranchID(item.getBranch().get(0).getBranchID());
        br.setImageUrl(item.getBranch().get(0).getImageUrl());

        BranchAndCustomerTableResponseResultData tb = new BranchAndCustomerTableResponseResultData();
        tb.setTableName(item.getCustomerTable().get(0).getTableName());
        tb.setCustomerTableID(item.getCustomerTable().get(0).getCustomerTableID());
        tb.setBranchID(item.getCustomerTable().get(0).getBranchID());
        tb.setZone(item.getCustomerTable().get(0).getZone());

        List<BranchAndCustomerTableResponseResultData> br1 = new ArrayList<>();
        br1.add(br);

        List<BranchAndCustomerTableResponseResultData> tb1 = new ArrayList<>();
        tb1.add(tb);

        List<List<BranchAndCustomerTableResponseResultData>> list = new ArrayList<>();
        list.add(br1);
        list.add(tb1);

        Constant.menuListResultDataGlobal = null;

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("TableQR", Parcels.wrap(list));
        //intent.putExtra("BuffetReceiptID", item.getBuffetReceiptID());
        intent.putExtra("BuffetReceiptID", "192");
        intent.putExtra("BranchID", item.getBranchID());
        startActivity(intent);


    }

    private void loadMore() {
        if (allowNext) {
            orders.add(null);
            adapter.notifyItemChanged(orders.size() - 1);
            page++;
            getOrderList(pagingCallback());
        }
    }

    private void keepItem(List<OrderListResultData> resp) {

        if (orders.size() > 0) {
            if (orders.get(orders.size() - 1) == null) {
                orders.remove(orders.size() - 1);
                adapter.notifyDataSetChanged();
            }
        }

        if (resp != null) {
            for (OrderListResultData order : resp) {
                orders.add(order);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_ORDER_JOIN_QR:

                    init();

                    break;
            }
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClickedBack() {
        finish();
    }

    @OnClick(R.id.layout_qr_code)
    public void onClickScanQr() {
        Intent intent = new Intent(OrderJoinPageActivity.this
                , OrderJoinScanQRActivity.class);
        startActivityForResult(intent,Constant.REQUEST_CODE_ORDER_JOIN_QR);
    }
}
