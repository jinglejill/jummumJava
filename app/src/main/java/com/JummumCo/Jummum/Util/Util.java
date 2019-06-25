package com.JummumCo.Jummum.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.ImageMenuBaseData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.android.jummum.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;


public class Util {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static Gson gson;

    public static Gson createGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setDateFormat(Constant.JSON_DATE_TIME_FORMAT)
                    .create();
        }
        return gson;
    }

    public static void showToast(View rootView, String text) {

        if (rootView == null || rootView.getContext() == null) {
            Toast.makeText(Contextor.getInstance().getContext()
                    , text
                    , Toast.LENGTH_SHORT).show();
        } else {
            Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT).show();
        }

    }

    public static void showAlert(Context context, String message, DialogInterface.OnClickListener cancleClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setMessage(message)
                .setCancelable(true);
        alertDialog.setNegativeButton("ตกลง", cancleClickListener);
        alertDialog.show();
    }

    public static void showAlertIOS(Context context, String message, iOSDialogClickListener clickListener) {
        new iOSDialogBuilder(context)
                .setSubtitle(message)
                .setBoldPositiveLabel(true)
                .setCancelable(false)
                .setPositiveListener("OK",clickListener)
                .build()
                .show();
    }

    public static void showConfirm(Context context, String message, DialogInterface.OnClickListener confirmClickListener, DialogInterface.OnClickListener cancelClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setMessage(message)
                .setCancelable(true);

        alertDialog.setPositiveButton("OK", confirmClickListener);
        alertDialog.setNegativeButton("Cancel", cancelClickListener);

        alertDialog.show();
    }

    public static String getErrorMessage(ResponseBody errorBody) {
        try {
            JSONObject jsonErrorBody = new JSONObject(errorBody.string());
            return jsonErrorBody.get("OnError").toString();
        } catch (IOException e) {
            return e.getMessage();
        } catch (JSONException e) {
            return e.getMessage();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String getModifireDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public static void loadImage2(final Context context, final ImageView imageView, final ProgressBar progressBar, String image, String branchId, String type) {
       progressBar.setVisibility(View.VISIBLE);
        new CommonRepository().getImage(image, type, branchId, new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response) {

//                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
//                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);

                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
//                imageView.setImageBitmap(decode);

                Glide.with(context)
                        .load(Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT))
                        .asBitmap()
                        .placeholder(R.drawable.noimage)
                        .into(imageView);

            }

            @Override
            public void onError(String message) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                Log.i("debug = ", message);
            }
        });
    }

    public static void loadImage3(final Context context, final ImageView imageView, final ProgressBar progressBar, String image, String branchId, String type, final String menuId)
    {
        progressBar.setVisibility(View.VISIBLE);
        new CommonRepository().getImage(image, type, branchId, new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response)
            {
                ImageMenuBaseData image = new ImageMenuBaseData();
                image.setMenuId(menuId);
                image.setImageBase64(response.get(0).getBase64StringImage());

                Constant.imageMenuBaseData64.add(image);

                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }

                Glide.with(context)
                        .load(Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT))
                        .asBitmap()
                        .placeholder(R.drawable.noimage)
                        .into(imageView);

            }

            @Override
            public void onError(String message) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                Log.i("debug = ", message);
            }
        });
    }

    public static void downloadImage(final Context context, final ImageView imageView, final ProgressBar progressBar, String image, String branchId, String type, final String menuId,final MenuListResultData item)
    {
        new CommonRepository().getImage(image, type, branchId, new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response)
            {
                ImageMenuBaseData image = new ImageMenuBaseData();
                image.setMenuId(menuId);
                image.setImageBase64(response.get(0).getBase64StringImage());

                Constant.imageMenuBaseData64.add(image);


                progressBar.setVisibility(View.GONE);
                byte[] decodeString = Base64.decode(image.getImageBase64(), Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                imageView.setImageBitmap(decode);
                item.setImageLoaded(1);
//                item.setImageBitmap(decode);


            }

            @Override
            public void onError(String message) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                Log.i("debug = ", message);
            }
        });
    }

    public static void loadImage(Context context, String image, ImageView imageView, final ProgressBar progressBar) {
        Glide.with(context)
                .load(image)
                .error(R.drawable.noimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    public static void loadImage(Context context, String image, ImageView imageView) {
        Glide.with(context)
                .load(image)
                .bitmapTransform(new RoundedCornersTransformation(context, 15, 2))
                .error(R.drawable.visa_card)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }

    public static void loadImageCircleImageView(Context context, String image, CircleImageView imageView) {

        Glide.with(context)
                .load(image)
                .error(R.drawable.noimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }

    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static Spanned formatHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        }
        return Html.fromHtml(html);
    }

    public static String numberFormat(Double value){
        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
        if(value==0){
            return "0.00";
        }
        return formatter.format(value);
    }

    public static String phoneFormat(String value){
        return PhoneNumberUtils.formatNumber(value);
    }


    public static boolean DownloadImage(ResponseBody body,ImageView imgView) {

        File externalFilesDir = null;

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(Environment.getExternalStorageDirectory() + File.separator + "AndroidTutorialPoint.jpg");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                Log.d("DownloadImage", e.toString());
                return false;
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            int width, height;

            Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +
                    File.separator + "AndroidTutorialPoint.jpg");
            width = bMap.getWidth();
            height = bMap.getHeight();
            Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false);
            imgView.setImageBitmap(bMap2);


            return true;

        } catch (IOException e) {

            Log.d("DownloadImage", e.toString());
            return false;
        }
    }


}
