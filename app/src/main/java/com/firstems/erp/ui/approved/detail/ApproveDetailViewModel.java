package com.firstems.erp.ui.approved.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.ApproveInfoRequest;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ApprovedModelLoadCallback;
import com.firstems.erp.model.ApproveDetailModel;
import com.firstems.erp.model.ApproveModel;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ProgressApproveModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveDetailViewModel extends ViewModel {
    private MutableLiveData<List<FileIncludeModel>> fiListMutableLiveData;

    private MutableLiveData<ApproveInfoApiResponse> dataApproveDetail;

    public ApproveDetailViewModel() {
        dataApproveDetail= new MutableLiveData<>();
        fiListMutableLiveData= new MutableLiveData<>();

        initFileList();
    }

    public void getDataApproveDetail(String dcmnCode, String keyCode, ApprovedModelLoadCallback approvedModelLoadCallback) {
        ApproveInfoRequest approveInfoRequest= new ApproveInfoRequest();
        approveInfoRequest.setDcmnCode(dcmnCode);
        approveInfoRequest.setKeyCode(keyCode);
        System.out.println(approveInfoRequest.convertToJson());
        ApiServices.
                getInstance().
                getInfoApprove(SharedPreferencesManager.getInstance().getPrefToken(),
                        approveInfoRequest.convertToJson(),
                        new Callback<ApproveInfoApiResponse>() {
                            @Override
                            public void onResponse(Call<ApproveInfoApiResponse> call, Response<ApproveInfoApiResponse> response) {
                                if (response.isSuccessful()){
                                    ApproveInfoApiResponse approveInfoApiResponse = response.body();
                                    dataApproveDetail.setValue(approveInfoApiResponse);
                                    approvedModelLoadCallback.onDataLoaded(dataApproveDetail);
                                }
                                else {
                                    approvedModelLoadCallback.onDataLoadFail(response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<ApproveInfoApiResponse> call, Throwable t) {
                                approvedModelLoadCallback.onDataLoadFail(t.getMessage());
                                System.out.println(t.getMessage());
                            }
                        });
    }
    private void initFileList() {
        List<FileIncludeModel> fileIncludeModels= new ArrayList<>();
        fileIncludeModels.add(new FileIncludeModel("Phiếu đề nghị tạm ứng.pdf"));
        fileIncludeModels.add(new FileIncludeModel("Phiếu đề nghị tạm chi.pdf"));
        fiListMutableLiveData.setValue(fileIncludeModels);
    }

    public MutableLiveData<List<FileIncludeModel>> getFiListMutableLiveData() {
        return fiListMutableLiveData;
    }
}