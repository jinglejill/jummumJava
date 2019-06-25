package com.JummumCo.Jummum.Views.ListItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.android.jummum.R;
import com.JummumCo.Jummum.Views.BaseCustomViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by likit on 14-Oct-16.
 */
public class MenuTypeListItem extends BaseCustomViewGroup {


    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_qty)
    TextView tvQty;
    @BindView(R.id.layout_qty_total)
    RelativeLayout layoutQtyTotal;
    @BindView(R.id.tv_totle)
    TextView tvTotle;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    private MenuListResultData category;
    //    private List<ProductLIstResultData> productLIstResultData;
    private int qty;
    private int qty_total;
    private int price;

    public MenuTypeListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public MenuTypeListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public MenuTypeListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public MenuTypeListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_view_menu_item, this);
        ButterKnife.bind(this);
    }

    private void initInstances() {

    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    public void setItem(MenuListResultData category) {
        this.category = category;
        init();
    }

    public void init() {
        tvTitle.setText(category.getTitleThai());
        tvQty.setText(qty+"");
//        Util.loadImage(binding.getRoot().getContext(), category.getServicefeeAmount(), binding.imgTitle, null);




    }

    public void setItemClickListener(final IClickListener<Integer> itemClickListener, final int position) {
        listItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });
    }

}
