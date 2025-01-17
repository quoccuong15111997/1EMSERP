// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ReportingFragmentBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout constraintLayout7;

  @NonNull
  public final FrameLayout frameReportting;

  @NonNull
  public final View include14;

  @NonNull
  public final TabItem tabFinance;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final TabItem tabSell;

  @NonNull
  public final TabItem tabWarehouse;

  protected ReportingFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout constraintLayout7, FrameLayout frameReportting, View include14,
      TabItem tabFinance, TabLayout tabLayout, TabItem tabSell, TabItem tabWarehouse) {
    super(_bindingComponent, _root, _localFieldCount);
    this.constraintLayout7 = constraintLayout7;
    this.frameReportting = frameReportting;
    this.include14 = include14;
    this.tabFinance = tabFinance;
    this.tabLayout = tabLayout;
    this.tabSell = tabSell;
    this.tabWarehouse = tabWarehouse;
  }

  @NonNull
  public static ReportingFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.reporting_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ReportingFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ReportingFragmentBinding>inflateInternal(inflater, R.layout.reporting_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static ReportingFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.reporting_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ReportingFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ReportingFragmentBinding>inflateInternal(inflater, R.layout.reporting_fragment, null, false, component);
  }

  public static ReportingFragmentBinding bind(@NonNull View view) {
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
  public static ReportingFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (ReportingFragmentBinding)bind(component, view, R.layout.reporting_fragment);
  }
}
