package com.firstems.erp.ui.signature;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.adapter.signature.SignatureModel;
import com.firstems.erp.api.model.response.signature.SignatureApiResponse;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.LoadSignatureDataDiffListCallback;
import com.firstems.erp.callback.ServerCheckCallback;
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
import java.util.List;

public class SignatureGirdDiffViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    private MutableLiveData<List<SignatureVM>> listSignatureModels;
    private MutableLiveData<String> title;
    private MutableLiveData<List<SignatureModel>> signatureModelMutableLiveData;
    
    public SignatureGirdDiffViewModel() {
        listSignatureModels = new MutableLiveData<>();
        title = new MutableLiveData<>();
        signatureModelMutableLiveData = new MutableLiveData<>();
        
        initTilte();
        fakeData();
        loadDataSignature(new FilterModel(SysConfig.createDateLoadSign().get(0), SysConfig.createDateLoadSign().get(1), true, true, true));
    }
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    public void loadDataSignature(FilterModel filterModel) {
        int para = (filterModel.isWaitsignature() ? 1 : 0) + (filterModel.isWaitApproved() ? 2 : 0) + (filterModel.isDone() ? 4 : 0);
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_SIGNATURE_LIST,
                String.valueOf(filterModel.getBeginDate()) + String.valueOf(filterModel.getEndDate()) + para, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider.getInstance().getListSignatureApi(filterModel, dataApiCallback);
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
    
    public void loadDataSignatureResume(FilterModel filterModel, LoadSignatureDataDiffListCallback signatureDataDiffListCallback) {
        int para = (filterModel.isWaitsignature() ? 1 : 0) + (filterModel.isWaitApproved() ? 2 : 0) + (filterModel.isDone() ? 4 : 0);
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_SIGNATURE_LIST,
                String.valueOf(filterModel.getBeginDate()) + String.valueOf(filterModel.getEndDate()) + para, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider.getInstance().getListSignatureApi(filterModel, dataApiCallback);
                    }
                    
                    @Override
                    public void onApiLoadFail() {
                        serverCheckCallback.onServerLoadFail();
                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        DataConvertProvider.getInstance().convertJsonToObject(data, new SignatureApiResponse(), new ConvertJsonCallback() {
                            @Override
                            public void onConvertSuccess(Object obj) {
                                SignatureApiResponse signatureApiResponse = (SignatureApiResponse) obj;
                                List<SignatureItemApiResponse> listItem = signatureApiResponse.getSignatureItemApiResponses();
                                List<SignatureModel> signatureModels = new ArrayList<>();
                                for (SignatureItemApiResponse item : listItem) {
                                    if (signatureModels.size() == 0){
                                        SignatureModel model = new SignatureModel();
                                        model.setDcmnCode(item.getDcmnCode());
                                        model.setSignatureItemApiResponseList(new ArrayList<>());
                                        model.getSignatureItemApiResponseList().add(item);
                                        signatureModels.add(model);
                                    }
                                    else {
                                        int flag  = 0;
                                        for (int i = 0 ; i < signatureModels.size(); i++){
                                            SignatureModel model = signatureModels.get(i);
                                            if (model.getDcmnCode().equals(item.getDcmnCode())){
                                                if (model.getSignatureItemApiResponseList()== null){
                                                    model.setSignatureItemApiResponseList(new ArrayList<>());
                                                    model.getSignatureItemApiResponseList().add(item);
                                                }
                                                else {
                                                    model.getSignatureItemApiResponseList().add(item);
                                                }
                                                flag = 1;
                                            }
                                        }
                                        if (flag == 0){
                                            SignatureModel model = new SignatureModel();
                                            model.setDcmnCode(item.getDcmnCode());
                                            model.setSignatureItemApiResponseList(new ArrayList<>());
                                            model.getSignatureItemApiResponseList().add(item);
                                            signatureModels.add(model);
                                        }
                                    }
                                }
                                signatureDataDiffListCallback.onLoaded(signatureModels);
                            }
                        });
                    }
                    
                    @Override
                    public void onUpdateImage(int status) {
                    
                    }
                });
    }
    
    private void getData(String data) {
        DataConvertProvider.getInstance().convertJsonToObject(data, new SignatureApiResponse(), new ConvertJsonCallback() {
            @Override
            public void onConvertSuccess(Object obj) {
                SignatureApiResponse signatureApiResponse = (SignatureApiResponse) obj;
                List<SignatureItemApiResponse> listItem = signatureApiResponse.getSignatureItemApiResponses();
                List<SignatureModel> signatureModels = new ArrayList<>();
                for (SignatureItemApiResponse item : listItem) {
                    if (signatureModels.size() == 0){
                        SignatureModel model = new SignatureModel();
                        model.setDcmnCode(item.getDcmnCode());
                        model.setSignatureItemApiResponseList(new ArrayList<>());
                        model.getSignatureItemApiResponseList().add(item);
                        signatureModels.add(model);
                    }
                    else {
                        int flag  = 0;
                        for (int i = 0 ; i < signatureModels.size(); i++){
                            SignatureModel model = signatureModels.get(i);
                            if (model.getDcmnCode().equals(item.getDcmnCode())){
                                if (model.getSignatureItemApiResponseList()== null){
                                    model.setSignatureItemApiResponseList(new ArrayList<>());
                                    model.getSignatureItemApiResponseList().add(item);
                                }
                                else {
                                    model.getSignatureItemApiResponseList().add(item);
                                }
                                flag = 1;
                            }
                        }
                        if (flag == 0){
                            SignatureModel model = new SignatureModel();
                            model.setDcmnCode(item.getDcmnCode());
                            model.setSignatureItemApiResponseList(new ArrayList<>());
                            model.getSignatureItemApiResponseList().add(item);
                            signatureModels.add(model);
                        }
                    }
                }
                signatureModelMutableLiveData.setValue(signatureModels);
            }
        });
    }
    
    private void initTilte() {
        title.setValue("Trình ký");
    }
    
    private void fakeData() {
        List<SignatureVM> list = new ArrayList<>();
        list.add(new SignatureVM("Đơn xin đổi ca", 0, 1));
        list.add(new SignatureVM("Đơn xin nghĩ phép", 0, 2));
        list.add(new SignatureVM("Liên hệ công vụ", 0, 3));
        list.add(new SignatureVM("Phiếu đăng ký công tác", 0, 4));
        list.add(new SignatureVM("Phiếu đề nghịn thanh toán", 0, 5));
        list.add(new SignatureVM("Phiếu tạm ứng", 0, 6));
        listSignatureModels.setValue(list);
    }
    
    public LiveData<List<SignatureVM>> getListModels() {
        return listSignatureModels;
    }
    
    public LiveData<String> getTitle() {
        return title;
    }
    
    public MutableLiveData<List<SignatureModel>> getSignatureModelMutableLiveData() {
        return signatureModelMutableLiveData;
    }
}