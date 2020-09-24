package com.firstems.erp.ui.signature.askpermission.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDCApiResponse;
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

public class AskPermissionInfoViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<Employee> employ_di_lam;
    private MutableLiveData<Employee> employ_cham_cong;
    private MutableLiveData<List<TimekeepingTypeDC>> liveDataListLoaiChamCong;

    public AskPermissionInfoViewModel() {
        title= new MutableLiveData<>();
        employ_di_lam= new MutableLiveData<>();
        employ_cham_cong= new MutableLiveData<>();
        liveDataListLoaiChamCong= new MutableLiveData<>();

        initTitle();
        initListChamCong();
    }

    private void initListChamCong() {
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_TIME_KEPPING_TYPE_LIST_DC, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getListTimeKeppingDC(dataApiCallback);
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
                .convertJsonToObject(data, new TimekeepingTypeDCApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        TimekeepingTypeDCApiResponse timekeepingTypeDCApiResponse = (TimekeepingTypeDCApiResponse) obj;
                        liveDataListLoaiChamCong.setValue(timekeepingTypeDCApiResponse.getTimekeepingTypeDCList());
                    }
                });
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(46));
    }
    public LiveData<String> getTitle(){
        return title;
    }

    public LiveData<Employee> getEmploy_di_lam() {
        return employ_di_lam;
    }

    public LiveData<Employee> getEmploy_cham_cong() {
        return employ_cham_cong;
    }

    public void setEmploy_di_lam(Employee employ) {
        employ_di_lam.setValue(employ);
    }

    public void setEmploy_cham_cong(Employee employ) {
        employ_cham_cong.setValue(employ);
    }

    public LiveData<List<TimekeepingTypeDC>> getLiveDataListLoaiChamCong() {
        return liveDataListLoaiChamCong;
    }
}