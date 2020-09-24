// Generated by data binding compiler. Do not edit!
package com.firstems.erp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.R;
import com.firstems.erp.helper.widgets.EMSButtonSecond;
import com.firstems.erp.helper.widgets.EMSEditText;
import com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class TicketBillPaymentRequestFragmentBinding extends ViewDataBinding {
  @NonNull
  public final EMSButtonSecond btnDone;

  @NonNull
  public final EMSTextviewHighLineNonBorder edtDate;

  @NonNull
  public final EMSEditText edtInfo;

  @NonNull
  public final EMSEditText edtSoHoaDon;

  @NonNull
  public final EMSEditText edtSoTien;

  @NonNull
  public final View header;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ConstraintLayout layoutDateDeNghiChi;

  @NonNull
  public final LinearLayout linearLayoutDate;

  @NonNull
  public final LinearLayout linearLayoutNoidungDeNghi;

  @NonNull
  public final LinearLayout linearLayoutSoHoaDon;

  @NonNull
  public final LinearLayout linearLayoutSoTien;

  @NonNull
  public final TextView txtTitleDate;

  @NonNull
  public final TextView txtTitleInfo;

  @NonNull
  public final TextView txtTitleSoHoaDon;

  @NonNull
  public final TextView txtTitleSoTien;

  protected TicketBillPaymentRequestFragmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, EMSButtonSecond btnDone, EMSTextviewHighLineNonBorder edtDate,
      EMSEditText edtInfo, EMSEditText edtSoHoaDon, EMSEditText edtSoTien, View header,
      ImageView imageView, ConstraintLayout layoutDateDeNghiChi, LinearLayout linearLayoutDate,
      LinearLayout linearLayoutNoidungDeNghi, LinearLayout linearLayoutSoHoaDon,
      LinearLayout linearLayoutSoTien, TextView txtTitleDate, TextView txtTitleInfo,
      TextView txtTitleSoHoaDon, TextView txtTitleSoTien) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnDone = btnDone;
    this.edtDate = edtDate;
    this.edtInfo = edtInfo;
    this.edtSoHoaDon = edtSoHoaDon;
    this.edtSoTien = edtSoTien;
    this.header = header;
    this.imageView = imageView;
    this.layoutDateDeNghiChi = layoutDateDeNghiChi;
    this.linearLayoutDate = linearLayoutDate;
    this.linearLayoutNoidungDeNghi = linearLayoutNoidungDeNghi;
    this.linearLayoutSoHoaDon = linearLayoutSoHoaDon;
    this.linearLayoutSoTien = linearLayoutSoTien;
    this.txtTitleDate = txtTitleDate;
    this.txtTitleInfo = txtTitleInfo;
    this.txtTitleSoHoaDon = txtTitleSoHoaDon;
    this.txtTitleSoTien = txtTitleSoTien;
  }

  @NonNull
  public static TicketBillPaymentRequestFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.ticket_bill_payment_request_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static TicketBillPaymentRequestFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<TicketBillPaymentRequestFragmentBinding>inflateInternal(inflater, R.layout.ticket_bill_payment_request_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static TicketBillPaymentRequestFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.ticket_bill_payment_request_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static TicketBillPaymentRequestFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<TicketBillPaymentRequestFragmentBinding>inflateInternal(inflater, R.layout.ticket_bill_payment_request_fragment, null, false, component);
  }

  public static TicketBillPaymentRequestFragmentBinding bind(@NonNull View view) {
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
  public static TicketBillPaymentRequestFragmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (TicketBillPaymentRequestFragmentBinding)bind(component, view, R.layout.ticket_bill_payment_request_fragment);
  }
}