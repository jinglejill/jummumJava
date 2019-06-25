package com.JummumCo.Jummum.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
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

public class ScanQrChooseTableActivity extends BaseActivity {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.scanner_view)
    CodeScannerView scannerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_search)
    TextAwesome btnSearch;

    private  CommonRepository commonRepository;
    private CodeScanner mCodeScanner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_choose_table);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        commonRepository = new CommonRepository();
    }

    private void setNewCameraQr() {
        final Activity activity = ScanQrChooseTableActivity.this;
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
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
        commonRepository.getScanQr(resultText, PreferenceManager.getInstance().getToken(), PreferenceManager.getInstance().getUserName(), getHttpCallback());
    }

    @NonNull
    private IHttpCallback<List<List<BranchAndCustomerTableResponseResultData>>> getHttpCallback() {
        return new IHttpCallback<List<List<BranchAndCustomerTableResponseResultData>>>() {
            @Override
            public void onSuccess(List<List<BranchAndCustomerTableResponseResultData>> response) {

                if (response.get(0).size() > 0) {
                    if (response.get(0).get(0).getBranchID() != null) {

                        Intent intent = getIntent();
                        intent.putExtra("BranchID", response.get(0).get(0).getBranchID());
                        intent.putExtra("TableQR", Parcels.wrap(response));
                        setResult(RESULT_OK, intent);
                        finish();

                        if (Constant.tableQrCode != null) {
                            if (!response.get(0).get(0).getBranchID().equals(Constant.tableQrCode.get(0).get(0).getBranchID())) {
                                Constant.menuListResultDataGlobal = null;
                            }
                        } else {
                            Constant.tableQrCode = response;
                        }

                    } else {
                        Util.showAlert(ScanQrChooseTableActivity.this
                                , "Qr Code ไม่ถูกต้อง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        onResume();
                    }
                } else {
                    Util.showAlert(ScanQrChooseTableActivity.this
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
    protected void onDestroy() {
        super.onDestroy();
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
    public void onClickBlack() {
        finish();
    }
}
