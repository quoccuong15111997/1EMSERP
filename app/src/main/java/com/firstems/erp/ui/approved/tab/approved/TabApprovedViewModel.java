package com.firstems.erp.ui.approved.tab.approved;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.approved.ApprovedApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedItemApiResponse;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.helper.date.DateTimeHelper;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.Date;
import java.util.List;

public class TabApprovedViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    private MutableLiveData<List<ApprovedItemApiResponse>> dataListApprove;
    public TabApprovedViewModel() {
        dataListApprove= new MutableLiveData<>();

        loadDataApproved();
    }
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    public void loadDataApproved() {
        String beginDate = DateTimeHelper.getInstance().minusDate(SharedPreferencesManager.getInstance().getNumberDaySignature());
        String endDate = DateTimeHelper.getInstance().apiDateFormat.format(new Date(System.currentTimeMillis()));
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_APPROVED_LIST, beginDate+endDate, new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DataNetworkProvider.getInstance().getListApprovedApi(beginDate,endDate,dataApiCallback);
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
        DataConvertProvider.getInstance().convertJsonToObject(data, new ApprovedApiResponse(), new ConvertJsonCallback() {
            @Override
            public void onConvertSuccess(Object obj) {
                ApprovedApiResponse approvedApiResponse = (ApprovedApiResponse) obj;
                dataListApprove.setValue(approvedApiResponse.getApprovedItemApiResponses());
            }
        });
    }

    public MutableLiveData<List<ApprovedItemApiResponse>> getDataListApprove() {
        return dataListApprove;
    }
}