package com.firstems.erp.ui.date;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectDateViewModel extends ViewModel {
    private MutableLiveData<String> title;
    public SelectDateViewModel() {
        title= new MutableLiveData<>();

        initTitle();
    }

    private void initTitle() {
        title.setValue("Điều chỉnh thời gian");
    }

    public LiveData<String> getTitle() {
        return title;
    }
}