package com.JummumCo.Jummum.Activity;

import android.app.backup.FullBackupDataOutput;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.JummumCo.Jummum.Fragment.CustomTableListFragment;
import com.JummumCo.Jummum.Fragment.MenuFragment;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomTableActivity extends BaseActivity implements MenuFragment.OnFragmentInteractionListener {


    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    private CommonRepository commonRepository;
    private List<BranchAndCustomerTableResponseResultData> menuTypeListResultData;
    private List<BranchAndCustomerTableResponseResultData> tableListAll;
    private List<BranchAndCustomerTableResponseResultData> tableList;
    private FragmentStatePagerAdapter pagerAdapter;
    private int qTy;
    private double sumPrice;
    private BranchAndCustomerTableResponseResultData tableResponseResultData;
    private List<BranchAndCustomerTableResponseResultData> market;
    private List<MenuListResultData> menuListResultData;
    private int qty = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_table);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        commonRepository = new CommonRepository();
        market = new ArrayList<>();
        Parcelable parcelable = getIntent().getParcelableExtra("BranchData");
        if (parcelable != null) {
            tableResponseResultData = Parcels.unwrap(parcelable);
            market.add(tableResponseResultData);
        }
        tableList = new ArrayList<>();



        getTableList();

    }

    private void getTableList() {
        commonRepository.getCustomTable(tableResponseResultData.getBranchID(),
                new IHttpCallback<List<BranchAndCustomerTableResponseResultData>>() {
            @Override
            public void onSuccess(List<BranchAndCustomerTableResponseResultData> response) {
                tableListAll = response;
                menuTypeListResultData = response;

                setMenuTypeList();
            }

            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });
    }

    private void setMenuTypeList() {


        pagerAdapter = getFragmentStatePagerAdapter();
        viewpager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewpager);

        for (int i = 0; i < menuTypeListResultData.size(); i++) {
            tabs.getTabAt(i).setText(menuTypeListResultData.get(i).getZone());
        }

    }

    @NonNull
    private FragmentStatePagerAdapter getFragmentStatePagerAdapter() {

        return new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return CustomTableListFragment.newInstance(tableListAll,
                        menuTypeListResultData.get(position).getZone(),market);
            }

            @Override
            public int getCount() {
                return menuTypeListResultData.size();
            }
        };

    }


    @Override
    public void onItemClick(MenuListResultData menu) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getBackActivity() {
        fileList();
    }

    @OnClick(R.id.btn_home)
    public void onClick() {
        finish();
    }
}
