package com.firstems.erp.model;

/**
 * Created by Nguyen Quoc Cuong on 7/21/2020.
 */
public class Language {
    private String langCode;
    private String langName;

    public Language(String langCode, String langName) {
        this.langCode = langCode;
        this.langName = langName;
    }

    public Language() {
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    @Override
    public String toString() {
        return langName;
    }
}
