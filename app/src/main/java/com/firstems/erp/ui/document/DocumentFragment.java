package com.firstems.erp.ui.document;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.content.ContentItem;
import com.firstems.erp.api.model.response.document_category.DocumentCategory;
import com.firstems.erp.api.model.response.export_place.ExportPlace;
import com.firstems.erp.api.model.response.location.LocationItem;
import com.firstems.erp.callback.BackToHomeCallback;
import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.callback.QueryStringCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.DocumentFragmentBinding;
import com.firstems.erp.helper.dialog.DatePickerDialog;
import com.firstems.erp.helper.query.QueryStringHelper;
import com.firstems.erp.model.QueryModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.document.list.DocumentListActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DocumentFragment extends CommonFragment {
    
    private DocumentViewModel mViewModel;
    private DocumentFragmentBinding binding;
    private View view;
    private TextView txtOpenGrapSearch, txtCloseGrapSearch;
    private BackToHomeCallback backToHomeCallback;
    private ArrayAdapter adapterLocation;
    private List<LocationItem>  locationResponseList;
    private Date dateBegin, dateEnd;
    private ArrayAdapter adapterExportPlace, adapterContent, adapterCategory;
    private Spinner spinnerExportPlace, spinnerContent, spinnerCategory;
    public DocumentFragment(BackToHomeCallback backToHomeCallback) {
        this.backToHomeCallback = backToHomeCallback;
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.document_fragment, container, false);
        view = binding.getRoot();
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
                        TransitionManager.beginDelayedTransition(binding.layoutGraphsearch);
                        binding.layoutGraphsearch.setVisibility(View.VISIBLE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 700);
        } catch (Exception ex) {
            binding.layoutGraphsearch.setVisibility(View.VISIBLE);
            ex.printStackTrace();
        }
    }
    private void setText() {
        binding.txtTitleDocumentCode.setText(SharedPreferencesManager.getSystemLabel(169) /*Số công văn*/);
        binding.txtTitleDocumentName.setText(SharedPreferencesManager.getSystemLabel(170) /*Tên công văn*/);
        binding.txtTitleInfo.setText(SharedPreferencesManager.getSystemLabel(171) /*Nội dung chi tiết*/);
        binding.txtOpenGraphSearch.setText(SharedPreferencesManager.getSystemLabel(172) /*Thêm điều kiện tìm kiếm*/);
        binding.txtCloseGraphSearch.setText(SharedPreferencesManager.getSystemLabel(180) /*Ẩn điều kiện tìm kiếm*/);
        binding.txtTitleReleaseYear.setText(SharedPreferencesManager.getSystemLabel(173) /*Năm phát hành*/);
        binding.txtTitleLocate.setText(SharedPreferencesManager.getSystemLabel(174) /*Nơi phát hành/ Nơi nhận*/);
        binding.txtTitleContent.setText(SharedPreferencesManager.getSystemLabel(175) /*Nội dung chính*/);
        binding.txtTitleStack.setText(SharedPreferencesManager.getSystemLabel(179) /*Ngăn tủ chứa tài liệu*/);
        binding.txtTitleCategory.setText(SharedPreferencesManager.getSystemLabel(176) /*Loại tài liệu*/);
        binding.txtTitleCompanyBranch.setText(SharedPreferencesManager.getSystemLabel(177) /*Chi nhánh công ty*/);
        binding.txtTu.setText(SharedPreferencesManager.getSystemLabel(183) /*Từ*/);
        binding.txtDen.setText(SharedPreferencesManager.getSystemLabel(20) /*đến*/);
        binding.button.setText(SharedPreferencesManager.getSystemLabel(182) /*Tìm công văn*/);
        binding.chkSearchByTime.setText(SharedPreferencesManager.getSystemLabel(178) /*Lọc theo thời gian*/);
        binding.edtDocumentName.setHint(SharedPreferencesManager.getSystemLabel(204) /*Nhập tên công văn*/);
        binding.edtInfo.setHint(SharedPreferencesManager.getSystemLabel(205) /*Nhập nội dung chi tiết*/);
    }
    
    private void addEvents() {
        binding.layoutDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = 0;
                long maxDate = dateEnd!=null ? dateEnd.getTime() : 0;
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateBegin = date;
                        binding.txtDateFrom.setText(simpleDateFormatDisplay.format(dateBegin));
                    }
                });
            }
        });
        binding.layoutDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long minDate = dateBegin!=null ? dateBegin.getTime() : 0;
                long maxDate = 0;
                DatePickerDialog.getInstance().showDialogSelectDate(minDate, maxDate, getContext(), new PickDateCallback() {
                    @Override
                    public void onDatePicker(Date date) {
                        dateEnd = date;
                        binding.txtDateTo.setText(simpleDateFormatDisplay.format(dateEnd));
                    }
                });
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSearch();
            }
        });
        txtOpenGrapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOpenGrapSearch.setVisibility(View.GONE);
                txtCloseGrapSearch.setVisibility(View.VISIBLE);
                binding.layoutGraphsearch.setVisibility(View.VISIBLE);
            }
        });
        txtCloseGrapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOpenGrapSearch.setVisibility(View.VISIBLE);
                txtCloseGrapSearch.setVisibility(View.GONE);
                binding.layoutGraphsearch.setVisibility(View.GONE);
            }
        });
        binding.include.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeCallback.onBackPress();
            }
        });
    }
    
    private void doSearch() {
        List<QueryModel> queryModelList = new ArrayList<>();
        if (createQueryModel(binding.edtDocumentCode,"MAINNUMB")!=null){
            queryModelList.add(createQueryModel(binding.edtDocumentCode,"MAINNUMB"));
        }
        
        if (createQueryModel(binding.edtDocumentName,"CNTNBRIF")!=null){
            queryModelList.add(createQueryModel(binding.edtDocumentName,"CNTNBRIF"));
        }
        
        if (createQueryModel(binding.edtInfo,"CNTNDCMN")!=null){
            queryModelList.add(createQueryModel(binding.edtInfo,"CNTNDCMN"));
        }
        
        if (createQueryModel(binding.edtReleaseYear,"BEG_DATE")!=null){
            queryModelList.add(createQueryModel(binding.edtReleaseYear,"BEG_DATE"));
        }
        if (spinnerExportPlace.getSelectedItemPosition()!=0){
            ExportPlace exportPlace = (ExportPlace) spinnerExportPlace.getSelectedItem();
            if (exportPlace!=null){
                queryModelList.add(new QueryModel("INPDCMNPBLS.PBLSCODE",exportPlace.getiTEMCODE()));
            }
        }
        if (spinnerCategory.getSelectedItemPosition()!=0){
            DocumentCategory documentCategory = (DocumentCategory) spinnerCategory.getSelectedItem();
            if (documentCategory!=null){
                queryModelList.add(new QueryModel("INPDCMNTYPE.DCTPCODE",documentCategory.getiTEMCODE()));
            }
        }
        if (spinnerContent.getSelectedItemPosition()!=0){
            ContentItem contentItem = (ContentItem) spinnerContent.getSelectedItem();
            if (contentItem!=null){
                queryModelList.add(new QueryModel("CNTNCODE",contentItem.getiTEMCODE()));
            }
        }
        if (binding.spinnerCompanyBranch.getSelectedItemPosition()!=0){
            LocationItem locationItem = (LocationItem) binding.spinnerCompanyBranch.getSelectedItem();
            if (locationItem!=null){
                queryModelList.add(new QueryModel("LCTNCODE",locationItem.getItemCode()));
            }
        }
        new QueryStringHelper(queryModelList).builder(new QueryStringCallback() {
            @Override
            public void onSuccess(String query) {
                if (binding.chkSearchByTime.isChecked()){
                    if (!query.equals("")){
                        query += "AND (BEG_DATE >= '"+simpleDateFormatSystem.format(dateBegin)+"' AND BEG_DATE <= '"+simpleDateFormatSystem.format(dateEnd)+"')";
                    }
                    else {
                       query += " (BEG_DATE >= '"+simpleDateFormatSystem.format(dateBegin)+"' AND BEG_DATE <= '"+simpleDateFormatSystem.format(dateEnd)+"')";
                    }
                }
                Intent intent = new Intent(getContext(), DocumentListActivity.class);
                intent.putExtra(Constant.NAME_PUT_QUERY_STRING, query);
                startActivity(intent);
            }
        });
    }
    private QueryModel createQueryModel(EditText editText,String key){
        if (!editText.getText().toString().equals("")){
            return new QueryModel(key,editText.getText().toString());
        }
        return null;
    }
    
    
    private void addControls() {
        txtOpenGrapSearch=binding.txtOpenGraphSearch;
        txtCloseGrapSearch=binding.txtCloseGraphSearch;
        locationResponseList = new ArrayList<>();
        
        adapterLocation = new ArrayAdapter(getContext(), R.layout.spiner_item, locationResponseList);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCompanyBranch.setAdapter(adapterLocation);
        
        spinnerExportPlace = binding.spinerLocate;
        adapterExportPlace = new ArrayAdapter(getContext(),R.layout.spiner_item);
        adapterExportPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExportPlace.setAdapter(adapterExportPlace);
        
        spinnerContent = binding.spinerContent;
        adapterContent = new ArrayAdapter(getContext(),R.layout.spiner_item);
        adapterContent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContent.setAdapter(adapterContent);
        
        spinnerCategory = binding.spinerCategory;
        adapterCategory = new ArrayAdapter(getContext(),R.layout.spiner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DocumentViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView txttitle = binding.include.findViewById(R.id.txtTitle);
                txttitle.setText(s);
            }
        });
        mViewModel.getLiveDataLocation().observe(getViewLifecycleOwner(), new Observer<List<LocationItem>>() {
            @Override
            public void onChanged(List<LocationItem> locationResponses) {
                locationResponseList.clear();
                locationResponseList.addAll(locationResponses);
                adapterLocation.notifyDataSetChanged();
            }
        });
        mViewModel.getLiveDataExportPlace().observe(getViewLifecycleOwner(), new Observer<List<ExportPlace>>() {
            @Override
            public void onChanged(List<ExportPlace> exportPlaces) {
                ExportPlace exportPlace = new ExportPlace();
                exportPlace.setiTEMNAME(SharedPreferencesManager.getSystemLabel(138));
                exportPlace.setiTEMCODE("00000");
                adapterExportPlace.add(exportPlace);
                adapterExportPlace.addAll(exportPlaces);
                adapterExportPlace.notifyDataSetChanged();
            }
        });
        mViewModel.getLiveDataContent().observe(getViewLifecycleOwner(), new Observer<List<ContentItem>>() {
            @Override
            public void onChanged(List<ContentItem> contentItems) {
                ContentItem contentItem = new ContentItem();
                contentItem.setiTEMNAME(SharedPreferencesManager.getSystemLabel(138));
                contentItem.setiTEMCODE("0000");
                adapterContent.add(contentItem);
                adapterContent.addAll(contentItems);
                adapterContent.notifyDataSetChanged();
            }
        });
        mViewModel.getLiveDataDocumentCategory().observe(getViewLifecycleOwner(), new Observer<List<DocumentCategory>>() {
            @Override
            public void onChanged(List<DocumentCategory> documentCategories) {
                DocumentCategory documentCategory = new DocumentCategory();
                documentCategory.setiTEMNAME(SharedPreferencesManager.getSystemLabel(138));
                documentCategory.setiTEMCODE("0000");
                adapterCategory.add(documentCategory);
                adapterCategory.addAll(documentCategories);
                adapterCategory.notifyDataSetChanged();
            }
        });
    }
    
}