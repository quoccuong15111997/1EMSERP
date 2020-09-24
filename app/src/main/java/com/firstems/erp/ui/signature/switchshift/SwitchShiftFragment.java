package com.firstems.erp.ui.signature.switchshift;

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
import com.firstems.erp.adapter.SwitchShiftAdapter;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.request.switchshift.SwitchShifhRequest;
import com.firstems.erp.api.model.request.switchshift.SwithshiftDetailRequest;
import com.firstems.erp.api.model.request.switchshift.SwithshiftHeaderRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftApiResponse;
import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftDetail;
import com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftItem;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.LoadDataKeppingCallback;
import com.firstems.erp.callback.SwitchShiftItemClickCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.databinding.SwitchShiftFragmentBinding;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.model.switchshift.SwitchShift;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.firstems.erp.ui.signature.switchshift.info.SwitchShiftInfoActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwitchShiftFragment extends CommonFragment implements SwitchShiftItemClickCallback {
    
    private SwitchShiftViewModel mViewModel;
    private View view;
    private SwitchShiftFragmentBinding switchShiftFragmentBinding;
    private TextView txtTitle;
    private RecyclerView recyclerViewSwitchShift;
    private SwitchShiftAdapter switchShiftAdapter;
    private List<SwitchShift> list;
    private int CODE_ADD_SWITCH_SHIFT = 1;
    private int CODE_EDIT_SWITCH_SHIFT = 2;
    private int positionEdit=0;
    private SignatureItemApiResponse signatureItemApiResponse;
    private AccessRole accessRole;
    private Date createDate;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        switchShiftFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.switch_shift_fragment,container,false);
        view=switchShiftFragmentBinding.getRoot();
        
        setAminHeader();
        addControls();
        addEvents();
        setText();
        customUI();
        return view;
    }
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(switchShiftFragmentBinding.lParentContent);
                        switchShiftFragmentBinding.txtTitleInfo.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            switchShiftFragmentBinding.txtTitleInfo.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void customUI() {
    }
    
    private void setText() {
        switchShiftFragmentBinding.txtTitleDocumentCode.setText(SharedPreferencesManager.getSystemLabel(23));
        switchShiftFragmentBinding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(24));
        switchShiftFragmentBinding.chkReShift.setText(SharedPreferencesManager.getSystemLabel(25));
        switchShiftFragmentBinding.txtTitleDate.setText(SharedPreferencesManager.getSystemLabel(26));
        switchShiftFragmentBinding.txtTitleSum.setText(SharedPreferencesManager.getSystemLabel(28));
        switchShiftFragmentBinding.txtTitleSpecies.setText(SharedPreferencesManager.getSystemLabel(27));
        switchShiftFragmentBinding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
        switchShiftFragmentBinding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
    }
    
    private void addEvents() {
        switchShiftFragmentBinding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signatureItemApiResponse!=null){
                    DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
                    deleteDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                    deleteDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                        @Override
                        public void onAccept() {
                            ApiServices
                                    .getInstance()
                                    .deleteDocument(SharedPreferencesManager.getInstance().getPrefToken(), deleteDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                            if (response.isSuccessful()){
                                                ApiResponse apiResponse = response.body();
                                                if (apiResponse.isRETNCODE()){
                                                    progressdialog.dismiss();
                                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(52));
                                                }
                                            }
                                            else {
                                                progressdialog.dismiss();
                                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(51));
                                            }
                                        }
                                        
                                        @Override
                                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                                            progressdialog.dismiss();
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(51));
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
        switchShiftFragmentBinding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SwitchShiftInfoActivity.class);
                startActivityForResult(intent,CODE_ADD_SWITCH_SHIFT);
            }
        });
        switchShiftFragmentBinding.include2.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        switchShiftFragmentBinding.constraintLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.isBlank(switchShiftFragmentBinding.edtInfo.getText().toString())){
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
                                    doUpdateSwitchShift();
                                }
                                
                                @Override
                                public void onCancel() {
                                
                                }
                            });
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(48));
                        return;
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(47));
                    return;
                }
                
                
                
            }
        });
        switchShiftFragmentBinding.constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.isBlank(switchShiftFragmentBinding.edtInfo.getText().toString())){
                    if (list.size()>0) {
                        if (signatureItemApiResponse!=null){
                            showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                @Override
                                public void onAccept() {
                                    progressdialog.show();
                                    CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                                    commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                    commitDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                
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
                                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(60));
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                                            System.out.println(t.getMessage());
                                            progressdialog.dismiss();
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(60));
                                        }
                                    });
                                }
            
                                @Override
                                public void onCancel() {
                
                                }
                            });
                        }
                        else {
                            showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(19), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
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
                        return;
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(47));
                    return;
                }
            }
        });
    }
    
    private void doUpdateSwitchShift() {
        progressdialog.show();
        List<SwithshiftDetailRequest>swithshiftDetailRequests = new ArrayList<>();
        for (SwitchShift switchShift : list){
            SwithshiftDetailRequest detailRequest = new SwithshiftDetailRequest();
            detailRequest.setfRLVDATE(simpleDateFormatSystem.format(switchShift.getDateBegin()));
            detailRequest.settOLVDATE(simpleDateFormatSystem.format(switchShift.getDateEnd()));
            detailRequest.seteMPLCDDT(switchShift.getEmployeeDiLam().getItemCode());
            detailRequest.seteMPLCDNM(switchShift.getEmployeeDiLam().getItemName());
            detailRequest.seteMPLRLTN(switchShift.getEmployeeChamCong().getItemCode());
            detailRequest.seteMPLRLNM(switchShift.getEmployeeChamCong().getItemName());
            detailRequest.setmAINCODE(signatureItemApiResponse.getMainCode());
            if (switchShift.isMorning()){
                detailRequest.settIMEMORN(switchShift.getContentMornig().getItemCode());
            }
            if (switchShift.isAfternoon()){
                detailRequest.settIMEAFTR(switchShift.getContentAfternoon().getItemCode());
            }
            if (switchShift.isEverning()){
                detailRequest.settIMEEVEN(switchShift.getContentEverning().getItemCode());
            }
            
            swithshiftDetailRequests.add(detailRequest);
        }
        List<SwithshiftHeaderRequest> swithshiftHeaderRequests = new ArrayList<>();
        SwithshiftHeaderRequest headerRequest = new SwithshiftHeaderRequest();
        headerRequest.setDetailRequests(swithshiftDetailRequests);
        headerRequest.setmAINDATE(simpleDateFormatSystem.format(createDate));
        headerRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        headerRequest.setnOTETEXT(switchShiftFragmentBinding.edtInfo.getText().toString());
        headerRequest.setcHGESLRY(switchShiftFragmentBinding.chkReShift.isChecked() ? 1 : 0);
        headerRequest.setmAINCODE(signatureItemApiResponse.getMainCode());
        headerRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
        
        swithshiftHeaderRequests.add(headerRequest);
        SwitchShifhRequest switchShifhRequest= new SwitchShifhRequest();
        switchShifhRequest.setSwithshiftHeaderRequests(swithshiftHeaderRequests);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(switchShifhRequest),JsonObject.class);
        ApiServices.getInstance().updateSwitchsift(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        progressdialog.dismiss();
                        showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                    }
                }
                else {
                    progressdialog.dismiss();
                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(61));
            }
        });
    }
    
    private void doSaveAndCommit() {
        progressdialog.show();
        List<SwithshiftDetailRequest>swithshiftDetailRequests = new ArrayList<>();
        for (SwitchShift switchShift : list){
            SwithshiftDetailRequest detailRequest = new SwithshiftDetailRequest();
            detailRequest.setfRLVDATE(simpleDateFormatSystem.format(switchShift.getDateBegin()));
            detailRequest.settOLVDATE(simpleDateFormatSystem.format(switchShift.getDateEnd()));
            detailRequest.seteMPLCDDT(switchShift.getEmployeeDiLam().getItemCode());
            detailRequest.seteMPLCDNM(switchShift.getEmployeeDiLam().getItemName());
            detailRequest.seteMPLRLTN(switchShift.getEmployeeChamCong().getItemCode());
            detailRequest.seteMPLRLNM(switchShift.getEmployeeChamCong().getItemName());
            if (switchShift.isMorning()){
                detailRequest.settIMEMORN(switchShift.getContentMornig().getItemCode());
            }
            if (switchShift.isAfternoon()){
                detailRequest.settIMEAFTR(switchShift.getContentAfternoon().getItemCode());
            }
            if (switchShift.isEverning()){
                detailRequest.settIMEEVEN(switchShift.getContentEverning().getItemCode());
            }
            
            swithshiftDetailRequests.add(detailRequest);
        }
        List<SwithshiftHeaderRequest> swithshiftHeaderRequests = new ArrayList<>();
        SwithshiftHeaderRequest headerRequest = new SwithshiftHeaderRequest();
        headerRequest.setDetailRequests(swithshiftDetailRequests);
        headerRequest.setmAINDATE(simpleDateFormatSystem.format(createDate));
        headerRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        headerRequest.setnOTETEXT(switchShiftFragmentBinding.edtInfo.getText().toString());
        headerRequest.setcHGESLRY(switchShiftFragmentBinding.chkReShift.isChecked() ? 1 : 0);
        headerRequest.setmAINCODE("");
        
        swithshiftHeaderRequests.add(headerRequest);
        SwitchShifhRequest switchShifhRequest= new SwitchShifhRequest();
        switchShifhRequest.setSwithshiftHeaderRequests(swithshiftHeaderRequests);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(switchShifhRequest),JsonObject.class);
        ApiServices.getInstance().addAndCommitSignatureDocumentSwitchShift(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
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
                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(60));
                }
            }
            
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(60));
            }
        });
    }
    
    private void doSave() {
        progressdialog.show();
        List<SwithshiftDetailRequest>swithshiftDetailRequests = new ArrayList<>();
        for (SwitchShift switchShift : list){
            SwithshiftDetailRequest detailRequest = new SwithshiftDetailRequest();
            detailRequest.setfRLVDATE(simpleDateFormatSystem.format(switchShift.getDateBegin()));
            detailRequest.settOLVDATE(simpleDateFormatSystem.format(switchShift.getDateEnd()));
            detailRequest.seteMPLCDDT(switchShift.getEmployeeDiLam().getItemCode());
            detailRequest.seteMPLCDNM(switchShift.getEmployeeDiLam().getItemName());
            detailRequest.seteMPLRLTN(switchShift.getEmployeeChamCong().getItemCode());
            detailRequest.seteMPLRLNM(switchShift.getEmployeeChamCong().getItemName());
            if (switchShift.isMorning()){
                detailRequest.settIMEMORN(switchShift.getContentMornig().getItemCode());
            }
            if (switchShift.isAfternoon()){
                detailRequest.settIMEAFTR(switchShift.getContentAfternoon().getItemCode());
            }
            if (switchShift.isEverning()){
                detailRequest.settIMEEVEN(switchShift.getContentEverning().getItemCode());
            }
            
            swithshiftDetailRequests.add(detailRequest);
        }
        List<SwithshiftHeaderRequest> swithshiftHeaderRequests = new ArrayList<>();
        SwithshiftHeaderRequest headerRequest = new SwithshiftHeaderRequest();
        headerRequest.setDetailRequests(swithshiftDetailRequests);
        headerRequest.setmAINDATE(simpleDateFormatSystem.format(createDate));
        headerRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        headerRequest.setnOTETEXT(switchShiftFragmentBinding.edtInfo.getText().toString());
        headerRequest.setcHGESLRY(switchShiftFragmentBinding.chkReShift.isChecked() ? 1 : 0);
        headerRequest.setmAINCODE("");
        
        swithshiftHeaderRequests.add(headerRequest);
        SwitchShifhRequest switchShifhRequest= new SwitchShifhRequest();
        switchShifhRequest.setSwithshiftHeaderRequests(swithshiftHeaderRequests);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(switchShifhRequest),JsonObject.class);
        DataNetworkProvider.getInstance().addNewSwitchShift(jsonObject, new DataApiCallback() {
            @Override
            public void onDataApi(String jsonAPI) {
                DataConvertProvider.getInstance().convertJsonToObject(jsonAPI, new SwitchShiftApiResponse(), new ConvertJsonCallback() {
                    @Override
                    public void onConvertSuccess(Object obj) {
                        SwitchShiftApiResponse switchShiftApiResponse = (SwitchShiftApiResponse) obj;
                        System.out.println(switchShiftApiResponse.getRETNMSSG());
                        if (switchShiftApiResponse.isRETNCODE()){
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),switchShiftApiResponse.getRETNMSSG());
                        }
                        else {
                            progressdialog.dismiss();
                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),switchShiftApiResponse.getRETNMSSG());
                        }
                    }
                });
            }
            
            @Override
            public void onApiLoadFail(String mess) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),mess);
            }
        });
    }
    
    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62),SharedPreferencesManager.getSystemLabel(63));
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        
        txtTitle=switchShiftFragmentBinding.include2.findViewById(R.id.txtTitle);
        list=new ArrayList<>();
        recyclerViewSwitchShift=switchShiftFragmentBinding.recycleview;
        switchShiftAdapter= new SwitchShiftAdapter(list,this);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSwitchShift.setAdapter(switchShiftAdapter);
        recyclerViewSwitchShift.setLayoutManager(linearLayoutManager);
        
        createDate = new Date(System.currentTimeMillis());
        switchShiftFragmentBinding.editTextTextPersonName.setText(simpleDateFormatDisplay.format(createDate));
        
        if (signatureItemApiResponse!=null){
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_progress_switch_shift, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                    .commit();
        }
        else {
            switchShiftFragmentBinding.setIsEditable(true);
            switchShiftAdapter.setRole(1);
        }
    }
    
    private void getDetailDocument() {
        if (mViewModel!=null){
            mViewModel.loadData(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode());
        }
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SwitchShiftViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        if (signatureItemApiResponse!=null){
            // Document is exits
            getDetailDocument();
        }
        else {
            SwitchShiftItem  switchShiftItem = new SwitchShiftItem();
            switchShiftItem.setMainDate(simpleDateFormatDisplay.format(createDate));
            switchShiftFragmentBinding.setItem(switchShiftItem);
        }
        mViewModel.loadDataTimeKeepingList(new LoadDataKeppingCallback() {
            @Override
            public void onLoaded() {
                mViewModel.getMutableLiveDataSwitchShiftResponse().observe(getViewLifecycleOwner(), new Observer<SwitchShiftApiResponse>() {
                    @Override
                    public void onChanged(SwitchShiftApiResponse switchShiftApiResponse) {
                        try {
                            if (switchShiftApiResponse!=null){
                                if (switchShiftApiResponse.getSwitchShiftItems()!=null){
                                    accessRole =checkAccessRole(switchShiftApiResponse.getSwitchShiftItems().get(0).getSttEsign(),switchShiftApiResponse.getSwitchShiftItems().get(0).getAccessRight()
                                            ,switchShiftFragmentBinding.constraintLayout8
                                            ,switchShiftFragmentBinding.constraintLayout9
                                            ,switchShiftFragmentBinding.llDelete
                                            ,switchShiftFragmentBinding.imgAdd);
                                    switchShiftFragmentBinding.setItem(switchShiftApiResponse.getSwitchShiftItems().get(0));
                                    switchShiftAdapter.setRole(accessRole.isEdit() ? 1 : 0);
                                    list.clear();
                                    if (switchShiftApiResponse.getSwitchShiftItems().get(0).getSwitchShiftDetails()!=null){
                                        for (SwitchShiftDetail switchShiftDetail : switchShiftApiResponse.getSwitchShiftItems().get(0).getSwitchShiftDetails()){
                                            SwitchShift switchShift = new SwitchShift();
                                            switchShift.setDateBegin(simpleDateFormatSystem.parse(Util.formatDateSystem(switchShiftDetail.getFrLVDATE())));
                                            switchShift.setDateEnd(simpleDateFormatSystem.parse(Util.formatDateSystem(switchShiftDetail.getToLVDATE())));
                                            if (switchShiftDetail.getTimeMORN()!=null && !switchShiftDetail.getTimeMORN().equals("")){
                                                switchShift.setContentMornig(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeMORN()));
                                                switchShift.setMorning(true);
                                            }
                                            if (switchShiftDetail.getTimeAFTR()!=null && !switchShiftDetail.getTimeAFTR().equals("")){
                                                switchShift.setAfternoon(true);
                                                switchShift.setContentAfternoon(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeAFTR()));
                                            }
                                            if (switchShiftDetail.getTimeEEVEN()!=null && !switchShiftDetail.getTimeEEVEN().equals("")){
                                                switchShift.setEverning(true);
                                                switchShift.setContentEverning(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeEEVEN()));
                                            }
                                            switchShift.setEmployeeDiLam(new Employee(switchShiftDetail.getEmplCDDT(),switchShiftDetail.getEmplCDNM()));
                                            switchShift.setEmployeeChamCong(new Employee(switchShiftDetail.getEmplRLTN(),switchShiftDetail.getEmplRLNM()));
                                            
                                            list.add(switchShift);
                                            switchShiftAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                        
                    }
                });
            }
        });
        mViewModel.getMutableLiveDataSwitchShiftResponse().observe(getViewLifecycleOwner(), new Observer<SwitchShiftApiResponse>() {
            @Override
            public void onChanged(SwitchShiftApiResponse switchShiftApiResponse) {
                try {
                    if (switchShiftApiResponse!=null){
                        if (switchShiftApiResponse.getSwitchShiftItems()!=null){
                            accessRole =checkAccessRole(switchShiftApiResponse.getSwitchShiftItems().get(0).getSttEsign(),switchShiftApiResponse.getSwitchShiftItems().get(0).getAccessRight()
                                    ,switchShiftFragmentBinding.constraintLayout8
                                    ,switchShiftFragmentBinding.constraintLayout9
                                    ,switchShiftFragmentBinding.llDelete
                                    ,switchShiftFragmentBinding.imgAdd);
                            switchShiftFragmentBinding.setItem(switchShiftApiResponse.getSwitchShiftItems().get(0));
                            if (switchShiftApiResponse.getSwitchShiftItems().get(0).getSwitchShiftDetails()!=null){
                                for (SwitchShiftDetail switchShiftDetail : switchShiftApiResponse.getSwitchShiftItems().get(0).getSwitchShiftDetails()){
                                    SwitchShift switchShift = new SwitchShift();
                                    switchShift.setDateBegin(simpleDateFormatSystem.parse(Util.formatDateSystem(switchShiftDetail.getFrLVDATE())));
                                    switchShift.setDateEnd(simpleDateFormatSystem.parse(Util.formatDateSystem(switchShiftDetail.getToLVDATE())));
                                    if (switchShiftDetail.getTimeMORN()!=null){
                                        switchShift.setContentMornig(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeMORN()));
                                        switchShift.setMorning(true);
                                    }
                                    if (switchShiftDetail.getTimeAFTR()!=null){
                                        switchShift.setAfternoon(true);
                                        switchShift.setContentAfternoon(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeAFTR()));
                                    }
                                    if (switchShiftDetail.getTimeEEVEN()!=null){
                                        switchShift.setAfternoon(true);
                                        switchShift.setContentEverning(mViewModel.getInfoTimeKeepping(switchShiftDetail.getTimeEEVEN()));
                                    }
                                    switchShift.setEmployeeDiLam(new Employee(switchShiftDetail.getEmplCDDT(),switchShiftDetail.getEmplCDNM()));
                                    switchShift.setEmployeeChamCong(new Employee(switchShiftDetail.getEmplRLTN(),switchShiftDetail.getEmplRLNM()));
                                    
                                    list.add(switchShift);
                                    switchShiftAdapter.notifyDataSetChanged();
                                }
                            }
                            getActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_progress_switch_shift, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                                    .commit();
                        }
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                
            }
        });
    }
    
    @Override
    public void onDeleteClick(int position) {
        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53),SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
            @Override
            public void onAccept() {
                list.remove(position);
                switchShiftAdapter.notifyDataSetChanged();
                SnackbarHelper.getInstance().snackBarIconSuccess(getView(),SharedPreferencesManager.getSystemLabel(52));
            }
            
            @Override
            public void onCancel() {
            
            }
        });
    }
    
    @Override
    public void onEditClick(int position) {
        positionEdit = position;
        Intent intent=  new Intent(getContext(), SwitchShiftInfoActivity.class);
        intent.putExtra(Constant.NAME_PUT_SWITCH_SHIFT,list.get(position));
        intent.putExtra(Constant.NAME_PUT_SWITCH_SHIFT_ACTION,Constant.ACTION_EDIT);
        startActivityForResult(intent,CODE_EDIT_SWITCH_SHIFT);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD_SWITCH_SHIFT && resultCode == Activity.RESULT_OK){
            SwitchShift switchShift = (SwitchShift) data.getSerializableExtra(Constant.NAME_PUT_SWITCH_SHIFT);
            if (switchShift!=null){
                list.add(switchShift);
                recyclerViewSwitchShift.post(new Runnable() {
                    @Override
                    public void run() {
                        switchShiftAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
        if (requestCode == CODE_EDIT_SWITCH_SHIFT && resultCode == Activity.RESULT_OK){
            SwitchShift switchShift = (SwitchShift) data.getSerializableExtra(Constant.NAME_PUT_SWITCH_SHIFT);
            
            if (switchShift!=null){
                list.set(positionEdit,switchShift);
                recyclerViewSwitchShift.post(new Runnable() {
                    @Override
                    public void run() {
                        switchShiftAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}