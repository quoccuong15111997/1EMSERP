// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HomeFragmentBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imageView36;

  @NonNull
  public final LinearLayout linearLayout14;

  protected HomeFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView imageView36, LinearLayout linearLayout14) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageView36 = imageView36;
    this.linearLayout14 = linearLayout14;
  }

  @NonNull
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HomeFragmentBinding>inflateInternal(inflater, R.layout.home_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HomeFragmentBinding>inflateInternal(inflater, R.layout.home_fragment, null, false, component);
  }

  public static HomeFragmentBinding bind(@NonNull View view) {
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
  public static HomeFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (HomeFragmentBinding)bind(component, view, R.layout.home_fragment);
  }
}