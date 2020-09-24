package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class EMSFrameLayout extends FrameLayout {
    public EMSFrameLayout(@NonNull Context context) {
        super(context);
    }

    public EMSFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EMSFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(new ColorDrawable(Color.parseColor(SharedPreferencesManager.getInstance().getBacgroundColor())));
    }
}
