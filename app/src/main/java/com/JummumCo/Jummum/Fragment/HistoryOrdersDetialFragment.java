package com.JummumCo.Jummum.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.OderListmentAdapter;
import com.JummumCo.Jummum.Interface.IFragmentControlListener;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.RatingResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.JummumCo.Jummum.Activity.PaymentActivity.setListViewHeightBasedOnChildren;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HistoryOrdersDetialFragment extends BaseFragment {


    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.txt_name_market)
    TextView txtNameMarket;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.txt_sum_qty)
    TextView txtSumQty;
    @BindView(R.id.txt_sumprice)
    TextView txtSumprice;
    @BindView(R.id.txt_service_charge)
    TextView txtServiceCharge;
    @BindView(R.id.txt_price_service_charge)
    TextView txtPriceServiceCharge;
    @BindView(R.id.txt_vat)
    TextView txtVat;
    @BindView(R.id.txt_price_vat)
    TextView txtPriceVat;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.btn_clear)
    Button btnClear;
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
    @BindView(R.id.txt_name_discount)
    TextView txtNameDiscount;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.layout_discount)
    LinearLayout layoutDiscount;
    private OderListmentAdapter adapter;
    private OrderListResultData orders;
    private CommonRepository commonRepository;
    private double sumPrice, sumBalance, rate;
    private int sumQty;

    private IFragmentControlListener fragmentListener;

    public HistoryOrdersDetialFragment() {
        super();
    }

    public static BaseFragment newInstance(OrderListResultData orders) {
        HistoryOrdersDetialFragment fragment = new HistoryOrdersDetialFragment();
        Bundle args = new Bundle();
        args.putParcelable("Order", Parcels.wrap(orders));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_order_detial, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        fragmentListener = (IFragmentControlListener) getActivity();
        commonRepository = new CommonRepository();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        fragmentListener.onChangeTitle(getString(R.string.historyDetial));
        orders = Parcels.unwrap(getArguments().getParcelable("Order"));
        SetRecycleView();

        getRating();

        rateSta.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                rate = rating;
            }
        });

    }

    private void getRating() {
        commonRepository.getRating(orders.getReceiptID(), new IHttpCallback<List<RatingResultData>>() {
            @Override
            public void onSuccess(List<RatingResultData> response) {
                rateSta.setRating(Float.parseFloat(response.get(0).getRating().get(0).getScore()));
            }

            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });
    }

    private void SetRecycleView() {

        orderNo.setText(orders.getReceiptNoID());
        txtNameMarket.setText(orders.getBranch().get(0).getName());
        txtDate.setText(orders.getOpenTableDate());
        adapter = new OderListmentAdapter(orders.getOrderTaking());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);

        for (int i = 0; i < orders.getOrderTaking().size(); i++) {
            sumQty += Integer.parseInt(orders.getOrderTaking().get(i).getQuantity());
            sumPrice += Integer.parseInt(orders.getOrderTaking().get(i).getQuantity()) * Integer.parseInt(orders.getOrderTaking().get(i).getPrice());

        }
        txtSumQty.setText(String.valueOf(sumQty) + " รายการ");

        txtSumprice.setText(Util.numberFormat(sumPrice));
        txtServiceCharge.setText("Service Charge " + orders.getServiceChargePercent() + " %");
        txtPriceServiceCharge.setText(Util.numberFormat(Double.parseDouble(orders.getServiceChargeValue())));
        txtVat.setText("Vat " + orders.getVatPercent() + " %");
        txtPriceVat.setText(Util.numberFormat(Double.parseDouble(orders.getVatValue())));
        sumBalance = sumPrice + Double.parseDouble(orders.getServiceChargeValue()) + Double.parseDouble(orders.getVatValue()) - Double.parseDouble(orders.getDiscountValue());

        txtBalance.setText(Util.numberFormat(sumBalance));
        txtStatus.setText(orders.getStatusText());

        if (orders.getDiscountType() != "" || !orders.getDiscountType().equals("0")){
            layoutDiscount.setVisibility(View.VISIBLE);

            if (orders.getDiscountType().equals("1")){
                txtNameDiscount.setText(orders.getDiscountAmount()+ " บาท");
            }else if(orders.getDiscountType().equals("2")){
                txtNameDiscount.setText(orders.getDiscountAmount()+ " %");
            }

            txtDiscount.setText(orders.getDiscountValue());

        }else{
            layoutDiscount.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.submit)
    public void onViewClickedSubmit() {
//        commonRepository.getInsertRating(orders.getReceiptID(), String.valueOf(rate),
//                String.valueOf(rate), "", PreferenceManager.getInstance().getUserName(),
//                Util.getModifireDate(), PreferenceManager.getInstance().getToken(),
//                new IHttpCallback<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        //rateSta.setRating(Float.parseFloat(response.get(0).getRating().get(0).getScore()));
//                        Util.showAlert(getContext(), "We hope you have enjoyed our service For comments, compliments or enquiries, please write to us below",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        Util.showToast(mainContent, message);
//                    }
//                });
    }
}
