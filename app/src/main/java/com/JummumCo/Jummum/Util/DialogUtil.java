package com.JummumCo.Jummum.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;

import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by likit on 13-Jan-17.
 */

public class DialogUtil {

    private ProgressDialog progressDialog;
    private Activity activity;

    public DialogUtil(Activity activity) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    public void showProgressDialog(final String message) {
        if (progressDialog.isShowing()) {
            return;
        }


        SpannableString spannableString =  new SpannableString(message + "...");
        AssetManager am = getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am,  "font/prompt-regular.ttf");
        spannableString.setSpan(custom_font, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        progressDialog.setMessage(spannableString);

//        progressDialog.setMessage(message + "...");
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void cancel() {
        progressDialog.dismiss();
    }
}
