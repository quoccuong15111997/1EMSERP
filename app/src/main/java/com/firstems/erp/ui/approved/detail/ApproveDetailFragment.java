package com.firstems.erp.ui.approved.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoApiResponse;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoItemApiResponse;
import com.firstems.erp.api.model.response.department.DepartmentItemApiResponse;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.callback.ApprovedModelLoadCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.databinding.ApproveDetailFragmentBinding;
import com.firstems.erp.enums.TypeApproved;
import com.firstems.erp.enums.TypeSelect;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.department.DepartmentActivity;
import com.firstems.erp.ui.shared.employee.EmployeeActivity;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.firstems.erp.common.Constant.NANE_PUT_DEPARTMENT_LIST;
import static com.firstems.erp.common.Constant.NANE_PUT_TYPE_SELECT;

public class ApproveDetailFragment extends CommonFragment {
    
    private ApproveDetailViewModel mViewModel;
    private ApproveDetailFragmentBinding binding;
    private TextView txtTitle;
    RecyclerView recyclerViewProgress;
    private String dcmnCode="";
    private String keyCode = "";
    private List<Employee> employeeListThamKhao;
    private int CODE_ADD_EMPLOYEE = 1;
    private int CODE_ADD_DEPARTMENT = 2;
    private List<DepartmentItemApiResponse> departmentList;
    private TypeApproved typeApproved = TypeApproved.APPROVED;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.approve_detail_fragment, container, false);
        addControls();
        addEvents();
        setText();
        return binding.getRoot();
    }
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.layoutTitleNoiDung.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.layoutTitleNoiDung.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void setText() {
        binding.textView2.setText(SharedPreferencesManager.getSystemLabel(150) /*Nội dung chứng từ*/);
        binding.layoutOption.txtTitleApprove.setText(SharedPreferencesManager.getSystemLabel(9) /*Phê duyệt*/);
        binding.txtTitleUser.setText(SharedPreferencesManager.getSystemLabel(151) /*Người trình ký*/);
        binding.txtTitleRoom.setText(SharedPreferencesManager.getSystemLabel(152) /*Bộ phận*/);
        binding.txtTitleDate.setText(SharedPreferencesManager.getSystemLabel(153) /*Ngày chứng từ*/);
        binding.txtTitleMoney.setText(SharedPreferencesManager.getSystemLabel(131) /*Số tiền*/);
        binding.txtTitleContent.setText(SharedPreferencesManager.getSystemLabel(154) /*Diễn giải*/);
        binding.layoutOption.radOk.setText(SharedPreferencesManager.getSystemLabel(155) /*Đồng ý duyệt*/);
        binding.layoutOption.radCancel.setText(SharedPreferencesManager.getSystemLabel(158) /*Hủy bỏ*/);
        binding.layoutOption.radConsultation.setText(SharedPreferencesManager.getSystemLabel(157) /*Tham khảo ý kiến*/);
        binding.layoutOption.radRequestInfomation.setText(SharedPreferencesManager.getSystemLabel(156) /*Yêu cầu bổ sung*/);
        binding.layoutOption.radAction.setText(SharedPreferencesManager.getSystemLabel(160) /*Thực hiện*/);
        binding.layoutOption.radCheck.setText(SharedPreferencesManager.getSystemLabel(159) /*Kiểm tra*/);
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(161) /*Ghi chú*/);
        binding.btnSend.setText(SharedPreferencesManager.getSystemLabel(162) /*Gửi thông tin*/);
        binding.layoutOption.txtPhongBan.setText(SharedPreferencesManager.getSystemLabel(165) /*Chọn phòng ban*/);
        binding.layoutOption.txtNhanVien.setText(SharedPreferencesManager.getSystemLabel(166) /*Chọn nhân viên*/);
    }
    
    private void addEvents() {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layoutOption.radOk.isChecked()
                        || binding.layoutOption.radAction.isChecked()
                        || binding.layoutOption.radCheck.isChecked()
                        || binding.layoutOption.radConsultation.isChecked()
                        || binding.layoutOption.radCancel.isChecked()
                        || binding.layoutOption.radRequestInfomation.isChecked()){
                    if (binding.layoutOption.radConsultation.isChecked() && employeeListThamKhao.size()==0){
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(167) /*Vui lòng chọn nhân viên tham khảo ý kiến*/);
                    }
                    else {
                        String employeeLis = "";
                        if (binding.layoutOption.radConsultation.isChecked()){
                            for (int i= 0;i<employeeListThamKhao.size();i++){
                                Employee employee = employeeListThamKhao.get(i);
                                if (i==employeeListThamKhao.size()-1){
                                    employeeLis+=employee.getItemCode();
                                }
                                else
                                    employeeLis+=employee.getItemCode()+",";
                            }
                        }
                        showLoadingNonMessDialog();
                        String progressCode = findProgressCode();
                        DataNetworkProvider
                                .getInstance()
                                .doApproveDocument(dcmnCode, keyCode, progressCode, binding.edtInfo.getText().toString(), employeeLis, new DataApiCallback() {
                                    @Override
                                    public void onDataApi(String jsonAPI) {
                                        DataConvertProvider.getInstance().convertJsonToObject(jsonAPI, new ApiResponse(), new ConvertJsonCallback() {
                                            @Override
                                            public void onConvertSuccess(Object obj) {
                                                binding.lParentContent.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        loadingNonMessDialog.dismiss();
                                                        ApiResponse apiResponse = (ApiResponse) obj;
                                                        if (apiResponse.isRETNCODE()){
                                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                                        }
                                                        else {
                                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                                        }
                                                    }
                                                }, 700);
                                            }
                                        });
                                    }
                                    
                                    @Override
                                    public void onApiLoadFail(String mess) {
                                        System.out.println(mess);
                                        showOutTOKEN();
                                    }
                                });
                    }
                }
                else {
                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(163) /*Vui lòng chọn loại kiểm duyệt*/);
                }
            }
        });
        binding.layoutOption.txtPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layoutOption.radConsultation.isChecked()){
                    Intent intent = new Intent(getActivity(), DepartmentActivity.class);
                    intent.putExtra(NANE_PUT_TYPE_SELECT, TypeSelect.MULTI);
                    intent.putExtra(NANE_PUT_DEPARTMENT_LIST, (Serializable) departmentList);
                    startActivityForResult(intent,CODE_ADD_DEPARTMENT);
                }
            }
        });
        binding.layoutOption.txtNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layoutOption.radConsultation.isChecked()){
                    Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                    intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_ACTION_SELECT_EMPLOYEE,false);
                    intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_LIST_EMPLOYEE, (Serializable) employeeListThamKhao);
                    intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(76));
                    String para ="";
                    for (int i=0;i<departmentList.size();i++){
                        DepartmentItemApiResponse itemApiResponse = departmentList.get(i);
                        if (i==departmentList.size()-1){
                            para+=itemApiResponse.getItemCode();
                        }
                        else {
                            para+=itemApiResponse.getItemCode()+",";
                        }
                    }
                    
                    intent.putExtra(Constant.NANE_PUT_PARA_EMPLOYEE,para);
                    startActivityForResult(intent,CODE_ADD_EMPLOYEE);
                }
            }
        });
        binding.include9.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        
        binding.layoutOption.radOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("100000");
            }
        });
        binding.layoutOption.radRequestInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("010000");
            }
        });
        binding.layoutOption.radConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("001000");
            }
        });
        binding.layoutOption.radCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("000100");
            }
        });
        binding.layoutOption.radCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("000010");
            }
        });
        binding.layoutOption.radAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheck("000001");
            }
        });
    }
    
    private String findProgressCode() {
        if (binding.layoutOption.radOk.isChecked())
            return "002";
        else if (binding.layoutOption.radRequestInfomation.isChecked())
            return "003";
        else if (binding.layoutOption.radConsultation.isChecked())
            return "005";
        else if (binding.layoutOption.radCancel.isChecked())
            return "004";
        else if (binding.layoutOption.radAction.isChecked())
            return "006";
        else if (binding.layoutOption.radCheck.isChecked())
            return "008";
        else
            return "";
    }
    
    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83),SharedPreferencesManager.getSystemLabel(63));
        showLoadingNonMessDialog();
        
        txtTitle = binding.include9.findViewById(R.id.txtTitle);
        
        Intent intent = getActivity().getIntent();
        dcmnCode = intent.getStringExtra(Constant.NAME_PUT_APPROVED_INFO_DCMNCODE);
        keyCode = intent.getStringExtra(Constant.NAME_PUT_APPROVED_INFO_KEY_CODE);
        typeApproved = (TypeApproved) intent.getSerializableExtra(Constant.NANE_PUT_TYPE_APPROVED);
        employeeListThamKhao = new ArrayList<>();
        
        departmentList = new ArrayList<>();
        
        if (typeApproved == TypeApproved.APPROVED){
            binding.layoutOption.constraintLayout3.setVisibility(View.GONE);
            binding.btnSend.setVisibility(View.GONE);
            binding.linearLayoutNoidungDeNghi.setVisibility(View.GONE);
        }
        else {
            binding.layoutOption.constraintLayout3.setVisibility(View.VISIBLE);
            binding.btnSend.setVisibility(View.VISIBLE);
            binding.linearLayoutNoidungDeNghi.setVisibility(View.VISIBLE);
        }
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ApproveDetailViewModel.class);
        
        mViewModel.getDataApproveDetail(dcmnCode, keyCode, new ApprovedModelLoadCallback() {
            @Override
            public void onDataLoaded(MutableLiveData<ApproveInfoApiResponse> mutableLiveData) {
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<ApproveInfoApiResponse>() {
                    @Override
                    public void onChanged(ApproveInfoApiResponse approveInfoApiResponse) {
                        ApproveInfoItemApiResponse item = approveInfoApiResponse.getResponseList().get(0);
                        
                        checkPermision(item);
                        
                        binding.txtUserName.setText(item.getEmpSgName());
                        binding.txtRoom.setText(item.getDpsgName());
                        binding.txtDate.setText(Util.formatDate(item.getMainDate()));
                        binding.txtMoney.setText(Util.convertToCurrencyVN(item.getCurrentValue()));
                        binding.txtTitleMoney.setText(item.getLabelName());
                        binding.txtContent.setText(item.getNoteSgst());
                        txtTitle.setText(item.getDcmnName()+" - "+item.getMainCode());
                        
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_progress, new ReviewProcessFragment(item.getDcmnCode(),item.getKeyCode()))
                                .commit();
                        
                        binding.include9.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingNonMessDialog.dismiss();
                            }
                        },700);
                    }
                });
            }
            
            @Override
            public void onDataLoadFail(String failMessage) {
                binding.include9.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingNonMessDialog.dismiss();
                    }
                },700);
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(164) /*Chứng từ không tồn tại*/);
            }
    
            @Override
            public void onServerFail() {
                showOutTOKEN();
            }
        });
    }
    
    private void checkPermision(ApproveInfoItemApiResponse item) {
        if (item.getpRCSLIST().equals("%")){
            binding.layoutOption.radOk.setEnabled(true);
            binding.layoutOption.radConsultation.setEnabled(true);
            binding.layoutOption.radRequestInfomation.setEnabled(true);
            binding.layoutOption.radCheck.setEnabled(true);
            binding.layoutOption.radAction.setEnabled(true);
            binding.layoutOption.radCancel.setEnabled(true);
        }
        else {
            binding.layoutOption.radOk.setEnabled(item.getpRCSLIST().contains("002"));
            binding.layoutOption.radRequestInfomation.setEnabled(item.getpRCSLIST().contains("003"));
            binding.layoutOption.radConsultation.setEnabled(item.getpRCSLIST().contains("005"));
            binding.layoutOption.radCancel.setEnabled(item.getpRCSLIST().contains("004"));
            binding.layoutOption.radAction.setEnabled(item.getpRCSLIST().contains("006"));
            binding.layoutOption.radCheck.setEnabled(item.getpRCSLIST().contains("008"));
        }
        binding.layoutOption.radConsultation.setEnabled((item.getSendOptn() & 1) >0);
    }
    
    private void updateCheck(String bit){
        char [] arrChar = bit.toCharArray();
        if (arrChar[0]=='1'){
            binding.layoutOption.radOk.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.GONE);
        }
        else
            binding.layoutOption.radOk.setChecked(false);
        if (arrChar[1]=='1'){
            binding.layoutOption.radRequestInfomation.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.GONE);
        }
        else
            binding.layoutOption.radRequestInfomation.setChecked(false);
        if (arrChar[2]=='1'){
            binding.layoutOption.radConsultation.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.VISIBLE);
        }
        else{
            binding.layoutOption.radConsultation.setChecked(false);
        }
        if (arrChar[3]=='1'){
            binding.layoutOption.radCancel.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.GONE);
            
        }
        else
            binding.layoutOption.radCancel.setChecked(false);
        if (arrChar[4] == '1'){
            binding.layoutOption.radCheck.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.GONE);
        }
        else
            binding.layoutOption.radCheck.setChecked(false);
        if (arrChar[5] == '1'){
            binding.layoutOption.radAction.setChecked(true);
            binding.layoutOption.layoutSelectEmployee.setVisibility(View.GONE);
        }
        else
            binding.layoutOption.radAction.setChecked(false);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD_DEPARTMENT && resultCode == Activity.RESULT_OK){
            departmentList.clear();
            List<DepartmentItemApiResponse> responseList = (List<DepartmentItemApiResponse>) data.getSerializableExtra(NANE_PUT_DEPARTMENT_LIST);
            departmentList.addAll(responseList);
            String s ="";
            for (DepartmentItemApiResponse item : departmentList){
                s+=item.getItemName()+",";
            }
            if (s.equals("")){
                binding.layoutOption.txtPhongBan.setText(SharedPreferencesManager.getSystemLabel(165) /*Chọn phòng ban*/);
            }
            else {
                binding.layoutOption.txtPhongBan.setText(s);
            }
        }
        if (requestCode==CODE_ADD_EMPLOYEE && resultCode== Activity.RESULT_OK){
            List<Employee> employees = (List<Employee>) data.getSerializableExtra(Constant.NAME_PUT_LIST_EMPLOYEE);
            employeeListThamKhao.clear();
            employeeListThamKhao.addAll(employees);
            String s= "";
            for (Employee employee : employeeListThamKhao){
                s+=employee.getItemName()+",";
            }
            if (s.equals("")){
                binding.layoutOption.txtNhanVien.setText(SharedPreferencesManager.getSystemLabel(166) /*Chọn nhân viên*/);
            }
            else {
                binding.layoutOption.txtNhanVien.setText(s);
            }
        }
    }
}