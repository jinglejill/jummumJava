package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BankResultData;
import com.JummumCo.Jummum.Model.DisputeResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferDisputeActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_refund_amount)
    TextView txtRefundAmount;
    @BindView(R.id.txt_note)
    EditText txtNote;
    @BindView(R.id.btn_select_note)
    Button btnSelectNote;
    @BindView(R.id.txt_account_no)
    EditText txtAccountNo;

    @BindView(R.id.txt_note_desc)
    EditText txtNoteDesc;
    @BindView(R.id.txt_tel)
    EditText txtTel;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.main_content)
    LinearLayout mainContent;


    private OrderListResultData orders;
    private CommonRepository commonRepository;
    private List<List<BankResultData>> bank;
    private List<String> CLUBS_TH, CLUBS_EN;
    private int selectedIndexBank = 0;

    private String receiptID = "0";
    private String strRefundAmount = "0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_dispute);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        strRefundAmount = getIntent().getStringExtra("RefundAmount");
        receiptID = getIntent().getStringExtra("ReceiptID");
        commonRepository = new CommonRepository();

        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
        String yourFormattedString = formatter.format(Double.parseDouble(strRefundAmount));
        txtRefundAmount.setText(yourFormattedString + " บาท");


        txtTel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        showProgressDialog();
        commonRepository.getTransferFormAndBank(receiptID, new IHttpCallback<List<List<BankResultData>>>() {
            @Override
            public void onSuccess(List<List<BankResultData>> response) {
                hideProgressDialog();
                bank = response;
                CLUBS_TH = new ArrayList<>();
                CLUBS_EN = new ArrayList<>();


                for (BankResultData data : bank.get(1)) {
                    CLUBS_TH.add(data.getBankName());
                    CLUBS_EN.add(data.getBankNameEn());
                }

            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
            }
        });
    }

    @OnClick(R.id.btn_select_note)
    public void onViewClickedSearchNote() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(TransferDisputeActivity.this);
//        builder.setTitle("เหตุผลที่ต้องการขอคืนเงิน");
        builder.setItems(CLUBS_TH.toArray(new String[CLUBS_TH.size()])
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndexBank = which;
                        txtNote.setText(CLUBS_TH.get(selectedIndexBank));
                    }
                });
        builder.create();
        builder.show();
    }

    private boolean validate() {

        boolean valid = true;
        return valid;
    }

    @OnClick({R.id.btn_yes, R.id.btn_no})
    public void onViewClickedAction(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                if(validate()){
                    setInsertTransferForm();
                }
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }

    private void setInsertTransferForm() {
        showProgressDialog();
        commonRepository.getInsertTransferForm(receiptID
                , bank.get(1).get(selectedIndexBank).getBankID()
                , txtAccountNo.getText().toString()
                ,strRefundAmount
                , txtTel.getText().toString()
                , txtNoteDesc.getText().toString()
                , PreferenceManager.getInstance().getUserName()
                , Util.getModifireDate(),
                new IHttpCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        hideProgressDialog();
                        Util.showAlertIOS(TransferDisputeActivity.this,
                                "แบบฟอร์มยืนยันการโอนเงินได้ถูกส่งไปแล้ว",
                                new iOSDialogClickListener() {
                                    @Override
                                    public void onClick(iOSDialog dialog) {
                                        Intent intent = new Intent(TransferDisputeActivity.this
                                                , HistoryOrderActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                });
                    }

                    @Override
                    public void onError(String message) {
                        hideProgressDialog();
                        message = "Cannot connect to server";
                        Util.showToast(mainContent,message);
                    }
                }
        );
    }

    @OnClick(R.id.btn_back)
    public void onViewClickedBack() {
        finish();
    }
}
