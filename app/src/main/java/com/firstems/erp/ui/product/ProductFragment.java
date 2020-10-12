package com.firstems.erp.ui.product;

import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.progress.ErrorCodeAdapter;
import com.firstems.erp.adapter.progress.ProductAdapter;
import com.firstems.erp.api.model.request.progress.ProgressProductDetail;
import com.firstems.erp.api.model.request.progress.ProgressProductHeader;
import com.firstems.erp.api.model.request.progress.ProgressProductRequest;
import com.firstems.erp.api.model.response.ApiResponse;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
import com.firstems.erp.api.model.response.product.ProgressStep;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.ProductFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.model.product.ErrorCodeModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.product.barcode.ScannerActivity;
import com.firstems.erp.ui.product.progress.ProductProgressActivity;
import com.firstems.erp.ui.product.progress.ProductProgressFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends CommonFragment {

    private ProductViewModel mViewModel;
    private ProductFragmentBinding binding;
    private TextView txtTitle;
    private ImageView imgBack, imgSave;
    private int CODE_OPEN_PRODUCT_PROGRESS = 45;
    private List<ProgressProductDetailItem> listCurrent;
    private List<ProgressProductDetailItem> list;
    private ProductAdapter productAdapter;
    private ArrayAdapter<ProgressStep> arrayAdapterProgressStep;
    private Dialog dialog;
    private ProgressItem progressItemSelected;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void setAminHeader() {
        try {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransitionManager.beginDelayedTransition(binding.parent);
                        binding.btnDone.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 300);
        } catch (Exception ex) {
            binding.btnDone.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(getActivity());
            }
        });
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSave();
            }
        });
        binding.edtMaLenhSanXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductProgressActivity.class);
                startActivityForResult(intent, CODE_OPEN_PRODUCT_PROGRESS);
            }
        });
        productAdapter.setProductItemClickListener(new ProductAdapter.ProductItemClickListener() {
            @Override
            public void ontemClick(ProgressProductDetailItem item, int position) {
                if (dialog==null){
                    showInputQuatity(item, position);
                }
            }
        });
        binding.spinerCongDoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ProgressStep progressStep = arrayAdapterProgressStep.getItem(i);
                    List<ProgressProductDetailItem> listTemp = new ArrayList<>();
                    for (ProgressProductDetailItem item : listCurrent) {
                        if (item.getStepcode().equals(progressStep.getStepCode())) {
                            listTemp.add(item);
                        }
                    }
                    productAdapter.setData(listTemp);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSave();
            }
        });
    }

    private void doSave() {
        if (listCurrent.size()!=0){
            showConfirmMessage("Xác nhận lưu", "Bạn chắc chắn muốn lưu?", "Đồng ý", "Hủy", new ConfirmCallback() {
                @Override
                public void onAccept() {
                    doStartCommit();
                }

                @Override
                public void onCancel() {

                }
            });
        }
        else {
            showErrorDialog("THÔNG BÁO","Không có sản phẩm để lưu");
        }
    }

    private void doStartCommit() {
        showLoadingDialog("Đang thực hiện");
        ProgressProductHeader progressProductHeader = new ProgressProductHeader();
        progressProductHeader.setCompcode(SharedPreferencesManager.getInstance().getPrefCompcode());
        progressProductHeader.setLctncode(SharedPreferencesManager.getInstance().getPrefLctcode());

        List<ProgressProductDetail> progressProductDetailList = new ArrayList<>();
        for (ProgressProductDetailItem item : listCurrent){
            ProgressProductDetail detail = new ProgressProductDetail();
            detail.setCompcode(SharedPreferencesManager.getInstance().getPrefCompcode());
            detail.setLctncode(SharedPreferencesManager.getInstance().getPrefLctcode());
            detail.setErroqtty(item.getQuatityBad());
            detail.setPrdcqtty(item.getQuatityGood());
            detail.setPrdccode(item.getPrdccode());
            detail.setErrocode(item.getErrorCode());
            progressProductDetailList.add(detail);
        }
        progressProductHeader.setProgressProductDetailList(progressProductDetailList);
        List<ProgressProductHeader> progressProductHeaderList = new ArrayList<>();
        progressProductHeaderList.add(progressProductHeader);
        ProgressProductRequest progressProductRequest = new ProgressProductRequest();
        progressProductRequest.setProgressProductHeaderList(progressProductHeaderList);
        JsonObject jsonObject = new Gson().fromJson(new Gson().toJson(progressProductRequest), JsonObject.class);
        ApiServices.getInstance().upDateProgressProduct(SharedPreferencesManager.getInstance().getPrefToken(), jsonObject, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    loadingDialog.dismiss();
                    if (response.body().isRETNCODE()){
                        showSuccessDialog("Thành công",response.body().getRETNMSSG());
                        mViewModel.getData(progressItemSelected.getCmmdcode());
                    }
                    else {
                        loadingDialog.dismiss();
                        showErrorDialog("Không thành công",response.body().getRETNMSSG());
                    }
                }
                else {
                    System.out.println(response.message());
                    loadingDialog.dismiss();
                    showOutTOKEN();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                loadingDialog.dismiss();
                showOutTOKEN();
            }
        });
    }

    private void addControls() {
        showLoadingNonMessDialog();

        Intent intent = getActivity().getIntent();
        progressItemSelected = (ProgressItem) intent.getSerializableExtra(Constant.NAME_PUT_PROGRESS_PRODUCT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingNonMessDialog!=null){
                    loadingNonMessDialog.dismiss();
                }
                setAminHeader();
            }
        }, 1500);
        txtTitle = binding.include6.findViewById(R.id.txtTitle);
        imgBack = binding.include6.findViewById(R.id.imgClose);
        imgSave = binding.include6.findViewById(R.id.imgDone);

        listCurrent = new ArrayList<>();
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleProduct.setLayoutManager(linearLayoutManager);
        binding.recycleProduct.setAdapter(productAdapter);

        arrayAdapterProgressStep = new ArrayAdapter<>(getContext(), R.layout.spiner_item);
        arrayAdapterProgressStep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerCongDoan.setAdapter(arrayAdapterProgressStep);

        txtTitle.setText("Quản lý sản xuất");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        if (progressItemSelected!=null){
            mViewModel.getData(progressItemSelected.getCmmdcode());
            binding.edtMaLenhSanXuat.setText(progressItemSelected.getCmmdcode());
            binding.txtNgayTaoLenhSanXuat.setText(Util.formatDateCustomChar(progressItemSelected.getCmmddate(), "-"));
        }
        mViewModel.getMutableLiveDataProgressDetail().observe(getViewLifecycleOwner(), new Observer<List<ProgressProductDetailItem>>() {
            @Override
            public void onChanged(List<ProgressProductDetailItem> progressProductDetailItems) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingNonMessDialog != null) {
                            loadingNonMessDialog.dismiss();
                        }
                        binding.loadingInList.setVisibility(View.VISIBLE);
                        listCurrent.clear();
                        listCurrent.addAll(progressProductDetailItems);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                arrayAdapterProgressStep.clear();
                                String strTemp = "";
                                for (ProgressProductDetailItem item : listCurrent) {
                                    if (!strTemp.contains(item.getStepcode())) {
                                        arrayAdapterProgressStep.add(item.getProgressStep());
                                        strTemp += item.getStepcode() + ",";
                                    }
                                }
                            }
                        });
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.loadingInList.setVisibility(View.GONE);
                                if (listCurrent.size() > 0) {
                                    binding.recycleProduct.setVisibility(View.VISIBLE);
                                    binding.txtNon.setVisibility(View.GONE);
                                } else {
                                    binding.recycleProduct.setVisibility(View.GONE);
                                    binding.txtNon.setVisibility(View.VISIBLE);
                                }
                            }
                        }, 700);
                    }
                }, 1500);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*showLoadingNonMessDialog();
        if (requestCode == CODE_OPEN_PRODUCT_PROGRESS) {
            if (resultCode == Activity.RESULT_OK) {
                ProgressItem progressItem = (ProgressItem) data.getSerializableExtra(Constant.NAME_PUT_PROGRESS_PRODUCT);
                if (progressItem != null) {
                    progressItemSelected = progressItem;
                    binding.edtMaLenhSanXuat.setText(progressItem.getCmmdcode());
                    binding.txtNgayTaoLenhSanXuat.setText(Util.formatDateCustomChar(progressItem.getCmmddate(), "-"));
                    mViewModel.getData(progressItem.getCmmdcode());
                }
            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingNonMessDialog != null) {
                            loadingNonMessDialog.dismiss();
                        }
                    }
                }, 700);
            }
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showInputQuatity(ProgressProductDetailItem item, int position) {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_input_quatity);
        dialog.setCancelable(false);

        AppBarLayout appBarLayout = dialog.findViewById(R.id.app_bar_layout);
        ImageView imgClose = appBarLayout.findViewById(R.id.bt_close);
        ImageView imgSave = appBarLayout.findViewById(R.id.bt_save);
        TextView txtTitle = appBarLayout.findViewById(R.id.txtTitleDialog);
        EditText edtQuatityGood = dialog.findViewById(R.id.edtQuatityGood);
        edtQuatityGood.setText(String.valueOf(item.getQuatityGood()));
        EditText edtQuatityBad = dialog.findViewById(R.id.edtQuatityBad);
        edtQuatityBad.setText(String.valueOf(item.getQuatityBad()));
        TextView txtError = dialog.findViewById(R.id.txtError);
        TextView txtSumQuatity = dialog.findViewById(R.id.txtSumQuatity);

        ImageButton imgPlusGood = dialog.findViewById(R.id.imgPlusGood);
        ImageButton imgSubGood = dialog.findViewById(R.id.imgSubGood);
        ImageButton imgPlusBad = dialog.findViewById(R.id.imgPlusBad);
        ImageButton imgSubBad = dialog.findViewById(R.id.imgsubBad);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        Spinner spinnerError = dialog.findViewById(R.id.spinnerError);

        List<ErrorCodeModel> errorCodeModels = new ArrayList<>();
        errorCodeModels.add(new ErrorCodeModel("0", "Ấn để chọn", false));
        errorCodeModels.add(new ErrorCodeModel("ss", "Quên", false));
        errorCodeModels.add(new ErrorCodeModel("ss", "Tại", false));
        errorCodeModels.add(new ErrorCodeModel("ss", "Bị", false));
        errorCodeModels.add(new ErrorCodeModel("ss", "Thì", false));
        errorCodeModels.add(new ErrorCodeModel("ss", "Là", false));

        ErrorCodeAdapter errorCodeAdapter = new ErrorCodeAdapter(errorCodeModels, dialog.getContext());
        spinnerError.setAdapter(errorCodeAdapter);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveQuatity(position, item, Long.parseLong(edtQuatityGood.getText().toString()), Long.parseLong(edtQuatityBad.getText().toString()), txtError.getText().toString());
                if (dialog!=null){
                    dialog.dismiss();
                    dialog=null;
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveQuatity(position, item, Long.parseLong(edtQuatityGood.getText().toString()), Long.parseLong(edtQuatityBad.getText().toString()), txtError.getText().toString());
               if (dialog!=null){
                   dialog.dismiss();
                   dialog=null;
               }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog!=null){
                    dialog.dismiss();
                    dialog=null;
                }
            }
        });

        errorCodeAdapter.setOnSpinnerMultiCheckListener(new ErrorCodeAdapter.OnSpinnerMultiCheckListener() {
            @Override
            public void onIteCheck(int position) {
                errorCodeModels.get(position).setCheck(!errorCodeModels.get(position).isCheck());
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        String s = "";
                        for (ErrorCodeModel errorCodeModel : errorCodeModels) {
                            if (errorCodeModel.isCheck()) {
                                s += errorCodeModel.getErrorName() + ",";
                            }
                        }
                        if (!s.equals("")) {
                            txtError.setText(s);
                            txtError.setVisibility(View.VISIBLE);
                        } else {
                            txtError.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        txtTitle.setText(item.getPrdcname());
        txtSumQuatity.setText(String.valueOf((long) item.getPrdcqtty()));

        imgPlusGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlusClick(edtQuatityGood, (long) item.getPrdcqtty(), Long.parseLong(edtQuatityBad.getText().toString()));
            }
        });
        imgPlusBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlusClick(edtQuatityBad, (long) item.getPrdcqtty(), Long.parseLong(edtQuatityGood.getText().toString()));
            }
        });
        imgSubGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubClick(edtQuatityGood);
            }
        });
        imgSubBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubClick(edtQuatityBad);
            }
        });
        edtQuatityGood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!s.toString().equals("")) {
                                long number = Long.parseLong(s.toString());
                                long quatityBad = Long.parseLong(edtQuatityBad.getText().toString());
                                if (number + quatityBad > (long) item.getPrdcqtty()) {
                                    edtQuatityGood.setText(String.valueOf((long) item.getPrdcqtty() - quatityBad));
                                }
                            } else {
                                edtQuatityGood.setText("0");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        edtQuatityBad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!s.toString().equals("")) {
                                long number = Long.parseLong(s.toString());
                                long quatityGood = Long.parseLong(edtQuatityGood.getText().toString());
                                if (number + quatityGood > (long) item.getPrdcqtty()) {
                                    edtQuatityBad.setText(String.valueOf((long) item.getPrdcqtty() - quatityGood));
                                }
                            } else {
                                edtQuatityBad.setText("0");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void doSaveQuatity(int position, ProgressProductDetailItem item, long numGood, long numBad, String errorCode) {
        try {
            list.get(position).setQuatityBad(numBad);
            list.get(position).setErrorCode(errorCode);
            list.get(position).setQuatityGood(numGood);
            list.get(position).setEdit(true);
            int indexCurrentProduct = listCurrent.indexOf(list.get(position));
            System.out.println(listCurrent.get(indexCurrentProduct).getQuatityGood()+"Good");
            System.out.println(listCurrent.get(indexCurrentProduct).getQuatityBad()+"bad");
            System.out.println(indexCurrentProduct);
            productAdapter.notifyItemChanged(position);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onSubClick(EditText edt) {
        if (!edt.getText().toString().equals("0")) {
            try {
                long number = Long.parseLong(edt.getText().toString());
                number -= 1;
                edt.setText(String.valueOf(number));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void onPlusClick(EditText edt, long maxValue, long tempValue) {
        if (!edt.getText().toString().equals("0")) {
            try {
                long number = Long.parseLong(edt.getText().toString());
                if (number + tempValue < maxValue) {
                    number += 1;
                    edt.setText(String.valueOf(number));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            edt.setText("1");
        }
    }
}