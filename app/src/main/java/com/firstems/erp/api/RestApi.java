package com.firstems.erp.api;

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
import com.firstems.erp.api.model.response.phieu_tam_ung.PhieuTamUngApiResponse;
import com.firstems.erp.api.model.response.product.ProgressApiResponse;
import com.firstems.erp.api.model.response.product.ProgressProductDetailApiResponse;
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
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public interface RestApi {
    
    //Read file
    @GET("{url}")
    Call<ResponseBody> getFile(@Path("url") String url, @Header("TOKEN") String token);
    
    //Login
    @POST("/Api/data/runApi_syst?run_Code=SYS001.02.001")
    Call<LoginReponse> systemLogin(@Body JsonObject body);
    
    //Login with locate
    @POST("/Api/data/runApi_data?run_Code=SYS001.02.002")
    Call<LoginReponse> systemLoginLocate(@Header("TOKEN") String token, @Body JsonObject body);
    
    //Update
    @POST("/Api/data/runApi_data?run_Code=SYS001.01.999")
    Call<RunCodeUpDateApiResponse> getRunCodeUpdate(@Header("TOKEN") String token, @Body JsonObject boby);
    
    //nhãn hệ thống
    @POST("/Api/data/runApi_syst?run_Code=SYS001.01.001")
    Call<SystemLabelApiResponse> getAllSystemLabel(@Header("TOKEN") String token, @Body JsonObject boby);
    
    //Approved
    @POST("/Api/data/runApi_data?run_Code=ERP001.02.001")
    Call<ApprovedApiResponse> getApprovedList(@Header("TOKEN") String token , @Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=ERP001.02.002")
    Call<ApproveInfoApiResponse> getInfoApprove(@Header("TOKEN") String token , @Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=ERP001.01.001")
    Call<SignatureApiResponse> getListSignature(@Header("TOKEN") String token , @Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=CTL001.06.002")
    Call<EmployeeApiResponse> getListEmployee(@Header("TOKEN") String token,@Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=CTL001.06.005")
    Call<NationalApiResponse> getListNational(@Header("TOKEN") String token);
    
    @POST("/Api/data/runApi_data?run_Code=CTL001.06.006")
    Call<LocationTypeApiResponse> getListLocationType(@Header("TOKEN") String token);
    
    @POST("/Api/data/runApi_data?run_Code=CTL001.06.004")
    Call<TimekeepingTypeCTApiResponse> getListTimekeepingType(@Header("TOKEN") String token);
    
    @POST("/Api/data/runApi_data?run_Code=CTL001.01.001")
    Call<ProvinceApiResponse> getListProvince(@Header("TOKEN") String token);
    
    //Detail
    @POST("/Api/data/runApi_data?run_Code=ERP001.02.002")
    Call<ReviewProcessApiResponse> getDeatailReviewProgress(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Detail Signature Bussiness Registration
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.003")
    Call<BussinessRegistrationApiResponse> getDetailBussinessRegst(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Detail Signature Switch Shift
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.003")
    Call<SwitchShiftApiResponse> getDetailSwitchShift(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Add New Bussiness Regist
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0021")
    Call<BussinessRegistrationApiResponse> addNewBussinessRegt(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Edit Bussiness Regits
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0022")
    Call<BussinessRegistrationEditResponse> editBussinessRegstDocument(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Trình ký chứng từ
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.005")
    Call<ApiResponse> commitDocument(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Thêm mới và trình ký chứng từ đăng ký công tác
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0023")
    Call<ApiResponse> addNewAndcommitSignatureDocumentBusiness(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Xóa chứng từ
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.004")
    Call<ApiResponse> deleteDocument(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Danh sách loại chấm công đổi ca
    @POST("/Api/data/runApi_data?run_Code=CTL001.06.003")
    Call<TimekeepingTypeDCApiResponse> getListTimekeepingTypeDC(@Header("TOKEN") String token);
    
    // Thêm mới đổi ca
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0031")
    Call<SwitchShiftApiResponse> addNewSwithShift(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Thêm mới và trình ký chứng từ đổi ca
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0033")
    Call<ApiResponse> addNewAndcommitSignatureDocumentSwitchShift(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Cập nhật đổi ca
    @POST("/Api/data/runApi_data?run_Code=HMR001.01.0032")
    Call<SwitchsiftUpdateResponse> updateSwithShift(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Chi tiết Đơn xin phép
    @POST("/Api/data/runApi_Data?run_Code=SYS001.06.003")
    Call<AskPermistionApiResponse> getDetailAskPermistion(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Thêm mới đơn xin nghỉ phép
    @POST("/Api/data/runApi_Data?run_Code=HMR001.01.0011")
    Call<ApiResponse> addNewAskPermistion(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Thêm mới và trình ký đơn xin nghỉ phép
    @POST("/Api/data/runApi_Data?run_Code=HMR001.01.0013")
    Call<ApiResponse> addAndCommitAskPermistion(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Chỉnh sửa đơn xin nghỉ phép
    @POST("/Api/data/runApi_Data?run_Code=HMR001.01.0012")
    Call<AskPermistionUpdateResponse> editAskpermistion(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Danh sách phòng ban
    @POST("/Api/data/runApi_Data?run_Code=CTL001.06.001")
    Call<DepartmentApiResponse> getAllDepartment(@Header("TOKEN") String token);
    
    // Phê duyệt chứng từ
    @POST("/Api/data/runApi_Data?run_Code=ERP001.02.003")
    Call<ApiResponse> doAproveDocument(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Chi tiết phiếu tạm ứng
    
    //Thêm mới phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0011")
    Call<ApiResponse> addNewPhieuTamUng(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Thêm mới và trình ký phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0013")
    Call<ApiResponse> addAndCommitPhieuTamUng(@Header("TOKEN") String token , @Body JsonObject body);
    
    // Chỉnh sủa phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0012")
    Call<ApiResponse> editPhieuTamUng(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Chi tiết phiếu đề nghị thanh toán
    
    //Thêm mới phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0021")
    Call<ApiResponse> addNewPhieuDeNghiThanhToan(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Thêm mới và trình ký phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0023")
    Call<ApiResponse> addAndCommitPhieudeNghiThanhToan(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Chỉnh sửa phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0022")
    Call<ApiResponse> editPhieuDeNghiThanhToan(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Chi tiết liên hệ công vụ
    @POST("/Api/data/runApi_Data?run_Code=SYS001.06.003")
    Call<ServiceContactsApiResponse> getDetailSiganutureServiceContact(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Thêm mới liên hệ công vụ
    @POST("/Api/data/runApi_Data?run_Code=HMR001.02.0011")
    Call<AddNewServiceContactResponse> addNewLienHeCongVu(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Thêm mới và trình ký liên hệ công vụ
    @POST("/Api/data/runApi_Data?run_Code=HMR001.02.0013")
    Call<AddNewServiceContactResponse> addAndCommitLienHeCongVu(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Chỉnh sủa liên hệ công vụ
    @POST("/Api/data/runApi_Data?run_Code=HMR001.02.0012")
    Call<AddNewServiceContactResponse> editLienHeCongVu(@Header("TOKEN") String token , @Body JsonObject body);
    
    //Lãnh vực liên quan
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.007")
    Call<LanhVucLienQuanApiResponse> getAllLanhVucLienQuan(@Header("TOKEN") String token, @Body JsonObject body);
    
    //Chi tiết phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=SYS001.06.003")
    Call<BillPaymentApiResponse> getDetailBillPayment(@Header("TOKEN") String token,@Body JsonObject body);
    
    //Thêm mới phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0021")
    Call<AddNewPaymentResponse> addNewBillPayment(@Header("TOKEN") String token, @Body JsonObject body);
    
    //Thêm mới và trình ký phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0023")
    Call<AddNewPaymentResponse> andAndCommitBillPayment(@Header("TOKEN") String token,@Body JsonObject body);
    
    //Chỉnh sửa phiếu đề nghị thanh toán
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0022")
    Call<AddNewPaymentResponse> editBillpayment(@Header("TOKEN") String token,@Body JsonObject body);
    
    //Chi tiết phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=SYS001.06.003")
    Call<AdvanceProposalFormApiResponse> getDetailAdvanceProposal(@Header("TOKEN") String token,@Body JsonObject body);
    
    //Thêm mới phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0011")
    Call<AdvanceProposalAddNewResponse> addNewAdvanceProposal(@Header("TOKEN") String token, @Body JsonObject body);
    
    //Thêm mới và trình ký phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0013")
    Call<AdvanceProposalAddNewResponse> addAndCommitAdvanceProposal(@Header("TOKEN") String token,@Body JsonObject body);
    
    //Chỉnh sửa phiếu tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=ACC001.01.0012")
    Call<AdvanceProposalAddNewResponse> editAdvanceProposal(@Header("TOKEN") String token,@Body JsonObject body);
    
    // Danh sách loại đối tượng liên quan
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.006")
    Call<LoaiDoiTuongLienQuanApiResponse> getAllLoaiDoiTuongLienQuan(@Header("TOKEN") String token);
    
    // Danh sách công trình/ dự án
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.004")
    Call<ProjectListApiResponse> getAllProjectList(@Header("TOKEN") String token);
    
    // Danh sách loại tiền tệ
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.003")
    Call<CurrencyListApiResponse> getAllCurrencyList(@Header("TOKEN") String token);
    
    // Danh sách loại đề nghị tạm ứng
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.002")
    Call<LoaiDoiTuongNhanApiResponse> getAllLoaiDoiTuongNhan(@Header("TOKEN") String token);
    
    // Danh sách loại đề nghị
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.007")
    Call<LoaiDeNghiApiResponse> getAllLoaiDeNghi(@Header("TOKEN") String token,@Body JsonObject body);
    
    @POST("/Api/data/runApi_Data?run_Code=CTL001.07.005")
    Call<DoiTuongNhanApiResponse> getAllDoiTuongNhan(@Header("TOKEN") String token,@Body JsonObject body);
    
    // Upload file
    @POST("/Api/data/runApi_File?run_Code=SYS001.06.006")
    Call<ApiResponse> uploadFile(@Header("TOKEN") String token,
                                 @Body RequestBody files);
    
    @Multipart
    @POST("/Api/data/runApi_File?run_Code=SYS001.06.006")
    Call<ApiResponse> uploadAttachment(@Header("TOKEN") String token,
                                       @Part MultipartBody.Part filePart,
                                       @Part("DCMNCODE") RequestBody dcmnCode,
                                       @Part("KEY_CODE") RequestBody keyCode);
    // Xóa file
    @POST("/Api/data/runApi_File?run_Code=SYS001.06.007")
    Call<ApiResponse> deleteFile(@Header("TOKEN") String token,
                                 @Body RequestBody files);
    
    @Multipart
    @POST("/Api/data/runApi_File?run_Code=SYS001.06.007")
    Call<ApiResponse> deleteFileInclude(@Header("TOKEN") String token,
                                        @Part("DCMNCODE") RequestBody dcmnCode,
                                        @Part("KEY_CODE") RequestBody keyCode,
                                        @Part("FILECODE") RequestBody fileCode);
    
    @POST("/Api/data/runApi_Data?run_Code=SYS001.06.008")
    Call<DocumentApiResponse> getListDocument(@Header("TOKEN") String token, @Body JsonObject body);
    
    @POST("/Api/data/runApi_Data?run_Code=`CTL001.01.005`")
    Call<LocationApiResponse> getAllLocation(@Header("TOKEN") String token);
    
    // Danh sách nơi phát hành
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.009")
    Call<ExportPlaceApiResponse> getListExportPlace(@Header("TOKEN") String token, @Body JsonObject body);
    
    // Danh sách nội dung chính
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.009")
    Call<ContentApiResponse> getListContent(@Header("TOKEN") String token, @Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=SYS001.06.009")
    Call<DocumentCategoryApiResponse> getListdocumentCategory(@Header("TOKEN") String token, @Body JsonObject body);
    
    @POST("/Api/data/runApi_data?run_Code=ACC001.01.003")
    Call<PhieuTamUngApiResponse> getListPhieuTamUngNeuCo(@Header("TOKEN") String token, @Body JsonObject body);

    /*===========================/PRODUCT/===========================/*/
    @POST("/Api/data/runApi_Data?run_Code=PRD001.02.006")
    Call<ProgressApiResponse> getAllProgress(@Header("TOKEN") String token);
    @POST("/Api/data/runApi_Data?run_Code=PRD001.02.003")
    Call<ProgressProductDetailApiResponse> getProgressProductDetail(@Header("TOKEN") String token, @Body JsonObject body);
    // Cập nhật lệnh sản xuất
    @POST("/Api/data/runApi_Data?run_Code=PRD001.02.004")
    Call<ApiResponse> upDateProgressProduct(@Header("TOKEN") String token, @Body JsonObject body);
}
