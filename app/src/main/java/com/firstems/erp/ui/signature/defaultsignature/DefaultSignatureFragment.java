package com.firstems.erp.ui.signature.defaultsignature;

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
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.DefaultSignatureFragmentBinding;
import com.firstems.erp.ui.shared.reviewprocess.ReviewProcessFragment;

public class DefaultSignatureFragment extends Fragment {
    
    private DefaultSignatureViewModel mViewModel;
    private SignatureItemApiResponse signatureItemApiResponse;
    private DefaultSignatureFragmentBinding binding;
    private TextView textViewTitle;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.default_signature_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void addEvents() {
        binding.include25.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
    
    private void addControls() {
        Intent intent = getActivity().getIntent();
        signatureItemApiResponse = (SignatureItemApiResponse) intent.getSerializableExtra(Constant.NAME_PUT_SIGNATURE);
        if (signatureItemApiResponse != null) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_reviews_progress, new ReviewProcessFragment(signatureItemApiResponse.getDcmnCode(), signatureItemApiResponse.getKeyCode()))
                    .commit();
        }
        textViewTitle = binding.include25.findViewById(R.id.txtTitle);
        textViewTitle.setText("Chi tiết chứng từ");
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DefaultSignatureViewModel.class);
    }
    
}