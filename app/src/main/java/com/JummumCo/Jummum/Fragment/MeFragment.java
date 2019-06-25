package com.JummumCo.Jummum.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Activity.CommentActivity;
import com.JummumCo.Jummum.Activity.ContactUsActivity;
import com.JummumCo.Jummum.Activity.CreditCardActivity;
import com.JummumCo.Jummum.Activity.HistoryOrderActivity;
import com.JummumCo.Jummum.Activity.LoginActivity;
import com.JummumCo.Jummum.Activity.PresentMarketActivity;
import com.JummumCo.Jummum.Activity.PrivacyPolicyActivity;
import com.JummumCo.Jummum.Activity.ProfileActivity;
import com.JummumCo.Jummum.Activity.TermsOfServiceActivity;
import com.JummumCo.Jummum.CustomView.TextAwesome;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.BaseResponse;
import com.JummumCo.Jummum.Respository.MemberRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MeFragment extends BaseFragment {


    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.txt_title_header)
    TextView txtTitleHeader;
    @BindView(R.id.btn_back)
    TextView btnBack;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.layout_creditcard)
    LinearLayout layoutCreditcard;
    @BindView(R.id.layout_service)
    LinearLayout layoutService;
    @BindView(R.id.layout_privacy)
    LinearLayout layoutPrivacy;
    @BindView(R.id.layout_recommendshop)
    LinearLayout layoutRecommendshop;
    @BindView(R.id.layout_comment)
    LinearLayout layoutComment;
    @BindView(R.id.layout_contactus)
    LinearLayout layoutContactus;
    @BindView(R.id.layout_logout)
    LinearLayout layoutLogout;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.layout_history)
    LinearLayout layoutHistory;
    @BindView(R.id.btn_profile)
    RelativeLayout btnProfile;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private WrapperFragment wrapperFragment;
    private MemberRepository memberRepository;

    public MeFragment() {
        super();
    }

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        wrapperFragment = (WrapperFragment) getParentFragment();
        memberRepository = new MemberRepository();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        txtTitleHeader.setText(PreferenceManager.getInstance().getFullName());
        Util.loadImage2(getContext(), profileImage,progressBar,"Jummum_logo.png","0","5");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (dev.thaigpstracker.Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (dev.thaigpstracker.Fragment level's variables) State here
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.layout_contactus)
    public void onViewClickedContactus() {
        Intent intent = new Intent(getContext(), ContactUsActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.layout_service, R.id.layout_privacy})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.layout_service:
                intent = new Intent(getContext(), TermsOfServiceActivity.class);
                break;
            case R.id.layout_privacy:
                intent = new Intent(getContext(), PrivacyPolicyActivity.class);
                break;
        }

        startActivity(intent);
    }

    @OnClick(R.id.layout_history)
    public void onViewClickedHistory() {
        Intent intent = new Intent(getContext(), HistoryOrderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_logout)
    public void onViewClickedLogout() {

        memberRepository.getLogout(PreferenceManager.getInstance().getUserName(),
                "1", "-1",
                Util.getModifireDate(),
                PreferenceManager.getInstance().getUserName(),
                PreferenceManager.getInstance().getToken(),
                PreferenceManager.getInstance().getToken(),
                "log out in Me screen",
                "", new IHttpCallback<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        PreferenceManager.getInstance().setMemberId(null);
                        PreferenceManager.getInstance().setUsername(null);
                        PreferenceManager.getInstance().setToken(null);
                        LoginManager.getInstance().logOut();

                        //fb logout
                        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                                .Callback() {

                            @Override
                            public void onCompleted(GraphResponse graphResponse) {

                                LoginManager.getInstance().logOut();
//                                listener.onLoggedOutFromFacebook();
                            }
                        }).executeAsync();



                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(String message) {
                        message = "Cannot connect to server";
                        Util.showToast(mainContent, message);
                    }
                });

    }

    @OnClick(R.id.btn_profile)
    public void onViewClickedProfile() {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.layout_recommendshop, R.id.layout_comment})
    public void onViewClickedAction(View view) {
        switch (view.getId()) {
            case R.id.layout_recommendshop:
                Intent intent = new Intent(getContext(), PresentMarketActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_comment:
                Constant.commentMe = false;
                Intent intent2 = new Intent(getContext(), CommentActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @OnClick(R.id.layout_creditcard)
    public void onClickCreditCard() {
        Intent intent = new Intent(getContext(), CreditCardActivity.class);
        intent.putExtra("showCreditMe",true);
        intent.putExtra("header","บัตรเครดิต/เดบิต");
        startActivity(intent);
    }
}
