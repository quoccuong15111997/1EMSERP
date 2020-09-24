package com.firstems.erp.ui.document.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DocumentAdapter;
import com.firstems.erp.api.model.response.document.DocumentItemApiResponse;
import com.firstems.erp.callback.BottomSheSortCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.DocumnetListFragmentBinding;
import com.firstems.erp.model.DocumentSortBit;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.document.detail.DocumnetDetailActivity;
import com.firstems.erp.utils.ItemAnimation;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DocumnetListFragment extends CommonFragment {
    
    private DocumnetListViewModel mViewModel;
    private DocumnetListFragmentBinding binding;
    private DocumentAdapter documentAdapter;
    private List<DocumentItemApiResponse> documentItemApiResponses;
    private List<DocumentItemApiResponse> listCurrent;
    private String queryString = "";
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    private DocumentSortBit documentSortBit;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.documnet_list_fragment, container, false) ;
        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.etSearch.setHint(SharedPreferencesManager.getSystemLabel(184) /*Tìm kiếm nhanh*/);
    }
    
    private void addEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        documentAdapter.setDocumentItemClickListener(new DocumentAdapter.DocumentItemClickListener() {
            @Override
            public void onDocumentItemClick(int position, DocumentItemApiResponse item) {
                Intent intent= new Intent(getContext(), DocumnetDetailActivity.class);
                intent.putExtra(Constant.NAME_PUT_ITEM_DOCUMENT, item);
                startActivity(intent);
            }
        });
        binding.imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogSort(new BottomSheSortCallback() {
                    @Override
                    public void onComplteCheck() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            doSort();
                        }
                    }
                });
            }
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                doSearchAction(editable.toString().toLowerCase().trim());
            }
        });
    }
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void doSort() {
        documentSortBit.resetValue();
        if (documentSortBit.isIs_btnOldToNew() || documentSortBit.isIs_btnNewToOld()){
            if (documentSortBit.isIs_btnOldToNew()){
                if (documentSortBit.isIs_btnAZ()){
                    Collections.sort(documentItemApiResponses, Comparator.comparing( DocumentItemApiResponse::getBegDateTypeDate, Date :: compareTo)
                            .thenComparing(DocumentItemApiResponse::getcNTNBRIF, String :: compareTo));
                }
                else if (documentSortBit.isIs_btnZA()){
                    Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getBegDateTypeDate, Date :: compareTo)
                            .thenComparing(DocumentItemApiResponse::getcNTNBRIF, (s, t1) -> t1.compareTo(s)));
                }
                else {
                    Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getBegDateTypeDate, Date :: compareTo));
                }
            }
            if (documentSortBit.isIs_btnNewToOld()){
                if (documentSortBit.isIs_btnAZ()){
                    
                    Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getBegDateTypeDate,(date, t1) -> t1.compareTo(date))
                            .thenComparing(DocumentItemApiResponse::getcNTNBRIF, String :: compareTo));
                }
                else if (documentSortBit.isIs_btnZA()){
                    
                    Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getBegDateTypeDate,(date, t1) -> t1.compareTo(date))
                            .thenComparing(DocumentItemApiResponse::getcNTNBRIF, (s, t1) -> t1.compareTo(s)));
                }
                else {
                    Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getBegDateTypeDate,(date, t1) -> t1.compareTo(date)));
                }
            }
        }
        else {
            if (documentSortBit.isIs_btnAZ()){
                Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getcNTNBRIF, String :: compareTo));
            }
            if (documentSortBit.isIs_btnZA()){
                Collections.sort(documentItemApiResponses, Comparator.comparing(DocumentItemApiResponse::getcNTNBRIF,(s, t1) -> t1.compareTo(s)));
            }
        }
        binding.recycleview.post(new Runnable() {
            @Override
            public void run() {
                documentAdapter.notifyDataSetChanged();
            }
        });
        
    }
    
    private void doSearchAction(String s) {
        if (!s.equals("")){
            documentItemApiResponses.clear();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                documentItemApiResponses.addAll(listCurrent
                        .stream()
                        .filter(x -> x.cNTNBRIF.toLowerCase().contains(s) || x.mAINNUMB.toLowerCase().contains(s))
                        .collect(Collectors.toList()));
            }
        }
        binding.recycleview.post(new Runnable() {
            @Override
            public void run() {
                documentAdapter.notifyDataSetChanged();
            }
        });
    }
    
    private void addControls() {
        showLoadingNonMessDialog();
        
        Intent intent = getActivity().getIntent();
        queryString = intent.getStringExtra(Constant.NAME_PUT_QUERY_STRING);
        
        listCurrent = new ArrayList<>();
        documentItemApiResponses = new ArrayList<>();
        documentAdapter = new DocumentAdapter(documentItemApiResponses);
        documentAdapter.setAnimation_type(ItemAnimation.RIGHT_LEFT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleview.setAdapter(documentAdapter);
        binding.recycleview.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        binding.recycleview.addItemDecoration(dividerItemDecoration);
        
        bottom_sheet = binding.bottomSheet;
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        documentSortBit = new DocumentSortBit();
        
        binding.etSearch.clearFocus();
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DocumnetListViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.loaData(queryString);
        mViewModel.getMutableLiveDataDocument().observe(getViewLifecycleOwner(), new Observer<List<DocumentItemApiResponse>>() {
            @Override
            public void onChanged(List<DocumentItemApiResponse> list) {
                documentItemApiResponses.clear();
                listCurrent.clear();
                listCurrent.addAll(list);
                documentItemApiResponses.addAll(listCurrent);
                binding.recycleview.post(new Runnable() {
                    @Override
                    public void run() {
                        documentAdapter.notifyDataSetChanged();
                        loadingNonMessDialog.dismiss();
                    }
                });
            }
        });
    }
    private void showBottomSheetDialogSort(BottomSheSortCallback bottomSheSortCallback) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        final View view = getLayoutInflater().inflate(R.layout.sheet_floating, null);
        Button btnOk;
        Button btnAZ, btnZA, btnNewToOld, btnOldToNew;
        TextView txtTitleTime, txtTitleSort;
        txtTitleTime = view.findViewById(R.id.txtTitleTime);
        txtTitleTime.setText(SharedPreferencesManager.getSystemLabel(185) /*Thời gian*/);
        txtTitleSort = view.findViewById(R.id.txtTitleSort);
        txtTitleSort.setText(SharedPreferencesManager.getSystemLabel(186) /*Sắp xếp*/);
        btnAZ = view.findViewById(R.id.btnAZ);
        btnAZ.setText(SharedPreferencesManager.getSystemLabel(190) /*A ==> Z*/);
        btnZA = view.findViewById(R.id.btnZA);
        btnZA.setText(SharedPreferencesManager.getSystemLabel(191) /*Z ==> A*/);
        btnOk = view.findViewById(R.id.btnOk);
        btnOk.setText(SharedPreferencesManager.getSystemLabel(187) /*Xem kết quả*/);
        btnNewToOld = view.findViewById(R.id.btnNew);
        btnNewToOld.setText(SharedPreferencesManager.getSystemLabel(188) /*Mới ==> Cũ*/);
        btnOldToNew = view.findViewById(R.id.btnOld);
        btnOldToNew.setText(SharedPreferencesManager.getSystemLabel(189) /*Cũ ==> Mới*/);
        
        restoreStateSort(btnAZ,documentSortBit.isIs_btnAZ());
        restoreStateSort(btnZA,documentSortBit.isIs_btnZA());
        restoreStateSort(btnNewToOld,documentSortBit.isIs_btnNewToOld());
        restoreStateSort(btnOldToNew,documentSortBit.isIs_btnOldToNew());
        
        documentSortBit.resetValue();
        
        btnAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = outletTypeClick(btnAZ);
                btnZA.setSelected(false);
                btnZA.setTextColor(getResources().getColor(R.color.grey_40));
                documentSortBit.setIs_btnAZ(res);
                documentSortBit.setIs_btnZA(false);
            }
        });
        btnZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = outletTypeClick(btnZA);
                btnAZ.setSelected(false);
                btnAZ.setTextColor(getResources().getColor(R.color.grey_40));
                documentSortBit.setIs_btnZA(res);
                documentSortBit.setIs_btnAZ(false);
            }
        });
        btnNewToOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = outletTypeClick(btnNewToOld);
                btnOldToNew.setSelected(false);
                btnOldToNew.setTextColor(getResources().getColor(R.color.grey_40));
                documentSortBit.setIs_btnNewToOld(res);
                documentSortBit.setIs_btnOldToNew(false);
            }
        });
        btnOldToNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = outletTypeClick(btnOldToNew);
                btnNewToOld.setSelected(false);
                btnNewToOld.setTextColor(getResources().getColor(R.color.grey_40));
                documentSortBit.setIs_btnOldToNew(res);
                documentSortBit.setIs_btnNewToOld(false);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheSortCallback.onComplteCheck();
                if (mBottomSheetDialog!=null){
                    mBottomSheetDialog.dismiss();
                }
            }
        });
        mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        
        // set background transparent
        ((View) view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        
    }
    private boolean outletTypeClick(Button view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            if (b.isSelected()) {
                b.setTextColor(getResources().getColor(R.color.grey_40));
            } else {
                b.setTextColor(Color.WHITE);
            }
            b.setSelected(!b.isSelected());
        }
        return view.isSelected();
    }
    private void restoreStateSort(Button btn,boolean isCheck){
        btn.setSelected(isCheck);
        if (!isCheck) {
            btn.setTextColor(getResources().getColor(R.color.grey_40));
        } else {
            btn.setTextColor(Color.WHITE);
        }
        
    }
}