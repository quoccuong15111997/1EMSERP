package com.firstems.erp.common.chart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/20/2020.
 */
public class ChartDataAdapter extends ArrayAdapter<ChartItem> {
    public ChartDataAdapter(Context context, List<ChartItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //noinspection ConstantConditions
        return getItem(position).getView(position, convertView, getContext());
    }

    @Override
    public int getItemViewType(int position) {
        // return the views type
        ChartItem ci = getItem(position);
        return ci != null ? ci.getItemType() : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3; // we have 3 different item-types
    }
}
