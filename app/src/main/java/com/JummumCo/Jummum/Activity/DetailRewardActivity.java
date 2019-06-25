package com.JummumCo.Jummum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;

public class DetailRewardActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.image_view_type)
    ImageView imageViewType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.txt_point)
    TextView txtPoint;
    @BindView(R.id.more)
    TextAwesome more;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.cart)
    TextView cart;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_desc_law)
    TextView tvDescLaw;

    private RewardListResultData reward;
    private BottomSheetDialog bottomSheetDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reward);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (getIntent().getParcelableExtra("Reward") != null) {
            reward = Parcels.unwrap(getIntent().getParcelableExtra("Reward"));
        }

        setView();
        getBottomDialog();
    }

    private void getBottomDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_choose_reward, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());

        TextView submit = (TextView) bottomSheetView.findViewById(R.id.submit);
        TextView cancleDialog = (TextView) bottomSheetView.findViewById(R.id.menu_bottom_sheet_cancle);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CommonRepository().getRedeenReward(PreferenceManager.getInstance().getMemberId(),
                        reward.getRewardRedemptionID(),
                        PreferenceManager.getInstance().getUserName(),
                        Util.getModifireDate(),
                        new IHttpCallback<List<List<RewardListResultData>>>() {
                            @Override
                            public void onSuccess(List<List<RewardListResultData>> response) {
                                Intent intent = new Intent(DetailRewardActivity.this
                                        , CodeRewardActivity.class);
                                intent.putExtra("RewardCode", Parcels.wrap(response.get(0).get(0)));
                                intent.putExtra("Reward", Parcels.wrap(reward));
                                startActivity(intent);
                            }

                            @Override
                            public void onError(String message) {
                                Util.showAlert(DetailRewardActivity.this,
                                        message,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                            }
                        });
//                Intent intent = new Intent(DetailRewardActivity.this
//                        , ScanQrChooseTableActivity.class);
//                startActivityForResult(intent, Constant.ADD_QR_ORDER_REQUEST_CODE);
//                bottomSheetDialog.hide();

            }
        });


        cancleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.hide();
            }
        });
    }

    private void setView() {
        tvTitle.setText(reward.getHeader());
        tvDesc.setText(reward.getSubTitle());


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(reward.getPoint()));
        txtPoint.setText(yourFormattedString);


        tvDescLaw.setText(reward.getTermsConditions());

        new CommonRepository().getImage(reward.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
            @Override
            public void onSuccess(List<ImageResultData> response) {

                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                imageViewType.setImageBitmap(decode);
            }

            @Override
            public void onError(String message) {
                Log.i("debug = ", message);
            }
        });
    }

    @OnClick(R.id.layout_bottom)
    public void onClickReward() {
        bottomSheetDialog.show();
    }


    @OnClick(R.id.btn_back)
    public void onClickBack() {
        finish();
    }
}
