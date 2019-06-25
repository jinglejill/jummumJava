package com.JummumCo.Jummum.Views.ListItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.OrderTaking2ResultData;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.BaseCustomViewGroup;
import com.android.jummum.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by likit on 14-Oct-16.
 */
public class OrderListmentListItem extends BaseCustomViewGroup {


    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    private OrderTaking2ResultData category;
    private int qty;
    private int qty_total;
    private int price;
    private int numPosition = 0;

    public OrderListmentListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public OrderListmentListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public OrderListmentListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public OrderListmentListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_view_order_payment_item, this);
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

    public void setItem(OrderTaking2ResultData category, int position) {
        this.category = category;
        this.numPosition = position;
        init();
    }

    public void init() {
        txtNum.setText(category.getQuantity());
        txtName.setText(category.getMenu().get(0).getTitleThai());

        txtPrice.setText(Util.numberFormat(Double.parseDouble(category.getSpecialPrice()) * Integer.parseInt(category.getQuantity())));
    }


}
