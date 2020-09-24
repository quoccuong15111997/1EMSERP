package com.firstems.erp.ui.shared.image;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.adapter.ViewPagerAdapter;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.ViewImageFragmentBinding;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewImageFragment extends CommonFragment {

    private ViewImageViewModel mViewModel;
    private ViewImageFragmentBinding binding;
    private List<String> listUrl;
    private ViewPagerAdapter viewPagerAdapter;
    private List<ImageModel> imageModelList;
    private int position = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.view_image_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void addControls() {
        initProgressDialog(SharedPreferencesManager.getSystemLabel(83),SharedPreferencesManager.getSystemLabel(63));
        progressdialog.show();
    
        imageModelList= new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(imageModelList, getContext());
        binding.viewPager.setAdapter(viewPagerAdapter);
        Intent intent = getActivity().getIntent();
        imageModelList.addAll((Collection<? extends ImageModel>) intent.getSerializableExtra(Constant.NANE_PUT_LIST_BITMAP));
        position = intent.getIntExtra(Constant.NANE_PUT_POSITION_ITEM_CLICK,0);
        
        viewPagerAdapter.notifyDataSetChanged();
        
        binding.viewPager.setCurrentItem(position);
        progressdialog.dismiss();
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewImageViewModel.class);
    }
}