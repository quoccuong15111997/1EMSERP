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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.progress.ErrorCodeAdapter;
import com.firstems.erp.adapter.progress.ProductAdapter;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.ProductFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.model.product.ErrorCodeModel;
import com.firstems.erp.ui.product.barcode.ScannerActivity;
import com.firstems.erp.ui.product.progress.ProductProgressActivity;
import com.firstems.erp.ui.product.progress.ProductProgressFragment;
import com.google.android.material.appbar.AppBarLayout;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends CommonFragment {

    private ProductViewModel mViewModel;
    private ProductFragmentBinding binding;
    private TextView txtTitle;
    private ImageView imgBack, imgSave;
    private int CODE_OPEN_PRODUCT_PROGRESS = 45;
    private int CODE_OPEN_SCANER = 452;
    private List<ProgressProductDetailItem> list;
    private ProductAdapter productAdapter;

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
            }, 750);
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
                showInputQuatity(item, position);
            }
        });
        binding.imgBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ScannerActivity.class);
                startActivityForResult(intent, CODE_OPEN_SCANER);
            }
        });
    }

    private void doSave() {
    }

    private void addControls() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingNonMessDialog.dismiss();
                setAminHeader();
            }
        }, 1500);
        txtTitle = binding.include6.findViewById(R.id.txtTitle);
        imgBack = binding.include6.findViewById(R.id.imgClose);
        imgSave = binding.include6.findViewById(R.id.imgDone);

        list = new ArrayList<>();
        productAdapter = new ProductAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleProduct.setLayoutManager(linearLayoutManager);
        binding.recycleProduct.setAdapter(productAdapter);

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
        mViewModel.getMutableLiveDataProgressDetail().observe(getViewLifecycleOwner(), new Observer<List<ProgressProductDetailItem>>() {
            @Override
            public void onChanged(List<ProgressProductDetailItem> progressProductDetailItems) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingNonMessDialog != null) {
                            loadingNonMessDialog.dismiss();
                        }
                        list.clear();
                        list.addAll(progressProductDetailItems);
                        binding.recycleProduct.post(new Runnable() {
                            @Override
                            public void run() {
                                productAdapter.notifyDataSetChanged();
                            }
                        });
                        if (list.size() > 0) {
                            binding.recycleProduct.setVisibility(View.VISIBLE);
                            binding.txtNon.setVisibility(View.GONE);
                        } else {
                            binding.recycleProduct.setVisibility(View.GONE);
                            binding.txtNon.setVisibility(View.VISIBLE);
                        }
                    }
                }, 1500);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_OPEN_PRODUCT_PROGRESS) {
            if (resultCode == Activity.RESULT_OK) {
                ProgressItem progressItem = (ProgressItem) data.getSerializableExtra(Constant.NAME_PUT_PROGRESS_PRODUCT);
                if (progressItem != null) {
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
        }
        if (requestCode == CODE_OPEN_SCANER) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(Constant.NAME_PUT_RESULT_BARCODE);
                System.out.println(result);
                if (result != null) {
                    if (!result.equals("")) {
                        binding.edtMaLenhSanXuat.setText(result);
                        mViewModel.getData(result);
                    }
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
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingNonMessDialog();
    }
    private void showInputQuatity(ProgressProductDetailItem item, int position) {
        final Dialog dialog = new Dialog(getContext());
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
        errorCodeModels.add(new ErrorCodeModel("0","Ấn để chọn",false));
        errorCodeModels.add(new ErrorCodeModel("ss","Quên",false));
        errorCodeModels.add(new ErrorCodeModel("ss","Tại",false));
        errorCodeModels.add(new ErrorCodeModel("ss","Bị",false));
        errorCodeModels.add(new ErrorCodeModel("ss","Thì",false));
        errorCodeModels.add(new ErrorCodeModel("ss","Là",false));

        ErrorCodeAdapter errorCodeAdapter = new ErrorCodeAdapter(errorCodeModels,dialog.getContext());
        spinnerError.setAdapter(errorCodeAdapter);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog!=null){
                    dialog.dismiss();
                }
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveQuatity(position,item, Long.parseLong(edtQuatityGood.getText().toString()) ,Long.parseLong(edtQuatityBad.getText().toString()),txtError.getText().toString());
               /* if (dialog!=null){
                    dialog.dismiss();
                }*/
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveQuatity(position,item, Long.parseLong(edtQuatityGood.getText().toString()) ,Long.parseLong(edtQuatityBad.getText().toString()),txtError.getText().toString());
             /*  if (dialog!=null){
                   dialog.dismiss();
               }*/
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
                        for (ErrorCodeModel errorCodeModel : errorCodeModels){
                            if (errorCodeModel.isCheck()){
                                s+=errorCodeModel.getErrorName()+",";
                            }
                        }
                        if (!s.equals("")){
                            txtError.setText(s);
                            txtError.setVisibility(View.VISIBLE);
                        }
                        else {
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
                onPlusClick(edtQuatityGood,(long) item.getPrdcqtty(),Long.parseLong(edtQuatityBad.getText().toString()));
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
                           if (!s.toString().equals("")){
                               long number = Long.parseLong(s.toString());
                               long quatityBad = Long.parseLong(edtQuatityBad.getText().toString());
                               if (number + quatityBad > (long) item.getPrdcqtty()){
                                   edtQuatityGood.setText(String.valueOf((long) item.getPrdcqtty() - quatityBad));
                               }
                           }
                           else {
                               edtQuatityGood.setText("0");
                           }
                       }
                       catch (Exception ex){
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
                            if (!s.toString().equals("")){
                                long number = Long.parseLong(s.toString());
                                long quatityGood = Long.parseLong(edtQuatityGood.getText().toString());
                                if (number + quatityGood > (long) item.getPrdcqtty()){
                                    edtQuatityBad.setText(String.valueOf((long) item.getPrdcqtty() - quatityGood));
                                }
                            }
                            else {
                                edtQuatityBad.setText("0");
                            }
                        }
                        catch (Exception ex){
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

    private void doSaveQuatity(int position,ProgressProductDetailItem item,long numGood, long numBad, String errorCode) {
        try {
            list.get(position).setQuatityBad(numBad);
            list.get(position).setErrorCode(errorCode);
            list.get(position).setQuatityGood(numGood);
            list.get(position).setEdit(true);
            productAdapter.notifyItemChanged(position);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void onSubClick(EditText edt) {
        if (!edt.getText().toString().equals("0")){
            try {
                long number = Long.parseLong(edt.getText().toString());
                number-=1;
                edt.setText(String.valueOf(number));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void onPlusClick(EditText edt, long maxValue, long tempValue) {
        if (!edt.getText().toString().equals("0")){
            try {
                long number = Long.parseLong(edt.getText().toString());
                if (number + tempValue < maxValue){
                    number+=1;
                    edt.setText(String.valueOf(number));
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else {
            edt.setText("1");
        }
    }
}