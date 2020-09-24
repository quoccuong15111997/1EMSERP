package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
public class EMSLinearLayout extends LinearLayout {
    public EMSLinearLayout(Context context) {
        super(context);
    }

    public EMSLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EMSLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(new ColorDrawable(Color.parseColor("#019676")));
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(10, 10, 10, 10);
    }
}
