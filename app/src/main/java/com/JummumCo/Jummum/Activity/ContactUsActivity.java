package com.JummumCo.Jummum.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.jummum.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.JummumCo.Jummum.Activity.LuckyActivity.setWindowFlag;
import static com.JummumCo.Jummum.Util.Constant.BASE_URL;

public class ContactUsActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        webView.getSettings().setJavaScriptEnabled(true);
        //This the the enabling of the zoom controls
        webView.getSettings().setBuiltInZoomControls(true);

        //This will zoom out the WebView
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setInitialScale(100);

        webView.loadUrl(BASE_URL + "HtmlContactUs.php");
    }

    @OnClick(R.id.btn_back)
    public void onViewClickedBack() {
        finish();
    }
}
