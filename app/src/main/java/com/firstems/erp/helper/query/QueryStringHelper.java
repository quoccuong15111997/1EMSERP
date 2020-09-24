package com.firstems.erp.helper.query;

import com.firstems.erp.callback.QueryStringCallback;
import com.firstems.erp.model.QueryModel;

import java.util.List;

public class QueryStringHelper {
    private List<QueryModel> queryModels;
    
    public QueryStringHelper(List<QueryModel> queryModels) {
        this.queryModels = queryModels;
    }
    
    public void builder(QueryStringCallback queryStringCallback){
        String result = "";
        for (int i = 0;i<queryModels.size() ;i++){
            if (i == queryModels.size()-1){
                result += queryModels.get(i);
            }
            else {
                result += queryModels.get(i)+" AND ";
            }
        }
        queryStringCallback.onSuccess(result);
    }
}
