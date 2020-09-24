package com.firstems.erp.ui.signature.askpermission;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.askpermistion.AskPermistionApiResponse;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDCApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.LoadContentCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskPermissionViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<String> title;
    private MutableLiveData<AskPermistionHeader> permistionApiResponseMutableLiveData;
    private MutableLiveData<List<TimekeepingTypeDC>> liveDataTimeKeeping;
    public AskPermissionViewModel(){
        title= new MutableLiveData<>();
        permistionApiResponseMutableLiveData= new MutableLiveData<>();
        liveDataTimeKeeping = new MutableLiveData<>();
        initTitle();
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(43));
    }
    public LiveData<String> getTitle(){
        return title;
    }

    public LiveData<AskPermistionHeader> getPermistionApiResponseMutableLiveData() {
        return permistionApiResponseMutableLiveData;
    }

    public void getDataSinature(String dcmnCode, String keyCode){
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_SIGNATURE_DETAIL, dcmnCode + keyCode, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getDetailAskPermistion(dcmnCode,keyCode,dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getData(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getData(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new AskPermistionApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        AskPermistionApiResponse askPermistionHeader = (AskPermistionApiResponse) obj;
                        permistionApiResponseMutableLiveData.setValue(askPermistionHeader.getAskPermistionHeaders().get(0));
                    }
                });
    }
    
    public LiveData<List<TimekeepingTypeDC>> getLiveDataTimeKeeping() {
        return liveDataTimeKeeping;
    }
    
    public TimekeepingTypeDC findTimeKeeping(String itemCode){
        TimekeepingTypeDC timekeepingTypeDC = null;
        for (TimekeepingTypeDC dc : liveDataTimeKeeping.getValue()){
            if (dc.getItemCode().equals(itemCode)){
                timekeepingTypeDC = dc;
                break;
            }
        }
        return timekeepingTypeDC;
    }
    
    public void getTimeKeepingList(LoadContentCallback loadContentCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_TIME_KEPPING_TYPE_LIST_DC, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices.getInstance().getTimekeepingTypeDCList(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<TimekeepingTypeDCApiResponse>() {
                    @Override
                    public void onResponse(Call<TimekeepingTypeDCApiResponse> call, Response<TimekeepingTypeDCApiResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().isRETNCODE()){
                                dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                            }
                            else {
                                dataApiCallback.onApiLoadFail(response.body().getRETNMSSG());
                            }
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<TimekeepingTypeDCApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new TimekeepingTypeDCApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        TimekeepingTypeDCApiResponse timekeepingTypeDCApiResponse = (TimekeepingTypeDCApiResponse) obj;
                        liveDataTimeKeeping.setValue(timekeepingTypeDCApiResponse.getTimekeepingTypeDCList());
                        loadContentCallback.Loaded();
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
    }
}