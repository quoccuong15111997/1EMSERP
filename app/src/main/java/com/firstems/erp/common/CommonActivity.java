package com.firstems.erp.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.loading.LoadingActivity;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public abstract class CommonActivity extends AppCompatActivity {
    protected Typeface tfRegular;
    protected Typeface tfLight;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }
    protected void setAnimViewVisible(@NotNull final View lParentContent, @NotNull final View vTarget, long duration) {
        Intrinsics.checkParameterIsNotNull(lParentContent, "lParentContent");
        Intrinsics.checkParameterIsNotNull(vTarget, "vTarget");
        
        try {
            (new Handler()).postDelayed((Runnable)(new Runnable() {
                public final void run() {
                    View var10000 = lParentContent;
                    if (var10000 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup)var10000);
                        vTarget.setVisibility(View.VISIBLE);
                    }
                }
            }), duration);
        } catch (Exception var6) {
            var6.printStackTrace();
            vTarget.setVisibility(View.VISIBLE);
        }
        
    }
    
    protected void setAnimViewGone(@NotNull final View lParentContent, @NotNull final View vTarget, long duration) {
        Intrinsics.checkParameterIsNotNull(lParentContent, "lParentContent");
        Intrinsics.checkParameterIsNotNull(vTarget, "vTarget");
        
        try {
            (new Handler()).postDelayed((Runnable)(new Runnable() {
                public final void run() {
                    View var10000 = lParentContent;
                    if (var10000 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup)var10000);
                        vTarget.setVisibility(View.GONE);
                    }
                }
            }), duration);
        } catch (Exception var6) {
            var6.printStackTrace();
            vTarget.setVisibility(View.GONE);
        }
        
    }
    
    protected void setOveridePendingTransisi(@NotNull Activity context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        
        try {
            context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        
    }
    protected void showOutTOKEN() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(false);
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        TextView txtTitle = dialog.findViewById(R.id.title);
        TextView txtContent = dialog.findViewById(R.id.content);
        
        txtTitle.setText(SharedPreferencesManager.getSystemLabel(50));
        txtContent.setText(SharedPreferencesManager.getSystemLabel(201));
        
        AppCompatButton appCompatButton = dialog.findViewById(R.id.bt_close);
        appCompatButton.setText(SharedPreferencesManager.getSystemLabel(4));
        
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(CommonActivity.this, LoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
