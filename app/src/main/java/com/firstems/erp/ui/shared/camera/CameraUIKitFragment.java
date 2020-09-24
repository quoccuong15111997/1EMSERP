package com.firstems.erp.ui.shared.camera;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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

import com.camerakit.CameraKitView;
import com.firstems.erp.R;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.CameraUIKitFragmentBinding;

import java.io.File;
import java.io.FileOutputStream;

public class CameraUIKitFragment extends Fragment {
    
    private CameraUIKitViewModel mViewModel;
    private CameraKitView cameraKitView;
    private CameraUIKitFragmentBinding binding;
    private TextView txtTitle;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.camera_u_i_kit_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void addEvents() {
        binding.include6.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
                        ContextWrapper cw = new ContextWrapper(getContext());
                        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }
                        String fileName = System.currentTimeMillis()+".png";
                        File mypath = new File(directory, fileName);
                        try {
                            FileOutputStream outputStream = new FileOutputStream(mypath.getPath());
                            outputStream.write(bytes);
                            outputStream.close();
                            returnData(mypath.getAbsolutePath());
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        binding.floatingShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("captune");
                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
                        System.out.println("captune");
                        ContextWrapper cw = new ContextWrapper(getContext());
                        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }
                        String fileName = System.currentTimeMillis()+".png";
                        File mypath = new File(directory, fileName);
                        try {
                            FileOutputStream outputStream = new FileOutputStream(mypath.getPath());
                            outputStream.write(bytes);
                            outputStream.close();
                            returnData(mypath.getAbsolutePath());
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                cameraKitView.captureFrame(new CameraKitView.FrameCallback() {
                    @Override
                    public void onFrame(CameraKitView cameraKitView, byte[] bytes) {
                        System.out.println(bytes.length);
                    }
                });
            }
        });
    }
    
    private void returnData(String absolutePath) {
        Intent intent = new Intent();
        intent.putExtra(Constant.NAME_PUT_PATH_IMAGE,absolutePath);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }
    
    private void addControls() {
        txtTitle = binding.include6.findViewById(R.id.txtTitle);
        txtTitle.setText("Chụp ảnh");
        cameraKitView = binding.camera;
        cameraKitView.requestPermissions(getActivity());
        cameraKitView.setPermissions(CameraKitView.PERMISSION_CAMERA);
        cameraKitView.setPermissions(CameraKitView.PERMISSION_LOCATION);
        cameraKitView.setPermissions(CameraKitView.PERMISSION_MICROPHONE);
        cameraKitView.setPermissions(CameraKitView.PERMISSION_STORAGE);
        cameraKitView.requestPermissions(getActivity());
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CameraUIKitViewModel.class);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }
    
    @Override
    public void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }
    
    @Override
    public void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}