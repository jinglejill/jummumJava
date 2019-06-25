package com.JummumCo.Jummum.Views.ListItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.JummumCo.Jummum.Model.CreditUserData;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Views.BaseCustomViewGroup;
import com.JummumCo.Jummum.Views.RecycleViewHolder.IClickListener;
import com.android.jummum.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by likit on 14-Oct-16.
 */
public class CreditListItem extends BaseCustomViewGroup {


    @BindView(R.id.image_type)
    ImageView imageType;
    @BindView(R.id.txt_num_credit)
    TextView txtNumCredit;
    @BindView(R.id.btn_remove)
    TextView btnRemove;
    @BindView(R.id.list_item)
    LinearLayout listItem;
    @BindView(R.id.icon_check)
    TextView iconCheck;
    @BindView(R.id.layout_list)
    LinearLayout layoutList;
    private CreditUserData category;
    private int qty;
    private int qty_total;
    private int price;
    private int numPosition = 0;

    public CreditListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CreditListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CreditListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CreditListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_view_credit_item, this);
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

    public void setItem(CreditUserData category, int position) {
        this.category = category;
        this.numPosition = position;
        init();
    }

    public void init() {

        if (category.getId() != 999) {
            switch (category.getCreditType()) {
                case "visa":
                    imageType.setImageResource(R.drawable.visa_card);
                    break;
                case "mastercard":
                    imageType.setImageResource(R.drawable.master_card);
                    break;
                case "JCB":
                    imageType.setImageResource(R.drawable.jcb);
                    break;
            }

            txtNumCredit.setText("xxxx xxxx xxxx " + category.getNumCredit().substring(category.getNumCredit().length() - 4));

            if (Constant.creditCard) {
                btnRemove.setVisibility(VISIBLE);
                iconCheck.setVisibility(GONE);
            } else {
                btnRemove.setVisibility(GONE);
            }

        } else {

            txtNumCredit.setText(category.getNumCredit());


            btnRemove.setVisibility(GONE);
        }
    }


    public void setItemClickListener(final IClickListener<Integer> itemClickListener, final int position) {
        btnRemove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(position);
            }
        });
    }

    public void setItemClickListenerList(final IClickListener<Integer> itemClickListener, final int position) {
        listItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });
    }
}
