package com.JummumCo.Jummum.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Respository.MemberRepository;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.UserAccountAuthenResultData;
import com.android.jummum.R;
import com.JummumCo.Jummum.Util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.layout_email)
    LinearLayout layoutEmail;
    @BindView(R.id.txt_email_title)
    TextView txtEmailTitle;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_fullname)
    TextView tvFullname;
    @BindView(R.id.tv_lastname)
    TextView tvLastname;
    @BindView(R.id.tv_birth_date)
    TextView tvBirthDate;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    MemberRepository memberRepository;
    UserAccountAuthenResultData userAccountAuthenResultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        memberRepository = new MemberRepository();

        memberRepository.getUserData(PreferenceManager.getInstance().getUserName(), PreferenceManager.getInstance().getToken(),
                PreferenceManager.getInstance().getUserName(), new IHttpCallback<List<List<UserAccountAuthenResultData>>>() {
                    @Override
                    public void onSuccess(List<List<UserAccountAuthenResultData>> response) {
                        userAccountAuthenResultData = response.get(0).get(0);
                        setView();
                    }

                    @Override
                    public void onError(String message) {
                        message = "Cannot connect to server";
                        Util.showToast(mainContent,message);
                    }
                });
    }

    private void setView()
    {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean isEmail = userAccountAuthenResultData.getUsername().matches(regex);

        if(!isEmail)//fb
        {
            if(userAccountAuthenResultData.getEmail().equals(""))
            {
                //hide
                layoutEmail.setVisibility(View.GONE);
            }
            txtEmailTitle.setText("อีเมล (FB)");
        }
        tvEmail.setText(userAccountAuthenResultData.getEmail().toString());
        tvFullname.setText(userAccountAuthenResultData.getFirstName());
        tvLastname.setText(userAccountAuthenResultData.getLastName());
        tvBirthDate.setText(userAccountAuthenResultData.getBirthDate().substring(0,10));
        tvTel.setText(Util.phoneFormat(userAccountAuthenResultData.getPhoneNo()));
    }

    @OnClick(R.id.btn_back)
    public void onViewClickedBack() {
        finish();
    }
}
