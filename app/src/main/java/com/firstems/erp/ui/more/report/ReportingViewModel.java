package com.firstems.erp.ui.more.report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportingViewModel extends ViewModel {
    private MutableLiveData<String> title;
    public ReportingViewModel() {
        title= new MutableLiveData<>();

        initTitle();
    }

    private void initTitle() {
        title.setValue("Báo cáo");
    }

    public LiveData<String> getTitle() {
        return title;
    }
}