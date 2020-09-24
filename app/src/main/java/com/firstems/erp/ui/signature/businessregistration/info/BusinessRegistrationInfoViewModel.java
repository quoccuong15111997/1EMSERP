package com.firstems.erp.ui.signature.businessregistration.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.locationtype.LocationType;
import com.firstems.erp.api.model.response.locationtype.LocationTypeApiResponse;
import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.api.model.response.national.NationalApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCTApiResponse;
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

import java.util.List;

public class BusinessRegistrationInfoViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<String> title;
    private MutableLiveData<List<LocationType>> liveDataLocateType;
    private MutableLiveData<List<National>> liveDataNationalList;
    private MutableLiveData<List<TimekeepingTypeCT>> liveDataTimeKeppingType;
    public BusinessRegistrationInfoViewModel(){
        title= new MutableLiveData<>();
        liveDataLocateType= new MutableLiveData<>();
        liveDataNationalList= new MutableLiveData<>();
        liveDataTimeKeppingType= new MutableLiveData<>();

        initTitle() ;
        loadData();
    }

    public LiveData<List<TimekeepingTypeCT>> getLiveDataTimeKeppingType() {
        return liveDataTimeKeppingType;
    }

    private void loadData() {
        // LocateType
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_LOCATION_TYPE_LIST, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider.getInstance().getListLocateType(dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataLocateType(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
        // National List
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_NATIONAL_LIST, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getListNational(dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataNationalList(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
        // Time Kepping type List
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
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataTimeKepping(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getDataTimeKepping(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new TimekeepingTypeCTApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        TimekeepingTypeCTApiResponse timekeepingTypeCTApiResponse= (TimekeepingTypeCTApiResponse) obj;
                        liveDataTimeKeppingType.setValue(timekeepingTypeCTApiResponse.getTimekeepingTypeCTList());
                    }
                });
    }

    private void getDataNationalList(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new NationalApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        NationalApiResponse nationalApiResponse = (NationalApiResponse) obj;
                        liveDataNationalList.setValue(nationalApiResponse.getNationalList());
                    }
                });
    }

    private void getDataLocateType(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new LocationTypeApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LocationTypeApiResponse locationTypeApiResponse = (LocationTypeApiResponse) obj;
                        liveDataLocateType.setValue(locationTypeApiResponse.getLocationTypeList());
                    }
                });
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(98));
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public LiveData<List<LocationType>> getLiveDataLocateType() {
        return liveDataLocateType;
    }

    public LiveData<List<National>> getLiveDataNationalList() {
        return liveDataNationalList;
    }
}