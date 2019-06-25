package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.JummumCo.Jummum.Adapter.BranchListRecyclerViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
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
import butterknife.OnTextChanged;

public class MarketActivity extends BaseActivity {

    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private BranchListRecyclerViewAdapter adapter;
    private List<BranchAndCustomerTableResponseResultData> branchAndCustomerTableResponseResultData;
    private LinearLayoutManager linearLayoutManager;
    private CommonRepository commonRepository;

    private int perPage = 10;
    private int page = 1;
    private boolean isLoading;
    private boolean allowNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        ButterKnife.bind(this);
        initinstan();
    }

    private void initinstan() {
        linearLayoutManager = new LinearLayoutManager(this);
        commonRepository = new CommonRepository();

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
        page = 1;

    }


    private void getBranchList(IHttpCallback<List<BranchAndCustomerTableResponseResultData>> callback) {
        if (etSearch.getText().length() == 0) {
            branchAndCustomerTableResponseResultData.clear();
            SetRecycleView();
        } else {
//            showProgressDialog("กำลังโหลด...");
            commonRepository.getBranch(etSearch.getText().toString(), String.valueOf(page), String.valueOf(perPage),
                    callback);
        }
    }

    @NonNull
    private IHttpCallback<List<BranchAndCustomerTableResponseResultData>> getHttpCallbackFirst() {
        return new IHttpCallback<List<BranchAndCustomerTableResponseResultData>>() {
            @Override
            public void onSuccess(List<BranchAndCustomerTableResponseResultData> response) {
                hideProgressDialog();
                branchAndCustomerTableResponseResultData = new ArrayList<>();
                keepItem(response);
                allowNext = response != null && response.size() == perPage;
                isLoading = false;
                SetRecycleView();
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                Util.showAlert(MarketActivity.this, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        };
    }


    private IHttpCallback<List<BranchAndCustomerTableResponseResultData>> pagingCallback() {
        return new IHttpCallback<List<BranchAndCustomerTableResponseResultData>>() {
            @Override
            public void onSuccess(List<BranchAndCustomerTableResponseResultData> response) {
                hideProgressDialog();
                if (page > 1) {
                    keepItem(response);
                } else {

                }
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
                    if (branchAndCustomerTableResponseResultData
                            .get(branchAndCustomerTableResponseResultData.size() - 1) == null) {
                        branchAndCustomerTableResponseResultData
                                .remove(branchAndCustomerTableResponseResultData.size() - 1);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };
    }

    private void SetRecycleView() {


        adapter = new BranchListRecyclerViewAdapter(branchAndCustomerTableResponseResultData);
        adapter.setOnClickListener(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                Constant.reOrder = false;
                Intent intent = new Intent(MarketActivity.this, CustomTableActivity.class);
                intent.putExtra("BranchData", Parcels.wrap(branchAndCustomerTableResponseResultData.get(item)));
                startActivity(intent);
            }
        });
//        etSearch.addTextChangedListener(textWatcher);
//        adapter.getFilter().filter(etSearch.getText());
        recyclerView.setAdapter(adapter);
    }

    private void loadMore() {
        if (allowNext) {
            branchAndCustomerTableResponseResultData.add(null);
            adapter.notifyItemChanged(branchAndCustomerTableResponseResultData.size() - 1);
            page++;
            getBranchList(pagingCallback());
        }
    }

    private void keepItem(List<BranchAndCustomerTableResponseResultData> resp) {

        if (branchAndCustomerTableResponseResultData.size() > 0) {
            if (branchAndCustomerTableResponseResultData.get(branchAndCustomerTableResponseResultData.size() - 1) == null) {
                branchAndCustomerTableResponseResultData.remove(branchAndCustomerTableResponseResultData.size() - 1);
                adapter.notifyDataSetChanged();
            }
        }

        if (resp != null) {
            for (BranchAndCustomerTableResponseResultData order : resp) {
                branchAndCustomerTableResponseResultData.add(order);
            }
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                adapter.getFilter().filter(s.toString());
            }
        }
    };

    @OnTextChanged(value = R.id.et_search
            , callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChangeEt(Editable editable) {
        getBranchList(getHttpCallbackFirst());
    }

    @OnClick(R.id.btn_home)
    public void onClickBack() {
        finish();
    }
}
