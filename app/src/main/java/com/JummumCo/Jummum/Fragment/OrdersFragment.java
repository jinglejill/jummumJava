package com.JummumCo.Jummum.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.JummumCo.Jummum.Adapter.OrderRecyclerViewAdapter;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteItemClick;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class OrdersFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;


    private OrderRecyclerViewAdapter adapter;
    private OrdersFragment.OnFragmentInteractionListener listener;
    private LinearLayoutManager linearLayoutManager;
    private String menutypeId;
    private List<List<BranchAndCustomerTableResponseResultData>> tableResponseResultData;

    private NoteItemClick selectedItem;

    public OrdersFragment() {
        super();
    }

    public static OrdersFragment newInstance(List<List<BranchAndCustomerTableResponseResultData>> table) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putParcelable("Table", Parcels.wrap(table));
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
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }


    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {

    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        tableResponseResultData = Parcels.unwrap(getArguments().getParcelable("Table"));
        linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderRecyclerViewAdapter( Constant.menuListResultDataGlobal, tableResponseResultData);
        adapter.setOnClickListener(getNoteOnClickListener());
        adapter.setPlusOnClickListener(getPlusOnClickListener());
        adapter.setMinusOnClickListener(getMinusOnClickListener());
        adapter.setNoteOnClickListener(getNoteOnClickListener());
        adapter.setNoteItemClickListener(getNoteItemClickListener());
        adapter.setNoteItemTakeClickListener(getNoteItemTakeClickListener());
        adapter.setClearNoteItemTakeClickListener(getClearItemTakeClickListener());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private IClickListener<NoteItemClick> getClearItemTakeClickListener() {
        return new IClickListener<NoteItemClick>() {
            @Override
            public void onClick(NoteItemClick item) {
                selectedItem = item;

                Constant.menuListResultDataGlobal.get(selectedItem.getRootPosition())
                        .getNoteList()
                        .get(selectedItem.getPosition())
                        .clear();

                List<NoteListResponseResultData> data =  Constant.menuListResultDataGlobal
                        .get(selectedItem.getRootPosition())
                        .getNoteList()
                        .get(selectedItem.getPosition());

                refreshNote(data);
            }
        };
    }

    @NonNull
    private IClickListener<NoteItemClick> getNoteItemTakeClickListener() {
        return new IClickListener<NoteItemClick>() {
            @Override
            public void onClick(NoteItemClick item) {
                selectedItem = item;
                OnFragmentInteractionListener fragmentListener = (OnFragmentInteractionListener) getActivity();

                String take;

                take =  Constant.menuListResultDataGlobal.get(selectedItem.getRootPosition())
                        .getTakeAway()
                        .get(selectedItem.getPosition())
                        .getTakeAway();

                boolean statusTake = false;

                if (take == null) {
                    Constant.menuListResultDataGlobal.get(selectedItem.getRootPosition())
                            .getTakeAway()
                            .get(selectedItem.getPosition())
                            .setTakeAway("1");
                    statusTake = true;

                } else {
                    if (take.equals("1")) {
                        Constant.menuListResultDataGlobal.get(selectedItem.getRootPosition())
                                .getTakeAway()
                                .get(selectedItem.getPosition())
                                .setTakeAway("0");
                        statusTake = false;

                    } else {
                        Constant.menuListResultDataGlobal.get(selectedItem.getRootPosition())
                                .getTakeAway()
                                .get(selectedItem.getPosition())
                                .setTakeAway("1");
                        statusTake = true;
                    }
                }


                adapter.refresh(selectedItem.getRootPosition());
                listener.onTakeStatus(statusTake);

            }
        };
    }

    private IClickListener<NoteItemClick> getNoteItemClickListener() {
        return new IClickListener<NoteItemClick>() {
            @Override
            public void onClick(NoteItemClick item) {

                selectedItem = item;
                OnFragmentInteractionListener fragmentListener = (OnFragmentInteractionListener) getActivity();
                List<NoteListResponseResultData> data =  Constant.menuListResultDataGlobal
                        .get(selectedItem.getRootPosition())
                        .getNoteList()
                        .get(selectedItem.getPosition());
                fragmentListener.onOpenNoteDialog(data);
            }
        };
    }

    public void refreshNote(List<NoteListResponseResultData> data) {
        adapter.refreshNote(selectedItem, data);
        int price = 0;
        for (NoteListResponseResultData d : data) {
            if (!d.getPrice().equals("0")) {
                price += Integer.parseInt(d.getPrice()) * Integer.parseInt(d.getQuantity()) ;
            }
        }

        listener.onUpdatePrice(price, data, selectedItem);
    }


    @NonNull
    private IClickListener<Integer> getNoteOnClickListener() {
        return new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                Constant.selectNoteMenuId =  Constant.menuListResultDataGlobal.get(item).getMenuID();
            }
        };
    }

    @NonNull
    private IClickListener<Integer> getMinusOnClickListener() {
        return new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {
                MenuListResultData menu2 =  Constant.menuListResultDataGlobal.get(item.intValue());
                if ( Constant.menuListResultDataGlobal.get(item.intValue()).getQty() <= 1) {
                    //orders.remove(item.intValue());
                    Constant.menuListResultDataGlobal.get(item.intValue()).setQty( Constant.menuListResultDataGlobal.get(item.intValue()).getQty() - 1);
                    adapter.removeItem(item.intValue());
                } else {
                    Constant.menuListResultDataGlobal.get(item.intValue()).setQty( Constant.menuListResultDataGlobal.get(item.intValue()).getQty() - 1);
                    int lastIndex =  Constant.menuListResultDataGlobal.get(item.intValue()).getNoteList().size() - 1;
                    Constant.menuListResultDataGlobal.get(item.intValue()).getNoteList().remove(lastIndex);
                    Constant.menuListResultDataGlobal.get(item.intValue()).getTakeAway().remove(lastIndex);
                    adapter.refresh(item.intValue());
                }
                final Toast toast = new Toast(getContext());
                ImageView view = new ImageView(getContext());
                view.setImageResource(R.drawable.removed);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 200);
                listener.onItemClick(menu2, 1);
            }
        };
    }

    @NonNull
    private IClickListener<Integer> getPlusOnClickListener() {
        return new IClickListener<Integer>() {
            @Override
            public void onClick(Integer item) {

                final Toast toast = new Toast(getContext());
                ImageView view = new ImageView(getContext());
                view.setImageResource(R.drawable.added);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 200);

                Constant.menuListResultDataGlobal.get(item).setQty( Constant.menuListResultDataGlobal.get(item).getQty() + 1);
                Constant.menuListResultDataGlobal.get(item).getNoteList().add(new ArrayList<NoteListResponseResultData>());
                Constant.menuListResultDataGlobal.get(item).getTakeAway().add(new NoteListResponseResultData());
                adapter.refresh(item);
                listener.onItemClick( Constant.menuListResultDataGlobal.get(item), 2);
            }
        };
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OrdersFragment.OnFragmentInteractionListener) {
            listener = (OrdersFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Save", "xxxxx");

    }


    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("Restore", "xx");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onItemClick(MenuListResultData menu, int type);

        void onOpenNoteDialog(List<NoteListResponseResultData> data);

        void onTakeStatus(boolean take);

        void onUpdatePrice(int price, List<NoteListResponseResultData> data, NoteItemClick noteItemClick);
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
