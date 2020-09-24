package com.firstems.erp.ui.report;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.firstems.erp.R;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.common.chart.BarChartItem;
import com.firstems.erp.common.chart.ChartDataAdapter;
import com.firstems.erp.common.chart.ChartItem;
import com.firstems.erp.common.chart.LineChartItem;
import com.firstems.erp.common.chart.PieChartItem;
import com.firstems.erp.databinding.ReportFragmentBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;

    private BackToHomeCallback backToHomeCallback;
    private View view;
    private ReportFragmentBinding reportFragmentBinding;
    private TextView txtTitle;

    public ReportFragment(BackToHomeCallback backToHomeCallback) {
        this.backToHomeCallback = backToHomeCallback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        reportFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.report_fragment, container, false);
        view = reportFragmentBinding.getRoot();
        addControls();
        fakeDataChart();
        return view;
    }

    private void fakeDataChart() {
        ListView lv = reportFragmentBinding.listView1;

        ArrayList<ChartItem> list = new ArrayList<>();

        // 30 items
        for (int i = 0; i < 30; i++) {

            if(i % 3 == 0) {
                list.add(new LineChartItem(generateDataLine(i + 1), getContext()));
            } else if(i % 3 == 1) {
                list.add(new BarChartItem(generateDataBar(i + 1), getContext()));
            } else if(i % 3 == 2) {
                list.add(new PieChartItem(generateDataPie(), getContext()));
            }
        }

        ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
        lv.setAdapter(cda);
    }

    private void addControls() {
        txtTitle=reportFragmentBinding.include.findViewById(R.id.txtTitle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(values1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values2.add(new Entry(i, values1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(values2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);

        return new LineData(sets);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Pie data
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        return new PieData(d);
    }
}