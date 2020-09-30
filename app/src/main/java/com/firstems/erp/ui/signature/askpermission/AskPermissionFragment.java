package com.firstems.erp.ui.signature.askpermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.adapter.ApprovedItemAdapter;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.request.askpermistion.AskPermistionRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionDetail;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader;
import com.firstems.erp.api.model.response.askpermistion.AskPermistionUpdateResponse;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.LoadContentCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.AskPermissionFragmentBinding;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.helper.validation.ValidationData;
import com.firstems.erp.model.approved.Approved;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.firstems.erp.ui.signature.askpermission.info.AskPermissionInfoActivity;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskPermissionFragment extends CommonFragment {

    private AskPermissionViewModel mViewModel;
    private AskPermissionFragmentBinding binding;
    private View view;
    private TextView txtTitle;
    private List<Approved> list;
    private RecyclerView recyclerView;
    private ApprovedItemAdapter approvedItemAdapter;
    private int CODE_ADD_APPROVED = 1;
    private int CODE_EDIT_APPROVED = 2;
    private int positionEdit = 0;
    private SignatureItemApiResponse signatureItemApiResponse;
    private Date mainDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.ask_permission_fragment,container,false);
        view=binding.getRoot();

        setAminHeader();
        addControls();
        addEvents();
        setText();
        return view;
    }
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.edtInfo.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.edtInfo.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void setText() {
        binding.txtTitleDateCreate.setText(SharedPreferencesManager.getSystemLabel(23));
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(24));
        binding.txtTitleDocumentCode.setText(SharedPreferencesManager.getSystemLabel(44));
        binding.txtTitleDate.setText(SharedPreferencesManager.getSystemLabel(26));
        binding.txtTitleSpecies.setText(SharedPreferencesManager.getSystemLabel(27));
        binding.txtTitleSum.setText(SharedPreferencesManager.getSystemLabel(28));
        binding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
        binding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
    }

    private void addEvents() {
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (signatureItemApiResponse!=null){
                   showConfirmMessage(SharedPreferencesManager.getSystemLabel(49) /*Xác nhận*/,
                           SharedPreferencesManager.getSystemLabel(53) /*Bạn chắc chắn muốn xóa chứng từ này?*/,
                           SharedPreferencesManager.getSystemLabel(54) /*Đồng ý*/,
                           SharedPreferencesManager.getSystemLabel(55) /*Hủy*/, new ConfirmCallback() {
                               @Override
                               public void onAccept() {
                                   DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
                                   deleteDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                   deleteDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                                   ApiServices.getInstance().deleteDocument(SharedPreferencesManager.getInstance().getPrefToken(), deleteDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                                       @Override
                                       public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                           if (response.isSuccessful()){
                                               if (response.body().isRETNCODE()){
                                                   showSuccessDialog(SharedPreferencesManager.getSystemLabel(50) /*Thông báo*/,!response.body().getRETNMSSG().equals("") ? response.body().getRETNMSSG(): SharedPreferencesManager.getSystemLabel(52) /*Xóa chứng từ thành công!*/ );
                                               }
                                               else {
                                                   showErrorDialog(SharedPreferencesManager.getSystemLabel(50) /*Thông báo*/,!response.body().getRETNMSSG().equals("") ? response.body().getRETNMSSG(): SharedPreferencesManager.getSystemLabel(51) /*Không thể xóa chứng từ này!*/);
                                               }
                                           }
                                           else {
                                               showOutTOKEN();
                                           }
                                       }
    
                                       @Override
                                       public void onFailure(Call<ApiResponse> call, Throwable t) {
                                           showOutTOKEN();
                                       }
                                   });
                    
                               }
                
                               @Override
                               public void onCancel() {
                    
                               }
                           });
               }
            }
        });
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), AskPermissionInfoActivity.class);
                startActivityForResult(intent,CODE_ADD_APPROVED);
            }
        });
        binding.include2.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        binding.constraintLayout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.edtInfo.equals("")){
                    if (list.size()>0){
                        if (signatureItemApiResponse!=null){
                            // trình ký
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
                            // Luu mới và trình ký
                            showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                @Override
                                public void onAccept() {
                                    doSaveAndCommit();
                                }
    
                                @Override
                                public void onCancel() {
        
                                }
                            });
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(48));
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(47));
                }
               
            }
        });
        binding.constraintLayout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationData.isBlank(binding.edtInfo)){
                    if (list.size()>0){
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
                        showToastError(SharedPreferencesManager.getSystemLabel(48));
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(47));
                }
            }
        });
    }
    
    private void doCommit() {
        progressdialog.show();
        AskPermistionHeader askPermistionHeader = new AskPermistionHeader();
        askPermistionHeader.setcOMPCODE(SharedPreferencesManager.getInstance().getPrefCompcode());
        askPermistionHeader.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        askPermistionHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        askPermistionHeader.setsUMLEAV(Double.parseDouble(binding.edtSumDay.getText().toString()));
        askPermistionHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        askPermistionHeader.setKeyCode(signatureItemApiResponse.getKeyCode());
        List<AskPermistionDetail> askPermistionDetails = new ArrayList<>();
        for (Approved approved : list){
            AskPermistionDetail detail = new AskPermistionDetail();
            detail.settIMEMORN((approved.isMorning() ? approved.getContentMornig().getItemCode() : null));
            detail.settIMEAFTR((approved.isAfternoon() ? approved.getContentAfternoon().getItemCode() : null));
            detail.settIMEEVEN((approved.isEverning() ? approved.getContentEverning().getItemCode() : null));
            detail.setfRLVDATE(simpleDateFormatSystem.format(approved.getDateBegin()));
            detail.settOLVDATE(simpleDateFormatSystem.format(approved.getDateEnd()));
            detail.seteMPLRLTN(approved.getEmployeeDiLam().getItemCode());
        
            askPermistionDetails.add(detail);
        }
        askPermistionHeader.setAskPermistionDetails(askPermistionDetails);
        List<AskPermistionHeader> askPermistionHeaders = new ArrayList<>();
        askPermistionHeaders.add(askPermistionHeader);
    
        AskPermistionRequest askPermistionRequest = new AskPermistionRequest();
        askPermistionRequest.setAskPermistionHeaders(askPermistionHeaders);
    
        try {
            JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(askPermistionRequest), JsonObject.class);
            ApiServices
                    .getInstance()
                    .editAskPermistion(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AskPermistionUpdateResponse>() {
                        @Override
                        public void onResponse(Call<AskPermistionUpdateResponse> call, Response<AskPermistionUpdateResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                                    commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                    commitDocumentRequest.setKeyCode(response.body().getAskPermistionUpdateItemLis().get(0).getKeyCode());
                                    ApiServices.getInstance().commitDocument(SharedPreferencesManager.getInstance().getPrefToken(), commitDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                            if (response.isSuccessful()){
                                                ApiResponse apiResponse =response.body();
                                                if (apiResponse.isRETNCODE()){
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
                                        }
                                    });
                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50), response.body().getRETNMSSG());
                                }
                            }
                            else {
                                progressdialog.dismiss();
                                showOutTOKEN();
                            }
                        }
                    
                        @Override
                        public void onFailure(Call<AskPermistionUpdateResponse> call, Throwable t) {
                            progressdialog.dismiss();
                            showOutTOKEN();
                        }
                    });
        }
        catch (Exception ex){
            progressdialog.dismiss();
            ex.printStackTrace();
            showOutTOKEN();
        }
    }
    
    private void doSaveAndCommit() {
        progressdialog.show();
    
        AskPermistionHeader askPermistionHeader = new AskPermistionHeader();
        askPermistionHeader.setcOMPCODE(SharedPreferencesManager.getInstance().getPrefCompcode());
        askPermistionHeader.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        askPermistionHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        askPermistionHeader.setsUMLEAV(Double.parseDouble(binding.edtSumDay.getText().toString()));
        askPermistionHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        List<AskPermistionDetail> askPermistionDetails = new ArrayList<>();
        for (Approved approved : list){
            AskPermistionDetail detail = new AskPermistionDetail();
            detail.settIMEMORN((approved.isMorning() ? approved.getContentMornig().getItemCode() : null));
            detail.settIMEAFTR((approved.isAfternoon() ? approved.getContentAfternoon().getItemCode() : null));
            detail.settIMEEVEN((approved.isEverning() ? approved.getContentEverning().getItemCode() : null));
            detail.setfRLVDATE(simpleDateFormatSystem.format(approved.getDateBegin()));
            detail.settOLVDATE(simpleDateFormatSystem.format(approved.getDateEnd()));
            detail.seteMPLRLNM(approved.getEmployeeDiLam().getItemName());
            detail.seteMPLRLTN(approved.getEmployeeDiLam().getItemCode());
        
            askPermistionDetails.add(detail);
        }
        askPermistionHeader.setAskPermistionDetails(askPermistionDetails);
        List<AskPermistionHeader> askPermistionHeaders = new ArrayList<>();
        askPermistionHeaders.add(askPermistionHeader);
    
        AskPermistionRequest askPermistionRequest = new AskPermistionRequest();
        askPermistionRequest.setAskPermistionHeaders(askPermistionHeaders);
    
        try {
            JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(askPermistionRequest), JsonObject.class);
            ApiServices
                    .getInstance()
                    .addNewAndCommitAskPermistion(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    progressdialog.dismiss();
                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50), response.body().getRETNMSSG());
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
        catch (Exception ex){
            progressdialog.dismiss();
            showOutTOKEN();
            ex.printStackTrace();
        }
    
    }
    
    private void doUpdate() {
        progressdialog.show();

        AskPermistionHeader askPermistionHeader = new AskPermistionHeader();
        askPermistionHeader.setcOMPCODE(SharedPreferencesManager.getInstance().getPrefCompcode());
        askPermistionHeader.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        askPermistionHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        askPermistionHeader.setsUMLEAV(Double.parseDouble(binding.edtSumDay.getText().toString()));
        askPermistionHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        askPermistionHeader.setKeyCode(signatureItemApiResponse.getKeyCode());
        List<AskPermistionDetail> askPermistionDetails = new ArrayList<>();
        for (Approved approved : list){
            AskPermistionDetail detail = new AskPermistionDetail();
            detail.settIMEMORN((approved.isMorning() ? approved.getContentMornig().getItemCode() : null));
            detail.settIMEAFTR((approved.isAfternoon() ? approved.getContentAfternoon().getItemCode() : null));
            detail.settIMEEVEN((approved.isEverning() ? approved.getContentEverning().getItemCode() : null));
            detail.setfRLVDATE(simpleDateFormatSystem.format(approved.getDateBegin()));
            detail.settOLVDATE(simpleDateFormatSystem.format(approved.getDateEnd()));
            detail.seteMPLRLTN(approved.getEmployeeDiLam().getItemCode());

            askPermistionDetails.add(detail);
        }
        askPermistionHeader.setAskPermistionDetails(askPermistionDetails);
        List<AskPermistionHeader> askPermistionHeaders = new ArrayList<>();
        askPermistionHeaders.add(askPermistionHeader);

        AskPermistionRequest askPermistionRequest = new AskPermistionRequest();
        askPermistionRequest.setAskPermistionHeaders(askPermistionHeaders);

        try {
            JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(askPermistionRequest), JsonObject.class);
            ApiServices
                    .getInstance()
                    .editAskPermistion(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AskPermistionUpdateResponse>() {
                        @Override
                        public void onResponse(Call<AskPermistionUpdateResponse> call, Response<AskPermistionUpdateResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    progressdialog.dismiss();
                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50), response.body().getRETNMSSG());
                                }
                            }
                            else {
                                progressdialog.dismiss();
                                showOutTOKEN();
                            }
                        }

                        @Override
                        public void onFailure(Call<AskPermistionUpdateResponse> call, Throwable t) {
                            progressdialog.dismiss();
                            showOutTOKEN();
                        }
                    });
        }
        catch (Exception ex){
            progressdialog.dismiss();
            ex.printStackTrace();
            showOutTOKEN();
        }
    }

    private void doSave() {
        progressdialog.show();

        AskPermistionHeader askPermistionHeader = new AskPermistionHeader();
        askPermistionHeader.setcOMPCODE(SharedPreferencesManager.getInstance().getPrefCompcode());
        askPermistionHeader.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        askPermistionHeader.setmAINDATE(simpleDateFormatSystem.format(mainDate));
        askPermistionHeader.setsUMLEAV(Double.parseDouble(binding.edtSumDay.getText().toString()));
        askPermistionHeader.setmEXLNNTE(binding.edtInfo.getText().toString());
        List<AskPermistionDetail> askPermistionDetails = new ArrayList<>();
        for (Approved approved : list){
            AskPermistionDetail detail = new AskPermistionDetail();
            detail.settIMEMORN((approved.isMorning() ? approved.getContentMornig().getItemCode() : null));
            detail.settIMEAFTR((approved.isAfternoon() ? approved.getContentAfternoon().getItemCode() : null));
            detail.settIMEEVEN((approved.isEverning() ? approved.getContentEverning().getItemCode() : null));
            detail.setfRLVDATE(simpleDateFormatSystem.format(approved.getDateBegin()));
            detail.settOLVDATE(simpleDateFormatSystem.format(approved.getDateEnd()));
            detail.seteMPLRLNM(approved.getEmployeeDiLam().getItemName());
            detail.seteMPLRLTN(approved.getEmployeeDiLam().getItemCode());

            askPermistionDetails.add(detail);
        }
        askPermistionHeader.setAskPermistionDetails(askPermistionDetails);
        List<AskPermistionHeader> askPermistionHeaders = new ArrayList<>();
        askPermistionHeaders.add(askPermistionHeader);

        AskPermistionRequest askPermistionRequest = new AskPermistionRequest();
        askPermistionRequest.setAskPermistionHeaders(askPermistionHeaders);

        try {
            JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(askPermistionRequest), JsonObject.class);
            ApiServices
                    .getInstance()
                    .addNewAskPermistion(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().isRETNCODE()){
                                    progressdialog.dismiss();
                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50), response.body().getRETNMSSG());
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
        catch (Exception ex){
            progressdialog.dismiss();
            showErrorDialog(SharedPreferencesManager.getSystemLabel(50), SharedPreferencesManager.getSystemLabel(64));
            ex.printStackTrace();
        }

    }

    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62), SharedPreferencesManager.getSystemLabel(63));
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        
        txtTitle=binding.include2.findViewById(R.id.txtTitle);

        recyclerView =binding.recycleview;
        list = new ArrayList<>();
        approvedItemAdapter = new ApprovedItemAdapter(list, new ApprovedItemAdapter.ApprovedItemClick() {
            @Override
            public void onEditClick(Approved approved, int position) {
                Intent intent= new Intent(getActivity(), AskPermissionInfoActivity.class);
                intent.putExtra(Constant.NAME_PUT_APPROVED,approved);
                startActivityForResult(intent,CODE_EDIT_APPROVED);
            }

            @Override
            public void onDeleteClick(Approved approved, int position) {
                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                    @Override
                    public void onAccept() {
                        list.remove(position);
                        updateList();
                        SnackbarHelper.getInstance().snackBarIconSuccess(getView(),SharedPreferencesManager.getSystemLabel(65));
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(approvedItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        
        if (signatureItemApiResponse!=null){
        
        }
        else {
            mainDate = DateTime.now().withTimeAtStartOfDay().toDate();
            AskPermistionHeader askPermistionHeader = new AskPermistionHeader();
            askPermistionHeader.setmAINDATE(simpleDateFormatDisplay.format(mainDate));
            binding.setItem(askPermistionHeader);
            binding.setIsEditable(true);
            approvedItemAdapter.setRole(1);
        }
        binding.layoutNgayLap.setEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AskPermissionViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        if (signatureItemApiResponse!=null){
            mViewModel.getDataSinature(signatureItemApiResponse.getDcmnCode(),signatureItemApiResponse.getKeyCode());
        }

        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        mViewModel.getTimeKeepingList(new LoadContentCallback() {
            @Override
            public void Loaded() {
                mViewModel.getPermistionApiResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<AskPermistionHeader>() {
                    @Override
                    public void onChanged(AskPermistionHeader askPermistionHeader) {
                        if (signatureItemApiResponse!=null){
                            binding.setItem(askPermistionHeader);
                            AccessRole accessRole = checkAccessRole(askPermistionHeader.getsTTESIGN(),askPermistionHeader.aCCERGHT,binding.constraintLayout12,binding.constraintLayout13,binding.llDelete,binding.imgAdd);
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_ask_permistion, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(),signatureItemApiResponse.getKeyCode()))
                                    .commit();
                            binding.setIsEditable(accessRole.isEdit());
                            approvedItemAdapter.setRole(accessRole.isEdit() ? 1 : 0);
                            try {
                                mainDate = (askPermistionHeader.getMainDate()!=null && askPermistionHeader.getmAINCODE().equals("") ? simpleDateFormatSystem.parse(Util.formatDateSystem(askPermistionHeader.getMainDate())) : new Date(System.currentTimeMillis()));
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                            for (AskPermistionDetail detail : askPermistionHeader.askPermistionDetails){
                                Approved approved = new Approved();
                                approved.setEmployeeDiLam(new Employee(detail.geteMPLRLTN(),detail.geteMPLRLNM()));
                                try {
                                    approved.setDateEnd(detail.gettOLVDATE()!=null ? simpleDateFormatSystem.parse(Util.formatDateSystem(detail.gettOLVDATE())) : new Date(System.currentTimeMillis()));
                                    approved.setDateBegin(detail.getfRLVDATE()!=null ? simpleDateFormatSystem.parse(Util.formatDateSystem(detail.getfRLVDATE())) : new Date(System.currentTimeMillis()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                    
                                if(detail.gettIMEMORN()!=null && !detail.gettIMEMORN().equals("")) {
                                    approved.setMorning(true);
                                    approved.setContentMornig(mViewModel.findTimeKeeping(detail.gettIMEMORN()));
                                }
                                if(detail.gettIMEAFTR()!=null && !detail.gettIMEAFTR().equals("")) {
                                    approved.setAfternoon(true);
                                    TimekeepingTypeDC typeDCChieu = mViewModel.findTimeKeeping(detail.gettIMEAFTR());
                                    approved.setContentAfternoon(typeDCChieu);
                                }
                                if(detail.gettIMEEVEN()!=null && !detail.gettIMEEVEN().equals("")) {
                                    approved.setEverning(true);
                                    TimekeepingTypeDC typeDCToi = mViewModel.findTimeKeeping(detail.gettIMEEVEN());
                                    approved.setContentEverning(typeDCToi);
                                }
                                list.add(approved);
                                approvedItemAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD_APPROVED && resultCode == Activity.RESULT_OK){
            Approved approved = (Approved) data.getSerializableExtra(Constant.NAME_PUT_APPROVED);
            list.add(approved);
            updateList();
            SnackbarHelper.getInstance().snackBarIconSuccess(getView(),SharedPreferencesManager.getSystemLabel(66));
        }
        else if (requestCode == CODE_EDIT_APPROVED && resultCode == Activity.RESULT_OK){
            Approved approved = (Approved) data.getSerializableExtra(Constant.NAME_PUT_APPROVED);
            list.set(positionEdit,approved);
            updateList();
            SnackbarHelper.getInstance().snackBarIconSuccess(getView(),SharedPreferencesManager.getSystemLabel(67));
        }
    }
    private void updateList(){
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (Approved approved : list){
                    sum+=approved.getNumberDay();
                }
                binding.edtSumDay.setText(String.valueOf(sum));
                approvedItemAdapter.notifyDataSetChanged();
            }
        });
    }
}