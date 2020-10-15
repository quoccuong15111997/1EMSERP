package com.firstems.erp.ui.product.progress;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.product.ProgressApiResponse;
import com.firstems.erp.api.model.response.product.ProgressItem;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductProgressViewModel extends ViewModel {
    private MutableLiveData<List<ProgressItem>> mutableLiveDataProgressItem;
    private ServerCheckCallback serverCheckCallback;

    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }

    public ProductProgressViewModel() {
        mutableLiveDataProgressItem = new MutableLiveData<>();
    }

    public void loadData() {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_GET_ALL_PROGRESS_PRODUCT, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices.getInstance().getAllProgress(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<ProgressApiResponse>() {
                    @Override
                    public void onResponse(Call<ProgressApiResponse> call, Response<ProgressApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onDataApi(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ProgressApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new ProgressApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ProgressApiResponse progressApiResponse = (ProgressApiResponse) obj;
                        mutableLiveDataProgressItem.setValue(progressApiResponse.getProgressItems());
                    }
                });
            }

            @Override
            public void onUpdateImage(int status) {

            }
        });
    }

    public MutableLiveData<List<ProgressItem>> getMutableLiveDataProgressItem() {
        return mutableLiveDataProgressItem;
    }
}