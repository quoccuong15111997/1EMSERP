package com.firstems.erp.ui.more.help;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModel extends ViewModel {
    private MutableLiveData<String> title;

    public HelpViewModel() {
        title= new MutableLiveData<>();
        
        initTitle();
    }

    private void initTitle() {
    }
}