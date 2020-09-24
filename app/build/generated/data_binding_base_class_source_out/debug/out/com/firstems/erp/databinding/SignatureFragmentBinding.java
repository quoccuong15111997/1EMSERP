// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.firstems.erp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class SignatureFragmentBinding extends ViewDataBinding {
  @NonNull
  public final FloatingActionButton fabFilter;

  @NonNull
  public final View include;

  @NonNull
  public final RecyclerView recycleSignature;

  protected SignatureFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      FloatingActionButton fabFilter, View include, RecyclerView recycleSignature) {
    super(_bindingComponent, _root, _localFieldCount);
    this.fabFilter = fabFilter;
    this.include = include;
    this.recycleSignature = recycleSignature;
  }

  @NonNull
  public static SignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.signature_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static SignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<SignatureFragmentBinding>inflateInternal(inflater, R.layout.signature_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static SignatureFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.signature_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static SignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<SignatureFragmentBinding>inflateInternal(inflater, R.layout.signature_fragment, null, false, component);
  }

  public static SignatureFragmentBinding bind(@NonNull View view) {
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
  public static SignatureFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (SignatureFragmentBinding)bind(component, view, R.layout.signature_fragment);
  }
}
