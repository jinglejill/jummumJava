package com.JummumCo.Jummum.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.JummumCo.Jummum.Activity.DetialHotDealActivity;
import com.JummumCo.Jummum.Adapter.VoucherAdaptor;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Interface.RecyclerViewListener;
import com.JummumCo.Jummum.Manager.PreferenceManager;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HotDealFragment extends Fragment {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    VoucherAdaptor adaptor;

    Unbinder unbinder;


    public HotDealFragment() {
        super();
    }

    CommonRepository commonRepository = new CommonRepository();
    List<HotDealData> hotDealData;
    List<HotDealData> _hotDealData;

    int page = 1;
    int perPage = 3000;

    String branchID;
    String memberID;

    public static HotDealFragment newInstance() {
        HotDealFragment fragment = new HotDealFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_hot_deal, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init dev.thaigpstracker.Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        branchID = "";
        memberID = PreferenceManager.getInstance().getMemberId();

        hotDealData = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        adaptor = new VoucherAdaptor(hotDealData, recyclerView, perPage, R.layout.list_view_hotdeal_item);
        recyclerView.setAdapter(adaptor);
        adaptor.setListener(new RecyclerViewListener<HotDealData>() {
            @Override
            public void onClick(HotDealData item) {

                Intent intent = new Intent(getContext(), DetialHotDealActivity.class);
                intent.putExtra("HotdealData", Parcels.wrap(item));
                startActivity(intent);

            }

            @Override
            public void onLoadMore() {
//                page += 1;
//                loadData();
            }
        });

        loadData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hotDealData.clear();
                for (HotDealData data : _hotDealData) {
//                    if (data.getHeader().toUpperCase().contains(charSequence.toString().toUpperCase())
//                            || data.getSubTitle().toUpperCase().contains(charSequence.toString().toUpperCase()))
                    if((Pattern.compile(Pattern.quote((String) charSequence.toString()), Pattern.CASE_INSENSITIVE).matcher(data.getHeader()).find()) ||
                            (Pattern.compile(Pattern.quote((String) charSequence.toString()), Pattern.CASE_INSENSITIVE).matcher(data.getSubTitle()).find()) ||
                            (Pattern.compile(Pattern.quote((String) charSequence.toString()), Pattern.CASE_INSENSITIVE).matcher(data.getTermsConditions()).find())
                            )
                    {
                        hotDealData.add(data);
                    }
                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    private void loadData() {
        hotDealData.add(null);
        adaptor.notifyItemInserted(hotDealData.size() - 1);
        commonRepository.getHotdeals(page, perPage, memberID, "", new IHttpCallback<List<HotDealData>>() {
            @Override
            public void onSuccess(List<HotDealData> response) {
                recyclerView.setVisibility(View.VISIBLE);
                _hotDealData = new ArrayList<>(response);
                if (response.size() > 0) {
                    hotDealData.remove(hotDealData.size() - 1);
                    adaptor.notifyItemRemoved(hotDealData.size());
                    for (HotDealData h : response) {
                        hotDealData.add(h);
                        adaptor.notifyItemInserted(hotDealData.size());
                    }
                }
                adaptor.setLoaded();
            }
            @Override
            public void onError(String message) {
                message = "Cannot connect to server";
                Util.showToast(mainContent, message);
                adaptor.setLoaded();
                recyclerView.setVisibility(View.GONE);
            }
        });
    }
}
