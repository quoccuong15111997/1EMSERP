package com.firstems.erp.ui.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.model.Language;
import com.firstems.erp.viewmodel.config.ConfigUIBindingModel;

import java.util.ArrayList;
import java.util.List;

public class ConfigViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<ConfigUIBindingModel> uiModel;
    private MutableLiveData<List<Language>> langList;
    private MutableLiveData<String> numberDateValue;
    public ConfigViewModel() {
        title= new MutableLiveData<>();
        uiModel= new MutableLiveData<>();
        langList= new MutableLiveData<>();
        numberDateValue= new MutableLiveData<>();
        iniTitle();
        initUIModel();
        initLang();
        initNumberDate();
    }

    private void initNumberDate() {
        numberDateValue.setValue("7");
    }

    private void initLang() {
        List<Language>  languages= new ArrayList<>();
        languages.add(new Language("V"," Tiếng Việt"));
        languages.add(new Language("E", "English"));
        langList.setValue(languages);
    }

    private void initUIModel() {
        ConfigUIBindingModel configUIBindingModel= new ConfigUIBindingModel();
        configUIBindingModel.setTitleSystemSetting("Cài đặt hệ thống");
        configUIBindingModel.setTitleApproveSetiing("Mặc định xem phê duyệt");
        configUIBindingModel.setTitleSignatureSetting("Mặc định trình kí");
        configUIBindingModel.setTitleAskPermistion("Mặc định đơn xin phép");
        configUIBindingModel.setTitleDomain("Domain");
        configUIBindingModel.setEdtDomain("http://api-dev.firstems.com/");
        configUIBindingModel.setTitleLanguage("Ngôn ngữ");
        configUIBindingModel.setTitleNumberDay("Số ngày");
        configUIBindingModel.setEdtNumberDay("7");
        configUIBindingModel.setSwitchInfoSignature("Chi tiết");
        configUIBindingModel.setSwitchInfoApprove("Chi tiết");
        configUIBindingModel.setSwitchUnSignature("Chưa trình kí");
        configUIBindingModel.setSwitchDone("Hoàn tất");
        configUIBindingModel.setSwitchBySession("Theo buổi");
        configUIBindingModel.setSwitchWork("Công khoán");
        configUIBindingModel.setButtonSave("Lưu");
        configUIBindingModel.setButtonCancel("Hủy");
        configUIBindingModel.setButtonDefault("Mặc định");

        uiModel.setValue(configUIBindingModel);
    }

    private void iniTitle() {
        title.setValue("Cấu hình");
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<ConfigUIBindingModel> getUiModel() {
        return uiModel;
    }

    public MutableLiveData<List<Language>> getLangList() {
        return langList;
    }

    public LiveData<String> getNumberDateValue() {
        return numberDateValue;
    }
}