package com.firstems.erp.ui.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.product.ProgressProductDetailApiResponse;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
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

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<ProgressProductDetailItem>> mutableLiveDataProgressDetail;
    private ServerCheckCallback serverCheckCallback;

    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }

    public ProductViewModel() {
        mutableLiveDataProgressDetail = new MutableLiveData<>();

    }
    public void getData(String code){

        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_GET_DETAIL_PROGRESS_PRODUCT, code, new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("KEY_CODE", code);
                ApiServices.getInstance().getProgressProductDetail(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ProgressProductDetailApiResponse>() {
                    @Override
                    public void onResponse(Call<ProgressProductDetailApiResponse> call, Response<ProgressProductDetailApiResponse> response) {
                        if (response.isSuccessful()) {
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        } else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ProgressProductDetailApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new ProgressProductDetailApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ProgressProductDetailApiResponse progressProductDetailApiResponse = (ProgressProductDetailApiResponse) obj;
                        mutableLiveDataProgressDetail.setValue(progressProductDetailApiResponse.getProgressProductDetailItems());
                    }
                });
            }

            @Override
            public void onUpdateImage(int status) {

            }
        });
    }

    public MutableLiveData<List<ProgressProductDetailItem>> getMutableLiveDataProgressDetail() {
        return mutableLiveDataProgressDetail;
    }
}