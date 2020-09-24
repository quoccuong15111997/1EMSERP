package com.firstems.erp.ui.shared.reviewprocess;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessApiResponse;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessItem;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.data.DataSourceProvider;

import java.util.List;

public class ReviewProcessViewModel extends ViewModel {
    private MutableLiveData<List<ReviewProcessItem>> liveDataReviewProgressList;
    public ReviewProcessViewModel() {
        liveDataReviewProgressList= new MutableLiveData<>();

    }

    public LiveData<List<ReviewProcessItem>> getLiveDataReviewProgressList() {
        return liveDataReviewProgressList;
    }
    public void loadData(String dcmnCode, String keyCode){
        DataSourceProvider
                .getInstance()
                .getDataSource(Constant.RUN_CODE_REVIEWS_PROGRESS_DETAILS, dcmnCode + keyCode, new LoadApiCallback() {
                    @Override
                    public void onApiLoadSuccess(DataApiCallback dataApiCallback) {
                        DataNetworkProvider
                                .getInstance()
                                .getDetailDocument(dataApiCallback,dcmnCode,keyCode);
                    }

                    @Override
                    public void onApiLoadFail() {

                    }
                }, new DataSourceProviderCallback() {
                    @Override
                    public void onDataSource(String data) {
                        getDataReviewProgress(data);
                    }

                    @Override
                    public void onUpdateImage(int status) {

                    }
                });
    }

    private void getDataReviewProgress(String data) {
        DataConvertProvider
                .getInstance()
                .convertJsonToObject(data, new ReviewProcessApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        ReviewProcessApiResponse reviewProcessApiResponse = (ReviewProcessApiResponse) obj;
                        liveDataReviewProgressList.setValue(reviewProcessApiResponse.getReviewProcessItems());
                    }
                });
    }
}