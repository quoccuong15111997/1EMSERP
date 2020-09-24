package com.firstems.erp.ui.document.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.document.DocumentItemApiResponse;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.DocumentDetailFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.file.FileFragment;

public class DocumentDetailFragment extends Fragment {
    
    private DocumentDetailViewModel mViewModel;
    private DocumentDetailFragmentBinding binding;
    private TextView txtTitle;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.document_detail_fragment, container, false);
        
        initText();
        addControls();
        addEvents();
    return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleLoaiTaiLieu.setText(SharedPreferencesManager.getSystemLabel(176) /*Loại tài liệu*/);
        binding.txtTilteSoCongVan.setText(SharedPreferencesManager.getSystemLabel(169) /*Số công văn*/);
        binding.txtTitleNgayPhatHanh.setText(SharedPreferencesManager.getSystemLabel(173) /*Năm phát hành*/);
        binding.txtTitleNoiDungChinh.setText(SharedPreferencesManager.getSystemLabel(175) /*Nội dung chính*/);
        binding.txtTitleNoiDungChiTiet.setText(SharedPreferencesManager.getSystemLabel(171) /*Nội dung chi tiết*/);
        binding.txtTitleNoiPhatHanh.setText(SharedPreferencesManager.getSystemLabel(174) /*Nơi phát hành/ Nơi nhận*/);
        binding.txtTitleTrangThai.setText(SharedPreferencesManager.getSystemLabel(22) /*Trạng thái*/);
    }
    
    private void addEvents() {
        binding.include27.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
    
    private void addControls() {
        Intent intent  = getActivity().getIntent();
        DocumentItemApiResponse documentItemApiResponse = (DocumentItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_ITEM_DOCUMENT);
        if (documentItemApiResponse!=null){
            binding.setItem(documentItemApiResponse);
            FileFragment fileFragment = new FileFragment(documentItemApiResponse.getDocumentFiles());
            fileFragment.setEditable(false);
            fileFragment.setShowChooseFile(false);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_file_include,fileFragment).commit();
        }
        txtTitle = binding.include27.findViewById(R.id.txtTitle);
        txtTitle.setText(SharedPreferencesManager.getSystemLabel(192) /*Chi tiết công văn - Tài liệu*/);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DocumentDetailViewModel.class);
        
    }
    
}