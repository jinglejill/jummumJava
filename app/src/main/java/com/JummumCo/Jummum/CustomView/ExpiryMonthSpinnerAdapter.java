package com.JummumCo.Jummum.CustomView;

import java.util.Locale;

import co.omise.android.ui.NumberRangeSpinnerAdapter;

public class ExpiryMonthSpinnerAdapter extends NumberRangeSpinnerAdapter {
    public ExpiryMonthSpinnerAdapter() {
        super(1, 12);
    }

    @Override
    protected String getItemDropDownLabel(int number) {
        return String.format(Locale.getDefault(), "%02d", number);
    }

    @Override
    protected String getItemLabel(int number) {
        return String.format(Locale.getDefault(), "%02d", number);
    }

}
