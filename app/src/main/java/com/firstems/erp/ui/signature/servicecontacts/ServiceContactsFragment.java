package com.firstems.erp.ui.signature.servicecontacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.firstems.erp.adapter.ImageIncludeAdapter;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.request.servicecontact.ServiceContactRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.lanh_vuc_lien_quan.LanhVucLienQuan;
import com.firstems.erp.api.model.response.servicecontacts.AddNewServiceContactResponse;
import com.firstems.erp.api.model.response.servicecontacts.ServiceContactsItem;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.LoadContentCallback;
import com.firstems.erp.callback.LoadListEmployeeCallback;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.SaveFileToLocalCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.ServiceContactsFragmentBinding;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.dialog.DialogUtils;
import com.firstems.erp.helper.file.GetFileHelper;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.employee.EmployeeActivity;
import com.firstems.erp.ui.shared.file.FileFragment;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.firstems.erp.ui.shared.file.FileFragment.listFilePath;

public class ServiceContactsFragment extends CommonFragment {
    
    private ServiceContactsViewModel mViewModel;
    private View view;
    private ServiceContactsFragmentBinding binding;
    private TextView txtTitle;
    private List<Bitmap> imageIncludeList;
    private ImageIncludeAdapter imageIncludeAdapter;
    private DialogUtils dialogUtils;
    private int CODE_ADD_NGUOI_NHAN = 3;
    private int CODE_ADD_NGUOI_THAM_KHAO = 4;
    private List<Employee> employeeListNhan;
    private List<Employee> employeeListThamKhao;
    private ArrayAdapter<LanhVucLienQuan> adapterRelatedFields;
    private List<LanhVucLienQuan> relatedFieldList;
    private SignatureItemApiResponse signatureItemApiResponse;
    private Date dateCreate, dateComplete;
    private AccessRole accessRole;
    private List<String> pathImage;
    private int upLoadStatus = 0;
    
    public ServiceContactsFragment() {
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.service_contacts_fragment, container, false);
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
                        binding.txtTitleDateComplete.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.txtTitleDateComplete.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void setText() {
        binding.txtTitleDateCreate.setText(SharedPreferencesManager.getSystemLabel(23));
        binding.txtTitleReceiver.setText(SharedPreferencesManager.getSystemLabel(68));
        binding.txtTitleListRefer.setText(SharedPreferencesManager.getSystemLabel(69));
        binding.txtTitleRelatedField.setText(SharedPreferencesManager.getSystemLabel(70));
        binding.txtTitlePurposeContact.setText(SharedPreferencesManager.getSystemLabel(71));
        binding.txtTitleContactContent.setText(SharedPreferencesManager.getSystemLabel(72));
        binding.txtTitleDateComplete.setText(SharedPreferencesManager.getSystemLabel(73));
        binding.txtTitleLocate.setText(SharedPreferencesManager.getSystemLabel(74));
        binding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
        binding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
    }
    private void addEvents() {
        binding.constraintLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeListNhan.size()>0){
                    if (employeeListThamKhao.size()>0){
                        if (!binding.edtPurposeContact.getText().toString().equals("")){
                            if (!binding.edtContactContent.getText().toString().equals("")){
                                if (!binding.edtLocate.getText().toString().equals("")){
                                    if (signatureItemApiResponse!=null){
                                        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(56), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                            @Override
                                            public void onAccept() {
                                                doUpdate();
                                                return;
                                            }
                                            
                                            @Override
                                            public void onCancel() {
                                            
                                            }
                                        });
                                    }
                                    else {
                                        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(56), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                            @Override
                                            public void onAccept() {
                                                doSave();
                                                return;
                                            }
                                            
                                            @Override
                                            public void onCancel() {
                                            
                                            }
                                        });
                                    }
                                }
                                else {
                                    showToastError(SharedPreferencesManager.getSystemLabel(77));
                                    return;
                                }
                            }
                            else {
                                showToastError(SharedPreferencesManager.getSystemLabel(78));
                                return;
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(79));
                            return;
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(80));
                        return;
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(81));
                    return;
                }
            }
        });
        binding.constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeListNhan.size()>0){
                    if (employeeListThamKhao.size()>0){
                        if (!binding.edtPurposeContact.getText().toString().equals("")){
                            if (!binding.edtContactContent.getText().toString().equals("")){
                                if (!binding.edtLocate.getText().toString().equals("")){
                                    if (signatureItemApiResponse!=null){
                                        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                            @Override
                                            public void onAccept() {
                                                progressdialog.show();
                                                CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                                                commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                                                commitDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                                                System.out.println(commitDocumentRequest.convertToJson());
                
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
                                                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                                                            }
                                                        }
                                                        else {
                                                            progressdialog.dismiss();
                                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                                                        }
                                                    }
                                                    @Override
                                                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                                                        progressdialog.dismiss();
                                                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                                                    }
                                                });
                                            }
            
                                            @Override
                                            public void onCancel() {
                
                                            }
                                        });
                                    }
                                    else {
                                        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                            @Override
                                            public void onAccept() {
                                                doAddAndCommit();
                                            }
            
                                            @Override
                                            public void onCancel() {
                
                                            }
                                        });
                                    }
                                }
                                else {
                                    showToastError(SharedPreferencesManager.getSystemLabel(77));
                                    return;
                                }
                            }
                            else {
                                showToastError(SharedPreferencesManager.getSystemLabel(78));
                                return;
                            }
                        }
                        else {
                            showToastError(SharedPreferencesManager.getSystemLabel(79));
                            return;
                        }
                    }
                    else {
                        showToastError(SharedPreferencesManager.getSystemLabel(80));
                        return;
                    }
                }
                else {
                    showToastError(SharedPreferencesManager.getSystemLabel(81));
                    return;
                }
                
            }
        });
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        binding.include4.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        binding.txtReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_ACTION_SELECT_EMPLOYEE,false);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_LIST_EMPLOYEE, (Serializable) employeeListNhan);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(75));
                startActivityForResult(intent,CODE_ADD_NGUOI_NHAN);
            }
        });
        binding.txtListRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_ACTION_SELECT_EMPLOYEE,false);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_LIST_EMPLOYEE, (Serializable) employeeListThamKhao);
                intent.putExtra(com.firstems.erp.common.Constant.NAME_PUT_TITLE_EMPLOYEE,SharedPreferencesManager.getSystemLabel(76));
                startActivityForResult(intent,CODE_ADD_NGUOI_THAM_KHAO);
            }
        });
        binding.layoutTimeDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.getInstance().showDialogSelectDate(System.currentTimeMillis(), 0, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateComplete = date;
                        binding.txtDateComplete.setText(simpleDateFormatDisplay.format(date));
                    }
                });
            }
        });
    }
    
    private void doAddAndCommit() {
        progressdialog.show();
        ServiceContactsItem itemRequest = new ServiceContactsItem();
        itemRequest.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        itemRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        itemRequest.setmCONTENT(binding.edtContactContent.getText().toString());
        itemRequest.setmPURPNME(binding.edtPurposeContact.getText().toString());
        itemRequest.setfISHDATE(simpleDateFormatSystem.format(dateComplete));
        itemRequest.setfISHPLCE(binding.edtLocate.getText().toString());
        itemRequest.seteMPLRECV(getEmplRecv());
        itemRequest.seteMPLREFR(getEmplRefer());
    
        LanhVucLienQuan lanhVucLienQuan = (LanhVucLienQuan) binding.spinerRelatedField.getSelectedItem();
        itemRequest.setdCMNSBCD(lanhVucLienQuan.getiTEMCODE());
    
        LanhVucLienQuan quan = (LanhVucLienQuan) binding.spinerRelatedField.getSelectedItem();
        itemRequest.setdCMNSBCD(quan.getiTEMCODE());
    
        List<ServiceContactsItem> contactsItems = new ArrayList<>();
        contactsItems.add(itemRequest);
    
        ServiceContactRequest serviceContactRequest = new ServiceContactRequest();
        serviceContactRequest.setServiceContactsItems(contactsItems);
    
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(serviceContactRequest),JsonObject.class);
    
        Log.d("JSON: ", jsonObject.toString());
        System.out.println(jsonObject);
    
        ApiServices.getInstance().addAddCommitLienHeCongVu(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewServiceContactResponse>() {
            @Override
            public void onResponse(Call<AddNewServiceContactResponse> call, Response<AddNewServiceContactResponse> response) {
                if (response.isSuccessful()){
                    AddNewServiceContactResponse addNewServiceContactResponse = response.body();
                    if (addNewServiceContactResponse.isRETNCODE()){
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFile(addNewServiceContactResponse.getRetuenValue().get(0).getKeyCode(),addNewServiceContactResponse.getRETNMSSG(),getListImage(), FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),addNewServiceContactResponse.getRETNMSSG());
                        }
                    
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
            public void onFailure(Call<AddNewServiceContactResponse> call, Throwable t) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
            }
        });
    }
    
    private void doUpdate() {
        progressdialog.show();
        
        ServiceContactsItem itemRequest = new ServiceContactsItem();
        itemRequest.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        itemRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        itemRequest.setmCONTENT(binding.edtContactContent.getText().toString());
        itemRequest.setmPURPNME(binding.edtPurposeContact.getText().toString());
        itemRequest.setfISHDATE(simpleDateFormatSystem.format(dateComplete));
        itemRequest.setfISHPLCE(binding.edtLocate.getText().toString());
        itemRequest.seteMPLRECV(getEmplRecv());
        itemRequest.seteMPLREFR(getEmplRefer());
        itemRequest.setmAINCODE(signatureItemApiResponse.getMainCode());
        itemRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
        
        LanhVucLienQuan quan = (LanhVucLienQuan) binding.spinerRelatedField.getSelectedItem();
        itemRequest.setdCMNSBCD(quan.getiTEMCODE());
        
        List<ServiceContactsItem> contactsItems = new ArrayList<>();
        contactsItems.add(itemRequest);
        
        ServiceContactRequest serviceContactRequest = new ServiceContactRequest();
        serviceContactRequest.setServiceContactsItems(contactsItems);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(serviceContactRequest),JsonObject.class);
        
        ApiServices.getInstance().editLienHeCongVu(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewServiceContactResponse>() {
            @Override
            public void onResponse(Call<AddNewServiceContactResponse> call, Response<AddNewServiceContactResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().isRETNCODE()){
                        deleteFile(response.body().getRetuenValue().get(0).getKeyCode());
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFile(response.body().getRetuenValue().get(0).getKeyCode(), response.body().getRETNMSSG(), getListImage(), FileFragment.fileIncludeList);
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
                    }
                    
                }
                else {
                    progressdialog.dismiss();
                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                }
            }
            
            @Override
            public void onFailure(Call<AddNewServiceContactResponse> call, Throwable t) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
            }
        });
    }
    
    private void deleteFile(String keyCode) {
        if (FileFragment.imageModelListRemove.size()>0){
            for (ImageModel imageModel : FileFragment.imageModelListRemove){
                ApiServices.getInstance().deleteAttactment(SharedPreferencesManager.getInstance().getPrefToken(),
                        signatureItemApiResponse.getDcmnCode(), keyCode, imageModel.getFileCode(), new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                System.out.println(response.body().getRETNMSSG());
                                System.out.println(response.message());
                                FileFragment.imageModelListRemove.clear();
                            }
            
                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        });
            }
        }
    }
    
    private void doSave() {
        progressdialog.show();
        
        ServiceContactsItem itemRequest = new ServiceContactsItem();
        itemRequest.setmAINDATE(simpleDateFormatSystem.format(dateCreate));
        itemRequest.setlCTNCODE(SharedPreferencesManager.getInstance().getPrefLctcode());
        itemRequest.setmCONTENT(binding.edtContactContent.getText().toString());
        itemRequest.setmPURPNME(binding.edtPurposeContact.getText().toString());
        itemRequest.setfISHDATE(simpleDateFormatSystem.format(dateComplete));
        itemRequest.setfISHPLCE(binding.edtLocate.getText().toString());
        itemRequest.seteMPLRECV(getEmplRecv());
        itemRequest.seteMPLREFR(getEmplRefer());
        
        LanhVucLienQuan lanhVucLienQuan = (LanhVucLienQuan) binding.spinerRelatedField.getSelectedItem();
        itemRequest.setdCMNSBCD(lanhVucLienQuan.getiTEMCODE());
        
        LanhVucLienQuan quan = (LanhVucLienQuan) binding.spinerRelatedField.getSelectedItem();
        itemRequest.setdCMNSBCD(quan.getiTEMCODE());
        
        List<ServiceContactsItem> contactsItems = new ArrayList<>();
        contactsItems.add(itemRequest);
        
        ServiceContactRequest serviceContactRequest = new ServiceContactRequest();
        serviceContactRequest.setServiceContactsItems(contactsItems);
        
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(serviceContactRequest),JsonObject.class);
        
        Log.d("JSON: ", jsonObject.toString());
        System.out.println(jsonObject);
        
        ApiServices.getInstance().addNewLienHeCongVu(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<AddNewServiceContactResponse>() {
            @Override
            public void onResponse(Call<AddNewServiceContactResponse> call, Response<AddNewServiceContactResponse> response) {
                if (response.isSuccessful()){
                    AddNewServiceContactResponse addNewServiceContactResponse = response.body();
                    if (addNewServiceContactResponse.isRETNCODE()){
                        if (FileFragment.listFilePath.size()>0 || FileFragment.fileIncludeList.size()>0){
                            upLoadFile(addNewServiceContactResponse.getRetuenValue().get(0).getKeyCode(),addNewServiceContactResponse.getRETNMSSG(),getListImage(), FileFragment.fileIncludeList);
                        }
                        else {
                            progressdialog.dismiss();
                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),addNewServiceContactResponse.getRETNMSSG());
                        }
                       
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
            public void onFailure(Call<AddNewServiceContactResponse> call, Throwable t) {
                progressdialog.dismiss();
                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
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
            ApiServices.getInstance().uploadFile(SharedPreferencesManager.getInstance().getPrefToken(), "LHCV", keyCode,
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
                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                System.out.println(response.message());
                            }
                    
                        }
                
                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            progressdialog.dismiss();
                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                            System.out.println(t.getMessage());
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
    
    private void checkUpload(){
        if (upLoadStatus == pathImage.size()){
            progressdialog.dismiss();
            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),"Tạo chứng từ thành công");
        }
        else if (upLoadStatus < 0){
            progressdialog.dismiss();
            showErrorDialog(SharedPreferencesManager.getSystemLabel(50)," Tạo chứng từ thất bại");
        }
    }
    private String getEmplRefer() {
        String s ="";
        for (int i=0; i< employeeListThamKhao.size();i++){
            Employee employee = employeeListThamKhao.get(i);
            if (i==employeeListThamKhao.size()-1){
                s+=employee.getItemCode();
            }
            else {
                s+=employee.getItemCode()+",";
            }
        }
        return s;
    }
    
    private String getEmplRecv() {
        String s ="";
        for (int i=0; i< employeeListNhan.size();i++){
            Employee employee = employeeListNhan.get(i);
            if (i==employeeListNhan.size()-1){
                s+=employee.getItemCode();
            }
            else {
                s+=employee.getItemCode()+",";
            }
        }
        return s;
    }
    
    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62),SharedPreferencesManager.getSystemLabel(63));
       
        showLoadingNonMessDialog();
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(com.firstems.erp.common.Constant.NAME_PUT_SIGNATURE);
        
        dialogUtils= new DialogUtils(getContext());
        
        employeeListNhan = new ArrayList<>();
        employeeListThamKhao= new ArrayList<>();
        txtTitle=binding.include4.findViewById(R.id.txtTitle);
        
        relatedFieldList= new ArrayList<>();
        adapterRelatedFields = new ArrayAdapter<LanhVucLienQuan>(getContext(),R.layout.spiner_item,relatedFieldList);
        adapterRelatedFields.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerRelatedField.setAdapter(adapterRelatedFields);
        
        if (signatureItemApiResponse==null){
            dateCreate = new Date(System.currentTimeMillis());
            binding.txtDateCreate.setText(simpleDateFormatDisplay.format(dateCreate));
            dateComplete = new Date(System.currentTimeMillis());
            binding.txtDateComplete.setText(simpleDateFormatDisplay.format(dateCreate));
        }
        binding.setIsEdit(true);
        pathImage = new ArrayList<>();
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ServiceContactsViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        
        mViewModel.getBitmapImage().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                imageIncludeList.add(bitmap);
                imageIncludeAdapter.notifyDataSetChanged();
            }
        });
        
        mViewModel.loadListRatedField(new LoadContentCallback() {
            @Override
            public void Loaded() {
                mViewModel.getListRelatedMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<LanhVucLienQuan>>() {
                    @Override
                    public void onChanged(List<LanhVucLienQuan> lanhVucLienQuans) {
                        adapterRelatedFields.clear();
                        adapterRelatedFields.addAll(lanhVucLienQuans);
                        adapterRelatedFields.notifyDataSetChanged();
                    }
                });
                if (signatureItemApiResponse!=null){
                    mViewModel.getDetailServiceContact(signatureItemApiResponse.getDcmnCode(),signatureItemApiResponse.getKeyCode());
                    mViewModel.loadListEmployee(new LoadListEmployeeCallback() {
                        @Override
                        public void onLoaded(List<Employee> employees) {
                            mViewModel.getContactsItemMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ServiceContactsItem>() {
                                @Override
                                public void onChanged(ServiceContactsItem item) {
                                    try {
                                        accessRole = checkAccessRole(item.getsTTESIGN(),item.getaCCERGHT(),binding.constraintLayout8
                                                ,binding.constraintLayout9,binding.llDelete,null);
                                        binding.setIsEdit(accessRole.isEdit());
                                        dateCreate = (item.getmAINDATE() != null ? simpleDateFormatSystem.parse(Util.formatDateSystem(item.getmAINDATE())) : new Date(System.currentTimeMillis()));
                                        dateComplete = (item.getfISHDATE() != null ? simpleDateFormatSystem.parse(Util.formatDateSystem(item.getfISHDATE())) : new Date(System.currentTimeMillis()));
                                        binding.txtDateCreate.setText(simpleDateFormatDisplay.format(dateCreate));
                                        binding.txtDateComplete.setText(simpleDateFormatDisplay.format(dateComplete));
                                        
                                        String emplRecvText = "";
                                        String empReferText = "";
                                        for (Employee employee : employees){
                                            if (item.geteMPLRECV().contains(employee.getItemCode())){
                                                emplRecvText+=employee.getItemName()+",";
                                                employeeListNhan.add(employee);
                                            }
                                            if (item.geteMPLREFR().contains(employee.getItemCode())){
                                                empReferText += employee.getItemName()+",";
                                                employeeListThamKhao.add(employee);
                                            }
                                        }
                                        binding.txtReceiver.setText(emplRecvText);
                                        binding.txtListRefer.setText(empReferText);
                                        binding.edtPurposeContact.setText(item.getmPURPNME() != null ? item.getmPURPNME() : "");
                                        binding.edtContactContent.setText(item.getmCONTENT() !=null ? item.getmCONTENT() : "");
                                        binding.edtLocate.setText(item.getfISHPLCE() != null ? item.getfISHPLCE() : "");
                                        
                                        for (int i = 0;i < relatedFieldList.size();i++){
                                            LanhVucLienQuan quan = relatedFieldList.get(i);
                                            if (quan.getiTEMCODE().equals(item.getdCMNSBCD())){
                                                binding.spinerRelatedField.setSelection(i);
                                                break;
                                            }
                                        }
                                        if (item.getsTTESIGN()<=0){
                                            FileFragment fileFragment = new FileFragment(item.getDocumentFiles());
                                            fileFragment.setEditable(accessRole.isEdit());
                                            System.out.println("Edit: "+accessRole.isEdit());
                                            getActivity().getSupportFragmentManager()
                                                    .beginTransaction()
                                                    .replace(R.id.frame_file_include, fileFragment)
                                                    .commit();
                                        }
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.frame_reviews_progress, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                                                .commit();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    binding.txtDateComplete.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingNonMessDialog.dismiss();
                                        }
                                    },700);
                                }
                            });
                        }
                    });
                }
                else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_file_include, new FileFragment(new ArrayList<>()))
                            .commit();
                    binding.txtDateComplete.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingNonMessDialog.dismiss();
                        }
                    },700);
                }
            }
        });
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_ADD_NGUOI_NHAN && resultCode == RESULT_OK){
            List<Employee> employees = (List<Employee>) data.getSerializableExtra(com.firstems.erp.common.Constant.NAME_PUT_LIST_EMPLOYEE);
            employeeListNhan.clear();
            binding.txtReceiver.setText("");
            if (employees!=null){
                if (employees.size()>0){
                    employeeListNhan.addAll(employees);
                    String s ="";
                    for (Employee employee : employees){
                        s+=employee.getItemName()+", ";
                    }
                    binding.txtReceiver.setText(s);
                }
            }
        }
        if (requestCode == CODE_ADD_NGUOI_THAM_KHAO && resultCode == RESULT_OK){
            List<Employee> employees = (List<Employee>) data.getSerializableExtra(com.firstems.erp.common.Constant.NAME_PUT_LIST_EMPLOYEE);
            employeeListThamKhao.clear();
            binding.txtListRefer.setText("");
            if (employees!=null){
                if (employees.size()>0){
                    employeeListThamKhao.addAll(employees);
                    String s ="";
                    for (Employee employee : employees){
                        s+=employee.getItemName()+", ";
                    }
                    binding.txtListRefer.setText(s);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}