// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.firstems.erp.helper.widgets.EMSButtonSecond;
import com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder;
import com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder;
import com.firstems.erp.model.FilterModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FilterSignatureFragmentBinding extends ViewDataBinding {
  @NonNull
  public final EMSButtonSecond btnSort;

  @NonNull
  public final CheckBox chkChoDuyet;

  @NonNull
  public final CheckBox chkChuaTrinhKy;

  @NonNull
  public final CheckBox chkHoanTat;

  @NonNull
  public final ImageView imageView24;

  @NonNull
  public final ImageView imageView242;

  @NonNull
  public final View include8;

  @NonNull
  public final EMSConstrainLayoutBorder layoutDateEnd;

  @NonNull
  public final EMSConstrainLayoutBorder layoutDateForm;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout6;

  @NonNull
  public final LinearLayout linearLayout7;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateBegin;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateEnd;

  @NonNull
  public final TextView txtTitleDateBegin;

  @NonNull
  public final TextView txtTitleDateEnd;

  @NonNull
  public final TextView txtTitleStatus;

  @NonNull
  public final View view4;

  @NonNull
  public final View view5;

  @NonNull
  public final View view6;

  @Bindable
  protected FilterModel mFilter;

  protected FilterSignatureFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, EMSButtonSecond btnSort, CheckBox chkChoDuyet, CheckBox chkChuaTrinhKy,
      CheckBox chkHoanTat, ImageView imageView24, ImageView imageView242, View include8,
      EMSConstrainLayoutBorder layoutDateEnd, EMSConstrainLayoutBorder layoutDateForm,
      LinearLayout linearLayout, LinearLayout linearLayout6, LinearLayout linearLayout7,
      EMSTextviewHighLineNonBorder txtDateBegin, EMSTextviewHighLineNonBorder txtDateEnd,
      TextView txtTitleDateBegin, TextView txtTitleDateEnd, TextView txtTitleStatus, View view4,
      View view5, View view6) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSort = btnSort;
    this.chkChoDuyet = chkChoDuyet;
    this.chkChuaTrinhKy = chkChuaTrinhKy;
    this.chkHoanTat = chkHoanTat;
    this.imageView24 = imageView24;
    this.imageView242 = imageView242;
    this.include8 = include8;
    this.layoutDateEnd = layoutDateEnd;
    this.layoutDateForm = layoutDateForm;
    this.linearLayout = linearLayout;
    this.linearLayout6 = linearLayout6;
    this.linearLayout7 = linearLayout7;
    this.txtDateBegin = txtDateBegin;
    this.txtDateEnd = txtDateEnd;
    this.txtTitleDateBegin = txtTitleDateBegin;
    this.txtTitleDateEnd = txtTitleDateEnd;
    this.txtTitleStatus = txtTitleStatus;
    this.view4 = view4;
    this.view5 = view5;
    this.view6 = view6;
  }

  public abstract void setFilter(@Nullable FilterModel filter);

  @Nullable
  public FilterModel getFilter() {
    return mFilter;
  }

  @NonNull
  public static FilterSignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.filter_signature_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FilterSignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FilterSignatureFragmentBinding>inflateInternal(inflater, R.layout.filter_signature_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static FilterSignatureFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.filter_signature_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FilterSignatureFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FilterSignatureFragmentBinding>inflateInternal(inflater, R.layout.filter_signature_fragment, null, false, component);
  }

  public static FilterSignatureFragmentBinding bind(@NonNull View view) {
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
  public static FilterSignatureFragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FilterSignatureFragmentBinding)bind(component, view, R.layout.filter_signature_fragment);
  }
}
