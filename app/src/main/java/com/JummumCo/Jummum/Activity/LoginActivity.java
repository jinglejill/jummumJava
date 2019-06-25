package com.JummumCo.Jummum.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.UserAccountAuthenResultData;
import com.JummumCo.Jummum.Respository.MemberRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.txt_username)
    EditText txtUsername;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.layout_register)
    LinearLayout layoutRegister;
    @BindView(R.id.layout_forget_password)
    RelativeLayout layoutForgetPassword;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.login_button_fb)
    LoginButton loginButtonFb;
    @BindView(R.id.logo_crop)
    ImageView logoCrop;
    private MemberRepository memberRepository;
    private CallbackManager callbackManager;
    private AccessToken mAccessToken;
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        memberRepository = new MemberRepository();

        callbackManager = CallbackManager.Factory.create();
        loginButtonFb.setReadPermissions("public_profile", "email");
        token = FirebaseInstanceId.getInstance().getToken();
        loginButtonFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mAccessToken = loginResult.getAccessToken();
                getUserProfile(mAccessToken);
            }

            @Override
            public void onCancel() {
                Log.i("fb = ", "cancle");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("fb = ", error.toString());
            }
        });
        //login();


        //set logo crop height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        logoCrop.getLayoutParams().height = 700*screenWidth/1024;
        logoCrop.requestLayout();


        //check first time installed
        int firstTimeInstalled = PreferenceManager.getInstance().getFirstTimeInstalled();
        if(firstTimeInstalled == 0)
        {
            //show dialog
            final Dialog dialog = new Dialog(LoginActivity.this);
            dialog.setContentView(R.layout.diaolog_first_install);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


            final Button btnOK = (Button) dialog.findViewById(R.id.btn_ok);
            final TextView txtDetial = (TextView) dialog.findViewById(R.id.txt_detail);


            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PreferenceManager.getInstance().setFirstTimeInstalled(1);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    private void getUserProfile(final AccessToken mAccessToken) {

        final Profile profile = Profile.getCurrentProfile();
        GraphRequest request = GraphRequest.newMeRequest(
                mAccessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("LoginActivity", response.toString());


                        try {

                            String strBirthday = "";
                            String birthday = object.getString("birthday").toString();
                            if(!birthday.equals(""))
                            {
                                Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                                strBirthday = dateFormat.format(date1);
                            }

                            memberRepository.getLoginFb(object.getString("email").toString(),
                                    "0",
                                    "",
                                    strBirthday,
                                    token,
                                    "",
                                    object.getString("name").toString(),
                                    object.getString("first_name").toString(),
                                    object.getString("last_name").toString(),
                                    "",
                                    "",
                                    object.getString("id").toString(),
                                    "",
                                    Util.getModifireDate(),
                                    object.getString("email").toString(),
                                    token,
                                    "login fb",
                                    getDeviceName(),
                                    new IHttpCallback<List<List<UserAccountAuthenResultData>>>() {
                                        @Override
                                        public void onSuccess(List<List<UserAccountAuthenResultData>> response) {

                                            if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                                                if (!response.get(0).get(0).getUserAccountID().equals(PreferenceManager.getInstance().getSaveCreditCard())) {
                                                    PreferenceManager.getInstance().setSaveCreditCard(null);
                                                }
                                            }

                                            PreferenceManager.getInstance().setMemberId(response.get(0).get(0).getUserAccountID());
                                            PreferenceManager.getInstance().setUsername(response.get(0).get(0).getUsername());
                                            PreferenceManager.getInstance().setFullName(response.get(0).get(0).getFullName());
                                            PreferenceManager.getInstance().setToken(response.get(0).get(0).getDeviceToken());


                                            //check fb login before
                                            boolean foundLoginBefore = false;
                                            Dictionary fbLoginDic = PreferenceManager.getInstance().getFbLoginDic();
                                            if(fbLoginDic != null)
                                            {
                                                for (Enumeration e = fbLoginDic.keys(); e.hasMoreElements();)
                                                {
                                                    String key = (String) e.nextElement();
                                                    if(key.equals(response.get(0).get(0).getUsername()))
                                                    {
                                                        foundLoginBefore = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                fbLoginDic = new Hashtable();
                                            }

                                            if(!foundLoginBefore)
                                            {
                                                fbLoginDic.put(response.get(0).get(0).getUsername(),"1");
                                                PreferenceManager.getInstance().setFbLoginDic(fbLoginDic);


                                                Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
                                                intent.putExtra("UserAccountID", response.get(0).get(0).getUserAccountID());
                                                intent.putExtra("Email", response.get(0).get(0).getEmail());
                                                intent.putExtra("Username", response.get(0).get(0).getUsername());
                                                intent.putExtra("FirstName", response.get(0).get(0).getFirstName());
                                                intent.putExtra("LastName", response.get(0).get(0).getLastName());
                                                intent.putExtra("BirthDate", response.get(0).get(0).getBirthDate());
                                                intent.putExtra("PhoneNo", response.get(0).get(0).getPhoneNo());
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onError(String message) {

                                        }
                                    });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void login() {
        //        String pass = "37bc854596cb721fca1da704fd385296bf881462013f09ffd80af72f0e92b5bc";
//        String username = "thidaporn.kijkamjai@gmail.com";
        String pass = txtPassword.getText().toString().trim();
        String username = txtUsername.getText().toString().trim();

        memberRepository.login(pass, username, username, token,getDeviceName(), new IHttpCallback<List<List<UserAccountAuthenResultData>>>() {
            @Override
            public void onSuccess(List<List<UserAccountAuthenResultData>> response) {

                if (response.get(0).size() > 0) {
                    UserAccountAuthenResultData resp = response.get(0).get(0);
                    if (resp.getUsername() != "") {

                        if (PreferenceManager.getInstance().getSaveCreditCard() != null) {
                            if (!resp.getUserAccountID().equals(PreferenceManager.getInstance().getSaveCreditCard())) {
                                PreferenceManager.getInstance().setSaveCreditCard(null);
                            }
                        }
                        PreferenceManager.getInstance().setMemberId(resp.getUserAccountID());
                        PreferenceManager.getInstance().setUsername(resp.getUsername());
                        PreferenceManager.getInstance().setFullName(resp.getFirstName() + " " + resp.getLastName());
                        PreferenceManager.getInstance().setToken(resp.getDeviceToken());

                        Log.i("xxx", "");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Util.showAlert(LoginActivity.this, "Username หรือ Password ไม่ถูกต้อง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    }
                }else {
                    Util.showAlert(LoginActivity.this, "เกิดข้อผิดพลาด กรุณาทำรายการใหม่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                }
            }

            @Override
            public void onError(String message) {
                Util.showAlert(LoginActivity.this, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        });




    }

    @OnClick(R.id.layout_register)
    public void onViewClickedRegister() {
        Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_forget_password)
    public void onViewClickedForgetPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClickedLogin() {

//        Dictionary fbLoginDic = new Hashtable();
//        fbLoginDic.put("b","2");
//        PreferenceManager.getInstance().setFbLoginDic(fbLoginDic);
//
//        PreferenceManager.getInstance().getFbLoginDic();
        login();
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
