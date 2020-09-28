package com.firstems.erp.ui.signature.filtersignature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.FilterSignatureFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.model.FilterModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.system.SysConfig;

import java.util.Date;

public class FilterSignatureFragment extends CommonFragment {

    private FilterSignatureViewModel mViewModel;
    private FilterSignatureFragmentBinding binding;
    private TextView txtTitle;
    private ImageView imgClose, imgDone;
    private Button btnDone;
    private Date dateBegin, dateEnd;
    private FilterModel filterModelCurent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.filter_signature_fragment, container, false);

        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleDateBegin.setText(SharedPreferencesManager.getSystemLabel(32));
        binding.txtTitleDateEnd.setText(SharedPreferencesManager.getSystemLabel(33));
        binding.txtTitleStatus.setText(SharedPreferencesManager.getSystemLabel(22));
        binding.chkChuaTrinhKy.setText(SharedPreferencesManager.getSystemLabel(146));
        binding.chkChoDuyet.setText(SharedPreferencesManager.getSystemLabel(147));
        binding.chkHoanTat.setText(SharedPreferencesManager.getSystemLabel(148));
        binding.btnSort.setText(SharedPreferencesManager.getSystemLabel(144));
    }
    
    private void addEvents() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        binding.include8.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHelper.getInstance().setAnimationTopToBottom(getActivity());
                getActivity().finish();
            }
        });
        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDone();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDone();
            }
        });
        binding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = 0;
                long maxDate = dateEnd.getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin = date;
                        binding.txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin));
                    }
                });
            }
        });
        binding.layoutDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long maxDate = 0;
                long minDate = dateBegin.getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd = date;
                        binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
                    }
                });
            }
        });
    }
    
    private void doDone() {
        FilterModel filterModel = new FilterModel();
        filterModel.setBeginDate(dateBegin);
        filterModel.setEndDate(dateEnd);
        filterModel.setDone(binding.chkHoanTat.isChecked());
        filterModel.setWaitApproved(binding.chkChoDuyet.isChecked());
        filterModel.setWaitsignature(binding.chkChuaTrinhKy.isChecked());
        Intent intent = new Intent();
        intent.putExtra(Constant.NAME_PUT_FILTER_MODEL,filterModel);
        getActivity().setResult(Activity.RESULT_OK,intent);
        setOveridePendingTransisi(getActivity());
        getActivity().finish();
    }
    
    private void addControls() {
        Intent intent = getActivity().getIntent();
        filterModelCurent  = (FilterModel) intent.getSerializableExtra(Constant.NAME_PUT_FILTER_MODEL);
        
        txtTitle=binding.include8.findViewById(R.id.txtTitle);
        imgClose = binding.include8.findViewById(R.id.imgClose);
        imgDone=binding.include8.findViewById(R.id.imgDone);
        btnDone = binding.btnSort;
        
        if (filterModelCurent!=null){
            dateBegin = filterModelCurent.getBeginDate();
            dateEnd = filterModelCurent.getEndDate();
            binding.setFilter(filterModelCurent);
        }
        else {
                dateBegin = SysConfig.createDateLoadSign().get(0);
                dateEnd = SysConfig.createDateLoadSign().get(1);
        }
        binding.txtDateBegin.setText(dateBegin!=null ? simpleDateFormatDisplay.format(dateBegin) : "");
        binding.txtDateEnd.setText(dateEnd!=null ? simpleDateFormatDisplay.format(dateEnd) : "");
        
        binding.chkChuaTrinhKy.setChecked(SharedPreferencesManager.getInstance().getWaitSignature());
        binding.chkChoDuyet.setChecked(SharedPreferencesManager.getInstance().getWaitAppreoved());
        binding.chkHoanTat.setChecked(SharedPreferencesManager.getInstance().getCompleteSignature());
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FilterSignatureViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }

}