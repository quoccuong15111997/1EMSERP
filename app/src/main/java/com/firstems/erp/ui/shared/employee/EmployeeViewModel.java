package com.firstems.erp.ui.shared.employee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.EmployeeRequest;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.employee.EmployeeApiResponse;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    private MutableLiveData<List<Employee>> listMutableLiveEmployee;

    public EmployeeViewModel() {
        listMutableLiveEmployee = new MutableLiveData<>();
    }

    public void loadDataEmployee(String parameter) {
        if (parameter==null){
            parameter = "";
        }
        EmployeeRequest employeeRequest = new EmployeeRequest(parameter);
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_EMPLOYEE_LIST, parameter,new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DataNetworkProvider.getInstance().getListEmployeeApi(employeeRequest.convertToJson(),dataApiCallback);
            }

            @Override
            public void onApiLoadFail() {

            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new EmployeeApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        EmployeeApiResponse employeeApiResponse = (EmployeeApiResponse) obj;
                        listMutableLiveEmployee.setValue(employeeApiResponse.getEmployeeItemApiResponses());
                    }
                });
            }
            @Override
            public void onUpdateImage(int status) {

            }
        });
    }
    public LiveData<List<Employee>> getListMutableLiveEmployee() {
        return listMutableLiveEmployee;
    }
}