package com.firstems.erp.ui.signature.billpaymentrequest.ticket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

public class TicketBillPaymentRequestViewModel extends ViewModel {
    private MutableLiveData<String> title;

    public TicketBillPaymentRequestViewModel() {
        title= new MutableLiveData<>();

        initTitle();
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(123));
    }

    public LiveData<String> getTitle() {
        return title;
    }
}