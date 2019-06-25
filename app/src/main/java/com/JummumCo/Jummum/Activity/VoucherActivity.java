package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.JummumCo.Jummum.Adapter.VoucherAdaptor;
import com.JummumCo.Jummum.Adapter.VoucherMeRecyclerViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Interface.RecyclerViewListener;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.DialogUtil;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoucherActivity extends AppCompatActivity {

    DialogUtil dialogUtil;


    String branchID;
    String memberID;
    @BindView(R.id.btn_close)
    RelativeLayout btnClose;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    VoucherMeRecyclerViewAdapter adaptor;
//    VoucherAdapter adaptor;

    CommonRepository commonRepository = new CommonRepository();
    List<HotDealData> hotDealData;

    int page = 1;
    int perPage = 30000000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        ButterKnife.bind(this);

        branchID = getIntent().getStringExtra("BranchID");
        memberID = PreferenceManager.getInstance().getMemberId();


        dialogUtil = new DialogUtil(this);

        init();
    }

    private void init() {

        hotDealData = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        adaptor = new VoucherMeRecyclerViewAdapter(hotDealData, 1);
//        adaptor = new VoucherAdapter(hotDealData, 1);
        recyclerView.setAdapter(adaptor);
        adaptor.setOnClickListener(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                Intent intent = getIntent();
                intent.putExtra("HotDeal", Parcels.wrap(hotDealData.get(item)));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        loadData();
    }

    private void loadData() {
        hotDealData.add(null);
        adaptor.notifyItemInserted(hotDealData.size() - 1);

        commonRepository.getVoucher2(branchID, memberID, new IHttpCallback<List<HotDealData>>() {
            @Override
            public void onSuccess(List<HotDealData> response) {
                if (response.size() > 0) {
                    hotDealData.remove(hotDealData.size() - 1);
                    adaptor.notifyItemRemoved(hotDealData.size());

                    for (HotDealData h : response) {
                        hotDealData.add(h);
                        adaptor.notifyItemInserted(hotDealData.size());
                    }

                }else{
                    Util.showAlert(VoucherActivity.this, "ไม่มี Voucher", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
                }
            }

            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);

            }
        });

    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        onBackPressed();
    }
}
