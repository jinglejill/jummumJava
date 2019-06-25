package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
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

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class CancleOrderActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_note)
    EditText txtNote;
    @BindView(R.id.btn_select_note)
    Button btnSelectNote;
    @BindView(R.id.txt_price)
    EditText txtPrice;
    @BindView(R.id.txt_price_range)
    TextView txtPriceRange;
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
    private List<List<DisputeResultData>> dispute;
    private List<String> CLUBS_TH, CLUBS_EN;
    private int select_dispote = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancle_order);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        orders = Parcels.unwrap(getIntent().getParcelableExtra("Orders"));
        commonRepository = new CommonRepository();

        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
        String yourFormattedString = formatter.format(Double.parseDouble(orders.getNetTotal()));
        txtPriceRange.setText("จำนวนเงิน THB 0.01 to " + yourFormattedString);
        txtPrice.setText(yourFormattedString);

        txtTel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        showProgressDialog();
        commonRepository.getDispute(1, new IHttpCallback<List<List<DisputeResultData>>>() {
            @Override
            public void onSuccess(List<List<DisputeResultData>> response) {
                hideProgressDialog();
                dispute = response;
                CLUBS_TH = new ArrayList<>();
                CLUBS_EN = new ArrayList<>();


                for (DisputeResultData data : dispute.get(0)) {
                    CLUBS_TH.add(data.getText());
                    CLUBS_EN.add(data.getTextEn());
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
                new AlertDialog.Builder(CancleOrderActivity.this);
        builder.setTitle("เหตุผลที่ต้องการขอคืนเงิน");
        builder.setItems(CLUBS_TH.toArray(new String[CLUBS_TH.size()])
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_dispote = which;
                        txtNote.setText(CLUBS_TH.get(select_dispote));
                    }
                });
        builder.create();
        builder.show();
    }

    private boolean validate() {

        boolean valid = true;
        if (txtNote.getText().length() == 0) {
            txtNote.setError("กรุณาเลือก "+getString(R.string.note));
            valid = false;
        } else {
            txtNote.setError(null);
        }

        if (txtPrice.getText().length() == 0) {
            txtPrice.setError("กรุณาระบุ "+getString(R.string.price));
            valid = false;
        }
        else if(Double.parseDouble(txtPrice.getText().toString()) > Double.parseDouble(orders.getNetTotal()))
        {
            txtPrice.setError("จำนวนเงินไม่ถูกต้อง");
            valid = false;
        }
        else
        {
            txtPrice.setError(null);
        }

        if (txtNoteDesc.getText().length() == 0) {
            txtNoteDesc.setError("กรุณาระบุ "+getString(R.string.note));
            valid = false;
        } else {
            txtNoteDesc.setError(null);
        }

        if (txtTel.getText().length() == 0) {
            txtTel.setError("กรุณาระบุ "+getString(R.string.tel));
            valid = false;
        } else {
            txtTel.setError(null);
        }
        return valid;
    }

    @OnClick({R.id.btn_yes, R.id.btn_no})
    public void onViewClickedAction(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                if(validate()){
                    setInsertCancelOrder();
                }
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }

    private void setInsertCancelOrder() {
        showProgressDialog();
        commonRepository.getInsertCancel(orders.getReceiptID()
                , dispute.get(0).get(select_dispote).getDisputeReasonID()
                , txtPrice.getText().toString()
                , txtNoteDesc.getText().toString()
                , txtTel.getText().toString()
                , PreferenceManager.getInstance().getUserName()
                , Util.getModifireDate(),
                new IHttpCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        hideProgressDialog();
                        Util.showAlertIOS(CancleOrderActivity.this,
                                "คำร้องขอคืนเงินเต็มจำนวน ได้ถูกส่งไปแล้ว กรุณารอการยืนยันจากร้านค้า",
                                new iOSDialogClickListener() {
                                    @Override
                                    public void onClick(iOSDialog dialog) {
                                        Intent intent = new Intent(CancleOrderActivity.this
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
