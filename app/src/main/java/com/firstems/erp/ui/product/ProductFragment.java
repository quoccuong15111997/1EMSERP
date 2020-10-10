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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.progress.ProductAdapter;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.common.Util;
import com.firstems.erp.databinding.ProductFragmentBinding;
import com.firstems.erp.helper.animation.AnimationHelper;
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
            public void ontemClick(ProgressProductDetailItem item) {
                showInputQuatity(item);
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
    private void showInputQuatity(ProgressProductDetailItem item) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_input_quatity);
        dialog.setCancelable(true);

        AppBarLayout appBarLayout = dialog.findViewById(R.id.app_bar_layout);
        ImageView imgClose = appBarLayout.findViewById(R.id.bt_close);
        ImageView imgSave = appBarLayout.findViewById(R.id.bt_save);
        TextView txtTitle = appBarLayout.findViewById(R.id.txtTitleDialog);
        EditText edtQuatityGood = dialog.findViewById(R.id.edtQuatityGood);
        EditText edtQuatityBad = dialog.findViewById(R.id.edtQuatityBad);
        TextView txtError = dialog.findViewById(R.id.txtError);
        TextView txtSumQuatity = dialog.findViewById(R.id.txtSumQuatity);
        Spinner spinnerError = dialog.findViewById(R.id.spinnerError);

        txtTitle.setText(item.getPrdcname());
        txtSumQuatity.setText(String.valueOf((long) item.getPrdcqtty()));


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}