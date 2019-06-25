package com.JummumCo.Jummum.Activity;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.DialogUtil;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveReceiptActivity extends AppCompatActivity {

    CommonRepository commonRepository = new CommonRepository();


    List<List<PayResponseResultData>> menuListResultData;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.order_date)
    TextView orderDate;
    @BindView(R.id.note_content)
    LinearLayout noteContent;
    @BindView(R.id.txt_qty)
    TextView txtQty;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.show_total_amount)
    LinearLayout showTotalAmount;
    @BindView(R.id.special_price_discount_title)
    TextView specialPriceDiscountTitle;
    @BindView(R.id.special_price_discount)
    TextView specialPriceDiscount;
    @BindView(R.id.show_special_discount)
    LinearLayout showSpecialDiscount;
    @BindView(R.id.discount_program_title)
    TextView discountProgramTitle;
    @BindView(R.id.discount_program_value)
    TextView discountProgramValue;
    @BindView(R.id.show_discount_program)
    LinearLayout showDiscountProgram;
    @BindView(R.id.txt_code_promotion)
    TextView txtCodePromotion;
    @BindView(R.id.btn_pomotion)
    Button btnPomotion;
    @BindView(R.id.layout_promotion)
    LinearLayout layoutPromotion;
    @BindView(R.id.txt_code_promotion_code)
    EditText txtCodePromotionCode;
    @BindView(R.id.btn_pomotion_code)
    Button btnPomotionCode;
    @BindView(R.id.txt_label_promotion_code)
    TextView txtLabelPromotionCode;
    @BindView(R.id.layout_promotion_code)
    LinearLayout layoutPromotionCode;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.txt_name_discount)
    TextView txtNameDiscount;
    @BindView(R.id.txt_delete)
    TextView txtDelete;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.layout_discount)
    LinearLayout layoutDiscount;
    @BindView(R.id.txt_total_2_title)
    TextView txtTotal2Title;
    @BindView(R.id.txt_total_2)
    TextView txtTotal2;
    @BindView(R.id.show_after_discount)
    LinearLayout showAfterDiscount;
    @BindView(R.id.txt_service_charge_lb)
    TextView txtServiceChargeLb;
    @BindView(R.id.txt_service_charge)
    TextView txtServiceCharge;
    @BindView(R.id.show_service_charge)
    LinearLayout showServiceCharge;
    @BindView(R.id.txt_vat_lb)
    TextView txtVatLb;
    @BindView(R.id.txt_vat)
    TextView txtVat;
    @BindView(R.id.show_vat)
    LinearLayout showVat;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.show_net_total)
    LinearLayout showNetTotal;
    @BindView(R.id.lucky_draw_title)
    TextView luckyDrawTitle;
    @BindView(R.id.show_lucky_draw_count)
    LinearLayout showLuckyDrawCount;
    @BindView(R.id.before_vat)
    TextView beforeVat;
    @BindView(R.id.show_before_vat)
    LinearLayout showBeforeVat;
    @BindView(R.id.layout_include_summary)
    LinearLayout layoutIncludeSummary;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private double balance, discount, sumBalance, serviceC, vat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_receipt);
        ButterKnife.bind(this);

        final DialogUtil dialogUtil = new DialogUtil(this);
        dialogUtil.showProgressDialog(getString(R.string.loading));

        menuListResultData = Parcels.unwrap(getIntent().getParcelableExtra("Orders"));


        commonRepository.getImage(menuListResultData.get(3).get(0).getJummumLogo(), "5", "0", new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response) {

                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                imageView.setImageBitmap(decode);


                DecimalFormat formatter = new DecimalFormat("#,###,###0.00");

                List<OrderSummary> orderLists = Parcels.unwrap(getIntent().getParcelableExtra("OrderList"));
                SummaryResponseResultData summayData = Parcels.unwrap(getIntent().getParcelableExtra("Summary"));

//                int balance = summayData.get(0).getTotalAmount();
//                int qty = getIntent().getIntExtra("Qty", 0);
//                String name = getIntent().getStringExtra("Name");
//
//                orderNo.setText(menuListResultData.get(3).get(0).getReceiptNoID());
//
//                String strDate = menuListResultData.get(3).get(0).getReceiptDate();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yy HH:mm");
//                try {
//                    Date date = dateFormat.parse(strDate);
//                    orderDate.setText(dateFormat2.format(date));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                txtQty.setText(String.valueOf(qty));
//                tvTitle.setText("ร้าน " + name);
//                txtTotal.setText(formatter.format(balance));
//                txtTotal2.setText(formatter.format(Double.parseDouble(menuListResultData.get(2).get(0).getAfterDiscount())));
//                txtServiceChargeLb.setText("Service charge " + summayData.get(0).getServiceChargePercent() + "%");
//                if (summayData.get(0).getServiceChargeValue() > 0) {
//                    txtServiceCharge.setText(formatter.format(summayData.get(0).getServiceChargeValue()));
//                } else {
//                    txtServiceCharge.setText("0.00");
//                }
//
//                txtVatLb.setText("VAT " + summayData.get(0).getVatPercent() + "%");
//
//                if (summayData.get(0).getVatValue() > 0) {
//                    txtVat.setText(formatter.format(summayData.get(0).getVatValue()));
//                } else {
//                    txtVat.setText("0.00");
//                }
//                txtBalance.setText(formatter.format(summayData.get(0).getNetTotal()));
//
//                if (getIntent().getParcelableExtra("HotDeal") != null) {
//
//                    HotDealData hotDealData = Parcels.unwrap(getIntent().getParcelableExtra("HotDeal"));
//                    txtNameDiscount.setText(hotDealData.getHeader());
//                    txtDiscount.setText("-" + summayData.get(0).getDiscountValue());
//                    layoutDiscount.setVisibility(View.VISIBLE);
//                } else {
//                    layoutDiscount.setVisibility(View.GONE);
//                }


                txtQty.setText(summayData.getNoOfItem());
                txtTotal.setText(Util.numberFormat(Double.parseDouble(String.valueOf(summayData.getTotalAmount()))));
                specialPriceDiscountTitle.setText(summayData.getSpecialPriceDiscountTitle());
                specialPriceDiscount.setText(Util.numberFormat(Double.parseDouble(summayData.getSpecialPriceDiscount())));
                discountProgramTitle.setText(summayData.getDiscountProgramTitle());
                discountProgramValue.setText(summayData.getDiscountProgramValue());


                txtTotal2Title.setText(summayData.getAfterDiscountTitle());
                txtTotal2.setText(Util.numberFormat(summayData.getAfterDiscount()));

                txtServiceChargeLb.setText("Service charge " + summayData.getServiceChargePercent() + "%");
                txtServiceCharge.setText(Util.numberFormat(summayData.getServiceChargeValue()));
                txtVatLb.setText("VAT " + summayData.getPercentVat() + "%");
                txtVat.setText(Util.numberFormat(summayData.getVatValue()));

                vat = summayData.getVatValue();
                serviceC = summayData.getServiceChargeValue();

                txtBalance.setText(Util.numberFormat(summayData.getNetTotal()));
                luckyDrawTitle.setText(summayData.getLuckyDrawTitle());

                beforeVat.setText(Util.numberFormat(summayData.getBeforeVat()));

                txtDiscount.setText(summayData.getDiscountPromoCodeValue());
                txtNameDiscount.setText(summayData.getDiscountPromoCodeTitle());

                if (summayData.getShowTotalAmount().equals("1")) {
                    showTotalAmount.setVisibility(View.VISIBLE);
                } else {
                    showTotalAmount.setVisibility(View.GONE);
                }

                if (summayData.getShowSpecialPriceDiscount().equals("1")) {
                    showSpecialDiscount.setVisibility(View.VISIBLE);
                } else {
                    showSpecialDiscount.setVisibility(View.GONE);
                }

                if (summayData.getShowDiscountProgram().equals("1")) {
                    showDiscountProgram.setVisibility(View.VISIBLE);
                } else {
                    showDiscountProgram.setVisibility(View.GONE);
                }

                if (summayData.getShowAfterDiscount().equals("1")) {
                    showAfterDiscount.setVisibility(View.VISIBLE);
                } else {
                    showAfterDiscount.setVisibility(View.GONE);
                }

                if (summayData.getShowServiceCharge().equals("1")) {
                    showServiceCharge.setVisibility(View.VISIBLE);
                } else {
                    showServiceCharge.setVisibility(View.GONE);
                }

                if (summayData.getShowVat().equals("1")) {
                    showVat.setVisibility(View.VISIBLE);
                } else {
                    showVat.setVisibility(View.GONE);
                }

                if (summayData.getShowNetTotal().equals("1")) {
                    showNetTotal.setVisibility(View.VISIBLE);
                } else {
                    showNetTotal.setVisibility(View.GONE);
                }

                if (summayData.getShowLuckyDrawCount().equals("1")) {
                    showLuckyDrawCount.setVisibility(View.VISIBLE);
                } else {
                    showLuckyDrawCount.setVisibility(View.GONE);
                }

                if (summayData.getShowBeforeVat().equals("1")) {
                    showBeforeVat.setVisibility(View.VISIBLE);
                } else {
                    showBeforeVat.setVisibility(View.GONE);
                }

                if (summayData.getShowVoucherListButton().equals("1")){
                    layoutPromotion.setVisibility(View.GONE);
                }else{
                    layoutPromotion.setVisibility(View.GONE);
                }

                if (summayData.getApplyVoucherCode().equals("0")){
                    layoutPromotionCode.setVisibility(View.GONE);
                }else {
                    layoutPromotionCode.setVisibility(View.GONE);
                    layoutDiscount.setVisibility(View.VISIBLE);
                    txtDelete.setVisibility(View.GONE);
                    txtNameDiscount.setText(summayData.getDiscountPromoCodeTitle());
                    txtDiscount.setText(summayData.getDiscountPromoCodeValue());
                }





                for (OrderSummary summary : orderLists) {
                    View child = getLayoutInflater().inflate(R.layout.list_view_order_payment_item, null);

                    TextView txtNum = child.findViewById(R.id.txt_num);
                    TextView txtName = child.findViewById(R.id.txt_name);
                    TextView txtNameNoteAddDisp = child.findViewById(R.id.txt_name_note_add_disp);
                    TextView txtNameNoteRemoveDisp = child.findViewById(R.id.txt_name_note_remove_disp);
                    TextView txtPrice = child.findViewById(R.id.txt_price);
                    TextView txtNameTake = child.findViewById(R.id.txt_name_take);
                    TextView txtNameNoteAdd = child.findViewById(R.id.txt_name_note_add);
                    TextView txtNameNoteRemove = child.findViewById(R.id.txt_name_note_remove);
                    LinearLayout layoutAdd = child.findViewById(R.id.layout_add);
                    LinearLayout layoutRemove = child.findViewById(R.id.layout_remove);

                    txtNum.setText(String.valueOf(summary.getQty()));
                    txtName.setText(summary.getProductName());

                    txtNameNoteAddDisp.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    txtNameNoteRemoveDisp.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    txtNameTake.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

                    String[] notes = summary.getNoteName().split(",");
                    List<String> noteAdd = new ArrayList<>();
                    List<String> noteRemove = new ArrayList<>();

                    boolean take = false;

                    for (String note : notes) {
                        if (note.startsWith("1|")) {
                            noteAdd.add(note.replace("1|", ""));
                        } else if (note.startsWith("-1|")) {
                            noteRemove.add(note.replace("-1|", ""));
                        }
                        if (note.equals("Take")) {
                            take = true;
                        }
                    }

                    txtNameTake.setVisibility(take ? View.VISIBLE : View.GONE);

                    if (noteAdd.size() > 0) {
                        layoutAdd.setVisibility(View.VISIBLE);
                        txtNameNoteAdd.setText(TextUtils.join(", ", noteAdd));
                    } else {
                        layoutAdd.setVisibility(View.GONE);
                    }
                    if (noteRemove.size() > 0) {
                        layoutRemove.setVisibility(View.VISIBLE);
                        txtNameNoteRemove.setText(TextUtils.join(", ", noteRemove));
                    } else {
                        layoutRemove.setVisibility(View.GONE);
                    }
                    txtPrice.setText(formatter.format(Integer.parseInt(summary.getPrice())));

                    noteContent.addView(child);
                }

                mainContent.setVisibility(View.VISIBLE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogUtil.hideProgressDialog();
                        saveImage();
                    }
                }, 1000);

            }

            @Override
            public void onError(String message) {
                toast(message);
                onBackPressed();
            }
        });

    }

    private void saveImage() {

        Bitmap bitmap = getBitmapFromView(mainContent);
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JUMMUM";
        File dir = new File(file_path);
        if (!dir.exists()) {
            boolean maeked = dir.mkdirs();
        }

        File file = new File(dir, "ReceiptNo" + menuListResultData.get(3).get(0).getReceiptNoID() + ".png");
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
            Uri result = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        toast("Save success.");

        setResult(RESULT_OK);
        finish();
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


}
