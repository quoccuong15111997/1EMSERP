package com.firstems.erp.ui.shared.relatedfields;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuan;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuanApiResponse;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.List;

public class RelatedFieldsViewModel extends ViewModel {
    private MutableLiveData<List<LanhVucLienQuan>> listMutableLiveData;
    public RelatedFieldsViewModel() {
        listMutableLiveData = new MutableLiveData<>();

        loadData();
    }

    private void loadData() {
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_RELATED_FIELD, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getAllLanhVucLienQuan(dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {

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
                .convertJsonToObject(data, new LanhVucLienQuanApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LanhVucLienQuanApiResponse response = (LanhVucLienQuanApiResponse) obj;
                        listMutableLiveData.setValue(response.getLanhVucLienQuans());
                    }
                });
    }

    public LiveData<List<LanhVucLienQuan>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}