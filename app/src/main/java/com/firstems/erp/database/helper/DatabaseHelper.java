package com.firstems.erp.database.helper;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.firstems.erp.api.model.response.location.LocationResponse;
import com.firstems.erp.callback.location.GetAllLocationCallbackl;
import com.firstems.erp.callback.location.InsertLocationCallback;
import com.firstems.erp.callback.runcode.GetAllRunCodeDataCallback;
import com.firstems.erp.callback.runcode.GetRunCodeDataCallback;
import com.firstems.erp.callback.runcode.SaveRunCodeDataUpdateCallback;
import com.firstems.erp.callback.runcode.UpDateDataRunCodeCallback;
import com.firstems.erp.callback.runcode.UpdateStatusrunCodeCallback;
import com.firstems.erp.common.Constant;
import com.firstems.erp.data.DataStructureProvider;
import com.firstems.erp.database.db.ERPDatabase;
import com.firstems.erp.database.model.LocationDBModel;
import com.firstems.erp.database.model.RunCodeData;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
public class DatabaseHelper {
    private static DatabaseHelper instance;
    private static Context context;
    private static ERPDatabase erpDatabase;
    private DatabaseHelper(){

    }
    public static DatabaseHelper getInstance(){
        if (instance==null){
            instance= new DatabaseHelper();
        }
        return instance;
    }
    public static void init(Context cx){
        context=cx;
        erpDatabase= Room.databaseBuilder(context, ERPDatabase.class, Constant.DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    public void getRunCodeData(GetRunCodeDataCallback getRunCodeDataCallback,String runCode, String parameter){
        GetRunCodeDataTask getRunCodeDataTask= new GetRunCodeDataTask(runCode,parameter,getRunCodeDataCallback);
        getRunCodeDataTask.execute();
    }

    public void saveRunCode(List<RunCodeData> runCodeData, SaveRunCodeDataUpdateCallback callback){
        SaveRunCodeDataTask saveRunCodeDataTask= new SaveRunCodeDataTask(runCodeData, callback);
        saveRunCodeDataTask.execute();
    }
    public void updateDataRunCode(String runCode,String parameter, String data,UpDateDataRunCodeCallback callback){
        UpdateDataRunCodeTask updateDataRunCodeTask= new UpdateDataRunCodeTask(runCode,parameter,data,callback);
        updateDataRunCodeTask.execute();
    }
    public void updateStatusRunCode(String runCode,String parameter, int status, UpdateStatusrunCodeCallback callback){
        UpdateStatusRunCodeTask updateStatusRunCodeTask= new UpdateStatusRunCodeTask(runCode,parameter,status,callback);
        updateStatusRunCodeTask.execute();
    }
    public void saveSingelRunCodeData(RunCodeData codeData,SaveRunCodeDataUpdateCallback callback){
        SaveSingelRunCodeDataTask saveSingelRunCodeDataTask= new SaveSingelRunCodeDataTask(codeData,callback);
        saveSingelRunCodeDataTask.execute();
    }

    class GetAllRunCodeDataTask extends AsyncTask<Void,Void, List<RunCodeData>> {
        private GetAllRunCodeDataCallback getAllRunCodeDataCallback;

        public GetAllRunCodeDataTask(GetAllRunCodeDataCallback getAllRunCodeDataCallback) {
            this.getAllRunCodeDataCallback = getAllRunCodeDataCallback;
        }

        @Override
        protected void onPostExecute(List<RunCodeData> runCodeData) {
            super.onPostExecute(runCodeData);
            getAllRunCodeDataCallback.onGetRunCodeData(runCodeData);
        }

        @Override
        protected List<RunCodeData> doInBackground(Void... voids) {
            return erpDatabase.erpDaoAccess().getAllRunCodeData();
        }
    }
    class GetRunCodeDataTask extends AsyncTask<Void,Void,List<RunCodeData>>{
        private String runCode;
        private String parameter;
        private GetRunCodeDataCallback getRunCodeDataCallback;

        public GetRunCodeDataTask(String runCode, String parameter, GetRunCodeDataCallback getRunCodeDataCallback) {
            this.runCode = runCode;
            this.parameter = parameter;
            this.getRunCodeDataCallback = getRunCodeDataCallback;
        }

        @Override
        protected void onPostExecute(List<RunCodeData> runCodeData) {
            super.onPostExecute(runCodeData);
            getRunCodeDataCallback.onGetRunCodeData(runCodeData);
        }

        @Override
        protected List<RunCodeData> doInBackground(Void... voids) {
            String id = DataStructureProvider.idRuncodeGenerate(runCode,parameter,
                    SharedPreferencesManager.getInstance().getPrefCompcode(),
                    SharedPreferencesManager.getInstance().getPrefLctcode());
            return erpDatabase.erpDaoAccess().getRunCodeData(id);
        }
    }
    class SaveRunCodeDataTask extends AsyncTask<Void,Void,Boolean>{
        private List<RunCodeData> runCodeData;
        private SaveRunCodeDataUpdateCallback callback;

        public SaveRunCodeDataTask(List<RunCodeData> runCodeData, SaveRunCodeDataUpdateCallback callback) {
            this.runCodeData = runCodeData;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            callback.onSave(aBoolean);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            List<Long> longs = erpDatabase.erpDaoAccess().saveRunCodeData(runCodeData);
            return longs.size()>0;
        }
    }
    class UpdateDataRunCodeTask extends AsyncTask<Void,Void,Void>{
        private String runCode;
        private String parameter;
        private String data;
        private UpDateDataRunCodeCallback callback;

        public UpdateDataRunCodeTask(String runCode, String parameter, String data, UpDateDataRunCodeCallback callback) {
            this.runCode = runCode;
            this.parameter = parameter;
            this.data = data;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onUpdateSuccess();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String id = DataStructureProvider.idRuncodeGenerate(runCode,parameter,
                    SharedPreferencesManager.getInstance().getPrefCompcode(),
                    SharedPreferencesManager.getInstance().getPrefLctcode());
            if (!parameter.equals("")){
                List<RunCodeData> runCodeData = erpDatabase.erpDaoAccess().checkRunCodeIDExits(id);
                if (runCodeData.size()==0){
                    RunCodeData codeDataNew= new RunCodeData();
                    codeDataNew.setId(id);
                    codeDataNew.setRunCode(runCode);
                    codeDataNew.setStatus(-1);
                    Long aLong= erpDatabase.erpDaoAccess().saveSingelRunCodeData(codeDataNew);
                    if (aLong!=0){
                        erpDatabase.erpDaoAccess().updateData(id,data);
                    }
                }
                else {
                    erpDatabase.erpDaoAccess().updateData(id,data);
                }
            }
            else {
                erpDatabase.erpDaoAccess().updateData(id,data);
            }

            return null;
        }
    }
    class UpdateStatusRunCodeTask extends AsyncTask<Void,Void,Void>{
        private String runCode;
        private String parameter;
        private int status;
        private UpdateStatusrunCodeCallback callback;

        public UpdateStatusRunCodeTask(String runCode, String parameter, int status, UpdateStatusrunCodeCallback callback) {
            this.runCode = runCode;
            this.parameter = parameter;
            this.status = status;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onUpdateSucess();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String id = DataStructureProvider.idRuncodeGenerate(runCode,parameter,
                    SharedPreferencesManager.getInstance().getPrefCompcode(),
                    SharedPreferencesManager.getInstance().getPrefLctcode());
            if (!parameter.equals("")){
                erpDatabase.erpDaoAccess().updateStatus(id,status);
                erpDatabase.erpDaoAccess().updateStatus(runCode,status);
            }
            else {
                erpDatabase.erpDaoAccess().updateStatus(id,status);
            }
            return null;
        }
    }
    class SaveSingelRunCodeDataTask extends AsyncTask<Void,Void,Boolean>{
        private RunCodeData runCodeData;
        private SaveRunCodeDataUpdateCallback callback;

        public SaveSingelRunCodeDataTask(RunCodeData runCodeData, SaveRunCodeDataUpdateCallback callback) {
            this.runCodeData = runCodeData;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            callback.onSave(aBoolean);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Long aLong = erpDatabase.erpDaoAccess().saveSingelRunCodeData(runCodeData);
            return aLong>0;
        }
    }
    public void getAllLocation(GetAllLocationCallbackl getAllLocationCallbackl){
        GetAllLoationTask getAllLoationTask = new GetAllLoationTask(getAllLocationCallbackl);
        getAllLoationTask.execute();
    }
    class GetAllLoationTask extends AsyncTask<Void,Void,List<LocationDBModel>>{
        private GetAllLocationCallbackl getAllLocationCallbackl;
    
        public GetAllLoationTask(GetAllLocationCallbackl getAllLocationCallbackl) {
            this.getAllLocationCallbackl = getAllLocationCallbackl;
        }
    
        @Override
        protected void onPostExecute(List<LocationDBModel> locationDBModels) {
            super.onPostExecute(locationDBModels);
            getAllLocationCallbackl.onLoadSuccess(locationDBModels);
        }
    
        @Override
        protected List<LocationDBModel> doInBackground(Void... voids) {
            return erpDatabase.locationDaoAccess().getAllLocation();
        }
    }
    
    public void getLocationByCode(String comCode, String lctCode, GetAllLocationCallbackl getAllLocationCallbackl){
        GetLocationByCodeTask getAllLoationTask = new GetLocationByCodeTask(comCode,lctCode,getAllLocationCallbackl);
        getAllLoationTask.execute();
    }
    class GetLocationByCodeTask extends AsyncTask<Void,Void,List<LocationDBModel>>{
        private String comCode;
        private String lctCode;
        private GetAllLocationCallbackl getAllLocationCallbackl;
    
        public GetLocationByCodeTask(String comCode, String lctCode, GetAllLocationCallbackl getAllLocationCallbackl) {
            this.comCode = comCode;
            this.lctCode = lctCode;
            this.getAllLocationCallbackl = getAllLocationCallbackl;
        }
    
        @Override
        protected void onPostExecute(List<LocationDBModel> locationDBModels) {
            super.onPostExecute(locationDBModels);
            getAllLocationCallbackl.onLoadSuccess(locationDBModels);
        }
        
        @Override
        protected List<LocationDBModel> doInBackground(Void... voids) {
            return erpDatabase.locationDaoAccess().getLocationByCode(comCode,lctCode);
        }
    }
    
    public void deleteAllLocation(){
        DeleteAllLocationtask  deleteAllLocationtask = new DeleteAllLocationtask();
        deleteAllLocationtask.execute();
    }
    
    class DeleteAllLocationtask extends AsyncTask<Void,Void,Void>{
    
        @Override
        protected Void doInBackground(Void... voids) {
            erpDatabase.locationDaoAccess().deleteAll();
            return null;
        }
    }
    
    public void insertMultiLocation(String compCode,List<LocationResponse> locationDBModels, InsertLocationCallback insertLocationCallback){
        InsertMultiLocationTask insertMultiLocationTask = new InsertMultiLocationTask(compCode,locationDBModels, insertLocationCallback);
        insertMultiLocationTask.execute();
    }
    class InsertMultiLocationTask extends AsyncTask<Void,Void,List<LocationDBModel>>{
        private List<LocationResponse> locationDBModels;
        private String comCode;
        private InsertLocationCallback insertLocationCallback;
    
        public InsertMultiLocationTask(String comCode,List<LocationResponse> locationDBModels, InsertLocationCallback insertLocationCallback) {
            this.locationDBModels = locationDBModels;
            this.comCode = comCode;
            this.insertLocationCallback = insertLocationCallback;
        }
    
        @Override
        protected void onPostExecute(List<LocationDBModel> locationDBModels) {
            super.onPostExecute(locationDBModels);
            insertLocationCallback.onSuccess(locationDBModels);
        }
    
        @Override
        protected List<LocationDBModel> doInBackground(Void... voids) {
            List<LocationDBModel> list = new ArrayList<>();
            for (LocationResponse locationResponse : locationDBModels){
                list.add(new LocationDBModel(locationResponse.getLocationCode(),locationResponse.getLocationName(),comCode));
            }
            erpDatabase.locationDaoAccess().insertMultiLocation(list);
            return erpDatabase.locationDaoAccess().getAllLocation();
        }
    }
    public void updateLocationCheck(String comCode, String lctCode, boolean isCheck){
        UpdateLocationCheckTask updateLocationCheckTask = new UpdateLocationCheckTask(comCode, lctCode, isCheck);
        updateLocationCheckTask.execute();
    }
    class UpdateLocationCheckTask extends AsyncTask<Void,Void,Void>{
        @NonNull
        private String comCode;
        @NonNull
        private String lctCode;
        private boolean isCheck;
    
        public UpdateLocationCheckTask(@NonNull String comCode, @NonNull String lctCode, boolean isCheck) {
            this.comCode = comCode;
            this.lctCode = lctCode;
            this.isCheck = isCheck;
        }
    
        @Override
        protected Void doInBackground(Void... voids) {
            erpDatabase.locationDaoAccess().setFalseAllCheck();
            erpDatabase.locationDaoAccess().updateLocation(comCode,lctCode,isCheck);
            return null;
        }
    }
}
