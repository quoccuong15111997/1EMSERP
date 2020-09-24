package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.firstems.erp.R;
import com.firstems.erp.helper.DrawableHelper;

public class EMSButton extends androidx.appcompat.widget.AppCompatButton {


    public EMSButton(Context context) {
        super(context);
    }

    public EMSButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(DrawableHelper.customDrawable(getContext(), R.drawable.custom_button_login,Color.parseColor("#019676")));
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void setAllCaps(boolean allCaps) {
        super.setAllCaps(false);
    }
}
