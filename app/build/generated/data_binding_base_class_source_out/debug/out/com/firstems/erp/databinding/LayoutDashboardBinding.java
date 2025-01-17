// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LayoutDashboardBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final PieChart chartPie;

  @NonNull
  public final CollapsingToolbarLayout collapsingToolbar;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final NestedScrollView nestedContent;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView17;

  @NonNull
  public final TextView textView18;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final BarChart topBarChart;

  protected LayoutDashboardBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appBarLayout, PieChart chartPie, CollapsingToolbarLayout collapsingToolbar,
      ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4,
      ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8,
      ImageView imageView9, NestedScrollView nestedContent, TextView textView, TextView textView10,
      TextView textView11, TextView textView12, TextView textView13, TextView textView14,
      TextView textView15, TextView textView16, TextView textView17, TextView textView18,
      TextView textView19, TextView textView2, TextView textView20, TextView textView3,
      TextView textView4, TextView textView6, TextView textView7, TextView textView8,
      TextView textView9, BarChart topBarChart) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appBarLayout = appBarLayout;
    this.chartPie = chartPie;
    this.collapsingToolbar = collapsingToolbar;
    this.imageView = imageView;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.imageView7 = imageView7;
    this.imageView8 = imageView8;
    this.imageView9 = imageView9;
    this.nestedContent = nestedContent;
    this.textView = textView;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textView13 = textView13;
    this.textView14 = textView14;
    this.textView15 = textView15;
    this.textView16 = textView16;
    this.textView17 = textView17;
    this.textView18 = textView18;
    this.textView19 = textView19;
    this.textView2 = textView2;
    this.textView20 = textView20;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
    this.topBarChart = topBarChart;
  }

  @NonNull
  public static LayoutDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_dashboard, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LayoutDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LayoutDashboardBinding>inflateInternal(inflater, R.layout.layout_dashboard, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_dashboard, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LayoutDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LayoutDashboardBinding>inflateInternal(inflater, R.layout.layout_dashboard, null, false, component);
  }

  public static LayoutDashboardBinding bind(@NonNull View view) {
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
  public static LayoutDashboardBinding bind(@NonNull View view, @Nullable Object component) {
    return (LayoutDashboardBinding)bind(component, view, R.layout.layout_dashboard);
  }
}
