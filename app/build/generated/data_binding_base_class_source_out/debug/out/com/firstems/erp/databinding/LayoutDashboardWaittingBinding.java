// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LayoutDashboardWaittingBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imageView16;

  @NonNull
  public final ImageView imageView22;

  @NonNull
  public final ImageView imageView23;

  @NonNull
  public final ImageView imageView25;

  @NonNull
  public final ImageView imageView26;

  @NonNull
  public final ImageView imageView27;

  @NonNull
  public final ImageView imageView28;

  @NonNull
  public final ImageView imageView29;

  @NonNull
  public final TextView textView38;

  @NonNull
  public final TextView textView40;

  @NonNull
  public final TextView textView41;

  @NonNull
  public final TextView textView42;

  @NonNull
  public final TextView textView43;

  @NonNull
  public final TextView textView44;

  @NonNull
  public final TextView textView45;

  @NonNull
  public final TextView textView46;

  protected LayoutDashboardWaittingBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ImageView imageView16, ImageView imageView22, ImageView imageView23,
      ImageView imageView25, ImageView imageView26, ImageView imageView27, ImageView imageView28,
      ImageView imageView29, TextView textView38, TextView textView40, TextView textView41,
      TextView textView42, TextView textView43, TextView textView44, TextView textView45,
      TextView textView46) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageView16 = imageView16;
    this.imageView22 = imageView22;
    this.imageView23 = imageView23;
    this.imageView25 = imageView25;
    this.imageView26 = imageView26;
    this.imageView27 = imageView27;
    this.imageView28 = imageView28;
    this.imageView29 = imageView29;
    this.textView38 = textView38;
    this.textView40 = textView40;
    this.textView41 = textView41;
    this.textView42 = textView42;
    this.textView43 = textView43;
    this.textView44 = textView44;
    this.textView45 = textView45;
    this.textView46 = textView46;
  }

  @NonNull
  public static LayoutDashboardWaittingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_dashboard_waitting, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LayoutDashboardWaittingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LayoutDashboardWaittingBinding>inflateInternal(inflater, R.layout.layout_dashboard_waitting, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutDashboardWaittingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_dashboard_waitting, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LayoutDashboardWaittingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LayoutDashboardWaittingBinding>inflateInternal(inflater, R.layout.layout_dashboard_waitting, null, false, component);
  }

  public static LayoutDashboardWaittingBinding bind(@NonNull View view) {
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
  public static LayoutDashboardWaittingBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (LayoutDashboardWaittingBinding)bind(component, view, R.layout.layout_dashboard_waitting);
  }
}
