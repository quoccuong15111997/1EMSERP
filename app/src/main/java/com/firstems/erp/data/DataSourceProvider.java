package com.firstems.erp.data;

import android.util.Log;

import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.callback.data.DataSourceProviderCallback;
import com.firstems.erp.callback.data.LoadApiCallback;
import com.firstems.erp.callback.data.LoadDatabaseCallback;
import com.firstems.erp.callback.runcode.GetRunCodeDataCallback;
import com.firstems.erp.callback.runcode.UpDateDataRunCodeCallback;
import com.firstems.erp.callback.runcode.UpdateStatusrunCodeCallback;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.database.model.RunCodeData;
import com.firstems.erp.system.SysConfig;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/6/2020.
 */
public class DataSourceProvider implements Serializable {
    private static DataSourceProvider instance;
    private DataSourceProvider(){

    }
    public static DataSourceProvider getInstance(){
        if (instance==null){
            instance= new DataSourceProvider();
        }
        return instance;
    }
    public void getDataSource(String runCode, String parameter ,LoadApiCallback loadApiCallback, DataSourceProviderCallback providerCallback){
        String TAG = "DATA_CACHE_RUNCODE="+runCode;
        RunCodeData runCodeData = SysConfig.getRunCodeDateUpdate(runCode);
        if (runCodeData==null){
            Log.e(TAG,"Run code no cache");
            loadApiCallback.onApiLoadSuccess(new DataApiCallback() {
                @Override
                public void onDataApi(String jsonAPI) {
                    Log.e("DATA_CACHE_"+runCode,"Data source read from api");
                    providerCallback.onDataSource(jsonAPI);
                    providerCallback.onUpdateImage(0);
                }

                @Override
                public void onApiLoadFail(String mess) {
                    System.out.println(TAG+" DataSourceProvider CLASS - LOAD API FAIL"+mess);
                    loadApiCallback.onApiLoadFail();
                }
            });
        }
        else {
            Log.e(TAG,"Run code is use cache");
            DatabaseHelper.getInstance().getRunCodeData(new GetRunCodeDataCallback() {
                @Override
                public void onGetRunCodeData(List<RunCodeData> runCodeDataList) {
                    if (runCodeDataList!=null){
                        if (runCodeDataList.size()>0){
                            RunCodeData runCodeDataCurrent = runCodeDataList.get(0);
                            if (runCodeData.getStatus()!=runCodeDataCurrent.getStatus()){
                                Log.e(TAG,"Status is different, begin update -- Status new :"+runCodeData.getStatus()+" Status current :"+runCodeDataCurrent.getStatus());
                                //Do get update
                                loadApiCallback.onApiLoadSuccess(new DataApiCallback() {
                                    @Override
                                    public void onDataApi(String jsonAPI) {
                                        DatabaseHelper.getInstance().updateDataRunCode(runCodeData.getRunCode(),parameter, jsonAPI, new UpDateDataRunCodeCallback() {
                                            @Override
                                            public void onUpdateSuccess() {
                                                DatabaseHelper.getInstance().updateStatusRunCode(runCodeData.getRunCode(),parameter, runCodeData.getStatus(), new UpdateStatusrunCodeCallback() {
                                                    @Override
                                                    public void onUpdateSucess() {
                                                        Log.e(TAG,"Save data with UPDATE db is success, begin load data from db");
                                                        loadDataFromDb(runCodeData.getRunCode(),parameter, new LoadDatabaseCallback() {
                                                            @Override
                                                            public void onDatabaseLoad(String jsonDatabase) {
                                                                //Return data
                                                                Log.e(TAG,"Data load from database with update is success : "+jsonDatabase);
                                                                providerCallback.onDataSource(jsonDatabase);
                                                                //Do update Image
                                                                providerCallback.onUpdateImage(1);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }

                                    @Override
                                    public void onApiLoadFail(String mess) {
                                        System.out.println(TAG+" DataSourceProvider CLASS - LOAD API FAIL"+mess);
                                        loadApiCallback.onApiLoadFail();
                                    }
                                });
                            }
                            else {
                                Log.e(TAG,"Status is =, begin load data ------- Status New: "+runCodeData.getStatus()+" Status current :"+runCodeDataCurrent.getStatus());
                                // Load data from db
                                loadDataFromDb(runCodeData.getRunCode(),parameter, new LoadDatabaseCallback() {
                                    @Override
                                    public void onDatabaseLoad(String jsonDatabase) {
                                        //Return data
                                        Log.e(TAG,"Data load from database success : "+jsonDatabase);
                                        providerCallback.onDataSource(jsonDatabase);
                                        //Do update Image
                                        providerCallback.onUpdateImage(0);
                                    }
                                });
                            }
                        }
                        else {
                            Log.e(TAG,"Run code list is size < 0");
                            loadApiCallback.onApiLoadSuccess(new DataApiCallback() {
                                @Override
                                public void onDataApi(String jsonAPI) {
                                    DatabaseHelper.getInstance().updateDataRunCode(runCodeData.getRunCode(),parameter, jsonAPI, new UpDateDataRunCodeCallback() {
                                        @Override
                                        public void onUpdateSuccess() {
                                            DatabaseHelper.getInstance().updateStatusRunCode(runCodeData.getRunCode(),parameter, runCodeData.getStatus(), new UpdateStatusrunCodeCallback() {
                                                @Override
                                                public void onUpdateSucess() {
                                                    Log.e(TAG,"Save data with UPDATE db is success, begin load data from db");
                                                    loadDataFromDb(runCodeData.getRunCode(),parameter, new LoadDatabaseCallback() {
                                                        @Override
                                                        public void onDatabaseLoad(String jsonDatabase) {
                                                            //Return data
                                                            Log.e(TAG,"Data load from database with update is success : "+jsonDatabase);
                                                            providerCallback.onDataSource(jsonDatabase);
                                                            //Do update Image
                                                            providerCallback.onUpdateImage(1);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }

                                @Override
                                public void onApiLoadFail(String mess) {
                                    System.out.println(TAG+" DataSourceProvider CLASS - LOAD API FAIL"+mess);
                                    loadApiCallback.onApiLoadFail();
                                }
                            });
                        }
                    }
                    else {
                        Log.e(TAG,"Run code list is null");
                    }
                }
            },runCodeData.getRunCode(),parameter);
        }
    }
    private void loadDataFromDb(String runCode, String parameter,LoadDatabaseCallback callback) {
        DatabaseHelper.getInstance().getRunCodeData(new GetRunCodeDataCallback() {
            @Override
            public void onGetRunCodeData(List<RunCodeData> runCodeDataList) {
                RunCodeData codeData = runCodeDataList.get(0);
                callback.onDatabaseLoad(codeData.getData());
            }
        },runCode,parameter);
    }
}
