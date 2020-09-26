package com.firstems.erp.ui.signature.billpaymentrequest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DetailTicketDetailAdapter;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.request.bill_payment.BillPaymentRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.bill_payment.AddNewPaymentResponse;
import com.firstems.erp.api.model.response.bill_payment.BillPaymentApiResponse;
import com.firstems.erp.api.model.response.bill_payment.BillPaymentDetail;
import com.firstems.erp.api.model.response.bill_payment.BillPaymentHeader;
import com.firstems.erp.api.model.response.currency.CurrencyItem;
import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanItem;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiItem;
import com.firstems.erp.api.model.response.loai_doi_tuong_lien_quan.LoaiDoiTuongLienQuanItem;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.SaveFileToLocalCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.UploadFileCallback;
import com.firstems.erp.callback.runcode.LoadDataAsynCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.BillPaymentRequestFragmentBinding;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.file.GetFileHelper;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.file.FileFragment;
import com.firstems.erp.ui.shared.loaidoituongnhan.DoiTuongNhanActivity;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.firstems.erp.ui.signature.billpaymentrequest.model.TicketBillPaymentDetail;
import com.firstems.erp.ui.signature.billpaymentrequest.sophieutamung.SoPhieuTamUngActivity;
import com.firstems.erp.ui.signature.billpaymentrequest.ticket.TicketBillPaymentRequestActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.firstems.erp.ui.shared.file.FileFragment.listFilePath;

public class BillPaymentRequestFragment extends CommonFragment {
    
    private BillPaymentRequestViewModel mViewModel;
    private BillPaymentRequestFragmentBinding billPaymentRequestFragmentBinding;
    private TextView txtTitle;
    private List<CurrencyItem> currencyItemList;
    private ArrayAdapter adapterCurrency;
    private List<LoaiDeNghiItem> loaiDeNghiItemList;
    private ArrayAdapter adapterLoaiDeNghi;
    private List<LoaiDoiTuongLienQuanItem> loaiDoiTuongList;
    private ArrayAdapter adapterLoaiDoiTuong;
    private List<LoaiDeNghiItem> loaiChiTieuItemList;
    private ArrayAdapter adapterLoaiChiTieu;
    private int CODE_SELECT_DOI_TUONG_NHAN = 1111 ;
    private DoiTuongNhanItem doiTuongNhanItemSelected;
    private int CODE_ADD_DETAIL = 22222 ;
    private int CODE_EDIT_DETAIL = 33333 ;
    private DecimalFormat dfnd = new DecimalFormat("#,###");
    private TicketBillPaymentDetail ticketBillPaymentDetailEdit;
    private int positionEdit= 0 ;
    private SignatureItemApiResponse signatureItemApiResponse;
    private AccessRole accessRole;
    private Date mainDate;
    private int CODE_SELECT_SO_PHIEU_TAM_UNG = 145;
    
    private List<TicketBillPaymentDetail> listDetail;
    private DetailTicketDetailAdapter detailTicketDetailAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        billPaymentRequestFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.bill_payment_request_fragment, container, false);
        
        initText();
        addControls();
        addEvents();
        return billPaymentRequestFragmentBinding.getRoot();
    }
    
    private void initText() {
        billPaymentRequestFragmentBinding.txtTitleDateCreate.setText(SharedPreferencesManager.getSystemLabel(117));
        billPaymentRequestFragmentBinding.txtTitleLoaiDeNghi.setText(SharedPreferencesManager.getSystemLabel(124));
        billPaymentRequestFragmentBinding.txtTitleLoaiChiTieu.setText(SharedPreferencesManager.getSystemLabel(125));
        billPaymentRequestFragmentBinding.txtTitleLoaiDoiTuongNhan.setText(SharedPreferencesManager.getSystemLabel(109));
        billPaymentRequestFragmentBinding.txtTitleTenDoiTuongNhan.setText(SharedPreferencesManager.getSystemLabel(110));
        billPaymentRequestFragmentBinding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(126));
        billPaymentRequestFragmentBinding.txtTiteDonViTienTe.setText(SharedPreferencesManager.getSystemLabel(112));
        billPaymentRequestFragmentBinding.txtTitleTiGia.setText(SharedPreferencesManager.getSystemLabel(113));
        billPaymentRequestFragmentBinding.txtTitleSoPhieuTamUng.setText(SharedPreferencesManager.getSystemLabel(118));
        billPaymentRequestFragmentBinding.txtTitleNgayTamUng.setText(SharedPreferencesManager.getSystemLabel(107));
        billPaymentRequestFragmentBinding.txtTitleSoTienTamUng.setText(SharedPreferencesManager.getSystemLabel(114));
        billPaymentRequestFragmentBinding.txtTitleSoTienDeNghiChi.setText(SharedPreferencesManager.getSystemLabel(127));
        billPaymentRequestFragmentBinding.txtTitleSoTienChi.setText(SharedPreferencesManager.getSystemLabel(128));
        billPaymentRequestFragmentBinding.txtTitleCode.setText(SharedPreferencesManager.getSystemLabel(129));
        billPaymentRequestFragmentBinding.txtTitleLyDoChiTien.setText(SharedPreferencesManager.getSystemLabel(130));
        billPaymentRequestFragmentBinding.txtSoTien.setText(SharedPreferencesManager.getSystemLabel(131));
        
        billPaymentRequestFragmentBinding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
        billPaymentRequestFragmentBinding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
        billPaymentRequestFragmentBinding.txtDelete.setText(SharedPreferencesManager.getSystemLabel(97));
    }
    
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(billPaymentRequestFragmentBinding.lParentContent);
                        billPaymentRequestFragmentBinding.txtTitleInfo.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            billPaymentRequestFragmentBinding.txtTitleInfo.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void addEvents() {
        billPaymentRequestFragmentBinding.txtSoPhieuTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiDeNghi.getSelectedItem();
                if (loaiDeNghiItem.getiTEMCODE().equals("003")){
                    Intent intent = new Intent(getContext(), SoPhieuTamUngActivity.class);
                    intent.putExtra(Constant.NAME_PUT_DOI_TUONG_NHAN, doiTuongNhanItemSelected);
                    startActivityForResult(intent,CODE_SELECT_SO_PHIEU_TAM_UNG);
                }
            }
        });
        billPaymentRequestFragmentBinding.constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!billPaymentRequestFragmentBinding.edtInfo.getText().toString().equals("")){
                    if (listDetail.size()>0){
                        if (checkCurrency()){
                            if (signatureItemApiResponse!=null){
                                // Trình ký
                                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                    @Override
                                    public void onAccept() {
                                        doCommit();
                                    }
            
                                    @Override
                                    public void onCancel() {
                
                                    }
                                });
                            }
                            else {
                                if (doiTuongNhanItemSelected!=null){
                                       showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(56), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                        @Override
                                        public void onAccept() {
                                            doSaveAndCommit();
                                        }
        
                                        @Override
                                        public void onCancel() {
            
                                        }
                                    });
                                }
                                else {
                                    showToastError(SharedPreferencesManager.getSystemLabel(122));
                                }
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(134));
                
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(48));
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(135));
                }
            }
        });
        billPaymentRequestFragmentBinding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                    @Override
                    public void onAccept() {
                        progressdialog.show();
                        DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
                        deleteDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                        deleteDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                        ApiServices.getInstance().deleteDocument(SharedPreferencesManager.getInstance().getPrefToken(), deleteDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                if (response.isSuccessful()){
                                    if (response.body().isRETNCODE()){
                                        progressdialog.dismiss();
                                        showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(65));
                                    }
                                    else {
                                        progressdialog.dismiss();
                                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(51));
                                        System.out.println(response.body().getRETNMSSG());
                                    }
                                }
                                else {
                                    progressdialog.dismiss();
                                    showOutTOKEN();
                                    System.out.println(response.message());
                                }
                            }
    
                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                progressdialog.dismiss();
                                showOutTOKEN();
                                System.out.println(t.getMessage());
                            }
                        });
                    }
    
                    @Override
                    public void onCancel() {
        
                    }
                });
            }
        });
        billPaymentRequestFragmentBinding.constraintLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doiTuongNhanItemSelected!=null){
                    if (!billPaymentRequestFragmentBinding.edtInfo.getText().toString().equals("")){
                        if (listDetail.size()>0){
                            if (checkCurrency()){
                                if (signatureItemApiResponse==null){
                                    // Thêm mới
                                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(56), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                        @Override
                                        public void onAccept() {
                                            doSave();
                                        }
            
                                        @Override
                                        public void onCancel() {
                
                                        }
                                    });
                                }
                                else {
                                    // Cập nhật
                                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(57), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                        @Override
                                        public void onAccept() {
                                            doUpdate();
                                        }
            
                                        @Override
                                        public void onCancel() {
                
                                        }
                                    });
                                }
                            }
                            else {
                                showToastError(SharedPreferencesManager.getSystemLabel(134));
    
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(48));
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(135));
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(122));
                }
            }
        });
        detailTicketDetailAdapter.setTicketDetailClickListener(new DetailTicketDetailAdapter.TicketDetailClickListener() {
            @Override
            public void onEditClick(TicketBillPaymentDetail detail, int position) {
                Intent intent = new Intent(getContext(),TicketBillPaymentRequestActivity.class);
                ticketBillPaymentDetailEdit = listDetail.get(position);
                positionEdit = position;
                intent.putExtra(Constant.NAME_PUT_TICKET_BILL_PAYMENT,listDetail.get(position));
                startActivityForResult(intent, CODE_EDIT_DETAIL);
            }
    
            @Override
            public void onDeleteClick(TicketBillPaymentDetail detail, int position) {
                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                    @Override
                    public void onAccept() {
                        listDetail.remove(position);
                        updateHeader();
                        billPaymentRequestFragmentBinding.recycleview.post(new Runnable() {
                            @Override
                            public void run() {
                                detailTicketDetailAdapter.notifyDataSetChanged();
                            }
                        });
                    }
    
                    @Override
                    public void onCancel() {
        
                    }
                });
            }
        });
        billPaymentRequestFragmentBinding.include24.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        billPaymentRequestFragmentBinding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), TicketBillPaymentRequestActivity.class);
                startActivity(intent);
            }
        });
        billPaymentRequestFragmentBinding.txtTenDoiTuongNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiDoiTuongLienQuanItem loaiDeNghiTamUngItem = (LoaiDoiTuongLienQuanItem) billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.getSelectedItem();
                Intent intent = new Intent(getContext(), DoiTuongNhanActivity.class);
                intent.putExtra(Constant.NAME_PUT_KEY_DOI_TUONG_NHAN,loaiDeNghiTamUngItem.getiTEMCODE());
                intent.putExtra(Constant.NAME_PUT_DOI_TUONG_NHAN,doiTuongNhanItemSelected);
                startActivityForResult(intent,CODE_SELECT_DOI_TUONG_NHAN);
            }
        });
        billPaymentRequestFragmentBinding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TicketBillPaymentRequestActivity.class);
                startActivityForResult(intent,CODE_ADD_DETAIL);
            }
        });
    }
    
    private void doCommit() {
        progressdialog.show();
        BillPaymentHeader billPaymentHeader = new BillPaymentHeader();
        billPaymentHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiDeNghi.getSelectedItem();
        billPaymentHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        LoaiDeNghiItem loaiChiTieu = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiChiTieu.getSelectedItem();
        billPaymentHeader.setsCTNCODE(loaiChiTieu.getiTEMCODE());
        LoaiDoiTuongLienQuanItem loaiDoiTuongLienQuanItem = (LoaiDoiTuongLienQuanItem) billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.getSelectedItem();
        billPaymentHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongLienQuanItem.getiTEMCODE()));
        billPaymentHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE());
        billPaymentHeader.setmEXLNNTE(billPaymentRequestFragmentBinding.edtInfo.getText().toString());
        CurrencyItem currencyItem = (CurrencyItem) billPaymentRequestFragmentBinding.spinerDonViTienTe.getSelectedItem();
        billPaymentHeader.setcUOMCODE(currencyItem.getiTEMCODE());
        billPaymentHeader.setkKKK0000(signatureItemApiResponse.getKeyCode());
        if (currencyItem.getiTEMCODE().equals("VND")){
            billPaymentHeader.setcUOMRATE(1.0);
        }
        else {
            billPaymentHeader.setcUOMRATE(Double.parseDouble(billPaymentRequestFragmentBinding.edtTiGia.getText().toString()));
        }
        try {
            billPaymentHeader.setsGSTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setsUMCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setrCPTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        List<BillPaymentDetail> billPaymentDetailList = new ArrayList<>();
        for (int i =0;i<listDetail.size();i++){
            TicketBillPaymentDetail ticketBillPaymentDetail = listDetail.get(i);
            BillPaymentDetail billPaymentDetail1 = new BillPaymentDetail();
            billPaymentDetail1.setmNEYCRAM(ticketBillPaymentDetail.getNumberPrice());
            billPaymentDetail1.setrFRNDATE(ticketBillPaymentDetail.getDateBill()!=null ? simpleDateFormatSystem.format(ticketBillPaymentDetail.getDateBill()) : null);
            billPaymentDetail1.setmEXLNNTED(ticketBillPaymentDetail.getContent());
            billPaymentDetail1.setrFRNCODE(ticketBillPaymentDetail.getBillCode());
            billPaymentDetail1.setIndex(String.valueOf(i+1));
            billPaymentDetailList.add(billPaymentDetail1);
        }
        billPaymentHeader.setdETAIL(billPaymentDetailList);
        List<BillPaymentHeader>billPaymentHeaderList= new ArrayList<>();
        billPaymentHeaderList.add(billPaymentHeader);
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        billPaymentRequest.setBillPaymentHeaders(billPaymentHeaderList);
    
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(billPaymentRequest), JsonObject.class);
        ApiServices.getInstance().editBillPayment(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewPaymentResponse>() {
            @Override
            public void onResponse(Call<AddNewPaymentResponse> call, Response<AddNewPaymentResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        deleteFile(response.body().getAddNewPaymentItems().get(0).getKeyCode());
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFileBeforeCommit(response.body().getAddNewPaymentItems().get(0).getKeyCode(), response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList,
                                    new UploadFileCallback() {
                                        @Override
                                        public void onUpLoadSuccess() {
                                            CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                                            commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                            commitDocumentRequest.setKeyCode(response.body().getAddNewPaymentItems().get(0).getKeyCode());
                                            ApiServices.getInstance().commitDocument(SharedPreferencesManager.getInstance().getPrefToken(), commitDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                                                @Override
                                                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                                    if (response.isSuccessful()){
                                                        if (response.body().isRETNCODE()){
                                                            progressdialog.dismiss();
                                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(59));
                                                        }
                                                        else {
                                                            progressdialog.dismiss();
                                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(60));
                                                        }
                                                    }
                                                    else {
                                                        progressdialog.dismiss();
                                                        showOutTOKEN();
                                                    }
                                                }
        
                                                @Override
                                                public void onFailure(Call<ApiResponse> call, Throwable t) {
                                                    progressdialog.dismiss();
                                                    showOutTOKEN();
                                                    System.out.println(t.getMessage());
                                                }
                                            });
    
                                        }
    
                                        @Override
                                        public void onUploadFail(String mess) {
                                            progressdialog.dismiss();
                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                            System.out.println(mess);
                                        }
                                    });
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                            System.out.println(response.body().getRETNMSSG());
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        System.out.println(response.body().getRETNMSSG());
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                }
                else {
                    progressdialog.dismiss();
                    System.out.println(response.message());
                    showOutTOKEN();
                }
            }
        
            @Override
            public void onFailure(Call<AddNewPaymentResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    
    private void doSaveAndCommit() {
        progressdialog.show();
        BillPaymentHeader billPaymentHeader = new BillPaymentHeader();
        billPaymentHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiDeNghi.getSelectedItem();
        billPaymentHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        LoaiDeNghiItem loaiChiTieu = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiChiTieu.getSelectedItem();
        billPaymentHeader.setsCTNCODE(loaiChiTieu.getiTEMCODE());
        LoaiDoiTuongLienQuanItem loaiDoiTuongLienQuanItem = (LoaiDoiTuongLienQuanItem) billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.getSelectedItem();
        billPaymentHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongLienQuanItem.getiTEMCODE()));
        billPaymentHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE());
        billPaymentHeader.setmEXLNNTE(billPaymentRequestFragmentBinding.edtInfo.getText().toString());
        CurrencyItem currencyItem = (CurrencyItem) billPaymentRequestFragmentBinding.spinerDonViTienTe.getSelectedItem();
        billPaymentHeader.setcUOMCODE(currencyItem.getiTEMCODE());
        if (currencyItem.getiTEMCODE().equals("VND")){
            billPaymentHeader.setcUOMRATE(1.0);
        }
        else {
            billPaymentHeader.setcUOMRATE(Double.parseDouble(billPaymentRequestFragmentBinding.edtTiGia.getText().toString()));
        }
        try {
            billPaymentHeader.setsGSTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setsUMCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setrCPTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        List<BillPaymentDetail> billPaymentDetailList = new ArrayList<>();
        for (int i =0;i<listDetail.size();i++){
            TicketBillPaymentDetail ticketBillPaymentDetail = listDetail.get(i);
            BillPaymentDetail billPaymentDetail1 = new BillPaymentDetail();
            billPaymentDetail1.setmNEYCRAM(ticketBillPaymentDetail.getNumberPrice());
            billPaymentDetail1.setrFRNDATE(ticketBillPaymentDetail.getDateBill()!=null ? simpleDateFormatSystem.format(ticketBillPaymentDetail.getDateBill()) : null);
            billPaymentDetail1.setmEXLNNTED(ticketBillPaymentDetail.getContent());
            billPaymentDetail1.setrFRNCODE(ticketBillPaymentDetail.getBillCode());
            billPaymentDetail1.setIndex(String.valueOf(i+1));
            billPaymentDetailList.add(billPaymentDetail1);
        }
        billPaymentHeader.setdETAIL(billPaymentDetailList);
        List<BillPaymentHeader>billPaymentHeaderList= new ArrayList<>();
        billPaymentHeaderList.add(billPaymentHeader);
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        billPaymentRequest.setBillPaymentHeaders(billPaymentHeaderList);
    
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(billPaymentRequest), JsonObject.class);
        ApiServices.getInstance().addAndCommitBillpayment(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewPaymentResponse>() {
            @Override
            public void onResponse(Call<AddNewPaymentResponse> call, Response<AddNewPaymentResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        progressdialog.dismiss();
                        upLoadFile(response.body().getAddNewPaymentItems().get(0).getKeyCode(),response.body().getRETNMSSG(), getListImage(),FileFragment.fileIncludeList);
                    }
                    else {
                        progressdialog.dismiss();
                        System.out.println(response.body().getRETNMSSG());
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                }
                else {
                    progressdialog.dismiss();
                    System.out.println(response.message());
                    showOutTOKEN();
                }
            }
        
            @Override
            public void onFailure(Call<AddNewPaymentResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    
    private boolean checkCurrency() {
        CurrencyItem currencyItem = (CurrencyItem) billPaymentRequestFragmentBinding.spinerDonViTienTe.getSelectedItem();
        if (currencyItem.getiTEMCODE().equals("VND"))
            return true;
        else {
            if (Integer.parseInt(billPaymentRequestFragmentBinding.edtTiGia.getText().toString()) > 1){
                return true;
            }
        }
        return false;
    }
    
    private void doUpdate() {
        progressdialog.show();
        BillPaymentHeader billPaymentHeader = new BillPaymentHeader();
        billPaymentHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiDeNghi.getSelectedItem();
        billPaymentHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        LoaiDeNghiItem loaiChiTieu = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiChiTieu.getSelectedItem();
        billPaymentHeader.setsCTNCODE(loaiChiTieu.getiTEMCODE());
        LoaiDoiTuongLienQuanItem loaiDoiTuongLienQuanItem = (LoaiDoiTuongLienQuanItem) billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.getSelectedItem();
        billPaymentHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongLienQuanItem.getiTEMCODE()));
        billPaymentHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE());
        billPaymentHeader.setmEXLNNTE(billPaymentRequestFragmentBinding.edtInfo.getText().toString());
        CurrencyItem currencyItem = (CurrencyItem) billPaymentRequestFragmentBinding.spinerDonViTienTe.getSelectedItem();
        billPaymentHeader.setcUOMCODE(currencyItem.getiTEMCODE());
        billPaymentHeader.setkKKK0000(signatureItemApiResponse.getKeyCode());
        if (currencyItem.getiTEMCODE().equals("VND")){
            billPaymentHeader.setcUOMRATE(1.0);
        }
        else {
            billPaymentHeader.setcUOMRATE(Double.parseDouble(billPaymentRequestFragmentBinding.edtTiGia.getText().toString()));
        }
        try {
            billPaymentHeader.setsGSTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setsUMCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienChi.getText().toString().replace(".","").trim()));
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setrCPTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        List<BillPaymentDetail> billPaymentDetailList = new ArrayList<>();
        for (int i =0;i<listDetail.size();i++){
            TicketBillPaymentDetail ticketBillPaymentDetail = listDetail.get(i);
            BillPaymentDetail billPaymentDetail1 = new BillPaymentDetail();
            billPaymentDetail1.setmNEYCRAM(ticketBillPaymentDetail.getNumberPrice());
            billPaymentDetail1.setrFRNDATE(ticketBillPaymentDetail.getDateBill()!=null ? simpleDateFormatSystem.format(ticketBillPaymentDetail.getDateBill()) : null);
            billPaymentDetail1.setmEXLNNTED(ticketBillPaymentDetail.getContent());
            billPaymentDetail1.setrFRNCODE(ticketBillPaymentDetail.getBillCode());
            billPaymentDetail1.setIndex(String.valueOf(i+1));
            billPaymentDetailList.add(billPaymentDetail1);
        }
        billPaymentHeader.setdETAIL(billPaymentDetailList);
        List<BillPaymentHeader>billPaymentHeaderList= new ArrayList<>();
        billPaymentHeaderList.add(billPaymentHeader);
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        billPaymentRequest.setBillPaymentHeaders(billPaymentHeaderList);
    
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(billPaymentRequest), JsonObject.class);
        ApiServices.getInstance().editBillPayment(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewPaymentResponse>() {
            @Override
            public void onResponse(Call<AddNewPaymentResponse> call, Response<AddNewPaymentResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        deleteFile(response.body().getAddNewPaymentItems().get(0).getKeyCode());
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFile(response.body().getAddNewPaymentItems().get(0).getKeyCode(), response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                            System.out.println(response.body().getRETNMSSG());
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        System.out.println(response.body().getRETNMSSG());
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                }
                else {
                    progressdialog.dismiss();
                    System.out.println(response.message());
                    showOutTOKEN();
                }
            }
        
            @Override
            public void onFailure(Call<AddNewPaymentResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    
    private void doSave() {
        progressdialog.show();
        BillPaymentHeader billPaymentHeader = new BillPaymentHeader();
        billPaymentHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiDeNghi.getSelectedItem();
        billPaymentHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        LoaiDeNghiItem loaiChiTieu = (LoaiDeNghiItem) billPaymentRequestFragmentBinding.spinerLoaiChiTieu.getSelectedItem();
        billPaymentHeader.setsCTNCODE(loaiChiTieu.getiTEMCODE());
        LoaiDoiTuongLienQuanItem loaiDoiTuongLienQuanItem = (LoaiDoiTuongLienQuanItem) billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.getSelectedItem();
        billPaymentHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongLienQuanItem.getiTEMCODE()));
        billPaymentHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE());
        billPaymentHeader.setmEXLNNTE(billPaymentRequestFragmentBinding.edtInfo.getText().toString());
        CurrencyItem currencyItem = (CurrencyItem) billPaymentRequestFragmentBinding.spinerDonViTienTe.getSelectedItem();
        billPaymentHeader.setcUOMCODE(currencyItem.getiTEMCODE());
        if (currencyItem.getiTEMCODE().equals("VND")){
            billPaymentHeader.setcUOMRATE(1.0);
        }
        else {
            billPaymentHeader.setcUOMRATE(Double.parseDouble(billPaymentRequestFragmentBinding.edtTiGia.getText().toString()));
        }
        try {
            billPaymentHeader.setsGSTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.getText().toString().replace(".","").trim()));
    
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setsUMCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienChi.getText().toString().replace(".","").trim()));
    
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            billPaymentHeader.setrCPTCRAM(Double.parseDouble(billPaymentRequestFragmentBinding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        List<BillPaymentDetail> billPaymentDetailList = new ArrayList<>();
        for (int i =0;i<listDetail.size();i++){
            TicketBillPaymentDetail ticketBillPaymentDetail = listDetail.get(i);
            BillPaymentDetail billPaymentDetail1 = new BillPaymentDetail();
            billPaymentDetail1.setmNEYCRAM(ticketBillPaymentDetail.getNumberPrice());
            billPaymentDetail1.setrFRNDATE(ticketBillPaymentDetail.getDateBill()!=null ? simpleDateFormatSystem.format(ticketBillPaymentDetail.getDateBill()) : null);
            billPaymentDetail1.setmEXLNNTED(ticketBillPaymentDetail.getContent());
            billPaymentDetail1.setrFRNCODE(ticketBillPaymentDetail.getBillCode());
            billPaymentDetail1.setIndex(String.valueOf(i+1));
            billPaymentDetailList.add(billPaymentDetail1);
        }
        billPaymentHeader.setdETAIL(billPaymentDetailList);
        List<BillPaymentHeader>billPaymentHeaderList= new ArrayList<>();
        billPaymentHeaderList.add(billPaymentHeader);
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        billPaymentRequest.setBillPaymentHeaders(billPaymentHeaderList);
    
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(billPaymentRequest), JsonObject.class);
        ApiServices.getInstance().addNewBillPayment(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewPaymentResponse>() {
            @Override
            public void onResponse(Call<AddNewPaymentResponse> call, Response<AddNewPaymentResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        progressdialog.dismiss();
                       upLoadFile(response.body().getAddNewPaymentItems().get(0).getKeyCode(),response.body().getRETNMSSG(), getListImage(),FileFragment.fileIncludeList);
                    }
                    else {
                        progressdialog.dismiss();
                        System.out.println(response.body().getRETNMSSG());
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                }
                else {
                    progressdialog.dismiss();
                    System.out.println(response.message());
                    showOutTOKEN();
                }
            }
    
            @Override
            public void onFailure(Call<AddNewPaymentResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    private List<String> getListImage() {
        List<String> listReturn= new ArrayList<>();
        for (String s : listFilePath){
            Bitmap bitmap = BitmapFactory.decodeFile(s);
            GetFileHelper.getInstance().saveImageToLocalApp(getContext(), bitmap, new SaveFileToLocalCallback() {
                @Override
                public void onSaveSuccess(String path) {
                    listReturn.add(path);
                }
                
                @Override
                public void onSaveFail(String mess) {
                    System.out.println(mess);
                }
            });
        }
        listFilePath.clear();
        return listReturn;
    }
    private void upLoadFile(String keyCode, String mess, List<String> listImage, List<FileIncludeModel> listFile) {
        if (listImage.size()>0 || checkFileInclude(listFile)){
            ApiServices.getInstance().uploadFile(SharedPreferencesManager.getInstance().getPrefToken(), "PHDNC", keyCode,
                    listImage,listFile, new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    progressdialog.dismiss();
                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),mess);
                                    System.out.println(response.body().getRETNMSSG());
                                    FileFragment.fileIncludeList.clear();
                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                                    System.out.println(response.body().getRETNMSSG());
                                }
                            }
                            else {
                                progressdialog.dismiss();
                                System.out.println(response.message());
                                showOutTOKEN();
                            }
                            
                        }
                        
                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            progressdialog.dismiss();
                            System.out.println(t.getMessage());
                            showOutTOKEN();
                        }
                    });
        }
        else {
            progressdialog.dismiss();
            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),mess);
            FileFragment.fileIncludeList.clear();
        }
    }
    private void upLoadFileBeforeCommit(String keyCode, String mess, List<String> listImage, List<FileIncludeModel> listFile, UploadFileCallback uploadFileCallback) {
        if (listImage.size()>0 || checkFileInclude(listFile)){
            ApiServices.getInstance().uploadFile(SharedPreferencesManager.getInstance().getPrefToken(), "PHDNC", keyCode,
                    listImage,listFile, new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    System.out.println(response.body().getRETNMSSG());
                                    FileFragment.fileIncludeList.clear();
                                    try {
                                        File dir = getContext().getDir("profile", Context.MODE_PRIVATE);
                                        if (dir.exists()) {
                                            for (File f : dir.listFiles()){
                                                System.out.println(f.getAbsolutePath());
                                                f.delete();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                    uploadFileCallback.onUpLoadSuccess();
                                }
                                else {
                                    System.out.println(response.body().getRETNMSSG());
                                    uploadFileCallback.onUploadFail(response.body().getRETNMSSG());
                                }
                            }
                            else {
                                uploadFileCallback.onUploadFail(response.message());
                            }
                            
                        }
                        
                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            progressdialog.dismiss();
                            System.out.println(t.getMessage());
                            showOutTOKEN();
                        }
                    });
        }
        else {
            progressdialog.dismiss();
            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),mess);
            FileFragment.fileIncludeList.clear();
        }
    }
    private boolean checkFileInclude(List<FileIncludeModel> listFile) {
        if (listFile.size()>0){
            for (FileIncludeModel fileIncludeModel : listFile){
                if (fileIncludeModel.getLoadType() == 2){
                    return true;
                }
            }
            return false;
        }
        else
            return false;
    }
    private void deleteFile(String keyCode) {
        if (FileFragment.imageModelListRemove.size() > 0) {
            for (ImageModel imageModel : FileFragment.imageModelListRemove) {
                ApiServices.getInstance().deleteAttactment(SharedPreferencesManager.getInstance().getPrefToken(),
                        signatureItemApiResponse.getDcmnCode(), keyCode, imageModel.getFileCode(), new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                System.out.println(response.body().getRETNMSSG());
                                System.out.println(response.message());
                            }
                            
                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        });
            }
            FileFragment.imageModelListRemove.clear();
        }
    }
    
    private void updateHeader() {
        if (listDetail.size()>0){
            double sumMoney = 0;
            for (TicketBillPaymentDetail dt : listDetail){
                sumMoney+=dt.getNumberPrice();
            }
            billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.setText(dfnd.format((int) sumMoney));
            billPaymentRequestFragmentBinding.edtSoTienChi.setText(dfnd.format((int) sumMoney));
        }
        else {
            billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.setText(dfnd.format((int) 0.0));
            billPaymentRequestFragmentBinding.edtSoTienChi.setText(dfnd.format((int) 0.0));
        }
    }
    
    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62),SharedPreferencesManager.getSystemLabel(63));
        showLoadingNonMessDialog();
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        
        txtTitle=billPaymentRequestFragmentBinding.include24.findViewById(R.id.txtTitle);
        currencyItemList= new ArrayList<>();
        adapterCurrency = new ArrayAdapter(getContext(),R.layout.spiner_item, currencyItemList);
        adapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billPaymentRequestFragmentBinding.spinerDonViTienTe.setAdapter(adapterCurrency);
        
        loaiDeNghiItemList = new ArrayList<>();
        adapterLoaiDeNghi = new ArrayAdapter(getContext(),R.layout.spiner_item, loaiDeNghiItemList);
        adapterLoaiDeNghi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billPaymentRequestFragmentBinding.spinerLoaiDeNghi.setAdapter(adapterLoaiDeNghi);
    
        loaiDoiTuongList= new ArrayList<>();
        adapterLoaiDoiTuong = new ArrayAdapter(getContext(), R.layout.spiner_item, loaiDoiTuongList);
        adapterLoaiDoiTuong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.setAdapter(adapterLoaiDoiTuong);
    
        loaiChiTieuItemList= new ArrayList<>();
        adapterLoaiChiTieu= new ArrayAdapter(getContext(),R.layout.spiner_item, loaiChiTieuItemList);
        adapterLoaiChiTieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billPaymentRequestFragmentBinding.spinerLoaiChiTieu.setAdapter(adapterLoaiChiTieu);
    
        listDetail = new ArrayList<>();
        detailTicketDetailAdapter = new DetailTicketDetailAdapter(listDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        billPaymentRequestFragmentBinding.recycleview.setLayoutManager(linearLayoutManager);
        billPaymentRequestFragmentBinding.recycleview.setAdapter(detailTicketDetailAdapter);
        billPaymentRequestFragmentBinding.recycleview.addItemDecoration(dividerItemDecoration);
        
        billPaymentRequestFragmentBinding.setIsEditable(true);
    
        mainDate = new Date(System.currentTimeMillis());
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BillPaymentRequestViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        if (signatureItemApiResponse != null) {
            mViewModel.loadBillPayment(signatureItemApiResponse.getDcmnCode(),signatureItemApiResponse.getKeyCode());
        }
        else {
            setAminHeader();
        }
        mViewModel.loadData(new LoadDataAsynCallback() {
            @Override
            public void onLoadSuccess() {
                mViewModel.getLiveDataCurrencyList().observe(getViewLifecycleOwner(), new Observer<List<CurrencyItem>>() {
                @Override
                public void onChanged(List<CurrencyItem> currencyItems) {
                    currencyItemList.clear();
                    currencyItemList.addAll(currencyItems);
                    adapterCurrency.notifyDataSetChanged();
                }
            });
                mViewModel.getLiveDataLoaiDeNghi().observe(getViewLifecycleOwner(), new Observer<List<LoaiDeNghiItem>>() {
                    @Override
                    public void onChanged(List<LoaiDeNghiItem> loaiDeNghiItems) {
                        loaiDeNghiItemList.clear();
                        loaiDeNghiItemList.addAll(loaiDeNghiItems);
                        adapterLoaiDeNghi.notifyDataSetChanged();
                    }
                });
                mViewModel.getLiveDataLoaiDoiTuongLienQuan().observe(getViewLifecycleOwner(), new Observer<List<LoaiDoiTuongLienQuanItem>>() {
                    @Override
                    public void onChanged(List<LoaiDoiTuongLienQuanItem> loaiDoiTuongLienQuanItems) {
                        loaiDoiTuongList.clear();
                        loaiDoiTuongList.addAll(loaiDoiTuongLienQuanItems);
                        adapterLoaiDoiTuong.notifyDataSetChanged();
                    }
                });
                mViewModel.getLiveDataLoaichiTieu().observe(getViewLifecycleOwner(), new Observer<List<LoaiDeNghiItem>>() {
                    @Override
                    public void onChanged(List<LoaiDeNghiItem> loaiDeNghiItems) {
                        loaiChiTieuItemList.addAll(loaiDeNghiItems);
                        adapterLoaiChiTieu.notifyDataSetChanged();
                    }
                });
                if (signatureItemApiResponse!=null){
                    mViewModel.getLiveDataBillPaymentResponse().observe(getViewLifecycleOwner(), new Observer<BillPaymentApiResponse>() {
                        @Override
                        public void onChanged(BillPaymentApiResponse billPaymentApiResponse) {
                            BillPaymentHeader billPaymentHeader = billPaymentApiResponse.getBillPaymentHeaders().get(0);
                            accessRole=checkAccessRole(billPaymentHeader.getsTTESIGN(),billPaymentHeader.getaCCERGHT(),
                                    billPaymentRequestFragmentBinding.constraintLayout8,
                                    billPaymentRequestFragmentBinding.constraintLayout9,
                                    billPaymentRequestFragmentBinding.llDelete,
                                    billPaymentRequestFragmentBinding.imgAdd);
                            detailTicketDetailAdapter.setEditable(accessRole.isEdit());
                            billPaymentRequestFragmentBinding.setIsEditable(accessRole.isEdit());
                            
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_progress_switch_shift,
                                    new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(),signatureItemApiResponse.getKeyCode()))
                                    .commit();
                            billPaymentRequestFragmentBinding.edtSoTienChi.setText(dfnd.format((int) billPaymentHeader.getsUMCRAM()));
                            billPaymentRequestFragmentBinding.edtSoTiendeNghiChi.setText(dfnd.format((int) billPaymentHeader.getsGSTCRAM()));
                            billPaymentRequestFragmentBinding.edtInfo.setText(billPaymentHeader.getmEXLNNTE());
                            
                            billPaymentRequestFragmentBinding.txtTenDoiTuongNhan.setText(billPaymentHeader.getoBJCNAME());
                            doiTuongNhanItemSelected = new DoiTuongNhanItem();
                            doiTuongNhanItemSelected.setiTEMNAME(billPaymentHeader.getoBJCNAME());
                            doiTuongNhanItemSelected.setiTEMCODE(billPaymentHeader.getoBJCCODE());
                            
                            for (int i=0;i<loaiDeNghiItemList.size();i++){
                                LoaiDeNghiItem loaiDeNghiItem = loaiDeNghiItemList.get(i);
                                if (loaiDeNghiItem.getiTEMCODE().equals(billPaymentHeader.getdCMNSBCD())){
                                    billPaymentRequestFragmentBinding.spinerLoaiDeNghi.setSelection(i);
                                    break;
                                }
                            }
                            for (int i=0;i<loaiChiTieuItemList.size();i++){
                                LoaiDeNghiItem loaiDeNghiItem = loaiDeNghiItemList.get(i);
                                if (loaiDeNghiItem.getiTEMCODE().equals(billPaymentHeader.getsCTNCODE())){
                                    billPaymentRequestFragmentBinding.spinerLoaiChiTieu.setSelection(i);
                                    break;
                                }
                            }
                            for (int i = 0;i<loaiDoiTuongList.size();i++){
                                LoaiDoiTuongLienQuanItem loaiDoiTuongNhanItem = loaiDoiTuongList.get(i);
                                if (loaiDoiTuongNhanItem.getiTEMCODE().equals(String.valueOf(billPaymentHeader.getoBJCTYPE()))){
                                    billPaymentRequestFragmentBinding.spinerLoaiDoiTuongNhan.setSelection(i);
                                    break;
                                }
                            }
                            for (int i = 0 ;i< currencyItemList.size();i++){
                                CurrencyItem currencyItem = currencyItemList.get(i);
                                if (currencyItem.getiTEMCODE().equals(billPaymentHeader.getcUOMCODE())){
                                    billPaymentRequestFragmentBinding.spinerDonViTienTe.setSelection(i);
                                    break;
                                }
                            }
                            if (billPaymentHeader.getcUOMCODE().equals("VND")){
                                billPaymentRequestFragmentBinding.edtTiGia.setText("1");
                            }
                            else {
                                billPaymentRequestFragmentBinding.edtTiGia.setText(String.valueOf((int) billPaymentHeader.getcUOMRATE()));
                            }
                            
                            billPaymentRequestFragmentBinding.edtNgayTamUng.setText(Util.formatDate(billPaymentHeader.getaDVNDATE()));
                            
                            if (billPaymentHeader.getdETAIL()!=null){
                                if (billPaymentHeader.getdETAIL().size()>0){
                                   if (billPaymentHeader.getdETAIL().get(0).getmEXLNNTED()!=null){
                                       for (BillPaymentDetail billPaymentDetail : billPaymentHeader.getdETAIL()){
                                           TicketBillPaymentDetail ticket = new TicketBillPaymentDetail();
                                           ticket.setContent(billPaymentDetail.getmEXLNNTED());
                                           ticket.setNumberPrice(billPaymentDetail.getmNEYCRAM());
                                           ticket.setBillCode(billPaymentDetail.getrFRNCODE());
                                           ticket.setDateBill(Util.convertStringToDate(Util.formatDate(billPaymentDetail.getrFRNDATE()),"dd-MM-yyyy"));
        
                                           listDetail.add(ticket);
                                           detailTicketDetailAdapter.notifyDataSetChanged();
                                       }
                                   }
                                }
                            }
                            if (billPaymentHeader.getsTTESIGN() == 0) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_file_include, new FileFragment(billPaymentHeader.getdCMNFILE())).commit();
                            }
                            loadingNonMessDialog.dismiss();
                            setAminHeader();
                        }
                    });
                }
                else {
                    setAminHeader();
                    loadingNonMessDialog.dismiss();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_file_include, new FileFragment(new ArrayList<>())).commit();
                }
            }
        });
        
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE_SELECT_DOI_TUONG_NHAN && resultCode == Activity.RESULT_OK){
            DoiTuongNhanItem nhanItem = (DoiTuongNhanItem) data.getSerializableExtra(Constant.NAME_PUT_DOI_TUONG_NHAN);
            if (nhanItem!=null){
                doiTuongNhanItemSelected=new DoiTuongNhanItem();
                doiTuongNhanItemSelected.setiTEMCODE(nhanItem.getiTEMCODE());
                doiTuongNhanItemSelected.setiTEMNAME(nhanItem.getiTEMNAME());
                billPaymentRequestFragmentBinding.txtTenDoiTuongNhan.setText(doiTuongNhanItemSelected.getiTEMNAME());
            }
        }
        if (requestCode == CODE_ADD_DETAIL && resultCode == Activity.RESULT_OK){
            TicketBillPaymentDetail ticketBillPaymentDetail = (TicketBillPaymentDetail) data.getSerializableExtra(Constant.NAME_PUT_TICKET_BILL_PAYMENT);
            if (ticketBillPaymentDetail!=null){
                listDetail.add(ticketBillPaymentDetail);
                detailTicketDetailAdapter.notifyDataSetChanged();
                updateHeader();
            }
        }
        if (requestCode == CODE_EDIT_DETAIL && resultCode == Activity.RESULT_OK){
            TicketBillPaymentDetail ticketBillPaymentDetail = (TicketBillPaymentDetail) data.getSerializableExtra(Constant.NAME_PUT_TICKET_BILL_PAYMENT);
            if (ticketBillPaymentDetail!=null){
                listDetail.remove(positionEdit);
                listDetail.add(ticketBillPaymentDetail);
                detailTicketDetailAdapter.notifyDataSetChanged();
                updateHeader();
            }
        }
    }
}