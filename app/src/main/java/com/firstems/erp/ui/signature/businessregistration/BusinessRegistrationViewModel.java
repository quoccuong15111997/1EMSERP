package com.firstems.erp.ui.signature.businessregistration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegistrationApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegstItem;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCTApiResponse;
import com.firstems.erp.callback.GetDataTimeKeepingTCCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.List;

public class BusinessRegistrationViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<BussinessRegstItem> liveDataBussiness;
    private MutableLiveData<List<TimekeepingTypeCT>> liveDataKeppingTypeCT;

    public BusinessRegistrationViewModel() {
        title= new MutableLiveData<>();
        liveDataBussiness= new MutableLiveData<>();
        liveDataKeppingTypeCT = new MutableLiveData<>();
        initTitle();
    }

    public void loaDataKeppingType(GetDataTimeKeepingTCCallback callback) {
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_TIME_KEPPING_TYPE_LIST, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getTimeKeppingTypeList(dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {

                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataTimeKepping(data,callback);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }
    public void getDataTimeKepping(String data,GetDataTimeKeepingTCCallback callback) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new TimekeepingTypeCTApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        TimekeepingTypeCTApiResponse timekeepingTypeCTApiResponse= (TimekeepingTypeCTApiResponse) obj;
                       callback.onLoaded(timekeepingTypeCTApiResponse.getTimekeepingTypeCTList());
                    }
                });
    }
    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(98));
    }
    public LiveData<String> getTitle(){
        return title;
    }

    public void loadData(String dcmnCode, String keyCode){
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_SIGNATURE_DETAIL, dcmnCode + keyCode, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        try{
                            DataNetworkProvider
                                    .getInstance()
                                    .getDetailSignatureBussinessRegist(dataApiCallback,dcmnCode,keyCode);
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onApiLoadFail() {

                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataSignature(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getDataSignature(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new BussinessRegistrationApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        BussinessRegistrationApiResponse bussinessRegistrationApiResponse = (BussinessRegistrationApiResponse) obj;
                        if (bussinessRegistrationApiResponse.getBussinessRegstItems()!=null){
                            liveDataBussiness.setValue(bussinessRegistrationApiResponse.getBussinessRegstItems().get(0));
                        }
                    }
                });
    }

    public LiveData<BussinessRegstItem> getLiveDataBussiness() {
        return liveDataBussiness;
    }

    public LiveData<List<TimekeepingTypeCT>> getLiveDataKeppingTypeCT() {
        return liveDataKeppingTypeCT;
    }
}