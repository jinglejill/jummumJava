package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Adapter.CreditListAdapter;
import com.JummumCo.Jummum.Manager.DBHelper;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.CreditUserData;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class CreditCardActivity extends BaseActivity {

    DBHelper mHelper;
    List<CreditUserData> friends;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    @BindView(R.id.layout_tranform_money)
    LinearLayout layoutTranformMoney;
    @BindView(R.id.icon_check)
    TextView iconCheck;
    @BindView(R.id.layout_not_credit)
    RelativeLayout layoutNotCredit;

    private CreditListAdapter adapter;
    private int credit = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        titleHeader.setText(getIntent().getStringExtra("header"));
        credit = getIntent().getIntExtra("credit", 0);
        mHelper = new DBHelper(CreditCardActivity.this);
        friends = mHelper.getCreditCard(PreferenceManager.getInstance().getMemberId());

        if (getIntent().getBooleanExtra("showCreditMe", false)) {
            layoutTranformMoney.setVisibility(View.GONE);
        } else {
            layoutTranformMoney.setVisibility(View.VISIBLE);
        }

        if (credit == 1) {
            CreditUserData userData = new CreditUserData();
            userData.setId(999);
            userData.setNumCredit("เพิ่มบัตรเครดิต/เดบิต");
            friends.add(userData);

            Constant.creditCard = false;
        } else {
            Constant.creditCard = true;
        }

        adapter = new CreditListAdapter(friends);
        adapter.setItemClickListener(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                mHelper.deleteFriend(String.valueOf(friends.get(item).getId()));
                friends.remove(item.intValue());
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setItemClickListenerList(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                if (credit == 1) {
                    if (friends.get(item).getId() == 999) {
                        Constant.status_form_payment = false;
                        Constant.status_choose_new_credit = true;
                        Constant.payment_status = 1;
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        PreferenceManager.getInstance().setTransferMoney(0);
                        finish();
                    } else {
                        Constant.status_form_payment = true;
                        Constant.status_choose_new_credit = false;
                        Constant.payment_status = 2;
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        PreferenceManager.getInstance().setTransferMoney(0);
                        PreferenceManager.getInstance().setSelectCardId(item.intValue());
                        finish();
                    }
                }
            }
        });
        listView.setAdapter(adapter);


        if (PreferenceManager.getInstance().getTransferMoney() == 1) {
            iconCheck.setVisibility(View.VISIBLE);
        } else {
            iconCheck.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }

    @OnClick(R.id.layout_tranform_money)
    public void onClickTranformPayment() {
        Constant.status_form_payment = true;
        Constant.status_choose_new_credit = false;
        Constant.payment_status = 3;
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        PreferenceManager.getInstance().setTransferMoney(1);
        finish();
    }
}
