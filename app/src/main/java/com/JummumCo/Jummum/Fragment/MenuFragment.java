package com.JummumCo.Jummum.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.JummumCo.Jummum.Adapter.MenuListRecyclerMeViewAdapter;
import com.JummumCo.Jummum.Model.MenuListResultData;
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

interface Updateable {
     void update();
}

public class MenuFragment extends Fragment implements Updateable{

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;
    @BindView(R.id.main_content)
    ConstraintLayout mainContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MenuFragment.OnFragmentInteractionListener listener;

    private MenuListRecyclerMeViewAdapter adapter;
    private List<MenuListResultData> menuList;
    private List<MenuListResultData> menuListMenu;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private String menutypeId;

    public MenuFragment() {
        super();
    }

    public static MenuFragment newInstance(List<MenuListResultData> menuType,String position) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putParcelable("MenuType", Parcels.wrap(menuType));
        args.putString("Position",position);
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
        menuListMenu = new ArrayList<>();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        menuList = Parcels.unwrap(getArguments().getParcelable("MenuType"));
        menutypeId = getArguments().getString("Position");
        linearLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(),2);

        menuListMenu.clear();

        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getMenuTypeID().equals(menutypeId)) {
                menuListMenu.add(menuList.get(i));
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
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

    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void update() {
        adapter.notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onItemClick(MenuListResultData menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (menuListMenu.get(0).getMenuTypeID().equals("0")){
            recyclerView.setLayoutManager(gridLayoutManager);
        }else{
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        adapter = new MenuListRecyclerMeViewAdapter(menuListMenu);
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

                final Toast toast = new Toast(getContext());
                ImageView view = new ImageView(getContext());
                view.setImageResource(R.drawable.added);
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

                MenuListResultData menu = menuListMenu.get(item);
                menu.setQty(menu.getQty() + 1);

                for(MenuListResultData menuReplate : menuList){
                    if (menuReplate.getMenuID().equals(menu.getMenuID())){
                        if (!menuReplate.getMenuTypeID().equals(menu.getMenuTypeID())) {
                            menuReplate.setQty(menuReplate.getQty() + 1);
                        }
                    }
                }
                listener.onItemClick(menuListMenu.get(item));
                adapter.refresh(item.intValue());


            }
        };
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
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
