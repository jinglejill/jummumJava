package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Fragment.MenuFragment;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity implements MenuFragment.OnFragmentInteractionListener {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.layout_total)
    RelativeLayout layoutTotal;
    @BindView(R.id.cart)
    LinearLayout cart;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.btn_cart)
    RelativeLayout btnCart;
    @BindView(R.id.layout_lucky)
    LinearLayout layoutLucky;
    private String branchID;
    private CommonRepository commonRepository;
    private List<MenuListResultData> menuTypeListResultData;
    private List<MenuListResultData> menuListAll;
    private FragmentStatePagerAdapter pagerAdapter;
    private int qTy;
    private double sumPrice;
    private List<List<BranchAndCustomerTableResponseResultData>> tableResponseResultData;
    private int qty = 0;
    private HotDealData hotDeal;
    private String buffetReceiptID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        commonRepository = new CommonRepository();
        Parcelable parcelable = getIntent().getParcelableExtra("TableQR");
        if (parcelable != null) {
            tableResponseResultData = Parcels.unwrap(parcelable);
        }
        branchID = getIntent().getStringExtra("BranchID");
        buffetReceiptID = getIntent().getStringExtra("BuffetReceiptID");
        if (getIntent().getParcelableExtra("HotDeal") != null) {
            hotDeal = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
        }
        //        buffetReceiptID = "192";

        getMenuList();

    }

    private void getMenuList() {
        if (buffetReceiptID == null) {
            commonRepository.getMenuList(branchID, PreferenceManager.getInstance().getToken(), PreferenceManager.getInstance().getUserName(), httpCallBack);
        } else {
            commonRepository.menuBelongToBuffetGetList(branchID, buffetReceiptID, httpCallBackMenu);
        }

    }

    private IHttpCallback<List<List<MenuListResultData>>> httpCallBack = new IHttpCallback<List<List<MenuListResultData>>>() {
        @Override
        public void onSuccess(List<List<MenuListResultData>> response) {
            menuListAll = response.get(0);
            menuTypeListResultData = response.get(1);

            if (response.get(2).get(0).getLuckyDrawSpend() != null) {
                tv3.setText(response.get(2).get(0).getLuckyDrawSpend() + " บาท");
            } else {
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                layoutLucky.setVisibility(View.GONE);

            }


            int price = 0, qty = 0;
            if (Constant.menuListResultDataGlobal != null) {
                if (Constant.menuListResultDataGlobal.size() > 0) {
                    for (int i = 0; i < menuListAll.size(); i++) {
                        menuListAll.get(i).setQty(0);
                        for (int x = 0; x < Constant.menuListResultDataGlobal.size(); x++) {
                            if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                if (!Constant.reOrder) {
                                    price += Integer.parseInt(Constant.menuListResultDataGlobal.get(x).getSpecialPrice()) * Constant.menuListResultDataGlobal.get(x).getQty();
                                } else {

                                    int noteTake = 0;
                                    for (NoteListResponseResultData takeaway : Constant.menuListResultDataGlobal.get(x).getTakeAway()) {
                                        if (takeaway.getTakeAway() != null) {
                                            if (takeaway.getTakeAway().equals("1")) {
                                                noteTake += Integer.parseInt(tableResponseResultData.get(0).get(0).getTakeAwayFee());
                                            }
                                        }
                                    }

                                    for (List<NoteListResponseResultData> notelist : Constant.menuListResultDataGlobal.get(x).getNoteList()) {
                                        for (NoteListResponseResultData note : notelist) {
                                            noteTake += Integer.parseInt(note.getPrice());
                                        }
                                    }

                                    price += (Integer.parseInt(Constant.menuListResultDataGlobal.get(x).getSpecialPrice()) * Constant.menuListResultDataGlobal.get(x).getQty()) + noteTake;
                                }
                                qty += Constant.menuListResultDataGlobal.get(x).getQty();
                            } else if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && !Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                menuListAll.get(i).setNoteList(Constant.menuListResultDataGlobal.get(x).getNoteList());
                                menuListAll.get(i).setTakeAway(Constant.menuListResultDataGlobal.get(x).getTakeAway());
                                menuListAll.get(i).setTakeawayIndex(Constant.menuListResultDataGlobal.get(x).getTakeawayIndex());
                            }
                        }
                    }
                    qTy = qty;
                    sumPrice = price;

                } else {
                    qTy = 0;
                    sumPrice = 0;
                }

            } else {
                qTy = 0;
                sumPrice = 0;
            }

            setMenuTypeList();
            setView();
        }

        @Override
        public void onError(String message) {
            message = "Cannot connect to server";
            Util.showToast(mainContent, message);
        }
    };


    private IHttpCallback<List<List<MenuListResultData>>> httpCallBackMenu = new IHttpCallback<List<List<MenuListResultData>>>() {
        @Override
        public void onSuccess(List<List<MenuListResultData>> response) {
            menuListAll = response.get(0);
            menuTypeListResultData = response.get(1);


            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            layoutLucky.setVisibility(View.GONE);




            int price = 0, qty = 0;
            if (Constant.menuListResultDataGlobal != null) {
                if (Constant.menuListResultDataGlobal.size() > 0) {
                    for (int i = 0; i < menuListAll.size(); i++) {
                        menuListAll.get(i).setQty(0);
                        for (int x = 0; x < Constant.menuListResultDataGlobal.size(); x++) {
                            if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                if (!Constant.reOrder) {
                                    price += Integer.parseInt(Constant.menuListResultDataGlobal.get(x).getSpecialPrice()) * Constant.menuListResultDataGlobal.get(x).getQty();
                                } else {

                                    int noteTake = 0;
                                    for (NoteListResponseResultData takeaway : Constant.menuListResultDataGlobal.get(x).getTakeAway()) {
                                        if (takeaway.getTakeAway() != null) {
                                            if (takeaway.getTakeAway().equals("1")) {
                                                noteTake += Integer.parseInt(tableResponseResultData.get(0).get(0).getTakeAwayFee());
                                            }
                                        }
                                    }

                                    for (List<NoteListResponseResultData> notelist : Constant.menuListResultDataGlobal.get(x).getNoteList()) {
                                        for (NoteListResponseResultData note : notelist) {
                                            noteTake += Integer.parseInt(note.getPrice());
                                        }
                                    }

                                    price += (Integer.parseInt(Constant.menuListResultDataGlobal.get(x).getSpecialPrice()) * Constant.menuListResultDataGlobal.get(x).getQty()) + noteTake;
                                }
                                qty += Constant.menuListResultDataGlobal.get(x).getQty();
                            } else if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && !Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                menuListAll.get(i).setNoteList(Constant.menuListResultDataGlobal.get(x).getNoteList());
                                menuListAll.get(i).setTakeAway(Constant.menuListResultDataGlobal.get(x).getTakeAway());
                                menuListAll.get(i).setTakeawayIndex(Constant.menuListResultDataGlobal.get(x).getTakeawayIndex());
                            }
                        }
                    }
                    qTy = qty;
                    sumPrice = price;

                } else {
                    qTy = 0;
                    sumPrice = 0;
                }

            } else {
                qTy = 0;
                sumPrice = 0;
            }

            setMenuTypeList();
            setView();
        }

        @Override
        public void onError(String message) {
            message = "Cannot connect to server";
            Util.showToast(mainContent, message);
        }
    };


    private void setMenuTypeList() {


        pagerAdapter = getFragmentStatePagerAdapter();
        viewpager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewpager);


        setNameTab();

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerAdapter.notifyDataSetChanged();

                setNameTab();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    private void setNameTab() {
        for (int i = 0; i < menuTypeListResultData.size(); i++) {
            tabs.getTabAt(i).setText(menuTypeListResultData.get(i).getName());
        }
    }

    @NonNull
    private FragmentStatePagerAdapter getFragmentStatePagerAdapter() {

        return new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return MenuFragment.newInstance(menuListAll, menuTypeListResultData.get(position).getMenuTypeID());
            }

            @Override
            public int getCount() {
                return menuTypeListResultData.size();
            }



            @Override
            public int getItemPosition(@NonNull Object object) {
                MenuFragment f = (MenuFragment) object;
                if (f != null) {
                    f.update();
                }
                return super.getItemPosition(object);
            }
        };

    }


    @Override
    public void onItemClick(MenuListResultData menu) {

        qTy++;
        sumPrice += Double.parseDouble(menu.getSpecialPrice());


        if (menu.getNoteList() == null) {
            menu.setNoteList(new ArrayList<List<NoteListResponseResultData>>());
        }

        menu.getNoteList().add(new ArrayList<NoteListResponseResultData>());

        if (menu.getTakeAway() == null) {
            menu.setTakeAway(new ArrayList<NoteListResponseResultData>());
        }

        menu.getTakeAway().add(new NoteListResponseResultData());

        setView();

    }

    private void setView() {

        tvTotal.setText(String.valueOf(qTy));
        txtTotal.setText(Util.numberFormat(sumPrice));

    }


    @OnClick(R.id.cart)
    public void onViewClickedCart() {
        getStartActivity();
    }

    private void getStartActivity() {

        if (!Constant.reOrder) {
            getOverrideData();
        } else {
            getOverrideDataReOrder();
        }

        if (Constant.menuListResultDataGlobal == null) {
            return;
        } else {
            if (Constant.menuListResultDataGlobal.size() == 0) {
                return;
            }
        }

        Intent intent = new Intent(MenuActivity.this, CartActivity.class);
        intent.putExtra("Balance", sumPrice);
        intent.putExtra("Qty", qty);
        intent.putExtra("BranchID", branchID);
        intent.putExtra("BuffetReceiptID", buffetReceiptID);
        intent.putExtra("TableQR", Parcels.wrap(tableResponseResultData));
        intent.putExtra("HotDeal", Parcels.wrap(hotDeal));
        startActivity(intent);
    }

    private void getOverrideData() {
        if (Constant.menuListResultDataGlobal != null) {

            // version ใหม่
            List<MenuListResultData> menuListResultDataGlobal = Constant.menuListResultDataGlobal;


            if (menuListResultDataGlobal.size() > 0) {
                this.qty = 0;

                for (int i = 0; i < menuListAll.size(); i++) {
                    if (menuListAll.get(i).getQty() > 0) {
                        int reorder = 0;
                        int qtyCheck = menuListAll.get(i).getQty();
                        for (MenuListResultData data : menuListResultDataGlobal) {
                            if (data.getMenuID().equals(menuListAll.get(i).getMenuID())) {

                                if (data.getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                    qtyCheck = menuListAll.get(i).getQty();
                                } else {
                                    qtyCheck = 0;
                                }
                                data.setQty(menuListAll.get(i).getQty());
                                data.setQuantity(String.valueOf(menuListAll.get(i).getQty()));
                                data.setTitleThai(menuListAll.get(i).getTitleThai());
                                data.setMenuImageFolder(menuListAll.get(i).getMenuImageFolder());
                                data.setImageUrl(menuListAll.get(i).getImageUrl());
                                data.setPrice(menuListAll.get(i).getPrice());
                                data.setSpecialPrice(menuListAll.get(i).getSpecialPrice());

                                List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                                List<NoteListResponseResultData> takes = new ArrayList<>();


                                if (data.getQty() > data.getNoteList().size()) {
                                    int qt1 = data.getQty() - data.getNoteList().size();
                                    for (int x = 0; x < qt1; x++) {
                                        notes.add(new ArrayList<NoteListResponseResultData>());
                                        takes.add(new NoteListResponseResultData());
                                    }
                                    data.setNoteList(notes);
                                    data.setTakeAway(takes);
                                } else {
                                    int qt1 = data.getNoteList().size() - data.getQty();
                                    for (int x = 0; x < qt1; x++) {
                                        data.getNoteList().remove(x);
//                                        data.getNoteList().remove(x);
                                    }

                                }



                            } else {
                                reorder++;
                            }
                        }

                        if (menuListResultDataGlobal.size() == reorder) {
                            Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                        }
                        qty += qtyCheck;
                    }
                }
            } else {
                Constant.menuListResultDataGlobal = null;
            }


        } else {
            Constant.menuListResultDataGlobal = new ArrayList<>();
            this.qty = 0;
            for (int i = 0; i < menuListAll.size(); i++) {
                if (menuListAll.get(i).getQty() > 0) {
                    int reorder = 0;
                    int qtyCheck = menuListAll.get(i).getQty();
                    if (Constant.menuListResultDataGlobal.size() > 0) {
                        for (MenuListResultData data : Constant.menuListResultDataGlobal) {
                            if (data.getMenuID().equals(menuListAll.get(i).getMenuID())) {
                                qtyCheck = 0;
                                data.setQty(menuListAll.get(i).getQty());
                                data.setQuantity(String.valueOf(menuListAll.get(i).getQty()));

                                List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                                List<NoteListResponseResultData> takes = new ArrayList<>();

                                for (int x = 0; x < data.getQty(); x++) {
                                    notes.add(new ArrayList<NoteListResponseResultData>());
                                    takes.add(new NoteListResponseResultData());
                                }

                                data.setNoteList(notes);
                                data.setTakeAway(takes);

                            } else {
                                reorder++;
                            }
                        }

                        if (Constant.menuListResultDataGlobal.size() == reorder) {
                            Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                        }
                    } else {
                        menuListAll.get(i).setQuantity(String.valueOf(menuListAll.get(i).getQty()));
                        Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                    }
                    qty += qtyCheck;
                }
            }
        }


    }

    private void getOverrideDataReOrder() {
        if (Constant.menuListResultDataGlobal != null) {
            List<MenuListResultData> menuListResultDataGlobal = Constant.menuListResultDataGlobal;

            if (menuListResultDataGlobal.size() > 0) {
                this.qty = 0;

                for (int i = 0; i < menuListAll.size(); i++) {
                    if (menuListAll.get(i).getQty() > 0) {
                        int reorder = 0;
                        int qtyCheck = menuListAll.get(i).getQty();
                        for (MenuListResultData data : menuListResultDataGlobal) {
                            if (data.getMenuID().equals(menuListAll.get(i).getMenuID())) {
                                if (data.getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                    qtyCheck = menuListAll.get(i).getQty();
                                } else {
                                    qtyCheck = 0;
                                }
                                data.setQty(menuListAll.get(i).getQty());
                                data.setQuantity(String.valueOf(menuListAll.get(i).getQty()));

                            } else {
                                reorder++;
                            }
                        }

                        if (menuListResultDataGlobal.size() == reorder) {
                            Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                        }
                        qty += qtyCheck;
                    }
                }
            } else {
                Constant.menuListResultDataGlobal = null;
            }

        } else {

            Constant.menuListResultDataGlobal = new ArrayList<>();
            this.qty = 0;
            for (int i = 0; i < menuListAll.size(); i++) {
                if (menuListAll.get(i).getQty() > 0) {
                    int reorder = 0;
                    int qtyCheck = menuListAll.get(i).getQty();
                    if (Constant.menuListResultDataGlobal.size() > 0) {
                        for (MenuListResultData data : Constant.menuListResultDataGlobal) {
                            if (data.getMenuID().equals(menuListAll.get(i).getMenuID())) {
                                qtyCheck = 0;
                                data.setQty(menuListAll.get(i).getQty());
                                data.setQuantity(String.valueOf(menuListAll.get(i).getQty()));

                                List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                                List<NoteListResponseResultData> takes = new ArrayList<>();

                                for (int x = 0; x < data.getQty(); x++) {
                                    notes.add(new ArrayList<NoteListResponseResultData>());
                                    takes.add(new NoteListResponseResultData());
                                }

                                data.setNoteList(notes);
                                data.setTakeAway(takes);

                            } else {
                                reorder++;
                            }
                        }

                        if (Constant.menuListResultDataGlobal.size() == reorder) {
                            Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                        }
                    } else {
                        menuListAll.get(i).setQuantity(String.valueOf(menuListAll.get(i).getQty()));
                        Constant.menuListResultDataGlobal.add(menuListAll.get(i));
                    }
                    qty += qtyCheck;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int price = 0, qty = 0;
        if (menuListAll != null) {

            if (Constant.menuListResultDataGlobal != null) {
                if (Constant.menuListResultDataGlobal.size() > 0) {

                    //for (int x = 0; x < Constant.menuListResultDataGlobal.size(); x++) {
                    for (int i = 0; i < menuListAll.size(); i++) {
                        boolean exist = false;
                        for (int x = 0; x < Constant.menuListResultDataGlobal.size(); x++) {
                            if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                menuListAll.get(i).setNoteList(Constant.menuListResultDataGlobal.get(x).getNoteList());
                                menuListAll.get(i).setTakeAway(Constant.menuListResultDataGlobal.get(x).getTakeAway());
                                menuListAll.get(i).setTakeawayIndex(Constant.menuListResultDataGlobal.get(x).getTakeawayIndex());

                                int noteTake = 0;
                                for (NoteListResponseResultData takeaway : Constant.menuListResultDataGlobal.get(x).getTakeAway()) {
                                    if (takeaway.getTakeAway() != null) {
                                        if (takeaway.getTakeAway().equals("1")) {
                                            noteTake += Integer.parseInt(tableResponseResultData.get(0).get(0).getTakeAwayFee());
                                        }
                                    }
                                }

                                for (List<NoteListResponseResultData> notelist : Constant.menuListResultDataGlobal.get(x).getNoteList()) {
                                    for (NoteListResponseResultData note : notelist) {
                                        noteTake += Integer.parseInt(note.getPrice());
                                    }
                                }

                                price += (Integer.parseInt(Constant.menuListResultDataGlobal.get(x).getSpecialPrice()) * Constant.menuListResultDataGlobal.get(x).getQty()) + noteTake;

                                qty += Constant.menuListResultDataGlobal.get(x).getQty();
                                exist = true;
                            } else if (Constant.menuListResultDataGlobal.get(x).getMenuID().equals(menuListAll.get(i).getMenuID())
                                    && !Constant.menuListResultDataGlobal.get(x).getMenuTypeID().equals(menuListAll.get(i).getMenuTypeID())) {
                                menuListAll.get(i).setQty(Constant.menuListResultDataGlobal.get(x).getQty());
                                menuListAll.get(i).setNoteList(Constant.menuListResultDataGlobal.get(x).getNoteList());
                                menuListAll.get(i).setTakeAway(Constant.menuListResultDataGlobal.get(x).getTakeAway());
                                menuListAll.get(i).setTakeawayIndex(Constant.menuListResultDataGlobal.get(x).getTakeawayIndex());
                                exist = true;
                            }
                        }
                        if (!exist) {
                            menuListAll.get(i).setQty(0);
                        }
                    }

                    setMenuTypeList();
                    qTy = qty;
                    sumPrice = price;
                    setView();
                } else {
                    getMenuList();
                    qTy = 0;
                    sumPrice = 0;
                    setView();
                }

            } else {
                getMenuList();
                qTy = 0;
                sumPrice = 0;
                setView();
            }

            if (!Constant.reOrder) {
                synchronized (this) {
                    getOverrideData();
                }
            } else {
                synchronized (this) {
                    getOverrideDataReOrder();
                }
            }
        }
    }

    @OnClick({R.id.btn_home, R.id.btn_cart})
    public void onViewClickedEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                /*
                getBackActivity();
                finish();
                */
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("HotDeal", false);
                startActivity(intent);
                break;
            case R.id.btn_cart:
                getStartActivity();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getBackActivity();
    }

    private void getBackActivity() {
        getOverrideData();
    }

    @OnClick(R.id.txt_total)
    public void onClick() {
    }
}
