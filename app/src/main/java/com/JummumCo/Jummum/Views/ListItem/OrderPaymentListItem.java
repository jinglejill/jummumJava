package com.JummumCo.Jummum.Views.ListItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Util.Util;
import com.JummumCo.Jummum.Views.BaseCustomViewGroup;
import com.android.jummum.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by likit on 14-Oct-16.
 */
public class OrderPaymentListItem extends BaseCustomViewGroup {


    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.txt_name_take)
    TextView txtNameTake;
    @BindView(R.id.txt_name_note_add)
    TextView txtNameNoteAdd;
    @BindView(R.id.txt_name_note_add_disp)
    TextView txtNameNoteAddDisp;
    @BindView(R.id.layout_add)
    LinearLayout layoutAdd;
    @BindView(R.id.txt_name_note_remove_disp)
    TextView txtNameNoteRemoveDisp;
    @BindView(R.id.txt_name_note_remove)
    TextView txtNameNoteRemove;
    @BindView(R.id.layout_remove)
    LinearLayout layoutRemove;
    @BindView(R.id.bath)
    TextView bath;
    private OrderSummary item;
    private int qty;
    private int qty_total;
    private int price;
    private int numPosition = 0;

    public OrderPaymentListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public OrderPaymentListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public OrderPaymentListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public OrderPaymentListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    public void setItem(OrderSummary category, int position) {
        this.item = category;
        this.numPosition = position;
        init();
    }

    public void init() {
        //txtNum.setText(String.valueOf(this.numPosition+1));
        txtNum.setText(String.valueOf(item.getQty()));
        txtName.setText(item.getProductName());

        txtNameNoteAddDisp.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtNameNoteRemoveDisp.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtNameTake.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        String[] notes = item.getNoteName().split(",");
        List<String> noteAdd = new ArrayList<>();
        List<String> noteRemove = new ArrayList<>();

        boolean take = false;

        for (String note : notes) {
            if (note.startsWith("1|")) {
                noteAdd.add(note.replace("1|", ""));
            } else if (note.startsWith("-1|")) {
                noteRemove.add(note.replace("-1|", ""));
            }
            if (note.equals("Take")) {
                take = true;
            }

        }

        txtNameTake.setVisibility(take ? VISIBLE : GONE);

        if (noteAdd.size() > 0) {
            layoutAdd.setVisibility(VISIBLE);
            txtNameNoteAdd.setText(TextUtils.join(", ", noteAdd));
        } else {
            layoutAdd.setVisibility(GONE);
        }
        if (noteRemove.size() > 0) {
            layoutRemove.setVisibility(VISIBLE);
            txtNameNoteRemove.setText(TextUtils.join(", ", noteRemove));
        } else {
            layoutRemove.setVisibility(GONE);
        }
        if (item.getPrice() != null && item.getPrice().length() > 0) {
            txtPrice.setText(Util.numberFormat(Double.parseDouble(item.getPrice())));
        } else {
            txtPrice.setText("0.00");
        }

    }


}
