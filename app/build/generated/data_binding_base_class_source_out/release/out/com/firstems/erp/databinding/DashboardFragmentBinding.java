// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class DashboardFragmentBinding extends ViewDataBinding {
  @NonNull
  public final PieChart chartPie;

  @NonNull
  public final ConstraintLayout constraintLayout5;

  @NonNull
  public final ConstraintLayout constraintLayout6;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imgCalenda;

  @NonNull
  public final ImageView imgContinueRevenue;

  @NonNull
  public final View include12;

  @NonNull
  public final LayoutTopDashboardBinding include13;

  @NonNull
  public final Spinner spinnerTime;

  @NonNull
  public final BarChart topBarChart;

  @NonNull
  public final TextView txtOrderValue;

  @NonNull
  public final TextView txtTitleOrder;

  @NonNull
  public final TextView txtTitleRevenue;

  @NonNull
  public final TextView txtTitleVentory;

  @NonNull
  public final TextView txtUnitOrder;

  @NonNull
  public final TextView txtUnitVentory;

  @NonNull
  public final TextView txtValueNumberOrder;

  @NonNull
  public final TextView txtValueNumberVentory;

  @NonNull
  public final TextView txtVentoryValue;

  @NonNull
  public final View view7;

  protected DashboardFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      PieChart chartPie, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6,
      ImageView imageView7, ImageView imgCalenda, ImageView imgContinueRevenue, View include12,
      LayoutTopDashboardBinding include13, Spinner spinnerTime, BarChart topBarChart,
      TextView txtOrderValue, TextView txtTitleOrder, TextView txtTitleRevenue,
      TextView txtTitleVentory, TextView txtUnitOrder, TextView txtUnitVentory,
      TextView txtValueNumberOrder, TextView txtValueNumberVentory, TextView txtVentoryValue,
      View view7) {
    super(_bindingComponent, _root, _localFieldCount);
    this.chartPie = chartPie;
    this.constraintLayout5 = constraintLayout5;
    this.constraintLayout6 = constraintLayout6;
    this.imageView7 = imageView7;
    this.imgCalenda = imgCalenda;
    this.imgContinueRevenue = imgContinueRevenue;
    this.include12 = include12;
    this.include13 = include13;
    setContainedBinding(this.include13);
    this.spinnerTime = spinnerTime;
    this.topBarChart = topBarChart;
    this.txtOrderValue = txtOrderValue;
    this.txtTitleOrder = txtTitleOrder;
    this.txtTitleRevenue = txtTitleRevenue;
    this.txtTitleVentory = txtTitleVentory;
    this.txtUnitOrder = txtUnitOrder;
    this.txtUnitVentory = txtUnitVentory;
    this.txtValueNumberOrder = txtValueNumberOrder;
    this.txtValueNumberVentory = txtValueNumberVentory;
    this.txtVentoryValue = txtVentoryValue;
    this.view7 = view7;
  }

  @NonNull
  public static DashboardFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DashboardFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DashboardFragmentBinding>inflateInternal(inflater, R.layout.dashboard_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static DashboardFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DashboardFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DashboardFragmentBinding>inflateInternal(inflater, R.layout.dashboard_fragment, null, false, component);
  }

  public static DashboardFragmentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static DashboardFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (DashboardFragmentBinding)bind(component, view, R.layout.dashboard_fragment);
  }
}