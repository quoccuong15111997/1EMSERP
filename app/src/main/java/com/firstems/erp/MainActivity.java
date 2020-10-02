package com.firstems.erp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firstems.erp.adapter.HomeAdapter;
import com.firstems.erp.api.model.request.RunCodeUpDateBody;
import com.firstems.erp.api.model.response.runcode.RunCodeUpDateApiResponse;
import com.firstems.erp.api.model.response.runcode.RunCodeUpDateItemApiResponse;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.callback.runcode.GetRunCodeDataCallback;
import com.firstems.erp.callback.runcode.SaveRunCodeDataUpdateCallback;
import com.firstems.erp.common.CommonActivity;
import com.firstems.erp.data.DataStructureProvider;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.database.model.RunCodeData;
import com.firstems.erp.helper.toast.ToastHelper;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.system.SysConfig;
import com.firstems.erp.ui.approved.ApprovedFragment;
import com.firstems.erp.ui.document.DocumentFragment;
import com.firstems.erp.ui.home.HomeFragment;
import com.firstems.erp.ui.more.MoreFragment;
import com.firstems.erp.ui.signature.SignatureGirdDiffFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CommonActivity implements BackToHomeCallback, HomeAdapter.HomeItemOnClickListener {
    private BottomNavigationView navView;
    private FragmentManager fragmentManager;
    private Fragment fragmentSignature, fragmentApproved, fragmentDocument, fragmentReport, fragmentAccount, fragmentDashboard, fragmentMore;
    private int flagBackPress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        FrameLayout frame_container = findViewById(R.id.frame_container);
        setupHideKeyboard(frame_container);
        
        getDataUpdate();
        addControls();
        addEvents();
        setText();
        hideSoftKeyboard(this);
    }

    private void getDataUpdate() {
        RunCodeUpDateBody runCodeUpDateBody= new RunCodeUpDateBody(SharedPreferencesManager.getInstance().getPrefCompcode());
        System.out.println(runCodeUpDateBody.convertToJson());
        ApiServices.getInstance().getDataUpdate(SharedPreferencesManager.getInstance().getPrefToken(), runCodeUpDateBody.convertToJson(), new Callback<RunCodeUpDateApiResponse>() {
            @Override
            public void onResponse(Call<RunCodeUpDateApiResponse> call, Response<RunCodeUpDateApiResponse> response) {
                if (response.isSuccessful()){
                    RunCodeUpDateApiResponse runCodeUpDateApiResponse= response.body();
                    List<RunCodeUpDateItemApiResponse> itemApiResponses = runCodeUpDateApiResponse.getRunCodeUpDateItemApiResponses();

                    List<RunCodeData> runCodeDataNow = new ArrayList<>();
                    for (RunCodeUpDateItemApiResponse item : itemApiResponses){
                        RunCodeData runCode = new RunCodeData();
                        runCode.setRunCode(item.getItemCode());
                        runCode.setStatus(item.getStatusUpdate());

                        runCodeDataNow.add(runCode);
                    }
                    SysConfig.setRunCodeDataList(runCodeDataNow);
                        List<RunCodeData> runCodeData = new ArrayList<>();
                        for (RunCodeUpDateItemApiResponse item : itemApiResponses){
                            System.out.println("RUN CODE LIST: "+item.getItemCode());
                            RunCodeData runCode = new RunCodeData();
                            runCode.setId(runCode+"");
                            runCode.setRunCode(item.getItemCode());
                            runCode.setStatus(-1);

                            runCodeData.add(runCode);
                        }
                        for (RunCodeData rc : runCodeData){
                            rc.setId(DataStructureProvider.idRuncodeGenerate(rc.getRunCode(),"",
                                    SharedPreferencesManager.getInstance().getPrefCompcode(),
                                    SharedPreferencesManager.getInstance().getPrefLctcode()));
                            rc.setCompany(SharedPreferencesManager.getInstance().getPrefCompcode());
                            rc.setLocation(SharedPreferencesManager.getInstance().getPrefLctcode());
                            System.out.println("RUN CODE LIST: "+rc.getId());
                            DatabaseHelper.getInstance().getRunCodeData(new GetRunCodeDataCallback() {
                                @Override
                                public void onGetRunCodeData(List<RunCodeData> runCodeDataList) {
                                    if (runCodeDataList==null || runCodeDataList.size()==0){
                                        DatabaseHelper.getInstance().saveSingelRunCodeData(rc, new SaveRunCodeDataUpdateCallback() {
                                            @Override
                                            public void onSave(boolean isSuccess) {
                                                if (isSuccess){
                                                    Log.e("SAVE_RUNCODE_UPDATE","Save success : "+rc.getRunCode());
                                                }
                                                else {
                                                    Log.e("SAVE_RUNCODE_UPDATE","Save Fail");
                                                }
                                            }
                                        });
                                    }
                                }
                            },rc.getRunCode(),"");
                        }
                        SharedPreferencesManager.getInstance().setFirstSetupRuncode(false);
                    }
                else {
                    showOutTOKEN();
                }
            }

            @Override
            public void onFailure(Call<RunCodeUpDateApiResponse> call, Throwable t) {
                showOutTOKEN();
            }
        });
    }

    private void setText() {
       /* Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime()+"");
        System.out.println(android.text.format.DateFormat.format("EEEE", calendar.getTime()));*/

        navView.getMenu().getItem(0).setTitle(SharedPreferencesManager.getSystemLabel(7));
        navView.getMenu().getItem(1).setTitle(SharedPreferencesManager.getSystemLabel(8));
        navView.getMenu().getItem(2).setTitle(SharedPreferencesManager.getSystemLabel(9));
        navView.getMenu().getItem(3).setTitle(SharedPreferencesManager.getSystemLabel(10));
        navView.getMenu().getItem(4).setTitle(SharedPreferencesManager.getSystemLabel(11));
    }

    private void addEvents() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_dashboard : {
                        if (fragmentDashboard==null){
                            fragmentDashboard= new HomeFragment(MainActivity.this::onItemClick);
                        }
                        loadFragment(fragmentDashboard);
                        break;
                    }
                    case R.id.navigation_submission :{
                        if (fragmentSignature == null){
                            fragmentSignature = new SignatureGirdDiffFragment(MainActivity.this::onBackPress);
                            //fragmentSignature= new SignatureGirdFragment();
                        }
                        loadFragment(fragmentSignature);
                        break;
                    }
                    case R.id.navigation_approved : {
                        if (fragmentApproved==null){
                            fragmentApproved= new ApprovedFragment(MainActivity.this::onBackPress);
                        }
                        loadFragment(fragmentApproved);
                        break;
                    }
                    case R.id.navigation_document : {
                        if (fragmentDocument==null){
                            fragmentDocument= new DocumentFragment(MainActivity.this::onBackPress);
                        }
                        loadFragment(fragmentDocument);
                        break;
                    }
                   /* case R.id.navigation_report : {
                        if (fragmentReport==null){
                            fragmentReport= new ReportFragment(MainActivity.this::onBackPress);
                        }
                        loadFragment(fragmentReport);
                        break;
                    }*/
                    case  R.id.navigation_more : {
                        if (fragmentMore==null){
                            fragmentMore= new MoreFragment(MainActivity.this::onBackPress);
                        }
                        loadFragment(fragmentMore);
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void addControls() {
        navView = findViewById(R.id.nav_view);
       fragmentDashboard= new HomeFragment(this::onItemClick);
        //fragmentDashboard= new SignatureGirdDiffFragment(MainActivity.this::onBackPress);
        //ReviewProcessFragment  reviewProcessFragment = new ReviewProcessFragment("LHCV","TTN00104000118120005");
        loadFragment(fragmentDashboard);
    }
    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction
                    .replace(R.id.frame_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPress() {
        if (fragmentDashboard==null){
            fragmentDashboard= new HomeFragment(this::onItemClick);
        }
        loadFragment(fragmentDashboard);
        navView.getMenu().getItem(0).setChecked(true);
    }
    
    @Override
    public void onBackPressed() {
        if (flagBackPress == 0){
            ToastHelper.getInstance().toastIconInfo("Nhấn back lần nữa để thoát", Toast.LENGTH_SHORT);
            flagBackPress+= 1;
        }
        else if (flagBackPress == 1){
            super.onBackPressed();
        }
    }
    
    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0 : {
                if (fragmentSignature == null){
                    fragmentSignature = new SignatureGirdDiffFragment(MainActivity.this::onBackPress);
                    //fragmentSignature= new SignatureGirdFragment();
                }
                loadFragment(fragmentSignature);
                navView.getMenu().getItem(1).setChecked(true);
                break;
            }
            case 1 : {
                if (fragmentApproved==null){
                    fragmentApproved= new ApprovedFragment(MainActivity.this::onBackPress);
                }
                loadFragment(fragmentApproved);
                navView.getMenu().getItem(2).setChecked(true);
                break;
            }
            case 2 : {
                if (fragmentDocument==null){
                    fragmentDocument= new DocumentFragment(MainActivity.this::onBackPress);
                }
                loadFragment(fragmentDocument);
                navView.getMenu().getItem(3).setChecked(true);
                break;
            }
            default : {
                if (fragmentMore==null){
                    fragmentMore= new MoreFragment(MainActivity.this::onBackPress);
                }
                loadFragment(fragmentMore);
                navView.getMenu().getItem(4).setChecked(true);
                break;
            }
        }
    }
}