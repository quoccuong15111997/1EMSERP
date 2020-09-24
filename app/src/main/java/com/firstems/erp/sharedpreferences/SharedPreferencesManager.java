package com.firstems.erp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firstems.erp.BuildConfig;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class SharedPreferencesManager {
    private static SharedPreferences sPreferences;
    private static SharedPreferencesManager instance;

    //Set up
    private static final String PREF_FIRST_SETUP = BuildConfig.APPLICATION_ID + ".pref_first_setup";
    private static final String PREF_FCM_TOKEN = BuildConfig.APPLICATION_ID + ".pref_fmc_token";
    private static final String PREF_FIRST_SETUP_RUNCODE = BuildConfig.APPLICATION_ID + ".pref_first_setup_runcode";

    //Config
    private static final String PREF_DOMAIN = BuildConfig.APPLICATION_ID + ".pref_doamin";
    private static final String PREF_LANGUAGE = BuildConfig.APPLICATION_ID + ".pref_language";
    private static final String PREF_DEFAULT_DAY_SIGNATURE = BuildConfig.APPLICATION_ID + ".pref_defaut_day_signature";
    private static final String PREF_INFO_SIGNATURE = BuildConfig.APPLICATION_ID + ".pref_info_signature";
    private static final String PREF_WAIT_SIGNATURE = BuildConfig.APPLICATION_ID + ".pref_wait_signature";
    private static final String PREF_WAIT_APPROVED = BuildConfig.APPLICATION_ID + ".pref_wait_approved";
    private static final String PREF_COMPLETE_SIGNATURE = BuildConfig.APPLICATION_ID + ".pref_complete_signature";
    private static final String PREF_INFO_APPROVED = BuildConfig.APPLICATION_ID + ".pref_info_approved";
    private static final String PREF_DEFAUL_REQUEST_FORM_PART = BuildConfig.APPLICATION_ID + ".pref_default_request_form_part";
    private static final String PREF_DEFAUL_REQUEST_FORM_CONG_KHOAN = BuildConfig.APPLICATION_ID + ".pref_request_form_cong_khoang";


    private static final String PREF_COLOR_BASE = BuildConfig.APPLICATION_ID + ".pref_color_base";
    private static final String PREF_COLOR_SECOND = BuildConfig.APPLICATION_ID + ".pref_color_second";
    private static final String PREF_COLOR_BACKGROUND = BuildConfig.APPLICATION_ID + ".pref_color_second";
    private static final String PREF_COLOR_TEXT = BuildConfig.APPLICATION_ID + ".pref_color_text";
    private static final String PREF_COLOR_BORDER_TEXT = BuildConfig.APPLICATION_ID + ".pref_color_border_text";
    private static final String PREF_COLOR_BACKGROUND_TEXT = BuildConfig.APPLICATION_ID + ".pref_color_border_text";
    private static final String PREF_COLOR_ACCENT = BuildConfig.APPLICATION_ID + ".pref_color_accent";

    //Account
    private static final String PREF_USERNAME = BuildConfig.APPLICATION_ID + ".pref_username";
    private static final String PREF_PASSWORD = BuildConfig.APPLICATION_ID + ".pref_password";
    private static final String PREF_REMEMBER = BuildConfig.APPLICATION_ID + ".pref_remember";
    private static final String PREF_TOKEN = BuildConfig.APPLICATION_ID + ".pref_token";
    private static final String PREF_COMPCODE = BuildConfig.APPLICATION_ID + ".pref_comp_code";
    private static final String PREF_COMPNAME = BuildConfig.APPLICATION_ID + ".pref_comp_name";
    private static final String PREF_LCTCODE = BuildConfig.APPLICATION_ID + ".pref_location_code";
    private static final String PREF_LCTNAME = BuildConfig.APPLICATION_ID + ".pref_location_name";

    private static final String PREF_USERCODE = BuildConfig.APPLICATION_ID + ".pref_usercode";
    private static final String PREF_ACCOUNT_NAME = BuildConfig.APPLICATION_ID + ".pref_account_name";
    private static final String PREF_APP_RIGHT = BuildConfig.APPLICATION_ID + ".pref_app_right";
    private static final String PREF_EMP_CODE = BuildConfig.APPLICATION_ID + ".pref_emp_code";
    private static final String PREF_PART_CODE = BuildConfig.APPLICATION_ID + ".pref_part_code";
    private static final String PREF_PART_NAME = BuildConfig.APPLICATION_ID + ".pref_part_name";
    private static final String PREF_POST_CODE = BuildConfig.APPLICATION_ID + ".pref_post_code";
    private static final String PREF_POST_NAME = BuildConfig.APPLICATION_ID + ".pref_post_name";
    private static final String PREF_JOB_NAME = BuildConfig.APPLICATION_ID + ".pref_job_name";
    private static final String PREF_JOB_CODE = BuildConfig.APPLICATION_ID + ".pref_job_code";
    private static final String PREF_LOGO_COMP = BuildConfig.APPLICATION_ID + ".pref_logo_comp";
    
    private static final String PREF_SIGN_LOAD_DATE_BEBIN = BuildConfig.APPLICATION_ID + ".sign_load_date_begin";
    private static final String PREF_SIGN_LOAD_DATE_END = BuildConfig.APPLICATION_ID + ".sign_load_date_end";
    private static final String PREF_SIGN_LOAD_STTSIGN = BuildConfig.APPLICATION_ID + ".sign_load_sttsign";
    private static final String PREF_SYSLABEL_BASE = BuildConfig.APPLICATION_ID+".label";
    private static final String PREF_SYSLABEL_BASE_LOAD = BuildConfig.APPLICATION_ID+".label_load";
    

    private SharedPreferencesManager() {

    }
    public static SharedPreferencesManager getInstance(){
        if (instance== null){
            instance= new SharedPreferencesManager();
        }
        return instance;
    }
    public static void init(Context context) {
        if (sPreferences == null) {
            sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }
    
    public static void setSystemLabelData(int itemCode, String langCode,String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_SYSLABEL_BASE+itemCode+langCode,value);
        editor.commit();
    }
    public static String getSystemLabel(int itemCode){
         //return "Code: "+itemCode;
        return sPreferences.getString(PREF_SYSLABEL_BASE+itemCode+SharedPreferencesManager.getInstance().getLanguage(),"Loading");
    }
    
    
    public void setSyslabelBaseLoad(boolean isFisrt){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_SYSLABEL_BASE_LOAD,isFisrt);
        editor.commit();
    }
    public boolean isSyslabelBaseLoad(){
        return sPreferences.getBoolean(PREF_SYSLABEL_BASE_LOAD,true);
    }
    
    
    public static void setFCMToken(String token){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_FCM_TOKEN,token);
        editor.commit();
    }
    public static String getFCMToken(){
        return sPreferences.getString(PREF_FCM_TOKEN,"");
    }
    public void setFirstSetup(boolean isFisrt){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_FIRST_SETUP,isFisrt);
        editor.commit();
    }
    public boolean isFirstSetup(){
        return sPreferences.getBoolean(PREF_FIRST_SETUP,true);
    }

    public void setFirstSetupRuncode(boolean isFisrt){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_FIRST_SETUP_RUNCODE,isFisrt);
        editor.commit();
    }
    public boolean isFirstSetupRuncode() {
        return sPreferences.getBoolean(PREF_FIRST_SETUP_RUNCODE, true);
    }

    //Domain
    public void setDomain(String domain){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_DOMAIN,domain);
        editor.commit();
    }
    public String getDomain(){
        return sPreferences.getString(PREF_DOMAIN,"");
    }
    //Language
    public void setLanguage(String language){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_LANGUAGE,language);
        editor.commit();
    }
    public String getLanguage(){
        return sPreferences.getString(PREF_LANGUAGE,"V");
    }
    //Number day signature
    public void setNumberDaySignature(int day){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putInt(PREF_DEFAULT_DAY_SIGNATURE,day);
        editor.commit();
    }
    public int getNumberDaySignature(){
        return sPreferences.getInt(PREF_DEFAULT_DAY_SIGNATURE,7);
    }

    // info signature
    public void setInfoSignature(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_INFO_SIGNATURE,value);
        editor.commit();
    }
    public boolean getInfoSignature(){
        return sPreferences.getBoolean(PREF_INFO_SIGNATURE,false);
    }

    // wait signature
    public void setWaitAppreoved(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_WAIT_APPROVED,value);
        editor.commit();
    }
    public boolean getWaitAppreoved(){
        return sPreferences.getBoolean(PREF_WAIT_APPROVED,false);
    }
    // wait signature
    public void setWaitSignature(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_WAIT_SIGNATURE,value);
        editor.commit();
    }
    public boolean getWaitSignature(){
        return sPreferences.getBoolean(PREF_WAIT_SIGNATURE,false);
    }
    // complete signature
    public void setCompleteSignature(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_COMPLETE_SIGNATURE,value);
        editor.commit();
    }
    public boolean getCompleteSignature(){
        return sPreferences.getBoolean(PREF_COMPLETE_SIGNATURE,false);
    }

    // info approved
    public void setInfoApproved(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_INFO_APPROVED,value);
        editor.commit();
    }
    public boolean getInfoApproved(){
        return sPreferences.getBoolean(PREF_INFO_APPROVED,false);
    }

    // request form
    public void setDefaulRequestFormPart(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_DEFAUL_REQUEST_FORM_PART,value);
        editor.commit();
    }
    public boolean getDefaulRequestFormPart(){
        return sPreferences.getBoolean(PREF_DEFAUL_REQUEST_FORM_PART,false);
    }

    // request form cong khoang
    public void setDefaulRequestFormCongKhoan(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_DEFAUL_REQUEST_FORM_CONG_KHOAN,value);
        editor.commit();
    }
    public boolean getDefaulRequestFormCongKhoan(){
        return sPreferences.getBoolean(PREF_DEFAUL_REQUEST_FORM_CONG_KHOAN,false);
    }

    // Base Color
    public void setBaseColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_BASE,value);
        editor.commit();
    }
    public String getBaseColor(){
        return sPreferences.getString(PREF_COLOR_BASE,"#019676");
    }
    // Second Color
    public void setSecondColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_SECOND,value);
        editor.commit();
    }
    public String getSecondColor(){
        return sPreferences.getString(PREF_COLOR_SECOND,"#f89633");
    }
    // Background Color
    public void setBacgroundColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_BACKGROUND,value);
        editor.commit();
    }
    public String getBacgroundColor(){
        return sPreferences.getString(PREF_COLOR_BACKGROUND,"#fafafa");
    }

    // Text Color
    public void setTextColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_TEXT,value);
        editor.commit();
    }
    public String getTextColor(){
        return sPreferences.getString(PREF_COLOR_TEXT,"#000000");
    }
    // Border Text Color
    public void setBorderTextColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_BORDER_TEXT,value);
        editor.commit();
    }
    public String getBorderTextColor(){
        return sPreferences.getString(PREF_COLOR_BORDER_TEXT,"#dddddd");
    }
    //Color Accent
    public void setAccentColor(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COLOR_ACCENT,value);
        editor.commit();
    }
    public String getAccentColor(){
        return sPreferences.getString(PREF_COLOR_ACCENT,"#fff8f0");
    }

    public void setPrefUsername(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_USERNAME,value);
        editor.commit();
    }
    public String getPrefUsername(){
        return sPreferences.getString(PREF_USERNAME,"");
    }

    public void setPrefPassword(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_PASSWORD,value);
        editor.commit();
    }
    public String getPrefPassword(){
        return sPreferences.getString(PREF_PASSWORD,"");
    }

    public void setPrefRemember(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_REMEMBER,value);
        editor.commit();
    }
    public boolean getPrefrRemember(){
        return sPreferences.getBoolean(PREF_REMEMBER,false);
    }

    public void setPrefToken(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_TOKEN,value);
        editor.commit();
    }
    public String getPrefToken(){
        return sPreferences.getString(PREF_TOKEN,"");
    }
    public void setPrefCompcode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COMPCODE,value);
        editor.commit();
    }
    public String getPrefCompcode(){
        return sPreferences.getString(PREF_COMPCODE,"");
    }
    public void setPrefCompname(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_COMPNAME,value);
        editor.commit();
    }
    public String getPrefCompname(){
        return sPreferences.getString(PREF_COMPNAME,"");
    }

    public void setPrefLctcode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_LCTCODE,value);
        editor.commit();
    }
    public String getPrefLctcode(){
        return sPreferences.getString(PREF_LCTCODE,"");
    }

    public void setPrefLctname(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_LCTNAME,value);
        editor.commit();
    }
    public String getPrefLctname(){
        return sPreferences.getString(PREF_LCTNAME,"");
    }

    public void setPrefUsercode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_USERCODE,value);
        editor.commit();
    }
    public String getPrefUsercode(){
        return sPreferences.getString(PREF_USERCODE,"");
    }
    public void setPrefAccountName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_NAME,value);
        editor.commit();
    }
    public String getPrefAccountName(){
        return sPreferences.getString(PREF_ACCOUNT_NAME,"");
    }

    public void setPrefAppRight(int value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putInt(PREF_APP_RIGHT,value);
        editor.commit();
    }
    public int getPrefAppright(){
        return sPreferences.getInt(PREF_APP_RIGHT,0);
    }
    public void setPrefEmpCode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_EMP_CODE,value);
        editor.commit();
    }
    public String getPrefEmpCode(){
        return sPreferences.getString(PREF_EMP_CODE,"");
    }

    public void setPrefPartCode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_PART_CODE,value);
        editor.commit();
    }
    public String getPrefPartCode(){
        return sPreferences.getString(PREF_PART_CODE,"");
    }
    public void setPrefPartName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_PART_NAME,value);
        editor.commit();
    }
    public String getPrefPartName(){
        return sPreferences.getString(PREF_PART_NAME,"");
    }

    public void setPrefPostCode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_POST_CODE,value);
        editor.commit();
    }
    public String getPrefPostCode(){
        return sPreferences.getString(PREF_POST_CODE,"");
    }

    public void setPrefPostName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_POST_NAME,value);
        editor.commit();
    }
    public String getPrefPostName(){
        return sPreferences.getString(PREF_POST_NAME,"");
    }

    public void setPrefJobCode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_JOB_CODE,value);
        editor.commit();
    }
    public String getPrefJobCode(){
        return sPreferences.getString(PREF_JOB_CODE,"");
    }

    public void setPrefJobName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_JOB_NAME,value);
        editor.commit();
    }
    public String getPrefJobName(){
        return sPreferences.getString(PREF_JOB_NAME,"");
    }

    public void setPrefLogoComp(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_LOGO_COMP,value);
        editor.commit();
    }
    public String getPrefLogoComp(){
        return sPreferences.getString(PREF_LOGO_COMP,"");
    }
    
    public void setPrefSignLoadDateEnd(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_SIGN_LOAD_DATE_END,value);
        editor.commit();
    }
    public String getPrefSignLoadDateEnd(){
        return sPreferences.getString(PREF_SIGN_LOAD_DATE_END,"");
    }
    
    public void setPrefSignLoadDateBebin(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_SIGN_LOAD_DATE_BEBIN,value);
        editor.commit();
    }
    public String getPrefSignLoadDateBebin(){
        return sPreferences.getString(PREF_SIGN_LOAD_DATE_BEBIN,"");
    }
    
    public void setPrefSignLoadSttsign(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_SIGN_LOAD_STTSIGN,value);
        editor.commit();
    }
    public String getPrefSignLoadSttsign(){
        return sPreferences.getString(PREF_SIGN_LOAD_STTSIGN,"");
    }
    

    public void clearLoginData(){
        setPrefToken("");
        setPrefAppRight(0);
        setPrefCompcode("");
        setPrefCompname("");
        setPrefLctcode("");
        setPrefLctname("");
        setPrefUsercode("");
        setPrefAccountName("");
        setPrefEmpCode("");
        setPrefPartCode("");
        setPrefPartName("");
        setPrefPostCode("");
        setPrefPostName("");
        setPrefJobCode("");
        setPrefJobName("");
        setPrefLogoComp("");
    }
}
