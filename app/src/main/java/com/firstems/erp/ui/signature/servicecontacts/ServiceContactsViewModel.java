package com.firstems.erp.ui.signature.servicecontacts;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.EmployeeRequest;
import com.firstems.erp.api.model.response.employee.EmployeeApiResponse;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuan;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuanApiResponse;
import com.firstems.erp.api.model.response.servicecontacts.ServiceContactsApiResponse;
import com.firstems.erp.api.model.response.servicecontacts.ServiceContactsItem;
import com.firstems.erp.callback.LoadContentCallback;
import com.firstems.erp.callback.LoadListEmployeeCallback;
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

import java.util.ArrayList;
import java.util.List;

public class ServiceContactsViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<String> title;
    private MutableLiveData<List<Bitmap>> listFileInclude;
    private MutableLiveData<Bitmap> bitmapImage;
    private List<Bitmap> bitmaps;
    private MutableLiveData<List<LanhVucLienQuan>> listRelatedMutableLiveData;
    private MutableLiveData<ServiceContactsItem> contactsItemMutableLiveData;

    public ServiceContactsViewModel() {
        title= new MutableLiveData<>();
        contactsItemMutableLiveData= new MutableLiveData<>();
        listRelatedMutableLiveData= new MutableLiveData<>();
        bitmaps= new ArrayList<>();
        listFileInclude= new MutableLiveData<>();
        bitmapImage= new MutableLiveData<>();
        initListFileInclude();
        initTitle();
    }

    public void loadListEmployee(LoadListEmployeeCallback callback) {
        EmployeeRequest employeeRequest = new EmployeeRequest("");
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_EMPLOYEE_LIST, "", new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getListEmployeeApi(employeeRequest.convertToJson(),dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        callback.onServerFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(data, new EmployeeApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        EmployeeApiResponse employeeApiResponse = (EmployeeApiResponse) obj;
                                        callback.onLoaded(employeeApiResponse.getEmployeeItemApiResponses());
                                    }
                                });
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    public void getDetailServiceContact(String dcmnCode, String keyCode){
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_SIGNATURE_DETAIL, dcmnCode + keyCode, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getDetailServiceContact(dcmnCode,keyCode,dataApiCallback);
                    }

                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(data, new ServiceContactsApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        ServiceContactsApiResponse serviceContactsApiResponse = (ServiceContactsApiResponse) obj;
                                        if (serviceContactsApiResponse.getServiceContactsItems().size() > 0){
                                            serverCheckCallback.onServerLoadFail();
                                        }
                                        contactsItemMutableLiveData.setValue(serviceContactsApiResponse.getServiceContactsItems().get(0));
                                    }
                                });
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    public LiveData<ServiceContactsItem> getContactsItemMutableLiveData() {
        return contactsItemMutableLiveData;
    }

    public void loadListRatedField(LoadContentCallback loadContentCallback) {
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
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(data, new LanhVucLienQuanApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        LanhVucLienQuanApiResponse response = (LanhVucLienQuanApiResponse) obj;
                                        listRelatedMutableLiveData.setValue(response.getLanhVucLienQuans());
                                        loadContentCallback.Loaded();
                                    }
                                });
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    public LiveData<List<LanhVucLienQuan>> getListRelatedMutableLiveData() {
        return listRelatedMutableLiveData;
    }

    private void initListFileInclude() {
        listFileInclude.setValue(bitmaps);
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(82));
    }

    public MutableLiveData<Bitmap> getBitmapImage() {
        return bitmapImage;
    }

    public LiveData<String> getTitle(){
        return title;
    }

    public MutableLiveData<List<Bitmap>> getListFileInclude() {
        return listFileInclude;
    }

}