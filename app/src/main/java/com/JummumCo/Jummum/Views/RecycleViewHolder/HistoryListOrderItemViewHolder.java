package com.JummumCo.Jummum.Views.RecycleViewHolder;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.OrderPaymentAdapter;
import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.JummumCo.Jummum.Activity.PaymentActivity.setListViewHeightBasedOnChildren;


/**
 * Created by com_s on 05-Feb-17.
 */

public class HistoryListOrderItemViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.txt_name_market)
    TextView txtNameMarket;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.txt_sumprice)
    TextView txtSumprice;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.layout_detail)
    LinearLayout layoutDetail;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_order_buffet)
    Button btnOrderBuffet;
    @BindView(R.id.btn_reorder)
    Button btnReOrder;
    @BindView(R.id.btn_payment_tranfrom)
    Button btnPaymentTranfrom;
    @BindView(R.id.qr_join)
    ImageView qrJoin;


    View viewG;

    private CountDownTimer timer;


    private OrderListResultData item;
    private OrderPaymentAdapter adapter;
    private List<OrderSummary> orderList;
    private ResponseBody body = null;

    public HistoryListOrderItemViewHolder(View view) {

        super(view);
        viewG = view;
        ButterKnife.bind(this, view);

    }

    public void setItem(OrderListResultData item) {

        this.item = item;
        init();

    }

    private void init() {


        orderNo.setText(String.valueOf(item.getReceiptNoID()));
        txtNameMarket.setText(String.valueOf(item.getBranch().get(0).getName()));
        txtDate.setText(String.valueOf(item.getReceiptDate()));

        orderList = new ArrayList<>();
        for (OrderTaking2ResultData menuList : item.getOrderTaking()) {

            List<String> noteNames = new ArrayList<>();

            if (menuList.getTakeAway().equals("1")) {
                noteNames.add("Take");
            }

            for (NoteListResponseResultData note : menuList.getNotes())
            {
                if(note.getQuantity().equals("1"))
                {
                    noteNames.add(note.getType() + "|" + note.getName());
                }
                else
                {
                    noteNames.add(note.getType() + "|" + note.getName()+"("+note.getQuantity()+")");
                }
            }

            OrderSummary summary = new OrderSummary();
            summary.setProductName(menuList.getMenu().get(0).getTitleThai());
            summary.setQty(Integer.parseInt(menuList.getQuantity()));
            summary.setNoteName(TextUtils.join(",", noteNames));
            summary.setPrice(String.valueOf(Integer.parseInt(menuList.getSpecialPrice()) + menuList.getTakeAwayPrice() + menuList.getNotePrice()));
            orderList.add(summary);
        }


        adapter = new OrderPaymentAdapter(orderList);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);


        txtSumprice.setText(Util.numberFormat(Double.parseDouble(item.getNetTotal())));
        txtStatus.setText(String.valueOf(item.getStatusText()));

        if (item.getHasBuffetMenu().equals("1")) {
            tvTime.setVisibility(View.VISIBLE);
            btnOrderBuffet.setVisibility(View.VISIBLE);
            qrJoin.setVisibility(View.VISIBLE);


            new CommonRepository().getOrderJoiningQrGet(item.getReceiptID(), new IHttpCallback<Response<ResponseBody>>() {
                @Override
                public void onSuccess(Response<ResponseBody> response) {
                    if(Util.DownloadImage(response.body(),qrJoin)){
                        body = response.body();
                    }

                }

                @Override
                public void onError(String message) {

                }
            });

            if (timer != null) {
                timer.cancel();
            }
            timer = new CountDownTimer(item.getTimeToCountDown() * 1000, 1000) {
                @Override
                public void onTick(long millis) {
                    tvTime.setText("" + String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(millis),
                            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                    ));


                    item.setTimeToCountDown(item.getTimeToCountDown() - 1);
                }

                @Override
                public void onFinish() {
                    tvTime.setVisibility(View.GONE);
                    btnOrderBuffet.setVisibility(View.GONE);
                    item.setHasBuffetMenu("0");
                }
            };
            timer.start();

        } else {
            tvTime.setVisibility(View.GONE);
            btnOrderBuffet.setVisibility(View.GONE);
            qrJoin.setVisibility(View.GONE);
        }

        if (item.getStatus().equals("1")) {
            btnPaymentTranfrom.setVisibility(View.VISIBLE);
        }else{
            btnPaymentTranfrom.setVisibility(View.GONE);
        }

    }


    public void setListViewClickListener(final IClickListener<List<OrderSummary>> listener) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(orderList);
            }
        });
    }

    public void setOrderBuffetClickListener(View.OnClickListener listener) {
        btnOrderBuffet.setOnClickListener(listener);
    }

    public void setReorderClickListener(final IClickListener<List<OrderSummary>> listener) {
        btnReOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(orderList);
            }
        });
    }

    public void setPaymentClickListener(View.OnClickListener listener) {
        btnPaymentTranfrom.setOnClickListener(listener);
    }

    public void setOrJoinClickListener(View.OnClickListener listener) {
        qrJoin.setOnClickListener(listener);
    }

}
