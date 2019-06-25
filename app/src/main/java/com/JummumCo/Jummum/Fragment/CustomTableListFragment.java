package com.JummumCo.Jummum.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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

import com.JummumCo.Jummum.Activity.MenuActivity;
import com.JummumCo.Jummum.Adapter.CustomTableListRecyclerViewAdapter;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CustomTableListFragment extends Fragment {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private CustomTableListRecyclerViewAdapter adapter;
    private List<BranchAndCustomerTableResponseResultData> tableList;
    private List<BranchAndCustomerTableResponseResultData> tableListCustom;
    private List<List<BranchAndCustomerTableResponseResultData>> tableListCustomNoadd;
    private List<BranchAndCustomerTableResponseResultData> market;
    private LinearLayoutManager linearLayoutManager;
    private String tableZone;

    public CustomTableListFragment() {
        super();
    }

    public static CustomTableListFragment newInstance(List<BranchAndCustomerTableResponseResultData> menuType,
                                                      String position,
                                                      List<BranchAndCustomerTableResponseResultData> market) {
        CustomTableListFragment fragment = new CustomTableListFragment();
        Bundle args = new Bundle();
        args.putParcelable("TableType", Parcels.wrap(menuType));
        args.putString("Position", position);
        args.putParcelable("Market", Parcels.wrap(market));
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
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        tableListCustom = new ArrayList<>();
        tableListCustomNoadd = new ArrayList<List<BranchAndCustomerTableResponseResultData>>();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        etSearch.setHint("เลือกโต๊ะ");
        tableList = Parcels.unwrap(getArguments().getParcelable("TableType"));
        tableZone = getArguments().getString("Position");
        market = Parcels.unwrap(getArguments().getParcelable("Market"));
        linearLayoutManager = new LinearLayoutManager(getContext());

        tableListCustom.clear();

        for (int i = 0; i < tableList.size(); i++) {
            if (tableList.get(i).getZone().equals(tableZone)) {
                for (int x = 0; x < tableList.get(i).getCustomerTable().size(); x++) {
                    tableListCustom.add(tableList.get(i).getCustomerTable().get(x));
                }
            }
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onItemClick(BranchAndCustomerTableResponseResultData menu);
    }

    @Override
    public void onResume() {
        super.onResume();

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomTableListRecyclerViewAdapter(tableListCustom);
        adapter.setOnClickListener(getItemClickListener());
        etSearch.addTextChangedListener(textWatcher);
        adapter.getFilter().filter(etSearch.getText());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private IClickListener<Integer> getItemClickListener() {
        return new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {

                if (Constant.reOrder){
                    List<BranchAndCustomerTableResponseResultData> table;
                    table = new ArrayList<>();
                    table.add(tableListCustom.get(item));

                    tableListCustomNoadd.add(market);
                    tableListCustomNoadd.add(table);

                    Intent intent = getActivity().getIntent();
                    intent.putExtra("BranchID", tableListCustom.get(item).getBranchID());
                    intent.putExtra("TableQR", Parcels.wrap(tableListCustomNoadd));
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }else {
                    List<BranchAndCustomerTableResponseResultData> table;
                    table = new ArrayList<>();
                    table.add(tableListCustom.get(item));

                    tableListCustomNoadd.add(market);
                    tableListCustomNoadd.add(table);

                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    intent.putExtra("BranchID", tableListCustom.get(item).getBranchID());
                    intent.putExtra("TableQR", Parcels.wrap(tableListCustomNoadd));
                    startActivity(intent);

                    if (Constant.tableQrCode != null) {
                        if (!tableListCustom.get(item).getBranchID().equals(Constant.tableQrCode.get(0).get(0).getBranchID())) {
                            Constant.menuListResultDataGlobal = new ArrayList<>();
                        }
                    } else {
                        Constant.tableQrCode = tableListCustomNoadd;
                    }
                }
            }
        };
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                adapter.getFilter().filter(s.toString());
            }
        }
    };


}
