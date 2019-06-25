package com.JummumCo.Jummum.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Activity.MarketActivity;
import com.JummumCo.Jummum.Activity.MenuActivity;
import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class QrFragment extends BaseFragment {


    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.scanner_view)
    CodeScannerView scannerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_search)
    TextAwesome btnSearch;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.layout_back)
    RelativeLayout layoutBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    private WrapperFragment wrapperFragment;
    private CodeScanner mCodeScanner;
    CommonRepository commonRepository;

    private List<OrderTaking2ResultData> orders;

    public QrFragment() {
        super();
    }

    public static QrFragment newInstance() {
        QrFragment fragment = new QrFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_scanqr, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        wrapperFragment = (WrapperFragment) getParentFragment();

    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {

        commonRepository = new CommonRepository();
        btnBack.setVisibility(View.GONE);
        layoutBack.setVisibility(View.GONE);

//       getscanQr("http://www.jummum.co/app/appStorePlayStore.php?tableNo= F3D63C45FE03A85344BD9E8D06B58BEB374A9D7A9F84D4EAFA32E05986A72A27");
    }


    private void setNewCameraQr() {
        final Activity activity = getActivity();
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        getscanQr(result.getText());
                    }
                });
            }
        });
    }

    private boolean setPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 50);
            return true;
        } else {
            return false;
        }
    }


    private void getscanQr(String resultText) {
        commonRepository.getScanQr(resultText, PreferenceManager.getInstance().getToken(), PreferenceManager.getInstance().getUserName(), getHttpCallback());
    }

    @NonNull
    private IHttpCallback<List<List<BranchAndCustomerTableResponseResultData>>> getHttpCallback() {
        return new IHttpCallback<List<List<BranchAndCustomerTableResponseResultData>>>() {
            @Override
            public void onSuccess(List<List<BranchAndCustomerTableResponseResultData>> response) {

                if (response.get(0).size() > 0) {
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    if (response.get(0).get(0).getBranchID() != null) {

                        intent.putExtra("BranchID", response.get(0).get(0).getBranchID());
                        intent.putExtra("TableQR", Parcels.wrap(response));


                        if (Constant.tableQrCode != null) {
                            if (!response.get(0).get(0).getBranchID().equals(Constant.tableQrCode.get(0).get(0).getBranchID())) {
                                Constant.menuListResultDataGlobal = null;
                            }
                        } else {
                            Constant.tableQrCode = response;
                        }

                    } else {
                        Util.showAlert(getContext(), "Qr Code ไม่ถูกต้อง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        onResume();
                        return;
                    }

                    if (response.size() > 2) {

                        if (response.get(2) != null) {
                            if (response.get(2).size() > 0) {
                                if (!response.get(2).get(0).getBuffetReceiptID().equals("0")) {
                                    intent.putExtra("BuffetReceiptID", response.get(2).get(0).getBuffetReceiptID());
                                }

                                if (!response.get(2).get(0).getVoucherCode().equals("")) {
                                    HotDealData hotDeal = new HotDealData();
                                    hotDeal.setVoucherCode(response.get(2).get(0).getVoucherCode());
                                    intent.putExtra("HotDeal", Parcels.wrap(hotDeal));
                                }

                            }
                        }

                        if (response.get(3) != null) {
                            if (response.get(3).size() > 0) {
                                orders = new ArrayList<>();
                                OrderTaking2ResultData order = new OrderTaking2ResultData();
                                Constant.menuListResultDataGlobal = new ArrayList<>();

                                for (BranchAndCustomerTableResponseResultData data : response.get(3)) {
                                    if (Constant.menuListResultDataGlobal.size() <= 0) {
                                        MenuListResultData menu = new MenuListResultData();

                                        menu.setMenuID(String.valueOf(data.getMenuID()));
                                        menu.setQty(Integer.parseInt(data.getQuantity()));
                                        menu.setQuantity(String.valueOf(data.getQuantity()));
                                        menu.setMenuTypeID(String.valueOf(data.getMenuTypeID()));
                                        menu.setPrice(data.getPrice());
                                        menu.setSpecialPrice(data.getSpecialPrice());

                                        menu.setBranchID(data.getBranchID());

                                        List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                                        List<NoteListResponseResultData> note = new ArrayList<>();
                                        if (response.get(4).size() <= 0) {

                                            for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                notes.add(new ArrayList<NoteListResponseResultData>());
                                            }
                                            menu.setNoteList(notes);

                                        } else {
                                            for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
//                                            notes.add(data.getNotes());
                                                for (BranchAndCustomerTableResponseResultData dataNote : response.get(4)) {
                                                    if (data.getSaveOrderTakingID().equals(dataNote.getSaveOrderTakingID())) {
                                                        note.add(dataNote.getNotes().get(0));
                                                    }
                                                }
                                                notes.add(note);
                                            }

                                            if (notes.size() <= 0) {
                                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                    notes.add(new ArrayList<NoteListResponseResultData>());
                                                }
                                            }

                                            menu.setNoteList(notes);
                                        }


                                        List<NoteListResponseResultData> takes = new ArrayList<>();
                                        for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                            NoteListResponseResultData take = new NoteListResponseResultData();
                                            take.setTakeAway(data.getTakeAway());
                                            takes.add(take);
                                        }

                                        menu.setTakeAway(takes);

                                        List<Integer> takeIndex = new ArrayList<>();
                                        takeIndex.add(Integer.valueOf(data.getTakeAway()));
                                        menu.setTakeawayIndex(takeIndex);

//                                    branchs = new ArrayList<>();
//
//                                    List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
//                                    BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
//                                    branch.setBranchID(orders.getBranchID());
//                                    branch.setName(orders.getBranch().get(0).getName());
//                                    branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
//                                    branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
//                                    branchs1.add(branch);
//                                    branchs.add(branchs1);


                                        Constant.menuListResultDataGlobal.add(menu);

                                    } else {

                                        MenuListResultData menu = new MenuListResultData();
                                        int replateOrder = 0;
                                        for (MenuListResultData global : Constant.menuListResultDataGlobal) {
                                            if (global.getMenuID().equals(data.getMenuID())) {
                                                replateOrder = 1;
                                                global.setQty(global.getQty() + Integer.parseInt(data.getQuantity()));

                                                List<List<NoteListResponseResultData>> notes = new ArrayList<>();

                                                if (response.get(4).size() <= 0) {
                                                    for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                        global.getNoteList().add(new ArrayList<NoteListResponseResultData>());
                                                    }
                                                } else {

                                                    for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                        for (BranchAndCustomerTableResponseResultData dataNote : response.get(4)) {
                                                            if (data.getSaveOrderTakingID().equals(dataNote.getSaveOrderTakingID())) {
                                                                global.getNoteList().add(data.getNotes());
                                                            }
                                                        }
                                                    }

                                                }


                                                NoteListResponseResultData take = new NoteListResponseResultData();
                                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                    take.setTakeAway(data.getTakeAway());
                                                    global.getTakeAway().add(take);
                                                }

                                                global.getTakeawayIndex().add(Integer.valueOf(data.getTakeAway()));

                                        /*
                                        branchs = new ArrayList<>();

                                        List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
                                        BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
                                        branch.setBranchID(orders.getBranchID());
                                        branch.setName(orders.getBranch().get(0).getName());
                                        branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                                        branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
                                        branchs1.add(branch);
                                        branchs.add(branchs1);
                                        */


                                                return;
                                            }
                                        }

                                        if (replateOrder != 1) {
                                            if (response.get(3).size() > 0) {
                                                menu.setMenuID(String.valueOf(data.getMenuID()));
                                                menu.setQty(Integer.parseInt(data.getQuantity()));
                                                menu.setQuantity(String.valueOf(data.getQuantity()));
                                                menu.setMenuTypeID(String.valueOf(data.getMenuTypeID()));
                                                menu.setPrice(data.getPrice());
                                                menu.setSpecialPrice(data.getSpecialPrice());

                                                menu.setBranchID(data.getBranchID());

                                                List<List<NoteListResponseResultData>> notes = new ArrayList<>();
                                                List<NoteListResponseResultData> note = new ArrayList<>();
                                                if (response.get(4).size() <= 0) {

                                                    for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                        notes.add(new ArrayList<NoteListResponseResultData>());
                                                    }
                                                    menu.setNoteList(notes);

                                                } else {
                                                    for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                        for (BranchAndCustomerTableResponseResultData dataNote : response.get(4)) {
                                                            if (data.getSaveOrderTakingID().equals(dataNote.getSaveOrderTakingID())) {
                                                                note.add(dataNote.getNotes().get(0));

                                                            }
                                                        }
                                                        notes.add(note);
                                                    }

                                                    if (notes.size() <= 0) {
                                                        for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                            notes.add(new ArrayList<NoteListResponseResultData>());
                                                        }
                                                    }

                                                    menu.setNoteList(notes);
                                                }


                                                List<NoteListResponseResultData> takes = new ArrayList<>();
                                                for (int i = 0; i < Integer.parseInt(data.getQuantity()); i++) {
                                                    NoteListResponseResultData take = new NoteListResponseResultData();
                                                    take.setTakeAway(data.getTakeAway());
                                                    takes.add(take);
                                                }

                                                menu.setTakeAway(takes);

                                                List<Integer> takeIndex = new ArrayList<>();
                                                takeIndex.add(Integer.valueOf(data.getTakeAway()));
                                                menu.setTakeawayIndex(takeIndex);

                                        /*
                                        branchs = new ArrayList<>();

                                        List<BranchAndCustomerTableResponseResultData> branchs1 = new ArrayList<>();
                                        BranchAndCustomerTableResponseResultData branch = new BranchAndCustomerTableResponseResultData();
                                        branch.setBranchID(orders.getBranchID());
                                        branch.setName(orders.getBranch().get(0).getName());
                                        branch.setTakeAwayFee(orders.getBranch().get(0).getTakeAwayFee());
                                        branch.setImageUrl(orders.getBranch().get(0).getImageUrl());
                                        branchs1.add(branch);
                                        branchs.add(branchs1);
                                        */

                                                Constant.menuListResultDataGlobal.add(menu);
                                            }
                                        }

                                    }
                                }

                            }
                        }
                    }


                    startActivity(intent);
                } else {
                    Util.showAlert(getContext(), "Qr Code ไม่ถูกต้อง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    onResume();
                }
            }

            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
                onResume();
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

    @Override
    public void onResume() {
        super.onResume();
        if (!setPermission()) {
            if (mCodeScanner != null) {
                mCodeScanner.startPreview();
            } else {
                setNewCameraQr();
                mCodeScanner.startPreview();
            }

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (!setPermission()) {
            mCodeScanner.releaseResources();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (!setPermission()) {
            mCodeScanner.stopPreview();
        }
    }

    @OnClick(R.id.btn_search)
    public void onViewClickedSearch() {
        Intent intent = new Intent(getContext(), MarketActivity.class);
        startActivity(intent);
    }
}
