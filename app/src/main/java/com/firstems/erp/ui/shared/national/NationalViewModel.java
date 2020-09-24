package com.firstems.erp.ui.shared.national;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.api.model.response.national.NationalApiResponse;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.List;

public class NationalViewModel extends ViewModel {
    private MutableLiveData<List<National>> liveDataNational;
    public NationalViewModel() {
        liveDataNational= new MutableLiveData<>();

        loadData();
    }

    public LiveData<List<National>> getLiveDataNational() {
        return liveDataNational;
    }

    private void loadData() {
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

                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getNationalData(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getNationalData(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new NationalApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        NationalApiResponse nationalApiResponse = (NationalApiResponse) obj;
                        liveDataNational.setValue(nationalApiResponse.getNationalList());
                    }
                });
    }
}