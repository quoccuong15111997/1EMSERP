package com.firstems.erp.ui.chart;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstems.erp.R;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.ChartViewFragmentBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartViewFragment extends CommonFragment {

    private ChartViewViewModel mViewModel;
    private ChartViewFragmentBinding binding;
    private BarChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.chart_view_fragment, container, false);

        addEvents();
        addControls();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void addControls() {
        chart=binding.topBarChart;
        setUpTopBarChart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChartViewViewModel.class);
        // TODO: Use the ViewModel
    }
    private void setUpTopBarChart() {
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(tfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(5, true);
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.setFitBars(true);

        chart.animateY(700);

        chart.getLegend().setEnabled(true);
        BarDataSet set1;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            float multi = (100 + 1);
            float val = (float) (Math.random() * multi) + multi / 3;
            values.add(new BarEntry(i, val));
        }

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);
        }

        chart.invalidate();
    }
}