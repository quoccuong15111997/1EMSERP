package com.firstems.erp.common.chart;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.data.ChartData;

/**
 * Created by Nguyen Quoc Cuong on 7/20/2020.
 */
public abstract class ChartItem {
    static final int TYPE_BARCHART = 0;
    static final int TYPE_LINECHART = 1;
    static final int TYPE_PIECHART = 2;

    ChartData<?> mChartData;

    ChartItem(ChartData<?> cd) {
        this.mChartData = cd;
    }

    public abstract int getItemType();

    public abstract View getView(int position, View convertView, Context c);
}
