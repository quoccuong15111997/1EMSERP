package com.firstems.erp.ui.signature;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.signature.SignatureApiResponse;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.LoadSignatureDataCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.model.FilterModel;
import com.firstems.erp.system.SysConfig;
import com.firstems.erp.viewmodel.SignatureVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignatureViewModel extends ViewModel {
    private MutableLiveData<List<SignatureVM>> listSignatureModels;
    private MutableLiveData<String> title;
    private MutableLiveData<HashMap<String, List<SignatureItemApiResponse>>> hashMapSignature;

    public SignatureViewModel() {
        listSignatureModels= new MutableLiveData<>();
        title= new MutableLiveData<>();
        hashMapSignature = new MutableLiveData<>();

        initTilte();
        fakeData();
        loadDataSignature(new FilterModel(SysConfig.createDateLoadSign().get(0),SysConfig.createDateLoadSign().get(1),true,true,true));
    }

    public void loadDataSignature(FilterModel filterModel){
        int para = (filterModel.isWaitsignature() ? 1 : 0 ) +(filterModel.isWaitApproved() ? 2 : 0) +(filterModel.isDone() ? 4 : 0);
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_SIGNATURE_LIST,
                String.valueOf(filterModel.getBeginDate())+String.valueOf(filterModel.getEndDate()) + para, new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DataNetworkProvider.getInstance().getListSignatureApi(filterModel,dataApiCallback);
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
    public void loadDataSignatureResume(FilterModel filterModel, LoadSignatureDataCallback loadSignatureDataCallback){
        int para = (filterModel.isWaitsignature() ? 1 : 0 ) +(filterModel.isWaitApproved() ? 2 : 0) +(filterModel.isDone() ? 4 : 0);
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_SIGNATURE_LIST,
                String.valueOf(filterModel.getBeginDate())+String.valueOf(filterModel.getEndDate()) + para, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider.getInstance().getListSignatureApi(filterModel,dataApiCallback);
                    }
                    
                    @Override
                    public void onApiLoadFail() {
                    
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        HashMap<String, List<SignatureItemApiResponse>> hashMap = new HashMap<>();
                        DataConvertProvider.getInstance().convertJsonToObject(data, new SignatureApiResponse(), new ConvertJsonCallback() {
                            @Override
                            public void onConvertSuccess(Object obj) {
                                SignatureApiResponse signatureApiResponse= (SignatureApiResponse) obj;
                                List<SignatureItemApiResponse> listItem = signatureApiResponse.getSignatureItemApiResponses();
                                for (SignatureItemApiResponse item : listItem){
                                    if (hashMap.containsKey(item.getDcmnCode())){
                                        hashMap.get(item.getDcmnCode()).add(item);
                                    }
                                    else {
                                        List<SignatureItemApiResponse> list= new ArrayList<>();
                                        list.add(item);
                                        hashMap.put(item.getDcmnCode(),list);
                                    }
                                }
                                loadSignatureDataCallback.onLoaded(hashMap);
                            }
                        });
                    }
                    
                    @Override
                    public void onUpdateImage(int status) {
                    
                    }
                });
    }

    private void getData(String data) {
        HashMap<String, List<SignatureItemApiResponse>> hashMap = new HashMap<>();
        DataConvertProvider.getInstance().convertJsonToObject(data, new SignatureApiResponse(), new ConvertJsonCallback() {
            @Override
            public void onConvertSuccess(Object obj) {
                SignatureApiResponse signatureApiResponse= (SignatureApiResponse) obj;
                List<SignatureItemApiResponse> listItem = signatureApiResponse.getSignatureItemApiResponses();
                for (SignatureItemApiResponse item : listItem){
                    if (hashMap.containsKey(item.getDcmnCode())){
                        hashMap.get(item.getDcmnCode()).add(item);
                    }
                    else {
                        List<SignatureItemApiResponse> list= new ArrayList<>();
                        list.add(item);
                        hashMap.put(item.getDcmnCode(),list);
                    }
                }

                System.out.println(listItem.size());
                System.out.println(hashMap.size());
                hashMapSignature.setValue(hashMap);
            }
        });
    }
    private void initTilte() {
        title.setValue("Trình ký");
    }

    private void fakeData() {
        List<SignatureVM> list= new ArrayList<>();
        list.add(new SignatureVM("Đơn xin đổi ca",0,1));
        list.add(new SignatureVM("Đơn xin nghĩ phép",0,2));
        list.add(new SignatureVM("Liên hệ công vụ",0,3));
        list.add(new SignatureVM("Phiếu đăng ký công tác",0,4));
        list.add(new SignatureVM("Phiếu đề nghịn thanh toán",0,5));
        list.add(new SignatureVM("Phiếu tạm ứng",0,6));
        listSignatureModels.setValue(list);
    }

    public LiveData<List<SignatureVM>> getListModels(){
        return listSignatureModels;
    }
    public LiveData<String> getTitle(){
        return title;
    }


    public MutableLiveData<HashMap<String, List<SignatureItemApiResponse>>> getHashMapSignature() {
        return hashMapSignature;
    }
}