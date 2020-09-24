package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.firstems.erp.R;
import com.firstems.erp.helper.DrawableHelper;

public class EMSConstrainLayoutBorder extends ConstraintLayout {
    public EMSConstrainLayoutBorder(Context context) {
        super(context);
    }

    public EMSConstrainLayoutBorder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSConstrainLayoutBorder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(DrawableHelper.customDrawableWithBorder(getContext(), R.drawable.cutom_edittext_main, Color.WHITE));
    }
}
