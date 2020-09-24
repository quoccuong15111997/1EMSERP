package com.firstems.erp.ui.signature.switchshift;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDCApiResponse;
import com.firstems.erp.callback.LoadDataKeppingCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.model.switchshift.SwitchShift;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class SwitchShiftViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<List<SwitchShift>> mutableLiveDataSwitchShift;
    private MutableLiveData<SwitchShiftApiResponse> mutableLiveDataSwitchShiftResponse;
    private MutableLiveData<List<TimekeepingTypeDC>> typeDCMutableLiveData;

    public SwitchShiftViewModel() {
        title= new MutableLiveData<>();
        mutableLiveDataSwitchShift= new MutableLiveData<>();
        mutableLiveDataSwitchShiftResponse = new MutableLiveData<>();
        typeDCMutableLiveData= new MutableLiveData<>();
        initTitle();
        initData();
    }

    public void loadDataTimeKeepingList(LoadDataKeppingCallback loadDataKeppingCallback) {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_TIME_KEPPING_TYPE_LIST_DC, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DataNetworkProvider.getInstance().getListTimeKeppingDC(dataApiCallback);
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
                        typeDCMutableLiveData.setValue(timekeepingTypeDCApiResponse.getTimekeepingTypeDCList());
                        loadDataKeppingCallback.onLoaded();
                    }
                });
            }

            @Override
            public void onUpdateImage(int status) {

            }
        });
    }

    private void initData() {
        List<SwitchShift> switchShifts= new ArrayList<>();
        mutableLiveDataSwitchShift.setValue(switchShifts);
    }

    public LiveData<List<SwitchShift>> getMutableLiveDataSwitchShift() {
        return mutableLiveDataSwitchShift;
    }

    public void setMutableLiveDataSwitchShift(List<SwitchShift> list) {
        mutableLiveDataSwitchShift.setValue(list);
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(30));
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
                                    .getDetailSignatureSwitchShift(dcmnCode,keyCode,dataApiCallback);
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
                        getDataSwitchShift(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getDataSwitchShift(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new SwitchShiftApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        SwitchShiftApiResponse switchShiftApiResponse = (SwitchShiftApiResponse) obj;
                        mutableLiveDataSwitchShiftResponse.setValue(switchShiftApiResponse);
                    }
                });
    }

    public MutableLiveData<SwitchShiftApiResponse> getMutableLiveDataSwitchShiftResponse() {
        return mutableLiveDataSwitchShiftResponse;
    }
    public TimekeepingTypeDC getInfoTimeKeepping(String itemCode){
        for (TimekeepingTypeDC typeDC : typeDCMutableLiveData.getValue()){
            if (typeDC.getItemCode().equals(itemCode)){
                return typeDC;
            }
        }
        return new TimekeepingTypeDC();
    }
}