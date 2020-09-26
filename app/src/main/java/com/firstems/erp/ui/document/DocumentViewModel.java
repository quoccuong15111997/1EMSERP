package com.firstems.erp.ui.document;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.content.ContentApiResponse;
import com.firstems.erp.api.model.response.content.ContentItem;
import com.firstems.erp.api.model.response.document_category.DocumentCategory;
import com.firstems.erp.api.model.response.document_category.DocumentCategoryApiResponse;
import com.firstems.erp.api.model.response.export_place.ExportPlace;
import com.firstems.erp.api.model.response.export_place.ExportPlaceApiResponse;
import com.firstems.erp.api.model.response.location.LocationApiResponse;
import com.firstems.erp.api.model.response.location.LocationItem;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataSourceProvider;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    private MutableLiveData<List<ExportPlace>> liveDataExportPlace;
    private MutableLiveData<List<ContentItem>> liveDataContent;
    private MutableLiveData<List<DocumentCategory>> liveDataDocumentCategory;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<String> title;
    private MutableLiveData<List<LocationItem>> liveDataLocation;
    public DocumentViewModel(){
        title= new MutableLiveData<>();
        liveDataLocation=new MutableLiveData<>();
        liveDataExportPlace= new MutableLiveData<>();
        liveDataContent = new MutableLiveData<>();
        liveDataDocumentCategory= new MutableLiveData<>();
        initTitle();
        loadData();
    }
    
    private void loadData() {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOCATION_LIST, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices.getInstance().getAllLocation(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<LocationApiResponse>() {
                    @Override
                    public void onResponse(Call<LocationApiResponse> call, Response<LocationApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<LocationApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
                serverCheckCallback.onServerLoadFail();
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new LocationApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LocationApiResponse locationApiResponse = (LocationApiResponse) obj;
                        liveDataLocation.setValue(locationApiResponse.getLocationResponses());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_EXPORT_PLACE, "lstDcmnPbls", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("LISTCODE","lstDcmnPbls");
                ApiServices.getInstance().getListExportPlace(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ExportPlaceApiResponse>() {
                    @Override
                    public void onResponse(Call<ExportPlaceApiResponse> call, Response<ExportPlaceApiResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().isRETNCODE()){
                                dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                            }
                            else {
                                dataApiCallback.onApiLoadFail(response.body().getRETNMSSG());
                            }
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<ExportPlaceApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
                serverCheckCallback.onServerLoadFail();
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new ExportPlaceApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ExportPlaceApiResponse exportPlaceApiResponse = (ExportPlaceApiResponse) obj;
                        liveDataExportPlace.setValue(exportPlaceApiResponse.getExportPlaceList());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_EXPORT_PLACE, "lstContent", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("LISTCODE","lstContent");
                ApiServices.getInstance().getListContent(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ContentApiResponse>() {
                    @Override
                    public void onResponse(Call<ContentApiResponse> call, Response<ContentApiResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().isRETNCODE()){
                                dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                            }
                            else {
                                dataApiCallback.onApiLoadFail(response.body().getRETNMSSG());
                            }
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<ContentApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
                serverCheckCallback.onServerLoadFail();
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new ContentApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ContentApiResponse contentApiResponse = (ContentApiResponse) obj;
                        liveDataContent.setValue(contentApiResponse.getContentItems());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_EXPORT_PLACE, "lstDcmnType", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("LISTCODE","lstDcmnType");
                ApiServices.getInstance().getListDocumentCategory(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<DocumentCategoryApiResponse>() {
                    @Override
                    public void onResponse(Call<DocumentCategoryApiResponse> call, Response<DocumentCategoryApiResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().isRETNCODE()){
                                dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                            }
                            else {
                                dataApiCallback.onApiLoadFail(response.body().getRETNMSSG());
                            }
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
    
                    @Override
                    public void onFailure(Call<DocumentCategoryApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
            }
    
            @Override
            public void onApiLoadFail() {
                serverCheckCallback.onServerLoadFail();
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new DocumentCategoryApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        DocumentCategoryApiResponse documentCategoryApiResponse = (DocumentCategoryApiResponse) obj;
                        liveDataDocumentCategory.setValue(documentCategoryApiResponse.getDocumentCategories());
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
    }
    
    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(168) /*Tìm kiếm công văn - Tài liệu*/);
    }
    public LiveData<String> getTitle(){
        return title;
    }
    
    public LiveData<List<LocationItem>> getLiveDataLocation() {
        return liveDataLocation;
    }
    
    public LiveData<List<ExportPlace>> getLiveDataExportPlace() {
        return liveDataExportPlace;
    }
    
    public LiveData<List<ContentItem>> getLiveDataContent() {
        return liveDataContent;
    }
    
    public LiveData<List<DocumentCategory>> getLiveDataDocumentCategory() {
        return liveDataDocumentCategory;
    }
}