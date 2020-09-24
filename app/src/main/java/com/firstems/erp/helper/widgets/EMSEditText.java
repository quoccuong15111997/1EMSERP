package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.util.AttributeSet;

public class EMSEditText extends androidx.appcompat.widget.AppCompatEditText {
    public EMSEditText(Context context) {
        super(context);
    }

    public EMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   /* @Override
    public void setBackground(Drawable background) {
        super.setBackground(DrawableHelper.customDrawableWithBorder(getContext(), R.drawable.cutom_edittext_main, Color.WHITE));
    }*/
}
