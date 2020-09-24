package com.firstems.erp.ui.company;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

public class SelectCompanyViewModel extends ViewModel {
    private MutableLiveData<String> title;

    public SelectCompanyViewModel() {
        title= new MutableLiveData<>();

        initTile();
    }

    private void initTile() {
        title.setValue(SharedPreferencesManager.getSystemLabel(6));
    }

    public LiveData<String> getTitle() {
        return title;
    }
}