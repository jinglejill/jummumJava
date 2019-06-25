package com.JummumCo.Jummum.Activity;

import android.Manifest;
import android.content.ContentValues;
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
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.SaveOrderInsertData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SaveOrderInsertActivity extends BaseActivity {


    @BindView(R.id.btn_home)
    RelativeLayout btnHome;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.name_market)
    TextView nameMarket;
    @BindView(R.id.lb_date)
    TextView lbDate;
    @BindView(R.id.layout_table)
    LinearLayout layoutTable;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.img_qr)
    ImageView imgQr;
    @BindView(R.id.layout_image)
    LinearLayout layoutImage;
    @BindView(R.id.title_header)
    TextView titleHeader;

    private SaveOrderInsertData saveOrder;
    private static final String TAG = "TAG : ";
    private Uri result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_order_insert);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        nameMarket.setText(getIntent().getStringExtra("NameMarket"));
        saveOrder = Parcels.unwrap(getIntent().getParcelableExtra("SaveOrderInsertData"));

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yy HH:mm");
        String formattedDate = df.format(c.getTime());

        lbDate.setText(formattedDate);

        showProgressDialog();

        new CommonRepository().getSaveOrderInsertData(saveOrder, new IHttpCallback<Response<ResponseBody>>() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                boolean writtenToDisk = DownloadImage(response.body());
                if (writtenToDisk) {
                    if (isStoragePermissionGranted()) {
                        goSave();
                    }
                }
            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                Util.showToast(mainContent, message);
            }
        });

        /*
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL);

        Retrofit retrofit = builder.build();

        ApiService apiService = retrofit.create(ApiService.class);


        Call<ResponseBody> call = apiService.getSaveOrderInsertData(saveOrder);

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

        */

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

            int width, height;

            Bitmap bMap = BitmapFactory.decodeFile(getExternalFilesDir(null) +
                    File.separator + "AndroidTutorialPoint.jpg");
            width = bMap.getWidth();
            height = bMap.getHeight();
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

    private void goSave() {

        Bitmap bitmap = getBitmapFromView(layoutImage);
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JUMMUM";
        File dir = new File(file_path);
        if (!dir.exists()) {
            boolean maeked = dir.mkdirs();
        }

        File file = new File(dir, "ReceiptNo" + saveOrder.getModifiedDate() + ".png");
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

            Util.showToast(mainContent, "QR Code ได้ถูกบันทึกลงในเครื่องเรียบร้อยแล้ว");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        toast("Save success.");

//        setResult(RESULT_OK);
//        finish();
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

    @OnClick(R.id.btn_home)
    public void onViewClickedBack() {
        finish();
    }

}
