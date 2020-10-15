package com.firstems.erp.ui.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.error.ErrorApiResponse;
import com.firstems.erp.api.model.response.error.ErrorItem;
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
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<ProgressProductDetailItem>> mutableLiveDataProgressDetail;
    private ServerCheckCallback serverCheckCallback;
    private MutableLiveData<List<ErrorItem>> liveDataErrorList;

    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }

    public ProductViewModel() {
        mutableLiveDataProgressDetail = new MutableLiveData<>();
        liveDataErrorList= new MutableLiveData<>();

        getListError();
    }

    private void getListError() {
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_GET_LIST_ERROR, "lstPrdcErro", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject  jsonObject = new JsonObject();
                jsonObject.addProperty("LISTCODE","lstPrdcErro");
                ApiServices.getInstance().getListError(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ErrorApiResponse>() {
                    @Override
                    public void onResponse(Call<ErrorApiResponse> call, Response<ErrorApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ErrorApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new ErrorApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ErrorApiResponse errorApiResponse = (ErrorApiResponse) obj;
                        liveDataErrorList.setValue(errorApiResponse.getErrorItemList());
                    }
                });
            }

            @Override
            public void onUpdateImage(int status) {

            }
        });
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
                        List<ProgressProductDetailItem> progressProductDetailItems = new ArrayList<>();
                     /*   for (int i= 0; i< 100;i++){
                            try {
                                ProgressProductDetailItem item = (ProgressProductDetailItem) progressProductDetailApiResponse.getProgressProductDetailItems().get(0).clone();
                                item.setPrdcname(item.getPrdcname()+" - "+i);
                                item.setPrdccode(item.getPrdccode()+i);
                                item.setPrdcqtty(item.getPrdcqtty()+i);
                                if (i < 20){
                                    item.setStepcode(item.getStepcode()+20);
                                    item.setStepname(item.getStepname()+" - "+20);
                                }
                                else if (i > 21 && i< 40){
                                    item.setStepcode(item.getStepcode()+40);
                                    item.setStepname(item.getStepname()+" - "+40);
                                }
                                else if (i > 41 && i < 60){
                                    item.setStepcode(item.getStepcode()+60);
                                    item.setStepname(item.getStepname()+" - "+60);
                                }
                                else if (i > 61 && i < 80){
                                    item.setStepcode(item.getStepcode()+80);
                                    item.setStepname(item.getStepname()+" - "+80);
                                }
                                else if (i > 81 && i < 100){
                                    item.setStepcode(item.getStepcode()+100);
                                    item.setStepname(item.getStepname()+" - "+100);
                                }
                                progressProductDetailItems.add(item);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }*/
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

    public MutableLiveData<List<ErrorItem>> getLiveDataErrorList() {
        return liveDataErrorList;
    }
}