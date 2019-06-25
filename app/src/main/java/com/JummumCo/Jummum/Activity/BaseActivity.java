package com.JummumCo.Jummum.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;


import com.android.jummum.R;
import com.JummumCo.Jummum.Util.DialogUtil;


/**
 * Created by likit on 1/6/2560.
 */

public class BaseActivity extends AppCompatActivity {
    private DialogUtil dialogUtil;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        dialogUtil = new DialogUtil(this);
    }

    public void setupWithToobar (Toolbar toolbar){
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void showProgressDialog(){
        dialogUtil.showProgressDialog(getString(R.string.loading));
    }
    public void showProgressDialog(String message){
        dialogUtil.showProgressDialog(message);
    }
    public void hideProgressDialog(){
        dialogUtil.hideProgressDialog();
    }

}
