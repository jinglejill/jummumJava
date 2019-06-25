package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Respository.MemberRepository;
import com.JummumCo.Jummum.Model.BaseResponse;
import com.android.jummum.R;
import com.JummumCo.Jummum.Util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.txt_username)
    EditText txtUsername;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.layout_button)
    RelativeLayout layoutButton;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private MemberRepository memberRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        memberRepository = new MemberRepository();
    }

    @OnClick({R.id.btn_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_commit:
                if (validate()){
                    memberRepository.getForgetPassword(txtUsername.getText().toString(), "",
                            txtUsername.getText().toString(), "send email for reset password&",
                            new IHttpCallback<BaseResponse>() {
                                @Override
                                public void onSuccess(BaseResponse response) {
                                    Util.showAlert(ForgetPasswordActivity.this, "เราได้ส่งอีเมลให้คุณแล้ว กรุณาเช็คอีเมลของคุณ(ถ้าไม่พบ กรุณาตรวจสอบใน Junk mail ค่ะ)", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                            finish();

                                        }
                                    });
                                }

                                @Override
                                public void onError(String message) {
                                    Util.showAlert(ForgetPasswordActivity.this, message, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                }
                            });
                }
                break;
        }
    }

    private boolean validate() {
        boolean valid = true;
        if (txtUsername.getText().length() == 0) {
            txtUsername.setError(getString(R.string.username_lb));
            valid = false;
        } else {
            txtUsername.setError(null);
        }
        return valid;
    }
}
