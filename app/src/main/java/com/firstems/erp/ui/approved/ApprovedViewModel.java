package com.firstems.erp.ui.approved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.request.ApprovedRequest;
import com.firstems.erp.api.model.response.approved.ApprovedApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.helper.date.DateTimeHelper;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedViewModel extends ViewModel {
    private ServerCheckCallback serverCheckCallback;
    private MutableLiveData<String> title;
    private MutableLiveData<List<ApprovedItemApiResponse>> dataListApprove;
    public ApprovedViewModel() {
        title= new MutableLiveData<>();
        dataListApprove= new MutableLiveData<>();

        initTitle();
        loadDataApproved();
    }
    
    public void setServerCheckCallback(ServerCheckCallback serverCheckCallback) {
        this.serverCheckCallback = serverCheckCallback;
    }
    
    private void loadDataApproved() {
        ApprovedRequest approvedRequest = createApprovedDate();
        ApiServices.getInstance().getApprovedList(SharedPreferencesManager.getInstance().getPrefToken(),
                approvedRequest.convertToJson(),
                new Callback<ApprovedApiResponse>() {
                    @Override
                    public void onResponse(Call<ApprovedApiResponse> call, Response<ApprovedApiResponse> response) {
                        if (response.isSuccessful()){
                            dataListApprove.setValue(response.body().getApprovedItemApiResponses());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApprovedApiResponse> call, Throwable t) {
                        serverCheckCallback.onServerLoadFail();
                    }
                });
    }

    private ApprovedRequest createApprovedDate() {
        ApprovedRequest approvedRequest= new ApprovedRequest();
        approvedRequest.setEndDate(DateTimeHelper.getInstance().apiDateFormat.format(new Date(System.currentTimeMillis())));
        approvedRequest.setBeginDate(DateTimeHelper.getInstance().minusDate(60));
        System.out.println(approvedRequest.convertToJson());
        return approvedRequest;
    }

    public MutableLiveData<List<ApprovedItemApiResponse>> getDataListApprove() {
        return dataListApprove;
    }

    private void initTitle() {
        title.setValue(SharedPreferencesManager.getSystemLabel(15) /*Phê duyệt chứng từ*/);
    }

    public LiveData<String> getTitle() {
        return title;
    }
}