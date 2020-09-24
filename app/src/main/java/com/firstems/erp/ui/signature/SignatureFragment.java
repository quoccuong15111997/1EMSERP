package com.firstems.erp.ui.signature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import com.firstems.erp.R;
import com.firstems.erp.adapter.SignatuneListAdapter;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.callback.LoadSignatureDataCallback;
import com.firstems.erp.callback.OnAddNewSignatureCallback;
import com.firstems.erp.callback.SignatureItemClickCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.SignatureFragmentBinding;
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

import java.util.HashMap;
import java.util.List;

public class SignatureFragment extends CommonFragment {
    
    private SignatureViewModel mViewModel;
    private SignatureFragmentBinding signatureFragmentBinding;
    private View view;
    private SignatuneListAdapter signatuneListAdapter;
    private TextView txtTitle;
    private BackToHomeCallback backToHomeCallback;
    private HashMap<String, List<SignatureItemApiResponse>> hashMap;
    private int CODE_OPEN_FILTER = 121;
    private int CODE_OPEN_SIGNATURE = 1211;
    private FilterModel filterModel;
    
    public SignatureFragment(BackToHomeCallback backToHomeCallback) {
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
        showLoadingNonMessDialog();
        addControls();
        addEvents();
        return view;
    }
    
    private void addEvents() {
        signatureFragmentBinding.fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), FilterSignatureActivity.class);
                intent.putExtra(Constant.NAME_PUT_FILTER_MODEL, filterModel);
                startActivityForResult(intent, CODE_OPEN_FILTER);
            }
        });
        signatureFragmentBinding.include.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeCallback.onBackPress();
            }
        });
    }
    
    private void addControls() {
        txtTitle = signatureFragmentBinding.include.findViewById(R.id.txtTitle);
        hashMap= new HashMap<>();
        signatuneListAdapter= new SignatuneListAdapter(hashMap, new OnAddNewSignatureCallback() {
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
        }, new SignatureItemClickCallback() {
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
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        
        signatureFragmentBinding.recycleSignature.setAdapter(signatuneListAdapter);
        signatureFragmentBinding.recycleSignature.setLayoutManager(linearLayoutManager);
    
        filterModel = new FilterModel(SysConfig.createDateLoadSign().get(0),SysConfig.createDateLoadSign().get(1),true,true,true);
        
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
        mViewModel = ViewModelProviders.of(this).get(SignatureViewModel.class);
        
        mViewModel.getHashMapSignature().observe(getViewLifecycleOwner(), new Observer<HashMap<String, List<SignatureItemApiResponse>>>() {
            @Override
            public void onChanged(HashMap<String, List<SignatureItemApiResponse>> stringListHashMap) {
                    hashMap.putAll(stringListHashMap);
                    signatureFragmentBinding.recycleSignature.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingNonMessDialog.dismiss();
                            signatuneListAdapter.notifyDataSetChanged();
                        }
                    },700);
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
        //mViewModel.loadDataSignature(filterModel);
       if (mViewModel!=null){
           mViewModel.loadDataSignatureResume(filterModel, new LoadSignatureDataCallback() {
               @Override
               public void onLoaded(HashMap<String, List<SignatureItemApiResponse>> hashMap) {
                   signatuneListAdapter.setData(hashMap);
                   loadingNonMessDialog.dismiss();
               }
           });
       }
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
        mViewModel.loadDataSignature(filterModel);
    }
    
    /*private void doFilter(FilterModel filterModel) {
        progressdialog.show();
        hashMap.clear();
        System.out.println("Done: "+filterModel.isDone());
        System.out.println("Sign: "+filterModel.isWaitsignature());
        System.out.println("approved: "+filterModel.isWaitApproved());
        HashMap<String, List<SignatureItemApiResponse>> hashMapFilter = new HashMap<>();
        Set<String> setKey = mViewModel.getHashMapSignature().getValue().keySet();
        for (String key : setKey){
            List<Integer> indexsIsDone = new ArrayList<>();
            List<Integer> indexsIsSign = new ArrayList<>();
            List<Integer> indexsIsApproved = new ArrayList<>();
            List<SignatureItemApiResponse> signatureItemApiResponses = mViewModel.getHashMapSignature().getValue().get(key);
            List<SignatureItemApiResponse> signatureItemFilterList = new ArrayList<>();
            for (int i = 0; i < signatureItemApiResponses.size();i++){
                SignatureItemApiResponse response = signatureItemApiResponses.get(i);
                if (response.getsTTESIGN() == 0){
                    indexsIsSign.add(i);
                }
                else if (response.getsTTESIGN() > 0 && response.getsTTESIGN() < 100){
                    indexsIsApproved.add(i);
                }
                else if (response.getsTTESIGN() >= 100){
                    indexsIsDone.add(i);
                }
            }
            List<Integer> integerListAll = new ArrayList<>();
            if (filterModel.isDone()){
                integerListAll.addAll(indexsIsDone);
            }
            if (filterModel.isWaitApproved()){
                integerListAll.addAll(indexsIsApproved);
            }
            if (filterModel.isWaitsignature()){
                integerListAll.addAll(indexsIsSign);
            }
            Set<Integer> integerSetList = new HashSet<>(integerListAll);
            integerListAll.clear();
            integerListAll.addAll(integerSetList);
            for (Integer z : integerListAll){
                signatureItemFilterList.add(signatureItemApiResponses.get(z));
            }
           if (signatureItemFilterList.size() > 0 ){
               hashMapFilter.put(key,signatureItemFilterList);
           }
        }
        hashMap.clear();
        hashMap.putAll(hashMapFilter);
        signatureFragmentBinding.recycleSignature.post(new Runnable() {
            @Override
            public void run() {
                signatuneListAdapter.notifyDataSetChanged();
                progressdialog.dismiss();
            }
        });
    }*/
}