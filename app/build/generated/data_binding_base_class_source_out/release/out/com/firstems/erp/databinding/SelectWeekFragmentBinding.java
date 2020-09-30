// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class SelectWeekFragmentBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout llLastWeek;

  @NonNull
  public final LinearLayout llSevenDayAgo;

  @NonNull
  public final LinearLayout llThisWeek;

  @NonNull
  public final RadioButton rad;

  @NonNull
  public final RadioButton radLastWeek;

  @NonNull
  public final RadioButton radSevenDayAgo;

  @NonNull
  public final TextView textView32;

  @NonNull
  public final TextView textView33;

  @NonNull
  public final TextView textView34;

  @NonNull
  public final TextView textView35;

  @NonNull
  public final TextView textView36;

  protected SelectWeekFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout llLastWeek, LinearLayout llSevenDayAgo, LinearLayout llThisWeek, RadioButton rad,
      RadioButton radLastWeek, RadioButton radSevenDayAgo, TextView textView32, TextView textView33,
      TextView textView34, TextView textView35, TextView textView36) {
    super(_bindingComponent, _root, _localFieldCount);
    this.llLastWeek = llLastWeek;
    this.llSevenDayAgo = llSevenDayAgo;
    this.llThisWeek = llThisWeek;
    this.rad = rad;
    this.radLastWeek = radLastWeek;
    this.radSevenDayAgo = radSevenDayAgo;
    this.textView32 = textView32;
    this.textView33 = textView33;
    this.textView34 = textView34;
    this.textView35 = textView35;
    this.textView36 = textView36;
  }

  @NonNull
  public static SelectWeekFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.select_week_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static SelectWeekFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<SelectWeekFragmentBinding>inflateInternal(inflater, R.layout.select_week_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static SelectWeekFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.select_week_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static SelectWeekFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<SelectWeekFragmentBinding>inflateInternal(inflater, R.layout.select_week_fragment, null, false, component);
  }

  public static SelectWeekFragmentBinding bind(@NonNull View view) {
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
  public static SelectWeekFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (SelectWeekFragmentBinding)bind(component, view, R.layout.select_week_fragment);
  }
}