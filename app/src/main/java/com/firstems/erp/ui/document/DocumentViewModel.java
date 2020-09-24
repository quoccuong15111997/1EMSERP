package com.firstems.erp.ui.document;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.location.LocationApiResponse;
import com.firstems.erp.api.model.response.location.LocationItem;
import com.firstems.erp.api.services.ApiServices;
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

public class DocumentViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<List<LocationItem>> liveDataLocation;
    public DocumentViewModel(){
        title= new MutableLiveData<>();
        liveDataLocation=new MutableLiveData<>();
        initTitle();
        loadData();
    }
    
    private void loadData() {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOCATION_LIST, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices.getInstance().getAllLocation(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<LocationApiResponse>() {
                    @Override
                    public void onResponse(Call<LocationApiResponse> call, Response<LocationApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<LocationApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
        
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new LocationApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LocationApiResponse locationApiResponse = (LocationApiResponse) obj;
                        liveDataLocation.setValue(locationApiResponse.getLocationResponses());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
    }
    
    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(168) /*Tìm kiếm công văn - Tài liệu*/);
    }
    public LiveData<String> getTitle(){
        return title;
    }
    
    public LiveData<List<LocationItem>> getLiveDataLocation() {
        return liveDataLocation;
    }
}