package com.firstems.erp.ui.report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ReportViewModel extends ViewModel {
    private MutableLiveData<String> title;

    public ReportViewModel() {
        title= new MutableLiveData<>();

        initTitle();
    }

    private void initTitle() {
        title.setValue("Báo cáo thống kê");
    }
    public LiveData<String> getTitle(){
        return title;
    }
}