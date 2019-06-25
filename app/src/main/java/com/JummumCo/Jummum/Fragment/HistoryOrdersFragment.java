package com.JummumCo.Jummum.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.JummumCo.Jummum.Interface.IFragmentControlListener;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Adapter.OrderListRecyclerViewAdapter;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.android.jummum.R;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HistoryOrdersFragment extends BaseFragment {




    public HistoryOrdersFragment() {
        super();
    }

    public static BaseFragment newInstance() {
        HistoryOrdersFragment fragment = new HistoryOrdersFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_history_order, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {

    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {


    }




    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void onResume() {
        super.onResume();

    }


}
