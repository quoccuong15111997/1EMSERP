package com.firstems.erp.api.services;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.firstems.erp.api.RestApi;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalAddNewResponse;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedApiResponse;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoApiResponse;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionApiResponse;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionUpdateResponse;
import com.firstems.erp.api.model.response.bill_payment.AddNewPaymentResponse;
import com.firstems.erp.api.model.response.bill_payment.BillPaymentApiResponse;
import com.firstems.erp.api.model.response.bussiness.BussinessRegistrationEditResponse;
import com.firstems.erp.api.model.response.content.ContentApiResponse;
import com.firstems.erp.api.model.response.currency.CurrencyListApiResponse;
import com.firstems.erp.api.model.response.department.DepartmentApiResponse;
import com.firstems.erp.api.model.response.document.DocumentApiResponse;
import com.firstems.erp.api.model.response.document_category.DocumentCategoryApiResponse;
import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanApiResponse;
import com.firstems.erp.api.model.response.employee.EmployeeApiResponse;
import com.firstems.erp.api.model.response.export_place.ExportPlaceApiResponse;
import com.firstems.erp.api.model.response.label.SystemLabelApiResponse;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuanApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiApiResponse;
import com.firstems.erp.api.model.response.loai_de_nghi_tam_ung.LoaiDoiTuongNhanApiResponse;
import com.firstems.erp.api.model.response.loai_doi_tuong_lien_quan.LoaiDoiTuongLienQuanApiResponse;
import com.firstems.erp.api.model.response.location.LocationApiResponse;
import com.firstems.erp.api.model.response.locationtype.LocationTypeApiResponse;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.national.NationalApiResponse;
import com.firstems.erp.api.model.response.project_list.ProjectListApiResponse;
import com.firstems.erp.api.model.response.province.ProvinceApiResponse;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessApiResponse;
import com.firstems.erp.api.model.response.runcode.RunCodeUpDateApiResponse;
import com.firstems.erp.api.model.response.servicecontacts.AddNewServiceContactResponse;
import com.firstems.erp.api.model.response.servicecontacts.ServiceContactsApiResponse;
import com.firstems.erp.api.model.response.signature.SignatureApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegistrationApiResponse;
import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftApiResponse;
import com.firstems.erp.api.model.response.switchsift.SwitchsiftUpdateResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCTApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDCApiResponse;
import com.firstems.erp.common.DateDeserializer;
import com.firstems.erp.common.Util;
import com.firstems.erp.model.FileIncludeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class ApiServices {
    private static ApiServices apiService;

    private Retrofit retrofit;
    private ApiServices(String baseUrl) {
        initClient(baseUrl);
        System.out.println("URl in apiservice retrofit: "+baseUrl);
    }

    private void initClient(@NonNull String baseUrl) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer.DateSerializer())
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build());
        retrofit = builder.build();
    }

    public static void init(@NonNull String baseUrl) {
        if (apiService == null) {
            apiService = new ApiServices(baseUrl);
        }
    }
    public static void reset(){
        apiService=null;
    }

    public static ApiServices getInstance() {
        return apiService;
    }


    //Read File
    public void getFile(String url, String token, Callback<ResponseBody> callback){
        if (retrofit!=null){
            Call<ResponseBody> responseCall = retrofit.create(RestApi.class).getFile(url,token);
            responseCall.enqueue(callback);
        }
    }

    //Login
    public void doSystemLogin(JsonObject body, Callback<LoginReponse> callback){
        if (retrofit!=null){
            Call<LoginReponse> doSystemLogin = retrofit.create(RestApi.class).systemLogin(body);
            doSystemLogin.enqueue(callback);
        }
    }
    //Login locate
    public void doLocateLogin(String token,JsonObject body, Callback<LoginReponse> callback){
        if (retrofit!=null){
            Call<LoginReponse> doSystemLogin = retrofit.create(RestApi.class).systemLoginLocate(token,body);
            doSystemLogin.enqueue(callback);
        }
    }

    //Get data update
    public void getDataUpdate(String token, JsonObject body, Callback<RunCodeUpDateApiResponse> callback){
        if (retrofit!=null){
            Call<RunCodeUpDateApiResponse> runCodeUpDateApiResponseCall = retrofit.create(RestApi.class).getRunCodeUpdate(token,body);
            runCodeUpDateApiResponseCall.enqueue(callback);
        }
    }

    // Approve list
    public void getApprovedList(String token, JsonObject body, Callback<ApprovedApiResponse> callback){
        if (retrofit!=null){
            Call<ApprovedApiResponse> approvedApiResponseCall = retrofit.create(RestApi.class).getApprovedList(token,body);
            approvedApiResponseCall.enqueue(callback);
        }
    }
    //Approve info
    public void getInfoApprove(String token, JsonObject body, Callback<ApproveInfoApiResponse>callback){
        if (retrofit!=null){
            Call<ApproveInfoApiResponse> approveInfoApiResponseCall = retrofit.create(RestApi.class).getInfoApprove(token,body);
            approveInfoApiResponseCall.enqueue(callback);
        }
    }
    //Signature List
    public void getListSignature(String token, JsonObject body, Callback<SignatureApiResponse> callback){
        if (retrofit!=null){
            Call<SignatureApiResponse> signatureApiResponseCall= retrofit.create(RestApi.class).getListSignature(token,body);
            signatureApiResponseCall.enqueue(callback);
        }
    }
    //Employee list
    public void getListEmployee(String token,JsonObject body, Callback<EmployeeApiResponse> callback){
        if (retrofit!=null){
            Call<EmployeeApiResponse> employeeApiResponseCall = retrofit.create(RestApi.class).getListEmployee(token,body);
            employeeApiResponseCall.enqueue(callback);
        }
    }
    //National List
    public void getNationalList(String token, Callback<NationalApiResponse> callback){
        if (retrofit!=null){
            Call<NationalApiResponse> nationalApiResponseCall = retrofit.create(RestApi.class).getListNational(token);
            nationalApiResponseCall.enqueue(callback);
        }
    }
    //Location Type
    public void getLocationType(String token, Callback<LocationTypeApiResponse> callback){
        if (retrofit!=null){
            Call<LocationTypeApiResponse> locationTypeApiResponseCall=retrofit.create(RestApi.class).getListLocationType(token);
            locationTypeApiResponseCall.enqueue(callback);
        }
    }
    // Get List Kepping type CT
    public void getTimekeepingTypeList(String token, Callback<TimekeepingTypeCTApiResponse> callback){
        if (retrofit!=null){
            Call<TimekeepingTypeCTApiResponse>  timekeepingTypeCTApiResponseCall = retrofit.create(RestApi.class).getListTimekeepingType(token);
            timekeepingTypeCTApiResponseCall.enqueue(callback);
        }
    }
    // Get List Province
    public void getListProvince(String token, Callback<ProvinceApiResponse> callback){
        if (retrofit!=null){
            Call<ProvinceApiResponse> provinceApiResponseCall= retrofit.create(RestApi.class).getListProvince(token);
            provinceApiResponseCall.enqueue(callback);
        }
    }

    //Get Detail an Review Progress
    public void getDetailDocument(String token, JsonObject body, Callback<ReviewProcessApiResponse> callback){
        if (retrofit!=null){
            Call<ReviewProcessApiResponse> reviewProcessApiResponseCall = retrofit.create(RestApi.class).getDeatailReviewProgress(token,body);
            reviewProcessApiResponseCall.enqueue(callback);
        }
    }
    //Get Detail Signature Bussiness Registration
    public void getDetailSignatureBussinessRegistration(String token, JsonObject body, Callback<BussinessRegistrationApiResponse> callback){
        if (retrofit!=null){
            Call<BussinessRegistrationApiResponse> bussinessRegistrationApiResponseCall = retrofit.create(RestApi.class).getDetailBussinessRegst(token,body);
            bussinessRegistrationApiResponseCall.enqueue(callback);
        }
    }
    //Get detail Signature Switch Shift
    public void getDetailSignatureWitchShift(String token, JsonObject body, Callback<SwitchShiftApiResponse> callback){
        if (retrofit!=null){
            Call<SwitchShiftApiResponse> switchShiftApiResponseCall = retrofit.create(RestApi.class).getDetailSwitchShift(token,body);
            switchShiftApiResponseCall.enqueue(callback);
        }
    }
    //Add new Bussiness Regst
    public void addNewBussinessRegist(String token,JsonObject body, Callback<BussinessRegistrationApiResponse> callback){
        if (retrofit!=null){
            Call<BussinessRegistrationApiResponse> bussinessRegistrationApiResponseCall = retrofit.create(RestApi.class).addNewBussinessRegt(token,body);
            bussinessRegistrationApiResponseCall.enqueue(callback);
        }
    }
    //Edit Bussiness Regst
    public void editBussinessRegst(String token, JsonObject body, Callback<BussinessRegistrationEditResponse> callback){
        if (retrofit!=null){
            Call<BussinessRegistrationEditResponse> apiResponseCall = retrofit.create(RestApi.class).editBussinessRegstDocument(token,body);
            apiResponseCall.enqueue(callback);
        }
    }
    //Commit document
    public void commitDocument(String token, JsonObject body, Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiResponseCall = retrofit.create(RestApi.class).commitDocument(token,body);
            apiResponseCall.enqueue(callback);
        }
    }
    // Add new and commit document
    public void addAndCommitSignatureDocumentBussiness(String token, JsonObject body,Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiServicesCall = retrofit.create(RestApi.class).addNewAndcommitSignatureDocumentBusiness(token,body);
            apiServicesCall.enqueue(callback);
        }
    }
    // Delete document
    public void deleteDocument(String token, JsonObject body, Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiResponseCall = retrofit.create(RestApi.class).deleteDocument(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    // Get List Kepping type DC
    public void getTimekeepingTypeDCList(String token, Callback<TimekeepingTypeDCApiResponse> callback){
        if (retrofit!=null){
            Call<TimekeepingTypeDCApiResponse>  timekeepingTypeCTApiResponseCall = retrofit.create(RestApi.class).getListTimekeepingTypeDC(token);
            timekeepingTypeCTApiResponseCall.enqueue(callback);
        }
    }
    public void addNewSwithShift(String token, JsonObject boby,Callback<SwitchShiftApiResponse> callback){
        if (retrofit!=null){
            Call<SwitchShiftApiResponse> switchShiftApiResponseCall = retrofit.create(RestApi.class).addNewSwithShift(token,boby);
            switchShiftApiResponseCall.enqueue(callback);
        }
    }
    //Chi tiết đơn xin phép
    public void getDetailAskPermistion(String token, JsonObject body, Callback<AskPermistionApiResponse> callback){
        if (retrofit!=null){
            Call<AskPermistionApiResponse> askPermistionApiResponseCall= retrofit.create(RestApi.class).getDetailAskPermistion(token,body);
            askPermistionApiResponseCall.enqueue(callback);
        }
    }

    // Thêm mới đơn xin nghỉ phép
    public void addNewAskPermistion(String token, JsonObject body,Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiResponseCall =retrofit.create(RestApi.class).addNewAskPermistion(token,body);
            apiResponseCall.enqueue(callback);
        }
    }

    // Thêm mới và trình ký đơn xin nghỉ phép
    public void addNewAndCommitAskPermistion(String token, JsonObject body,Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiResponseCall =retrofit.create(RestApi.class).addAndCommitAskPermistion(token,body);
            apiResponseCall.enqueue(callback);
        }
    }

    // Chỉnh sửa đơn xin nghỉ phép
    public void editAskPermistion(String token, JsonObject body, Callback<AskPermistionUpdateResponse> callback){
        if (retrofit!=null){
            Call<AskPermistionUpdateResponse> apiResponseCall = retrofit.create(RestApi.class).editAskpermistion(token,body);
            apiResponseCall.enqueue(callback);
        }
    }

    public void addAndCommitSignatureDocumentSwitchShift(String token, JsonObject body,Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiServicesCall = retrofit.create(RestApi.class).addNewAndcommitSignatureDocumentSwitchShift(token,body);
            apiServicesCall.enqueue(callback);
        }
    }
    public void getListDepartment(String token, Callback<DepartmentApiResponse> callback){
        if (retrofit!=null){
            Call<DepartmentApiResponse> departmentApiResponseCall = retrofit.create(RestApi.class).getAllDepartment(token);
            departmentApiResponseCall.enqueue(callback);
        }
    }
    public void doApproveDocument( String token,JsonObject body, Callback<ApiResponse> callback){
        if (retrofit!=null){
            Call<ApiResponse> apiResponseCall= retrofit.create(RestApi.class).doAproveDocument(token,body);
            apiResponseCall.enqueue(callback);
        }
    }
    // cập nhật đổi ca
    public void updateSwitchsift(String token, JsonObject boby, Callback<SwitchsiftUpdateResponse> callback){
        if (retrofit!=null){
            Call<SwitchsiftUpdateResponse> apiResponseCall = retrofit.create(RestApi.class).updateSwithShift(token,boby);
            apiResponseCall.enqueue(callback);
        }
    }
    
    //Chi tiết liên hệ công vụ
    public void getDetailSiganutureServiceContact(String token, JsonObject body,Callback<ServiceContactsApiResponse> callback){
        if (retrofit!=null){
            Call<ServiceContactsApiResponse> serviceContactsApiResponseCall= retrofit.create(RestApi.class).getDetailSiganutureServiceContact(token,body);
            serviceContactsApiResponseCall.enqueue(callback);
        }
    }
    //Thêm mới liên hệ công vụ
    public void addNewLienHeCongVu(String token, JsonObject body, Callback<AddNewServiceContactResponse> callback){
        if (retrofit!=null){
            Call<AddNewServiceContactResponse> apiResponseCall =retrofit.create(RestApi.class).addNewLienHeCongVu(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    //Thêm mới và trình ký liên hệ công vụ
    public void addAddCommitLienHeCongVu(String token, JsonObject body, Callback<AddNewServiceContactResponse> callback){
        if (retrofit!=null){
            Call<AddNewServiceContactResponse> apiResponseCall = retrofit.create(RestApi.class).addAndCommitLienHeCongVu(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    //Chỉnh sửa liên hệ công vụ
    public void editLienHeCongVu(String token, JsonObject body, Callback<AddNewServiceContactResponse> callback){
        if (retrofit!=null){
            Call<AddNewServiceContactResponse> apiResponseCall = retrofit.create(RestApi.class).editLienHeCongVu(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    // Lãnh vực liên quan
    public void getAllLanhVucLienQuan(String token,JsonObject body, Callback<LanhVucLienQuanApiResponse> callback){
        if (retrofit!=null){
            Call<LanhVucLienQuanApiResponse> lanhVucLienQuanApiResponseCall = retrofit.create(RestApi.class).getAllLanhVucLienQuan(token,body);
            lanhVucLienQuanApiResponseCall.enqueue(callback);
        }
    }
    // Chi tiết phiếu đề nghị thanh toán
    public void getDetailBillPament(String token, JsonObject boby, Callback<BillPaymentApiResponse> callback){
        if (retrofit!=null){
            Call<BillPaymentApiResponse> billPaymentApiResponseCall = retrofit.create(RestApi.class).getDetailBillPayment(token, boby);
            billPaymentApiResponseCall.enqueue(callback);
        }
    }
    // Thêm mới phiếu đề nghị thanh toán
    public void addNewBillPayment(String token, JsonObject body, Callback<AddNewPaymentResponse> callback){
        if (retrofit!=null){
            Call<AddNewPaymentResponse> apiResponseCall = retrofit.create(RestApi.class).addNewBillPayment(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    // Thêm mới và trình ký phiếu đề nghị thanh toán
    public void addAndCommitBillpayment(String token , JsonObject body, Callback<AddNewPaymentResponse> callback){
        if (retrofit!=null){
            Call<AddNewPaymentResponse>  apiResponseCall = retrofit.create(RestApi.class).andAndCommitBillPayment(token,body);
            apiResponseCall.enqueue(callback);
        }
    }
    //Chỉnh sửa phiếu đề nghị thanh toán
    public void editBillPayment(String token, JsonObject body, Callback<AddNewPaymentResponse> callback){
        if (retrofit!=null){
            Call<AddNewPaymentResponse>apiResponseCall = retrofit.create(RestApi.class).editBillpayment(token, body);
            apiResponseCall.enqueue(callback);
        }
    }

    //Chi tiết phiếu tạm ứng
    public void getDetailAdvanceProposal(String token, JsonObject body, Callback<AdvanceProposalFormApiResponse> callback){
        if (retrofit!=null){
            Call<AdvanceProposalFormApiResponse> advanceProposalFormApiResponseCall = retrofit.create(RestApi.class).getDetailAdvanceProposal(token,body);
            advanceProposalFormApiResponseCall.enqueue(callback);
        }
    }
    //Thêm mới phiếu tạm ứng
    public void addNewAdvanceProposal(String token, JsonObject boby, Callback<AdvanceProposalAddNewResponse>callback){
        if (retrofit!=null){
            Call<AdvanceProposalAddNewResponse> apiResponseCall =retrofit.create(RestApi.class).addNewAdvanceProposal(token, boby);
            apiResponseCall.enqueue(callback);
        }
    }
    //Thêm mới và trình ký phiếu tạm ứng
    public void addAndCommitAdvanceProposal(String token, JsonObject body, Callback<AdvanceProposalAddNewResponse> callback){
        if (retrofit!=null){
            Call<AdvanceProposalAddNewResponse> apiResponseCall= retrofit.create(RestApi.class).addAndCommitAdvanceProposal(token,body);
            apiResponseCall.enqueue(callback);
        }
    }
    //Chỉnh sửa phiếu tạm ứng
    public void editAdvanceProposal(String token, JsonObject body, Callback<AdvanceProposalAddNewResponse> callback){
        if (retrofit!=null){
            Call<AdvanceProposalAddNewResponse> apiResponseCall =retrofit.create(RestApi.class).editAdvanceProposal(token, body);
            apiResponseCall.enqueue(callback);
        }
    }
    // Danh sách loại đối tượng liên quan
    public void getAllLoaiDoiTuongLienQuan(String token, Callback<LoaiDoiTuongLienQuanApiResponse> callback){
        if (retrofit!=null){
            Call<LoaiDoiTuongLienQuanApiResponse> loaiDoiTuongLienQuanApiResponseCall =retrofit.create(RestApi.class).getAllLoaiDoiTuongLienQuan(token);
            loaiDoiTuongLienQuanApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách công trình/ dự án
    public void getAllProjectList(String token, Callback<ProjectListApiResponse> callback){
        if (retrofit!=null){
            Call<ProjectListApiResponse> projectListApiResponseCall = retrofit.create(RestApi.class).getAllProjectList(token);
            projectListApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách loại tiền tệ
    public void getAlCurrency(String token, Callback<CurrencyListApiResponse> callback){
        if (retrofit!=null){
            Call<CurrencyListApiResponse> currencyListApiResponseCall = retrofit.create(RestApi.class).getAllCurrencyList(token);
            currencyListApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách loại đối tượng nhận
    public void getAllLoaiDoiTuongNhan(String token, Callback<LoaiDoiTuongNhanApiResponse> callback){
        if (retrofit!=null){
            Call<LoaiDoiTuongNhanApiResponse> loaiDeNghiTamUngApiResponseCall = retrofit.create(RestApi.class).getAllLoaiDoiTuongNhan(token);
            loaiDeNghiTamUngApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách loại đề nghị
    public void getAllLoaiDeNghi(String token, JsonObject body, Callback<LoaiDeNghiApiResponse> callback){
        if (retrofit!=null){
            Call<LoaiDeNghiApiResponse> loaiDeNghiApiResponseCall = retrofit.create(RestApi.class).getAllLoaiDeNghi(token, body);
            loaiDeNghiApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách đối tượng nhận
    public void getAllDoiTuongNhan(String token, JsonObject body, Callback<DoiTuongNhanApiResponse> callback) {
        if (retrofit!=null){
            Call<DoiTuongNhanApiResponse> doiTuongNhanApiResponseCall = retrofit.create(RestApi.class).getAllDoiTuongNhan(token,body);
            doiTuongNhanApiResponseCall.enqueue(callback);
        }
    }
    
    // Upload File
    public void uploadFile(String token, String dcmnCode, String keyCode, List<String> pathList, List<FileIncludeModel> fileIncludeModelList, Callback<ApiResponse>callback ){
        if (retrofit!=null){
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (String path : pathList) {
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("Files", Util.removeAccent(file.getName()), requestBody);
                System.out.println( Util.removeAccent(file.getName()));
                System.out.println(file.getName());
                
            }
            for (FileIncludeModel fileIncludeModel : fileIncludeModelList){
                if (fileIncludeModel.getFilePath()!=null){
                    File file = new File(fileIncludeModel.getFilePath());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    builder.addFormDataPart("Files", Util.removeAccent(file.getName()), requestBody);
                    System.out.println( Util.removeAccent(file.getName()));
                    System.out.println(file.getName());
                }
            }
            builder.addFormDataPart("DCMNCODE", dcmnCode);
            builder.addFormDataPart("KEY_CODE", keyCode);
            MultipartBody requestBody = builder.build();
            Call<ApiResponse> call = retrofit.create(RestApi.class).uploadFile(token,requestBody);
            call.enqueue(callback);
        }
    }
    
    // Delete File
    public void deleteFile(String token, String dcmnCode, String keyCode, String fileCode,Callback<ApiResponse>callback){
        if (retrofit!=null){
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("DCMNCODE", dcmnCode);
            builder.addFormDataPart("KEY_CODE", keyCode);
            builder.addFormDataPart("FILECODE", fileCode);
            MultipartBody requestBody = builder.build();
            Call<ApiResponse> call = retrofit.create(RestApi.class).deleteFile(token,requestBody);
            call.enqueue(callback);
        }
    }
    public void uploadAttactment(String token, String dcmnCode, String keyCode, File file, Callback<ApiResponse>callback){
        if (retrofit!=null){
            RequestBody dcmn = RequestBody.create(MediaType.parse("text/plain"),dcmnCode);
    
            RequestBody key = RequestBody.create(MediaType.parse("text/plain"),keyCode);
    
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    
            MultipartBody.Part body =MultipartBody.Part.createFormData("Files", file.getName(), requestFile);
            
            Call<ApiResponse> call = retrofit.create(RestApi.class).uploadAttachment(token,body,dcmn,key);
            call.enqueue(callback);
        }
    }
    public void deleteAttactment(String token, String dcmnCode, String keyCode, String fileCode, Callback<ApiResponse>callback){
        if (retrofit!=null){
            RequestBody dcmn = RequestBody.create(MediaType.parse("text/plain"),dcmnCode);
    
            RequestBody key = RequestBody.create(MediaType.parse("text/plain"),keyCode);
            
            RequestBody file = RequestBody.create(MediaType.parse("text/plain"),fileCode);
            
            Call<ApiResponse> call = retrofit.create(RestApi.class).deleteFileInclude(token,dcmn,key,file);
            call.enqueue(callback);
        }
    }
    // Danh sách chứng từ, tài liệu
    public void getAllDocumentList(String token, JsonObject body, Callback<DocumentApiResponse> callback){
        if (retrofit!=null){
            Call<DocumentApiResponse> documentApiResponseCall = retrofit.create(RestApi.class).getListDocument(token, body);
            documentApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách chi  nhánh công ty
    public void getAllLocation(String token, Callback<LocationApiResponse>callback){
        if (retrofit!=null){
            Call<LocationApiResponse> locationApiResponseCall = retrofit.create(RestApi.class).getAllLocation(token);
            locationApiResponseCall.enqueue(callback);
        }
    }
    // nhãn hệ thống
    public void getSystemLabel(String token, JsonObject body, Callback<SystemLabelApiResponse> callback){
        if (retrofit!=null){
            Call<SystemLabelApiResponse> systemLabelApiResponseCall = retrofit.create(RestApi.class).getAllSystemLabel(token, body);
            systemLabelApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách nơi phát hành
    public void getListExportPlace(String token, JsonObject body, Callback<ExportPlaceApiResponse> callback){
        if (retrofit!=null){
            Call<ExportPlaceApiResponse> gExportPlaceApiResponseCall = retrofit.create(RestApi.class).getListExportPlace(token,body);
            gExportPlaceApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách nội dung chính
    public void getListContent(String token, JsonObject body, Callback<ContentApiResponse> callback){
        if (retrofit!=null){
            Call<ContentApiResponse> contentApiResponseCall =  retrofit.create(RestApi.class).getListContent(token, body);
            contentApiResponseCall.enqueue(callback);
        }
    }
    // Danh sách lại tài liệu
    public void getListDocumentCategory(String token, JsonObject body, Callback<DocumentCategoryApiResponse> callback){
        if (retrofit!=null){
            Call<DocumentCategoryApiResponse> documentCategoryApiResponseCall = retrofit.create(RestApi.class).getListdocumentCategory(token,body);
            documentCategoryApiResponseCall.enqueue(callback);
        }
    }
}
