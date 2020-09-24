package com.firstems.erp.ui.shared.loaidoituongnhan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanApiResponse;
import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanItem;
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
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoiTuongNhanViewModel extends ViewModel {
    private MutableLiveData<List<DoiTuongNhanItem>> mutableLiveDataDoiTuongNhanList;
    public DoiTuongNhanViewModel() {
        mutableLiveDataDoiTuongNhanList = new MutableLiveData<>();
    }
    public LiveData<List<DoiTuongNhanItem>> getMutableLiveDataDoiTuongNhanList() {
        return mutableLiveDataDoiTuongNhanList;
    }
    public void loadListDoiTuongNhan(String key){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_DOI_TUONG_NHAN, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("CONDFLTR",key);
                ApiServices.getInstance().getAllDoiTuongNhan(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<DoiTuongNhanApiResponse>() {
                    @Override
                    public void onResponse(Call<DoiTuongNhanApiResponse> call, Response<DoiTuongNhanApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<DoiTuongNhanApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new DoiTuongNhanApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        DoiTuongNhanApiResponse doiTuongNhanApiResponse = (DoiTuongNhanApiResponse) obj;
                        mutableLiveDataDoiTuongNhanList.setValue(doiTuongNhanApiResponse.getDoiTuongNhanItems());
                        
                    }
                });
            }
            
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
}