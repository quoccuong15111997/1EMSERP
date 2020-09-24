package com.firstems.erp.ui.signature.askpermission.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.SingelChoiseDialogCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.AskPermissionInfoFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.helper.validation.ValidationData;
import com.firstems.erp.model.approved.Approved;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.employee.EmployeeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AskPermissionInfoFragment extends CommonFragment {

    private AskPermissionInfoViewModel mViewModel;
    private View view;
    private AskPermissionInfoFragmentBinding binding;
    private TextView txtTitle;
    private int CODE_OPEN_EMPLOYEE_1 = 1;
    private int CODE_OPEN_EMPLOYEE_2 = 2;
    private Date dateBegin, dateEnd;
    private TimekeepingTypeDC typeDCSang, typeDCChieu, typeDCToi;
    private List<TimekeepingTypeDC> timekeepingTypeDCList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.ask_permission_info_fragment,container,false);
        view=binding.getRoot();

        addControls();
        addEvents();
        setText();
        return view;
    }

    private void setText() {
        binding.txtTitleDateBegin.setText(SharedPreferencesManager.getSystemLabel(32));
        binding.txtTitleDateEnd.setText(SharedPreferencesManager.getSystemLabel(33));
        binding.txtTitleEmployee.setText(SharedPreferencesManager.getSystemLabel(45));
        binding.chkMorning.setText(SharedPreferencesManager.getSystemLabel(36));
        binding.chkAfternoon.setText(SharedPreferencesManager.getSystemLabel(37));
        binding.chkEverning.setText(SharedPreferencesManager.getSystemLabel(38));
        binding.txtTitleLoaiChamCongSang.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.txtTitleLoaiChamCongChieu.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.txtTitleLoaiChamCongToi.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.btnDone.setText(SharedPreferencesManager.getSystemLabel(40));
    }

    private void addEvents() {
        binding.include3.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.include3.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        binding.edtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                intent.putExtra(Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(41));
                intent.putExtra(Constant.NAME_PUT_EMPLOYEE,binding.getEmployDiLam());
                startActivityForResult(intent,CODE_OPEN_EMPLOYEE_1);
                AnimationHelper.getInstance().setAnimationBottomToTop(getActivity());
            }
        });
        binding.edtLoaiChamCongSang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType(binding.edtLoaiChamCongSang, typeDCSang);
            }
        });
        binding.edtLoaiChamCongToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType(binding.edtLoaiChamCongToi,typeDCChieu);
            }
        });
        binding.edtLoaiChamCongChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectType(binding.edtLoaiChamCongChieu,typeDCToi);
            }
        });
        binding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long minDate, maxDate;
                minDate = System.currentTimeMillis();
                if (dateEnd==null){
                    maxDate = 0;
                }
                else
                    maxDate = dateEnd.getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate,maxDate,getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin=date;
                        binding.txtDateBegin.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
        binding.layoutDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long minDate, maxDate;
                maxDate = 0;

                if (dateBegin==null){
                    minDate = System.currentTimeMillis();
                }
                else
                    minDate = dateBegin.getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate,maxDate,getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd = date;
                        binding.txtDateEnd.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
    }

    private void doSave() {
        if (!ValidationData.isBlank(binding.txtDateBegin)){
            if (!ValidationData.isBlank(binding.txtDateEnd)){
                if (!ValidationData.isBlank(binding.edtEmployee)){
                    if (!ValidationData.isBlank(binding.edtEmployee)){
                        if (binding.chkMorning.isChecked() || binding.chkAfternoon.isChecked()
                                || binding.chkEverning.isChecked()){
                            if (binding.chkMorning.isChecked()){
                                if (ValidationData.isBlank(binding.edtLoaiChamCongSang)){
                                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công sáng");
                                    return;
                                }
                            }
                            if (binding.chkAfternoon.isChecked()){
                                if (ValidationData.isBlank(binding.edtLoaiChamCongChieu)){
                                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công chiều");
                                    return;
                                }
                            }
                            if (binding.chkEverning.isChecked()){
                                if (ValidationData.isBlank(binding.edtLoaiChamCongToi)){
                                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công tối");
                                    return;
                                }
                            }

                        }
                        else {
                            SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công");
                            return;
                        }
                    }
                    else {
                        // Nhan vien duoc cham cong is emty
                        SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn nhân viên được chấm công");
                        return;
                    }
                }
                else {
                    // Nhan vien di lam is emty
                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn nhân viên đi làm");
                    return;
                }
            }
            else {
                // Date end is blank
                SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn đến ngày");
                return;
            }
        }
        else {
            // Date begin is blank
            SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn từ ngày");
            return;
        }
        Approved approved= new Approved();
        approved.setEmployeeDiLam(binding.getEmployDiLam());
        approved.setEmployeeChamCong(binding.getEmployDuocChamCong());
        approved.setMorning(binding.chkMorning.isChecked());
        approved.setAfternoon(binding.chkAfternoon.isChecked());
        approved.setEverning(binding.chkEverning.isChecked());
        approved.setContentMornig(typeDCSang);
        approved.setContentAfternoon(typeDCChieu);
        approved.setContentEverning(typeDCToi);
        approved.setDateEnd(dateEnd);
        approved.setDateBegin(dateBegin);

        Intent intent= new Intent();
        intent.putExtra(Constant.NAME_PUT_APPROVED,approved);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();

    }
    private void selectType(TextView textView, TimekeepingTypeDC timekeepingTypeDC){
        showSingelChoiseDialod(timekeepingTypeDCList, new SingelChoiseDialogCallback() {
            @Override
            public void itemSelected(int postion) {
                textView.setText(timekeepingTypeDCList.get(postion).getItemName());
                TimekeepingTypeDC dc = timekeepingTypeDCList.get(postion);
                timekeepingTypeDC.setItemName(dc.getItemName());
                timekeepingTypeDC.setItemCode(dc.getItemCode());
            }
        });
    }

    private void addControls() {
        txtTitle=binding.include3.findViewById(R.id.txtTitle);
        Intent intent = getActivity().getIntent();
        Approved approvedEdit = (Approved) intent.getSerializableExtra(Constant.NAME_PUT_APPROVED);
        if (approvedEdit!=null){
            binding.setModel(approvedEdit);
            binding.setEmployDiLam(approvedEdit.getEmployeeDiLam());
            binding.setEmployDuocChamCong(approvedEdit.getEmployeeChamCong());
            dateEnd = approvedEdit.getDateEnd();
            dateBegin = approvedEdit.getDateBegin();
            binding.setDateBegin(displayDate(dateBegin));
            binding.setDateEnd(displayDate(dateEnd));
        }
        else {
            binding.setDateBegin("");
            binding.setDateEnd("");
        }
        timekeepingTypeDCList= new ArrayList<>();
        typeDCSang= new TimekeepingTypeDC();
        typeDCChieu= new TimekeepingTypeDC();
        typeDCToi= new TimekeepingTypeDC();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AskPermissionInfoViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        mViewModel.getEmploy_di_lam().observe(getViewLifecycleOwner(), new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                binding.setEmployDiLam(employee);
            }
        });
        mViewModel.getEmploy_cham_cong().observe(getViewLifecycleOwner(), new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                binding.setEmployDuocChamCong(employee);
            }
        });
        mViewModel.getLiveDataListLoaiChamCong().observe(getViewLifecycleOwner(), new Observer<List<TimekeepingTypeDC>>() {
            @Override
            public void onChanged(List<TimekeepingTypeDC> timekeepingTypeDCS) {
                timekeepingTypeDCList.clear();
                timekeepingTypeDCList.addAll(timekeepingTypeDCS);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE_OPEN_EMPLOYEE_1 && resultCode== Activity.RESULT_OK){
            Employee employee = (Employee) data.getSerializableExtra(Constant.NAME_PUT_EMPLOYEE);
            mViewModel.setEmploy_di_lam(employee);
        }
        else if (requestCode==CODE_OPEN_EMPLOYEE_2 && resultCode== Activity.RESULT_OK){
            Employee employee = (Employee) data.getSerializableExtra(Constant.NAME_PUT_EMPLOYEE);
            mViewModel.setEmploy_cham_cong(employee);
        }
    }
    private String displayDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
}