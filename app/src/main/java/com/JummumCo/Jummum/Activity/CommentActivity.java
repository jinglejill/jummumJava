package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class CommentActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.txt_desc)
    EditText txtDesc;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private CommonRepository commonRepository;
    private String ratingID;
    private String receiptID;
    private String score;
    private String comment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        commonRepository = new CommonRepository();

        ratingID = getIntent().getStringExtra("RatingID");
        receiptID = getIntent().getStringExtra("ReceiptID");
        score = getIntent().getStringExtra("Score");
        comment = getIntent().getStringExtra("Comment");

        txtDesc.setText(comment);
        if(comment != null && !comment.equals(""))
        {
            layoutBottom.setVisibility(View.GONE);
            txtDesc.setEnabled(false);
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_yes, R.id.btn_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_yes:
                callData();
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }

    private void callData() {
        showProgressDialog();
        if(comment != null)
        {
            commonRepository.getUpdateRating(ratingID,
                    receiptID,
                    score,
                    txtDesc.getText().toString(),
                    PreferenceManager.getInstance().getUserName(),
                    Util.getModifireDate(),
                    new IHttpCallback<String>() {
                        @Override
                        public void onSuccess(String response) {
                            hideProgressDialog();

                            Util.showAlertIOS(CommentActivity.this,
                                    "ข้อเสนอแนะ และคำติชมได้ถูกส่งไปแล้ว ขอบคุณค่ะ",
                                    new iOSDialogClickListener() {
                                        @Override
                                        public void onClick(iOSDialog dialog) {
                                            dialog.dismiss();
                                            if ( Constant.commentMe) {
                                                Intent intent = new Intent(CommentActivity.this
                                                        , HistoryOrderActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }else {
                                                finish();
                                            }
                                        }
                                    });

                        }

                        @Override
                        public void onError(String message) {
                            hideProgressDialog();
                            message = "Cannot connect to server";
                            Util.showToast(mainContent,message);
                        }
                    });
        }
        else
        {
            commonRepository.getInsertComment(PreferenceManager.getInstance().getMemberId(),
                    txtDesc.getText().toString(),
                    PreferenceManager.getInstance().getUserName(),
                    Util.getModifireDate(),
                    new IHttpCallback<String>() {
                        @Override
                        public void onSuccess(String response) {
                            hideProgressDialog();

                            Util.showAlertIOS(CommentActivity.this,
                                    "ข้อเสนอแนะ และคำติชมได้ถูกส่งไปแล้ว ขอบคุณค่ะ",
                                    new iOSDialogClickListener() {
                                        @Override
                                        public void onClick(iOSDialog dialog) {
                                            dialog.dismiss();
                                            if ( Constant.commentMe) {
                                                Intent intent = new Intent(CommentActivity.this
                                                        , HistoryOrderActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }else {
                                                finish();
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void onError(String message) {
                            hideProgressDialog();
                            message = "Cannot connect to server";
                            Util.showToast(mainContent,message);
                        }
                    });
        }
    }
}
