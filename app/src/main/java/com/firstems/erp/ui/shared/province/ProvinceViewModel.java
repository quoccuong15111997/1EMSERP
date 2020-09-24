package com.firstems.erp.ui.shared.province;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.province.Province;
import com.firstems.erp.api.model.response.province.ProvinceApiResponse;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.List;

public class ProvinceViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<List<Province>> liveDataListProvince;
    public ProvinceViewModel() {
        liveDataListProvince= new MutableLiveData<>();

        loadData();
    }

    private void loadData() {
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_PROVINCE_LIST, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getProvinceList(dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataProvince(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getDataProvince(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new ProvinceApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ProvinceApiResponse provinceApiResponse = (ProvinceApiResponse) obj;
                        liveDataListProvince.setValue(provinceApiResponse.getProvinceList());
                    }
                });
    }

    public LiveData<List<Province>> getLiveDataListProvince() {
        return liveDataListProvince;
    }
}