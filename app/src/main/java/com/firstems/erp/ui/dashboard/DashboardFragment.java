package com.firstems.erp.ui.dashboard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstems.erp.R;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.LayoutDashboardBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardFragment extends CommonFragment {

    private DashboardViewModel mViewModel;
    private BarChart chart;
    private PieChart chartPie;
    //private DashboardFragmentBinding binding;
    private LayoutDashboardBinding binding;

    String[] parties = new String[] {
            "Hồ Chí Minh", "Hà Nội", "Đà Nẵng", "Tiền Giang", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.layout_dashboard, container, false);

        addControls();
        addEvents();
        setUpTopBarChart();
        setUpPieChart();
        return binding.getRoot();
    }

    private void setUpPieChart() {
        /*chartPie.setUsePercentValues(true);
        chartPie.getDescription().setEnabled(false);
        chartPie.setExtraOffsets(5, 10, 5, 5);

        chartPie.setDragDecelerationFrictionCoef(0.95f);

        setData(4,85);
        chartPie.setCenterTextTypeface(tfLight);
        chartPie.setCenterText(generateCenterSpannableText());

        chartPie.setDrawHoleEnabled(true);
        chartPie.setHoleColor(Color.WHITE);

        chartPie.setTransparentCircleColor(Color.WHITE);
        chartPie.setTransparentCircleAlpha(110);

        chartPie.setHoleRadius(58f);
        chartPie.setTransparentCircleRadius(61f);

        chartPie.setDrawCenterText(true);

        chartPie.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartPie.setRotationEnabled(true);
        chartPie.setHighlightPerTapEnabled(true);

        chartPie.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chartPie.setEntryLabelColor(Color.WHITE);
        chartPie.setEntryLabelTypeface(tfRegular);
        chartPie.setEntryLabelTextSize(12f);*/
        setData(4,85);
        chartPie.setUsePercentValues(true);
        chartPie.getDescription().setEnabled(false);
        chartPie.setExtraOffsets(5, 10, 5, 5);

        chartPie.setDragDecelerationFrictionCoef(0.95f);

        chartPie.setCenterTextTypeface(tfLight);
        chartPie.setCenterText(generateCenterSpannableText());

        chartPie.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        chartPie.setDrawHoleEnabled(true);
        chartPie.setHoleColor(Color.WHITE);

        chartPie.setTransparentCircleColor(Color.WHITE);
        chartPie.setTransparentCircleAlpha(110);

        chartPie.setHoleRadius(58f);
        chartPie.setTransparentCircleRadius(61f);

        chartPie.setDrawCenterText(true);

        chartPie.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartPie.setRotationEnabled(true);
        chartPie.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true)
        chartPie.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        chartPie.setEntryLabelColor(Color.WHITE);
        chartPie.setEntryLabelTypeface(tfRegular);
        chartPie.setEntryLabelTextSize(12f);

        Legend l = chartPie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void setUpTopBarChart() {
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        chart.animateY(1500);

        chart.getLegend().setEnabled(false);
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

    private void addEvents() {
    }

    private void addControls() {
        chart=binding.topBarChart;
        chartPie=binding.chartPie;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
    }
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("FirstEMS.ERP\ndeveloped by Nguyễn Quốc Cường");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 12, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 12, s.length() - 13, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 12, s.length() - 13, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 12, s.length() - 13, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 12, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 12, s.length(), 0);
        return s;
    }
    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) (Math.random() * range) + range / 5, parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);

       // dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chartPie));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tfLight);
        chartPie.setData(data);

        // undo all highlights
        chartPie.highlightValues(null);

        chartPie.invalidate();
    }
}