package com.firstems.erp.ui.signature.billpaymentrequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.SignatureDetailsRequest;
import com.firstems.erp.api.model.response.bill_payment.BillPaymentApiResponse;
import com.firstems.erp.api.model.response.currency.CurrencyItem;
import com.firstems.erp.api.model.response.currency.CurrencyListApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiItem;
import com.firstems.erp.api.model.response.loai_doi_tuong_lien_quan.LoaiDoiTuongLienQuanApiResponse;
import com.firstems.erp.api.model.response.loai_doi_tuong_lien_quan.LoaiDoiTuongLienQuanItem;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.callback.runcode.LoadDataAsynCallback;
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

public class BillPaymentRequestViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private MutableLiveData<String> title;
    private MutableLiveData<List<CurrencyItem>> liveDataCurrencyList;
    private MutableLiveData<List<LoaiDeNghiItem>> liveDataLoaiDeNghi;
    private MutableLiveData<List<LoaiDoiTuongLienQuanItem>> liveDataLoaiDoiTuongLienQuan;
    private MutableLiveData<List<LoaiDeNghiItem>> liveDataLoaichiTieu;
    private MutableLiveData<BillPaymentApiResponse> liveDataBillPaymentResponse;
    public BillPaymentRequestViewModel() {
        title= new MutableLiveData<>();
        liveDataCurrencyList = new MutableLiveData<>();
        liveDataLoaiDeNghi= new MutableLiveData<>();
        initTitle();
        liveDataLoaiDoiTuongLienQuan= new MutableLiveData<>();
        liveDataLoaichiTieu= new MutableLiveData<>();
        liveDataBillPaymentResponse = new MutableLiveData<>();
    }
    public void loadBillPayment(String dcmnCode, String keyCode){
        SignatureDetailsRequest signatureDetailsRequest = new SignatureDetailsRequest();
        signatureDetailsRequest.setKeyCode(keyCode);
        signatureDetailsRequest.setDcmnCode(dcmnCode);
        ApiServices.getInstance().getDetailBillPament(SharedPreferencesManager.getInstance().getPrefToken(), signatureDetailsRequest.convertToJson(), new Callback<BillPaymentApiResponse>() {
            @Override
            public void onResponse(Call<BillPaymentApiResponse> call, Response<BillPaymentApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        liveDataBillPaymentResponse.setValue(response.body());
                    }
                    else {
                        System.out.println(response.body().getRETNMSSG());
                    }
                }
                else {
                    serverCheckCallback.onServerLoadFail();
                    System.out.println(response.message());
                }
            }
    
            @Override
            public void onFailure(Call<BillPaymentApiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                serverCheckCallback.onServerLoadFail();
            }
        });
    }
    
    public LiveData<BillPaymentApiResponse> getLiveDataBillPaymentResponse() {
        return liveDataBillPaymentResponse;
    }
    
    public void loadData(LoadDataAsynCallback loadDataAsynCallback) {
       loadLoaiDeNGhi(new LoadDataAsynCallback() {
           @Override
           public void onLoadSuccess() {
               loadDonViTienTe(new LoadDataAsynCallback() {
                   @Override
                   public void onLoadSuccess() {
                       loaiDoiTuong(new LoadDataAsynCallback() {
                           @Override
                           public void onLoadSuccess() {
                               loadLoaiChiTieu(new LoadDataAsynCallback() {
                                   @Override
                                   public void onLoadSuccess() {
                                       loadDataAsynCallback.onLoadSuccess();
                                   }
                               });
                           }
                       });
                   }
               });
           }
       });
    }
    private void loadDonViTienTe(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_CURRENCY_LIST, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices
                        .getInstance()
                        .getAlCurrency(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<CurrencyListApiResponse>() {
                            @Override
                            public void onResponse(Call<CurrencyListApiResponse> call, Response<CurrencyListApiResponse> response) {
                                if (response.isSuccessful()){
                                    dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                                }
                                else {
                                    dataApiCallback.onApiLoadFail(response.message());
                                }
                            }
                        
                            @Override
                            public void onFailure(Call<CurrencyListApiResponse> call, Throwable t){
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new CurrencyListApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        CurrencyListApiResponse currencyListApiResponse = (CurrencyListApiResponse) obj;
                        liveDataCurrencyList.setValue(currencyListApiResponse.getCurrencyItems());
                        loadDataAsynCallback.onLoadSuccess();
                    }
                });
            }
        
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
    private void loadLoaiDeNGhi(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOAI_TAM_UNG, "PHDNC", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("CONDFLTR","DcmnCode='PHDNC'");
                ApiServices.getInstance().getAllLoaiDeNghi(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<LoaiDeNghiApiResponse>() {
                    @Override
                    public void onResponse(Call<LoaiDeNghiApiResponse> call, Response<LoaiDeNghiApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<LoaiDeNghiApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new LoaiDeNghiApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LoaiDeNghiApiResponse loaiDeNghiApiResponse = (LoaiDeNghiApiResponse) obj;
                        liveDataLoaiDeNghi.setValue(loaiDeNghiApiResponse.getLoaiDeNghiItems());
                        loadDataAsynCallback.onLoadSuccess();
                    }
                });
            }
            
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
    private void loaiDoiTuong(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOAI_DOI_TUONG_LIEN_QUAN, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices.getInstance().getAllLoaiDoiTuongLienQuan(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<LoaiDoiTuongLienQuanApiResponse>() {
                    @Override
                    public void onResponse(Call<LoaiDoiTuongLienQuanApiResponse> call, Response<LoaiDoiTuongLienQuanApiResponse> response) {
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
                    public void onFailure(Call<LoaiDoiTuongLienQuanApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new LoaiDoiTuongLienQuanApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LoaiDoiTuongLienQuanApiResponse loaiDoiTuongLienQuanApiResponse = (LoaiDoiTuongLienQuanApiResponse) obj;
                        liveDataLoaiDoiTuongLienQuan.setValue(loaiDoiTuongLienQuanApiResponse.getLoaiDoiTuongLienQuanItems());
                        loadDataAsynCallback.onLoadSuccess();
                    }
                });
            }
    
            @Override
            public void onUpdateImage(int status) {
        
            }
        });
    }
    private void loadLoaiChiTieu(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOAI_TAM_UNG, "SCTNC", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("CONDFLTR","DcmnCode='SCTNC'");
                ApiServices.getInstance().getAllLoaiDeNghi(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<LoaiDeNghiApiResponse>() {
                    @Override
                    public void onResponse(Call<LoaiDeNghiApiResponse> call, Response<LoaiDeNghiApiResponse> response) {
                        if (response.isSuccessful()){
                            dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<LoaiDeNghiApiResponse> call, Throwable t) {
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
                DataConvertProvider.getInstance().convertJsonToObject(data, new LoaiDeNghiApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LoaiDeNghiApiResponse loaiDeNghiApiResponse = (LoaiDeNghiApiResponse) obj;
                        liveDataLoaichiTieu.setValue(loaiDeNghiApiResponse.getLoaiDeNghiItems());
                        loadDataAsynCallback.onLoadSuccess();
                    }
                });
            }
            
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(123));
        
    }
    public LiveData<String> getTitle(){
        return title;
    }
    public LiveData<List<LoaiDeNghiItem>> getLiveDataLoaiDeNghi() {
        return liveDataLoaiDeNghi;
    }
    
    public LiveData<List<CurrencyItem>> getLiveDataCurrencyList() {
        return liveDataCurrencyList;
    }
    
    public LiveData<List<LoaiDeNghiItem>> getLiveDataLoaichiTieu() {
        return liveDataLoaichiTieu;
    }
    
    public LiveData<List<LoaiDoiTuongLienQuanItem>> getLiveDataLoaiDoiTuongLienQuan() {
        return liveDataLoaiDoiTuongLienQuan;
    }
}