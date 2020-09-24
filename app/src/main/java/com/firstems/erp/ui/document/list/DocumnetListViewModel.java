package com.firstems.erp.ui.document.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.document.DocumentRequest;
import com.firstems.erp.api.model.response.document.DocumentApiResponse;
import com.firstems.erp.api.model.response.document.DocumentItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumnetListViewModel extends ViewModel {
    private MutableLiveData<List<DocumentItemApiResponse>> mutableLiveDataDocument;
    public DocumnetListViewModel() {
        mutableLiveDataDocument = new MutableLiveData<>();
    }
    
    public void loaData(String queryString) {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_DOCUMENT_LIST, queryString, new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                DocumentRequest documentRequest = new DocumentRequest();
                documentRequest.setDcmnCode("inpDcmnCntn");
                documentRequest.setQueryString(queryString);
                System.out.println(documentRequest.convertToJson());
                ApiServices.getInstance().getAllDocumentList(SharedPreferencesManager.getInstance().getPrefToken(), documentRequest.convertToJson(), new Callback<DocumentApiResponse>() {
                    @Override
                    public void onResponse(Call<DocumentApiResponse> call, Response<DocumentApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<DocumentApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
        
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new DocumentApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        DocumentApiResponse documentApiResponse = (DocumentApiResponse) obj;
                        mutableLiveDataDocument.setValue(documentApiResponse.getDocumentItemApiResponses());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
    }
    
    public LiveData<List<DocumentItemApiResponse>> getMutableLiveDataDocument() {
        return mutableLiveDataDocument;
    }
}