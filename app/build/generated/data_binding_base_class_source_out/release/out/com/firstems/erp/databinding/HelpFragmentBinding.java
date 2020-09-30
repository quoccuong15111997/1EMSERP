// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HelpFragmentBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardSystemSetting;

  @NonNull
  public final ImageView imageView30;

  @NonNull
  public final ImageView imageView301;

  @NonNull
  public final ImageView imageView31;

  @NonNull
  public final ImageView imageView33;

  @NonNull
  public final ImageView imageView34;

  @NonNull
  public final ImageView imageView35;

  @NonNull
  public final View include18;

  @NonNull
  public final TextView textView47;

  @NonNull
  public final TextView textView471;

  @NonNull
  public final TextView textView48;

  @NonNull
  public final TextView textView49;

  @NonNull
  public final TextView textView51;

  @NonNull
  public final TextView textView52;

  @NonNull
  public final TextView textView53;

  @NonNull
  public final TextView txtCoppyright;

  @NonNull
  public final TextView txtTitleHelp;

  @NonNull
  public final TextView txtTitleSystemSetting;

  @NonNull
  public final View view10;

  @NonNull
  public final View view11;

  @NonNull
  public final View view12;

  protected HelpFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardSystemSetting, ImageView imageView30, ImageView imageView301,
      ImageView imageView31, ImageView imageView33, ImageView imageView34, ImageView imageView35,
      View include18, TextView textView47, TextView textView471, TextView textView48,
      TextView textView49, TextView textView51, TextView textView52, TextView textView53,
      TextView txtCoppyright, TextView txtTitleHelp, TextView txtTitleSystemSetting, View view10,
      View view11, View view12) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardSystemSetting = cardSystemSetting;
    this.imageView30 = imageView30;
    this.imageView301 = imageView301;
    this.imageView31 = imageView31;
    this.imageView33 = imageView33;
    this.imageView34 = imageView34;
    this.imageView35 = imageView35;
    this.include18 = include18;
    this.textView47 = textView47;
    this.textView471 = textView471;
    this.textView48 = textView48;
    this.textView49 = textView49;
    this.textView51 = textView51;
    this.textView52 = textView52;
    this.textView53 = textView53;
    this.txtCoppyright = txtCoppyright;
    this.txtTitleHelp = txtTitleHelp;
    this.txtTitleSystemSetting = txtTitleSystemSetting;
    this.view10 = view10;
    this.view11 = view11;
    this.view12 = view12;
  }

  @NonNull
  public static HelpFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.help_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HelpFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HelpFragmentBinding>inflateInternal(inflater, R.layout.help_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static HelpFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.help_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HelpFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HelpFragmentBinding>inflateInternal(inflater, R.layout.help_fragment, null, false, component);
  }

  public static HelpFragmentBinding bind(@NonNull View view) {
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
  public static HelpFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (HelpFragmentBinding)bind(component, view, R.layout.help_fragment);
  }
}