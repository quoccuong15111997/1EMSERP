package com.firstems.erp;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.firstems.erp.databinding.AccountFragmentBindingImpl;
import com.firstems.erp.databinding.ActivitySwitchShiftBindingImpl;
import com.firstems.erp.databinding.ActivityTestBindingImpl;
import com.firstems.erp.databinding.AdvanceProposalFormFragmentBindingImpl;
import com.firstems.erp.databinding.AdvanceProposalLayoutBindingImpl;
import com.firstems.erp.databinding.ApproveDetailFragmentBindingImpl;
import com.firstems.erp.databinding.ApprovedFragmentBindingImpl;
import com.firstems.erp.databinding.AskPermissionFragmentBindingImpl;
import com.firstems.erp.databinding.AskPermissionInfoFragmentBindingImpl;
import com.firstems.erp.databinding.BillPaymentFragmentBindingImpl;
import com.firstems.erp.databinding.BillPaymentRequestFragmentBindingImpl;
import com.firstems.erp.databinding.BusinessRegistrationFragmentBindingImpl;
import com.firstems.erp.databinding.BusinessRegistrationInfoFragmentBindingImpl;
import com.firstems.erp.databinding.ChartViewFragmentBindingImpl;
import com.firstems.erp.databinding.ConfigFragmentBindingImpl;
import com.firstems.erp.databinding.DashboardFragmentBindingImpl;
import com.firstems.erp.databinding.DataTableFragmentBindingImpl;
import com.firstems.erp.databinding.DefaultSignatureFragmentBindingImpl;
import com.firstems.erp.databinding.DepartmentFragmentBindingImpl;
import com.firstems.erp.databinding.DocumentDetailFragmentBindingImpl;
import com.firstems.erp.databinding.DocumentFragmentBindingImpl;
import com.firstems.erp.databinding.DocumnetListFragmentBindingImpl;
import com.firstems.erp.databinding.EmployeeFragmentBindingImpl;
import com.firstems.erp.databinding.FileFragmentBindingImpl;
import com.firstems.erp.databinding.FilterSignatureFragmentBindingImpl;
import com.firstems.erp.databinding.HelpFragmentBindingImpl;
import com.firstems.erp.databinding.HomeFragmentBindingImpl;
import com.firstems.erp.databinding.HomeFragmentTypeFlatBindingImpl;
import com.firstems.erp.databinding.LayoutDashboardBindingImpl;
import com.firstems.erp.databinding.LayoutDashboardCardBottomBindingImpl;
import com.firstems.erp.databinding.LayoutDashboardCardTopBindingImpl;
import com.firstems.erp.databinding.LayoutDashboardWaittingBindingImpl;
import com.firstems.erp.databinding.LayoutOptionApprovedBindingImpl;
import com.firstems.erp.databinding.LayoutSelectDateBindingImpl;
import com.firstems.erp.databinding.LayoutSignatureGirdBindingImpl;
import com.firstems.erp.databinding.LayoutTopDashboardBindingImpl;
import com.firstems.erp.databinding.LoaiDoiTuongNhanFragmentBindingImpl;
import com.firstems.erp.databinding.LoginFragmentBindingImpl;
import com.firstems.erp.databinding.LoginLayoutTypeFlatBindingImpl;
import com.firstems.erp.databinding.MoreFragmentBindingImpl;
import com.firstems.erp.databinding.NationalFragmentBindingImpl;
import com.firstems.erp.databinding.PDFViewerFragmentBindingImpl;
import com.firstems.erp.databinding.ProcessApproveFragmentBindingImpl;
import com.firstems.erp.databinding.ProvinceFragmentBindingImpl;
import com.firstems.erp.databinding.RelatedFieldsFragmentBindingImpl;
import com.firstems.erp.databinding.ReportFragmentBindingImpl;
import com.firstems.erp.databinding.ReportingFragmentBindingImpl;
import com.firstems.erp.databinding.ReviewProcessFragmentBindingImpl;
import com.firstems.erp.databinding.SelectCompanyFragmentBindingImpl;
import com.firstems.erp.databinding.SelectCustomFragmentBindingImpl;
import com.firstems.erp.databinding.SelectDateFragmentBindingImpl;
import com.firstems.erp.databinding.SelectDayFragmentBindingImpl;
import com.firstems.erp.databinding.SelectMonthFragmentBindingImpl;
import com.firstems.erp.databinding.SelectWeekFragmentBindingImpl;
import com.firstems.erp.databinding.SellFragmentBindingImpl;
import com.firstems.erp.databinding.ServiceContactsFragmentBindingImpl;
import com.firstems.erp.databinding.SignatureFragmentBindingImpl;
import com.firstems.erp.databinding.SignatureGirdFragmentBindingImpl;
import com.firstems.erp.databinding.SwitchShiftFragmentBindingImpl;
import com.firstems.erp.databinding.SwitchShiftInfoFragmentBindingImpl;
import com.firstems.erp.databinding.TabApprovedFragmentBindingImpl;
import com.firstems.erp.databinding.TabWaitApproveFragmentBindingImpl;
import com.firstems.erp.databinding.TicketBillPaymentRequestFragmentBindingImpl;
import com.firstems.erp.databinding.ViewImageFragmentBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACCOUNTFRAGMENT = 1;

  private static final int LAYOUT_ACTIVITYSWITCHSHIFT = 2;

  private static final int LAYOUT_ACTIVITYTEST = 3;

  private static final int LAYOUT_ADVANCEPROPOSALFORMFRAGMENT = 4;

  private static final int LAYOUT_ADVANCEPROPOSALLAYOUT = 5;

  private static final int LAYOUT_APPROVEDETAILFRAGMENT = 6;

  private static final int LAYOUT_APPROVEDFRAGMENT = 7;

  private static final int LAYOUT_ASKPERMISSIONFRAGMENT = 8;

  private static final int LAYOUT_ASKPERMISSIONINFOFRAGMENT = 9;

  private static final int LAYOUT_BILLPAYMENTFRAGMENT = 10;

  private static final int LAYOUT_BILLPAYMENTREQUESTFRAGMENT = 11;

  private static final int LAYOUT_BUSINESSREGISTRATIONFRAGMENT = 12;

  private static final int LAYOUT_BUSINESSREGISTRATIONINFOFRAGMENT = 13;

  private static final int LAYOUT_CHARTVIEWFRAGMENT = 14;

  private static final int LAYOUT_CONFIGFRAGMENT = 15;

  private static final int LAYOUT_DASHBOARDFRAGMENT = 16;

  private static final int LAYOUT_DATATABLEFRAGMENT = 17;

  private static final int LAYOUT_DEFAULTSIGNATUREFRAGMENT = 18;

  private static final int LAYOUT_DEPARTMENTFRAGMENT = 19;

  private static final int LAYOUT_DOCUMENTDETAILFRAGMENT = 20;

  private static final int LAYOUT_DOCUMENTFRAGMENT = 21;

  private static final int LAYOUT_DOCUMNETLISTFRAGMENT = 22;

  private static final int LAYOUT_EMPLOYEEFRAGMENT = 23;

  private static final int LAYOUT_FILEFRAGMENT = 24;

  private static final int LAYOUT_FILTERSIGNATUREFRAGMENT = 25;

  private static final int LAYOUT_HELPFRAGMENT = 26;

  private static final int LAYOUT_HOMEFRAGMENT = 27;

  private static final int LAYOUT_HOMEFRAGMENTTYPEFLAT = 28;

  private static final int LAYOUT_LAYOUTDASHBOARD = 29;

  private static final int LAYOUT_LAYOUTDASHBOARDCARDBOTTOM = 30;

  private static final int LAYOUT_LAYOUTDASHBOARDCARDTOP = 31;

  private static final int LAYOUT_LAYOUTDASHBOARDWAITTING = 32;

  private static final int LAYOUT_LAYOUTOPTIONAPPROVED = 33;

  private static final int LAYOUT_LAYOUTSELECTDATE = 34;

  private static final int LAYOUT_LAYOUTSIGNATUREGIRD = 35;

  private static final int LAYOUT_LAYOUTTOPDASHBOARD = 36;

  private static final int LAYOUT_LOAIDOITUONGNHANFRAGMENT = 37;

  private static final int LAYOUT_LOGINFRAGMENT = 38;

  private static final int LAYOUT_LOGINLAYOUTTYPEFLAT = 39;

  private static final int LAYOUT_MOREFRAGMENT = 40;

  private static final int LAYOUT_NATIONALFRAGMENT = 41;

  private static final int LAYOUT_PDFVIEWERFRAGMENT = 42;

  private static final int LAYOUT_PROCESSAPPROVEFRAGMENT = 43;

  private static final int LAYOUT_PROVINCEFRAGMENT = 44;

  private static final int LAYOUT_RELATEDFIELDSFRAGMENT = 45;

  private static final int LAYOUT_REPORTFRAGMENT = 46;

  private static final int LAYOUT_REPORTINGFRAGMENT = 47;

  private static final int LAYOUT_REVIEWPROCESSFRAGMENT = 48;

  private static final int LAYOUT_SELECTCOMPANYFRAGMENT = 49;

  private static final int LAYOUT_SELECTCUSTOMFRAGMENT = 50;

  private static final int LAYOUT_SELECTDATEFRAGMENT = 51;

  private static final int LAYOUT_SELECTDAYFRAGMENT = 52;

  private static final int LAYOUT_SELECTMONTHFRAGMENT = 53;

  private static final int LAYOUT_SELECTWEEKFRAGMENT = 54;

  private static final int LAYOUT_SELLFRAGMENT = 55;

  private static final int LAYOUT_SERVICECONTACTSFRAGMENT = 56;

  private static final int LAYOUT_SIGNATUREFRAGMENT = 57;

  private static final int LAYOUT_SIGNATUREGIRDFRAGMENT = 58;

  private static final int LAYOUT_SWITCHSHIFTFRAGMENT = 59;

  private static final int LAYOUT_SWITCHSHIFTINFOFRAGMENT = 60;

  private static final int LAYOUT_TABAPPROVEDFRAGMENT = 61;

  private static final int LAYOUT_TABWAITAPPROVEFRAGMENT = 62;

  private static final int LAYOUT_TICKETBILLPAYMENTREQUESTFRAGMENT = 63;

  private static final int LAYOUT_VIEWIMAGEFRAGMENT = 64;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(64);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.account_fragment, LAYOUT_ACCOUNTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.activity_switch_shift, LAYOUT_ACTIVITYSWITCHSHIFT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.activity_test, LAYOUT_ACTIVITYTEST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.advance_proposal_form_fragment, LAYOUT_ADVANCEPROPOSALFORMFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.advance_proposal_layout, LAYOUT_ADVANCEPROPOSALLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.approve_detail_fragment, LAYOUT_APPROVEDETAILFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.approved_fragment, LAYOUT_APPROVEDFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.ask_permission_fragment, LAYOUT_ASKPERMISSIONFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.ask_permission_info_fragment, LAYOUT_ASKPERMISSIONINFOFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.bill_payment_fragment, LAYOUT_BILLPAYMENTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.bill_payment_request_fragment, LAYOUT_BILLPAYMENTREQUESTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.business_registration_fragment, LAYOUT_BUSINESSREGISTRATIONFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.business_registration_info_fragment, LAYOUT_BUSINESSREGISTRATIONINFOFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.chart_view_fragment, LAYOUT_CHARTVIEWFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.config_fragment, LAYOUT_CONFIGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.dashboard_fragment, LAYOUT_DASHBOARDFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.data_table_fragment, LAYOUT_DATATABLEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.default_signature_fragment, LAYOUT_DEFAULTSIGNATUREFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.department_fragment, LAYOUT_DEPARTMENTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.document_detail_fragment, LAYOUT_DOCUMENTDETAILFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.document_fragment, LAYOUT_DOCUMENTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.documnet_list_fragment, LAYOUT_DOCUMNETLISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.employee_fragment, LAYOUT_EMPLOYEEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.file_fragment, LAYOUT_FILEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.filter_signature_fragment, LAYOUT_FILTERSIGNATUREFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.help_fragment, LAYOUT_HELPFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.home_fragment, LAYOUT_HOMEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.home_fragment_type_flat, LAYOUT_HOMEFRAGMENTTYPEFLAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_dashboard, LAYOUT_LAYOUTDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_dashboard_card_bottom, LAYOUT_LAYOUTDASHBOARDCARDBOTTOM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_dashboard_card_top, LAYOUT_LAYOUTDASHBOARDCARDTOP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_dashboard_waitting, LAYOUT_LAYOUTDASHBOARDWAITTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_option_approved, LAYOUT_LAYOUTOPTIONAPPROVED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_select_date, LAYOUT_LAYOUTSELECTDATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_signature_gird, LAYOUT_LAYOUTSIGNATUREGIRD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.layout_top_dashboard, LAYOUT_LAYOUTTOPDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.loai_doi_tuong_nhan_fragment, LAYOUT_LOAIDOITUONGNHANFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.login_fragment, LAYOUT_LOGINFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.login_layout_type_flat, LAYOUT_LOGINLAYOUTTYPEFLAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.more_fragment, LAYOUT_MOREFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.national_fragment, LAYOUT_NATIONALFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.p_d_f_viewer_fragment, LAYOUT_PDFVIEWERFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.process_approve_fragment, LAYOUT_PROCESSAPPROVEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.province_fragment, LAYOUT_PROVINCEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.related_fields_fragment, LAYOUT_RELATEDFIELDSFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.report_fragment, LAYOUT_REPORTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.reporting_fragment, LAYOUT_REPORTINGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.review_process_fragment, LAYOUT_REVIEWPROCESSFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_company_fragment, LAYOUT_SELECTCOMPANYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_custom_fragment, LAYOUT_SELECTCUSTOMFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_date_fragment, LAYOUT_SELECTDATEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_day_fragment, LAYOUT_SELECTDAYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_month_fragment, LAYOUT_SELECTMONTHFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.select_week_fragment, LAYOUT_SELECTWEEKFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.sell_fragment, LAYOUT_SELLFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.service_contacts_fragment, LAYOUT_SERVICECONTACTSFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.signature_fragment, LAYOUT_SIGNATUREFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.signature_gird_fragment, LAYOUT_SIGNATUREGIRDFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.switch_shift_fragment, LAYOUT_SWITCHSHIFTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.switch_shift_info_fragment, LAYOUT_SWITCHSHIFTINFOFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.tab_approved_fragment, LAYOUT_TABAPPROVEDFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.tab_wait_approve_fragment, LAYOUT_TABWAITAPPROVEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.ticket_bill_payment_request_fragment, LAYOUT_TICKETBILLPAYMENTREQUESTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.firstems.erp.R.layout.view_image_fragment, LAYOUT_VIEWIMAGEFRAGMENT);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACCOUNTFRAGMENT: {
        if ("layout/account_fragment_0".equals(tag)) {
          return new AccountFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for account_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSWITCHSHIFT: {
        if ("layout/activity_switch_shift_0".equals(tag)) {
          return new ActivitySwitchShiftBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_switch_shift is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTEST: {
        if ("layout/activity_test_0".equals(tag)) {
          return new ActivityTestBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_test is invalid. Received: " + tag);
      }
      case  LAYOUT_ADVANCEPROPOSALFORMFRAGMENT: {
        if ("layout/advance_proposal_form_fragment_0".equals(tag)) {
          return new AdvanceProposalFormFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for advance_proposal_form_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ADVANCEPROPOSALLAYOUT: {
        if ("layout/advance_proposal_layout_0".equals(tag)) {
          return new AdvanceProposalLayoutBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for advance_proposal_layout is invalid. Received: " + tag);
      }
      case  LAYOUT_APPROVEDETAILFRAGMENT: {
        if ("layout/approve_detail_fragment_0".equals(tag)) {
          return new ApproveDetailFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for approve_detail_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_APPROVEDFRAGMENT: {
        if ("layout/approved_fragment_0".equals(tag)) {
          return new ApprovedFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for approved_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ASKPERMISSIONFRAGMENT: {
        if ("layout/ask_permission_fragment_0".equals(tag)) {
          return new AskPermissionFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for ask_permission_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_ASKPERMISSIONINFOFRAGMENT: {
        if ("layout/ask_permission_info_fragment_0".equals(tag)) {
          return new AskPermissionInfoFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for ask_permission_info_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_BILLPAYMENTFRAGMENT: {
        if ("layout/bill_payment_fragment_0".equals(tag)) {
          return new BillPaymentFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for bill_payment_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_BILLPAYMENTREQUESTFRAGMENT: {
        if ("layout/bill_payment_request_fragment_0".equals(tag)) {
          return new BillPaymentRequestFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for bill_payment_request_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_BUSINESSREGISTRATIONFRAGMENT: {
        if ("layout/business_registration_fragment_0".equals(tag)) {
          return new BusinessRegistrationFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for business_registration_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_BUSINESSREGISTRATIONINFOFRAGMENT: {
        if ("layout/business_registration_info_fragment_0".equals(tag)) {
          return new BusinessRegistrationInfoFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for business_registration_info_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CHARTVIEWFRAGMENT: {
        if ("layout/chart_view_fragment_0".equals(tag)) {
          return new ChartViewFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for chart_view_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_CONFIGFRAGMENT: {
        if ("layout/config_fragment_0".equals(tag)) {
          return new ConfigFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for config_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DASHBOARDFRAGMENT: {
        if ("layout/dashboard_fragment_0".equals(tag)) {
          return new DashboardFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dashboard_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DATATABLEFRAGMENT: {
        if ("layout/data_table_fragment_0".equals(tag)) {
          return new DataTableFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for data_table_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DEFAULTSIGNATUREFRAGMENT: {
        if ("layout/default_signature_fragment_0".equals(tag)) {
          return new DefaultSignatureFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for default_signature_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DEPARTMENTFRAGMENT: {
        if ("layout/department_fragment_0".equals(tag)) {
          return new DepartmentFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for department_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DOCUMENTDETAILFRAGMENT: {
        if ("layout/document_detail_fragment_0".equals(tag)) {
          return new DocumentDetailFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for document_detail_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DOCUMENTFRAGMENT: {
        if ("layout/document_fragment_0".equals(tag)) {
          return new DocumentFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for document_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_DOCUMNETLISTFRAGMENT: {
        if ("layout/documnet_list_fragment_0".equals(tag)) {
          return new DocumnetListFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for documnet_list_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_EMPLOYEEFRAGMENT: {
        if ("layout/employee_fragment_0".equals(tag)) {
          return new EmployeeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for employee_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_FILEFRAGMENT: {
        if ("layout/file_fragment_0".equals(tag)) {
          return new FileFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for file_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_FILTERSIGNATUREFRAGMENT: {
        if ("layout/filter_signature_fragment_0".equals(tag)) {
          return new FilterSignatureFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for filter_signature_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_HELPFRAGMENT: {
        if ("layout/help_fragment_0".equals(tag)) {
          return new HelpFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for help_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_HOMEFRAGMENT: {
        if ("layout/home_fragment_0".equals(tag)) {
          return new HomeFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for home_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_HOMEFRAGMENTTYPEFLAT: {
        if ("layout/home_fragment_type_flat_0".equals(tag)) {
          return new HomeFragmentTypeFlatBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for home_fragment_type_flat is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTDASHBOARD: {
        if ("layout/layout_dashboard_0".equals(tag)) {
          return new LayoutDashboardBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_dashboard is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTDASHBOARDCARDBOTTOM: {
        if ("layout/layout_dashboard_card_bottom_0".equals(tag)) {
          return new LayoutDashboardCardBottomBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_dashboard_card_bottom is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTDASHBOARDCARDTOP: {
        if ("layout/layout_dashboard_card_top_0".equals(tag)) {
          return new LayoutDashboardCardTopBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_dashboard_card_top is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTDASHBOARDWAITTING: {
        if ("layout/layout_dashboard_waitting_0".equals(tag)) {
          return new LayoutDashboardWaittingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_dashboard_waitting is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTOPTIONAPPROVED: {
        if ("layout/layout_option_approved_0".equals(tag)) {
          return new LayoutOptionApprovedBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_option_approved is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTSELECTDATE: {
        if ("layout/layout_select_date_0".equals(tag)) {
          return new LayoutSelectDateBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_select_date is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTSIGNATUREGIRD: {
        if ("layout/layout_signature_gird_0".equals(tag)) {
          return new LayoutSignatureGirdBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_signature_gird is invalid. Received: " + tag);
      }
      case  LAYOUT_LAYOUTTOPDASHBOARD: {
        if ("layout/layout_top_dashboard_0".equals(tag)) {
          return new LayoutTopDashboardBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for layout_top_dashboard is invalid. Received: " + tag);
      }
      case  LAYOUT_LOAIDOITUONGNHANFRAGMENT: {
        if ("layout/loai_doi_tuong_nhan_fragment_0".equals(tag)) {
          return new LoaiDoiTuongNhanFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for loai_doi_tuong_nhan_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_LOGINFRAGMENT: {
        if ("layout/login_fragment_0".equals(tag)) {
          return new LoginFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for login_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_LOGINLAYOUTTYPEFLAT: {
        if ("layout/login_layout_type_flat_0".equals(tag)) {
          return new LoginLayoutTypeFlatBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for login_layout_type_flat is invalid. Received: " + tag);
      }
      case  LAYOUT_MOREFRAGMENT: {
        if ("layout/more_fragment_0".equals(tag)) {
          return new MoreFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for more_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_NATIONALFRAGMENT: {
        if ("layout/national_fragment_0".equals(tag)) {
          return new NationalFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for national_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PDFVIEWERFRAGMENT: {
        if ("layout/p_d_f_viewer_fragment_0".equals(tag)) {
          return new PDFViewerFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for p_d_f_viewer_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PROCESSAPPROVEFRAGMENT: {
        if ("layout/process_approve_fragment_0".equals(tag)) {
          return new ProcessApproveFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for process_approve_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_PROVINCEFRAGMENT: {
        if ("layout/province_fragment_0".equals(tag)) {
          return new ProvinceFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for province_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_RELATEDFIELDSFRAGMENT: {
        if ("layout/related_fields_fragment_0".equals(tag)) {
          return new RelatedFieldsFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for related_fields_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REPORTFRAGMENT: {
        if ("layout/report_fragment_0".equals(tag)) {
          return new ReportFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for report_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REPORTINGFRAGMENT: {
        if ("layout/reporting_fragment_0".equals(tag)) {
          return new ReportingFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for reporting_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_REVIEWPROCESSFRAGMENT: {
        if ("layout/review_process_fragment_0".equals(tag)) {
          return new ReviewProcessFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for review_process_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELECTCOMPANYFRAGMENT: {
        if ("layout/select_company_fragment_0".equals(tag)) {
          return new SelectCompanyFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_company_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELECTCUSTOMFRAGMENT: {
        if ("layout/select_custom_fragment_0".equals(tag)) {
          return new SelectCustomFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_custom_fragment is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_SELECTDATEFRAGMENT: {
        if ("layout/select_date_fragment_0".equals(tag)) {
          return new SelectDateFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_date_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELECTDAYFRAGMENT: {
        if ("layout/select_day_fragment_0".equals(tag)) {
          return new SelectDayFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_day_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELECTMONTHFRAGMENT: {
        if ("layout/select_month_fragment_0".equals(tag)) {
          return new SelectMonthFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_month_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELECTWEEKFRAGMENT: {
        if ("layout/select_week_fragment_0".equals(tag)) {
          return new SelectWeekFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for select_week_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SELLFRAGMENT: {
        if ("layout/sell_fragment_0".equals(tag)) {
          return new SellFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for sell_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SERVICECONTACTSFRAGMENT: {
        if ("layout/service_contacts_fragment_0".equals(tag)) {
          return new ServiceContactsFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for service_contacts_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SIGNATUREFRAGMENT: {
        if ("layout/signature_fragment_0".equals(tag)) {
          return new SignatureFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for signature_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SIGNATUREGIRDFRAGMENT: {
        if ("layout/signature_gird_fragment_0".equals(tag)) {
          return new SignatureGirdFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for signature_gird_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SWITCHSHIFTFRAGMENT: {
        if ("layout/switch_shift_fragment_0".equals(tag)) {
          return new SwitchShiftFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for switch_shift_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_SWITCHSHIFTINFOFRAGMENT: {
        if ("layout/switch_shift_info_fragment_0".equals(tag)) {
          return new SwitchShiftInfoFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for switch_shift_info_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_TABAPPROVEDFRAGMENT: {
        if ("layout/tab_approved_fragment_0".equals(tag)) {
          return new TabApprovedFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for tab_approved_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_TABWAITAPPROVEFRAGMENT: {
        if ("layout/tab_wait_approve_fragment_0".equals(tag)) {
          return new TabWaitApproveFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for tab_wait_approve_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_TICKETBILLPAYMENTREQUESTFRAGMENT: {
        if ("layout/ticket_bill_payment_request_fragment_0".equals(tag)) {
          return new TicketBillPaymentRequestFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for ticket_bill_payment_request_fragment is invalid. Received: " + tag);
      }
      case  LAYOUT_VIEWIMAGEFRAGMENT: {
        if ("layout/view_image_fragment_0".equals(tag)) {
          return new ViewImageFragmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for view_image_fragment is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(38);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "buttonCancel");
      sKeys.put(2, "buttonDefault");
      sKeys.put(3, "buttonSave");
      sKeys.put(4, "dateBegin");
      sKeys.put(5, "dateEnd");
      sKeys.put(6, "edtDomain");
      sKeys.put(7, "edtNumberDay");
      sKeys.put(8, "employDiLam");
      sKeys.put(9, "employDuocChamCong");
      sKeys.put(10, "filter");
      sKeys.put(11, "isEdit");
      sKeys.put(12, "isEditable");
      sKeys.put(13, "item");
      sKeys.put(14, "itemName");
      sKeys.put(15, "langValue");
      sKeys.put(16, "model");
      sKeys.put(17, "number");
      sKeys.put(18, "password");
      sKeys.put(19, "remember");
      sKeys.put(20, "switchBySession");
      sKeys.put(21, "switchDone");
      sKeys.put(22, "switchInfoApprove");
      sKeys.put(23, "switchInfoSignature");
      sKeys.put(24, "switchPendding");
      sKeys.put(25, "switchUnSignature");
      sKeys.put(26, "switchWork");
      sKeys.put(27, "title");
      sKeys.put(28, "titleApproveSetiing");
      sKeys.put(29, "titleAskPermistion");
      sKeys.put(30, "titleDomain");
      sKeys.put(31, "titleLanguage");
      sKeys.put(32, "titleNumberDay");
      sKeys.put(33, "titleSignatureSetting");
      sKeys.put(34, "titleSystemSetting");
      sKeys.put(35, "ui");
      sKeys.put(36, "urlLogo");
      sKeys.put(37, "username");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(64);

    static {
      sKeys.put("layout/account_fragment_0", com.firstems.erp.R.layout.account_fragment);
      sKeys.put("layout/activity_switch_shift_0", com.firstems.erp.R.layout.activity_switch_shift);
      sKeys.put("layout/activity_test_0", com.firstems.erp.R.layout.activity_test);
      sKeys.put("layout/advance_proposal_form_fragment_0", com.firstems.erp.R.layout.advance_proposal_form_fragment);
      sKeys.put("layout/advance_proposal_layout_0", com.firstems.erp.R.layout.advance_proposal_layout);
      sKeys.put("layout/approve_detail_fragment_0", com.firstems.erp.R.layout.approve_detail_fragment);
      sKeys.put("layout/approved_fragment_0", com.firstems.erp.R.layout.approved_fragment);
      sKeys.put("layout/ask_permission_fragment_0", com.firstems.erp.R.layout.ask_permission_fragment);
      sKeys.put("layout/ask_permission_info_fragment_0", com.firstems.erp.R.layout.ask_permission_info_fragment);
      sKeys.put("layout/bill_payment_fragment_0", com.firstems.erp.R.layout.bill_payment_fragment);
      sKeys.put("layout/bill_payment_request_fragment_0", com.firstems.erp.R.layout.bill_payment_request_fragment);
      sKeys.put("layout/business_registration_fragment_0", com.firstems.erp.R.layout.business_registration_fragment);
      sKeys.put("layout/business_registration_info_fragment_0", com.firstems.erp.R.layout.business_registration_info_fragment);
      sKeys.put("layout/chart_view_fragment_0", com.firstems.erp.R.layout.chart_view_fragment);
      sKeys.put("layout/config_fragment_0", com.firstems.erp.R.layout.config_fragment);
      sKeys.put("layout/dashboard_fragment_0", com.firstems.erp.R.layout.dashboard_fragment);
      sKeys.put("layout/data_table_fragment_0", com.firstems.erp.R.layout.data_table_fragment);
      sKeys.put("layout/default_signature_fragment_0", com.firstems.erp.R.layout.default_signature_fragment);
      sKeys.put("layout/department_fragment_0", com.firstems.erp.R.layout.department_fragment);
      sKeys.put("layout/document_detail_fragment_0", com.firstems.erp.R.layout.document_detail_fragment);
      sKeys.put("layout/document_fragment_0", com.firstems.erp.R.layout.document_fragment);
      sKeys.put("layout/documnet_list_fragment_0", com.firstems.erp.R.layout.documnet_list_fragment);
      sKeys.put("layout/employee_fragment_0", com.firstems.erp.R.layout.employee_fragment);
      sKeys.put("layout/file_fragment_0", com.firstems.erp.R.layout.file_fragment);
      sKeys.put("layout/filter_signature_fragment_0", com.firstems.erp.R.layout.filter_signature_fragment);
      sKeys.put("layout/help_fragment_0", com.firstems.erp.R.layout.help_fragment);
      sKeys.put("layout/home_fragment_0", com.firstems.erp.R.layout.home_fragment);
      sKeys.put("layout/home_fragment_type_flat_0", com.firstems.erp.R.layout.home_fragment_type_flat);
      sKeys.put("layout/layout_dashboard_0", com.firstems.erp.R.layout.layout_dashboard);
      sKeys.put("layout/layout_dashboard_card_bottom_0", com.firstems.erp.R.layout.layout_dashboard_card_bottom);
      sKeys.put("layout/layout_dashboard_card_top_0", com.firstems.erp.R.layout.layout_dashboard_card_top);
      sKeys.put("layout/layout_dashboard_waitting_0", com.firstems.erp.R.layout.layout_dashboard_waitting);
      sKeys.put("layout/layout_option_approved_0", com.firstems.erp.R.layout.layout_option_approved);
      sKeys.put("layout/layout_select_date_0", com.firstems.erp.R.layout.layout_select_date);
      sKeys.put("layout/layout_signature_gird_0", com.firstems.erp.R.layout.layout_signature_gird);
      sKeys.put("layout/layout_top_dashboard_0", com.firstems.erp.R.layout.layout_top_dashboard);
      sKeys.put("layout/loai_doi_tuong_nhan_fragment_0", com.firstems.erp.R.layout.loai_doi_tuong_nhan_fragment);
      sKeys.put("layout/login_fragment_0", com.firstems.erp.R.layout.login_fragment);
      sKeys.put("layout/login_layout_type_flat_0", com.firstems.erp.R.layout.login_layout_type_flat);
      sKeys.put("layout/more_fragment_0", com.firstems.erp.R.layout.more_fragment);
      sKeys.put("layout/national_fragment_0", com.firstems.erp.R.layout.national_fragment);
      sKeys.put("layout/p_d_f_viewer_fragment_0", com.firstems.erp.R.layout.p_d_f_viewer_fragment);
      sKeys.put("layout/process_approve_fragment_0", com.firstems.erp.R.layout.process_approve_fragment);
      sKeys.put("layout/province_fragment_0", com.firstems.erp.R.layout.province_fragment);
      sKeys.put("layout/related_fields_fragment_0", com.firstems.erp.R.layout.related_fields_fragment);
      sKeys.put("layout/report_fragment_0", com.firstems.erp.R.layout.report_fragment);
      sKeys.put("layout/reporting_fragment_0", com.firstems.erp.R.layout.reporting_fragment);
      sKeys.put("layout/review_process_fragment_0", com.firstems.erp.R.layout.review_process_fragment);
      sKeys.put("layout/select_company_fragment_0", com.firstems.erp.R.layout.select_company_fragment);
      sKeys.put("layout/select_custom_fragment_0", com.firstems.erp.R.layout.select_custom_fragment);
      sKeys.put("layout/select_date_fragment_0", com.firstems.erp.R.layout.select_date_fragment);
      sKeys.put("layout/select_day_fragment_0", com.firstems.erp.R.layout.select_day_fragment);
      sKeys.put("layout/select_month_fragment_0", com.firstems.erp.R.layout.select_month_fragment);
      sKeys.put("layout/select_week_fragment_0", com.firstems.erp.R.layout.select_week_fragment);
      sKeys.put("layout/sell_fragment_0", com.firstems.erp.R.layout.sell_fragment);
      sKeys.put("layout/service_contacts_fragment_0", com.firstems.erp.R.layout.service_contacts_fragment);
      sKeys.put("layout/signature_fragment_0", com.firstems.erp.R.layout.signature_fragment);
      sKeys.put("layout/signature_gird_fragment_0", com.firstems.erp.R.layout.signature_gird_fragment);
      sKeys.put("layout/switch_shift_fragment_0", com.firstems.erp.R.layout.switch_shift_fragment);
      sKeys.put("layout/switch_shift_info_fragment_0", com.firstems.erp.R.layout.switch_shift_info_fragment);
      sKeys.put("layout/tab_approved_fragment_0", com.firstems.erp.R.layout.tab_approved_fragment);
      sKeys.put("layout/tab_wait_approve_fragment_0", com.firstems.erp.R.layout.tab_wait_approve_fragment);
      sKeys.put("layout/ticket_bill_payment_request_fragment_0", com.firstems.erp.R.layout.ticket_bill_payment_request_fragment);
      sKeys.put("layout/view_image_fragment_0", com.firstems.erp.R.layout.view_image_fragment);
    }
  }
}
