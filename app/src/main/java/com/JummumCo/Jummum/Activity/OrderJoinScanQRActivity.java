package com.JummumCo.Jummum.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.OrderJoinResultData;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class OrderJoinScanQRActivity extends BaseActivity {


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
    @BindView(R.id.title_header)
    TextView titleHeader;

    private CodeScanner mCodeScanner;
    CommonRepository commonRepository;

    private List<OrderTaking2ResultData> orders;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scanqr);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        btnBack.setVisibility(View.VISIBLE);
        titleHeader.setText("สแกน QR Code เพื่อร่วมสั่งอาหาร");
        btnSearch.setVisibility(View.GONE);
        commonRepository = new CommonRepository();
//        getscanQr("7B23F0D93EBCBCBBF90FA65A11A718180AA6C116D78989CFAE57950A0AC43460");
    }


    private void setNewCameraQr() {

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getscanQr(result.getText());
                    }
                });
            }
        });
    }

    private boolean setPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
            return true;
        } else {
            return false;
        }
    }


    private void getscanQr(String resultText) {
        commonRepository.getScanQrderJoinQRInsert(PreferenceManager.getInstance().getMemberId()
                , resultText
                , PreferenceManager.getInstance().getUserName()
                , getHttpCallbackQR());
    }

    @NonNull
    private IHttpCallback<List<OrderJoinResultData>> getHttpCallbackQR() {
        return new IHttpCallback<List<OrderJoinResultData>>() {
            @Override
            public void onSuccess(List<OrderJoinResultData> response) {
                if (response.size() > 0) {
                    onStop();
                    setResult(RESULT_OK);
                    finish();

                } else {
                    Util.showAlert(OrderJoinScanQRActivity.this
                            , "Qr Code ไม่ถูกต้อง", new DialogInterface.OnClickListener() {
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

    @OnClick(R.id.btn_back)
    public void onClickBack() {
        finish();
    }
}
