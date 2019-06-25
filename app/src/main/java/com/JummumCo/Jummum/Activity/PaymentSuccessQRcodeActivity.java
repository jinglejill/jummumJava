package com.JummumCo.Jummum.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Manager.http.ApiService;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.BuildConfig;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentSuccessQRcodeActivity extends BaseActivity {

    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.img_qr)
    ImageView imgQr;
    @BindView(R.id.btn_sheard)
    Button btnSheard;
    @BindView(R.id.btn_finish)
    TextView btnFinish;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.layout_image)
    LinearLayout layoutImage;

    private List<List<PayResponseResultData>> response;
    private String TAG = "SAVE_IMAGE";
    private String receiptID;
    private CommonRepository commonRepository;
    private Uri result;
    private boolean historyPayment = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success_qrcode);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {

        commonRepository = new CommonRepository();

        Intent intent = getIntent();
        receiptID = intent.getStringExtra("receiptID");

        if (receiptID != null) {
//            Util.showToast(mainContent, "payment success");
            commonRepository.getPaymentComplate(receiptID, new IHttpCallback<List<List<PayResponseResultData>>>() {
                @Override
                public void onSuccess(List<List<PayResponseResultData>> response) {


                    Constant.responsePay.get(4).get(0).setLuckyDrawCount(response.get(1).get(0).getLuckyDrawCount());
                    Intent intent = new Intent(PaymentSuccessQRcodeActivity.this
                            , PaySuccessActivity.class);
                    intent.putExtra("Orders", Parcels.wrap(Constant.responsePay));
                    intent.putExtra("OrderList", Parcels.wrap(Constant.orderListPay));
                    intent.putExtra("Summary", Parcels.wrap(Constant.summayDataPay));
                    intent.putExtra("Qty", Constant.qtyPay);
                    intent.putExtra("HotDeal", Parcels.wrap(Constant.hotDealPay));
                    intent.putExtra("Name", Constant.namePay);
                    intent.putExtra("TableQR", Parcels.wrap(Constant.tableResponseResultDataPay));
                    startActivity(intent);
                }

                @Override
                public void onError(String message) {

                }
            });
        }else {
            Parcelable parcelable = getIntent().getParcelableExtra("ImageQR");
            if (parcelable != null) {

                response = Parcels.unwrap(parcelable);
                historyPayment = intent.getBooleanExtra("historyPayment",false);

                showProgressDialog();

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL_QR);

                Retrofit retrofit = builder.build();

                ApiService apiService = retrofit.create(ApiService.class);

                if (historyPayment){

                    Call<ResponseBody> call = apiService.getGBPrimePay2(response.get(6).get(0).getgBPrimeQRToken(),
                            response.get(3).get(0).getNetTotal(),
                            response.get(3).get(0).getCustomerTableID(),
                            response.get(3).get(0).getReferenceNo(),
                            "F",
                            response.get(6).get(0).getBackgroundUrl(),
                            response.get(6).get(0).getResponseUrl(),
                            response.get(3).get(0).getReceiptID(),
                            response.get(3).get(0).getBranchID(),
                            PreferenceManager.getInstance().getToken(),
                            PreferenceManager.getInstance().getMemberId(),
                            response.get(3).get(0).getReceiptNoID());

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            boolean writtenToDisk = DownloadImage(response.body());
                            //Util.showToast(mainContent, "file download was a success? " + writtenToDisk);
                            if (writtenToDisk) {
                                if (isStoragePermissionGranted()) {
                                    goSave();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            hideProgressDialog();
                            Util.showToast(mainContent, t.getMessage());

                        }
                    });

                }else {

                    Call<ResponseBody> call = apiService.getGBPrimePay2(response.get(6).get(0).getgBPrimeQRToken(),
                            response.get(2).get(0).getNetTotal(),
                            response.get(3).get(0).getCustomerTableID(),
                            response.get(3).get(0).getReferenceNo(),
                            "F",
                            response.get(6).get(0).getBackgroundUrl(),
                            response.get(6).get(0).getResponseUrl(),
                            response.get(3).get(0).getReceiptID(),
                            response.get(3).get(0).getBranchID(),
                            PreferenceManager.getInstance().getToken(),
                            PreferenceManager.getInstance().getMemberId(),
                            response.get(3).get(0).getReceiptNoID());

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            boolean writtenToDisk = DownloadImage(response.body());
                            //Util.showToast(mainContent, "file download was a success? " + writtenToDisk);
                            if (writtenToDisk) {
                                if (isStoragePermissionGranted()) {
                                    goSave();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            hideProgressDialog();
                            Util.showToast(mainContent, t.getMessage());

                        }
                    });

                }


            }
        }

    }


    private boolean DownloadImage(ResponseBody body) {

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
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

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;
            int qrHeight = screenHeight - 600;

            int width, height;

            Bitmap bMap = BitmapFactory.decodeFile(getExternalFilesDir(null) +
                    File.separator + "AndroidTutorialPoint.jpg");
            width = bMap.getWidth();
            height = bMap.getHeight();

            width = width*qrHeight/height;
            height = qrHeight;
            Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false);
            imgQr.setImageBitmap(bMap2);

            hideProgressDialog();

            return true;

        } catch (IOException e) {
            hideProgressDialog();
            Log.d("DownloadImage", e.toString());
            return false;
        }
    }

    @OnClick({R.id.btn_sheard, R.id.btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sheard:

//                if (isStoragePermissionGranted()){
//                    goSave();
//                }

                final Intent intentShared = new Intent(android.content.Intent.ACTION_SEND);
                intentShared.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentShared.putExtra(Intent.EXTRA_STREAM, result);
                intentShared.setType("image/*");
                startActivity(Intent.createChooser(intentShared, "Share image via"));



                break;
            case R.id.btn_finish:

                Intent intent = new Intent(PaymentSuccessQRcodeActivity.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("HotDeal", true);
                startActivity(intent);

                break;
        }
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
//            goSave();
//
//        }
//    }


    private void goSave() {

        Bitmap bitmap = getBitmapFromView(layoutImage);
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JUMMUM";
        File dir = new File(file_path);
        if (!dir.exists()) {
            boolean maeked = dir.mkdirs();
        }

        File file = new File(dir, "ReceiptNo" + response.get(3).get(0).getReceiptNoID() + ".png");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fOut);
            fOut.flush();
            fOut.close();
            ContentValues image = new ContentValues();
            image.put(MediaStore.Images.Media.TITLE, "NST");
            image.put(MediaStore.Images.Media.DISPLAY_NAME, file_path.substring(file_path.lastIndexOf('/') + 1));
            image.put(MediaStore.Images.Media.DESCRIPTION, "App Image");
            image.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
            image.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            image.put(MediaStore.Images.Media.ORIENTATION, 0);
            File parent = file.getParentFile();
            image.put(MediaStore.Images.ImageColumns.BUCKET_ID, parent.toString()
                    .toLowerCase().hashCode());
            image.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, parent.getName()
                    .toLowerCase());
            image.put(MediaStore.Images.Media.SIZE, file.length());
            image.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
            result = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);

            Util.showToast(mainContent,"QR Code ได้ถูกบันทึกลงในเครื่องเรียบร้อยแล้ว");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        toast("Save success.");

//        setResult(RESULT_OK);
//        finish();
    }


    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public Bitmap getBitmapFromView(View v) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        v.draw(canvas);
        return returnedBitmap;

    }


//    private Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable = view.getBackground();
//        if (bgDrawable != null) {
//            //has background drawable, then draw it on the canvas
//            bgDrawable.draw(canvas);
//        } else {
//            //does not have background drawable, then draw white background on the canvas
//            canvas.drawColor(Color.WHITE);
//        }
//        view.draw(canvas);
//        return returnedBitmap;
//    }

}
