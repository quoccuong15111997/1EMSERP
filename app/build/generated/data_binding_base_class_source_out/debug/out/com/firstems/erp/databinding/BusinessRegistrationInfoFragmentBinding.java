// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.firstems.erp.helper.widgets.EMSButtonSecond;
import com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder;
import com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class BusinessRegistrationInfoFragmentBinding extends ViewDataBinding {
  @NonNull
  public final EMSButtonSecond btnDone;

  @NonNull
  public final CheckBox chkAfternoon;

  @NonNull
  public final CheckBox chkEverning;

  @NonNull
  public final CheckBox chkMorning;

  @NonNull
  public final ImageView imageView24;

  @NonNull
  public final ImageView imageView242;

  @NonNull
  public final View include10;

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
  public final LinearLayout linearLayoutLoaiChamCongChieu;

  @NonNull
  public final LinearLayout linearLayoutLoaiChamCongSang;

  @NonNull
  public final LinearLayout linearLayoutLoaiChamCongToi;

  @NonNull
  public final LinearLayout linearLayoutLocate;

  @NonNull
  public final LinearLayout linearLayoutLocateDetail;

  @NonNull
  public final LinearLayout linearLayoutLocateType;

  @NonNull
  public final Spinner spinerLoaiChamCongChieu;

  @NonNull
  public final Spinner spinerLoaiChamCongSang;

  @NonNull
  public final Spinner spinerLoaiChamCongToi;

  @NonNull
  public final Spinner spinerLocateType;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateBegin;

  @NonNull
  public final EMSTextviewHighLineNonBorder txtDateEnd;

  @NonNull
  public final TextView txtLocate;

  @NonNull
  public final TextView txtTileNoiCongTac;

  @NonNull
  public final TextView txtTitle;

  @NonNull
  public final TextView txtTitleDateBegin;

  @NonNull
  public final TextView txtTitleDateEnd;

  @NonNull
  public final TextView txtTitleLoaiChamCongChieu;

  @NonNull
  public final TextView txtTitleLoaiChamCongSang;

  @NonNull
  public final TextView txtTitleLoaiChamCongToi;

  protected BusinessRegistrationInfoFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, EMSButtonSecond btnDone, CheckBox chkAfternoon, CheckBox chkEverning,
      CheckBox chkMorning, ImageView imageView24, ImageView imageView242, View include10,
      EMSConstrainLayoutBorder layoutDateEnd, EMSConstrainLayoutBorder layoutDateForm,
      LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout222,
      LinearLayout linearLayout22222, LinearLayout linearLayout6, LinearLayout linearLayout7,
      LinearLayout linearLayoutLoaiChamCongChieu, LinearLayout linearLayoutLoaiChamCongSang,
      LinearLayout linearLayoutLoaiChamCongToi, LinearLayout linearLayoutLocate,
      LinearLayout linearLayoutLocateDetail, LinearLayout linearLayoutLocateType,
      Spinner spinerLoaiChamCongChieu, Spinner spinerLoaiChamCongSang,
      Spinner spinerLoaiChamCongToi, Spinner spinerLocateType,
      EMSTextviewHighLineNonBorder txtDateBegin, EMSTextviewHighLineNonBorder txtDateEnd,
      TextView txtLocate, TextView txtTileNoiCongTac, TextView txtTitle, TextView txtTitleDateBegin,
      TextView txtTitleDateEnd, TextView txtTitleLoaiChamCongChieu,
      TextView txtTitleLoaiChamCongSang, TextView txtTitleLoaiChamCongToi) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnDone = btnDone;
    this.chkAfternoon = chkAfternoon;
    this.chkEverning = chkEverning;
    this.chkMorning = chkMorning;
    this.imageView24 = imageView24;
    this.imageView242 = imageView242;
    this.include10 = include10;
    this.layoutDateEnd = layoutDateEnd;
    this.layoutDateForm = layoutDateForm;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout222 = linearLayout222;
    this.linearLayout22222 = linearLayout22222;
    this.linearLayout6 = linearLayout6;
    this.linearLayout7 = linearLayout7;
    this.linearLayoutLoaiChamCongChieu = linearLayoutLoaiChamCongChieu;
    this.linearLayoutLoaiChamCongSang = linearLayoutLoaiChamCongSang;
    this.linearLayoutLoaiChamCongToi = linearLayoutLoaiChamCongToi;
    this.linearLayoutLocate = linearLayoutLocate;
    this.linearLayoutLocateDetail = linearLayoutLocateDetail;
    this.linearLayoutLocateType = linearLayoutLocateType;
    this.spinerLoaiChamCongChieu = spinerLoaiChamCongChieu;
    this.spinerLoaiChamCongSang = spinerLoaiChamCongSang;
    this.spinerLoaiChamCongToi = spinerLoaiChamCongToi;
    this.spinerLocateType = spinerLocateType;
    this.txtDateBegin = txtDateBegin;
    this.txtDateEnd = txtDateEnd;
    this.txtLocate = txtLocate;
    this.txtTileNoiCongTac = txtTileNoiCongTac;
    this.txtTitle = txtTitle;
    this.txtTitleDateBegin = txtTitleDateBegin;
    this.txtTitleDateEnd = txtTitleDateEnd;
    this.txtTitleLoaiChamCongChieu = txtTitleLoaiChamCongChieu;
    this.txtTitleLoaiChamCongSang = txtTitleLoaiChamCongSang;
    this.txtTitleLoaiChamCongToi = txtTitleLoaiChamCongToi;
  }

  @NonNull
  public static BusinessRegistrationInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.business_registration_info_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static BusinessRegistrationInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<BusinessRegistrationInfoFragmentBinding>inflateInternal(inflater, R.layout.business_registration_info_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static BusinessRegistrationInfoFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.business_registration_info_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static BusinessRegistrationInfoFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<BusinessRegistrationInfoFragmentBinding>inflateInternal(inflater, R.layout.business_registration_info_fragment, null, false, component);
  }

  public static BusinessRegistrationInfoFragmentBinding bind(@NonNull View view) {
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
  public static BusinessRegistrationInfoFragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (BusinessRegistrationInfoFragmentBinding)bind(component, view, R.layout.business_registration_info_fragment);
  }
}
