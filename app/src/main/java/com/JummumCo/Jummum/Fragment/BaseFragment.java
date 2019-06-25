package com.JummumCo.Jummum.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.android.jummum.R;
import com.JummumCo.Jummum.Util.DialogUtil;

/**
 * Created by Phitakphong on 5/6/2560.
 */

public class BaseFragment extends Fragment {

    private DialogUtil dialogUtil;
    private LayoutInflater inflater;
    private View rootView;
    private View progressLayout;
    private boolean fromBackPressed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogUtil = new DialogUtil(getActivity());
    }

    public void init(View rootView, LayoutInflater inflater) {
        this.rootView = rootView;
        this.inflater = inflater;
    }

    public boolean isLogedIn() {
        return PreferenceManager.getInstance().getUserName() != "";
    }

    public LayoutInflater getLayoutInflaters(){
        return this.inflater;
    }

    public void showProgress() {
        ViewGroup container = (ViewGroup) rootView.findViewById(R.id.fragment_container);
        if (container != null) {
            progressLayout = container.findViewById(R.id.progress_layout);
            if (progressLayout == null) {
                progressLayout = inflater.inflate(R.layout.layout_progress_bar, container);
            }
            progressLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        progressLayout = rootView.findViewById(R.id.progress_layout);
        progressLayout.setVisibility(View.GONE);
    }

    public void showProgressDialog(String message) {
        dialogUtil.showProgressDialog(message);
    }

    public void hideProgressDialog() {
        dialogUtil.hideProgressDialog();
    }

    public boolean isFromBackPressed() {
        return fromBackPressed;
    }

    public void setFromBackPressed(boolean fromBackPressed) {
        this.fromBackPressed = fromBackPressed;
    }

}
