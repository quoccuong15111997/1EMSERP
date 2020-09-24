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
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.helper.widgets.EMSButtonSecond;
import com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder;
import com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder;
import com.firstems.erp.model.switchshift.SwitchShift;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class SwitchShiftInfoFragmentBinding extends ViewDataBinding {
  @NonNull
  public final EMSButtonSecond btnDone;

  @NonNull
  public final CheckBox chkAfternoon;

  @NonNull
  public final CheckBox chkEverning;

  @NonNull
  public final CheckBox chkMorning;

  @NonNull
  public final TextView edtEmployee;

  @NonNull
  public final TextView edtLoaiChamCongChieu;

  @NonNull
  public final TextView edtLoaiChamCongSang;

  @NonNull
  public final TextView edtLoaiChamCongToi;

  @NonNull
  public final TextView edtNhanVienDuocChamCong;

  @NonNull
  public final ImageView imageView24;

  @NonNull
  public final ImageView imageView242;

  @NonNull
  public final View include3;

  @NonNull
  public final EMSConstrainLayoutBorder layoutDateEnd;

  @NonNull
  public final EMSConstrainLayoutBorder layoutDateForm;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout222;

  @NonNull
  public final LinearLayout linearLayout22222;

  @NonNull
  public final LinearLayout linearLayout6;

  @NonNull
  public final LinearLayout linearLayout7;

  @NonNull
  public final LinearLayout linearLayoutEmployee;

  @NonNull
  public final LinearLayout linearLayoutLoaiChamCongChieu;

  @NonNull
  public final LinearLayout linearLayoutLoaiChamCongSang;

  @NonNull
  public final LinearLayout linearLayoutLoaiChamCongToi;

  @NonNull
  public final LinearLayout linearLayoutNhanVienDuocChamCong;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateBegin;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateEnd;

  @NonNull
  public final TextView txtTitleDateBegin;

  @NonNull
  public final TextView txtTitleDateEnd;

  @NonNull
  public final TextView txtTitleEmployee;

  @NonNull
  public final TextView txtTitleLoaiChamCongChieu;

  @NonNull
  public final TextView txtTitleLoaiChamCongSang;

  @NonNull
  public final TextView txtTitleLoaiChamCongToi;

  @NonNull
  public final TextView txtTitleNhanVienDuocChamCong;

  @Bindable
  protected String mDateBegin;

  @Bindable
  protected String mDateEnd;

  @Bindable
  protected SwitchShift mModel;

  @Bindable
  protected Employee mEmployDiLam;

  @Bindable
  protected Employee mEmployDuocChamCong;

  protected SwitchShiftInfoFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, EMSButtonSecond btnDone, CheckBox chkAfternoon, CheckBox chkEverning,
      CheckBox chkMorning, TextView edtEmployee, TextView edtLoaiChamCongChieu,
      TextView edtLoaiChamCongSang, TextView edtLoaiChamCongToi, TextView edtNhanVienDuocChamCong,
      ImageView imageView24, ImageView imageView242, View include3,
      EMSConstrainLayoutBorder layoutDateEnd, EMSConstrainLayoutBorder layoutDateForm,
      LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout222,
      LinearLayout linearLayout22222, LinearLayout linearLayout6, LinearLayout linearLayout7,
      LinearLayout linearLayoutEmployee, LinearLayout linearLayoutLoaiChamCongChieu,
      LinearLayout linearLayoutLoaiChamCongSang, LinearLayout linearLayoutLoaiChamCongToi,
      LinearLayout linearLayoutNhanVienDuocChamCong, EMSTextviewHighLineNonBorder txtDateBegin,
      EMSTextviewHighLineNonBorder txtDateEnd, TextView txtTitleDateBegin, TextView txtTitleDateEnd,
      TextView txtTitleEmployee, TextView txtTitleLoaiChamCongChieu,
      TextView txtTitleLoaiChamCongSang, TextView txtTitleLoaiChamCongToi,
      TextView txtTitleNhanVienDuocChamCong) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnDone = btnDone;
    this.chkAfternoon = chkAfternoon;
    this.chkEverning = chkEverning;
    this.chkMorning = chkMorning;
    this.edtEmployee = edtEmployee;
    this.edtLoaiChamCongChieu = edtLoaiChamCongChieu;
    this.edtLoaiChamCongSang = edtLoaiChamCongSang;
    this.edtLoaiChamCongToi = edtLoaiChamCongToi;
    this.edtNhanVienDuocChamCong = edtNhanVienDuocChamCong;
    this.imageView24 = imageView24;
    this.imageView242 = imageView242;
    this.include3 = include3;
    this.layoutDateEnd = layoutDateEnd;
    this.layoutDateForm = layoutDateForm;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout222 = linearLayout222;
    this.linearLayout22222 = linearLayout22222;
    this.linearLayout6 = linearLayout6;
    this.linearLayout7 = linearLayout7;
    this.linearLayoutEmployee = linearLayoutEmployee;
    this.linearLayoutLoaiChamCongChieu = linearLayoutLoaiChamCongChieu;
    this.linearLayoutLoaiChamCongSang = linearLayoutLoaiChamCongSang;
    this.linearLayoutLoaiChamCongToi = linearLayoutLoaiChamCongToi;
    this.linearLayoutNhanVienDuocChamCong = linearLayoutNhanVienDuocChamCong;
    this.txtDateBegin = txtDateBegin;
    this.txtDateEnd = txtDateEnd;
    this.txtTitleDateBegin = txtTitleDateBegin;
    this.txtTitleDateEnd = txtTitleDateEnd;
    this.txtTitleEmployee = txtTitleEmployee;
    this.txtTitleLoaiChamCongChieu = txtTitleLoaiChamCongChieu;
    this.txtTitleLoaiChamCongSang = txtTitleLoaiChamCongSang;
    this.txtTitleLoaiChamCongToi = txtTitleLoaiChamCongToi;
    this.txtTitleNhanVienDuocChamCong = txtTitleNhanVienDuocChamCong;
  }

  public abstract void setDateBegin(@Nullable String dateBegin);

  @Nullable
  public String getDateBegin() {
    return mDateBegin;
  }

  public abstract void setDateEnd(@Nullable String dateEnd);

  @Nullable
  public String getDateEnd() {
    return mDateEnd;
  }

  public abstract void setModel(@Nullable SwitchShift model);

  @Nullable
  public SwitchShift getModel() {
    return mModel;
  }

  public abstract void setEmployDiLam(@Nullable Employee employDiLam);

  @Nullable
  public Employee getEmployDiLam() {
    return mEmployDiLam;
  }

  public abstract void setEmployDuocChamCong(@Nullable Employee employDuocChamCong);

  @Nullable
  public Employee getEmployDuocChamCong() {
    return mEmployDuocChamCong;
  }

  @NonNull
  public static SwitchShiftInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.switch_shift_info_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static SwitchShiftInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<SwitchShiftInfoFragmentBinding>inflateInternal(inflater, R.layout.switch_shift_info_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static SwitchShiftInfoFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.switch_shift_info_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static SwitchShiftInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<SwitchShiftInfoFragmentBinding>inflateInternal(inflater, R.layout.switch_shift_info_fragment, null, false, component);
  }

  public static SwitchShiftInfoFragmentBinding bind(@NonNull View view) {
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
  public static SwitchShiftInfoFragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (SwitchShiftInfoFragmentBinding)bind(component, view, R.layout.switch_shift_info_fragment);
  }
}
