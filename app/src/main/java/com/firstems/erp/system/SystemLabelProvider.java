package com.firstems.erp.system;

import com.firstems.erp.api.model.request.label.LabelRequest;
import com.firstems.erp.api.model.response.label.SystemLabel;
import com.firstems.erp.api.model.response.label.SystemLabelApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.LoadSystemLabelCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SystemLabelProvider {
    private static SystemLabelProvider instance;
    private SystemLabelProvider(){
    
    }
    public static SystemLabelProvider getInstance(){
        if (instance==null){
            instance = new SystemLabelProvider();
        }
        return instance;
    }
   public void getDataSystemLabel(LoadSystemLabelCallback loadSystemLabelCallback){
       DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LABEL, SharedPreferencesManager.getInstance().getLanguage(), new LoadApiCallback() {
           @Override
           public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
               LabelRequest labelRequest = new LabelRequest();
               labelRequest.setAppCode("ERP");
               labelRequest.setLangCode( SharedPreferencesManager.getInstance().getLanguage());
               ApiServices.getInstance().getSystemLabel(SharedPreferencesManager.getInstance().getPrefToken(), labelRequest.convertToJson(), new Callback<SystemLabelApiResponse>() {
                   @Override
                   public void onResponse(Call<SystemLabelApiResponse> call, Response<SystemLabelApiResponse> response) {
                       if (response.isSuccessful()){
                           dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                       }
                       else {
                           dataApiCallback.onApiLoadFail(response.message());
                       }
                   }
    
                   @Override
                   public void onFailure(Call<SystemLabelApiResponse> call, Throwable t) {
                       dataApiCallback.onApiLoadFail(t.getMessage());
                   }
               });
           }
    
           @Override
           public void onApiLoadFail() {
               loadSystemLabelCallback.onLoadFail();
           }
       }, new DataSourceProviderCallback() {
           @Override
           public void onDataSource(String data) {
               DataConvertProvider.getInstance().convertJsonToObject(data, new SystemLabelApiResponse(), new ConvertJsonCallback() {
                   @Override
                   public void onConvertSuccess(Object obj) {
                       SystemLabelApiResponse systemLabelApiResponse = (SystemLabelApiResponse) obj;
                       writeDataToPreferences(systemLabelApiResponse.getSystemLabelList(),loadSystemLabelCallback);
                   }
               });
           }
    
           @Override
           public void onUpdateImage(int status) {
        
           }
       });
   }
    
    private void writeDataToPreferences(List<SystemLabel> systemLabelList, LoadSystemLabelCallback loadSystemLabelCallback) {
        for (SystemLabel lbl : systemLabelList){
            SharedPreferencesManager.setSystemLabelData(lbl.getIteCode(),SharedPreferencesManager.getInstance().getLanguage(), lbl.getItemName());
        }
        SharedPreferencesManager.getInstance().setSyslabelBaseLoad(true);
        loadSystemLabelCallback.onLoaded();
    }
    
}
