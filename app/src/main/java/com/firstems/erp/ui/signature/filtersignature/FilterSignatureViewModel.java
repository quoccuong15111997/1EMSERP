package com.firstems.erp.ui.signature.filtersignature;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

public class FilterSignatureViewModel extends ViewModel {
    private MutableLiveData<String> title;
    public FilterSignatureViewModel() {
        title= new MutableLiveData<>();

        initTitle();
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(144));
    }

    public LiveData<String> getTitle() {
        return title;
    }
}