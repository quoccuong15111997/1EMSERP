package com.firstems.erp.ui.signature.advanceproposalform;

import android.app.Activity;
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
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.request.advance_proposal_form.AdvanceProposalFormRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalAddNewResponse;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormApiResponse;
import com.firstems.erp.api.model.response.advance_proposal_form.AdvanceProposalFormHeader;
import com.firstems.erp.api.model.response.currency.CurrencyItem;
import com.firstems.erp.api.model.response.doi_tuong_nhan.DoiTuongNhanItem;
import com.firstems.erp.api.model.response.loai_de_nghi.LoaiDeNghiItem;
import com.firstems.erp.api.model.response.loai_de_nghi_tam_ung.LoaiDoiTuongNhanItem;
import com.firstems.erp.api.model.response.project_list.ProjectListItem;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.AdvanceProposalFormCallback;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.SaveFileToLocalCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.UploadFileCallback;
import com.firstems.erp.callback.runcode.LoadDataAsynCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.AdvanceProposalLayoutBinding;
import com.firstems.erp.helper.NumberTextWatcher;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.file.GetFileHelper;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.file.FileFragment;
import com.firstems.erp.ui.shared.loaidoituongnhan.DoiTuongNhanActivity;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.firstems.erp.ui.shared.file.FileFragment.listFilePath;

public class AdvanceProposalFormFragment extends CommonFragment {
    
    private AdvanceProposalFormViewModel mViewModel;
    private AdvanceProposalLayoutBinding binding;
    private TextView txtTitle;
    private List<CurrencyItem> currencyItemList;
    private ArrayAdapter adapterCurrency, adapterloaiDoiTuongNhan;
    private List<LoaiDoiTuongNhanItem> loaiDoiTuongNhanItemList;
    private List<ProjectListItem> projectListItemList;
    private ArrayAdapter projectListAdapter;
    private List<LoaiDeNghiItem> loaiDeNghiItemList;
    private ArrayAdapter adapterLoaiDeNghi;
    private int CODE_SELECT_DOI_TUONG_NHAN = 1111 ;
    private DoiTuongNhanItem doiTuongNhanItemSelected;
    private SignatureItemApiResponse signatureItemApiResponse;
    private Date dateCreate, dateDeNghiChi;
    private AccessRole accessRole;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.advance_proposal_layout, container, false);
        
        setAminHeader();
        initText();
        addcControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleNgayTamUng.setText(SharedPreferencesManager.getSystemLabel(107));
        binding.txtTitleLoaiDeNghi.setText(SharedPreferencesManager.getSystemLabel(108));
        binding.txtTitleLoaiDoiTuongNhan.setText(SharedPreferencesManager.getSystemLabel(109));
        binding.txtTitleTenDoiTuongNhan.setText(SharedPreferencesManager.getSystemLabel(110));
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(111));
        binding.txtTiteDonViTienTe.setText(SharedPreferencesManager.getSystemLabel(112));
        binding.txtTitleTiGia.setText(SharedPreferencesManager.getSystemLabel(113));
        binding.txtTitleSoTienTamUng.setText(SharedPreferencesManager.getSystemLabel(114));
        binding.txtTitleSoTienDuocDuyet.setText(SharedPreferencesManager.getSystemLabel(115));
        binding.txtTitleMaDuAn.setText(SharedPreferencesManager.getSystemLabel(116));
        binding.txtTitleNgayThanhToan.setText(SharedPreferencesManager.getSystemLabel(117));
        
        binding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
        binding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
        binding.txtDelete.setText(SharedPreferencesManager.getSystemLabel(97));
    }
    
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.txtTitleNgayTamUng.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.txtTitleNgayTamUng.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void addEvents() {
        binding.constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signatureItemApiResponse!=null){
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
                    if (doiTuongNhanItemSelected !=null){
                        if (!binding.edtInfo.getText().toString().equals("")){
                            if (!binding.edtTiGia.getText().toString().equals("")){
                                if (!binding.edtSoTienTamUng.getText().toString().equals("")){
                                    if (signatureItemApiResponse==null){
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
                                    showToastError(SharedPreferencesManager.getSystemLabel(119));
                                }
                            }
                            else {
                                showToastError(SharedPreferencesManager.getSystemLabel(120));
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(121));
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(122));
                    }
                }
            }
        });
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                    @Override
                    public void onAccept() {
                        progressdialog.show();
                        DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
                        deleteDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                        deleteDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                        ApiServices.getInstance().deleteDocument(SharedPreferencesManager.getInstance().getPrefToken(), deleteDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                if (response.isSuccessful()){
                                    if (response.body().isRETNCODE()){
                                        progressdialog.dismiss();
                                        showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(52));
                                    }
                                    else {
                                        progressdialog.dismiss();
                                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(51));
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
                            }
                        });
                    }
                    
                    @Override
                    public void onCancel() {
                    
                    }
                });
            }
        });
        binding.edtSoTienTamUng.addTextChangedListener(new NumberTextWatcher(binding.edtSoTienTamUng, binding.edtSoTienDuocDuyet));
        binding.edtSoTienTamUng.addTextChangedListener(new NumberTextWatcher(binding.edtSoTienDuocDuyet, null));
        binding.constraintLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doiTuongNhanItemSelected !=null){
                    if (!binding.edtInfo.getText().toString().equals("")){
                        if (!binding.edtTiGia.getText().toString().equals("")){
                            if (!binding.edtSoTienTamUng.getText().toString().equals("")){
                                if (signatureItemApiResponse==null){
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
                                showToastError(SharedPreferencesManager.getSystemLabel(119));
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(120));
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(121));
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(122));
                }
            }
        });
        binding.include7.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        binding.txtTenDoiTuongNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiDoiTuongNhanItem loaiDeNghiTamUngItem = (LoaiDoiTuongNhanItem) binding.spinerLoaiDoiTuongNhan.getSelectedItem();
                Intent intent = new Intent(getContext(), DoiTuongNhanActivity.class);
                intent.putExtra(Constant.NAME_PUT_KEY_DOI_TUONG_NHAN,loaiDeNghiTamUngItem.getiTEMCODE());
                intent.putExtra(Constant.NAME_PUT_DOI_TUONG_NHAN,doiTuongNhanItemSelected);
                startActivityForResult(intent,CODE_SELECT_DOI_TUONG_NHAN);
            }
        });
        binding.layoutDateDeNghiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = System.currentTimeMillis();
                long maxDate = 0;
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateDeNghiChi = date;
                        binding.txtNgayThanhToan.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
    }
    
    private void doCommit() {
        progressdialog.show();
        AdvanceProposalFormHeader advanceProposalFormHeader = new AdvanceProposalFormHeader();
        advanceProposalFormHeader.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        advanceProposalFormHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        advanceProposalFormHeader.setpERDDATE(simpleDateFormatSystem.format(dateDeNghiChi));
        CurrencyItem currencyItem = (CurrencyItem) binding.spinerDonViTienTe.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(currencyItem.getiTEMCODE());
        LoaiDoiTuongNhanItem loaiDoiTuongNhanItem = (LoaiDoiTuongNhanItem) binding.spinerLoaiDoiTuongNhan.getSelectedItem();
        advanceProposalFormHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongNhanItem.getiTEMCODE()));
        advanceProposalFormHeader.setcUOMRATE(Double.parseDouble(binding.edtTiGia.getText().toString()));
        advanceProposalFormHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE().split("\\*")[0]);
        advanceProposalFormHeader.setaDVNCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        advanceProposalFormHeader.setaCPTCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        ProjectListItem projectListItem = (ProjectListItem) binding.spinerMaDuAn.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(projectListItem.getiTEMCODE());
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) binding.spinerLoaiDeNghiTamUng.getSelectedItem();
        advanceProposalFormHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        
        advanceProposalFormHeader.setkKKK0000(signatureItemApiResponse.getKeyCode());
        
        List<AdvanceProposalFormHeader>advanceProposalFormHeaders = new ArrayList<>();
        advanceProposalFormHeaders.add(advanceProposalFormHeader);
        
        AdvanceProposalFormRequest advanceProposalFormRequest = new AdvanceProposalFormRequest();
        advanceProposalFormRequest.setAdvanceProposalFormHeaders(advanceProposalFormHeaders);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(advanceProposalFormRequest), JsonObject.class);
        
        ApiServices.getInstance().editAdvanceProposal(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AdvanceProposalAddNewResponse>() {
            @Override
            public void onResponse(Call<AdvanceProposalAddNewResponse> call, Response<AdvanceProposalAddNewResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        deleteFile(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode());
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFileBeforeCommit(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode(), response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList,
                                    new UploadFileCallback() {
                                        @Override
                                        public void onUpLoadSuccess() {
                                            CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                                            commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                            commitDocumentRequest.setKeyCode(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode());
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
                                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50), SharedPreferencesManager.getSystemLabel(60));
                                                            System.out.println("Advance Proposal " + response.body().getRETNMSSG());
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
                                                }
                                            });
                                        }
                                        
                                        @Override
                                        public void onUploadFail(String mess) {
                                            progressdialog.dismiss();
                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),mess);
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
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
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
            public void onFailure(Call<AdvanceProposalAddNewResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    
    private void doSaveAndCommit() {
        progressdialog.show();
        AdvanceProposalFormHeader advanceProposalFormHeader = new AdvanceProposalFormHeader();
        advanceProposalFormHeader.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        advanceProposalFormHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        advanceProposalFormHeader.setpERDDATE(simpleDateFormatSystem.format(dateDeNghiChi));
        CurrencyItem currencyItem = (CurrencyItem) binding.spinerDonViTienTe.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(currencyItem.getiTEMCODE());
        LoaiDoiTuongNhanItem loaiDoiTuongNhanItem = (LoaiDoiTuongNhanItem) binding.spinerLoaiDoiTuongNhan.getSelectedItem();
        advanceProposalFormHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongNhanItem.getiTEMCODE()));
        advanceProposalFormHeader.setcUOMRATE(Double.parseDouble(binding.edtTiGia.getText().toString()));
        advanceProposalFormHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE().split("\\*")[0]);
        advanceProposalFormHeader.setaDVNCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        advanceProposalFormHeader.setaCPTCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        ProjectListItem projectListItem = (ProjectListItem) binding.spinerMaDuAn.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(projectListItem.getiTEMCODE());
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) binding.spinerLoaiDeNghiTamUng.getSelectedItem();
        advanceProposalFormHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        
        List<AdvanceProposalFormHeader>advanceProposalFormHeaders = new ArrayList<>();
        advanceProposalFormHeaders.add(advanceProposalFormHeader);
        
        AdvanceProposalFormRequest advanceProposalFormRequest = new AdvanceProposalFormRequest();
        advanceProposalFormRequest.setAdvanceProposalFormHeaders(advanceProposalFormHeaders);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(advanceProposalFormRequest), JsonObject.class);
        
        ApiServices.getInstance().addAndCommitAdvanceProposal(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AdvanceProposalAddNewResponse>() {
            @Override
            public void onResponse(Call<AdvanceProposalAddNewResponse> call, Response<AdvanceProposalAddNewResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        if (listFilePath.size()>0){
                            upLoadFile(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode(),response.body().getRETNMSSG(), getListImage(),FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
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
            public void onFailure(Call<AdvanceProposalAddNewResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
        
    }
    
    private void doUpdate() {
        progressdialog.show();
        AdvanceProposalFormHeader advanceProposalFormHeader = new AdvanceProposalFormHeader();
        advanceProposalFormHeader.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        advanceProposalFormHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        advanceProposalFormHeader.setpERDDATE(simpleDateFormatSystem.format(dateDeNghiChi));
        CurrencyItem currencyItem = (CurrencyItem) binding.spinerDonViTienTe.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(currencyItem.getiTEMCODE());
        LoaiDoiTuongNhanItem loaiDoiTuongNhanItem = (LoaiDoiTuongNhanItem) binding.spinerLoaiDoiTuongNhan.getSelectedItem();
        advanceProposalFormHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongNhanItem.getiTEMCODE()));
        advanceProposalFormHeader.setcUOMRATE(Double.parseDouble(binding.edtTiGia.getText().toString()));
        advanceProposalFormHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE().split("\\*")[0]);
        advanceProposalFormHeader.setaDVNCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        advanceProposalFormHeader.setaCPTCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        ProjectListItem projectListItem = (ProjectListItem) binding.spinerMaDuAn.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(projectListItem.getiTEMCODE());
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) binding.spinerLoaiDeNghiTamUng.getSelectedItem();
        advanceProposalFormHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        
        advanceProposalFormHeader.setkKKK0000(signatureItemApiResponse.getKeyCode());
        
        List<AdvanceProposalFormHeader>advanceProposalFormHeaders = new ArrayList<>();
        advanceProposalFormHeaders.add(advanceProposalFormHeader);
        
        AdvanceProposalFormRequest advanceProposalFormRequest = new AdvanceProposalFormRequest();
        advanceProposalFormRequest.setAdvanceProposalFormHeaders(advanceProposalFormHeaders);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(advanceProposalFormRequest), JsonObject.class);
        
        ApiServices.getInstance().editAdvanceProposal(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AdvanceProposalAddNewResponse>() {
            @Override
            public void onResponse(Call<AdvanceProposalAddNewResponse> call, Response<AdvanceProposalAddNewResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        deleteFile(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode());
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFile(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode(), response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                            System.out.println(response.body().getRETNMSSG());
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
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
            public void onFailure(Call<AdvanceProposalAddNewResponse> call, Throwable t) {
                progressdialog.dismiss();
                System.out.println(t.getMessage());
                showOutTOKEN();
            }
        });
    }
    
    private void doSave() {
        progressdialog.show();
        AdvanceProposalFormHeader advanceProposalFormHeader = new AdvanceProposalFormHeader();
        advanceProposalFormHeader.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        advanceProposalFormHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        advanceProposalFormHeader.setpERDDATE(simpleDateFormatSystem.format(dateDeNghiChi));
        CurrencyItem currencyItem = (CurrencyItem) binding.spinerDonViTienTe.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(currencyItem.getiTEMCODE());
        LoaiDoiTuongNhanItem loaiDoiTuongNhanItem = (LoaiDoiTuongNhanItem) binding.spinerLoaiDoiTuongNhan.getSelectedItem();
        advanceProposalFormHeader.setoBJCTYPE(Integer.parseInt(loaiDoiTuongNhanItem.getiTEMCODE()));
        advanceProposalFormHeader.setcUOMRATE(Double.parseDouble(binding.edtTiGia.getText().toString()));
        advanceProposalFormHeader.setoBJCCODE(doiTuongNhanItemSelected.getiTEMCODE().split("\\*")[0]);
        advanceProposalFormHeader.setaDVNCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        advanceProposalFormHeader.setaCPTCRAM(Double.parseDouble(binding.edtSoTienTamUng.getText().toString().replace(".","").trim()));
        ProjectListItem projectListItem = (ProjectListItem) binding.spinerMaDuAn.getSelectedItem();
        advanceProposalFormHeader.setaCOBCODE(projectListItem.getiTEMCODE());
        LoaiDeNghiItem loaiDeNghiItem = (LoaiDeNghiItem) binding.spinerLoaiDeNghiTamUng.getSelectedItem();
        advanceProposalFormHeader.setdCMNSBCD(loaiDeNghiItem.getiTEMCODE());
        
        List<AdvanceProposalFormHeader>advanceProposalFormHeaders = new ArrayList<>();
        advanceProposalFormHeaders.add(advanceProposalFormHeader);
        
        AdvanceProposalFormRequest advanceProposalFormRequest = new AdvanceProposalFormRequest();
        advanceProposalFormRequest.setAdvanceProposalFormHeaders(advanceProposalFormHeaders);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(advanceProposalFormRequest), JsonObject.class);
        
        //Check
        System.out.println(loaiDoiTuongNhanItem.getiTEMNAME()+"->"+loaiDoiTuongNhanItem.getiTEMCODE());
        System.out.println(doiTuongNhanItemSelected.getiTEMNAME()+"=>"+doiTuongNhanItemSelected.getiTEMCODE());
        
        ApiServices.getInstance().addNewAdvanceProposal(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AdvanceProposalAddNewResponse>() {
            @Override
            public void onResponse(Call<AdvanceProposalAddNewResponse> call, Response<AdvanceProposalAddNewResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        if (listFilePath.size()>0){
                            upLoadFile(response.body().getAdvanceProposalAddNewResponseItem().get(0).getKeyCode(),response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
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
            public void onFailure(Call<AdvanceProposalAddNewResponse> call, Throwable t) {
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
    private void upLoadFile(String keyCode, String mess, List<String> listImage, List<FileIncludeModel> listFile) {
        if (listImage.size()>0 || checkFileInclude(listFile)){
            ApiServices.getInstance().uploadFile(SharedPreferencesManager.getInstance().getPrefToken(), "PHTAM", keyCode,
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
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
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
            ApiServices.getInstance().uploadFile(SharedPreferencesManager.getInstance().getPrefToken(), "PHTAM", keyCode,
                    listImage,listFile, new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    System.out.println(response.body().getRETNMSSG());
                                    FileFragment.fileIncludeList.clear();
                                    uploadFileCallback.onUpLoadSuccess();
                                }
                                else {
                                    System.out.println(response.body().getRETNMSSG());
                                    uploadFileCallback.onUploadFail(response.body().getRETNMSSG());
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
    private void addcControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62),SharedPreferencesManager.getSystemLabel(63));
        showLoadingNonMessDialog();
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        
        txtTitle=binding.include7.findViewById(R.id.txtTitle);
        currencyItemList= new ArrayList<>();
        adapterCurrency = new ArrayAdapter(getContext(), R.layout.spiner_item,currencyItemList);
        adapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerDonViTienTe.setAdapter(adapterCurrency);
        
        loaiDoiTuongNhanItemList = new ArrayList<>();
        adapterloaiDoiTuongNhan = new ArrayAdapter(getContext(),R.layout.spiner_item, loaiDoiTuongNhanItemList);
        adapterloaiDoiTuongNhan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerLoaiDoiTuongNhan.setAdapter(adapterloaiDoiTuongNhan);
        
        projectListItemList= new ArrayList<>();
        projectListAdapter =  new ArrayAdapter(getContext(), R.layout.spiner_item, projectListItemList);
        projectListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerMaDuAn.setAdapter(projectListAdapter);
        
        loaiDeNghiItemList = new ArrayList<>();
        adapterLoaiDeNghi = new ArrayAdapter(getContext(), R.layout.spiner_item,loaiDeNghiItemList);
        adapterLoaiDeNghi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerLoaiDeNghiTamUng.setAdapter(adapterLoaiDeNghi);
        
        dateCreate= new Date(System.currentTimeMillis());
        dateDeNghiChi = new Date(System.currentTimeMillis());
        
        binding.setIsEditable(true);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdvanceProposalFormViewModel.class);
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
        mViewModel.getLiveDataCurrencyList().observe(getViewLifecycleOwner(), new Observer<List<CurrencyItem>>() {
            @Override
            public void onChanged(List<CurrencyItem> currencyItems) {
                currencyItemList.clear();
                currencyItemList.addAll(currencyItems);
                adapterCurrency.notifyDataSetChanged();
            }
        });
        
        // Sipnner loại đối tượng nhận (Nhân viên tạm ứng - 1411....)
        mViewModel.loadData(new LoadDataAsynCallback() {
            @Override
            public void onLoadSuccess() {
                loadingNonMessDialog.dismiss();
                mViewModel.getLiveDataLoaideNghitamUng().observe(getViewLifecycleOwner(), new Observer<List<LoaiDoiTuongNhanItem>>() {
                    @Override
                    public void onChanged(List<LoaiDoiTuongNhanItem> loaiDeNghiTamUngItems) {
                        loaiDoiTuongNhanItemList.clear();
                        loaiDoiTuongNhanItemList.addAll(loaiDeNghiTamUngItems);
                        adapterloaiDoiTuongNhan.notifyDataSetChanged();
                    }
                });
                mViewModel.getLiveDataProjectList().observe(getViewLifecycleOwner(), new Observer<List<ProjectListItem>>() {
                    @Override
                    public void onChanged(List<ProjectListItem> projectListItems) {
                        projectListItemList.clear();
                        projectListItemList.addAll(projectListItems);
                        projectListAdapter.notifyDataSetChanged();
                    }
                });
                // Spinner Loại đề nghị tạm ứng
                mViewModel.getLiveDataLoaiTamUng().observe(getViewLifecycleOwner(), new Observer<List<LoaiDeNghiItem>>() {
                    @Override
                    public void onChanged(List<LoaiDeNghiItem> loaiDeNghiItems) {
                        loaiDeNghiItemList.clear();
                        loaiDeNghiItemList.addAll(loaiDeNghiItems);
                        adapterLoaiDeNghi.notifyDataSetChanged();
                    }
                });
                if (signatureItemApiResponse!=null){
                    mViewModel.loadAdvanceProposalForm(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode(), new AdvanceProposalFormCallback() {
                        @Override
                        public void onLoadSuccess(AdvanceProposalFormApiResponse advanceProposalFormApiResponse) {
                            if (advanceProposalFormApiResponse.isRETNCODE()){
                                if (advanceProposalFormApiResponse.getAdvanceProposalFormHeaders()!=null){
                                    if (advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().size() > 0){
                                        if (advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getmAINCODE()!=null){
                                            accessRole = checkAccessRole(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getsTTESIGN(),
                                                    advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getaCCERGHT(),
                                                    binding.constraintLayout8,binding.constraintLayout9,binding.llDelete,null);
                                            
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_progress_switch_shift,
                                                    new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                                                    .commit();
                                            binding.setIsEditable(accessRole.isEdit());
                                            
                                            if (advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getsTTESIGN() == 0){
                                                getActivity().getSupportFragmentManager()
                                                        .beginTransaction()
                                                        .replace(R.id.frame_file_include, new FileFragment(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getdCMNFILE()))
                                                        .commit();
                                            }
                                            
                                            binding.txtDateCreate.setText(Util.formatDate(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getmAINDATE()));
                                            binding.edtInfo.setText(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getmEXLNNTE());
                                            binding.edtTiGia.setText(String.valueOf((int) advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getcUOMRATE()));
                                            binding.edtSoTienTamUng.setText(String.valueOf((int) advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getaDVNCRAM()));
                                            binding.edtSoTienDuocDuyet.setText(String.valueOf((int) advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getaCPTCRAM()));
                                            doiTuongNhanItemSelected = new DoiTuongNhanItem();
                                            doiTuongNhanItemSelected.setCheck(true);
                                            doiTuongNhanItemSelected.setiTEMCODE(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getoBJCCODE());
                                            doiTuongNhanItemSelected.setiTEMNAME(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getObjcName());
                                            binding.txtTenDoiTuongNhan.setText(doiTuongNhanItemSelected.getiTEMNAME());
                                            LoaiDoiTuongNhanItem loaiDoiTuongNhanItem = new LoaiDoiTuongNhanItem();
                                            loaiDoiTuongNhanItem.setiTEMCODE(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getoBJCTYPE()+"");
                                            loaiDoiTuongNhanItem.setiTEMNAME(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getoBJCTYPENAME());
                                            binding.txtNgayThanhToan.setText(Util.formatDate(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getpERDDATE()));
                                            
                                            for (int i = 0 ;i < loaiDeNghiItemList.size();i++){
                                                LoaiDeNghiItem loaiDeNghi= loaiDeNghiItemList.get(i);
                                                if (loaiDeNghi.getiTEMCODE().equals(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getdCMNSBCD())){
                                                    binding.spinerLoaiDeNghiTamUng.setSelection(i);
                                                    break;
                                                }
                                            }
                                            
                                            for (int i = 0;i < loaiDoiTuongNhanItemList.size();i++){
                                                LoaiDoiTuongNhanItem item  = loaiDoiTuongNhanItemList.get(i);
                                                if (item.getiTEMCODE().equals(loaiDoiTuongNhanItem.getiTEMCODE())){
                                                    binding.spinerLoaiDoiTuongNhan.setSelection(i);
                                                    break;
                                                }
                                            }
                                            for (int i = 0;i< currencyItemList.size();i++){
                                                CurrencyItem currencyItem = currencyItemList.get(i);
                                                if (currencyItem.getiTEMCODE().equals(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getcUOMCODE())){
                                                    binding.spinerDonViTienTe.setSelection(i);
                                                    break;
                                                }
                                            }
                                            for (int i =0;i< projectListItemList.size() ; i++){
                                                ProjectListItem projectListItem = projectListItemList.get(i);
                                                if (projectListItem.getiTEMCODE().equals(advanceProposalFormApiResponse.getAdvanceProposalFormHeaders().get(0).getaCOBCODE())){
                                                    binding.spinerMaDuAn.setSelection(i);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                
                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),advanceProposalFormApiResponse.getRETNMSSG());
                            }
                        }
                        
                        @Override
                        public void onLoadFail(String mess) {
                            showOutTOKEN();
                        }
                        
                        @Override
                        public void onServerFail() {
                            showOutTOKEN();
                        }
                    });
                    progressdialog.dismiss();
                }
                else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_file_include, new FileFragment(new ArrayList<>()))
                            .commit();
                    progressdialog.dismiss();
                }
            }
        });
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE_SELECT_DOI_TUONG_NHAN && resultCode== Activity.RESULT_OK){
            doiTuongNhanItemSelected = (DoiTuongNhanItem) data.getSerializableExtra(Constant.NAME_PUT_DOI_TUONG_NHAN);
            if (doiTuongNhanItemSelected!=null){
                binding.txtTenDoiTuongNhan.setText(doiTuongNhanItemSelected.getiTEMNAME());
            }
        }
    }
}