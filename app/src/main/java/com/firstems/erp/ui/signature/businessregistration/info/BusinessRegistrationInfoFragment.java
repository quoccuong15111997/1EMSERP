package com.firstems.erp.ui.signature.businessregistration.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.locationtype.LocationType;
import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.api.model.response.province.Province;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.BusinessRegistrationInfoFragmentBinding;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.helper.validation.ValidationData;
import com.firstems.erp.model.business.Business;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.national.NationalActivity;
import com.firstems.erp.ui.shared.province.ProvinceActivity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessRegistrationInfoFragment extends CommonFragment {

    private BusinessRegistrationInfoViewModel mViewModel;
    private BusinessRegistrationInfoFragmentBinding binding;
    private Date dateBegin, dateEnd;
    private Spinner spinnerLocateType;
    private List<LocationType> locationTypeList;
    private ArrayAdapter<LocationType> locationTypeArrayAdapter;
    private int CODE_SELECT_NATIONAL = 001;
    private int CODE_SELECT_PROVINCE = 002;
    private Spinner spinnerLoaiCTSang,spinnerLoaiCTChieu,spinnerLoaiCTToi;
    private ArrayAdapter<TimekeepingTypeCT> timekeepingTypeCTArrayAdapter;
    private List<TimekeepingTypeCT> timekeepingTypeCTList;
    private National nationalSelected;
    private Province provinceSelected;
    private boolean flagTemp = false;
    private int loadFlag =0;

    private Business businessEdit;

    public static BusinessRegistrationInfoFragment newInstance() {
        return new BusinessRegistrationInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.business_registration_info_fragment, container, false);
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83), SharedPreferencesManager.getSystemLabel(63));
       // progressdialog.show();
        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTileNoiCongTac.setText(SharedPreferencesManager.getSystemLabel(105));
        binding.txtTitleDateBegin.setText(SharedPreferencesManager.getSystemLabel(32));
        binding.txtTitleDateEnd.setText(SharedPreferencesManager.getSystemLabel(33));
        binding.chkMorning.setText(SharedPreferencesManager.getSystemLabel(36));
        binding.chkAfternoon.setText(SharedPreferencesManager.getSystemLabel(37));
        binding.chkEverning.setText(SharedPreferencesManager.getSystemLabel(38));
        binding.txtTitleLoaiChamCongSang.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.txtTitleLoaiChamCongChieu.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.txtTitleLoaiChamCongToi.setText(SharedPreferencesManager.getSystemLabel(39));
        binding.btnDone.setText(SharedPreferencesManager.getSystemLabel(40));
    }
    
    private void addEvents() {
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        binding.include10.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        binding.txtDateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBeginDate();
            }
        });
        binding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBeginDate();
            }
        });
        binding.txtDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEndDate();
            }
        });
        binding.layoutDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEndDate();
            }
        });
        binding.include10.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.txtLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationType locationType = (LocationType) spinnerLocateType.getSelectedItem();
                if (locationType.getItemCode().equals("01")){
                    Intent intent= new Intent(getActivity(), ProvinceActivity.class);
                    intent.putExtra(Constant.NAME_PUT_TITLE_SELECT_PROVINCE,SharedPreferencesManager.getSystemLabel(99));
                    if (provinceSelected!=null){
                        intent.putExtra(Constant.NAME_PUT_PROVINCE,provinceSelected);
                    }
                    startActivityForResult(intent,CODE_SELECT_PROVINCE);
                }
                else if (locationType.getItemCode().equals("02")){
                    Intent intent= new Intent(getActivity(), NationalActivity.class);
                    intent.putExtra(Constant.NAME_PUT_TITLE_SELECT_NATIONAL,SharedPreferencesManager.getSystemLabel(100));
                    if (nationalSelected!=null){
                        intent.putExtra(Constant.NAME_PUT_NATIONAL, nationalSelected);
                    }
                    startActivityForResult(intent, CODE_SELECT_NATIONAL);
                }
            }
        });
        binding.spinerLocateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinceSelected=null;
                nationalSelected=null;
                binding.txtLocate.setText("");
                if (!flagTemp){
                    if (businessEdit!=null){
                        if (businessEdit.getNational()!=null){
                            binding.txtLocate.setText(businessEdit.getNational().getItemName());
                            nationalSelected = businessEdit.getNational();
                        }
                        if (businessEdit.getProvince()!=null){
                            binding.txtLocate.setText(businessEdit.getProvince().getItemName());
                            provinceSelected = businessEdit.getProvince();
                        }
                        flagTemp = true;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void selectEndDate() {
        long minDate = dateBegin.getTime();
        long maxDate = 0;
        DatePickerDialog
                .getInstance()
                .showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd = date;
                        binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
                    }
                });
    }

    private void selectBeginDate() {
        long minDate = 0;
        long maxDate = 0;
        DatePickerDialog
                .getInstance()
                .showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin = date;
                        if (dateBegin.getTime()>dateEnd.getTime()){
                            dateEnd = dateBegin;
                            binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
                        }
                        binding.txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin));
                    }
                });
    }

    private void addControls() {
        Intent intent=getActivity().getIntent();
        businessEdit = (Business) intent.getSerializableExtra(Constant.NAME_PUT_BUSINESS_REGISTRATION);

        dateBegin= DateTime.now().withTimeAtStartOfDay().toDate();
        dateEnd= DateTime.now().withTimeAtStartOfDay().toDate();
        binding.txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin));
        binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));

        spinnerLocateType = binding.spinerLocateType;
        locationTypeList= new ArrayList<>();
        locationTypeArrayAdapter =  new ArrayAdapter<>(getActivity(),R.layout.layout_custom_item_spiner,locationTypeList);
        locationTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocateType.setAdapter(locationTypeArrayAdapter);

        timekeepingTypeCTList= new ArrayList<>();
        timekeepingTypeCTArrayAdapter = new ArrayAdapter<TimekeepingTypeCT>(getContext(),R.layout.layout_custom_item_spiner,timekeepingTypeCTList);
        timekeepingTypeCTArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiCTSang = binding.spinerLoaiChamCongSang;
        spinnerLoaiCTChieu = binding.spinerLoaiChamCongChieu;
        spinnerLoaiCTToi = binding.spinerLoaiChamCongToi;

        spinnerLoaiCTSang.setAdapter(timekeepingTypeCTArrayAdapter);
        spinnerLoaiCTChieu.setAdapter(timekeepingTypeCTArrayAdapter);
        spinnerLoaiCTToi.setAdapter(timekeepingTypeCTArrayAdapter);
        if (businessEdit!=null){
            if (businessEdit.getTimekeepingTypeCTMorn()!=null){
                binding.chkMorning.setChecked(true);
                for (int i=0;i<timekeepingTypeCTList.size();i++){
                    TimekeepingTypeCT typeCT = timekeepingTypeCTList.get(i);
                    if (typeCT.getItemCode().equals(businessEdit.getTimekeepingTypeCTMorn().getItemCode())){
                        spinnerLoaiCTSang.setSelection(i);
                    }
                }

            }
            if (businessEdit.getTimekeepingTypeCTAfft()!=null){
                binding.chkAfternoon.setChecked(true);
                for (int i=0;i<timekeepingTypeCTList.size();i++){
                    TimekeepingTypeCT typeCT = timekeepingTypeCTList.get(i);
                    if (typeCT.getItemCode().equals(businessEdit.getTimekeepingTypeCTAfft().getItemCode())){
                        spinnerLoaiCTChieu.setSelection(i);
                    }
                }
            }
            if (businessEdit.getTimekeepingTypeCTEvrn()!=null){
                binding.chkEverning.setChecked(true);
                for (int i=0;i<timekeepingTypeCTList.size();i++){
                    TimekeepingTypeCT typeCT = timekeepingTypeCTList.get(i);
                    if (typeCT.getItemCode().equals(businessEdit.getTimekeepingTypeCTEvrn().getItemCode())){
                        spinnerLoaiCTToi.setSelection(i);
                    }
                }
            }
            dateBegin = businessEdit.getDateBegin();
            dateEnd = businessEdit.getDateEnd();
            binding.txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin));
            binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BusinessRegistrationInfoViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView txtTitle = binding.include10.findViewById(R.id.txtTitle);
                txtTitle.setText(s);
            }
        });
        mViewModel.getLiveDataLocateType().observe(getViewLifecycleOwner(), new Observer<List<LocationType>>() {
            @Override
            public void onChanged(List<LocationType> locationTypes) {
                locationTypeList.clear();
                locationTypeList.addAll(locationTypes);
                locationTypeArrayAdapter.notifyDataSetChanged();

                if (businessEdit!=null){
                    for (int i=0;i<locationTypeList.size();i++){
                        LocationType locationType = locationTypeList.get(i);
                        if (locationType.getItemCode().equals(businessEdit.getLocationType().getItemCode())){
                            spinnerLocateType.setSelection(i);
                        }
                    }
                }
                loadFlag+=1;
                hideLoadding();

            }
        });
        mViewModel.getLiveDataTimeKeppingType().observe(getViewLifecycleOwner(), new Observer<List<TimekeepingTypeCT>>() {
            @Override
            public void onChanged(List<TimekeepingTypeCT> timekeepingTypeCTS) {
                timekeepingTypeCTList.clear();
                timekeepingTypeCTList.addAll(timekeepingTypeCTS);
                timekeepingTypeCTArrayAdapter.notifyDataSetChanged();
                loadFlag+=1;
                hideLoadding();
            }
        });
    }
    private void hideLoadding(){
       /* if (loadFlag==2){
            progressdialog.dismiss();
        }*/
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE_SELECT_NATIONAL && resultCode== Activity.RESULT_OK){
            National national = (National) data.getSerializableExtra(Constant.NAME_PUT_NATIONAL);
            if (national!=null){
                nationalSelected = national;
                binding.txtLocate.setText(nationalSelected.getItemName());
            }
            else {
                nationalSelected=null;
                binding.txtLocate.setText("");
            }
        }
        if (requestCode==CODE_SELECT_PROVINCE && resultCode==Activity.RESULT_OK){
            Province province = (Province) data.getSerializableExtra(Constant.NAME_PUT_PROVINCE);
            if (province!=null){
                provinceSelected = province;
                binding.txtLocate.setText(provinceSelected.getItemName());
            }
            else {
                provinceSelected = null;
                binding.txtLocate.setText("");
            }
        }
    }
    private void saveData(){
        if (validationLocate()){
            if (validateText(binding.txtDateBegin)){
                if (validateText(binding.txtDateEnd)){
                    if (validatetionKeepingType()){
                        Business business = new Business();
                        business.setLocationType((LocationType) spinnerLocateType.getSelectedItem());
                        business.setProvince(provinceSelected);
                        business.setNational(nationalSelected);
                        business.setDateBegin(dateBegin);
                        business.setDateEnd(dateEnd);

                        if (binding.chkMorning.isChecked()){
                            business.setTimekeepingTypeCTMorn((TimekeepingTypeCT) spinnerLoaiCTSang.getSelectedItem());
                        }
                        if (binding.chkAfternoon.isChecked()){
                            business.setTimekeepingTypeCTAfft((TimekeepingTypeCT) spinnerLoaiCTChieu.getSelectedItem());
                        }
                        if (binding.chkEverning.isChecked()){
                            business.setTimekeepingTypeCTEvrn((TimekeepingTypeCT) spinnerLoaiCTToi.getSelectedItem());
                        }

                        Intent intent= new Intent();
                        intent.putExtra(Constant.NAME_PUT_BUSINESS_REGISTRATION,business);
                        getActivity().setResult(Activity.RESULT_OK,intent);
                        getActivity().finish();
                    }
                    else {
                        SnackbarHelper.getInstance().snackBarIconError(getView(),SharedPreferencesManager.getSystemLabel(104));
                        return;
                    }
                }
                else {
                    SnackbarHelper.getInstance().snackBarIconError(getView(),SharedPreferencesManager.getSystemLabel(101));
                    return;
                }
            }
            else {
                SnackbarHelper.getInstance().snackBarIconError(getView(),SharedPreferencesManager.getSystemLabel(102));
                return;
            }
        }
        else {
            SnackbarHelper.getInstance().snackBarIconError(getView(),SharedPreferencesManager.getSystemLabel(103));
            return;
        }
    }
    private boolean validateText(TextView textView){
        if (!ValidationData.isBlank(textView)){
            return true;
        }
        return false;
    }
    private boolean validatetionKeepingType(){
        if (binding.chkMorning.isChecked() || binding.chkAfternoon.isChecked() || binding.chkEverning.isChecked()){
            return true;
        }
        return false;
    }
    private boolean validationLocate(){
        LocationType locationType = (LocationType) spinnerLocateType.getSelectedItem();
        if (locationType.getItemCode().equals("01")){
            if (provinceSelected!=null){
                return true;
            }
            else
            {
                return false;
            }
        }
        if (locationType.getItemCode().equals("02")){
            if (nationalSelected!=null){
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
}