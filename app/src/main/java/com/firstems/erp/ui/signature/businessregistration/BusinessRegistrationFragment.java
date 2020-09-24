package com.firstems.erp.ui.signature.businessregistration;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.adapter.BussinessRegistrationAdapter;
import com.firstems.erp.api.model.request.CommitDocumentRequest;
import com.firstems.erp.api.model.request.DeleteDocumentRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.locationtype.LocationType;
import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.api.model.response.province.Province;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegistrationApiResponse;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegstDetail;
import com.firstems.erp.api.model.response.signature.bussiness.BussinessRegstItem;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.GetDataTimeKeepingTCCallback;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.data.ConvertJsonCallback;
import com.firstems.erp.callback.data.DataApiCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.data.DataConvertProvider;
import com.firstems.erp.data.DataNetworkProvider;
import com.firstems.erp.databinding.BusinessRegistrationFragmentBinding;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.snackbar.SnackbarHelper;
import com.firstems.erp.helper.validation.ValidationData;
import com.firstems.erp.model.business.Business;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;
import com.firstems.erp.ui.signature.businessregistration.info.BusinessRegistrationInfoActivity;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessRegistrationFragment extends CommonFragment implements BussinessRegistrationAdapter.OnItemClick {

    private BusinessRegistrationViewModel mViewModel;
    private BusinessRegistrationFragmentBinding binding;
    private TextView txtTitle;
    private Date dateBegin, dateEnd, dateCreate;
    private int CODE_ADD_BUSSINESS_REGISTRATION = 100;
    private int CODE_EDIT_BUSSINESS_REGISTRATION = 200;
    private final static int LOADING_DURATION = 1000;
    private BussinessRegistrationAdapter registrationAdapter;
    private List<Business> businessList;
    private RecyclerView recyclerView;
    private int positionEdit =0;
    private SignatureItemApiResponse signatureItemApiResponse;
    private List<TimekeepingTypeCT> timekeepingTypeCTList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.business_registration_fragment, container, false);

        setAminHeader();
        loadingAndDisplayContent();
        initText();
        addControls();
        addEvents();

        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleDateCreate.setText(SharedPreferencesManager.getSystemLabel(23));
        binding.txtTitleDateBegin.setText(SharedPreferencesManager.getSystemLabel(32));
        binding.txtTitleDateEnd.setText(SharedPreferencesManager.getSystemLabel(33));
        binding.txtTitleNumberDay.setText(SharedPreferencesManager.getSystemLabel(44));
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(86));
        binding.txtTitleContent.setText(SharedPreferencesManager.getSystemLabel(87));
        binding.chkVeMayBay.setText(SharedPreferencesManager.getSystemLabel(88));
        binding.chkVeTauLua.setText(SharedPreferencesManager.getSystemLabel(89));
        binding.chkHotel.setText(SharedPreferencesManager.getSystemLabel(92));
        binding.chkXeCongTacTronChuyen.setText(SharedPreferencesManager.getSystemLabel(90));
        binding.chkPhuongTienCongTacKhac.setText(SharedPreferencesManager.getSystemLabel(91));
        binding.chkVisa.setText(SharedPreferencesManager.getSystemLabel(93));
        binding.chkTamUng.setText(SharedPreferencesManager.getSystemLabel(94));
        binding.chkVeTauThuy.setText(SharedPreferencesManager.getSystemLabel(95));
        binding.txtTitleDate.setText(SharedPreferencesManager.getSystemLabel(26));
        binding.txtTitleSpecies.setText(SharedPreferencesManager.getSystemLabel(96));
        binding.textView5.setText(SharedPreferencesManager.getSystemLabel(29));
        binding.txtTrinhKi.setText(SharedPreferencesManager.getSystemLabel(8));
        binding.textView54.setText(SharedPreferencesManager.getSystemLabel(97));
    }
    
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.txtTitleInfo.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.txtTitleInfo.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void loadingAndDisplayContent() {
        initProgressDialogLoad(SharedPreferencesManager.getSystemLabel(83),SharedPreferencesManager.getSystemLabel(63));
        progressdialogLoad.show();
        initComponent();
    }

    private void initComponent() {
        binding.nestedScrollView.setVisibility(View.VISIBLE);
    }

    private void addEvents() {
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), BusinessRegistrationInfoActivity.class);
                startActivityForResult(intent, CODE_ADD_BUSSINESS_REGISTRATION);
            }
        });
        binding.include5.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        binding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateBegin();
            }
        });
        binding.layoutDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateEnd();
            }
        });
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signatureItemApiResponse!=null){
                    progressdialog.show();
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
        binding.constraintLayout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (signatureItemApiResponse==null){
                    if (!ValidationData.isBlank(binding.edtInfo)){
                        if (binding.chkVeMayBay.isChecked()
                                || binding.chkVeTauLua.isChecked()
                                || binding.chkHotel.isChecked()
                                || binding.chkXeCongTacTronChuyen.isChecked()
                                || binding.chkPhuongTienCongTacKhac.isChecked()
                                || binding.chkVisa.isChecked()
                                ||binding.chkTamUng.isChecked()){
                            if (businessList.size() != 0){
                                if (validDetail()){
                                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(56), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                        @Override
                                        public void onAccept() {
                                            progressdialog.show();
                                            doSaveBussinessResgt();
                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    });
                                }
                                else{
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(48));
                                }
                            }
                            else {
                                progressdialog.dismiss();
                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(48));
                            }
                        }
                        else {
                            progressdialog.dismiss();
                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(84));
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(85));
                    }
                }
                else {
                    if (!ValidationData.isBlank(binding.edtInfo)){
                        if (binding.chkVeMayBay.isChecked()
                                || binding.chkVeTauLua.isChecked()
                                || binding.chkHotel.isChecked()
                                || binding.chkXeCongTacTronChuyen.isChecked()
                                || binding.chkPhuongTienCongTacKhac.isChecked()
                                || binding.chkVisa.isChecked()
                                ||binding.chkTamUng.isChecked()){
                            if (businessList.size() != 0){
                                if (validDetail()){
                                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(57), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                        @Override
                                        public void onAccept() {
                                            progressdialog.show();
                                            doUpdateBussinessResgt();
                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    });

                                }
                                else {
                                    progressdialog.dismiss();
                                    showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(48));
                                }
                            }
                            else {
                                progressdialog.dismiss();
                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(48));
                            }
                        }
                        else {
                            progressdialog.dismiss();
                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(84));
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(85));
                    }
                }
            }
        });
        binding.constraintLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (signatureItemApiResponse!=null){
                    // Trình ký chứng từ đã tồn tại
                    showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(8), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                        @Override
                        public void onAccept() {
                            progressdialog.show();
                            CommitDocumentRequest commitDocumentRequest = new CommitDocumentRequest();
                            commitDocumentRequest.setDcmnCode(signatureItemApiResponse.getDcmnCode());
                            commitDocumentRequest.setKeyCode(signatureItemApiResponse.getKeyCode());
                            ApiServices
                                    .getInstance()
                                    .commitDocument(SharedPreferencesManager.getInstance().getPrefToken(), commitDocumentRequest.convertToJson(), new Callback<ApiResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                            if (response.isSuccessful()){
                                                ApiResponse  apiResponse =response.body();
                                                if (apiResponse.isRETNCODE()){
                                                    progressdialog.dismiss();
                                                    showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                                }
                                            }
                                            else {
                                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),response.body().getRETNMSSG());
                                                progressdialog.dismiss();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(64));
                                            progressdialog.dismiss();
                                        }
                                    });
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
                else {
                    // Tạo mới và trình ký chứng từ
                    if (!ValidationData.isBlank(binding.edtInfo)){
                        if (binding.chkVeMayBay.isChecked()
                                || binding.chkVeTauLua.isChecked()
                                || binding.chkHotel.isChecked()
                                || binding.chkXeCongTacTronChuyen.isChecked()
                                || binding.chkPhuongTienCongTacKhac.isChecked()
                                || binding.chkVisa.isChecked()
                                ||binding.chkTamUng.isChecked()){
                            if (businessList.size() != 0){
                                showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(58), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
                                    @Override
                                    public void onAccept() {
                                        progressdialog.show();
                                        doSaveAndCommitBussinessResgt();
                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                            else {
                                progressdialog.dismiss();
                                showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(48));
                            }
                        }
                        else {
                            progressdialog.dismiss();
                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(84));
                        }
                    }
                    else {
                        progressdialog.dismiss();
                        showErrorDialog(SharedPreferencesManager.getSystemLabel(50),SharedPreferencesManager.getSystemLabel(85));
                    }
                }
            }
        });
    }

    private boolean validDetail() {
        int sumDay = 0;
        for (Business business : businessList){
            long numberDay = (business.getDateEnd().getTime() - business.getDateBegin().getTime())/ (1000 * 60 * 60 * 24) % 365;
            sumDay+=numberDay+1;
            System.out.println("number sum day ==== date begin: "+business.getDateBegin());
            System.out.println("number sum day ==== date end: "+business.getDateEnd());
        }
        System.out.println("number day: "+binding.txtNumberDay.getText().toString());

        System.out.println("day create ======== date begin :"+dateBegin);
        System.out.println("day create ======== date end :"+dateEnd);
        if (sumDay==Integer.parseInt(binding.txtNumberDay.getText().toString()))
            return true;
        return false;
    }

    private void doUpdateBussinessResgt() {
        List<BussinessRegstItem> regstItemList = new ArrayList<>();
        regstItemList.addAll(getModifiDataEdit());
        regstItemList.get(0).setMainCode(signatureItemApiResponse.getMainCode());
        DataNetworkProvider
                .getInstance()
                .editBussinessRegst(new DataApiCallback() {
                    @Override
                    public void onDataApi(String jsonAPI) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(jsonAPI, new ApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        ApiResponse apiResponse = (ApiResponse) obj;
                                        if (apiResponse.isRETNCODE()){
                                            progressdialog.dismiss();
                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                        }
                                        else {
                                            progressdialog.dismiss();
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onApiLoadFail(String mess) {

                    }
                },regstItemList);
    }
    private List<BussinessRegstItem> getModifiDataEdit(){
        BussinessRegstItem bussinessRegstItem = new BussinessRegstItem();
        bussinessRegstItem.setWordkDay(Integer.parseInt(binding.txtNumberDay.getText().toString()));
        bussinessRegstItem.setBeginDate(simpleDateFormatSystem.format(dateBegin));
        bussinessRegstItem.setEndDate(simpleDateFormatSystem.format(dateEnd));
        bussinessRegstItem.setMainDate(simpleDateFormatSystem.format(dateCreate));
        bussinessRegstItem.setComCode(SharedPreferencesManager.getInstance().getPrefCompcode());
        bussinessRegstItem.setLocateCode(SharedPreferencesManager.getInstance().getPrefLctcode());
        bussinessRegstItem.setEmployCode(SharedPreferencesManager.getInstance().getPrefEmpCode());
        bussinessRegstItem.setNoteText(binding.edtInfo.getText().toString());
        bussinessRegstItem.setMainCode(signatureItemApiResponse.getMainCode());
        bussinessRegstItem.setKeyCode(signatureItemApiResponse.getKeyCode());
        List<Character> servicesArr = new ArrayList<>();
        if (binding.chkVeMayBay.isChecked())
            servicesArr.add('0');
        if (binding.chkVeTauLua.isChecked())
            servicesArr.add('1');
        if (binding.chkHotel.isChecked())
            servicesArr.add('2');
        if (binding.chkXeCongTacTronChuyen.isChecked())
            servicesArr.add('3');
        if (binding.chkPhuongTienCongTacKhac.isChecked())
            servicesArr.add('4');
        if (binding.chkVisa.isChecked())
            servicesArr.add('5');
        if (binding.chkTamUng.isChecked())
            servicesArr.add('6');
        if (binding.chkVeTauThuy.isChecked())
            servicesArr.add('7');
        String service ="";
        for (int i =0;i<servicesArr.size();i++){
            Character character = servicesArr.get(i);
            if (i==servicesArr.size()-1){
                service+=character;
            }
            else {
                service+=(character+",");
            }
        }
        bussinessRegstItem.setServRequest(service);
        List<BussinessRegstDetail> regstDetails= new ArrayList<>();
        for (Business business : businessList){
            BussinessRegstDetail detai  = new BussinessRegstDetail();
            detai.setEmployCode(SharedPreferencesManager.getInstance().getPrefEmpCode());
            detai.setFromDate(simpleDateFormatSystem.format(business.getDateBegin()));
            detai.setToDate(simpleDateFormatSystem.format(business.getDateEnd()));
            detai.setMainCode(signatureItemApiResponse.getMainCode());
            if (business.getLocationType().getItemCode().equals("01")){
                detai.setWorkType(business.getLocationType().getItemCode());
                detai.setWorkPlace(business.getProvince().getItemCode());
            }
            else if (business.getLocationType().getItemCode().equals("02")){
                detai.setWorkType(business.getLocationType().getItemCode());
                detai.setWorkPlace(business.getNational().getItemCode());
            }
            if (business.getTimekeepingTypeCTMorn()!=null)
                detai.setContentMor(business.getTimekeepingTypeCTMorn().getItemCode());
            if (business.getTimekeepingTypeCTAfft()!=null)
                detai.setContentAfft(business.getTimekeepingTypeCTAfft().getItemCode());
            if (business.getTimekeepingTypeCTEvrn()!=null)
                detai.setContentEvr(business.getTimekeepingTypeCTEvrn().getItemCode());

            regstDetails.add(detai);
        }
        bussinessRegstItem.setDetailList(regstDetails);
        List<BussinessRegstItem> regstItemList = new ArrayList<>();
        regstItemList.add(bussinessRegstItem);

        return regstItemList;
    }
    private List<BussinessRegstItem> getModifiData(){
        BussinessRegstItem bussinessRegstItem = new BussinessRegstItem();
        bussinessRegstItem.setWordkDay(Integer.parseInt(binding.txtNumberDay.getText().toString()));
        bussinessRegstItem.setBeginDate(simpleDateFormatSystem.format(dateBegin));
        bussinessRegstItem.setEndDate(simpleDateFormatSystem.format(dateEnd));
        bussinessRegstItem.setMainDate(simpleDateFormatSystem.format(dateCreate));
        bussinessRegstItem.setComCode(SharedPreferencesManager.getInstance().getPrefCompcode());
        bussinessRegstItem.setLocateCode(SharedPreferencesManager.getInstance().getPrefLctcode());
        bussinessRegstItem.setEmployCode(SharedPreferencesManager.getInstance().getPrefEmpCode());
        bussinessRegstItem.setNoteText(binding.edtInfo.getText().toString());
        List<Character> servicesArr = new ArrayList<>();
        if (binding.chkVeMayBay.isChecked())
            servicesArr.add('0');
        if (binding.chkVeTauLua.isChecked())
            servicesArr.add('1');
        if (binding.chkHotel.isChecked())
            servicesArr.add('2');
        if (binding.chkXeCongTacTronChuyen.isChecked())
            servicesArr.add('3');
        if (binding.chkPhuongTienCongTacKhac.isChecked())
            servicesArr.add('4');
        if (binding.chkVisa.isChecked())
            servicesArr.add('5');
        if (binding.chkTamUng.isChecked())
            servicesArr.add('6');
        if (binding.chkVeTauThuy.isChecked())
            servicesArr.add('7');
        String service ="";
        for (int i =0;i<servicesArr.size();i++){
            Character character = servicesArr.get(i);
            if (i==servicesArr.size()-1){
                service+=character;
            }
            else {
                service+=(character+",");
            }
        }
        bussinessRegstItem.setServRequest(service);
        List<BussinessRegstDetail> regstDetails= new ArrayList<>();
        for (Business business : businessList){
            BussinessRegstDetail detai  = new BussinessRegstDetail();
            detai.setEmployCode(SharedPreferencesManager.getInstance().getPrefEmpCode());
            detai.setFromDate(simpleDateFormatSystem.format(business.getDateBegin()));
            detai.setToDate(simpleDateFormatSystem.format(business.getDateEnd()));
            if (business.getLocationType().getItemCode().equals("01")){
                detai.setWorkType(business.getLocationType().getItemCode());
                detai.setWorkPlace(business.getProvince().getItemCode());
            }
            else if (business.getLocationType().getItemCode().equals("02")){
                detai.setWorkType(business.getLocationType().getItemCode());
                detai.setWorkPlace(business.getNational().getItemCode());
            }
            if (business.getTimekeepingTypeCTMorn()!=null)
                detai.setContentMor(business.getTimekeepingTypeCTMorn().getItemCode());
            if (business.getTimekeepingTypeCTAfft()!=null)
                detai.setContentAfft(business.getTimekeepingTypeCTAfft().getItemCode());
            if (business.getTimekeepingTypeCTEvrn()!=null)
                detai.setContentEvr(business.getTimekeepingTypeCTEvrn().getItemCode());

            regstDetails.add(detai);
        }
        bussinessRegstItem.setDetailList(regstDetails);
        List<BussinessRegstItem> regstItemList = new ArrayList<>();
        regstItemList.add(bussinessRegstItem);

        return regstItemList;
    }
    private void doSaveAndCommitBussinessResgt() {
        List<BussinessRegstItem> regstItemList = new ArrayList<>();
        regstItemList.addAll(getModifiData());
        DataNetworkProvider
                .getInstance()
                .addNewAndCommitBussiness(new DataApiCallback() {
                    @Override
                    public void onDataApi(String jsonAPI) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(jsonAPI, new ApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        ApiResponse apiResponse = (ApiResponse) obj;
                                        if (apiResponse.isRETNCODE()){
                                            progressdialog.dismiss();
                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                        }
                                        else {
                                            progressdialog.dismiss();
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onApiLoadFail(String mess) {
                        System.out.println(mess);
                    }
                },regstItemList);
    }

    private void doSaveBussinessResgt() {
        List<BussinessRegstItem> regstItemList = new ArrayList<>();
        regstItemList.addAll(getModifiData());
        DataNetworkProvider
                .getInstance()
                .addNewBussinessRegistration(new DataApiCallback() {
                    @Override
                    public void onDataApi(String jsonAPI) {
                        DataConvertProvider
                                .getInstance()
                                .convertJsonToObject(jsonAPI, new BussinessRegistrationApiResponse(), new ConvertJsonCallback() {
                                    @Override
                                    public void onConvertSuccess(Object obj) {
                                        BussinessRegistrationApiResponse apiResponse = (BussinessRegistrationApiResponse) obj;
                                        if (apiResponse.isRETNCODE()){
                                            showSuccessDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                            progressdialog.dismiss();
                                        }
                                        else {
                                            progressdialog.dismiss();
                                            showErrorDialog(SharedPreferencesManager.getSystemLabel(50),apiResponse.getRETNMSSG());
                                            System.out.println(apiResponse.getRETNMSSG());
                                        }
                                    }
                                });
                    }
                    @Override
                    public void onApiLoadFail(String mess) {

                    }
                },regstItemList);
    }

    private void selectDateEnd() {
        long minDate = dateBegin.getTime();
        long maxDate = 0;
        DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
            @Override
            public void onDatePicker(Date date) {
                dateEnd = date;
                binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
                long numberDay = (dateEnd.getTime() - dateBegin.getTime())/ (1000 * 60 * 60 * 24) % 365;

                System.out.println("Date begin: "+dateBegin.getTime());
                System.out.println("Date end: "+dateEnd.getTime());
                binding.txtNumberDay.setText(String.valueOf(1 + numberDay));
            }
        });

    }

    private void selectDateBegin() {
        long minDate = 0;
        long maxDate = 0;
        if (!dateBegin.equals(dateEnd)){
            maxDate = dateEnd.getTime();
        }
        DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
            @Override
            public void onDatePicker(Date date) {
                dateBegin = date;
                if (dateBegin.getTime() > dateEnd.getTime()){
                    dateEnd = dateBegin;
                    binding.txtDateEnd.setText(simpleDateFormatDisplay.format(dateEnd));
                }
                binding.txtDateBegin.setText(simpleDateFormatDisplay.format(dateBegin));
                long numberDay = (dateEnd.getTime() - dateBegin.getTime())/ (1000 * 60 * 60 * 24) % 365;
                binding.txtNumberDay.setText(String.valueOf(1 + numberDay));
            }
        });
    }

    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(62), SharedPreferencesManager.getSystemLabel(63));
        showLoadingNonMessDialog();
        
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        if (signatureItemApiResponse != null) {
            binding.llDelete.setVisibility(View.VISIBLE);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_reviews_progress, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                    .commit();
        } else {
            binding.llDelete.setVisibility(View.INVISIBLE);
        }

        txtTitle = binding.include5.findViewById(R.id.txtTitle);

        timekeepingTypeCTList = new ArrayList<>();
        recyclerView = binding.recycleview;
        businessList = new ArrayList<>();
        registrationAdapter = new BussinessRegistrationAdapter(businessList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(registrationAdapter);
        recyclerView.setHasFixedSize(true);

        if (signatureItemApiResponse == null) {
            Date date = DateTime.now().withTimeAtStartOfDay().toDate();
            dateCreate = date;
            dateBegin = date;
            dateEnd = date;

            binding.txtNumberDay.setText("1");

            Business business = new Business();
            business.setMainDate(dateCreate);
            business.setDateEnd(dateEnd);
            business.setDateBegin(dateBegin);
            business.setWorkDay(1);
            binding.setItem(business);
            long numberDay = (dateEnd.getTime() - dateBegin.getTime())/ (1000 * 60 * 60 * 24) % 365;
            binding.txtNumberDay.setText(String.valueOf(1 + numberDay));
        }
        binding.setIsEditable(true);
        binding.lParentContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingNonMessDialog.dismiss();
            }
        }, 1000);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BusinessRegistrationViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
        mViewModel.loaDataKeppingType(new GetDataTimeKeepingTCCallback() {
            @Override
            public void onLoaded(List<TimekeepingTypeCT> timekeepingTypeCTS) {
                timekeepingTypeCTList.clear();
                timekeepingTypeCTList.addAll(timekeepingTypeCTS);
                System.out.println("TIME KEEPING: "+ timekeepingTypeCTList.size());
                if (signatureItemApiResponse!=null){
                    mViewModel.loadData(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode());
                    mViewModel.getLiveDataBussiness().observe(getViewLifecycleOwner(), new Observer<BussinessRegstItem>() {
                        @Override
                        public void onChanged(BussinessRegstItem bussinessRegstItem) {
                            if (bussinessRegstItem!=null){
                                AccessRole accessRole = checkAccessRole(bussinessRegstItem.getStteSign(),bussinessRegstItem.getAccessRight(),binding.constraintLayout11,binding.constraintLayout10,binding.llDelete,binding.imgAdd);
                                registrationAdapter.setEdit(accessRole.isEdit());
                                Business business = new Business();
                                business.setWorkDay(bussinessRegstItem.getWordkDay());
                                business.setNoteText(bussinessRegstItem.getNoteText());
                                business.setListService(bussinessRegstItem.getServRequest());
                                System.out.println(bussinessRegstItem.getBeginDate());
                                System.out.println(Util.formatDateSystem(bussinessRegstItem.getBeginDate()));
                                binding.setIsEditable(accessRole.isEdit());
                                try {
                                    business.setMainDate(simpleDateFormatSystem.parse(Util.formatDateSystem(bussinessRegstItem.getMainDate())));
                                    business.setDateBegin(simpleDateFormatSystem.parse(Util.formatDateSystem(bussinessRegstItem.getBeginDate())));
                                    business.setDateEnd(simpleDateFormatSystem.parse(Util.formatDateSystem(bussinessRegstItem.getEndDate())));
                                }
                                catch (Exception ex){
                       /* business.setDateEnd(new Date(System.currentTimeMillis()));
                        business.setDateBegin(new Date(System.currentTimeMillis()));
                        business.setMainDate(new Date(System.currentTimeMillis()));*/
                                    ex.printStackTrace();
                                }
                                dateBegin = business.getDateBegin();
                                dateEnd = business.getDateEnd();
                                dateCreate = business.getMainDate();
                                binding.setItem(business);

                                if (bussinessRegstItem.getDetailList()!=null){
                                    if (bussinessRegstItem.getDetailList().size ()>0){
                                        for (int i =0;i<bussinessRegstItem.getDetailList().size();i++){
                                            BussinessRegstDetail detail = bussinessRegstItem.getDetailList().get(i);

                                            try {
                                                Business businessInfo = new Business();
                                                businessInfo.setDateBegin(simpleDateFormatSystem.parse(Util.formatDateSystem(detail.getFromDate())));
                                                businessInfo.setDateEnd(simpleDateFormatSystem.parse(Util.formatDateSystem(detail.getToDate())));
                                                businessInfo.setTimekeepingTypeCTMorn(findTimeKeeping(detail.getContentMor()));
                                                businessInfo.setTimekeepingTypeCTAfft(findTimeKeeping(detail.getContentAfft()));
                                                businessInfo.setTimekeepingTypeCTEvrn(findTimeKeeping(detail.getContentEvr()));
                                                businessInfo.setLocationType(new LocationType(detail.getWorkType()));
                                                businessInfo.setNational(new National(detail.getWorkType()));
                                                businessInfo.setProvince(new Province(detail.getWorkPlace()));

                                                businessList.add(businessInfo);
                                                registrationAdapter.notifyDataSetChanged();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                            long numberDay = (dateEnd.getTime() - dateBegin.getTime())/ (1000 * 60 * 60 * 24) % 365;
                            binding.txtNumberDay.setText(String.valueOf(1 + numberDay));
                        }
                    });
                }
                progressdialogLoad.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD_BUSSINESS_REGISTRATION && resultCode== Activity.RESULT_OK){
            Business business= (Business) data.getSerializableExtra(Constant.NAME_PUT_BUSINESS_REGISTRATION);
            if (business!=null){
                businessList.add(business);
                registrationAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == CODE_EDIT_BUSSINESS_REGISTRATION && resultCode == Activity.RESULT_OK){
            Business business = (Business) data.getSerializableExtra(Constant.NAME_PUT_BUSINESS_REGISTRATION);
            if (business!=null){
                businessList.set(positionEdit,business);
                registrationAdapter.notifyDataSetChanged();
                positionEdit = 0;
            }
        }
    }

    @Override
    public void onEdit(int position) {
        positionEdit = position;
        Business businessEdit = businessList.get(position);
        Intent intent= new Intent(getContext(),BusinessRegistrationInfoActivity.class);
        intent.putExtra(Constant.NAME_PUT_BUSINESS_REGISTRATION,businessEdit);
        startActivityForResult(intent,CODE_EDIT_BUSSINESS_REGISTRATION);
    }

    @Override
    public void onDelete(int position) {
        showConfirmMessage(SharedPreferencesManager.getSystemLabel(49), SharedPreferencesManager.getSystemLabel(53), SharedPreferencesManager.getSystemLabel(54), SharedPreferencesManager.getSystemLabel(55), new ConfirmCallback() {
            @Override
            public void onAccept() {
                businessList.remove(position);
                registrationAdapter.notifyDataSetChanged();
                SnackbarHelper
                        .getInstance()
                        .snackBarIconSuccess(getView(),SharedPreferencesManager.getSystemLabel(65));
            }

            @Override
            public void onCancel() {

            }
        });
    }
    private TimekeepingTypeCT findTimeKeeping(String itemCode){
        for (TimekeepingTypeCT typeCT : timekeepingTypeCTList){
            if (typeCT.getItemCode().toLowerCase().equals(itemCode.toLowerCase())){
                return typeCT;
            }
        }
        return null;
    }

}