package com.JummumCo.Jummum.CustomView;

import org.joda.time.YearMonth;

import co.omise.android.ui.NumberRangeSpinnerAdapter;

public class ExpiryYearSpinnerAdapter extends NumberRangeSpinnerAdapter {
    public ExpiryYearSpinnerAdapter() {
        super(YearMonth.now().getYear(), YearMonth.now().getYear() + 12);
    }

    @Override
    protected String getItemDropDownLabel(int number) {
        return Integer.toString(number);
    }

    @Override
    protected String getItemLabel(int number) {
        return Integer.toString(number).substring(2, 4);
    }
}
