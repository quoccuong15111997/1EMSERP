package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.firstems.erp.R;
import com.firstems.erp.helper.DrawableHelper;

public class EMSButtonSecond extends androidx.appcompat.widget.AppCompatButton {


    public EMSButtonSecond(Context context) {
        super(context);
    }

    public EMSButtonSecond(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSButtonSecond(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(DrawableHelper.customDrawable(getContext(), R.drawable.custom_button_login,Color.parseColor("#f89633")));
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(Color.parseColor("#FFFFFF"));
    }
}
