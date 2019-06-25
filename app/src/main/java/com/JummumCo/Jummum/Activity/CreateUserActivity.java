package com.JummumCo.Jummum.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.BaseResponse;
import com.JummumCo.Jummum.Respository.MemberRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateUserActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_email)
    EditText txtEmail;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.txt_full_name)
    EditText txtFullName;
    @BindView(R.id.txt_birth)
    EditText txtBirth;
    @BindView(R.id.txt_tel)
    EditText txtTel;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_select_birth)
    Button btnSelectBirth;
    @BindView(R.id.btn_clear_birthday)
    Button btnClearBirthday;
    @BindView(R.id.txt_last_name)
    EditText txtLastName;



    private MemberRepository memberRepository;
    private String token;
    private Calendar now;
//    private String birthDate;
    private String yearG = "";

    private String userAccountID;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String birthDate2;
    private String phoneNo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        memberRepository = new MemberRepository();
        token = FirebaseInstanceId.getInstance().getToken();
        now = Calendar.getInstance();


        userAccountID = getIntent().getStringExtra("UserAccountID");
        email = getIntent().getStringExtra("Email");
        username = getIntent().getStringExtra("Username");
        firstName = getIntent().getStringExtra("FirstName");
        lastName = getIntent().getStringExtra("LastName");
        birthDate2 = getIntent().getStringExtra("BirthDate");
        phoneNo = getIntent().getStringExtra("PhoneNo");
        if(username != null)
        {
            txtPassword.setVisibility(View.GONE);
            txtEmail.setVisibility(View.GONE);
//            txtEmail.setText(username);
            txtFullName.setText(firstName);
            txtLastName.setText(lastName);

            txtTel.setText(phoneNo);

            Util.showAlert(CreateUserActivity.this, "คุณล็อคอินผ่าน facebook สำเร็จแล้ว กรุณากรอกวันเกิด และเบอร์โทรศัพท์ของคุณ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }

        if(birthDate2 != null && !birthDate2.equals(""))
        {
            try
            {
                Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(birthDate2);
                DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
                String year = dateFormatYear.format(date1);

                DateFormat dateFormatMonth = new SimpleDateFormat("MMM");
                String month = dateFormatMonth.format(date1);

                DateFormat dateFormatDate = new SimpleDateFormat("d");
                String dayOfMonth = dateFormatDate.format(date1);


                txtBirth.setText(dayOfMonth + " " + month + " " + year);
                btnClearBirthday.setVisibility(View.VISIBLE);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if(txtBirth.getText().toString().equals(""))
        {
            btnClearBirthday.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.txt_birth)
    public void onViewClickedBirth() {

    }

    @OnClick(R.id.btn_commit)
    public void onViewClickedCommit() {

        if (validate())
        {
            String strBirthday = txtBirth.getText().toString();
            if(!strBirthday.equals(""))
            {
                Date date1= null;
                try {
                    date1 = new SimpleDateFormat("d MMM yyyy").parse(strBirthday);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                    strBirthday = dateFormat.format(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(username != null)//fb login-> update data
            {
                memberRepository.getUpdateUserAccount(userAccountID,
                        email,
                        strBirthday,
                        token,
                        txtPassword.getText().toString(),
                        txtFullName.getText().toString(),
                        txtLastName.getText().toString(),
                        username,
                        txtTel.getText().toString(),
                        Util.getModifireDate(),
                        email,
                        token, new IHttpCallback<BaseResponse>() {
                            @Override
                            public void onSuccess(BaseResponse response) {
                                Util.showAlert(CreateUserActivity.this, "สร้างบัญชีสำเร็จ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
//                                        finish();
                                    }
                                });
                            }

                            @Override
                            public void onError(String message) {
                                Util.showAlert(CreateUserActivity.this, message, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                            }
                        });
            }
            else//normal register
            {
                memberRepository.getCreateUser(txtEmail.getText().toString(),
                        strBirthday,
                        "", txtPassword.getText().toString(),
                        txtFullName.getText().toString(),
                        txtLastName.getText().toString(),
                        txtEmail.getText().toString(),
                        txtTel.getText().toString(),
                        Util.getModifireDate(),
                        txtEmail.getText().toString(),
                        token, new IHttpCallback<BaseResponse>() {
                            @Override
                            public void onSuccess(BaseResponse response) {
                                Util.showAlert(CreateUserActivity.this, "สร้างบัญชีสำเร็จ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        finish();
                                    }
                                });
                            }

                            @Override
                            public void onError(String message) {
                                Util.showAlert(CreateUserActivity.this, message, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                            }
                        });
            }

        }

    }

    private boolean validate() {
        boolean valid = true;
        if(username == null)
        {
            if (txtEmail.getText().length() == 0) {
                txtEmail.setError(getString(R.string.email));
                valid = false;
            } else {
                txtEmail.setError(null);
            }
            if (txtPassword.getText().length() == 0) {
                txtPassword.setError(getString(R.string.password));
                valid = false;
            } else {
                txtPassword.setError(null);
            }
        }

        if (txtFullName.getText().length() == 0) {
            txtFullName.setError(getString(R.string.fullname));
            valid = false;
        } else {
            txtFullName.setError(null);
        }
        if (txtLastName.getText().length() == 0) {
            txtLastName.setError(getString(R.string.lastName));
            valid = false;
        } else {
            txtLastName.setError(null);
        }
//        if (txtBirth.getText().length() == 0) {
//            txtBirth.setError(getString(R.string.birthDate));
//            valid = false;
//        } else {
//            txtBirth.setError(null);
//        }
//        if (txtTel.getText().length() == 0) {
//            txtTel.setError(getString(R.string.tel));
//            valid = false;
//        } else {
//            txtTel.setError(null);
//        }
        return valid;
    }

    @OnClick(R.id.btn_back)
    public void onViewClickedBack() {
        finish();
    }

    @OnClick(R.id.btn_select_birth)
    public void onViewClickedSelectBirth() {

        int mDay = 0;
        int mMonth = 0;
        int mYear = 0;
        if(txtBirth.getText().toString().equals(""))
        {
            String mday = "01";
            String mmonth="00";

            //convert them to int
            mDay=Integer.valueOf(mday);
            mMonth=Integer.valueOf(mmonth);
            mYear = now.get(Calendar.YEAR);

            if (yearG == "") {
                mYear = mYear - 20;
            }
        }
        else
        {
            String birthDay = txtBirth.getText().toString();
            try
            {
                Date date1=new SimpleDateFormat("d MMM yyyy").parse(birthDay);

                DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
                mYear = Integer.parseInt(dateFormatYear.format(date1));

                DateFormat dateFormatMonth = new SimpleDateFormat("MM");
                mMonth = Integer.parseInt(dateFormatMonth.format(date1))-1;

                DateFormat dateFormatDate = new SimpleDateFormat("d");
                mDay = Integer.parseInt(dateFormatDate.format(date1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;

//                        birthDate = year + "-" + month + "-" + dayOfMonth;


                        String monthChar = getGenMonth(month);

                        txtBirth.setText(dayOfMonth + " " + monthChar + " " + year);
                        btnClearBirthday.setVisibility(View.VISIBLE);
                        yearG = monthChar;

                        now.set(year, month, dayOfMonth);
                    }
                },
                mYear,
                mMonth,
                mDay
        ).show();

    }

    @OnClick(R.id.btn_clear_birthday)
    public void onViewClickedClearBirthday() {
        txtBirth.setText("");
        btnClearBirthday.setVisibility(View.GONE);
    }

        public String getGenMonth(int month){

        switch (month){
            case 1 : {
                return "Jan";
            }

            case 2 : {
                return "Feb";

            }
            case 3 : {
                return "Mar";

            }
            case 4 : {
                return "Apr";

            }
            case 5 : {
                return "May";

            }
            case 6 : {
                return "Jun";

            }
            case 7 : {
                return "Jul";

            }
            case 8 : {
                return "Aug";

            }
            case 9 : {
                return "Sep";

            }
            case 10 : {
                return "Oct";

            }
            case 11 : {
                return "Nov";

            }
            case 12 : {
                return "Dec";

            }
        }
        return "";
    }
}
