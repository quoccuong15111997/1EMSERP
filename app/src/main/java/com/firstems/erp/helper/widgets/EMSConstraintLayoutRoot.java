package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class EMSConstraintLayoutRoot extends ConstraintLayout {
    public EMSConstraintLayoutRoot(Context context) {
        super(context);
    }

    public EMSConstraintLayoutRoot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSConstraintLayoutRoot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(new ColorDrawable(Color.parseColor(SharedPreferencesManager.getInstance().getBacgroundColor())));
    }
}
