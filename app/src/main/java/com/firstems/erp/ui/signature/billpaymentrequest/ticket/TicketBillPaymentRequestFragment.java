package com.firstems.erp.ui.signature.billpaymentrequest.ticket;

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
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.TicketBillPaymentRequestFragmentBinding;
import com.firstems.erp.helper.NumberTextWatcher;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.signature.billpaymentrequest.model.TicketBillPaymentDetail;

import java.util.Date;

public class TicketBillPaymentRequestFragment extends CommonFragment {

    private TicketBillPaymentRequestViewModel mViewModel;
    private TicketBillPaymentRequestFragmentBinding binding;
    private TextView txtTitle;
    private Date date;
    private TicketBillPaymentDetail ticketBillPaymentDetailEdit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.ticket_bill_payment_request_fragment, container, false);

        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(126));
        binding.txtTitleSoTien.setText(SharedPreferencesManager.getSystemLabel(131));
        binding.txtTitleSoHoaDon.setText(SharedPreferencesManager.getSystemLabel(132));
        binding.txtTitleDate.setText(SharedPreferencesManager.getSystemLabel(133));
        binding.btnDone.setText(SharedPreferencesManager.getSystemLabel(40));
    }
    
    private void addEvents() {
        binding.edtSoTien.addTextChangedListener(new NumberTextWatcher(binding.edtSoTien, null));
        binding.layoutDateDeNghiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.getInstance().showDialogSelectDate(0, 0, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date d) {
                        date = d;
                        binding.edtDate.setText(simpleDateFormatDisplay.format(d));
                    }
                });
            }
        });
        binding.edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.getInstance().showDialogSelectDate(0, 0, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date d) {
                        date = d;
                        binding.edtDate.setText(simpleDateFormatDisplay.format(d));
                    }
                });
            }
        });
        binding.header.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        binding.header.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
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
    }

    private void doSave() {
        if (!binding.edtInfo.getText().toString().equals("")){
            if (!binding.edtSoTien.getText().toString().equals("")){
                TicketBillPaymentDetail ticketBillPaymentDetail = new TicketBillPaymentDetail();
                ticketBillPaymentDetail.setContent(binding.edtInfo.getText().toString());
                ticketBillPaymentDetail.setNumberPrice(Double.parseDouble(binding.edtSoTien.getText().toString().replace(".","").trim()));
                ticketBillPaymentDetail.setBillCode(binding.edtSoHoaDon.getText().toString());
                ticketBillPaymentDetail.setDateBill(date);
    
                Intent intent = new Intent();
                intent.putExtra(Constant.NAME_PUT_TICKET_BILL_PAYMENT, ticketBillPaymentDetail);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
            else {
                showToastError(SharedPreferencesManager.getSystemLabel(119));
            }
        }
        else {
            showToastError(SharedPreferencesManager.getSystemLabel(136));
        }
    }

    private void addControls() {
        txtTitle=binding.header.findViewById(R.id.txtTitle);
        Intent intent = getActivity().getIntent();
        ticketBillPaymentDetailEdit = (TicketBillPaymentDetail) intent.getSerializableExtra(Constant.NAME_PUT_TICKET_BILL_PAYMENT);
        if (ticketBillPaymentDetailEdit!=null){
            binding.edtSoTien.setText(String.valueOf((int) ticketBillPaymentDetailEdit.getNumberPrice()));
            binding.edtInfo.setText(ticketBillPaymentDetailEdit.getContent());
            binding.edtSoHoaDon.setText(ticketBillPaymentDetailEdit.getBillCode());
            binding.edtDate.setText(ticketBillPaymentDetailEdit.getDateBill() !=null ? simpleDateFormatDisplay.format(ticketBillPaymentDetailEdit.getDateBill()) : "");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TicketBillPaymentRequestViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }

}