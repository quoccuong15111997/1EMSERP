// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.firstems.erp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class NationalFragmentBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout contrainsSearch;

  @NonNull
  public final EditText editTextTextPersonName2;

  @NonNull
  public final ImageView imgSearch;

  @NonNull
  public final View include22;

  @NonNull
  public final RadioButton radAll;

  @NonNull
  public final RadioButton radSelected;

  @NonNull
  public final RadioButton radUnSelected;

  @NonNull
  public final RadioGroup radioGroup;

  @NonNull
  public final RecyclerView recycle;

  @NonNull
  public final TextView txtNumberSelect;

  protected NationalFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout contrainsSearch, EditText editTextTextPersonName2, ImageView imgSearch,
      View include22, RadioButton radAll, RadioButton radSelected, RadioButton radUnSelected,
      RadioGroup radioGroup, RecyclerView recycle, TextView txtNumberSelect) {
    super(_bindingComponent, _root, _localFieldCount);
    this.contrainsSearch = contrainsSearch;
    this.editTextTextPersonName2 = editTextTextPersonName2;
    this.imgSearch = imgSearch;
    this.include22 = include22;
    this.radAll = radAll;
    this.radSelected = radSelected;
    this.radUnSelected = radUnSelected;
    this.radioGroup = radioGroup;
    this.recycle = recycle;
    this.txtNumberSelect = txtNumberSelect;
  }

  @NonNull
  public static NationalFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.national_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static NationalFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<NationalFragmentBinding>inflateInternal(inflater, R.layout.national_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static NationalFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.national_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static NationalFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<NationalFragmentBinding>inflateInternal(inflater, R.layout.national_fragment, null, false, component);
  }

  public static NationalFragmentBinding bind(@NonNull View view) {
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
  public static NationalFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (NationalFragmentBinding)bind(component, view, R.layout.national_fragment);
  }
}
