package com.firstems.erp.ui.signature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.signature.SignatureDiffAdapter;
import com.firstems.erp.adapter.signature.SignatureModel;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.callback.DialogSignatureCallback;
import com.firstems.erp.callback.LoadSignatureDataDiffListCallback;
import com.firstems.erp.callback.OnAddNewSignatureCallback;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.callback.SignatureItemClickCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.SignatureFragmentBinding;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.model.FilterModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.system.SysConfig;
import com.firstems.erp.ui.signature.advanceproposalform.AdvanceProposalFormActivity;
import com.firstems.erp.ui.signature.askpermission.AskPermissionActivity;
import com.firstems.erp.ui.signature.billpaymentrequest.BillPaymentRequestActivity;
import com.firstems.erp.ui.signature.businessregistration.BusinessRegistrationActivity;
import com.firstems.erp.ui.signature.defaultsignature.DefaultSignatureActivity;
import com.firstems.erp.ui.signature.filtersignature.FilterSignatureActivity;
import com.firstems.erp.ui.signature.servicecontacts.ServiceContactsActivity;
import com.firstems.erp.ui.signature.switchshift.SwitchShiftActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SignatureGirdDiffFragment extends CommonFragment {
    
    private SignatureGirdDiffViewModel mViewModel;
    private SignatureFragmentBinding signatureFragmentBinding;
    private View view;
    private SignatureDiffAdapter signatuneListAdapter;
    private TextView txtTitle;
    private BackToHomeCallback backToHomeCallback;
    private HashMap<String, List<SignatureItemApiResponse>> hashMap;
    private int CODE_OPEN_FILTER = 121;
    private int CODE_OPEN_SIGNATURE = 1211;
    private FilterModel filterModel;
    private List<SignatureModel> modelList;
    private int flagLoad  = 0;
    
    public SignatureGirdDiffFragment(BackToHomeCallback backToHomeCallback) {
        this.backToHomeCallback = backToHomeCallback;
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        signatureFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.signature_fragment, container, false);
        view = signatureFragmentBinding.getRoot();
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83),SharedPreferencesManager.getSystemLabel(63));
        // progressdialog.show();
        addControls();
        addEvents();
        return view;
    }
    
    private void addEvents() {
        signatureFragmentBinding.fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent= new Intent(getActivity(), FilterSignatureActivity.class);
                intent.putExtra(Constant.NAME_PUT_FILTER_MODEL, filterModel);
                startActivityForResult(intent, CODE_OPEN_FILTER);*/
                showSortDialog(new DialogSignatureCallback() {
                    @Override
                    public void onDoneClick(FilterModel filterModel) {
                        doFilter(filterModel);
                    }
                });
            }
        });
        signatureFragmentBinding.include.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeCallback.onBackPress();
            }
        });
        signatuneListAdapter.setOnAddNewSignatureCallback(new OnAddNewSignatureCallback() {
            @Override
            public void onAddClick(SignatureItemApiResponse model) {
                switch (model.getDcmnCode()){
                    case "HDXDC" :{
                        openNewActivity(SwitchShiftActivity.class);
                        break;
                    }
                    case "HDXNP" :{
                        openNewActivity(AskPermissionActivity.class);
                        break;
                    }
                    case "LHCV": {
                        openNewActivity(ServiceContactsActivity.class);
                        break;
                    }
                    case "PDKCT":{
                        openNewActivity(BusinessRegistrationActivity.class);
                        break;
                    }
                    case "PHDNC":{
                        openNewActivity(BillPaymentRequestActivity.class);
                        break;
                    }
                    case "PHTAM": {
                        openNewActivity(AdvanceProposalFormActivity.class);
                        break;
                    }
                }
            }
        });
        signatuneListAdapter.setSignatureItemClickCallback(new SignatureItemClickCallback() {
            @Override
            public void ItemClick(int position, SignatureItemApiResponse signatureItemApiResponse) {
                switch (signatureItemApiResponse.getDcmnCode()){
                    case "HDXDC" :{
                        openNewActivityWithModel(SwitchShiftActivity.class,signatureItemApiResponse);
                        break;
                    }
                    case "HDXNP" :{
                        openNewActivityWithModel(AskPermissionActivity.class,signatureItemApiResponse);
                        break;
                    }
                    case "LHCV": {
                        openNewActivityWithModel(ServiceContactsActivity.class,signatureItemApiResponse);
                        break;
                    }
                    case "PDKCT":{
                        openNewActivityWithModel(BusinessRegistrationActivity.class,signatureItemApiResponse);
                        break;
                    }
                    case "PHDNC":{
                        openNewActivityWithModel(BillPaymentRequestActivity.class,signatureItemApiResponse);
                        break;
                    }
                    case "PHTAM": {
                        openNewActivityWithModel(AdvanceProposalFormActivity.class,signatureItemApiResponse);
                        break;
                    }
                    default: openNewActivityWithModel(DefaultSignatureActivity.class,signatureItemApiResponse);
                }
            }
        });
    }
    
    private void showSortDialog(DialogSignatureCallback dialogSignatureCallback){
        DialogPlus dialog = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_filter))
                .setGravity(Gravity.CENTER)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setInAnimation(R.anim.item_animation_float)
                .setMargin(20, 20, 20, 20)
                .setPadding(10,10,10,10)
                .setContentBackgroundResource(0)
                .create();
        View view = dialog.getHolderView();
        final Date[] dateBegin = {SysConfig.createDateLoadSign().get(0)};
        final Date[] dateEnd = {SysConfig.createDateLoadSign().get(1)};
        
        TextView txtDateBegin, txtDateEnd;
        txtDateBegin = view.findViewById(R.id.txtDateBegin);
        txtDateEnd = view.findViewById(R.id.txtDateEnd);
        txtDateBegin.setText(dateBegin[0] !=null ? simpleDateFormatDisplay.format(dateBegin[0]) : "");
        txtDateEnd.setText(dateEnd[0] !=null ? simpleDateFormatDisplay.format(dateEnd[0]) : "");
        
        CheckBox chkChuaTrinhKy,chkChoDuyet,chkHoanTat;
        chkChoDuyet = view.findViewById(R.id.chkChoDuyet);
        chkChuaTrinhKy = view.findViewById(R.id.chkChuaTrinhKy);
        chkHoanTat = view.findViewById(R.id.chkHoanTat);
        
        chkChuaTrinhKy.setChecked(SharedPreferencesManager.getInstance().getWaitSignature());
        chkChoDuyet.setChecked(SharedPreferencesManager.getInstance().getWaitAppreoved());
        chkHoanTat.setChecked(SharedPreferencesManager.getInstance().getCompleteSignature());
        
        Button btnDone = view.findViewById(R.id.btnSort);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterModel filterModel = new FilterModel();
                filterModel.setBeginDate(dateBegin[0]);
                filterModel.setEndDate(dateEnd[0]);
                filterModel.setDone(chkHoanTat.isChecked());
                filterModel.setWaitApproved(chkChoDuyet.isChecked());
                filterModel.setWaitsignature(chkChuaTrinhKy.isChecked());
                
                dialogSignatureCallback.onDoneClick(filterModel);
                if (dialog!=null){
                    dialog.dismiss();
                }
            }
        });
        ConstraintLayout layoutFrom, layoutTo;
        layoutFrom = view.findViewById(R.id.layoutDateForm);
        layoutTo = view.findViewById(R.id.layoutDateEnd);
        layoutFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = 0;
                long maxDate = dateEnd[0].getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin[0] = date;
                        txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin[0]));
                    }
                });
            }
        });
        txtDateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = 0;
                long maxDate = dateEnd[0].getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin[0] = date;
                        txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin[0]));
                    }
                });
            }
        });
        layoutTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long maxDate = 0;
                long minDate = dateBegin[0].getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd[0] = date;
                        txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd[0]));
                    }
                });
            }
        });
        txtDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long maxDate = 0;
                long minDate = dateBegin[0].getTime();
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd[0] = date;
                        txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd[0]));
                    }
                });
            }
        });
        dialog.show();
    }
    
    private void addControls() {
        txtTitle = signatureFragmentBinding.include.findViewById(R.id.txtTitle);
        modelList = new ArrayList<>();
        signatuneListAdapter= new SignatureDiffAdapter(modelList);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        signatureFragmentBinding.recycleSignature.setAdapter(signatuneListAdapter);
        signatureFragmentBinding.recycleSignature.setLayoutManager(linearLayoutManager);
        
        filterModel = new FilterModel(SysConfig.createDateLoadSign().get(0),SysConfig.createDateLoadSign().get(1),
                SharedPreferencesManager.getInstance().getWaitSignature(),
                SharedPreferencesManager.getInstance().getWaitAppreoved(),
                SharedPreferencesManager.getInstance().getCompleteSignature());
        
        String titleValue = SharedPreferencesManager.getSystemLabel(19) + " "+simpleDateFormatDisplay.format(filterModel.getBeginDate())+" "+ SharedPreferencesManager.getSystemLabel(20) + " " +simpleDateFormatDisplay.format(filterModel.getEndDate());
        
        txtTitle.setText(titleValue);
    }
    
    private void openNewActivityWithModel(Class aClass, SignatureItemApiResponse signatureItemApiResponse) {
        Intent intent = new Intent(getContext(),aClass);
        intent.putExtra(Constant.NAME_PUT_SIGNATURE,signatureItemApiResponse);
        System.out.println("DCMN: "+signatureItemApiResponse.getDcmnCode()+" keyCode: "+signatureItemApiResponse.getKeyCode());
        startActivityForResult(intent,CODE_OPEN_SIGNATURE);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignatureGirdDiffViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getSignatureModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<SignatureModel>>() {
            @Override
            public void onChanged(List<SignatureModel> signatureModels) {
                signatuneListAdapter.setSignatureModelList(signatureModels);
                loadingNonMessDialog.dismiss();
            }
        });
    }
    private void openNewActivity(Class aClass){
        Intent intent = new Intent(getContext(),aClass);
        startActivity(intent);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        filterModel = new FilterModel(SysConfig.createDateLoadSign().get(0),SysConfig.createDateLoadSign().get(1),
                SharedPreferencesManager.getInstance().getWaitSignature(),
                SharedPreferencesManager.getInstance().getWaitAppreoved(),
                SharedPreferencesManager.getInstance().getCompleteSignature());
        //mViewModel.loadDataSignature(filterModel);
        
        
        showLoadingNonMessDialog();
        
        
        mViewModel.loadDataSignatureResume(filterModel, new LoadSignatureDataDiffListCallback() {
            @Override
            public void onLoaded(List<SignatureModel> signatureModels) {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingNonMessDialog.isShowing()){
                            loadingNonMessDialog.dismiss();
                            signatuneListAdapter.setSignatureModelList(signatureModels);
                        }
                    }
                },1000);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_OPEN_FILTER && resultCode == Activity.RESULT_OK){
            filterModel = (FilterModel) data.getSerializableExtra(Constant.NAME_PUT_FILTER_MODEL);
            doFilter(filterModel);
            String titleValue = "Trình ký từ "+simpleDateFormatDisplay.format(filterModel.getBeginDate())+" đến "+simpleDateFormatDisplay.format(filterModel.getEndDate());
            txtTitle.setText(titleValue);
        }
        if (requestCode == CODE_OPEN_SIGNATURE && resultCode == Activity.RESULT_OK){
            String dcmnCode = data.getStringExtra(Constant.NAME_PUT_DCMN_CODE_SIGNATURE);
            int position = data.getIntExtra(Constant.NAME_PUT_POSITION_SINATURE,0);
        }
    }
    
    private void doFilter(FilterModel filterModel) {
        showLoadingNonMessDialog();
        SharedPreferencesManager.getInstance().setWaitAppreoved(filterModel.isWaitApproved());
        SharedPreferencesManager.getInstance().setWaitSignature(filterModel.isWaitsignature());
        SharedPreferencesManager.getInstance().setCompleteSignature(filterModel.isDone());
        mViewModel.loadDataSignature(filterModel);
    }
}