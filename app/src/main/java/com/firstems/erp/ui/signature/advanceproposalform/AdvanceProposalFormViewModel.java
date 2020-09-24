package com.firstems.erp.ui.signature.advanceproposalform;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.SignatureDetailsRequest;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormApiResponse;
import com.firstems.erp.api.model.response.currency.CurrencyItem;
import com.firstems.erp.api.model.response.currency.CurrencyListApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiItem;
import com.firstems.erp.api.model.response.loai_de_nghi_tam_ung.LoaiDoiTuongNhanApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi_tam_ung.LoaiDoiTuongNhanItem;
import com.firstems.erp.api.model.response.project_list.ProjectListApiResponse;
import com.firstems.erp.api.model.response.project_list.ProjectListItem;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.AdvanceProposalFormCallback;
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

public class AdvanceProposalFormViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<AdvanceProposalFormApiResponse> advanceProposalFormLiveData;
    private MutableLiveData<List<LoaiDoiTuongNhanItem>> liveDataLoaideNghitamUng;
    private MutableLiveData<List<LoaiDeNghiItem>> liveDataLoaiTamUng;
    private MutableLiveData<List<CurrencyItem>> liveDataCurrencyList;
    private MutableLiveData<List<ProjectListItem>> liveDataProjectList;
    public AdvanceProposalFormViewModel() {
        title= new MutableLiveData<>();
        advanceProposalFormLiveData = new MutableLiveData<>();
        liveDataLoaideNghitamUng= new MutableLiveData<>();
        liveDataCurrencyList = new MutableLiveData<>();
        liveDataProjectList = new MutableLiveData<>();
        liveDataLoaiTamUng = new MutableLiveData<>();
        initTitle();
    }
    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(106));
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }
    
    private void loadLoaiTamUng(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOAI_TAM_UNG, "PHTAM", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("CONDFLTR","DcmnCode='PHTAM'");
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
            
            }
        }, new DataSourceProviderCallback() {
            @Override
            public void onDataSource(String data) {
                DataConvertProvider.getInstance().convertJsonToObject(data, new LoaiDeNghiApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        LoaiDeNghiApiResponse loaiDeNghiApiResponse = (LoaiDeNghiApiResponse) obj;
                        liveDataLoaiTamUng.setValue(loaiDeNghiApiResponse.getLoaiDeNghiItems());
                        loadDataAsynCallback.onLoadSuccess();
                    }
                });
            }
        
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
    private void loadDoiTuongNhan(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_LOAI_DOI_TUONG_NHAN, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices
                        .getInstance()
                        .getAllLoaiDoiTuongNhan(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<LoaiDoiTuongNhanApiResponse>() {
                            @Override
                            public void onResponse(Call<LoaiDoiTuongNhanApiResponse> call, Response<LoaiDoiTuongNhanApiResponse> response) {
                                if (response.isSuccessful()){
                                    if (response.body().isRETNCODE()){
                                        dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                                    }
                                    else {
                                        dataApiCallback.onApiLoadFail(response.message());
                                    }
                                }
                                else {
                                    dataApiCallback.onApiLoadFail(response.message());
                                }
                            }
                        
                            @Override
                            public void onFailure(Call<LoaiDoiTuongNhanApiResponse> call, Throwable t) {
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
                DataConvertProvider
                        .getInstance()
                        .convertJsonToObject(data, new LoaiDoiTuongNhanApiResponse(), new ConvertJsonCallback() {
                            @Override
                            public void onConvertSuccess(Object obj) {
                                LoaiDoiTuongNhanApiResponse loaiDeNghiTamUngApiResponse = (LoaiDoiTuongNhanApiResponse) obj;
                                liveDataLoaideNghitamUng.setValue(loaiDeNghiTamUngApiResponse.getLoaiDeNghiTamUngItems());
                                loadDataAsynCallback.onLoadSuccess();
                            }
                        });
            }
        
            @Override
            public void onUpdateImage(int status) {
            
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
    private void loadDanhSachDuAn(LoadDataAsynCallback loadDataAsynCallback){
        DataSourceProvider.getInstance().getDataSource(Constant.RUN_CODE_PROJECT_LIST, "", new LoadApiCallback() {
            @Override
            public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                ApiServices
                        .getInstance()
                        .getAllProjectList(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<ProjectListApiResponse>() {
                            @Override
                            public void onResponse(Call<ProjectListApiResponse> call, Response<ProjectListApiResponse> response) {
                                if (response.isSuccessful()){
                                    dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                                }
                                else {
                                    dataApiCallback.onApiLoadFail(response.message());
                                }
                            }
                        
                            @Override
                            public void onFailure(Call<ProjectListApiResponse> call, Throwable t) {
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
                DataConvertProvider
                        .getInstance()
                        .convertJsonToObject(data, new ProjectListApiResponse(), new ConvertJsonCallback() {
                            @Override
                            public void onConvertSuccess(Object obj) {
                                ProjectListApiResponse projectListApiResponse = (ProjectListApiResponse) obj;
                                liveDataProjectList.setValue(projectListApiResponse.getProjectListItems());
                                loadDataAsynCallback.onLoadSuccess();
                            }
                        });
            }
        
            @Override
            public void onUpdateImage(int status) {
            
            }
        });
    }
    public void loadData(LoadDataAsynCallback loadDataAsynCallback) {
        loadLoaiTamUng(new LoadDataAsynCallback() {
            @Override
            public void onLoadSuccess() {
                loadDoiTuongNhan(new LoadDataAsynCallback() {
                    @Override
                    public void onLoadSuccess() {
                        loadDoiTuongNhan(new LoadDataAsynCallback() {
                            @Override
                            public void onLoadSuccess() {
                                loadDonViTienTe(new LoadDataAsynCallback() {
                                    @Override
                                    public void onLoadSuccess() {
                                        loadDanhSachDuAn(new LoadDataAsynCallback() {
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
        });
    }
    public void loadAdvanceProposalForm(String dcmnCode, String keyCode, AdvanceProposalFormCallback callback){
        SignatureDetailsRequest signatureDetailsRequest = new SignatureDetailsRequest();
        signatureDetailsRequest.setDcmnCode(dcmnCode);
        signatureDetailsRequest.setKeyCode(keyCode);
        ApiServices.getInstance().getDetailAdvanceProposal(SharedPreferencesManager.getInstance().getPrefToken(), signatureDetailsRequest.convertToJson(), new Callback<AdvanceProposalFormApiResponse>() {
            @Override
            public void onResponse(Call<AdvanceProposalFormApiResponse> call, Response<AdvanceProposalFormApiResponse> response) {
                if (response.isSuccessful()){
                    advanceProposalFormLiveData.setValue(response.body());
                    callback.onLoadSuccess(response.body());
                }
                else {
                    callback.onLoadFail(response.message());
                }
            }
    
            @Override
            public void onFailure(Call<AdvanceProposalFormApiResponse> call, Throwable t) {
                callback.onLoadFail(t.getMessage());
            }
        });
    }
   

    public LiveData<List<LoaiDoiTuongNhanItem>> getLiveDataLoaideNghitamUng() {
        return liveDataLoaideNghitamUng;
    }

    public LiveData<List<CurrencyItem>> getLiveDataCurrencyList() {
        return liveDataCurrencyList;
    }

    public LiveData<List<ProjectListItem>> getLiveDataProjectList() {
        return liveDataProjectList;
    }

    public LiveData<List<LoaiDeNghiItem>> getLiveDataLoaiTamUng() {
        return liveDataLoaiTamUng;
    }
}