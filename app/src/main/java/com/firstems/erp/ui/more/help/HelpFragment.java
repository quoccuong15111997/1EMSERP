package com.firstems.erp.ui.more.help;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.databinding.HelpFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.config.ConfigActivity;

import org.mozilla.javascript.annotations.JSGetter;

public class HelpFragment extends Fragment {
    
    private HelpViewModel mViewModel;
    private HelpFragmentBinding binding;
    private TextView txtTitle;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.help_fragment, container, false);
        
        
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void addEvents() {
        binding.include18.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.cardSystemSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ConfigActivity.class);
                startActivity(intent);
            }
        });
        binding.txtCoppyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAbout();
            }
        });
    }
    private void showDialogAbout() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_about_company);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        // ((TextView) dialog.findViewById(R.id.tv_version)).setText("Version " + BuildConfig.VERSION_NAME);
        
        ((View) dialog.findViewById(R.id.bt_getcode)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAction(getActivity());
            }
        });
        
        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        ((Button) dialog.findViewById(R.id.bt_rate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAction(getActivity());
            }
        });
        
        ((Button) dialog.findViewById(R.id.bt_portfolio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://firstems.com/Default.aspx"));
                startActivity(browserIntent);
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    public static void rateAction(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }
    private void addControls() {
        txtTitle=binding.include18.findViewById(R.id.txtTitle);
        txtTitle.setText("Cài đặt");
        binding.txtCoppyright.setText(SharedPreferencesManager.getSystemLabel(212) /*Copyright © 2020 FirstEMS IT SOLUTION Co., LTD.*/);
        try {
            binding.textView48.setText("ERP "+getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        };
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HelpViewModel.class);
        
    }
    
}