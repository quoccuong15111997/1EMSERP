// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class AccountFragmentBinding extends ViewDataBinding {
  @NonNull
  public final CircularImageView image;

  @NonNull
  public final NestedScrollView nestedScrollView;

  @NonNull
  public final Toolbar toolbar;

  protected AccountFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CircularImageView image, NestedScrollView nestedScrollView, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.image = image;
    this.nestedScrollView = nestedScrollView;
    this.toolbar = toolbar;
  }

  @NonNull
  public static AccountFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.account_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static AccountFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<AccountFragmentBinding>inflateInternal(inflater, R.layout.account_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static AccountFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.account_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static AccountFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<AccountFragmentBinding>inflateInternal(inflater, R.layout.account_fragment, null, false, component);
  }

  public static AccountFragmentBinding bind(@NonNull View view) {
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
  public static AccountFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (AccountFragmentBinding)bind(component, view, R.layout.account_fragment);
  }
}