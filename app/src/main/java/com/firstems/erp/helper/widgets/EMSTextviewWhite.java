package com.firstems.erp.helper.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;


public class EMSTextviewWhite extends androidx.appcompat.widget.AppCompatTextView {
    public EMSTextviewWhite(Context context) {
        super(context);
    }

    public EMSTextviewWhite(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EMSTextviewWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(Color.parseColor("#FFFFFF"));
    }
}
