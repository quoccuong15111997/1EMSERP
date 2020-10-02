package com.firstems.erp.ui.signature.switchshift.info;

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
import com.firstems.erp.databinding.SwitchShiftInfoFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.helper.validation.ValidationData;
import com.firstems.erp.model.switchshift.SwitchShift;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.employee.EmployeeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SwitchShiftInfoFragment extends CommonFragment {

    private SwitchShiftInfoViewModel mViewModel;
    private View view;
    private SwitchShiftInfoFragmentBinding switchShiftInfoFragmentBinding;
    private TextView txtTitle;
    private int CODE_OPEN_EMPLOYEE_1 = 1;
    private int CODE_OPEN_EMPLOYEE_2 = 2;
    private Date dateBegin, dateEnd;
    private TimekeepingTypeDC timekeepingSang,timekeepingChieu, timekeepingToi;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        switchShiftInfoFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.switch_shift_info_fragment,container,false);
        view=switchShiftInfoFragmentBinding.getRoot();

        addControls();
        addEvents();
        setText();
        return view;
    }

    private void setText() {
        switchShiftInfoFragmentBinding.txtTitleDateBegin.setText(SharedPreferencesManager.getSystemLabel(32));
        switchShiftInfoFragmentBinding.txtTitleDateEnd.setText(SharedPreferencesManager.getSystemLabel(33));
        switchShiftInfoFragmentBinding.txtTitleEmployee.setText(SharedPreferencesManager.getSystemLabel(34));
        switchShiftInfoFragmentBinding.txtTitleNhanVienDuocChamCong.setText(SharedPreferencesManager.getSystemLabel(35));
        switchShiftInfoFragmentBinding.chkMorning.setText(SharedPreferencesManager.getSystemLabel(36));
        switchShiftInfoFragmentBinding.txtTitleLoaiChamCongSang.setText(SharedPreferencesManager.getSystemLabel(39));
        switchShiftInfoFragmentBinding.chkAfternoon.setText(SharedPreferencesManager.getSystemLabel(37));
        switchShiftInfoFragmentBinding.txtTitleLoaiChamCongChieu.setText(SharedPreferencesManager.getSystemLabel(39));
        switchShiftInfoFragmentBinding.chkEverning.setText(SharedPreferencesManager.getSystemLabel(38));
        switchShiftInfoFragmentBinding.txtTitleLoaiChamCongToi.setText(SharedPreferencesManager.getSystemLabel(39));
        switchShiftInfoFragmentBinding.btnDone.setText(SharedPreferencesManager.getSystemLabel(40));
    }

    private void addEvents() {
        switchShiftInfoFragmentBinding.include3.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        switchShiftInfoFragmentBinding.include3.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        switchShiftInfoFragmentBinding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        switchShiftInfoFragmentBinding.edtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                intent.putExtra(Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(41));
                intent.putExtra(Constant.NAME_PUT_EMPLOYEE,switchShiftInfoFragmentBinding.getEmployDiLam());
                startActivityForResult(intent,CODE_OPEN_EMPLOYEE_1);
            }
        });
        switchShiftInfoFragmentBinding.edtNhanVienDuocChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), EmployeeActivity.class);
                intent.putExtra(Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(42));
                intent.putExtra(Constant.NAME_PUT_EMPLOYEE,switchShiftInfoFragmentBinding.getEmployDuocChamCong());
                startActivityForResult(intent, CODE_OPEN_EMPLOYEE_2);
            }
        });
        switchShiftInfoFragmentBinding.edtLoaiChamCongSang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timekeepingSang == null){
                    timekeepingSang = new TimekeepingTypeDC();
                }
                selectType(switchShiftInfoFragmentBinding.edtLoaiChamCongSang,timekeepingSang);
            }
        });
        switchShiftInfoFragmentBinding.edtLoaiChamCongToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timekeepingToi == null){
                    timekeepingToi = new TimekeepingTypeDC();
                }
                selectType(switchShiftInfoFragmentBinding.edtLoaiChamCongToi,timekeepingToi);
            }
        });
        switchShiftInfoFragmentBinding.edtLoaiChamCongChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timekeepingChieu == null){
                    timekeepingChieu = new TimekeepingTypeDC();
                }
                selectType(switchShiftInfoFragmentBinding.edtLoaiChamCongChieu,timekeepingChieu);
            }
        });
        switchShiftInfoFragmentBinding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
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
                        switchShiftInfoFragmentBinding.txtDateBegin.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
        switchShiftInfoFragmentBinding.layoutDateEnd.setOnClickListener(new View.OnClickListener() {
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
                        switchShiftInfoFragmentBinding.txtDateEnd.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
    }

    private void doSave() {
        if (!ValidationData.isBlank(switchShiftInfoFragmentBinding.txtDateBegin)){
            if (!ValidationData.isBlank(switchShiftInfoFragmentBinding.txtDateEnd)){
                if (!ValidationData.isBlank(switchShiftInfoFragmentBinding.edtEmployee)){
                    if (!ValidationData.isBlank(switchShiftInfoFragmentBinding.edtNhanVienDuocChamCong)){
                        if (switchShiftInfoFragmentBinding.chkMorning.isChecked() || switchShiftInfoFragmentBinding.chkAfternoon.isChecked()
                                || switchShiftInfoFragmentBinding.chkEverning.isChecked()){
                            if (switchShiftInfoFragmentBinding.chkMorning.isChecked()){
                                if (ValidationData.isBlank(switchShiftInfoFragmentBinding.edtLoaiChamCongSang)){
                                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công sáng");
                                    return;
                                }
                            }
                            if (switchShiftInfoFragmentBinding.chkAfternoon.isChecked()){
                                if (ValidationData.isBlank(switchShiftInfoFragmentBinding.edtLoaiChamCongChieu)){
                                    SnackbarHelper.getInstance().snackBarIconError(getView(),"Vui lòng chọn loại chấm công chiều");
                                    return;
                                }
                            }
                            if (switchShiftInfoFragmentBinding.chkEverning.isChecked()){
                                if (ValidationData.isBlank(switchShiftInfoFragmentBinding.edtLoaiChamCongToi)){
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
        
        SwitchShift switchShift= new SwitchShift();
        switchShift.setEmployeeDiLam(switchShiftInfoFragmentBinding.getEmployDiLam());
        switchShift.setEmployeeChamCong(switchShiftInfoFragmentBinding.getEmployDuocChamCong());
        switchShift.setMorning(switchShiftInfoFragmentBinding.chkMorning.isChecked());
        switchShift.setAfternoon(switchShiftInfoFragmentBinding.chkAfternoon.isChecked());
        switchShift.setEverning(switchShiftInfoFragmentBinding.chkEverning.isChecked());
        if (switchShift.isMorning()){
            switchShift.setContentMornig(timekeepingSang);
        }
        if (switchShift.isAfternoon()){
            switchShift.setContentAfternoon(timekeepingChieu);
        }
        if (switchShift.isEverning()){
            switchShift.setContentEverning(timekeepingToi);
        }
        switchShift.setDateEnd(dateEnd);
        switchShift.setDateBegin(dateBegin);

        Intent intent= new Intent();
        intent.putExtra(Constant.NAME_PUT_SWITCH_SHIFT,switchShift);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
        AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());

    }
    private void selectType(TextView textView,final TimekeepingTypeDC timekeeping){
        showSingelChoiseDialod(mViewModel.getLiveDataListLoaiChamCong().getValue(), new SingelChoiseDialogCallback() {
            @Override
            public void itemSelected(int postion) {
                TimekeepingTypeDC typeDC = mViewModel.getLiveDataListLoaiChamCong().getValue().get(postion);
                timekeeping.setItemCode(typeDC.getItemCode());
                timekeeping.setItemName(typeDC.getItemName());
                textView.setText(timekeeping.getItemName());
            }
        });
    }

    private void addControls() {
        txtTitle=switchShiftInfoFragmentBinding.include3.findViewById(R.id.txtTitle);
        Intent intent = getActivity().getIntent();
        SwitchShift switchShiftEdit = (SwitchShift) intent.getSerializableExtra(Constant.NAME_PUT_SWITCH_SHIFT);

        timekeepingSang= new TimekeepingTypeDC();
        timekeepingChieu= new TimekeepingTypeDC();
        timekeepingToi = new TimekeepingTypeDC();

        if (switchShiftEdit!=null){
            switchShiftInfoFragmentBinding.setModel(switchShiftEdit);
            switchShiftInfoFragmentBinding.setEmployDiLam(switchShiftEdit.getEmployeeDiLam());
            switchShiftInfoFragmentBinding.setEmployDuocChamCong(switchShiftEdit.getEmployeeChamCong());
            dateEnd = switchShiftEdit.getDateEnd();
            dateBegin = switchShiftEdit.getDateBegin();
            switchShiftInfoFragmentBinding.setDateBegin(displayDate(dateBegin));
            switchShiftInfoFragmentBinding.setDateEnd(displayDate(dateEnd));
            timekeepingSang = switchShiftEdit.getContentMornig();
            timekeepingChieu=switchShiftEdit.getContentAfternoon();
            timekeepingToi=switchShiftEdit.getContentEverning();
        }
        else {
            switchShiftInfoFragmentBinding.setDateBegin("");
            switchShiftInfoFragmentBinding.setDateEnd("");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SwitchShiftInfoViewModel.class);
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
                switchShiftInfoFragmentBinding.setEmployDiLam(employee);
            }
        });
        mViewModel.getEmploy_cham_cong().observe(getViewLifecycleOwner(), new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                switchShiftInfoFragmentBinding.setEmployDuocChamCong(employee);
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