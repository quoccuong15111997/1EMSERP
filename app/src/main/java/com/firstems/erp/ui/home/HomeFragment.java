package com.firstems.erp.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DialogTopHomeAdapter;
import com.firstems.erp.adapter.HomeAdapter;
import com.firstems.erp.api.model.request.LoginLocationRequest;
import com.firstems.erp.api.model.request.SystemLoginRequest;
import com.firstems.erp.api.model.response.login.LoginReponse;
import com.firstems.erp.api.model.response.login.SystemLoginApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.SaveDataCallback;
import com.firstems.erp.callback.location.GetAllLocationCallbackl;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.database.model.LocationDBModel;
import com.firstems.erp.databinding.HomeFragmentTypeFlatBinding;
import com.firstems.erp.model.DataHome;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends CommonFragment {
    
    private HomeViewModel mViewModel;
    private HomeFragmentTypeFlatBinding binding;
    private List<DataHome> dataHomeList;
    private HomeAdapter homeAdapter;
    private HomeAdapter.HomeItemOnClickListener homeItemOnClickListener;
    
    public HomeFragment(HomeAdapter.HomeItemOnClickListener homeItemOnClickListener) {
        this.homeItemOnClickListener = homeItemOnClickListener;
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
    
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_type_flat, container, false);
    
        setAminHeader();
        addControls();
        addEvents();
        
        return binding.getRoot();
    }
    
    private void addEvents() {
        binding.tvTitleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectLocation(new DialogTopHomeAdapter.DialogTopHomeOnClickListener() {
                    @Override
                    public void onItemClick(LocationDBModel model) {
                        if (!model.isCheck()){
                            showLoadingDialog("Đang đổi chi nhánh...");
                            SystemLoginRequest systemLoginRequest= new SystemLoginRequest();
                            systemLoginRequest.setUserLogin(SharedPreferencesManager.getInstance().getPrefUsername());
                            systemLoginRequest.setPassword(SharedPreferencesManager.getInstance().getPrefPassword());
                            systemLoginRequest.setTokenDevice(SharedPreferencesManager.getFCMToken());
                            ApiServices.getInstance().doSystemLogin(systemLoginRequest.convertToJson(), new retrofit2.Callback<LoginReponse>() {
                                @Override
                                public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                                    if (response.isSuccessful()){
                                        if (response.body().isRETNCODE()){
                                            SharedPreferencesManager.getInstance().setPrefToken(response.body().getSystemLoginApiResponse().getToken());
                                            doLoginByLocation(SharedPreferencesManager.getInstance().getPrefCompcode(),model.getLocationCode());
                                            DatabaseHelper.getInstance().updateLocationCheck(SharedPreferencesManager.getInstance().getPrefCompcode(), model.getLocationCode(), true);
                                            
                                        }
                                        else {
                                            loadingDialog.dismiss();
                                        }
                                    }
                                    else {
                                        loadingDialog.dismiss();
                                        showOutTOKEN();
                                    }
                                }
    
                                @Override
                                public void onFailure(Call<LoginReponse> call, Throwable t) {
                                    loadingDialog.dismiss();
                                    showOutTOKEN();
                                }
                            });
                        }
                    }
                });
            }
        });
        homeAdapter.setHomeItemOnClickListener(new HomeAdapter.HomeItemOnClickListener() {
            @Override
            public void onItemClick(int position) {
                homeItemOnClickListener.onItemClick(position);
            }
        });
        binding.lProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemOnClickListener.onItemClick(-1);
            }
        });
    }
    
    private void showSelectLocation(DialogTopHomeAdapter.DialogTopHomeOnClickListener dialogTopHomeOnClickListener) {
        DatabaseHelper.getInstance().getAllLocation(new GetAllLocationCallbackl() {
            @Override
            public void onLoadSuccess(List<LocationDBModel> locationDBModelList) {
                Dialog dialog;
                dialog= new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_top_home);
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.TOP;
                
                DialogTopHomeAdapter dialogTopHomeAdapter = new DialogTopHomeAdapter(locationDBModelList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                RecyclerView recyclerView = dialog.findViewById(R.id.recycle);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(dialogTopHomeAdapter);
                
                dialogTopHomeAdapter.setDialogTopHomeOnClickListener(new DialogTopHomeAdapter.DialogTopHomeOnClickListener() {
                    @Override
                    public void onItemClick(LocationDBModel model) {
                        dialogTopHomeOnClickListener.onItemClick(model);
                        dialog.dismiss();
                    }
                });
    
                dialog.setCancelable(true);
                dialog.getWindow().setAttributes(lp);
                dialog.show();
            }
        });
    }
    
    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.lParentContent);
                        binding.ivPhotoProfile.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 750);
        } catch (Exception ex) {
            binding.ivPhotoProfile.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void addControls() {
        dataHomeList = new ArrayList<>();
        homeAdapter = new HomeAdapter(dataHomeList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rvList.setAdapter(homeAdapter);
        binding.rvList.setLayoutManager(linearLayoutManager);
        
        initHomeList();
    
       // LoadImagePicassoMkLoader("http://api-dev.firstems.com/Api/data/runApi_File?run_Code=COM001.01.003&Key_Code=EMS001",binding.ivPhotoProfile,binding.pbLoadingProfile);
        binding.ivPhotoProfile.setImageResource(R.drawable.logo_ems);
        binding.ivPhotoProfile.setBorderColor(Color.WHITE);
        binding.pbLoadingProfile.setVisibility(View.GONE);
        
        binding.tvName.setText(SharedPreferencesManager.getInstance().getPrefAccountName());
        binding.tvEmail.setText(SharedPreferencesManager.getInstance().getPrefCompname());
        binding.tvTitleToolbar.setText(SharedPreferencesManager.getInstance().getPrefLctname());
    }
    
    private void initHomeList() {
        DataHome dataHome = new DataHome();
        dataHome.setTitle(SharedPreferencesManager.getSystemLabel(8));
        dataHome.setDescription1(SharedPreferencesManager.getSystemLabel(13));
        dataHome.setDescription2(SharedPreferencesManager.getSystemLabel(14));
        dataHome.setImage(R.drawable.ic_barang_masuk);
        dataHomeList.add(dataHome);
    
        DataHome dataHome1 = new DataHome();
        dataHome1.setTitle(SharedPreferencesManager.getSystemLabel(9));
        dataHome1.setDescription1(SharedPreferencesManager.getSystemLabel(15));
        dataHome1.setDescription2(SharedPreferencesManager.getSystemLabel(16));
        dataHome1.setImage(R.drawable.ic_stock_opname);
        dataHomeList.add(dataHome1);
    
        DataHome dataHome2 = new DataHome();
        dataHome2.setTitle(SharedPreferencesManager.getSystemLabel(12));
        dataHome2.setDescription1(SharedPreferencesManager.getSystemLabel(17));
        dataHome2.setDescription2(SharedPreferencesManager.getSystemLabel(18));
        dataHome2.setImage(R.drawable.ic_product_assets);
        dataHomeList.add(dataHome2);
        homeAdapter.notifyDataSetChanged();
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }
    public void LoadImagePicassoMkLoader(String uLoad, ImageView target, final MKLoader mDialog) {
        String load = uLoad.replace(" ", "%20");
        if (load.equalsIgnoreCase("") || load.isEmpty() || load.equalsIgnoreCase("null")) {
            
            Picasso
                    .with(getContext())
                    .load("https://firebasestorage.googleapis.com/v0/b/modul-74a9a.appspot.com/o/no_image.jpg?alt=media&token=68c2e66e-f14b-42bc-bdae-087346ba1d09")
                    .into(target, new Callback() {
                        @Override
                        public void onSuccess() {
                            mDialog.setVisibility(View.GONE);
                        }
                        
                        @Override
                        public void onError() {
                            mDialog.setVisibility(View.GONE);
                        }
                    });
        } else {
            mDialog.setVisibility(View.VISIBLE);
            Picasso
                    .with(getContext())
                    .load(load)
                    .into(target, new Callback() {
                        @Override
                        public void onSuccess() {
                            if (mDialog != null) {
                                mDialog.setVisibility(View.GONE);
                            }
                        }
                        
                        @Override
                        public void onError() {
                            if (mDialog != null) {
                                mDialog.setVisibility(View.GONE);
                            }
                            
                            Picasso
                                    .with(getContext())
                                    .load("https://firebasestorage.googleapis.com/v0/b/modul-74a9a.appspot.com/o/no_image.jpg?alt=media&token=68c2e66e-f14b-42bc-bdae-087346ba1d09");
                        }
                    });
        }
    }
    private void doLoginByLocation(String compCode, String locationCode) {
        LoginLocationRequest loginLocationRequest= new LoginLocationRequest();
        loginLocationRequest.setComCode(compCode);
        loginLocationRequest.setLoctCode(locationCode);
        
        ApiServices.getInstance().doLocateLogin(SharedPreferencesManager.getInstance().getPrefToken(), loginLocationRequest.convertToJson(), new retrofit2.Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    LoginReponse loginReponse= response.body();
                    saveData(loginReponse.getSystemLoginApiResponse(), new SaveDataCallback() {
                        @Override
                        public void onSaveComplete() {
                            loadingDialog.dismiss();
                            binding.tvName.setText(SharedPreferencesManager.getInstance().getPrefAccountName());
                            binding.tvEmail.setText(SharedPreferencesManager.getInstance().getPrefJobName());
                            binding.tvTitleToolbar.setText(SharedPreferencesManager.getInstance().getPrefLctname());
                            showToastSuccess("Đã thay đổi chi nhánh");
                        }
                        
                        @Override
                        public void onSaveFail() {
                            loadingDialog.dismiss();
                        }
                    });
                }
                else {
                    loadingDialog.dismiss();
                    showOutTOKEN();
                }
            }
            
            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                loadingDialog.dismiss();
                showOutTOKEN();
            }
        });
    }
    private void saveData(SystemLoginApiResponse loginApiResponse, SaveDataCallback saveDataCallback) {
        if (loginApiResponse.getUserLogin()!=null){
            SharedPreferencesManager.getInstance().setPrefToken(loginApiResponse.getToken());
            SharedPreferencesManager.getInstance().setPrefUsercode(loginApiResponse.getUserLogin().getUserCode());
            SharedPreferencesManager.getInstance().setPrefAccountName(loginApiResponse.getUserLogin().getUserName());
            SharedPreferencesManager.getInstance().setPrefEmpCode(loginApiResponse.getUserLogin().getEmpCode());
            SharedPreferencesManager.getInstance().setPrefPartCode(loginApiResponse.getUserLogin().getPartCode());
            SharedPreferencesManager.getInstance().setPrefPartName(loginApiResponse.getUserLogin().getPartName());
            SharedPreferencesManager.getInstance().setPrefPostCode(loginApiResponse.getUserLogin().getPositionCode());
            SharedPreferencesManager.getInstance().setPrefPostName(loginApiResponse.getUserLogin().getPositionName());
            SharedPreferencesManager.getInstance().setPrefJobCode(loginApiResponse.getUserLogin().getJobCode());
            SharedPreferencesManager.getInstance().setPrefJobName(loginApiResponse.getUserLogin().getJobName());
            SharedPreferencesManager.getInstance().setPrefLogoComp(loginApiResponse.getUserLogin().getLogoCompany());
            SharedPreferencesManager.getInstance().setPrefLctcode(loginApiResponse.getUserLogin().getLocateCode());
            SharedPreferencesManager.getInstance().setPrefLctname(loginApiResponse.getUserLogin().getLocateName());
            saveDataCallback.onSaveComplete();
        }
        else {
            saveDataCallback.onSaveFail();
        }
    }
}