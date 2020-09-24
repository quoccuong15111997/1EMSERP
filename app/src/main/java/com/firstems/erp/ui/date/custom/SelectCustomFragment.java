package com.firstems.erp.ui.date.custom;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.firstems.erp.R;
import com.firstems.erp.callback.DatePickerCallback;
import com.firstems.erp.callback.TypeSelecDate;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.databinding.SelectCustomFragmentBinding;
import com.firstems.erp.helper.dialog.DialogUtils;
import com.firstems.erp.utils.Tools;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SelectCustomFragment extends CommonFragment {

    private SelectCustomViewModel mViewModel;
    private SelectCustomFragmentBinding binding;
    private Calendar calendarEnd,calendaBegin;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.select_custom_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.llEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialogDatePicker(new DatePickerCallback() {
                   @Override
                   public void onDatePick(Calendar calendar) {
                       calendarEnd=calendar;
                       binding.txtValueEndDate.setText(converDate(calendarEnd));
                   }

                   @Override
                   public void typeSelect(TypeSelecDate typeSelecDate) {
                       typeSelecDate.typeSelectEndDate(calendaBegin);
                   }
               });
            }
        });
        binding.linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDatePicker(new DatePickerCallback() {
                    @Override
                    public void onDatePick(Calendar calendar) {
                        calendaBegin=calendar;
                        binding.txtValueBeginDate.setText(converDate(calendaBegin));
                    }

                    @Override
                    public void typeSelect(TypeSelecDate typeSelecDate) {
                        typeSelecDate.typeSelectEndDate(calendarEnd);
                    }
                });
            }
        });
    }

    private void addControls() {

        calendarEnd=Calendar.getInstance();
        //calendarEnd.set(Calendar.MONTH,calendarEnd.get(Calendar.MONTH)+1);
        calendaBegin=Calendar.getInstance();
        //calendaBegin.set(Calendar.MONTH,calendaBegin.get(Calendar.MONTH)+1);

        binding.txtValueEndDate.setText(converDate(calendarEnd));
        binding.txtValueBeginDate.setText(converDate(calendaBegin));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectCustomViewModel.class);

    }

}