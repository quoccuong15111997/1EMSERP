package com.firstems.erp.viewmodel.config;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;



import java.util.Observable;
import java.util.stream.Stream;

/**
 * Created by Nguyen Quoc Cuong on 7/21/2020.
 */
public class ConfigUIBindingModel extends BaseObservable {
    private String titleSystemSetting;
    private String titleSignatureSetting;
    private String titleApproveSetiing;
    private String titleAskPermistion;

    private String titleDomain;
    private String edtDomain;
    private String titleLanguage;
    private String langValue;
    private String titleNumberDay;
    private String edtNumberDay;

    @Bindable
    public String getEdtDomain() {
        return edtDomain;
    }

    public void setEdtDomain(String edtDomain) {
        this.edtDomain = edtDomain;
    }
    @Bindable
    public String getEdtNumberDay() {
        return edtNumberDay;
    }

    public void setEdtNumberDay(String edtNumberDay) {
        this.edtNumberDay = edtNumberDay;
        //System.out.println(edtNumberDay);
    }

    private String switchInfoSignature;
    private String switchPendding;
    private String switchUnSignature;
    private String switchDone;

    private String switchInfoApprove;
    private String switchBySession;
    private String switchWork;

    private String buttonSave;
    private String buttonCancel;
    private String buttonDefault;

    public ConfigUIBindingModel() {
    }

    public void setLangValue(String langValue) {
        this.langValue = langValue;
    }

    @Bindable
    public String getLangValue() {
        return langValue;
    }

    @Bindable
    public String getButtonDefault() {
        return buttonDefault;
    }

    public void setButtonDefault(String buttonDefault) {
        this.buttonDefault = buttonDefault;
    }

    @Bindable
    public String getTitleAskPermistion() {
        return titleAskPermistion;
    }

    public void setTitleAskPermistion(String titleAskPermistion) {
        this.titleAskPermistion = titleAskPermistion;
    }
    @Bindable
    public String getTitleSystemSetting() {
        return titleSystemSetting;
    }

    public void setTitleSystemSetting(String titleSystemSetting) {
        this.titleSystemSetting = titleSystemSetting;
    }
    @Bindable
    public String getTitleSignatureSetting() {
        return titleSignatureSetting;
    }

    public void setTitleSignatureSetting(String titleSignatureSetting) {
        this.titleSignatureSetting = titleSignatureSetting;
    }
    @Bindable
    public String getTitleApproveSetiing() {
        return titleApproveSetiing;
    }

    public void setTitleApproveSetiing(String titleApproveSetiing) {
        this.titleApproveSetiing = titleApproveSetiing;
    }
    @Bindable
    public String getTitleDomain() {
        return titleDomain;
    }

    public void setTitleDomain(String titleDomain) {
        this.titleDomain = titleDomain;
    }


    @Bindable
    public String getTitleLanguage() {
        return titleLanguage;
    }

    public void setTitleLanguage(String titleLanguage) {
        this.titleLanguage = titleLanguage;
    }
    @Bindable
    public String getTitleNumberDay() {
        return titleNumberDay;
    }

    public void setTitleNumberDay(String titleNumberDay) {
        this.titleNumberDay = titleNumberDay;
    }
    @Bindable
    public String getSwitchInfoSignature() {
        return switchInfoSignature;
    }

    public void setSwitchInfoSignature(String switchInfoSignature) {
        this.switchInfoSignature = switchInfoSignature;
    }
    @Bindable
    public String getSwitchPendding() {
        return switchPendding;
    }

    public void setSwitchPendding(String switchPendding) {
        this.switchPendding = switchPendding;
    }
    @Bindable
    public String getSwitchUnSignature() {
        return switchUnSignature;
    }

    public void setSwitchUnSignature(String switchUnSignature) {
        this.switchUnSignature = switchUnSignature;
    }
    @Bindable
    public String getSwitchDone() {
        return switchDone;
    }

    public void setSwitchDone(String switchDone) {
        this.switchDone = switchDone;
    }
    @Bindable
    public String getSwitchInfoApprove() {
        return switchInfoApprove;
    }

    public void setSwitchInfoApprove(String switchInfoApprove) {
        this.switchInfoApprove = switchInfoApprove;
    }
    @Bindable
    public String getSwitchBySession() {
        return switchBySession;
    }

    public void setSwitchBySession(String switchBySession) {
        this.switchBySession = switchBySession;
    }
    @Bindable
    public String getSwitchWork() {
        return switchWork;
    }

    public void setSwitchWork(String switchWork) {
        this.switchWork = switchWork;
    }
    @Bindable
    public String getButtonSave() {
        return buttonSave;
    }

    public void setButtonSave(String buttonSave) {
        this.buttonSave = buttonSave;
    }
    @Bindable
    public String getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(String buttonCancel) {
        this.buttonCancel = buttonCancel;
    }
}
