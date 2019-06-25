package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.JummumCo.Jummum.Fragment.HotDealFragment;
import com.JummumCo.Jummum.Fragment.MarketFragment;
import com.JummumCo.Jummum.Fragment.MeFragment;
import com.JummumCo.Jummum.Fragment.QrFragment;
import com.JummumCo.Jummum.Fragment.WrapperFragment;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Util.Constant;
import com.android.jummum.R;
import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @BindView(R.id.tabs)
    TabLayout tabs;

    private int currentTabPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        onChangeTitle(getString(R.string.app_name));
        checkUsrLogin();

        TabLayout.Tab hotTab = tabs.newTab();
        hotTab.setCustomView(R.layout.tab_hot_deal);
        tabs.addTab(hotTab);

        TabLayout.Tab homeTab = tabs.newTab();
        homeTab.setCustomView(R.layout.tab_qr);
        tabs.addTab(homeTab,true);
        ImageView tabIcon = (ImageView) homeTab.getCustomView().findViewById(R.id.img_title);
        tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.scan_qr_black));


        TabLayout.Tab rewardTab = tabs.newTab();
        rewardTab.setCustomView(R.layout.tab_reward);
        tabs.addTab(rewardTab);

        TabLayout.Tab noticeTab = tabs.newTab();
        noticeTab.setCustomView(R.layout.tab_me);
        tabs.addTab(noticeTab);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_layout, WrapperFragment.newInstance(QrFragment.newInstance()));
        ft.commit();

        tabs.addOnTabSelectedListener(onTabSelectedListener);

        Constant.menuListResultDataGlobal = null;

        if (getIntent().getBooleanExtra("HotDeal", false)) {
            tabs.getTabAt(0).select();
        }


    }

    private void checkUsrLogin() {
        if (PreferenceManager.getInstance().getMemberId() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            Fragment fragment = null;
            switch (tab.getPosition()) {
                case 0:
                    fragment = WrapperFragment.newInstance(HotDealFragment.newInstance());
                    break;
                case 1:
                    fragment = WrapperFragment.newInstance(QrFragment.newInstance());
                    break;
                case 2:
                    fragment = WrapperFragment.newInstance(MarketFragment.newInstance());
                    break;
                case 3:
                    fragment = WrapperFragment.newInstance(MeFragment.newInstance());
                    break;
            }

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (currentTabPosition < tab.getPosition()) {
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            } else {
                ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            }

            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();

            TextView tabText = (TextView) tab.getCustomView().findViewById(R.id.tabText);
            tabText.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.black));

//            if (tab.getPosition() != 0) {
//                TextView tabIcon = (TextView) tab.getCustomView().findViewById(R.id.tabIcon);
//                tabIcon.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.black));
//            }
//            else
            ImageView tabIcon = (ImageView) tab.getCustomView().findViewById(R.id.img_title);
            if(tab.getPosition() == 0)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.hot_deal_black));
            }
            else if(tab.getPosition() == 1)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.scan_qr_black));
            }
            else if(tab.getPosition() == 2)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.gift_small_black));
            }
            else if(tab.getPosition() == 3)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.me_black));
            }



            currentTabPosition = tab.getPosition();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            TextView tabText = (TextView) tab.getCustomView().findViewById(R.id.tabText);
            tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.ef_white));

//            if (tab.getPosition() != 0) {
//                TextView tabIcon = (TextView) tab.getCustomView().findViewById(R.id.tabIcon);
//                tabIcon.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.ef_white));
//            }
//            else
            ImageView tabIcon = (ImageView) tab.getCustomView().findViewById(R.id.img_title);
            if(tab.getPosition() == 0)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.hot_deal_white));
            }
            else if(tab.getPosition() == 1)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.scan_qr));
            }
            else if(tab.getPosition() == 2)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.gift_small));
            }
            else if(tab.getPosition() == 3)
            {
                tabIcon.setImageDrawable(getResources().getDrawable(R.drawable.me));
            }

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    private void onChangeTitle(String string) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("ยืนยันการออกแอพพลิเคชัน?");
        builder.setPositiveButton("ออก", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                moveTaskToBack(true);
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }
}
