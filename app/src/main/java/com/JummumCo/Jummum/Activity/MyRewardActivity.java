package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.JummumCo.Jummum.Adapter.RewardListRecyclerViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.RewardListResultData;
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
import co.ceryle.segmentedbutton.SegmentedButtonGroup;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class MyRewardActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.btn_group)
    SegmentedButtonGroup btnGroup;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private CommonRepository commonRepository;
    private LinearLayoutManager linearLayoutManager;
    private List<RewardListResultData> rewardListResultData;
    private RewardListRecyclerViewAdapter adapter;
    private int perPage = 20;
    private int page = 1;
    private boolean isLoading;
    private boolean allowNext;
    private int position_golble = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reward);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        commonRepository = new CommonRepository();
        btnGroup.setPosition(0);
        btnGroup.setOnClickedButtonListener(new SegmentedButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(int position) {
                page = 1;
                position_golble = position;
                Constant.myReward = position;
                setData();
            }
        });


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

        page = 1;
        setData();
    }

    private void setData() {
        switch (position_golble) {
            case 0:
                if (page > 1) {
                    myRewardCurrent(getHttpCallbackPageing());
                } else {
                    myRewardCurrent(getHttpCallbackFirst());
                }
                break;
            case 1:
                if (page > 1) {
                    myRewardUsed(getHttpCallbackPageing());
                } else {
                    myRewardUsed(getHttpCallbackFirst());
                }
                break;
            case 2:
                if (page > 1) {
                    myRewardExpired(getHttpCallbackPageing());
                } else {
                    myRewardExpired(getHttpCallbackFirst());
                }
                break;
        }
    }

    private void myRewardCurrent(IHttpCallback<List<List<RewardListResultData>>> callback) {
        showProgressDialog();
        commonRepository.getMyRewardCurrent(PreferenceManager.getInstance().getMemberId(), page, perPage, callback);

    }

    private void myRewardUsed(IHttpCallback<List<List<RewardListResultData>>> callback) {
        showProgressDialog();
        commonRepository.getMyRewardUsed(PreferenceManager.getInstance().getMemberId(), page, perPage, callback);
    }

    private void myRewardExpired(IHttpCallback<List<List<RewardListResultData>>> callback) {
        showProgressDialog();
        commonRepository.getMyRewardExpired(PreferenceManager.getInstance().getMemberId(), page, perPage, callback);
    }

    @NonNull
    private IHttpCallback<List<List<RewardListResultData>>> getHttpCallbackPageing() {
        return new IHttpCallback<List<List<RewardListResultData>>>() {
            @Override
            public void onSuccess(List<List<RewardListResultData>> response) {
                hideProgressDialog();
                if (response.get(0).size() > 0) {
                    keepItem(response.get(0));
                    allowNext = response != null && response.size() == perPage;
                    adapter.notifyDataSetChanged();
                    isLoading = false;
                } else {
                    if (rewardListResultData != null) {
                        rewardListResultData.clear();
                        adapter.notifyDataSetChanged();
                    }
                    Util.showAlert(MyRewardActivity.this, "คุณไม่มีรางวัล", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                if (page > 1) {
                    page--;
                    allowNext = true;
                    if (rewardListResultData.get(rewardListResultData.size() - 1) == null) {
                        rewardListResultData.remove(rewardListResultData.size() - 1);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };
    }

    @NonNull
    private IHttpCallback<List<List<RewardListResultData>>> getHttpCallbackFirst() {
        return new IHttpCallback<List<List<RewardListResultData>>>() {
            @Override
            public void onSuccess(List<List<RewardListResultData>> response) {
                hideProgressDialog();
                if (response.get(0).size() > 0) {
                    rewardListResultData = new ArrayList<>();
                    keepItem(response.get(0));
                    allowNext = response != null && response.size() == perPage;
                    isLoading = false;
                    SetRecycleView();
                } else {
                    if (rewardListResultData != null) {
                        rewardListResultData.clear();
                        adapter.notifyDataSetChanged();
                    }

                    Util.showAlert(MyRewardActivity.this, "คุณไม่มีรางวัล", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                isLoading = false;
                Util.showAlert(MyRewardActivity.this, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        };
    }


    private void SetRecycleView() {

        adapter = new RewardListRecyclerViewAdapter(rewardListResultData,1);
        adapter.setOnClickListener(new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                Intent intent = new Intent(MyRewardActivity.this
                        , CodeMyRewardActivity.class);
                intent.putExtra("RewardCode", Parcels.wrap(rewardListResultData.get(item)));
                //intent.putExtra("Reward", Parcels.wrap(reward));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadMore() {
        if (allowNext) {
            rewardListResultData.add(null);
            adapter.notifyItemChanged(rewardListResultData.size() - 1);
            page++;
            setData();
        }
    }

    private void keepItem(List<RewardListResultData> resp) {

        if (rewardListResultData.size() > 0) {
            for (int i=0;i<rewardListResultData.size();i++){
                rewardListResultData.get(i).setBranchImageUrl(rewardListResultData.get(i).getImageUrl());
            }
            if (rewardListResultData.get(rewardListResultData.size() - 1) == null) {
                rewardListResultData.remove(rewardListResultData.size() - 1);
                adapter.notifyDataSetChanged();
            }
        }

        if (resp != null) {
            for (int i=0;i<resp.size();i++){
                resp.get(i).setBranchImageUrl(resp.get(i).getImageUrl());
            }
            for (RewardListResultData order : resp) {
                rewardListResultData.add(order);
            }
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_group})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
