package com.firstems.erp.data;

import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.google.gson.Gson;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public class DataConvertProvider {
    private static DataConvertProvider instance;
    private DataConvertProvider(){

    }
    public static DataConvertProvider getInstance(){
        if (instance==null){
            instance= new DataConvertProvider();
        }
        return instance;
    }
    public void convertJsonToObject(String json, Object object, ConvertJsonCallback convertJsonCallback){
     try{
         //System.out.println("Convert "+json);
         Gson gson= new Gson();
         Object objReturn = gson.fromJson(json,object.getClass());
         convertJsonCallback.onConvertSuccess(objReturn);
     }
     catch (Exception ex){
         ex.printStackTrace();
     }
    }
}
