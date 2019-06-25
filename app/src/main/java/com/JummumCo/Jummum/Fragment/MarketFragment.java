package com.JummumCo.Jummum.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Activity.DetailRewardActivity;
import com.JummumCo.Jummum.Activity.MyRewardActivity;
import com.JummumCo.Jummum.Adapter.RewardListRecyclerViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MarketFragment extends BaseFragment {


    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.txt_point)
    TextView txtPoint;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;
    @BindView(R.id.btn_myreward)
    LinearLayout btnMyreward;
    private CommonRepository commonRepository;
    private List<RewardListResultData> orders;
    private List<RewardListResultData> _orders;
    private LinearLayoutManager linearLayoutManager;

    private int perPage = 20;
    private int page = 1;
    private boolean isLoading;
    private boolean allowNext;
    private RewardListRecyclerViewAdapter adapter;
    private int sumPoint = 0;

    public MarketFragment() {
        super();
    }

    public static MarketFragment newInstance() {
        MarketFragment fragment = new MarketFragment();
        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_reward, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init dev.thaigpstracker.Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        commonRepository = new CommonRepository();
        linearLayoutManager = new LinearLayoutManager(getContext());
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
        getOrderList(getHttpCallbackFirst());

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getOrderList(getHttpCallbackFirst());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getOrderList(IHttpCallback<List<List<RewardListResultData>>> callback) {
        showProgressDialog("กำลังโหลด...");
        commonRepository.getReward(
                page
                , perPage
                , PreferenceManager.getInstance().getMemberId()
                , etSearch.getText().toString()
                , callback);
    }


    @NonNull
    private IHttpCallback<List<List<RewardListResultData>>> getHttpCallbackFirst() {
        return new IHttpCallback<List<List<RewardListResultData>>>() {
            @Override
            public void onSuccess(List<List<RewardListResultData>> response) {
                hideProgressDialog();
                orders = new ArrayList<>();
                keepItem(response.get(1));
                sumPoint = Integer.parseInt(response.get(0).get(0).getPoint());
                allowNext = response != null && response.size() == perPage;
                isLoading = false;
                SetRecycleView();
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                Util.showAlert(getContext(), message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        };
    }

    private IHttpCallback<List<List<RewardListResultData>>> pagingCallback() {
        return new IHttpCallback<List<List<RewardListResultData>>>() {
            @Override
            public void onSuccess(List<List<RewardListResultData>> response) {
                hideProgressDialog();
                keepItem(response.get(1));
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (dev.thaigpstracker.Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (dev.thaigpstracker.Fragment level's variables) State here
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void loadMore() {
        if (allowNext) {
            orders.add(null);
            adapter.notifyItemChanged(orders.size() - 1);
            page++;
            getOrderList(pagingCallback());
        }
    }

    private void keepItem(List<RewardListResultData> resp) {

        if (orders.size() > 0) {
            if (orders.get(orders.size() - 1) == null) {
                orders.remove(orders.size() - 1);
                adapter.notifyDataSetChanged();
            }
        }

        if (resp != null) {
            for (RewardListResultData order : resp) {
                orders.add(order);
            }
        }
    }

    private void SetRecycleView() {

        adapter = new RewardListRecyclerViewAdapter(orders,0);
        adapter.setOnClickListener(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                Intent intent = new Intent(getContext()
                        , DetailRewardActivity.class);
                intent.putExtra("Reward", Parcels.wrap(orders.get(item)));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(sumPoint);
        txtPoint.setText(yourFormattedString);
    }

    @OnClick(R.id.btn_myreward)
    public void onClickMyReward() {
        Intent intent = new Intent(getContext()
                , MyRewardActivity.class);
        startActivity(intent);
    }
}
