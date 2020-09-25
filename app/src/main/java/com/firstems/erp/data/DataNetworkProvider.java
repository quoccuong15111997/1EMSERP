package com.firstems.erp.data;

import com.firstems.erp.api.model.request.ApproveDocumentRequest;
import com.firstems.erp.api.model.request.ApprovedRequest;
import com.firstems.erp.api.model.request.ReviewProgressRequest;
import com.firstems.erp.api.model.request.SignatureDetailsRequest;
import com.firstems.erp.api.model.request.SignatureRequest;
import com.firstems.erp.api.model.request.bussiness.BussinessRgisAddNewRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedApiResponse;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionApiResponse;
import com.firstems.erp.api.model.response.bussiness.BussinessRegistrationEditResponse;
import com.firstems.erp.api.model.response.department.DepartmentApiResponse;
import com.firstems.erp.api.model.response.employee.EmployeeApiResponse;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuanApiResponse;
import com.firstems.erp.api.model.response.locationtype.LocationTypeApiResponse;
import com.firstems.erp.api.model.response.national.NationalApiResponse;
import com.firstems.erp.api.model.response.province.ProvinceApiResponse;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessApiResponse;
import com.firstems.erp.api.model.response.servicecontacts.ServiceContactsApiResponse;
import com.firstems.erp.api.model.response.signature.SignatureApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegistrationApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegstItem;
import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCTApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDCApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.model.FilterModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.firstems.erp.data.DataStructureProvider.createApprovedRequest;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public class DataNetworkProvider {
    private static DataNetworkProvider instance;
    private DataNetworkProvider(){

    }
    public static DataNetworkProvider getInstance(){
        if (instance==null){
            instance= new DataNetworkProvider();
        }
        return instance;
    }

    //Employee list api
    public void getListEmployeeApi(JsonObject body, DataApiCallback dataApiCallback){
        ApiServices.getInstance().getListEmployee(SharedPreferencesManager.getInstance().getPrefToken(),body, new Callback<EmployeeApiResponse>() {
            @Override
            public void onResponse(Call<EmployeeApiResponse> call, Response<EmployeeApiResponse> response) {
                if (response.isSuccessful()){
                    EmployeeApiResponse employeeApiResponse = response.body();
                    String jsonResult = new Gson().toJson(employeeApiResponse);
                    dataApiCallback.onDataApi(jsonResult);
                }
                else {
                    dataApiCallback.onApiLoadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<EmployeeApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Get list signature
    public void getListSignatureApi(FilterModel filterModel ,DataApiCallback dataApiCallback) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int para = (filterModel.isWaitsignature() ? 1 : 0 ) +(filterModel.isWaitApproved() ? 2 : 0) +(filterModel.isDone() ? 4 : 0);
        System.out.println("para: "+para);
        SignatureRequest signatureRequest = new SignatureRequest();
        signatureRequest.setSstSign(para);
        signatureRequest.setEndDate(simpleDateFormat.format(filterModel.getEndDate()));
        signatureRequest.setBeginDate(simpleDateFormat.format(filterModel.getBeginDate()));
        System.out.println(signatureRequest.convertToJson());
        ApiServices.getInstance().getListSignature(SharedPreferencesManager.getInstance().getPrefToken(), signatureRequest.convertToJson(), new Callback<SignatureApiResponse>() {
            @Override
            public void onResponse(Call<SignatureApiResponse> call, Response<SignatureApiResponse> response) {
                if (response.isSuccessful()){
                    SignatureApiResponse signatureApiResponse= response.body();
                    String jsonResult = new Gson().toJson(signatureApiResponse);
                    dataApiCallback.onDataApi(jsonResult);
                }
                else {
                    dataApiCallback.onApiLoadFail(response.message());
                }

            }

            @Override
            public void onFailure(Call<SignatureApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Get List Approved
    public void getListApprovedApi(String beginDate, String endDate, DataApiCallback dataApiCallback){
        ApprovedRequest approvedRequest = createApprovedRequest(beginDate,endDate);
        System.out.println(approvedRequest.convertToJson());
        ApiServices.getInstance().getApprovedList(SharedPreferencesManager.getInstance().getPrefToken(),
                approvedRequest.convertToJson(),
                new Callback<ApprovedApiResponse>() {
                    @Override
                    public void onResponse(Call<ApprovedApiResponse> call, Response<ApprovedApiResponse> response) {
                        if (response.isSuccessful()){
                            ApprovedApiResponse approvedApiResponse =response.body();
                            String jsonResult = new Gson().toJson(approvedApiResponse);
                            dataApiCallback.onDataApi(jsonResult);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApprovedApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get List Location Type
    public void getListLocateType(DataApiCallback dataApiCallback){
        ApiServices
                .getInstance()
                .getLocationType(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<LocationTypeApiResponse>() {
                    @Override
                    public void onResponse(Call<LocationTypeApiResponse> call, Response<LocationTypeApiResponse> response) {
                        if (response.isSuccessful()){
                            LocationTypeApiResponse locationTypeApiResponse= response.body();
                            String jsonResult = new Gson().toJson(locationTypeApiResponse);
                            dataApiCallback.onDataApi(jsonResult);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LocationTypeApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get List National
    public void getListNational(DataApiCallback dataApiCallback){
        ApiServices
                .getInstance()
                .getNationalList(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<NationalApiResponse>() {
                    @Override
                    public void onResponse(Call<NationalApiResponse> call, Response<NationalApiResponse> response) {
                        if (response.isSuccessful()){
                            NationalApiResponse nationalApiResponse= response.body();
                            String json = new Gson().toJson(nationalApiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<NationalApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get Time Kepping Type List
    public void getTimeKeppingTypeList(DataApiCallback dataApiCallback){
        ApiServices
                .getInstance()
                .getTimekeepingTypeList(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<TimekeepingTypeCTApiResponse>() {
                    @Override
                    public void onResponse(Call<TimekeepingTypeCTApiResponse> call, Response<TimekeepingTypeCTApiResponse> response) {
                        if (response.isSuccessful()){
                            TimekeepingTypeCTApiResponse timekeepingTypeCTApiResponse =response.body();
                            String json = new Gson().toJson(timekeepingTypeCTApiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<TimekeepingTypeCTApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get List Province
    public void getProvinceList(DataApiCallback dataApiCallback){
        ApiServices
                .getInstance()
                .getListProvince(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<ProvinceApiResponse>() {
                    @Override
                    public void onResponse(Call<ProvinceApiResponse> call, Response<ProvinceApiResponse> response) {
                        if (response.isSuccessful()){
                            ProvinceApiResponse provinceApiResponse= response.body();

                            String json =new Gson().toJson(provinceApiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ProvinceApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get Detail an Review Progress
    public void getDetailDocument(DataApiCallback dataApiCallback,String dcmnCode,String keyCode){
        ReviewProgressRequest reviewProgressRequest= new ReviewProgressRequest();
        reviewProgressRequest.setDcmnCode(dcmnCode);
        reviewProgressRequest.setKeyCode(keyCode);
        System.out.println(reviewProgressRequest.convertToJson());
        ApiServices
                .getInstance()
                .getDetailDocument(SharedPreferencesManager.getInstance().getPrefToken(), reviewProgressRequest.convertToJson(), new Callback<ReviewProcessApiResponse>() {
                    @Override
                    public void onResponse(Call<ReviewProcessApiResponse> call, Response<ReviewProcessApiResponse> response) {
                        if (response.isSuccessful()){
                            ReviewProcessApiResponse reviewProcessApiResponse =response.body();
                            String json = new Gson().toJson(reviewProcessApiResponse);
                            System.out.println(json);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewProcessApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    //Get Signature detail
    public void getDetailSignatureBussinessRegist(DataApiCallback dataApiCallback,String dcmnCode, String keyCode){
        SignatureDetailsRequest detailsRequest = new SignatureDetailsRequest();
        detailsRequest.setDcmnCode(dcmnCode);
        detailsRequest.setKeyCode(keyCode);
        System.out.println(detailsRequest.convertToJson());
        ApiServices
                .getInstance()
                .getDetailSignatureBussinessRegistration(SharedPreferencesManager.getInstance().getPrefToken(), detailsRequest.convertToJson(), new Callback<BussinessRegistrationApiResponse>() {
                    @Override
                    public void onResponse(Call<BussinessRegistrationApiResponse> call, Response<BussinessRegistrationApiResponse> response) {
                        if (response.isSuccessful()){
                            BussinessRegistrationApiResponse bussinessRegistrationApiResponse = response.body();
                            String json = new Gson().toJson(bussinessRegistrationApiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<BussinessRegistrationApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    public void addNewBussinessRegistration(DataApiCallback dataApiCallback, List<BussinessRegstItem> regstItemList){
        BussinessRgisAddNewRequest bussinessRgisAddNewRequest = new BussinessRgisAddNewRequest();
        bussinessRgisAddNewRequest.setBussinessRegstItems(regstItemList);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(bussinessRgisAddNewRequest), JsonObject.class);
        ApiServices
                .getInstance()
                .addNewBussinessRegist(SharedPreferencesManager.getInstance().getPrefToken(),
                        jsonObject,
                        new Callback<BussinessRegistrationApiResponse>() {
                            @Override
                            public void onResponse(Call<BussinessRegistrationApiResponse> call, Response<BussinessRegistrationApiResponse> response) {
                                if (response.isSuccessful()){
                                    BussinessRegistrationApiResponse apiResponse = response.body();
                                    dataApiCallback.onDataApi(new Gson().toJson(apiResponse));
                                }
                                else {
                                    dataApiCallback.onApiLoadFail(response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<BussinessRegistrationApiResponse> call, Throwable t) {
                                dataApiCallback.onApiLoadFail(t.getMessage());
                            }
                        });
    }
    public void addNewAndCommitBussiness(DataApiCallback dataApiCallback,List<BussinessRegstItem> regstItemList){
        BussinessRgisAddNewRequest bussinessRgisAddNewRequest = new BussinessRgisAddNewRequest();
        bussinessRgisAddNewRequest.setBussinessRegstItems(regstItemList);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(bussinessRgisAddNewRequest), JsonObject.class);
        ApiServices
                .getInstance()
                .addAndCommitSignatureDocumentBussiness(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()){
                            ApiResponse apiResponse = response.body();
                            String json = new Gson().toJson(apiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message()+" "+response.body().getRETNMSSG());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    public void editBussinessRegst(DataApiCallback dataApiCallback, List<BussinessRegstItem> regstItemList){
        BussinessRgisAddNewRequest bussinessRgisAddNewRequest = new BussinessRgisAddNewRequest();
        bussinessRgisAddNewRequest.setBussinessRegstItems(regstItemList);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(bussinessRgisAddNewRequest), JsonObject.class);
        ApiServices
                .getInstance()
                .editBussinessRegst(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<BussinessRegistrationEditResponse>() {
                    @Override
                    public void onResponse(Call<BussinessRegistrationEditResponse> call, Response<BussinessRegistrationEditResponse> response) {
                        if (response.isSuccessful()){
                            ApiResponse apiResponse = response.body();
                            String json = new Gson().toJson(apiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.body().getRETNMSSG());
                        }
                    }

                    @Override
                    public void onFailure(Call<BussinessRegistrationEditResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }

    //Get detail Signature Switch Shift
    public void getDetailSignatureSwitchShift(String dcmnCode, String keyCode,DataApiCallback dataApiCallback){
        SignatureDetailsRequest detailsRequest = new SignatureDetailsRequest();
        detailsRequest.setDcmnCode(dcmnCode);
        detailsRequest.setKeyCode(keyCode);
        ApiServices
                .getInstance()
                .getDetailSignatureWitchShift(SharedPreferencesManager.getInstance().getPrefToken(), detailsRequest.convertToJson(), new Callback<SwitchShiftApiResponse>() {
                    @Override
                    public void onResponse(Call<SwitchShiftApiResponse> call, Response<SwitchShiftApiResponse> response) {
                        if (response.isSuccessful()){
                            SwitchShiftApiResponse switchShiftApiResponse = response.body();
                            String json = new Gson().toJson(switchShiftApiResponse);
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<SwitchShiftApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    // Danh sách loại chấm công đổi ca
    public void getListTimeKeppingDC(DataApiCallback dataApiCallback){
        ApiServices
                .getInstance()
                .getTimekeepingTypeDCList(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<TimekeepingTypeDCApiResponse>() {
                    @Override
                    public void onResponse(Call<TimekeepingTypeDCApiResponse> call, Response<TimekeepingTypeDCApiResponse> response) {
                        if (response.isSuccessful()){
                            String json = new Gson().toJson(response.body());
                            dataApiCallback.onDataApi(json);
                        }
                        else {
                            dataApiCallback.onApiLoadFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<TimekeepingTypeDCApiResponse> call, Throwable t) {
                        dataApiCallback.onApiLoadFail(t.getMessage());
                    }
                });
    }
    // Thêm mới đổi ca
    public void addNewSwitchShift(JsonObject boby,DataApiCallback dataApiCallback){
        ApiServices.getInstance().addNewSwithShift(SharedPreferencesManager.getInstance().getPrefToken(), boby, new Callback<SwitchShiftApiResponse>() {
            @Override
            public void onResponse(Call<SwitchShiftApiResponse> call, Response<SwitchShiftApiResponse> response) {
                if (response.isSuccessful()){
                    String json = new Gson().toJson(response.body());
                    dataApiCallback.onDataApi(json);
                }
                else{
                    dataApiCallback.onApiLoadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<SwitchShiftApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Chi tiết đơn xin phép
    public void getDetailAskPermistion(String dcmnCode, String keyCode,DataApiCallback dataApiCallback){
        SignatureDetailsRequest detailsRequest = new SignatureDetailsRequest();
        detailsRequest.setDcmnCode(dcmnCode);
        detailsRequest.setKeyCode(keyCode);
        ApiServices.getInstance().getDetailAskPermistion(SharedPreferencesManager.getInstance().getPrefToken(), detailsRequest.convertToJson(), new Callback<AskPermistionApiResponse>() {
            @Override
            public void onResponse(Call<AskPermistionApiResponse> call, Response<AskPermistionApiResponse> response) {
                if (response.isSuccessful()){
                    dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                }
                else dataApiCallback.onApiLoadFail(response.message());
            }

            @Override
            public void onFailure(Call<AskPermistionApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    //Danh sách bộ phận/ phòng ban
    public void getDepartmentList(DataApiCallback dataApiCallback){
        ApiServices.getInstance().getListDepartment(SharedPreferencesManager.getInstance().getPrefToken(), new Callback<DepartmentApiResponse>() {
            @Override
            public void onResponse(Call<DepartmentApiResponse> call, Response<DepartmentApiResponse> response) {
                if (response.isSuccessful()){
                    String json = new Gson().toJson(response.body());
                    dataApiCallback.onDataApi(json);
                }
                else {
                    dataApiCallback.onApiLoadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<DepartmentApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Trình ký chứng từ
    public void doApproveDocument(String dcmCode, String keyCode,String progCode, String note, String addEmployee,DataApiCallback dataApiCallback){
        ApproveDocumentRequest approveDocumentRequest = new ApproveDocumentRequest();
        approveDocumentRequest.setdCMNCODE(dcmCode);
        approveDocumentRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        approveDocumentRequest.setkEYCODE(keyCode);
        approveDocumentRequest.setnOTETEXT(note);
        approveDocumentRequest.setpRCSCODE(progCode);
        approveDocumentRequest.setaDDEMPL(addEmployee);

        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(approveDocumentRequest), JsonObject.class);
        System.out.println(jsonObject);
        ApiServices.getInstance().doApproveDocument(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
             if (response.isSuccessful()){
                 dataApiCallback.onDataApi(new Gson().toJson(response.body()));
             }
             else {
                 dataApiCallback.onApiLoadFail(response.message());
             }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Danh sách lãnh vực liên quan
    public void getAllLanhVucLienQuan(DataApiCallback dataApiCallback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("CONDFLTR","DcmnCode='LHCV'");
        ApiServices.getInstance().getAllLanhVucLienQuan(SharedPreferencesManager.getInstance().getPrefToken(),jsonObject, new Callback<LanhVucLienQuanApiResponse>() {
            @Override
            public void onResponse(Call<LanhVucLienQuanApiResponse> call, Response<LanhVucLienQuanApiResponse> response) {
                if (response.isSuccessful()){
                    dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                }
                else {
                    dataApiCallback.onApiLoadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<LanhVucLienQuanApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
    // Chi tiết phiếu liên hệ công vụ
    public void getDetailServiceContact(String dcmnCode, String keyCode,DataApiCallback dataApiCallback){
        SignatureDetailsRequest detailsRequest = new SignatureDetailsRequest();
        detailsRequest.setKeyCode(keyCode);
        detailsRequest.setDcmnCode(dcmnCode);
        ApiServices.getInstance().getDetailSiganutureServiceContact(SharedPreferencesManager.getInstance().getPrefToken(), detailsRequest.convertToJson(), new Callback<ServiceContactsApiResponse>() {
            @Override
            public void onResponse(Call<ServiceContactsApiResponse> call, Response<ServiceContactsApiResponse> response) {
                if (response.isSuccessful()){
                    dataApiCallback.onDataApi(new Gson().toJson(response.body()));
                }
                else {
                    dataApiCallback.onApiLoadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ServiceContactsApiResponse> call, Throwable t) {
                dataApiCallback.onApiLoadFail(t.getMessage());
            }
        });
    }
}
