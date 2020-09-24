package com.firstems.erp.ui.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.MainActivity;
import com.firstems.erp.R;
import com.firstems.erp.adapter.expan.CompanyExpanAdapter;
import com.firstems.erp.api.model.request.LoginLocationRequest;
import com.firstems.erp.api.model.response.company.CompanyResponse;
import com.firstems.erp.api.model.response.location.LocationResponse;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.login.SystemLoginApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.SaveDataCallback;
import com.firstems.erp.callback.location.InsertLocationCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.database.model.LocationDBModel;
import com.firstems.erp.databinding.SelectCompanyFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCompanyFragment extends CommonFragment {

    private SelectCompanyViewModel mViewModel;
    private SelectCompanyFragmentBinding binding;
    private List<CompanyResponse> companyList;
    private ExpandableListView expandableCompany;
    private CompanyExpanAdapter companyExpanAdapter;
    private TextView txtTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.select_company_fragment, container, false);

        initProgressDialog(SharedPreferencesManager.getSystemLabel(83), SharedPreferencesManager.getSystemLabel(63));
        addControls();
        checkData();
        addEvents();
        return binding.getRoot();
    }
    private void checkData() {
        companyList.size();
        Intent intent= getActivity().getIntent();
        companyList.addAll((Collection<? extends CompanyResponse>) intent.getSerializableExtra(Constant.NAME_PUT_LIST_COMPANY));
        companyExpanAdapter.notifyDataSetChanged();
    }

    private void addEvents() {
        expandableCompany.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                CompanyResponse company = companyList.get(groupPosition);
                LocationResponse location = company.getLocationList().get(childPosition);
                doLoginByLocation(company.getCompanyCode(),location.getLocationCode(), company.getLocationList());
                return true;
            }
        });
    }

    private void doLoginByLocation(String compCode, String locationCode,List<LocationResponse> locationList) {
        LoginLocationRequest loginLocationRequest= new LoginLocationRequest();
        loginLocationRequest.setComCode(compCode);
        loginLocationRequest.setLoctCode(locationCode);

        ApiServices.getInstance().doLocateLogin(SharedPreferencesManager.getInstance().getPrefToken(), loginLocationRequest.convertToJson(), new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    LoginReponse loginReponse= response.body();
                    System.out.println("Second TOKEN: "+loginReponse.getSystemLoginApiResponse().getToken());
                    DatabaseHelper.getInstance().insertMultiLocation(compCode, locationList, new InsertLocationCallback() {
                        @Override
                        public void onSuccess(List<LocationDBModel> locationDBModels) {
                            DatabaseHelper.getInstance().updateLocationCheck(compCode,locationCode,true);
                            System.out.println("Save location list success, list size = "+locationDBModels.size());
                        }
                    });
                    saveData(loginReponse.getSystemLoginApiResponse(), new SaveDataCallback() {
                        @Override
                        public void onSaveComplete() {
                            Intent intent= new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            setOveridePendingTransisi(getActivity());
                        }

                        @Override
                        public void onSaveFail() {

                        }
                    });
                }
                else {
                    showOutTOKEN();
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                showOutTOKEN();
            }
        });
    }
    private void saveData(SystemLoginApiResponse loginApiResponse, SaveDataCallback saveDataCallback) {
        if (loginApiResponse.getCompanyList().size()>0){
            if (loginApiResponse.getCompanyList().get(0).getLocationList().size()>0){
                SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
                SharedPreferencesManager.getInstance().setPrefAppRight(loginApiResponse.getAppRight());
                SharedPreferencesManager.getInstance().setPrefCompcode(loginApiResponse.getCompanyList().get(0).getCompanyCode());
                SharedPreferencesManager.getInstance().setPrefCompname(loginApiResponse.getCompanyList().get(0).getCompanyName());
                SharedPreferencesManager.getInstance().setPrefLctcode(loginApiResponse.getCompanyList().get(0).getLocationList().get(0).getLocationCode());
                SharedPreferencesManager.getInstance().setPrefLctname(loginApiResponse.getCompanyList().get(0).getLocationList().get(0).getLocationName());
                if (loginApiResponse.getUserLogin()!=null){
                    SharedPreferencesManager.getInstance().setPrefUsercode(loginApiResponse.getUserLogin().getUserCode());
                    SharedPreferencesManager.getInstance().setPrefAccountName(loginApiResponse.getUserLogin().getUserName());
                    SharedPreferencesManager.getInstance().setPrefEmpCode(loginApiResponse.getUserLogin().getEmpCode());
                    SharedPreferencesManager.getInstance().setPrefPartCode(loginApiResponse.getUserLogin().getPartCode());
                    SharedPreferencesManager.getInstance().setPrefPartName(loginApiResponse.getUserLogin().getPartName());
                    SharedPreferencesManager.getInstance().setPrefPostCode(loginApiResponse.getUserLogin().getPositionCode());
                    SharedPreferencesManager.getInstance().setPrefPostName(loginApiResponse.getUserLogin().getPositionName());
                    SharedPreferencesManager.getInstance().setPrefJobCode(loginApiResponse.getUserLogin().getJobCode());
                    SharedPreferencesManager.getInstance().setPrefJobName(loginApiResponse.getUserLogin().getJobName());
                    //SharedPreferencesManager.getInstance().setPrefLogoComp(loginApiResponse.getUserLogin().getLogoCompany());
                    SharedPreferencesManager.getInstance().setPrefLogoComp("http://api-dev.firstems.com/Api/data/runApi_File?run_Code=COM001.01.003&Key_Code=EMS001");
                    saveDataCallback.onSaveComplete();
                }
                else {
                    saveDataCallback.onSaveFail();
                }
            }
            else
                saveDataCallback.onSaveFail();
        }
        saveDataCallback.onSaveFail();
    }
    private void addControls() {
        binding.include21.findViewById(R.id.imgBack).setVisibility(View.INVISIBLE);
        txtTitle=binding.include21.findViewById(R.id.txtTitle);

        companyList = new ArrayList<>();
        expandableCompany = binding.expandelComapny;
        companyExpanAdapter= new CompanyExpanAdapter(getContext(),companyList);
        expandableCompany.setAdapter(companyExpanAdapter);
        expandableCompany.setDivider(null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SelectCompanyViewModel.class);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtTitle.setText(s);
            }
        });
    }

}